package mapfood.factory;

import mapfood.model.dto.EstabelecimentoDTO;
import mapfood.model.mongodb.Estabelecimento;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EstabelecimentoFactoryTest {

    @Test
    public void deveRetornarEntityQuantoPassarDTO() {
        EstabelecimentoDTO dto = new EstabelecimentoDTO();
        dto.setId("ID_TEST");
        dto.setNome("NOME_TEST");
        dto.setCidade("CIDADE_TEST");
        dto.setLatitude(-1D);
        dto.setLongitude(1D);
        dto.setTipoDeComida("TIPO_COMIDA_TEST");

        Estabelecimento entity = EstabelecimentoFactory.getInstance(dto);

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getNome(), entity.getNome());
        assertEquals(dto.getCidade(), entity.getCidade());
        assertEquals(dto.getLatitude(), entity.getLatitude(), 0);
        assertEquals(dto.getLongitude(), entity.getLongitude(), 0);
        assertEquals(dto.getTipoDeComida(), entity.getTipoDeComida());
    }

    @Test
    public void deveRetornarDTOQuantoPassarEntity() {
        Estabelecimento entity = new Estabelecimento();
        entity.setId("ID_TEST");
        entity.setNome("NOME_TEST");
        entity.setCidade("CIDADE_TEST");
        entity.setLatitude(-1D);
        entity.setLongitude(1D);
        entity.setTipoDeComida("TIPO_COMIDA_TEST");

        EstabelecimentoDTO dto = EstabelecimentoFactory.getInstance(entity);

        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getNome(), dto.getNome());
        assertEquals(entity.getCidade(), dto.getCidade());
        assertEquals(entity.getLatitude(), dto.getLatitude(), 0);
        assertEquals(entity.getLongitude(), dto.getLongitude(), 0);
        assertEquals(entity.getTipoDeComida(), dto.getTipoDeComida());
    }

}