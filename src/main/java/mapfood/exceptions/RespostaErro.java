package mapfood.exceptions;

public class RespostaErro {
    private final String mensagem;

    public RespostaErro(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

}
