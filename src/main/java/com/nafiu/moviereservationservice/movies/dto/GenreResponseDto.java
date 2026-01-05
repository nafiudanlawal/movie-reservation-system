package com.nafiu.moviereservationservice.movies.dto;

import java.util.Date;

public record GenreResponseDto(
        Integer id,
        String title,
        String description,
        Date createAt,

        Date updatedAt
) {}
