package mapfood.spatial;

import com.vividsolutions.jts.geom.Coordinate;
import mapfood.model.jpa.Motoboy;

import java.util.Comparator;

public class CoordinateComparator {

    private final Coordinate origin;

    public CoordinateComparator(Coordinate origin) {
        this.origin = origin;
    }

    public Comparator<Motoboy> getInstance() {
        return Comparator.comparingDouble(current -> origin.distance(current.getPosicao().getCoordinate()));
    }

}
