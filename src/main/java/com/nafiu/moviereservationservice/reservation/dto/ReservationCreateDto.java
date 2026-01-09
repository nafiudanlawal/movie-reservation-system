package com.nafiu.moviereservationservice.reservation.dto;


import jakarta.validation.constraints.NotNull;

public record ReservationCreateDto(
        @NotNull
        Integer showTimeId
) {
}
