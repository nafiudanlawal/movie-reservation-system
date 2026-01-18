package com.nafiu.moviereservationservice.movies;

import com.nafiu.moviereservationservice.movies.dto.ShowTimeCreateDto;
import com.nafiu.moviereservationservice.movies.dto.ShowTimeResponseDto;
import com.nafiu.moviereservationservice.movies.service.ShowTimeService;
import com.nafiu.moviereservationservice.reservation.dto.ReservationResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/show-times",produces = MediaType.APPLICATION_JSON_VALUE)
public class ShowTimeController {

    private final ShowTimeService service;

    @Autowired
    public ShowTimeController(ShowTimeService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public ShowTimeResponseDto createShowTime(@RequestBody @Valid ShowTimeCreateDto showTimeCreateDto) {
        return this.service.createShowTime(showTimeCreateDto);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
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
    @GetMapping("/{id}/reservations")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ReservationResponseDto>> getReservations(@PathVariable("id") Integer id){
        return this.service.getShowTimeReservations(id);
    }

}
