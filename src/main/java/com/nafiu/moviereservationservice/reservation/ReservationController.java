package com.nafiu.moviereservationservice.reservation;

import com.nafiu.moviereservationservice.reservation.dto.ReservationCreateDto;
import com.nafiu.moviereservationservice.reservation.dto.ReservationResponseDto;
import com.nafiu.moviereservationservice.reservation.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService service;

    public ReservationController(ReservationService service) {
        this.service = service;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationResponseDto createReservation(@RequestBody ReservationCreateDto reservationDto){
        return this.service.createReservation(reservationDto);
    }

    @GetMapping
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
}
