package com.example.emailservicespringboot.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.Map;
@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class HttpResponse {

    protected int statusCode;
    protected String timeStamp;
    protected HttpStatus httpStatus;
    protected String message;
    protected String path;
    protected String requestedMethod;
    protected Map<?,?>  data;
    protected String  developerMessage;

}
