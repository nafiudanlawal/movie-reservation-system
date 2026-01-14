package com.nafiu.moviereservationservice.movies;

import com.nafiu.moviereservationservice.movies.dto.GenreCreateDto;
import com.nafiu.moviereservationservice.movies.dto.GenreResponseDto;
import com.nafiu.moviereservationservice.movies.mapper.GenreMapper;
import com.nafiu.moviereservationservice.movies.model.Genre;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class GenreMapperTest {
    @Test
    public void showChangeGenreToResponseDto(){
        String title = "test genre";
        String description = "test description";
        Genre genre = new Genre(title, description);

        GenreResponseDto genreResponseDto = GenreMapper.genreToGenreResponseDto(genre);

        assertNull(genre.getId());
        assertEquals(genre.getTitle(), genreResponseDto.title());
        assertEquals(genre.getDescription(), genreResponseDto.description());
        assertEquals(genre.getCreatedAt(), genreResponseDto.createAt());
        assertEquals(genre.getUpdatedAt(), genreResponseDto.updatedAt());

    }

    @Test
    public void showCreateDtoToGenre(){
        String title = "test genre";
        String description = "test description";


        GenreCreateDto genreCreateDto = new GenreCreateDto(title, description);

        Genre genre = GenreMapper.genreFromGenreCreateDto(genreCreateDto);

        assertNull(genre.getId());
        assertEquals(genre.getTitle(), genreCreateDto.title());
        assertEquals(genre.getDescription(), genreCreateDto.description());
        assertNotNull(genre.getCreatedAt());
        assertNotNull(genre.getUpdatedAt());

    }

}
