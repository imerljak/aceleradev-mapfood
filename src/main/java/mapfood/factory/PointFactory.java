package mapfood.factory;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

final class PointFactory {

    private static final GeometryFactory FACTORY = new GeometryFactory();
    private static final CoordinateFactory COORDINATE_FACTORY = new CoordinateFactory();

    Point fromLatLong(Double latitude, Double longitude) {
        Coordinate coordinate = COORDINATE_FACTORY.getInstance(latitude, longitude);
        return FACTORY.createPoint(coordinate);
    }
}
