package com.nafiu.moviereservationservice.reservation.dto;

import jakarta.validation.constraints.Pattern;

public record SeatVenueUpdateDto(
        @Pattern(regexp = ".*\\S.*", message = "cannot be empty or just whitespace")
        String description,

        @Pattern(regexp = ".*\\S.*", message = "cannot be empty or just whitespace")
        String label
) { }
