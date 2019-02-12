package mapfood.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "idMotoboy",
        "idEstabelecimento",
        "consumoCombustivel",
        "distanciaEmMetros",
        "duracaoEmSegundos",
        "rota",
})
public class ResultadoRota {

    private Long idMotoboy;
    private String idEstabelecimento;
    private double consumoCombustivel;
    private Rota rota;

    public Long getIdMotoboy() {
        return idMotoboy;
    }

    public void setIdMotoboy(Long idMotoboy) {
        this.idMotoboy = idMotoboy;
    }

    public String getIdEstabelecimento() {
        return idEstabelecimento;
    }

    public void setIdEstabelecimento(String idEstabelecimento) {
        this.idEstabelecimento = idEstabelecimento;
    }

    public double getConsumoCombustivel() {
        return consumoCombustivel;
    }

    public void setConsumoCombustivel(double consumoCombustivel) {
        this.consumoCombustivel = consumoCombustivel;
    }

    public Rota getRota() {
        return rota;
    }

    public void setRota(Rota rota) {
        this.rota = rota;
    }

    public double getDistanciaEmMetros() {
        if (rota != null) {
            return rota.getDistanciaTotal();
        }

        return 0D;
    }

    public Long getDuracaoEmSegundos() {
        if (rota != null) {
            return rota.getDurataoTotalEmSegundos();
        }

        return 0L;
    }
}
