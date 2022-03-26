package com.kuniwake.julio.apimock.resource.exceptions;

import com.kuniwake.julio.apimock.services.exceptions.MyDataIntegratyViolationException;
import com.kuniwake.julio.apimock.services.exceptions.MyObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@ControllerAdvice
public class ManagerResourceExceptionHandler {

    @ExceptionHandler(MyObjectNotFoundException.class)
    public ResponseEntity<ManagerStandardError> objectNotFound(MyObjectNotFoundException ex, HttpServletRequest request){
        ManagerStandardError error = new ManagerStandardError(
                        LocalDate.now(),
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MyDataIntegratyViolationException.class)
    public ResponseEntity<ManagerStandardError> dataIntegratViolation(MyDataIntegratyViolationException ex, HttpServletRequest request){
        ManagerStandardError error = new ManagerStandardError(
                LocalDate.now(),
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
