package mapfood.exceptions;

import org.springframework.http.ResponseEntity;

public abstract class BusinessException extends RuntimeException {

    public BusinessException(){
        super();
    }

    public ResponseEntity<?> constroiResposta() {
       return ResponseEntity.ok(new RespostaErro(getMensagemErro()));
    }

    public abstract String getMensagemErro();
}