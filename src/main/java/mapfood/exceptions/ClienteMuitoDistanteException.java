package mapfood.exceptions;

import mapfood.model.dto.ClienteDTO;
import mapfood.model.jpa.Cliente;

public class ClienteMuitoDistanteException extends BusinessException {

    private Long idCliente;

    public ClienteMuitoDistanteException(Cliente cliente) {
        idCliente = cliente.getId();
    }

    public ClienteMuitoDistanteException(ClienteDTO clienteDTO) {
        idCliente = clienteDTO.getId();
    }

    @Override
    public String getMensagemErro() {
        return String.format("Cliente não encontrado: %d", idCliente);
    }
}
