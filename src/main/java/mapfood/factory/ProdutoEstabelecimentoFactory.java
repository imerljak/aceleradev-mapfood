package mapfood.factory;

import mapfood.dto.ProdutoEstabelecimentoDTO;
import mapfood.model.mongodb.ProdutoEstabelecimento;

public final class ProdutoEstabelecimentoFactory {

    public static ProdutoEstabelecimento getInstance(ProdutoEstabelecimentoDTO dto) {
        final ProdutoEstabelecimento entity = new ProdutoEstabelecimento();
        entity.setDescricao(dto.getDescricao());
        entity.setIdEstabelecimento(dto.getIdEstabelecimento());
        entity.setNomeEstabelecimento(dto.getNomeEstabelecimento());
        entity.setClassificacao(dto.getClassificacao());
        entity.setPrecoUnitario(dto.getPrecoUnitario());
        entity.setCidade(dto.getCidade());

        return entity;
    }

    public static ProdutoEstabelecimentoDTO getInstance(ProdutoEstabelecimento entity) {
        final ProdutoEstabelecimentoDTO dto = new ProdutoEstabelecimentoDTO();
        dto.setDescricao(entity.getDescricao());
        dto.setIdEstabelecimento(entity.getIdEstabelecimento());
        dto.setNomeEstabelecimento(entity.getNomeEstabelecimento());
        dto.setClassificacao(entity.getClassificacao());
        dto.setPrecoUnitario(entity.getPrecoUnitario());
        dto.setCidade(entity.getCidade());

        return dto;
    }

}
