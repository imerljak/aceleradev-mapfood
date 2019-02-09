package mapfood.exceptions;

import mapfood.model.dto.EstabelecimentoDTO;
import mapfood.model.mongodb.Estabelecimento;

public class EstabelecimentoNaoEncontradoException extends BusinessException {
    private String idEstabelecimento;

    public EstabelecimentoNaoEncontradoException(Estabelecimento estabelecimento) {
        idEstabelecimento = estabelecimento.getId();
    }

    public EstabelecimentoNaoEncontradoException(EstabelecimentoDTO estabelecimentoDTO) {
        idEstabelecimento = estabelecimentoDTO.getId();
    }

    @Override
    public String getMensagemErro() {
        return String.format("Estabelecimento não encontrado: %d", idEstabelecimento);
    }
}
