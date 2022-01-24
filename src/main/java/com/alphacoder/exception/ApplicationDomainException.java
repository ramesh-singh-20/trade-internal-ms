package com.alphacoder.exception;

import com.alphacoder.domain.error.ErrorDto;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ApplicationDomainException extends RuntimeException{
    private final List<ErrorDto> errors;
    private final HttpStatus status;

    public ApplicationDomainException(List<ErrorDto> errors, HttpStatus status){
        super();
        this.errors= errors;
        this.status= status;
    }

    public List<ErrorDto> getErrors() {
        return errors;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
