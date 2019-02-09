package mapfood.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SaidaDTO {

    @NotNull
    @NotEmpty
    private Long idMotoboy;
    private double tempoTotal;
    private double consumoTotal;
    private double distanciaTotal;
    private Long[] idClientes;
    private Object rota; // qual objeto??

    public Long getIdMotoboy() {
        return idMotoboy;
    }

    public void setIdMotoboy(Long idMotoboy) {
        this.idMotoboy = idMotoboy;
    }

    public double getTempoTotal() {
        return tempoTotal;
    }

    public void setTempoTotal(double tempoTotal) {
        this.tempoTotal = tempoTotal;
    }

    public double getConsumoTotal() {
        return consumoTotal;
    }

    public void setConsumoTotal(double consumoTotal) {
        this.consumoTotal = consumoTotal;
    }

    public double getDistanciaTotal() {
        return distanciaTotal;
    }

    public void setDistanciaTotal(double distanciaTotal) {
        this.distanciaTotal = distanciaTotal;
    }

    public Long[] getIdClientes() {
        return idClientes;
    }

    public void setIdClientes(Long[] idClientes) {
        this.idClientes = idClientes;
    }

    public Object getRota() {
        return rota;
    }

    public void setRota(Object rota) {
        this.rota = rota;
    }
}
