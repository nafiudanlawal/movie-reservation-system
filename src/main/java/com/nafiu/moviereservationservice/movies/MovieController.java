package com.nafiu.moviereservationservice.movies;


import com.nafiu.moviereservationservice.movies.dto.*;
import com.nafiu.moviereservationservice.movies.service.MovieService;
import com.nafiu.moviereservationservice.movies.service.ShowTimeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/movies", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Movie")
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
    public ShowTimeMovieResponseDto getMovieShowTime(
            @PathVariable("movieId") Integer movieId,
            @PathVariable("showTimeId") Integer showTimeId
    ) {
        return showTimeService.getMovieShowTime(movieId, showTimeId);
    }

    @GetMapping("/{movieId}/show-times")
    public List<ShowTimeMovieResponseDto> getMovieShowTimes(
            @PathVariable("movieId") Integer movieId,
            @RequestParam(required = false) LocalDate date
            ) {
        return showTimeService.getMovieShowTimes(movieId, date);
    }

    @PostMapping("/{id}/show-times")
    @PreAuthorize("hasRole('ADMIN')")
    public ShowTimeMovieResponseDto getMovieShowTimes(
            @PathVariable("id") Integer movieId,
            @RequestBody @Valid ShowTimeMovieCreateDto showTimeCreateDto
    ) {
        return showTimeService.addMovieShowTime(movieId, showTimeCreateDto);
    }

    @PatchMapping("/{id}/show-times/{showTimeId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ShowTimeMovieResponseDto updateMovieShowTimes(
            @PathVariable("id") Integer movieId,
            @PathVariable("showTimeId") Integer showTimeId,
            @RequestBody @Valid ShowTimeMovieUpdateDto showTimeMovieUpdateDto
    ) {
        return showTimeService.updateMovieShowTime(movieId, showTimeId, showTimeMovieUpdateDto);
    }

    @GetMapping("")
    public List<MovieResponseDto> getMovies() {
        return movieService.getAll();
    }

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public MovieResponseDto addMovie(
            @ModelAttribute @Valid MovieCreateDto movieDto,
            @RequestPart("image") MultipartFile file
    ) throws Exception {
        return this.movieService.createMovie(movieDto, file);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public MovieResponseDto getMovie(@PathVariable("id") Integer id, @RequestBody @Valid MovieUpdateDto movieDto) {
        return movieService.updateOne(id, movieDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteMovie(@PathVariable("id") Integer id) {
        movieService.deleteOne(id);
    }
}
