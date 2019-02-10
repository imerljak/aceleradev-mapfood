package mapfood.factory;

import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.LatLng;
import mapfood.model.dto.Rota;
import mapfood.model.dto.Rota.Trecho;
import mapfood.model.jpa.Posicao;

public class RotaMotoboyFactory {

    public static Rota getInstance(DirectionsResult directionsResult) {
        final Rota rota = new Rota();
        for (DirectionsRoute route : directionsResult.routes) {
            for (DirectionsLeg leg : route.legs) {
                final Trecho trecho = new Trecho();
                trecho.setDistanciaEmMetros(leg.distance.inMeters);
                trecho.setDuracaoEmSegundos(leg.duration.inSeconds);

                final LatLng startLocation = leg.startLocation;
                trecho.setInicio(Posicao.of(startLocation.lat, startLocation.lng));

                final LatLng endLocation = leg.endLocation;
                trecho.setFim(Posicao.of(endLocation.lat, endLocation.lng));

                rota.adicionarTrecho(trecho);
            }
        }

        return rota;
    }

}
