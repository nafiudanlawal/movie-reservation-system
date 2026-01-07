package com.nafiu.moviereservationservice.movies.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record ShowTimeMovieCreateDto(
        @FutureOrPresent
        @NotNull
        LocalDate date,

        @NotNull
        LocalTime time,

        @NotNull
        Integer venueId,

        @Digits(integer = 10, fraction = 2)
        @NotNull
        Double price
) {
}
