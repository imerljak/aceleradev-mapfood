package mapfood.factory;

import mapfood.model.dto.ClienteDTO;
import mapfood.model.jpa.Cliente;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClienteFactoryTest {

    @Test
    public void deveRetornarClienteDTOQuantoPassarCliente() {

        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setPosicao(new PointFactory().fromLatLong(200.00, -200.00));

        ClienteDTO dto = ClienteFactory.getInstance(cliente);

        assertEquals(cliente.getId(), dto.getId());
        assertEquals(cliente.getPosicao().getY(), dto.getLatitude(), 0);
        assertEquals(cliente.getPosicao().getX(), dto.getLongitude(), 0);
    }

    @Test
    public void deveRetornarClienteQuantoPassarClienteDTO() {

        ClienteDTO dto = new ClienteDTO();
        dto.setId(1L);
        dto.setLatitude(200.00);
        dto.setLongitude(-200.00);

        Cliente cliente = ClienteFactory.getInstance(dto);

        assertEquals(dto.getId(), cliente.getId());
        assertEquals(dto.getLatitude(), cliente.getPosicao().getY(), 0);
        assertEquals(dto.getLongitude(), cliente.getPosicao().getX(), 0);
    }

}