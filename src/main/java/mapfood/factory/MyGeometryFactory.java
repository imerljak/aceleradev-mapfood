package mapfood.factory;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.util.GeometricShapeFactory;
import mapfood.spatial.GeoUtils;
import org.springframework.stereotype.Component;

@Component
public final class MyGeometryFactory {

    private static final GeometricShapeFactory shapeFactory = new GeometricShapeFactory();

    public Geometry createCircle(Coordinate center, Double radius) {

        shapeFactory.setNumPoints(100);
        shapeFactory.setCentre(center);
        shapeFactory.setSize(GeoUtils.metersToDegrees(radius * 2));

        return shapeFactory.createCircle().getBoundary();
    }

}
