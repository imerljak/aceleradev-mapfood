package mapfood.model.dto;

import javax.validation.constraints.NotNull;

public class Pedido {

    @NotNull(message = "O idCliente deve ser informado.")
    private Long idCliente;

    public Pedido(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Pedido() {
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

}
