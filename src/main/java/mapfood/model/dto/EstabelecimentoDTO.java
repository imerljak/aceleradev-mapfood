package mapfood.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import mapfood.model.jpa.Posicao;

import java.io.Serializable;

public class EstabelecimentoDTO implements Serializable {

    private static final long serialVersionUID = 4532958488385764452L;

    private String nome;
    private String cidade;

    @JsonProperty
    private Double longitude;
    @JsonProperty
    private Double latitude;

    private String tipoDeComida;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTipoDeComida() {
        return tipoDeComida;
    }

    public void setTipoDeComida(String tipoDeComida) {
        this.tipoDeComida = tipoDeComida;
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
