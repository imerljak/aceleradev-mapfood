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
import mapfood.model.dto.*;
import mapfood.model.mongodb.Estabelecimento;
import mapfood.spatial.GeoUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RotasServiceImpl implements RotasService {

    private final GeoApiContext apiContext;

    private final ApplicationEventPublisher publisher;

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

    public RotasServiceImpl(GeoApiContext apiContext, ApplicationEventPublisher publisher, EstabelecimentoService estabelecimentoService, ClienteService clienteService, MotoboyService motoboyService) {
        this.apiContext = apiContext;
        this.publisher = publisher;
        this.estabelecimentoService = estabelecimentoService;
        this.clienteService = clienteService;
        this.motoboyService = motoboyService;
    }

    @Override
    public ResultadoRota getMelhorRotaPara(SolicitacaoEntrega solicitacaoEntrega) {

        LatLng posicaoEstabelecimento = getLatLngEstabelecimento(solicitacaoEntrega.getIdEstabelecimento());

        List<ClienteDTO> clientesDosPedidos = getClientesDosPedidos(solicitacaoEntrega.getPedidos());

        validaTodosClientesDentroDaDistanciaLimite(clientesDosPedidos, posicaoEstabelecimento);

        MotoboyDTO motoboyMaisProximo = getMotoboyMaisProximo(posicaoEstabelecimento);

        List<LatLng> posicaoDosClientes = posicaoDosClientes(clientesDosPedidos);

        /*Adiciona estabelecimento como primeiro ponto de parada do motoboy*/
        posicaoDosClientes.add(0, posicaoEstabelecimento);

        LatLng posicaoMotoboy = new LatLng(motoboyMaisProximo.getLatitude(), motoboyMaisProximo.getLongitude());
        DirectionsResult result = this.solicitaRotaAoServico(posicaoMotoboy, posicaoDosClientes);

        if (result == null) {
            throw new RuntimeException("Ocorreu um erro ao retornar a rota.");
        }

        final ResultadoRota resultadoRota = constroiResultadoApi(solicitacaoEntrega.getIdEstabelecimento(), motoboyMaisProximo, result);

        publisher.publishEvent(resultadoRota);
        return resultadoRota;
    }

    private ResultadoRota constroiResultadoApi(String idEstabelecimento, MotoboyDTO motoboyMaisProximo, DirectionsResult result) {
        long distanciaTotalEmMetros = 0;
        long duracaoTotal = 0;

        for (DirectionsRoute route : result.routes) {
            for (DirectionsLeg leg : route.legs) {
                distanciaTotalEmMetros += leg.distance.inMeters;
                duracaoTotal = leg.duration.inSeconds;
            }
        }

        double consumoCombustivel = (double) (distanciaTotalEmMetros / 1000) / consumoMotocicleta;

        ResultadoRota resultadoRota = new ResultadoRota();
        resultadoRota.setIdEstabelecimento(idEstabelecimento);
        resultadoRota.setDistanciaEmMetros(distanciaTotalEmMetros);
        resultadoRota.setDuracaoEmSegundos(duracaoTotal);
        resultadoRota.setConsumoCombustivel(consumoCombustivel);
        resultadoRota.setIdMotoboy(motoboyMaisProximo.getId());
        resultadoRota.setRota(result.routes);
        return resultadoRota;
    }

    private List<LatLng> posicaoDosClientes(List<ClienteDTO> clientesDosPedidos) {
        return clientesDosPedidos.parallelStream()
                .map(clienteDTO -> new LatLng(clienteDTO.getLatitude(), clienteDTO.getLongitude()))
                .collect(Collectors.toList());
    }

    private void validaTodosClientesDentroDaDistanciaLimite(List<ClienteDTO> clientesDosPedidos, LatLng posicaoEstabelecimento) {

        PointFactory pointFactory = new PointFactory();
        Point pontoEstabelecimento = pointFactory.fromLatLng(posicaoEstabelecimento);

        clientesDosPedidos.parallelStream()
                .forEach(clienteDTO -> {
                    Point pontoCliente = pointFactory.fromLatLong(clienteDTO.getLatitude(), clienteDTO.getLongitude());
                    double distanciaEmKilometros = GeoUtils.haversineDistance(pontoEstabelecimento, pontoCliente);

                    if (distanciaEmKilometros > limiteKmEntrega) {
                        throw new ClienteMuitoDistanteException(clienteDTO);
                    }
                });
    }

    private MotoboyDTO getMotoboyMaisProximo(LatLng latLngEstabelecimento) {
        return motoboyService.buscaMaisProximo(latLngEstabelecimento.lat, latLngEstabelecimento.lng, limiteKmEntrega)
                .orElseThrow(RuntimeException::new);
    }

    private LatLng getLatLngEstabelecimento(String idEstabelecimento) {
        Estabelecimento estabelecimento = estabelecimentoService.findById(idEstabelecimento)
                .orElseThrow(() -> new EstabelecimentoNaoEncontradoException(idEstabelecimento));

        return new LatLng(estabelecimento.getLatitude(), estabelecimento.getLongitude());
    }

    private List<ClienteDTO> getClientesDosPedidos(List<Pedido> pedidos) {
        return pedidos
                .stream()
                .map(Pedido::getIdCliente)
                .map(idCliente -> clienteService.buscaPorId(idCliente).orElseThrow(() -> new ClienteNaoEncontradoException(idCliente)))
                .collect(Collectors.toList());
    }

    private DirectionsResult solicitaRotaAoServico(LatLng origem, List<LatLng> paradas) {

        LatLng destino = paradas.remove(paradas.size() - 1);

        Instant horaSaida = calcularHoraSaida(paradas.size());

        try {
            return DirectionsApi.newRequest(apiContext)
                    .origin(origem)
                    .waypoints(paradas.toArray(new LatLng[]{}))
                    .destination(destino)
                    .optimizeWaypoints(true)
                    .departureTime(horaSaida)
                    .mode(TravelMode.DRIVING)
                    .trafficModel(TrafficModel.BEST_GUESS)
                    .await(); // await ou callback??

        } catch (ApiException | InterruptedException | IOException e) {
            System.out.println("Erro ao solicitar rota!");
            e.printStackTrace();
            return null;
        }
    }

    private Instant calcularHoraSaida(int size) {
        return Instant.now().plus(Duration.ofMinutes(size * tempoPreparo));
    }
}
