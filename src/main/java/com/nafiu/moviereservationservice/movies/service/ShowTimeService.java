package com.nafiu.moviereservationservice.movies.service;

import com.nafiu.moviereservationservice.movies.dto.*;
import com.nafiu.moviereservationservice.movies.mapper.ShowTimeMapper;
import com.nafiu.moviereservationservice.movies.model.Movie;
import com.nafiu.moviereservationservice.movies.model.ShowTime;
import com.nafiu.moviereservationservice.movies.repository.MovieRepository;
import com.nafiu.moviereservationservice.movies.repository.ShowTimeRepository;
import com.nafiu.moviereservationservice.reservation.model.Venue;
import com.nafiu.moviereservationservice.reservation.repository.VenueRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.TransactionScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ShowTimeService {

    private final ShowTimeRepository showTimeRepository;
    private final MovieRepository movieRepository;
    private final VenueRepository venueRepository;

    @Autowired
    public ShowTimeService(ShowTimeRepository showTimeRepository, MovieRepository movieRepository, VenueRepository venueRepository) {
        this.showTimeRepository = showTimeRepository;
        this.movieRepository = movieRepository;
        this.venueRepository = venueRepository;
    }

    @TransactionScoped
    public ShowTimeResponseDto createShowTime(ShowTimeCreateDto ShowTimeDto) {
        Venue venue = this.venueRepository.findById(ShowTimeDto.venueId()).orElseThrow(() ->
                new EntityNotFoundException("Venue with id: %s not found".formatted(ShowTimeDto.venueId()))
        );
        Movie movie = this.movieRepository.findById(ShowTimeDto.movieId()).orElseThrow(() ->
                new EntityNotFoundException("Movie with id: %s not found".formatted(ShowTimeDto.movieId())
                ));
        ShowTime showTime = ShowTimeMapper.showTimeFromShowTimeCreateDto(
                ShowTimeDto,
                movie,
                venue
        );
        this.showTimeRepository.save(showTime);
        return ShowTimeMapper.showTimeToShowTimeResponseDto(showTime);
    }

    public List<ShowTimeResponseDto> getShowTimes(LocalDate date) {
        List<ShowTime> showTimes;
        if (date == null) {
            showTimes = this.showTimeRepository.findAll();
        } else {
            showTimes = this.showTimeRepository.findByDate(date);
        }

        return showTimes.stream()
                .map(ShowTimeMapper::showTimeToShowTimeResponseDto)
                .toList();
    }


    public ShowTimeResponseDto getShowTime(Integer showTimeId) {
        ShowTime showTime = this.showTimeRepository.findById(showTimeId).orElseThrow(() ->
                new EntityNotFoundException("Showtime with id: %s not found".formatted(showTimeId))
        );
        return ShowTimeMapper.showTimeToShowTimeResponseDto(showTime);
    }

    @TransactionScoped
    public ShowTimeResponseDto updateShowTime(Integer showTimeId, ShowTimeUpdateDto showTimeUpdateDto) {
        ShowTime showTime = this.showTimeRepository.findById(showTimeId).orElseThrow(() ->
                new EntityNotFoundException("Showtime with id: %s not found".formatted(showTimeId))
        );

        if (showTimeUpdateDto.time() != null) {
            showTime.setTime(showTimeUpdateDto.time());
        }
        if (showTimeUpdateDto.date() != null) {
            showTime.setDate(showTimeUpdateDto.date());
        }
        if (showTimeUpdateDto.price() != null) {
            showTime.setPrice(showTimeUpdateDto.price());
        }
        if (showTimeUpdateDto.venueId() != null) {
            Venue venue = this.venueRepository.findById(showTimeUpdateDto.venueId()).orElseThrow(() ->
                    new EntityNotFoundException("Venue with id: %s not found".formatted(showTimeUpdateDto.venueId()))
            );
            showTime.setVenue(venue);
        }

        if (showTimeUpdateDto.movieId() != null) {
            Movie movie = this.movieRepository.findById(showTimeUpdateDto.movieId()).orElseThrow(() ->
                    new EntityNotFoundException("Movie with id: %s not found".formatted(showTimeUpdateDto.movieId()))
            );
            showTime.setMovie(movie);
        }

        this.showTimeRepository.save(showTime);
        return ShowTimeMapper.showTimeToShowTimeResponseDto(showTime);
    }

    public ShowTimeMovieResponseDto getMovieShowTime(Integer movieId, Integer showTimeId) throws EntityNotFoundException {
        ShowTime showTime = this.showTimeRepository.findByMovieIdAndId(movieId, showTimeId).orElseThrow(() ->
                new EntityNotFoundException(
                        "Showtime with id: %s and movie id %s not found".formatted(showTimeId, movieId))
        );
        return ShowTimeMapper.showTimeToShowTimeMovieResponseDto(showTime);
    }

    public List<ShowTimeMovieResponseDto> getMovieShowTimes(Integer movieId, LocalDate date) {
        List<ShowTime> showTimes;
        if (date == null) {
            showTimes = this.showTimeRepository.findByMovieId(movieId);
        } else {
            showTimes = this.showTimeRepository.findByMovieIdAndDate(movieId, date);
        }

        return showTimes.stream()
                .map(ShowTimeMapper::showTimeToShowTimeMovieResponseDto)
                .toList();
    }

    public ShowTimeMovieResponseDto addMovieShowTime(
            Integer movieId,
            ShowTimeMovieCreateDto showTimeMovieCreateDto
    ) throws EntityNotFoundException {
        ShowTimeCreateDto showTimeCreateDto = ShowTimeMapper.showTimeMovieCreateDtoToShowTimeCreateDto(showTimeMovieCreateDto, movieId);
        ShowTimeResponseDto showTimeResponseDto = this.createShowTime(showTimeCreateDto);
        return ShowTimeMapper.showTimeResponseDtoToShowTimeMovieResponseDto(showTimeResponseDto);
    }

    public ShowTimeMovieResponseDto updateMovieShowTime(
            Integer movieId,
            Integer showTimeId,
            ShowTimeMovieUpdateDto showTimeMovieUpdateDto
    ) throws EntityNotFoundException {
        ShowTimeUpdateDto showTimeUpdateDto = ShowTimeMapper.showTimeMovieUpdateDtoToShowTimeUpdateDto(
                showTimeMovieUpdateDto,
                movieId
        );
        ShowTimeResponseDto showTimeResponseDto = this.updateShowTime(showTimeId, showTimeUpdateDto);
        return ShowTimeMapper.showTimeResponseDtoToShowTimeMovieResponseDto(showTimeResponseDto);
    }
}
