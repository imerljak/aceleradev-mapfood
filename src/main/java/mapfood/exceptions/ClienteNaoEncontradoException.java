package mapfood.exceptions;

public class ClienteNaoEncontradoException extends RuntimeException {
    public ClienteNaoEncontradoException() {
    }

    public ClienteNaoEncontradoException(String s) {
        super(s);
    }
}
