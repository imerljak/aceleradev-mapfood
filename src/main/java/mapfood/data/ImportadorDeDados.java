package mapfood.data;

public interface ImportadorDeDados {

    /**
     * Efetua a importação dos dados para a base.
     */
    void importar();

    /**
     * Chama o próximo passo a ser efetuado na importação.
     * Não faz nada caso não precise.
     */
    void proximoPasso();

}
