package mapfood.factory;

import mapfood.dto.EstabelecimentoDTO;
import mapfood.model.mongodb.Estabelecimento;

public final class EstabelecimentoFactory {

    public static Estabelecimento getInstance(EstabelecimentoDTO estabelecimentoDTO) {
        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setNome(estabelecimentoDTO.getNome());
        estabelecimento.setCidade(estabelecimentoDTO.getCidade());
        estabelecimento.setPosicao(estabelecimentoDTO.getPosicao());
        estabelecimento.setTipoDeComida(estabelecimentoDTO.getTipoDeComida());

        return estabelecimento;
    }

    public static EstabelecimentoDTO getInstance(Estabelecimento estabelecimento) {
        EstabelecimentoDTO estabelecimentoDTO = new EstabelecimentoDTO();
        estabelecimentoDTO.setNome(estabelecimento.getNome());
        estabelecimentoDTO.setCidade(estabelecimento.getCidade());
        estabelecimentoDTO.setPosicao(estabelecimento.getPosicao());
        estabelecimentoDTO.setTipoDeComida(estabelecimento.getTipoDeComida());

        return estabelecimentoDTO;
    }

}
