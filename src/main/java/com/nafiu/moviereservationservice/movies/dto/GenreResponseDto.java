package com.nafiu.moviereservationservice.movies.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record GenreResponseDto(
        Integer id,
        String title,
        String description,
        Date createAt,

        Date updatedAt
) {}
