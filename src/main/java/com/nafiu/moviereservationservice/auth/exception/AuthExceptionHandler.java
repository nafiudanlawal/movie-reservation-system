package com.nafiu.moviereservationservice.auth.exception;


import com.nafiu.moviereservationservice.exceptions.ApiErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class AuthExceptionHandler {
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponseDto notFound(AuthenticationException ex) {
        return new ApiErrorResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), new Date());
    }
}
