package com.nafiu.moviereservationservice.exceptions;

import java.util.Date;

public record ApiErrorResponseDto(String message, Date date) { }

