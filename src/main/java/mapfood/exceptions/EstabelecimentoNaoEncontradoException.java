package mapfood.exceptions;

public class EstabelecimentoNaoEncontradoException extends BusinessException {
    private String idEstabelecimento;

    public EstabelecimentoNaoEncontradoException(String estabelecimento) {
        idEstabelecimento = estabelecimento;
    }

    @Override
    public String getMensagemErro() {
        return String.format("Estabelecimento não encontrado: %d", idEstabelecimento);
    }
}
