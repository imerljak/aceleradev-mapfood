package mapfood.exceptions;

public class ClienteNaoEncontradoException extends BusinessException {
    public ClienteNaoEncontradoException() {
    }

    public ClienteNaoEncontradoException(String s) {
        super(s);
    }
}
