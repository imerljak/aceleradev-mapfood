package mapfood.exceptions;

import com.google.maps.model.LatLng;
import org.apache.logging.log4j.util.Strings;

import java.util.List;

public class ClienteMuitoDistanteException extends BusinessException {
    public ClienteMuitoDistanteException() {
    }

    public ClienteMuitoDistanteException(String s) {
        super(s);
    }

    public ClienteMuitoDistanteException(List<LatLng> pontosForaDaArea) {
        super(Strings.join(pontosForaDaArea, '|'));
    }
}
