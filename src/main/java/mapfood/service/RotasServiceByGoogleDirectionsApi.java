package mapfood.service;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TrafficModel;
import com.google.maps.model.TravelMode;
import com.vividsolutions.jts.geom.Point;
import mapfood.exceptions.ClienteNaoEncontradoException;
import mapfood.exceptions.EstabelecimentoNaoEncontradoException;
import mapfood.factory.PointFactory;
import mapfood.model.dto.SolicitacaoEntrega;
import mapfood.model.mongodb.Estabelecimento;
import mapfood.spatial.CoordinateComparator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RotasServiceByGoogleDirectionsApi implements RotasService {

    private static final double EARTH_RADIUS = 6378137;
    private final GeoApiContext apiContext;

    private final EstabelecimentoService estabelecimentoService;
    private final ClienteService clienteService;
    private final MotoboyService motoboyService;


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
    public DirectionsResult getMelhorRotaPara(SolicitacaoEntrega solicitacaoEntrega) {

        LatLng origin = getLatLngEstabelecimento(solicitacaoEntrega);
        List<LatLng> waypoints = getLatLngClientes(solicitacaoEntrega, origin);

        // Criar retorno da api mapfood com dados do DirectionsResult.

        motoboyService.buscaPorAproximacao(origin.lat, origin.lng, limiteKmEntrega);

        return this.solicitaRotaAoServico(origin, waypoints);
    }

    private LatLng getLatLngEstabelecimento(SolicitacaoEntrega solicitacaoEntrega) {
        Estabelecimento estabelecimento = estabelecimentoService.findById(solicitacaoEntrega.getIdEstabelecimento())
                .orElseThrow(() -> new EstabelecimentoNaoEncontradoException(solicitacaoEntrega.getIdEstabelecimento()));

        return new LatLng(estabelecimento.getLatitude(), estabelecimento.getLongitude());
    }

    private List<LatLng> getLatLngClientes(SolicitacaoEntrega solicitacaoEntrega, LatLng origin) {
        List<LatLng> latLngs = solicitacaoEntrega.getPedidos()
                .stream()
                .map(pedido ->
                        clienteService.buscaPorId(
                                pedido.getIdCliente())
                                .orElseThrow(() -> new ClienteNaoEncontradoException(pedido.getIdCliente())))
                .map(dto -> new LatLng(dto.getLatitude(), dto.getLongitude()))
                .sorted(new CoordinateComparator(origin).getLatLngComparator())
                .collect(Collectors.toList());

        return latLngs;
    }

    private List<LatLng> getPointsOutsideTheArea(List<LatLng> latLngs, LatLng origin){
        PointFactory pointFactory = new PointFactory();
        Point originPoint = pointFactory.fromLatLong(origin.lat, origin.lng);

       return latLngs
                .stream()
                .map(pointFactory::fromLatLng)
                .filter(destPoint -> toMeters(originPoint.distance(destPoint)) > (limiteKmEntrega * 1000))
                .map(point -> new LatLng(point.getY(), point.getX()))
                .collect(Collectors.toList());
    }

    private Double toMeters(double degrees) {
        System.out.println("degrees = " + degrees);
        Double meters = degrees * (Math.PI / 180) * EARTH_RADIUS;
        System.out.println("meters = " + meters);
        return meters;
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
            System.out.println("Erro ao solicitar rota!");
            return null;
        }
    }

    private Instant calcularHoraSaida(int size) {
        return Instant.now().plus(Duration.ofMinutes(size * tempoPreparo));
    }

}
