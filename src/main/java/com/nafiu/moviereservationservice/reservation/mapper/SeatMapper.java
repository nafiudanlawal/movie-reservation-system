package com.nafiu.moviereservationservice.reservation.mapper;

import com.nafiu.moviereservationservice.reservation.dto.SeatCreateDto;
import com.nafiu.moviereservationservice.reservation.dto.SeatResponseDto;
import com.nafiu.moviereservationservice.reservation.dto.SeatVenueResponseDto;
import com.nafiu.moviereservationservice.reservation.dto.VenueSeatCreateDto;
import com.nafiu.moviereservationservice.reservation.model.Seat;
import com.nafiu.moviereservationservice.reservation.model.Venue;

public class SeatMapper {
    public static Seat SeatFromSeatCreateDto(SeatCreateDto seatCreateDto, Venue venue) {
        return new Seat(
                seatCreateDto.label(),
                seatCreateDto.description(),
                venue
        );
    }

    public static Seat SeatFromVenueSeatCreateDto(VenueSeatCreateDto seatCreateDto, Venue venue) {
        return new Seat(
                seatCreateDto.label(),
                seatCreateDto.description(),
                venue
        );
    }

    public static SeatResponseDto SeatToSeatResponseDto(Seat seat){
        return new SeatResponseDto(
                seat.getId(),
                seat.getLabel(),
                seat.getDescription(),
                seat.getVenue()
        );
    }
}
