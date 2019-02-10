package mapfood.spatial;

public class GeoUtils {

    private static final double EARTH_RADIUS = 6378137;

    public static Double metersToDegrees(Double meters) {
        return (meters / (Math.PI / 180) * EARTH_RADIUS);
    }

    public static Double degreesToMeters(Double degree) {
        return (degree * (Math.PI / 180) * EARTH_RADIUS);
    }

}
