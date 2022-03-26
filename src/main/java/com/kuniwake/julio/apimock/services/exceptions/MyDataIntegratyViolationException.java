package com.kuniwake.julio.apimock.services.exceptions;

public class MyDataIntegratyViolationException extends RuntimeException{

    //Exceção de Violação de Integridade com o Banco de Dados
    public MyDataIntegratyViolationException(String message) {
        super(message);
    }
}
