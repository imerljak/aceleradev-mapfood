package mapfood.exceptions;

import org.springframework.http.HttpStatus;

public class MotoboyNaoEncontradoException extends BusinessException {

    private static final long serialVersionUID = -8494350852369484604L;

    private final String mensagem;

    public MotoboyNaoEncontradoException(final String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String getMensagemErro() {
        return this.mensagem;
    }

    @Override
    protected HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
