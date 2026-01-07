package com.nafiu.moviereservationservice.movies;

import com.nafiu.moviereservationservice.movies.dto.ShowTimeCreateDto;
import com.nafiu.moviereservationservice.movies.dto.ShowTimeResponseDto;
import com.nafiu.moviereservationservice.movies.service.ShowTimeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/show-times")
public class ShowTimeController {

    private final ShowTimeService service;

    @Autowired
    public ShowTimeController(ShowTimeService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShowTimeResponseDto createShowTime(@RequestBody @Valid ShowTimeCreateDto showTimeCreateDto) {
        return this.service.createShowTime(showTimeCreateDto);
    }


    @GetMapping
    public List<ShowTimeResponseDto> getShowTimes(
            @RequestParam(required = false) LocalDate date
    ) {
        return this.service.getShowTimes(date);
    }

    @GetMapping("/{id}")
    public ShowTimeResponseDto getShowTime(
            @PathVariable("id")
            Integer id
    ) {
        return this.service.getShowTime(id);
    }
}
