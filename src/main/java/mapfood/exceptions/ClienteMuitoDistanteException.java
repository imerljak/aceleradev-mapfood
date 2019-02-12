package mapfood.exceptions;

import mapfood.dto.ClienteDTO;
import mapfood.model.jpa.Cliente;
import org.springframework.http.HttpStatus;

public class ClienteMuitoDistanteException extends BusinessException {

    private static final long serialVersionUID = -8036503715196979663L;

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

    @Override
    protected HttpStatus getStatus() {
        return HttpStatus.PRECONDITION_FAILED;
    }
}
