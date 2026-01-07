package com.nafiu.moviereservationservice.movies.mapper;

import com.nafiu.moviereservationservice.movies.dto.*;
import com.nafiu.moviereservationservice.movies.model.Movie;
import com.nafiu.moviereservationservice.movies.model.ShowTime;
import com.nafiu.moviereservationservice.reservation.model.Venue;

public class ShowTimeMapper {
    public static ShowTimeResponseDto showTimeToShowTimeResponseDto(ShowTime showTime) {
        return new ShowTimeResponseDto(
                showTime.getId(),
                showTime.getDate(),
                showTime.getTime(),
                showTime.getMovie(),
                showTime.getVenue(),
                showTime.getPrice()
        );
    }

    public static ShowTimeMovieResponseDto showTimeToShowTimeMovieResponseDto(ShowTime showTime) {
        return new ShowTimeMovieResponseDto(
                showTime.getId(),
                showTime.getDate(),
                showTime.getTime(),
                showTime.getPrice(),
                showTime.getVenue()
        );
    }
    public static ShowTimeMovieResponseDto showTimeResponseDtoToShowTimeMovieResponseDto(
            ShowTimeResponseDto showTimeResponseDto
    ) {
        return new ShowTimeMovieResponseDto(
                showTimeResponseDto.id(),
                showTimeResponseDto.date(),
                showTimeResponseDto.time(),
                showTimeResponseDto.price(),
                showTimeResponseDto.venue()
        );
    }
    public static ShowTimeCreateDto showTimeMovieCreateDtoToShowTimeCreateDto(
            ShowTimeMovieCreateDto showTimeResponseDto,
            Integer movieId
    ) {
        return new ShowTimeCreateDto(
                showTimeResponseDto.date(),
                showTimeResponseDto.time(),
                showTimeResponseDto.venueId(),
                movieId,
                showTimeResponseDto.price()
        );
    }

    public static ShowTimeUpdateDto showTimeMovieUpdateDtoToShowTimeUpdateDto(
            ShowTimeMovieUpdateDto showTimeUpdateDto,
            Integer movieId
    ) {
        return new ShowTimeUpdateDto(
                showTimeUpdateDto.date(),
                showTimeUpdateDto.time(),
                movieId,
                showTimeUpdateDto.venueId(),
                showTimeUpdateDto.price()
        );
    }

    public static ShowTime showTimeFromShowTimeCreateDto(
            ShowTimeCreateDto showTimeCreateDto,
            Movie movie,
            Venue venue
    ) {
        return new ShowTime(
                showTimeCreateDto.date(),
                showTimeCreateDto.time(),
                showTimeCreateDto.price(),
                movie,
                venue
        );
    }

    public static ShowTime showTimeFromShowTimeCreateDto(
            ShowTimeMovieCreateDto showTimeCreateDto,
            Movie movie,
            Venue venue
    ) {
        return new ShowTime(
                showTimeCreateDto.date(),
                showTimeCreateDto.time(),
                showTimeCreateDto.price(),
                movie,
                venue
        );
    }
}
