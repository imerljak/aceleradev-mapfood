package mapfood.factory;

import mapfood.model.dto.ClienteDTO;
import mapfood.model.jpa.Cliente;

public final class ClienteFactory {

    public static ClienteDTO getInstance(Cliente cliente) {
        final ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setPosicao(cliente.getPosicao());

        return clienteDTO;
    }

    public static Cliente getInstance(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setPosicao(clienteDTO.getPosicao());

        return cliente;
    }

}
