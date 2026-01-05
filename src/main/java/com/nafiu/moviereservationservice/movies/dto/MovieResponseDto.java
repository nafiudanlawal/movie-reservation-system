package com.nafiu.moviereservationservice.movies.dto;

import com.nafiu.moviereservationservice.movies.model.Genre;

import java.util.Date;

public record MovieResponseDto (
        Integer id,
        String title,
        String description,
        int year,

        Genre genre,

        String posterImage,

        Date createAt,

        Date updatedAt
){
}
