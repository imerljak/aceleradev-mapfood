package mapfood.factory;

import com.vividsolutions.jts.geom.Coordinate;
import org.springframework.stereotype.Component;

@Component
public class CoordinateFactory {

    public Coordinate getInstance(Double latitude, Double longitude) {
        return new Coordinate(longitude, latitude);
    }

}
