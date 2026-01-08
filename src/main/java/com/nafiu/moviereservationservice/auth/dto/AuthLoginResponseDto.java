package com.nafiu.moviereservationservice.auth.dto;

public record AuthLoginResponseDto(
        String token,
        String message

) {
}
