package com.nafiu.moviereservationservice.reservation;

import com.nafiu.moviereservationservice.exception.ApiErrorResponseDto;
import com.nafiu.moviereservationservice.exception.ApiErrorResponseDtoMapper;
import com.nafiu.moviereservationservice.reservation.dto.ReservationCreateDto;
import com.nafiu.moviereservationservice.reservation.dto.ReservationResponseDto;
import com.nafiu.moviereservationservice.reservation.exception.FullCapacityException;
import com.nafiu.moviereservationservice.reservation.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ReservationResponseDto> createReservation(@RequestBody @Valid ReservationCreateDto reservationDto){
        return this.service.createReservation(reservationDto);
    }

    @PutMapping("/{id}/cancel")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ReservationResponseDto> cancelReservation(@PathVariable("id") Integer id ) throws Exception {
        return this.service.cancelReservation(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ReservationResponseDto>> getReservations(){
        return this.service.getReservations();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDto> getReservation(
            @PathVariable("id")
            Integer id
    ){
        return this.service.getReservation(id);
    }
    @ExceptionHandler(FullCapacityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponseDto> handleGeneralException(FullCapacityException ex) {
        return ApiErrorResponseDtoMapper.createErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
