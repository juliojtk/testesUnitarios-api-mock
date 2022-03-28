package com.kuniwake.julio.apimock.services.exceptions;

public class MyDataIntegrityViolationException extends RuntimeException{

    //Exceção de Violação de Integridade com o Banco de Dados
    public MyDataIntegrityViolationException(String message) {
        super(message);
    }
}
