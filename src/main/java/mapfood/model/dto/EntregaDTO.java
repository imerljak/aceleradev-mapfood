package mapfood.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.Instant;

public class EntregaDTO {

    @JsonIgnore
    private String idEstabelecimento;
    private Instant dataSolicitacao;
    private Double distanciaEmMetros;
    private Long duracaoEmSegundos;

    public String getIdEstabelecimento() {
        return idEstabelecimento;
    }

    public void setIdEstabelecimento(String idEstabelecimento) {
        this.idEstabelecimento = idEstabelecimento;
    }

    public Instant getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Instant dataSolicitacao) {
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
