package com.nafiu.moviereservationservice.reservation.dto;

import com.nafiu.moviereservationservice.reservation.model.Seat;
import jakarta.validation.constraints.Min;

import java.util.List;

public record VenueResponseDto(
        Integer id,
        String name,

        String address,

        Integer capacity,

        List<Seat> seat
) {
}
