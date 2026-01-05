package com.nafiu.moviereservationservice.movies;


import com.nafiu.moviereservationservice.movies.dto.GenreResponseDto;
import com.nafiu.moviereservationservice.movies.dto.MovieCreateDto;
import com.nafiu.moviereservationservice.movies.dto.MovieResponseDto;
import com.nafiu.moviereservationservice.movies.dto.MovieUpdateDto;
import com.nafiu.moviereservationservice.movies.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{id}")
    public MovieResponseDto getMovie(@PathVariable("id") Integer id){
        return movieService.getOne(id);
    }

    @GetMapping("/{id}/genre")
    public GenreResponseDto getMovieGenre(@PathVariable("id") Integer id){
        return movieService.getMovieGenre(id);
    }

    @GetMapping("")
    public List<MovieResponseDto> getMovies(){
        return movieService.getAll();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public MovieResponseDto addMovie(@RequestBody @Valid MovieCreateDto movieDto){
        return this.movieService.createMovie(movieDto);
    }

    @PatchMapping("/{id}")
    public MovieResponseDto getMovie(@PathVariable("id") Integer id, @RequestBody @Valid MovieUpdateDto movieDto){
        return movieService.updateOne(id, movieDto);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable("id") Integer id){
        movieService.deleteOne(id);
    }
}
