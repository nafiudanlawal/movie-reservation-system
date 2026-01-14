package com.nafiu.moviereservationservice.exception;

import java.util.Date;

public record ApiErrorResponseDto(String message, Date date) { }

