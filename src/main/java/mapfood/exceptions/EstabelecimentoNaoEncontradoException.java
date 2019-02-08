package mapfood.exceptions;

public class EstabelecimentoNaoEncontradoException extends RuntimeException {

    public EstabelecimentoNaoEncontradoException() {
    }

    public EstabelecimentoNaoEncontradoException(String s) {
        super(s);
    }
}
