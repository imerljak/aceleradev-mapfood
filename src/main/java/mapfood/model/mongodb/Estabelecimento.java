package mapfood.model.mongodb;


import com.fasterxml.jackson.annotation.JsonProperty;
import mapfood.model.jpa.Posicao;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("estabelecimentos")
public class Estabelecimento {

    @Id
    private String id;

    private String nome;

    private String cidade;

    @JsonProperty
    private Double latitude;

    @JsonProperty
    private Double longitude;

    private String tipoDeComida;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Posicao getPosicao() {
        return Posicao.of(latitude, longitude);
    }

    public void setPosicao(Posicao posicao) {
        this.latitude = posicao.getLatitude();
        this.longitude = posicao.getLongitude();
    }

}
