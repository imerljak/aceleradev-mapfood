package mapfood.exceptions;

import org.springframework.http.HttpStatus;

public class ClienteMuitoDistanteException extends BusinessException {

    private static final long serialVersionUID = -8036503715196979663L;

    private Double distancia;

    public ClienteMuitoDistanteException(Double distancia) {
        this.distancia = distancia;
    }

    @Override
    public String getMensagemErro() {
        return String.format("Cliente muito distante: %.2f", distancia);
    }

    @Override
    protected HttpStatus getStatus() {
        return HttpStatus.PRECONDITION_FAILED;
    }
}
