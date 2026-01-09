package com.nafiu.moviereservationservice.reservation.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VenueCreateDto(
        @NotBlank
        String name,

        @NotBlank
        String address,

        @NotNull
        @Min(0)
        Integer capacity
) {
}
