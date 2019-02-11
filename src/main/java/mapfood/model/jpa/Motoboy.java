package mapfood.model.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Table(name = "motoboys", indexes = {
        @Index(name = "idx_latLng", columnList = "latitude, longitude")
})
@Entity
public class Motoboy implements Serializable {

    private static final long serialVersionUID = 7643857411211476277L;

    @JsonIgnore
    @Id
    @Column(columnDefinition = "SERIAL")
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Motoboy motoboy = (Motoboy) o;
        return id.equals(motoboy.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
