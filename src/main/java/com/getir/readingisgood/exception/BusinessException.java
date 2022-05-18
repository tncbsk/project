package com.getir.readingisgood.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class BusinessException extends GeneralException {


    public BusinessException(String errorCode , Object... messageArguments){
        super(errorCode , messageArguments);
    }

    BusinessException(){
        super();
    }
}
