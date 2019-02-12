package mapfood.factory;

import mapfood.dto.EntregaDTO;
import mapfood.model.mongodb.Entrega;
import org.junit.Test;

import java.time.Instant;

import static org.junit.Assert.assertEquals;

public class EntregaFactoryTest {

    @Test
    public void deveRetornarDTOQuandoReceberModel() {
        final Entrega model = new Entrega();
        model.setId("ID_TEST");
        model.setIdEstabelecimento("ID_ESTABELECIMENTO_TEST");
        model.setDuracaoEmSegundos(200L);
        model.setDistanciaEmMetros(1000D);
        model.setDataSolicitacao(Instant.now());

        final EntregaDTO dto = EntregaFactory.getInstance(model);

        assertEquals(dto.getIdEstabelecimento(), model.getIdEstabelecimento());
        assertEquals(dto.getDuracaoEmSegundos(), model.getDuracaoEmSegundos());
        assertEquals(dto.getDistanciaEmMetros(), model.getDistanciaEmMetros(), 0.0);
        assertEquals(dto.getDataSolicitacao(), model.getDataSolicitacao());
    }

    @Test
    public void deveRetornarModelQuandoReceberDTO() {
        final EntregaDTO dto = new EntregaDTO();
        dto.setIdEstabelecimento("ID_ESTABELECIMENTO_TEST");
        dto.setDuracaoEmSegundos(200L);
        dto.setDistanciaEmMetros(1000D);
        dto.setDataSolicitacao(Instant.now());

        final Entrega model = EntregaFactory.getInstance(dto);

        assertEquals(model.getIdEstabelecimento(), dto.getIdEstabelecimento());
        assertEquals(model.getDuracaoEmSegundos(), dto.getDuracaoEmSegundos());
        assertEquals(model.getDistanciaEmMetros(), dto.getDistanciaEmMetros(), 0.0);
        assertEquals(model.getDataSolicitacao(), dto.getDataSolicitacao());
    }

}