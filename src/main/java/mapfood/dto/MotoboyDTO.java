package mapfood.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import mapfood.model.jpa.Posicao;

import java.io.Serializable;

@JsonPropertyOrder({
        "id",
        "latitude",
        "longitude",
})
public class MotoboyDTO implements Serializable {

    private static final long serialVersionUID = -7937847395119199318L;

    @JsonProperty
    private Long id;

    @JsonProperty
    private Double latitude;

    @JsonProperty
    private Double longitude;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    public Posicao getPosicao() {
        return Posicao.of(latitude, longitude);
    }

    public void setPosicao(Posicao posicao) {
        this.latitude = posicao.getLatitude();
        this.longitude = posicao.getLongitude();
    }
}
