package mapfood.factory;

import mapfood.model.dto.ClienteDTO;
import mapfood.model.jpa.Cliente;
import mapfood.model.jpa.Posicao;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClienteFactoryTest {

    @Test
    public void deveRetornarClienteDTOQuantoPassarCliente() {

        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setPosicao(Posicao.of(200.00, -200.00));

        ClienteDTO dto = ClienteFactory.getInstance(cliente);

        assertEquals(cliente.getId(), dto.getId());
        assertEquals(cliente.getPosicao(), dto.getPosicao());
    }

    @Test
    public void deveRetornarClienteQuantoPassarClienteDTO() {

        ClienteDTO dto = new ClienteDTO();
        dto.setId(1L);
        dto.setPosicao(Posicao.of(200.00, -200.00));

        Cliente cliente = ClienteFactory.getInstance(dto);

        assertEquals(dto.getId(), cliente.getId());
        assertEquals(dto.getPosicao(), cliente.getPosicao());
    }

}