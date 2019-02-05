package mapfood.factory;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

final class PointFactory {

    private static final GeometryFactory FACTORY = new GeometryFactory();

    Point fromLatLong(Double latitude, Double longitude) {
        return FACTORY.createPoint(new Coordinate(longitude, latitude));
    }
}
