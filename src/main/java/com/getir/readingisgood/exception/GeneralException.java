package com.getir.readingisgood.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class GeneralException extends RuntimeException{

    private final String errorCode;

    private final Object[] messageArguments;

    public GeneralException(){
        this.errorCode = null;
        this.messageArguments = new Object[0];
    }

    public GeneralException(String errorCode , Object... messageArguments){
        this.errorCode = errorCode;
        this.messageArguments = messageArguments;

    }

    public Optional<String> getErrorCode(){
        return Optional.ofNullable(errorCode);
    }

    public Optional<Object[]> getMessageArguments() {
        return Optional.ofNullable(this.messageArguments);
    }
}
