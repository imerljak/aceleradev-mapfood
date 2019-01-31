package mapfood.factory;

import com.vividsolutions.jts.geom.Point;
import mapfood.model.dto.ClienteDTO;
import mapfood.model.jpa.Cliente;

public class ClienteFactory {

    public static ClienteDTO getInstance(Cliente cliente) {
        final ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setLatitude(cliente.getPosicao().getY());
        clienteDTO.setLongitude(cliente.getPosicao().getX());

        return clienteDTO;
    }

    public static Cliente getInstance(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());

        Point point = new PointFactory().fromLatLong(clienteDTO.getLatitude(), clienteDTO.getLongitude());
        cliente.setPosicao(point);

        return cliente;
    }

}
