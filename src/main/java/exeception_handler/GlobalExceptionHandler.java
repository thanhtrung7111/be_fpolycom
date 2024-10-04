package exeception_handler;


import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;
import service.data_return.DataReturnService;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    DataReturnService dataReturnService;

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        return  ResponseEntity.ok(dataReturnService.endpointNotFound());
    }




    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> handleuserNameNotFoundException(UsernameNotFoundException ex) {
        return  ResponseEntity.ok(dataReturnService.notFoundObject(ex.getMessage()));
    }

    @ExceptionHandler(NotRightException.class)
    public ResponseEntity<Object> handleuserNameNotFoundException(NotRightException ex) {
        return  ResponseEntity.ok(dataReturnService.notRight(ex.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleuserNameNotFoundException(RuntimeException ex) {
        return  ResponseEntity.ok(dataReturnService.notFoundObject(ex.getMessage()));
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<Object> handleTokenExpiredException(TokenExpiredException ex) {
        System.out.println("sai");
        return  ResponseEntity.ok(dataReturnService.tokenExpired(ex.getMessage()));
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> handleDataNotFoundException(DataNotFoundException ex) {
        return  ResponseEntity.ok(dataReturnService.dataNotFound(ex.getMessage()));
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return  ResponseEntity.ok(dataReturnService.failure(errors.toString()));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleOtherExceptions(Exception ex) {
        return  ResponseEntity.ok(dataReturnService.failure(ex.getMessage()));
    }
}
