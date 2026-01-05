package com.nafiu.moviereservationservice.reservation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VenueSeatCreateDto(
        String description,

        @NotNull @NotBlank
        String label
) { }
