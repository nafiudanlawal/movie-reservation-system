package com.nafiu.moviereservationservice.movies.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.FutureOrPresent;

import java.time.LocalDate;
import java.time.LocalTime;

public record ShowTimeMovieUpdateDto(
        @FutureOrPresent
        LocalDate date,

        LocalTime time,

        Integer venueId,
        @Digits(integer = 10, fraction = 2)
        Double price
) {
}
