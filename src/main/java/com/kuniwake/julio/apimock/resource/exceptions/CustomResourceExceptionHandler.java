package com.kuniwake.julio.apimock.resource.exceptions;

import com.kuniwake.julio.apimock.services.exceptions.CustomObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@ControllerAdvice
public class CustomResourceExceptionHandler {

    @ExceptionHandler(CustomObjectNotFoundException.class)
    public ResponseEntity<CustomStandardError> objectNotFound(CustomObjectNotFoundException ex, HttpServletRequest request){
        CustomStandardError error = new CustomStandardError(
                        LocalDate.now(),
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
