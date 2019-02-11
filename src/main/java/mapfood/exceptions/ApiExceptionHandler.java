package mapfood.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ApiExceptionHandler {


    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<?> handleBusinessExceptions(HttpServletRequest hsr, BusinessException e) {
        return e.constroiResposta();
    }

}
