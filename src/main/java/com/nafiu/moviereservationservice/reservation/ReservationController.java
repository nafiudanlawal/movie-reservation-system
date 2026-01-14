package com.nafiu.moviereservationservice.reservation;

import com.nafiu.moviereservationservice.exceptions.ApiErrorResponseDto;
import com.nafiu.moviereservationservice.reservation.dto.ReservationCreateDto;
import com.nafiu.moviereservationservice.reservation.dto.ReservationResponseDto;
import com.nafiu.moviereservationservice.reservation.exception.FullCapacityException;
import com.nafiu.moviereservationservice.reservation.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationController {
    private final ReservationService service;

    @Autowired
    public ReservationController(ReservationService service) {
        this.service = service;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('USER')")
    public ReservationResponseDto createReservation(@RequestBody @Valid ReservationCreateDto reservationDto){
        return this.service.createReservation(reservationDto);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<ReservationResponseDto> getReservations(){
        return this.service.getReservations();
    }
    @GetMapping("/{id}")
    public ReservationResponseDto getReservation(
            @PathVariable("id")
            Integer id
    ){
        return this.service.getReservation(id);
    }
    @ExceptionHandler(FullCapacityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponseDto handleGeneralException(FullCapacityException ex) {
        return new ApiErrorResponseDto(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                new Date()
        );
    }

}
