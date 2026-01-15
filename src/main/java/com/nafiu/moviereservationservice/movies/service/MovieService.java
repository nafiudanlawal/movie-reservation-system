package com.nafiu.moviereservationservice.movies.service;

import com.nafiu.moviereservationservice.movies.dto.GenreResponseDto;
import com.nafiu.moviereservationservice.movies.dto.MovieCreateDto;
import com.nafiu.moviereservationservice.movies.dto.MovieResponseDto;
import com.nafiu.moviereservationservice.movies.dto.MovieUpdateDto;
import com.nafiu.moviereservationservice.movies.mapper.GenreMapper;
import com.nafiu.moviereservationservice.movies.mapper.MovieMapper;
import com.nafiu.moviereservationservice.movies.model.Genre;
import com.nafiu.moviereservationservice.movies.model.Movie;
import com.nafiu.moviereservationservice.movies.repository.GenreRepository;
import com.nafiu.moviereservationservice.movies.repository.MovieRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final StorageService storageService;

    public MovieService(MovieRepository movieRepository, GenreRepository genreRepository, StorageService storageService) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.storageService = storageService;
    }

    public List<MovieResponseDto> getAll() {
        return movieRepository.findAll().stream().map(MovieMapper::movieToMovieResponseDto).toList();
    }

    public MovieResponseDto getOne(Integer id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Movie with id %d not found".formatted(id))
        );
        return MovieMapper.movieToMovieResponseDto(movie);
    }

    public void deleteOne(Integer id) {
        movieRepository.deleteById(id);
    }

    public MovieResponseDto createMovie(MovieCreateDto movieDto, @NotNull MultipartFile posterImageFile) throws Exception {
        Genre genre = this.genreRepository.findById(movieDto.genreId()).orElseThrow(() ->
                new EntityNotFoundException("Genre with id %d not found".formatted(movieDto.genreId()))
        );
        String posterImage = storageService.uploadMultipartFile(posterImageFile); // generate image poster
        Movie movie = MovieMapper.movieCreateDtoToMovie(movieDto, genre, posterImage);
        this.movieRepository.save(movie);
        return MovieMapper.movieToMovieResponseDto(movie);
    }

    public MovieResponseDto updateOne(Integer id, MovieUpdateDto movieDto) {
        Movie movie = movieRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Movie with id %d not found".formatted(id))
        );
        if (movieDto.title() != null) {
            movie.setTitle(movieDto.title());
        }
        if (movieDto.description() != null) {
            movie.setDescription(movieDto.description());
        }
        if (movieDto.year() != null) {
            movie.setYear(movieDto.year());
        }

        if (movieDto.genreId() != null) {
            Genre genre = this.genreRepository.findById(movieDto.genreId()).orElseThrow(() ->
                    new EntityNotFoundException("Genre with id %d not found".formatted(movieDto.genreId()))
            );
            movie.setGenre(genre);
        }
        this.movieRepository.save(movie);
        return MovieMapper.movieToMovieResponseDto(movie);
    }

    public GenreResponseDto getMovieGenre(Integer id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Movie with id %d not found".formatted(id))
        );
        return GenreMapper.genreToGenreResponseDto(movie.getGenre());
    }
}

