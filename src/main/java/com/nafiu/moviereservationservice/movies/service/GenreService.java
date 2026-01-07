package com.nafiu.moviereservationservice.movies.service;

import com.nafiu.moviereservationservice.movies.dto.GenreCreateDto;
import com.nafiu.moviereservationservice.movies.dto.GenreResponseDto;
import com.nafiu.moviereservationservice.movies.dto.GenreUpdateDto;
import com.nafiu.moviereservationservice.movies.dto.MovieResponseDto;
import com.nafiu.moviereservationservice.movies.mapper.GenreMapper;
import com.nafiu.moviereservationservice.movies.mapper.MovieMapper;
import com.nafiu.moviereservationservice.movies.model.Genre;
import com.nafiu.moviereservationservice.movies.repository.GenreRepository;
import com.nafiu.moviereservationservice.movies.repository.MovieRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GenreService {
    private final GenreRepository genreRepository;
    private final MovieRepository movieRepository;

    public GenreService(GenreRepository genreRepository, MovieRepository movieRepository) {
        this.genreRepository = genreRepository;
        this.movieRepository = movieRepository;
    }

    public List<GenreResponseDto> getAll() {
        return genreRepository.findAll().stream().map(GenreMapper::genreToGenreResponseDto).toList();
    }

    public GenreResponseDto getOne(Integer id) throws EntityNotFoundException {
        Genre genre = this.genreRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Genre with id %d not found".formatted(id))
        );
        return GenreMapper.genreToGenreResponseDto(genre);
    }

    public void deleteOne(Integer id) {
        genreRepository.deleteById(id);
    }

    public GenreResponseDto createGenre(GenreCreateDto genreDto) {
        Genre genre = GenreMapper.genreFromGenreCreateDto(genreDto);
        this.genreRepository.save(genre);
        return GenreMapper.genreToGenreResponseDto(genre);
    }

    public GenreResponseDto updateGenre(Integer id, GenreUpdateDto genreDto) {
        Genre genre = this.genreRepository.getReferenceById(id);
        if (genreDto.title() != null) {
            genre.setTitle(genreDto.title());
        }
        if (genreDto.description() != null) {
            genre.setDescription(genreDto.description());
        }
        genre.setUpdatedAt(new Date());
        this.genreRepository.save(genre);
        return GenreMapper.genreToGenreResponseDto(genre);
    }

    public List<MovieResponseDto> getGenreMovies(Integer genreId) {
        return movieRepository.getMovieByGenre_Id(genreId).stream().map(MovieMapper::movieToMovieResponseDto).toList();
    }
}

