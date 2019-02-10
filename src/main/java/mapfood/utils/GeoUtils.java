package mapfood.utils;

import mapfood.model.jpa.Posicao;

public class GeoUtils {

    private static final double EARTH_RADIUS = 6378.137;

    /**
     * Retorna distância aproximada (em kms) entre dois pontos aplicando a formula de Haversine.
     *
     * @param posicaoA ponto a
     * @param posicaoB ponto b
     * @return distancia aproximada em kilometros
     */
    public static double haversineDistance(Posicao posicaoA, Posicao posicaoB) {
        return haversineDistance(
                posicaoA.getLatitude(),
                posicaoA.getLongitude(),
                posicaoB.getLatitude(),
                posicaoB.getLongitude()
        );
    }

    /**
     * Aplica formula de haversine e retorna distancia aproximada entre os pontos.
     *
     * @param latA x1
     * @param lngA y1
     * @param latB x2
     * @param lngB y2
     * @return d
     */
    private static double haversineDistance(double latA, double lngA, double latB, double lngB) {
        double dLat = Math.toRadians((latB - latA));
        double dLng = Math.toRadians((lngB - lngA));

        latA = Math.toRadians(latA);
        latB = Math.toRadians(latB);

        double a = haversine(dLat) + Math.cos(latA) * Math.cos(latB) * haversine(dLng);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

    private static double haversine(double value) {
        return Math.pow(Math.sin(value / 2), 2);
    }

}
