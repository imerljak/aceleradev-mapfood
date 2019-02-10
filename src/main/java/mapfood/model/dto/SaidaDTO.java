package mapfood.model.dto;

public class SaidaDTO {

    private Long idMotoboy;
    private double consumoCombustivel;
    private double distanciaEmMetros;
    private double duracaoEmSegundos;
    private Long[] idClientes;
    private Object rota; // qual objeto??

    public SaidaDTO() {
    }

    public Long getIdMotoboy() {
        return idMotoboy;
    }

    public void setIdMotoboy(Long idMotoboy) {
        this.idMotoboy = idMotoboy;
    }

    public double getDuracaoEmSegundos() {
        return duracaoEmSegundos;
    }

    public void setDuracaoEmSegundos(double duracaoEmSegundos) {
        this.duracaoEmSegundos = duracaoEmSegundos;
    }

    public double getConsumoCombustivel() {
        return consumoCombustivel;
    }

    public void setConsumoCombustivel(double consumoCombustivel) {
        this.consumoCombustivel = consumoCombustivel;
    }

    public double getDistanciaEmMetros() {
        return distanciaEmMetros;
    }

    public void setDistanciaEmMetros(double distanciaEmMetros) {
        this.distanciaEmMetros = distanciaEmMetros;
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
