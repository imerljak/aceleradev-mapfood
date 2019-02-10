package mapfood.model.mongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document("dados_entregas")
public class DadosEntrega {

    @Id
    private String id;
    private String idEstabelecimento;
    private LocalDate dataSolicitacao;
    private Double distanciaEmMetros;
    private Long duracaoEmSegundos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdEstabelecimento() {
        return idEstabelecimento;
    }

    public void setIdEstabelecimento(String idEstabelecimento) {
        this.idEstabelecimento = idEstabelecimento;
    }

    public LocalDate getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDate dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public Double getDistanciaEmMetros() {
        return distanciaEmMetros;
    }

    public void setDistanciaEmMetros(Double distanciaEmMetros) {
        this.distanciaEmMetros = distanciaEmMetros;
    }

    public Long getDuracaoEmSegundos() {
        return duracaoEmSegundos;
    }

    public void setDuracaoEmSegundos(Long duracaoEmSegundos) {
        this.duracaoEmSegundos = duracaoEmSegundos;
    }
}
