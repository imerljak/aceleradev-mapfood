package mapfood.exceptions;

import mapfood.model.dto.ClienteDTO;
import mapfood.model.jpa.Cliente;

public class ClienteNaoEncontradoException extends BusinessException {

    private final Long idCliente;

    public ClienteNaoEncontradoException(Cliente cliente) {
        idCliente = cliente.getId();
    }

    public ClienteNaoEncontradoException(ClienteDTO clienteDTO) {
        idCliente = clienteDTO.getId();
    }


    @Override
    public String getMensagemErro() {
        return String.format("Cliente não encontrado: %d", idCliente);
    }
}
