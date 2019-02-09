package mapfood.exceptions;

public class ClienteNaoEncontradoException extends BusinessException {

    private final Long idCliente;

    public ClienteNaoEncontradoException(Long cliente) {
        idCliente = cliente;
    }

    @Override
    public String getMensagemErro() {
        return String.format("Cliente não encontrado: %d", idCliente);
    }

}
