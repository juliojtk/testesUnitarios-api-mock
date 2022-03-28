package com.kuniwake.julio.apimock.resource.exceptions;

import com.kuniwake.julio.apimock.services.exceptions.MyDataIntegrityViolationException;
import com.kuniwake.julio.apimock.services.exceptions.MyObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ManagerResourceExceptionHandlerTest {

    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
    public static final String E_MAIL_JA_CADASTRADO = "E-mail já cadastrado";

    @InjectMocks
    private ManagerResourceExceptionHandler managerResourceExceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void when_objectNotFoundException_then_return_responseEntity() {
        ResponseEntity<ManagerStandardError> response = managerResourceExceptionHandler
                .objectNotFound(
                        new MyObjectNotFoundException(OBJETO_NAO_ENCONTRADO),
                        new MockHttpServletRequest());

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(ManagerStandardError.class, response.getBody().getClass());
        Assertions.assertEquals(OBJETO_NAO_ENCONTRADO, response.getBody().getError());
        Assertions.assertEquals(404, response.getBody().getStatus());
    }

    @Test
    void when_objectDataIntegrityViolationException_then_return_responseEntity () {

        ResponseEntity<ManagerStandardError> response = managerResourceExceptionHandler
                .dataIntegrityViolation(
                        new MyDataIntegrityViolationException(E_MAIL_JA_CADASTRADO),
                        new MockHttpServletRequest());

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(ManagerStandardError.class, response.getBody().getClass());
        Assertions.assertEquals(E_MAIL_JA_CADASTRADO, response.getBody().getError());
        Assertions.assertEquals(400, response.getBody().getStatus());
        Assertions.assertNotEquals("/user/2", response.getBody().getPath()); // Tem que ser Diferente do Path do response
        Assertions.assertNotEquals(LocalDateTime.now(), response.getBody().getTimestamp());




    }
}