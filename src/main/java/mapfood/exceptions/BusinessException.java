package mapfood.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -2917739192728339336L;

    public ResponseEntity<?> constroiResposta() {
        return ResponseEntity.status(getStatus()).body(new RespostaErro(getMensagemErro()));
    }

    protected abstract HttpStatus getStatus();

    public abstract String getMensagemErro();
}
