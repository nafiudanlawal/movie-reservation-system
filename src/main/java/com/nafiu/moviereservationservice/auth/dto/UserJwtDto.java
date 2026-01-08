package com.nafiu.moviereservationservice.auth.dto;

public record UserJwtDto(
        Integer id,
        String name,
        String username,
        String role
) {
}
