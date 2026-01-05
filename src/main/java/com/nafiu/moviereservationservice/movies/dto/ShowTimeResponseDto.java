package com.nafiu.moviereservationservice.movies.dto;

import com.nafiu.moviereservationservice.movies.model.Movie;
import com.nafiu.moviereservationservice.reservation.model.Venue;
import java.time.LocalDate;
import java.time.LocalTime;

public record ShowTimeResponseDto(
        Integer id,
        LocalDate date,
        LocalTime time,
        Movie movie,
        Venue venue,
        Double price
) {
}
