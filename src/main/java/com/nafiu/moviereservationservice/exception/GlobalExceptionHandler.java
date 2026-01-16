package com.nafiu.moviereservationservice.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiErrorResponseDto> notFound(NoSuchElementException ex) {
        return ApiErrorResponseDtoMapper.createErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({EntityNotFoundException.class, NoResourceFoundException.class})
    public ResponseEntity<ApiErrorResponseDto> entityNotFound(Exception ex) {
        return ApiErrorResponseDtoMapper.createErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
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
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }

    @ExceptionHandler({DataIntegrityViolationException.class, HttpMessageConversionException.class, MissingServletRequestPartException.class})
    public ResponseEntity<ApiErrorResponseDto> badRequestData(Exception ex) {
        return ApiErrorResponseDtoMapper.createErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ApiErrorResponseDto> unsupportedRequestMediaType(HttpMediaTypeNotSupportedException ex) {
        return ApiErrorResponseDtoMapper.createErrorResponse(ex.getMessage(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponseDto> handleGeneralException(Exception ex) {
        ex.printStackTrace();
        return ApiErrorResponseDtoMapper.createErrorResponse("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ApiErrorResponseDto> forbiddenRequestData(AuthorizationDeniedException ex) {
        return ApiErrorResponseDtoMapper.createErrorResponse(ex.getMessage(), HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler({AccessDeniedException.class, AuthenticationException.class, CredentialsExpiredException.class})
    public ResponseEntity<ApiErrorResponseDto> deniedRequestData(Exception ex) {
        return ApiErrorResponseDtoMapper.createErrorResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
