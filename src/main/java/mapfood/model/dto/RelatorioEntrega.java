package mapfood.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({
        "idEstabelecimento",
        "distanciaTotalEmMetros",
        "duracaoTotalEmSegundos",
        "entregasNoPeriodo",
})
public class RelatorioEntrega {

    private final String idEstabelecimento;

    @JsonProperty("entregasNoPeriodo")
    private List<EntregaDTO> entregas = new ArrayList<>();

    public RelatorioEntrega(String idEstabelecimento) {
        this.idEstabelecimento = idEstabelecimento;
    }


    @JsonProperty("distanciaTotalEmMetros")
    public Double getDistanciaTotalEmMetros() {
        return entregas
                .parallelStream()
                .mapToDouble(EntregaDTO::getDistanciaEmMetros)
                .sum();
    }

    @JsonProperty("duracaoTotalEmSegundos")
    public Long getDuracaoTotalEmSegundos() {
        return entregas
                .parallelStream()
                .mapToLong(EntregaDTO::getDuracaoEmSegundos)
                .sum();
    }

    public String getIdEstabelecimento() {
        return idEstabelecimento;
    }

    public List<EntregaDTO> getEntregas() {
        return entregas;
    }

    public void setEntregas(List<EntregaDTO> entregas) {
        this.entregas = entregas;
    }
}
