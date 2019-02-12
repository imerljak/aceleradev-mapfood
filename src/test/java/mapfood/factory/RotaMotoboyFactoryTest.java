package mapfood.factory;

import com.google.maps.model.*;
import mapfood.dto.Rota;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RotaMotoboyFactoryTest {

    @Test
    public void deveRetornarRotaQuandoReceberDirectionResult() {
        final DirectionsResult result = buildDirections();

        final Rota instance = RotaMotoboyFactory.getInstance(result);

        assertNotNull(instance);
        assertEquals(instance.getDistanciaTotal(), 100);
        assertEquals(instance.getDurataoTotalEmSegundos(), 200);
    }

    private DirectionsResult buildDirections() {
        final DirectionsResult result = new DirectionsResult();
        result.routes = new DirectionsRoute[]{newRoute()};
        return result;
    }

    private DirectionsRoute newRoute() {
        final DirectionsRoute route = new DirectionsRoute();
        route.legs = new DirectionsLeg[]{newLeg()};

        return route;
    }

    private DirectionsLeg newLeg() {
        final DirectionsLeg leg = new DirectionsLeg();
        leg.duration = new Duration();
        leg.duration.inSeconds = 200;

        leg.distance = new Distance();
        leg.distance.inMeters = 100;

        leg.startLocation = new LatLng(-1, 1);
        leg.endLocation = new LatLng(-2, 2);

        return leg;
    }

}