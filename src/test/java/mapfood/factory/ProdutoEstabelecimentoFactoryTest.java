package mapfood.factory;

import mapfood.dto.ProdutoEstabelecimentoDTO;
import mapfood.model.mongodb.ProdutoEstabelecimento;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProdutoEstabelecimentoFactoryTest {

    @Test
    public void deveRetornarEntityQuantoPassarDTO() {
        final ProdutoEstabelecimentoDTO dto = new ProdutoEstabelecimentoDTO();
        dto.setDescricao("DESCRICAO_TEST");
        dto.setIdEstabelecimento("ID_ESTABELECIMENTO_TEST");
        dto.setNomeEstabelecimento("NOME_ESTABELECIMENTO_TEST");
        dto.setClassificacao("CLASSIFICACAO_TEST");
        dto.setPrecoUnitario(10.5);
        dto.setCidade("CIDADE_TEST");

        final ProdutoEstabelecimento entity = ProdutoEstabelecimentoFactory.getInstance(dto);

        assertEquals(dto.getDescricao(), entity.getDescricao());
        assertEquals(dto.getIdEstabelecimento(), entity.getIdEstabelecimento());
        assertEquals(dto.getNomeEstabelecimento(), entity.getNomeEstabelecimento());
        assertEquals(dto.getClassificacao(), entity.getClassificacao());
        assertEquals(dto.getPrecoUnitario(), entity.getPrecoUnitario(), 0);
        assertEquals(dto.getCidade(), entity.getCidade());
    }

    @Test
    public void deveRetornarDTOQuantoPassarEntity() {
        final ProdutoEstabelecimento entity = new ProdutoEstabelecimento();
        entity.setId("ID_TEST");
        entity.setDescricao("DESCRICAO_TEST");
        entity.setIdEstabelecimento("ID_ESTABELECIMENTO_TEST");
        entity.setNomeEstabelecimento("NOME_ESTABELECIMENTO_TEST");
        entity.setClassificacao("CLASSIFICACAO_TEST");
        entity.setPrecoUnitario(10.5);
        entity.setCidade("CIDADE_TEST");

        final ProdutoEstabelecimentoDTO dto = ProdutoEstabelecimentoFactory.getInstance(entity);

        assertEquals(entity.getDescricao(), dto.getDescricao());
        assertEquals(entity.getIdEstabelecimento(), dto.getIdEstabelecimento());
        assertEquals(entity.getNomeEstabelecimento(), dto.getNomeEstabelecimento());
        assertEquals(entity.getClassificacao(), dto.getClassificacao());
        assertEquals(entity.getPrecoUnitario(), dto.getPrecoUnitario(), 0);
        assertEquals(entity.getCidade(), dto.getCidade());
    }

}