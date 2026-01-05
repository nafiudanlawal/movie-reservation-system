package com.nafiu.moviereservationservice.reservation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SeatCreateDto(
        String description,

        @NotNull @NotBlank
        String label,
        @NotNull
        Integer venueId
) { }
