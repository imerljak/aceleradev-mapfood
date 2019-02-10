package mapfood.model.dto;

public class ResultadoRota {

    private Long idMotoboy;
    private String idEstabelecimento;
    private double consumoCombustivel;
    private double distanciaEmMetros;
    private Long duracaoEmSegundos;
    private Long[] idClientes;
    private Object rota; // qual objeto??

    public ResultadoRota() {
    }

    public Long getIdMotoboy() {
        return idMotoboy;
    }

    public void setIdMotoboy(Long idMotoboy) {
        this.idMotoboy = idMotoboy;
    }

    public Long getDuracaoEmSegundos() {
        return duracaoEmSegundos;
    }

    public void setDuracaoEmSegundos(Long duracaoEmSegundos) {
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

    public String getIdEstabelecimento() {
        return idEstabelecimento;
    }

    public void setIdEstabelecimento(String idEstabelecimento) {
        this.idEstabelecimento = idEstabelecimento;
    }
}
