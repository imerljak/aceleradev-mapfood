package mapfood.factory;

import com.google.maps.model.LatLng;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.util.GeometricShapeFactory;
import org.springframework.stereotype.Component;

@Component
public final class MyGeometryFactory {

    private static final GeometricShapeFactory shapeFactory = new GeometricShapeFactory();
    private static final GeometryFactory geoFactory = new GeometryFactory();
    private static final PointFactory pointFactory = new PointFactory();

    public Geometry createCircle(Coordinate center, Double radius) {

        Point point = pointFactory.fromCoordinate(center);

        shapeFactory.setNumPoints(100);
        shapeFactory.setCentre(center);
        shapeFactory.setSize(radius * 2);

        return shapeFactory.createCircle().getBoundary();
    }

    public Geometry createCircle(LatLng latLng, Double radius) {
        Coordinate point = new CoordinateFactory().getInstance(latLng);
        return createCircle(point, radius);
    }

    public Geometry createPoint(LatLng latLng) {
        return geoFactory.createPoint(new CoordinateFactory().getInstance(latLng)).getBoundary();
    }

}
