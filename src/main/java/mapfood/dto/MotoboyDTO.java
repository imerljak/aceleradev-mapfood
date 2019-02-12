package mapfood.dto;

import mapfood.model.jpa.Posicao;

import java.io.Serializable;

public class MotoboyDTO implements Serializable {

    private static final long serialVersionUID = -7937847395119199318L;

    private Long id;

    private Double latitude;

    private Double longitude;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Posicao getPosicao() {
        return Posicao.of(latitude, longitude);
    }

    public void setPosicao(Posicao posicao) {
        this.latitude = posicao.getLatitude();
        this.longitude = posicao.getLongitude();
    }
}
