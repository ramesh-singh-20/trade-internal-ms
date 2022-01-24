package com.alphacoder.controller.advice;

import com.alphacoder.domain.error.ErrorDto;
import com.alphacoder.domain.response.ResponseDto;
import com.alphacoder.exception.ApplicationDomainException;
import com.alphacoder.util.TradeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Slf4j
public class TradingJournalAdvice {

    @ExceptionHandler(ApplicationDomainException.class)
    public ResponseEntity<?> handleApplicationDomainException(ApplicationDomainException ex){
        log.error(ex.getMessage());
        ResponseDto responseDto= ResponseDto.forError(ex.getErrors().get(0));
        return new ResponseEntity<>(responseDto, ex.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        log.error(ex.getMessage());
        BindingResult bindingResult= ex.getBindingResult();
        List<FieldError> fieldErrors= bindingResult.getFieldErrors();
        //log.error(fieldErrors.get(0).getDefaultMessage());
        List<ErrorDto> errors= TradeUtil.getErrorDtos("TRADE_1003", fieldErrors.get(0).getDefaultMessage());
        ResponseDto responseDto= ResponseDto.forError(errors.get(0));
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<?> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex){
        log.error(ex.getMessage());
        List<ErrorDto> errors= TradeUtil.getErrorDtos("TRADE_1004", "No Data found For given input.");
        ResponseDto responseDto= ResponseDto.forError(errors.get(0));
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex){
        log.error(ex.getMessage());
        List<ErrorDto> errors= TradeUtil.getErrorDtos("TRADE_1005", "Required parameters are missing.");
        ResponseDto responseDto= ResponseDto.forError(errors.get(0));
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex){
        log.error(ex.getMessage());
        List<ErrorDto> errors= TradeUtil.getErrorDtos("TRADE_1002",
                "Something went wrong. Please try again after sometime.");
        ResponseDto responseDto= ResponseDto.forError(errors.get(0));
        return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
