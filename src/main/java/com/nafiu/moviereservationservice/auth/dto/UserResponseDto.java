package com.nafiu.moviereservationservice.auth.dto;

public record UserResponseDto(
        Integer id,
        String name,
        String username,
        String role
) {
}
