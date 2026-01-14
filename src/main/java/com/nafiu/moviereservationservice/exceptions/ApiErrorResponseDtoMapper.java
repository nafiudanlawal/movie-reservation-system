package com.nafiu.moviereservationservice.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.Date;

public class ApiErrorResponseDtoMapper {
    public static ResponseEntity<ApiErrorResponseDto> createErrorResponse(String message, HttpStatusCode statusCode) {
        return new ResponseEntity<>(new ApiErrorResponseDto(message, new Date()), statusCode);
    }
}
