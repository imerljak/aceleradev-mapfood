package mapfood.utils;

import mapfood.model.jpa.Posicao;
import org.junit.Assert;
import org.junit.Test;

public class GeoUtilsTest {

    @Test
    public void test1() {
        final Posicao a = Posicao.of(-30.028724, -51.199312);
        final Posicao b = Posicao.of(-30.049455, -51.227173);

        final double distancia = GeoUtils.haversineDistance(a, b);

        Assert.assertEquals(3.51, distancia, .3);
    }

    @Test
    public void test2() {
        final Posicao a = Posicao.of(-30.180729, -50.217865);
        final Posicao b = Posicao.of(-30.028646, -51.199263);

        final double distancia = GeoUtils.haversineDistance(a, b);

        Assert.assertEquals(95.9, distancia, .3);
    }

}