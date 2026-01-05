package com.nafiu.moviereservationservice.movies.mapper;

import com.nafiu.moviereservationservice.movies.dto.GenreCreateDto;
import com.nafiu.moviereservationservice.movies.dto.GenreResponseDto;
import com.nafiu.moviereservationservice.movies.model.Genre;

public class GenreMapper {
    public static Genre genreFromGenreCreateDto(GenreCreateDto genreCreateDto) {
        return new Genre(genreCreateDto.title(), genreCreateDto.description());
    }

    public static GenreResponseDto genreToGenreResponseDto(Genre genre) {
        return new GenreResponseDto(
                genre.getId(),
                genre.getTitle(),
                genre.getDescription(),
                genre.getCreatedAt(),
                genre.getUpdatedAt()
        );
    }
}
