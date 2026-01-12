package com.nafiu.moviereservationservice.movies.test;

import com.nafiu.moviereservationservice.movies.dto.MovieCreateDto;
import com.nafiu.moviereservationservice.movies.dto.MovieResponseDto;
import com.nafiu.moviereservationservice.movies.mapper.MovieMapper;
import com.nafiu.moviereservationservice.movies.model.Genre;
import com.nafiu.moviereservationservice.movies.model.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Profile;

import static org.junit.jupiter.api.Assertions.*;

@Profile("test")
public class MovieMapperTest {

    @Test
    public void showChangeGenreToResponseDto() {
        Integer id = 1;
        String title = "test genre";
        String description = "test description";
        String posterImage = "test image";
        Integer year = 2015;
        Genre genre = new Genre(title, description);

        Movie movie = new Movie(title, description, year, genre, posterImage);
        movie.setId(id);

        MovieResponseDto movieToMovieResponseDto = MovieMapper.movieToMovieResponseDto(movie);

        assertEquals(movie.getId(), movieToMovieResponseDto.id());
        assertEquals(movie.getTitle(), movieToMovieResponseDto.title());
        assertEquals(movie.getDescription(), movieToMovieResponseDto.description());
        assertEquals(movie.getYear(), movieToMovieResponseDto.year());
        assertEquals(movie.getPosterImage(), movieToMovieResponseDto.posterImage());
        assertEquals(movie.getGenre(), movieToMovieResponseDto.genre());
        assertEquals(movie.getCreatedAt(), movieToMovieResponseDto.createAt());
        assertEquals(movie.getUpdatedAt(), movieToMovieResponseDto.updatedAt());

    }

    @Test
    public void showCreateDtoToGenre() {
        Integer id = 1;
        Integer genreId = 1;
        String title = "test genre";
        String description = "test description";
        String posterImage = "test image";
        Integer year = 2015;
        Genre genre = new Genre(title, description);
        genre.setId(genreId);


        MovieCreateDto movieCreateDto = new MovieCreateDto(title, description, year, genre.getId());

        Movie movie = MovieMapper.movieCreateDtoToMovie(movieCreateDto, genre, posterImage);

        assertEquals(movie.getTitle(), movieCreateDto.title());
        assertEquals(movie.getDescription(), movieCreateDto.description());
        assertEquals(movie.getYear(), movieCreateDto.year());
        assertEquals(movie.getPosterImage(), posterImage);
        assertEquals(movie.getGenre().getId(), movieCreateDto.genreId());
        assertNotNull(movie.getCreatedAt());
        assertNotNull(movie.getUpdatedAt());

    }

}
