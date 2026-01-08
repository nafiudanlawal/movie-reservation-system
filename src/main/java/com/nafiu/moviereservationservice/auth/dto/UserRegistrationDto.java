package com.nafiu.moviereservationservice.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegistrationDto(
        @NotBlank
        String name,
        @NotBlank
        String username,

        @NotBlank @Size(min = 5, message = "must be at least 5 characters.")
        String password
) {
}