package com.nafiu.moviereservationservice.reservation.exception;

public class FullCapacityException extends RuntimeException{
    public FullCapacityException() {
        super("Venue capacity full");
    }
}
