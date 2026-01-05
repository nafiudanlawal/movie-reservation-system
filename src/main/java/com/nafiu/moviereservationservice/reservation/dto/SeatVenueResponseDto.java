package com.nafiu.moviereservationservice.reservation.dto;

import com.nafiu.moviereservationservice.reservation.model.Venue;

public record SeatVenueResponseDto(
        Integer id,
        String label,
        String description
) {
}
