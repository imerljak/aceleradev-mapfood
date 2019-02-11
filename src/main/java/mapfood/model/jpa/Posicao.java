package mapfood.model.jpa;

import com.google.maps.model.LatLng;
import mapfood.utils.GeoUtils;

import java.util.Objects;

public class Posicao {

    private final Double latitude;
    private final Double longitude;

    public Posicao(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static Posicao of(Double latitude, Double longitude) {
        return new Posicao(latitude, longitude);
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double distancia(Posicao posicao) {
        return GeoUtils.haversineDistance(this, posicao);
    }

    public LatLng asLatLng() {
        return new LatLng(latitude, longitude);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Posicao posicao = (Posicao) o;
        return latitude.equals(posicao.latitude) &&
                longitude.equals(posicao.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }

    @Override
    public String toString() {
        return "Posicao{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
