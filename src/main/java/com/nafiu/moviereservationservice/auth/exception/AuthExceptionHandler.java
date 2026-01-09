package com.nafiu.moviereservationservice.auth.exception;

import com.nafiu.moviereservationservice.exceptions.ApiErrorResponseDto;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.util.Date;

@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(AuthorizationDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiErrorResponseDto forbiddenRequestData(AuthorizationDeniedException ex) {
        return new ApiErrorResponseDto(ex.getMessage(), HttpStatus.FORBIDDEN.value(), new Date());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiErrorResponseDto deniedRequestData(AccessDeniedException ex) {
        return new ApiErrorResponseDto(ex.getMessage(), HttpStatus.UNAUTHORIZED.value(), new Date());
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiErrorResponseDto unauthorizedRequestData(AuthenticationException ex) {
        return new ApiErrorResponseDto(ex.getMessage(), HttpStatus.UNAUTHORIZED.value(), new Date());
    }
    @ExceptionHandler(CredentialsExpiredException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiErrorResponseDto credentialExpiredException(CredentialsExpiredException ex) {
        return new ApiErrorResponseDto(ex.getMessage(), HttpStatus.UNAUTHORIZED.value(), new Date());
    }

}
