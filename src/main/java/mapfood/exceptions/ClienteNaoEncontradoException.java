package mapfood.exceptions;

import org.springframework.http.HttpStatus;

public class ClienteNaoEncontradoException extends BusinessException {

    private static final long serialVersionUID = 5031928800387031290L;

    private final Long idCliente;

    public ClienteNaoEncontradoException(Long cliente) {
        idCliente = cliente;
    }

    @Override
    public String getMensagemErro() {
        return String.format("Cliente não encontrado: %d", idCliente);
    }

    @Override
    protected HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
