package com.nafiu.moviereservationservice.reservation.dto;

import com.nafiu.moviereservationservice.auth.model.User;
import com.nafiu.moviereservationservice.movies.model.ShowTime;

public record ReservationResponseDto(
        Integer id,

        User user,

        ShowTime showTime
) {
}
