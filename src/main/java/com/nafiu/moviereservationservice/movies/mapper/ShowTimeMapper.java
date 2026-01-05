package com.nafiu.moviereservationservice.movies.mapper;

import com.nafiu.moviereservationservice.movies.dto.ShowTimeCreateDto;
import com.nafiu.moviereservationservice.movies.dto.ShowTimeMovieCreateDto;
import com.nafiu.moviereservationservice.movies.dto.ShowTimeResponseDto;
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
