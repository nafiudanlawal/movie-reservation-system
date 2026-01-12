package com.nafiu.moviereservationservice.movies.dto;

import jakarta.validation.constraints.Min;

public record MovieUpdateDto(
        String title,

        String description,

        @Min(value = 1800, message = "year must be greater than 1800")
        Integer year,

        Integer genreId

) {
}
