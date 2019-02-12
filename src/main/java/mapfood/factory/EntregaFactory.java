package mapfood.factory;

import mapfood.dto.EntregaDTO;
import mapfood.model.mongodb.Entrega;

public class EntregaFactory {

    public static EntregaDTO getInstance(Entrega entrega) {
        final EntregaDTO dto = new EntregaDTO();
        dto.setDataSolicitacao(entrega.getDataSolicitacao());
        dto.setDistanciaEmMetros(entrega.getDistanciaEmMetros());
        dto.setDuracaoEmSegundos(entrega.getDuracaoEmSegundos());
        dto.setIdEstabelecimento(entrega.getIdEstabelecimento());

        return dto;
    }

    public static Entrega getInstance(EntregaDTO dto) {
        final Entrega entrega = new Entrega();
        entrega.setDataSolicitacao(dto.getDataSolicitacao());
        entrega.setDistanciaEmMetros(dto.getDistanciaEmMetros());
        entrega.setDuracaoEmSegundos(dto.getDuracaoEmSegundos());
        entrega.setIdEstabelecimento(dto.getIdEstabelecimento());

        return entrega;
    }

}
