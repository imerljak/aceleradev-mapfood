package mapfood.model.dto;

import java.util.Date;

public class DadosEntregaDTO {

    private String idEstabelecimento;
    private Date dataSolicitacao;
    private double distanciaEntregaEmKm;
    private double tempoEstimadoDeEntregaMinutos;

    public String getIdEstabelecimento() {
        return idEstabelecimento;
    }

    public void setIdEstabelecimento(String idEstabelecimento) {
        this.idEstabelecimento = idEstabelecimento;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public double getDistanciaEntregaEmKm() {
        return distanciaEntregaEmKm;
    }

    public void setDistanciaEntregaEmKm(double distanciaEntregaEmKm) {
        this.distanciaEntregaEmKm = distanciaEntregaEmKm;
    }

    public double getTempoEstimadoDeEntregaMinutos() {
        return tempoEstimadoDeEntregaMinutos;
    }

    public void setTempoEstimadoDeEntregaMinutos(double tempoEstimadoDeEntregaMinutos) {
        this.tempoEstimadoDeEntregaMinutos = tempoEstimadoDeEntregaMinutos;
    }
}
