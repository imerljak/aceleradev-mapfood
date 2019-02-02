package mapfood.factory;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import org.junit.Assert;
import org.junit.Test;

public class PointFactoryTest {

    @Test
    public void deveConstruirPointComMesmosValores() {
        GeometryFactory factory = new GeometryFactory();
        Point point = factory.createPoint(new Coordinate(-200, -300));

        Point myPoint = new PointFactory().fromLatLong(-300D, -200D);

        Assert.assertEquals(point, myPoint);
    }

}