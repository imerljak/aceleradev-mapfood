package mapfood.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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
    private final List<EntregaDTO> entregas;

    public RelatorioEntrega(String idEstabelecimento, List<EntregaDTO> entregas) {
        this.idEstabelecimento = idEstabelecimento;
        this.entregas = entregas;
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
}
