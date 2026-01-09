package com.nafiu.moviereservationservice.reservation.mapper;

import com.nafiu.moviereservationservice.reservation.dto.ReservationResponseDto;
import com.nafiu.moviereservationservice.reservation.model.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {
    public ReservationResponseDto reservationToReservationResponseDto(Reservation reservation){
        return new ReservationResponseDto(
                reservation.getId(),
                reservation.getUser(),
                reservation.getShowTime()
        );
    }
}
