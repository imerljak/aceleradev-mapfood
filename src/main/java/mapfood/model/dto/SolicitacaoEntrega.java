package mapfood.model.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class SolicitacaoEntrega {

    @NotEmpty(message = "O idEstabelecimento deve ser informado")
    private String idEstabelecimento;

    @Valid
    @NotEmpty
    @Size(max = 5, message = "Deve existir no máximo 5 pedidos por solicitação.") // ??
    private List<Pedido> pedidos = new ArrayList<>();

    protected SolicitacaoEntrega() {
    }

    public SolicitacaoEntrega(@NotNull(message = "O idEstabelecimento deve ser informado") String idEstabelecimento) {
        this.idEstabelecimento = idEstabelecimento;
    }

    public String getIdEstabelecimento() {
        return idEstabelecimento;
    }

    public void setIdEstabelecimento(String idEstabelecimento) {
        this.idEstabelecimento = idEstabelecimento;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
