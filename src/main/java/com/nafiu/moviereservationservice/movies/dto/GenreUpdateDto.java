package com.nafiu.moviereservationservice.movies.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GenreUpdateDto(
        String title,
        String description
) {}
