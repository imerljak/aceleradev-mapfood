package mapfood.exceptions;

import com.google.maps.model.LatLng;
import mapfood.model.dto.ClienteDTO;
import mapfood.model.jpa.Cliente;
import org.apache.logging.log4j.util.Strings;

import java.util.List;

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
