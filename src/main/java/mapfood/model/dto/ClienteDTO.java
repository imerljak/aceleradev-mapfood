package mapfood.model.dto;

import mapfood.model.jpa.Posicao;

public class ClienteDTO {

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
