package mapfood.model.dto;

/**
 * Stub class (trocar ou implementar p/ interface de Rotas
 */
public class RotaEntrega {

    private String idMotoboy;
    private Double distanciaEmKms;
    private Double tempoEstimado;
    private Double consumoCombustivelEstimado;

    private Object rota;

    protected RotaEntrega() {
    }

    public RotaEntrega(String idMotoboy) {
        this.idMotoboy = idMotoboy;
    }

    public String getIdMotoboy() {
        return idMotoboy;
    }

    public void setIdMotoboy(String idMotoboy) {
        this.idMotoboy = idMotoboy;
    }

    public Double getDistanciaEmKms() {
        return distanciaEmKms;
    }

    public void setDistanciaEmKms(Double distanciaEmKms) {
        this.distanciaEmKms = distanciaEmKms;
    }

    public Double getTempoEstimado() {
        return tempoEstimado;
    }

    public void setTempoEstimado(Double tempoEstimado) {
        this.tempoEstimado = tempoEstimado;
    }

    public Double getConsumoCombustivelEstimado() {
        return consumoCombustivelEstimado;
    }

    public void setConsumoCombustivelEstimado(Double consumoCombustivelEstimado) {
        this.consumoCombustivelEstimado = consumoCombustivelEstimado;
    }

    public Object getRota() {
        return rota;
    }

    public void setRota(Object rota) {
        this.rota = rota;
    }

}
