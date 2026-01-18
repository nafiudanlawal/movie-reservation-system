package com.nafiu.moviereservationservice.movies;

import com.nafiu.moviereservationservice.movies.dto.GenreCreateDto;
import com.nafiu.moviereservationservice.movies.dto.GenreResponseDto;
import com.nafiu.moviereservationservice.movies.dto.GenreUpdateDto;
import com.nafiu.moviereservationservice.movies.dto.MovieResponseDto;
import com.nafiu.moviereservationservice.movies.service.GenreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/genres", produces = MediaType.APPLICATION_JSON_VALUE)
@PreAuthorize(value = "hasRole('ADMIN')")
@Tag(name = "Genre")
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("")
    public List<GenreResponseDto> getGenres(){
        return this.genreService.getAll();
    }

    @GetMapping("/{id}")
    public GenreResponseDto getGenre(@PathVariable("id") Integer id){
        return this.genreService.getOne(id);
    }

    @GetMapping("/{id}/movies")
    public List<MovieResponseDto> getGenreMovies(@PathVariable("id") Integer id){
        return this.genreService.getGenreMovies(id);
    }
    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable("id") Integer id){
        this.genreService.deleteOne(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public GenreResponseDto addGenre(@RequestBody @Valid GenreCreateDto genreCreateDto){
        return this.genreService.createGenre(genreCreateDto);
    }

    @PatchMapping("/{id}")
    public GenreResponseDto addGenre(@PathVariable("id") Integer id, @RequestBody @Valid GenreUpdateDto genreUpdateDto){
        return this.genreService.updateGenre(id, genreUpdateDto);
    }
}
