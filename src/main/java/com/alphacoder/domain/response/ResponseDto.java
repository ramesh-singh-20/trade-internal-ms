package com.alphacoder.domain.response;

import com.alphacoder.domain.error.ErrorDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
public class ResponseDto<T> {

    private ResponseStatus status;
    private T data;
    private List<ErrorDto> errors;

    public ResponseDto(ResponseStatus status, T data){
        this.status= status;
        this.data= data;
    }

    public ResponseDto(ResponseStatus status, List<ErrorDto> errors){
        this.status= status;
        this.errors= errors;
    }

    public static <T> ResponseDto<T> forSuccess(T data){
        return new ResponseDto(ResponseStatus.SUCCESS, data);
    }

    public static<T> ResponseDto<T> forError(ErrorDto... errors) {
        return new ResponseDto(ResponseStatus.ERROR, Arrays.asList(errors));
    }
}
