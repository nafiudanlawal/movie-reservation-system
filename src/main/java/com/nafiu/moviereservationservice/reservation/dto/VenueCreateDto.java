package com.nafiu.moviereservationservice.reservation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VenueCreateDto(
        @NotBlank
        String name,

        @NotBlank
        String address
) { }
