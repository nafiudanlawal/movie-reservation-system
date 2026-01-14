package com.nafiu.moviereservationservice.reservation.dto;

import com.nafiu.moviereservationservice.auth.model.User;
import com.nafiu.moviereservationservice.movies.model.ShowTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public record ReservationResponseDto(
        Integer id,

        User user,

        ShowTime showTime
) {
    public ResponseEntity<ReservationResponseDto> toResponseEntity(){
        return ResponseEntity.ok().body(this);
    }
    public ResponseEntity<ReservationResponseDto> toResponseEntity(HttpStatus status){
        return ResponseEntity.status(status).body(this);
    }
}
