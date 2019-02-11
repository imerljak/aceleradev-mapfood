package mapfood.exceptions;

import org.springframework.http.HttpStatus;

public class EstabelecimentoNaoEncontradoException extends BusinessException {

    private static final long serialVersionUID = 3309443217687719078L;

    private String idEstabelecimento;

    public EstabelecimentoNaoEncontradoException(String estabelecimento) {
        idEstabelecimento = estabelecimento;
    }

    @Override
    public String getMensagemErro() {
        return String.format("Estabelecimento não encontrado: %s", idEstabelecimento);
    }

    @Override
    protected HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
