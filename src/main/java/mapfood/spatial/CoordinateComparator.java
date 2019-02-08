package mapfood.spatial;

import com.google.maps.model.LatLng;
import com.vividsolutions.jts.geom.Coordinate;
import mapfood.factory.CoordinateFactory;
import mapfood.model.jpa.Motoboy;

import java.util.Comparator;

public class CoordinateComparator {

    private final Coordinate origin;

    public CoordinateComparator(Coordinate origin) {
        this.origin = origin;
    }

    public CoordinateComparator(LatLng latLng) {
        origin = new CoordinateFactory().getInstance(latLng.lat, latLng.lng);
    }

    public Comparator<Motoboy> getMotoboyComparator() {
        return Comparator.comparingDouble(current -> origin.distance(current.getPosicao().getCoordinate()));
    }

    public Comparator<LatLng> getLatLngComparator() {
        return Comparator.comparingDouble(current -> origin.distance(new Coordinate(current.lng, current.lat)));
    }

}
