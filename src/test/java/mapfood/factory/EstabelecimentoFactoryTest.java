package mapfood.factory;

import mapfood.model.dto.EstabelecimentoDTO;
import mapfood.model.jpa.Posicao;
import mapfood.model.mongodb.Estabelecimento;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EstabelecimentoFactoryTest {

    @Test
    public void deveRetornarEntityQuantoPassarDTO() {
        EstabelecimentoDTO dto = new EstabelecimentoDTO();
        dto.setNome("NOME_TEST");
        dto.setCidade("CIDADE_TEST");
        dto.setPosicao(Posicao.of(-1D, 1D));
        dto.setTipoDeComida("TIPO_COMIDA_TEST");

        Estabelecimento entity = EstabelecimentoFactory.getInstance(dto);

        assertEquals(dto.getNome(), entity.getNome());
        assertEquals(dto.getCidade(), entity.getCidade());
        assertEquals(dto.getPosicao(), entity.getPosicao());
        assertEquals(dto.getTipoDeComida(), entity.getTipoDeComida());
    }

    @Test
    public void deveRetornarDTOQuantoPassarEntity() {
        Estabelecimento entity = new Estabelecimento();
        entity.setId("ID_TEST");
        entity.setNome("NOME_TEST");
        entity.setCidade("CIDADE_TEST");
        entity.setPosicao(Posicao.of(-1D, 1D));
        entity.setTipoDeComida("TIPO_COMIDA_TEST");

        EstabelecimentoDTO dto = EstabelecimentoFactory.getInstance(entity);

        assertEquals(entity.getNome(), dto.getNome());
        assertEquals(entity.getCidade(), dto.getCidade());
        assertEquals(entity.getPosicao(), dto.getPosicao());
        assertEquals(entity.getTipoDeComida(), dto.getTipoDeComida());
    }

}