package com.nafiu.moviereservationservice.reservation.dto;

import jakarta.validation.constraints.Pattern;

public record VenueUpdateDto(
        @Pattern(regexp = ".*\\S.*", message = "cannot be empty or just whitespace")
        String name,
        @Pattern(regexp = ".*\\S.*", message = "cannot be empty or just whitespace")
        String address
) { }
