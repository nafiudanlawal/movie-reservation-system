package com.nafiu.moviereservationservice.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponseDto notFound(NoSuchElementException ex) {
        return new ApiErrorResponseDto(ex.getMessage(), HttpStatus.NOT_FOUND.value(), new Date());
    }

    @ExceptionHandler({EntityNotFoundException.class, NoResourceFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponseDto entityNotFound(Exception ex) {
        return new ApiErrorResponseDto(ex.getMessage(), HttpStatus.NOT_FOUND.value(), new Date());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponseDto notFound(Exception ex) {
        return new ApiErrorResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), new Date());
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponseDto badRequestData(DataIntegrityViolationException ex) {
        return new ApiErrorResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), new Date());
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponseDto handleGeneralException(Exception ex) {
        ex.printStackTrace();
        return new ApiErrorResponseDto(
                "Internal server error.",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date()
        );
    }

}
