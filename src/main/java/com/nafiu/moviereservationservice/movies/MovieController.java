package com.nafiu.moviereservationservice.movies;


import com.nafiu.moviereservationservice.movies.dto.*;
import com.nafiu.moviereservationservice.movies.service.MovieService;
import com.nafiu.moviereservationservice.movies.service.ShowTimeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final ShowTimeService showTimeService;

    @Autowired
    public MovieController(MovieService movieService, ShowTimeService showTimeService) {
        this.movieService = movieService;
        this.showTimeService = showTimeService;
    }

    @GetMapping("/{id}")
    public MovieResponseDto getMovie(@PathVariable("id") Integer id) {
        return movieService.getOne(id);
    }

    @GetMapping("/{id}/genre")
    public GenreResponseDto getMovieGenre(@PathVariable("id") Integer id) {
        return movieService.getMovieGenre(id);
    }

    @GetMapping("/{movieId}/show-times/{showTimeId}")
    public ShowTimeResponseDto getMovieShowTime(
            @PathVariable("movieId") Integer movieId,
            @PathVariable("showTimeId") Integer showTimeId
    ) {
        return showTimeService.getMovieShowTime(movieId, showTimeId);
    }

    @GetMapping("/{id}/show-times")
    public List<ShowTimeResponseDto> getMovieShowTimes() {
        return showTimeService.getMovieShowTimes();
    }

    @PostMapping("/{id}/show-times")
    public ShowTimeResponseDto getMovieShowTimes(
            @PathVariable("id") Integer movieId,
            @RequestBody @Valid ShowTimeMovieCreateDto showTimeCreateDto
    ) {
        return showTimeService.addMovieShowTime(movieId, showTimeCreateDto);
    }

    @GetMapping("")
    public List<MovieResponseDto> getMovies() {
        return movieService.getAll();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public MovieResponseDto addMovie(@RequestBody @Valid MovieCreateDto movieDto) {
        return this.movieService.createMovie(movieDto);
    }

    @PatchMapping("/{id}")
    public MovieResponseDto getMovie(@PathVariable("id") Integer id, @RequestBody @Valid MovieUpdateDto movieDto) {
        return movieService.updateOne(id, movieDto);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable("id") Integer id) {
        movieService.deleteOne(id);
    }
}
