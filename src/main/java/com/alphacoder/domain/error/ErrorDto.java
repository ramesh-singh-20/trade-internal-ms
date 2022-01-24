package com.alphacoder.domain.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ErrorDto {
    private String code;
    private String message;
}
