package com.nafiu.moviereservationservice.movies.mapper;

import com.nafiu.moviereservationservice.movies.dto.MovieCreateDto;
import com.nafiu.moviereservationservice.movies.dto.MovieResponseDto;
import com.nafiu.moviereservationservice.movies.model.Genre;
import com.nafiu.moviereservationservice.movies.model.Movie;
import jakarta.validation.constraints.NotNull;

public class MovieMapper {

    public static MovieResponseDto movieToMovieResponseDto(Movie movie) {
        return new MovieResponseDto(
                movie.getId(),
                movie.getTitle(),
                movie.getDescription(),
                movie.getYear(),
                movie.getGenre(),
                movie.getPosterImage(),
                movie.getCreatedAt(),
                movie.getUpdatedAt()
        );
    }

    public static Movie movieCreateDtoToMovie(MovieCreateDto movieDto, @NotNull Genre genre, String posterImage) {
        Movie movie = new Movie();
        movie.setDescription(movieDto.description());
        movie.setTitle(movieDto.title());
        movie.setYear(movieDto.year());
        movie.setGenre(genre);
        movie.setPosterImage(posterImage);
        return movie;
    }
}
