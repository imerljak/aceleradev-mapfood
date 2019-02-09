package mapfood.exceptions;

import org.springframework.http.ResponseEntity;

public abstract class BusinessException extends RuntimeException {

    public BusinessException(){

    }

    public BusinessException(String message) {
        super(message);
    }

    public ResponseEntity<?> constroiResposta() {
       return ResponseEntity.ok(new RespostaErro());
    }
}
