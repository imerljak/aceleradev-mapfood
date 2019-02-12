package mapfood.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import mapfood.model.jpa.Posicao;

import java.io.Serializable;
import java.util.Objects;

@JsonPropertyOrder({
        "latitude",
        "longitude"
})
public class ClienteDTO implements Serializable {

    private static final long serialVersionUID = 5206501363808649896L;

    @JsonProperty
    private Double latitude;

    @JsonProperty
    private Double longitude;

    @JsonIgnore
    public Posicao getPosicao() {
        return Posicao.of(latitude, longitude);
    }

    public void setPosicao(Posicao posicao) {
        this.latitude = posicao.getLatitude();
        this.longitude = posicao.getLongitude();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteDTO that = (ClienteDTO) o;
        return latitude.equals(that.latitude) &&
                longitude.equals(that.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }
}
