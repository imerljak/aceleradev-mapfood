package mapfood.factory;

import mapfood.model.dto.EstabelecimentoDTO;
import mapfood.model.mongodb.Estabelecimento;

public final class EstabelecimentoFactory {

    public static Estabelecimento getInstance(EstabelecimentoDTO estabelecimentoDTO) {
        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setId(estabelecimentoDTO.getId());
        estabelecimento.setNome(estabelecimentoDTO.getNome());
        estabelecimento.setCidade(estabelecimentoDTO.getCidade());
        estabelecimento.setLatitude(estabelecimentoDTO.getLatitude());
        estabelecimento.setLongitude(estabelecimentoDTO.getLongitude());
        estabelecimento.setTipoDeComida(estabelecimentoDTO.getTipoDeComida());

        return estabelecimento;
    }

    public static EstabelecimentoDTO getInstance(Estabelecimento estabelecimento) {
        EstabelecimentoDTO estabelecimentoDTO = new EstabelecimentoDTO();
        estabelecimentoDTO.setId(estabelecimento.getId());
        estabelecimentoDTO.setNome(estabelecimento.getNome());
        estabelecimentoDTO.setCidade(estabelecimento.getCidade());
        estabelecimentoDTO.setLatitude(estabelecimento.getLatitude());
        estabelecimentoDTO.setLongitude(estabelecimento.getLongitude());
        estabelecimentoDTO.setTipoDeComida(estabelecimento.getTipoDeComida());

        return estabelecimentoDTO;
    }

}
