package com.nafiu.moviereservationservice.reservation.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public record VenueResponseDto(
        Integer id,
        String name,

        String address,

        Integer capacity
) {
    public ResponseEntity<VenueResponseDto> toResponseEntity(){
        return ResponseEntity.ok().body(this);
    }
    public ResponseEntity<VenueResponseDto> toResponseEntity(HttpStatus status){
        return ResponseEntity.status(status).body(this);
    }
}
