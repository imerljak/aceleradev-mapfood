package mapfood.factory;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.util.GeometricShapeFactory;
import org.springframework.stereotype.Component;

@Component
public final class GeometryFactory {

    private static final GeometricShapeFactory factory = new GeometricShapeFactory();

    public Geometry createCircle(Coordinate center, Double radius) {

        factory.setNumPoints(100);
        factory.setCentre(center);
        factory.setSize(radius * 2);

        return factory.createCircle().getBoundary();
    }

}
