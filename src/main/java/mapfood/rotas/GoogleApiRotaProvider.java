package mapfood.rotas;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TrafficModel;
import com.google.maps.model.TravelMode;
import mapfood.dto.Rota;
import mapfood.factory.RotaMotoboyFactory;
import mapfood.model.jpa.Posicao;
import mapfood.utils.PosicaoComparator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

@Component
public class GoogleApiRotaProvider implements RotaProvider {

    private final GeoApiContext apiContext;
    @Value("${mapfood.valores.tempo-preparo}")
    public Long tempoPreparo;

    public GoogleApiRotaProvider(GeoApiContext apiContext) {
        this.apiContext = apiContext;
    }

    @Override
    public CompletableFuture<Rota> getRota(Posicao motoboy, Posicao estabelecimento, List<Posicao> clientes) {


        final CompletableFuture<Rota> future = new CompletableFuture<>();


        Instant horaSaida = calcularHoraSaida(clientes.size());
        clientes.sort(new PosicaoComparator(estabelecimento).getPosicaoComparator());
        LatLng ultimoCliente = clientes.remove(clientes.size() - 1).asLatLng();

        final LatLng[] paradasLatLng = clientes
                .parallelStream()
                .map(Posicao::asLatLng)
                .toArray(LatLng[]::new);

        DirectionsApi.newRequest(apiContext)
                .origin(motoboy.asLatLng())
                .destination(estabelecimento.asLatLng())
                .departureTime(Instant.now())
                .mode(TravelMode.DRIVING)
                .trafficModel(TrafficModel.BEST_GUESS)
                .setCallback(callback(motoboyResult -> {

                    DirectionsApi.newRequest(apiContext)
                            .origin(estabelecimento.asLatLng())
                            .waypoints(paradasLatLng)
                            .destination(ultimoCliente)
                            .optimizeWaypoints(true)
                            .departureTime(horaSaida)
                            .mode(TravelMode.DRIVING)
                            .trafficModel(TrafficModel.BEST_GUESS)
                            .setCallback(callback(entregasResult -> {
                                final Rota rotaMotoboy = RotaMotoboyFactory.getInstance(motoboyResult);
                                final Rota rotaEntrega = RotaMotoboyFactory.getInstance(entregasResult);

                                future.complete(rotaMotoboy.merge(rotaEntrega));
                            }));
                }));

        return future;

    }

    private PendingResult.Callback<DirectionsResult> callback(Consumer<DirectionsResult> consumer) {
        return new PendingResult.Callback<DirectionsResult>() {
            @Override
            public void onResult(DirectionsResult directionsResult) {
                consumer.accept(directionsResult);
            }

            @Override
            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();
            }
        };
    }

    private Instant calcularHoraSaida(int size) {
        return Instant.now().plus(Duration.ofMinutes(size * tempoPreparo));
    }

}
