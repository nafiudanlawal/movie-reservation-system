package com.nafiu.moviereservationservice.reservation.dto;

import com.nafiu.moviereservationservice.reservation.model.Seat;

import java.util.List;

public record VenueResponseDto(
        Integer id,
        String name,

        String address,

        List<Seat> seat
) {
}
