package mapfood.service;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;
import com.vividsolutions.jts.geom.Point;
import mapfood.exceptions.ClienteMuitoDistanteException;
import mapfood.exceptions.ClienteNaoEncontradoException;
import mapfood.exceptions.EstabelecimentoNaoEncontradoException;
import mapfood.factory.PointFactory;
import mapfood.model.dto.MotoboyDTO;
import mapfood.model.dto.SaidaDTO;
import mapfood.model.dto.SolicitacaoEntrega;
import mapfood.model.mongodb.Estabelecimento;
import mapfood.spatial.CoordinateComparator;
import mapfood.spatial.GeoUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RotasServiceByGoogleDirectionsApi implements RotasService {

    private final GeoApiContext apiContext;

    private final EstabelecimentoService estabelecimentoService;
    private final ClienteService clienteService;
    private final MotoboyService motoboyService;

    @Value("${mapfood.valores.consumo-motocicleta}")
    private Double consumoMotocicleta;

    @Value("${mapfood.valores.limite-distancia-entrega}")
    private Double limiteKmEntrega;

    @Value("${mapfood.valores.limite-distancia-motoboy}")
    private Double limiteDistanciaMotoboy;

    @Value("${mapfood.valores.tempo-preparo}")
    private Integer tempoPreparo;

    public RotasServiceByGoogleDirectionsApi(GeoApiContext apiContext, EstabelecimentoService estabelecimentoService, ClienteService clienteService, MotoboyService motoboyService) {
        this.apiContext = apiContext;
        this.estabelecimentoService = estabelecimentoService;
        this.clienteService = clienteService;
        this.motoboyService = motoboyService;
    }

    @Override
    public SaidaDTO getMelhorRotaPara(SolicitacaoEntrega solicitacaoEntrega) {

        LatLng latLngEstabelecimento = getLatLngEstabelecimento(solicitacaoEntrega);
        List<LatLng> waypoints = getLatLngClientes(solicitacaoEntrega, latLngEstabelecimento);

        // Criar retorno da api mapfood com dados do DirectionsResult.
        MotoboyDTO maisProximo = motoboyService.buscaMaisProximo(latLngEstabelecimento.lat, latLngEstabelecimento.lng, limiteKmEntrega)
                .orElseThrow(RuntimeException::new);

        LatLng latLngMotoboy = new LatLng(maisProximo.getLatitude(), maisProximo.getLongitude());
        waypoints.add(0, latLngEstabelecimento);

        DirectionsResult result = this.solicitaRotaAoServico(latLngMotoboy, waypoints);

        if (result == null) {
            throw new RuntimeException("Ocorreu um erro ao retornar a rota.");
        }

        long distanciaTotalEmMetros = 0;
        long duracaoTotal = 0;

        for (DirectionsRoute route : result.routes) {
            for (DirectionsLeg leg : route.legs) {
                distanciaTotalEmMetros += leg.distance.inMeters;
                duracaoTotal = leg.duration.inSeconds;
            }
        }

        double consumoCombustivel = (double) (distanciaTotalEmMetros / 1000) / consumoMotocicleta;

        SaidaDTO saidaDTO = new SaidaDTO();
        saidaDTO.setDistanciaEmMetros(distanciaTotalEmMetros);
        saidaDTO.setDuracaoEmSegundos(duracaoTotal);
        saidaDTO.setConsumoCombustivel(consumoCombustivel);
        saidaDTO.setIdMotoboy(maisProximo.getId());
        saidaDTO.setRota(result.routes);

        return saidaDTO;
    }

    private LatLng getLatLngEstabelecimento(SolicitacaoEntrega solicitacaoEntrega) {
        Estabelecimento estabelecimento = estabelecimentoService.findById(solicitacaoEntrega.getIdEstabelecimento())

                // TODO: Melhorar validação.
                .orElseThrow(EstabelecimentoNaoEncontradoException::new);


        return new LatLng(estabelecimento.getLatitude(), estabelecimento.getLongitude());
    }

    private List<LatLng> getLatLngClientes(SolicitacaoEntrega solicitacaoEntrega, LatLng origin) {
        List<LatLng> latLngs = solicitacaoEntrega.getPedidos()
                .stream()
                .map(pedido ->
                        clienteService.buscaPorId(
                                pedido.getIdCliente())
                                .orElseThrow(ClienteNaoEncontradoException::new))

                .map(dto -> new LatLng(dto.getLatitude(), dto.getLongitude()))

                .sorted(new CoordinateComparator(origin).getLatLngComparator())

                .collect(Collectors.toList());

        PointFactory pointFactory = new PointFactory();


        Point originPoint = pointFactory.fromLatLong(origin.lat, origin.lng);

        List<LatLng> pontosForaDaArea = latLngs
                .stream()
                .map(pointFactory::fromLatLng)
                .filter(destPoint -> GeoUtils.degreesToMeters(originPoint.distance(destPoint)) > (limiteKmEntrega * 1000))
                .map(point -> new LatLng(point.getY(), point.getX()))
                .collect(Collectors.toList());

        if (!pontosForaDaArea.isEmpty()) {
            throw new ClienteMuitoDistanteException(pontosForaDaArea);
        }

        return latLngs;
    }

    private DirectionsResult solicitaRotaAoServico(LatLng origin, List<LatLng> waypoints) {

        LatLng destination = waypoints.get(waypoints.size() - 1);
        waypoints.remove(destination);

        Instant horaSaida = calcularHoraSaida(waypoints.size());

        try {
            return DirectionsApi.newRequest(apiContext)
                    .origin(origin)
                    .waypoints(waypoints.toArray(new LatLng[]{}))
                    .destination(destination)
                    .optimizeWaypoints(true)
                    .departureTime(horaSaida)
                    .mode(TravelMode.DRIVING)
                    .trafficModel(TrafficModel.BEST_GUESS)
                    .await(); // await ou callback??

        } catch (ApiException | InterruptedException | IOException e) {
            // TODO: incluir exception generica unchecked de preferencia.
            e.printStackTrace();
            return null;
        }
    }

    private Instant calcularHoraSaida(int size) {
        return Instant.now().plus(Duration.ofMinutes(size * tempoPreparo));
    }
}
