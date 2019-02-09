package mapfood.exceptions;

public class RespostaErro {
    private final String mensagem;

    public RespostaErro(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String clienteMuitoDistante(){
        return "Cliente muito distante!";
    }

    public String clienteNaoEncontrado(){
        return "Cliente não encontrado!";
    }

    public String estabelecimentoNaoEncontrado(){
        return "Estabelecimento não encontrado!";
    }
}
