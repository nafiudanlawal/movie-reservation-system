package com.nafiu.moviereservationservice.reservation.dto;


import jakarta.validation.constraints.NotNull;

public record ReservationCreateDto(
        @NotNull
        Integer userId,
        @NotNull
        Integer showTime,
        @NotNull Integer seat
) {
}
