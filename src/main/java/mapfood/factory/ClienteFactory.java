package mapfood.factory;

import mapfood.dto.ClienteDTO;
import mapfood.model.jpa.Cliente;

public final class ClienteFactory {

    public static ClienteDTO getInstance(Cliente cliente) {
        final ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setPosicao(cliente.getPosicao());

        return clienteDTO;
    }

    public static Cliente getInstance(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setPosicao(clienteDTO.getPosicao());

        return cliente;
    }

}
