package com.nafiu.moviereservationservice.movies.dto;

import com.nafiu.moviereservationservice.reservation.model.Venue;

import java.time.LocalDate;
import java.time.LocalTime;

public record ShowTimeMovieResponseDto(
        Integer id,
        LocalDate date,
        LocalTime time,
        Double price,
        Venue venue

) {
}
