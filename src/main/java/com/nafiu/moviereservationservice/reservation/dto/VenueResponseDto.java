package com.nafiu.moviereservationservice.reservation.dto;

public record VenueResponseDto(
        Integer id,
        String name,

        String address,

        Integer capacity
) {
}
