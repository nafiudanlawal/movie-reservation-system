package com.nafiu.moviereservationservice.movies.service;

import com.nafiu.moviereservationservice.movies.dto.GenreResponseDto;
import com.nafiu.moviereservationservice.movies.dto.MovieCreateDto;
import com.nafiu.moviereservationservice.movies.dto.MovieUpdateDto;
import com.nafiu.moviereservationservice.movies.mapper.GenreMapper;
import com.nafiu.moviereservationservice.movies.mapper.MovieMapper;
import com.nafiu.moviereservationservice.movies.dto.MovieResponseDto;
import com.nafiu.moviereservationservice.movies.model.Movie;
import com.nafiu.moviereservationservice.movies.repository.GenreRepository;
import com.nafiu.moviereservationservice.movies.repository.MovieRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    public MovieService(MovieRepository movieRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
    }

    public List<MovieResponseDto> getAll() {
        return movieRepository.findAll().stream().map(MovieMapper::movieToMovieResponseDto).toList();
    }


    public MovieResponseDto getOne(Integer id) {
        var result = movieRepository.findById(id);
        if(result.isEmpty()){
            throw new EntityNotFoundException("Movie with id %d not found".formatted(id));
        }
        return MovieMapper.movieToMovieResponseDto(result.get());
    }

    public void deleteOne(Integer id) {
        movieRepository.deleteById(id);
    }

    public MovieResponseDto createMovie(MovieCreateDto movieDto) {
        var result = this.genreRepository.findById(movieDto.genreId());
        if(result.isEmpty()){
            throw new EntityNotFoundException("Genre with id %d not found".formatted(movieDto.genreId()));
        }
        String posterImage = "n/a"; // generate image poster
        Movie movie = MovieMapper.movieCreateDtoToMovie(movieDto, result.get(), posterImage);
        this.movieRepository.save(movie);
        return MovieMapper.movieToMovieResponseDto(movie);
    }

    public MovieResponseDto updateOne(Integer id, MovieUpdateDto movieDto) {
        var movieOptional = movieRepository.findById(id);
        if(movieOptional.isEmpty()){
            throw new EntityNotFoundException("Movie with id %d not found".formatted(id));
        }
        Movie movie = movieOptional.get();
        if(movieDto.title() != null){
            movie.setTitle(movieDto.title());
        }
        if(movieDto.description() != null){
            movie.setDescription(movieDto.description());
        }
        if(movieDto.year() != null){
            movie.setYear(movieDto.year());
        }

        if(movieDto.genreId() != null){
            var genreOptional = this.genreRepository.findById(movieDto.genreId());
            if(genreOptional.isEmpty()){
                throw new EntityNotFoundException("Genre with id %d not found".formatted(movieDto.genreId()));
            }
            movie.setGenre(genreOptional.get());
        }
        this.movieRepository.save(movie);
        return MovieMapper.movieToMovieResponseDto(movie);
    }

    public GenreResponseDto getMovieGenre(Integer id) {
        var movieOptional = movieRepository.findById(id);
        if(movieOptional.isEmpty()){
            throw new EntityNotFoundException("Movie with id %d not found".formatted(id));
        }
        return GenreMapper.genreToGenreResponseDto(movieOptional.get().getGenre());
    }
}

