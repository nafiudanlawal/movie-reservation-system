package com.nafiu.moviereservationservice.movies.service;


import com.nafiu.moviereservationservice.movies.dto.ShowTimeCreateDto;
import com.nafiu.moviereservationservice.movies.dto.ShowTimeMovieCreateDto;
import com.nafiu.moviereservationservice.movies.dto.ShowTimeResponseDto;
import com.nafiu.moviereservationservice.movies.mapper.ShowTimeMapper;
import com.nafiu.moviereservationservice.movies.model.ShowTime;
import com.nafiu.moviereservationservice.movies.repository.ShowTimeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowTimeService {

    private final ShowTimeRepository showTimeRepository;

    @Autowired
    public ShowTimeService(ShowTimeRepository showTimeRepository) {
        this.showTimeRepository = showTimeRepository;
    }

    public ShowTimeResponseDto createShowTime(ShowTimeCreateDto ShowTimeDto) {
        return null;
    }

    public List<ShowTimeResponseDto> getShowTimes() {
        return null;
    }

    public ShowTimeResponseDto getShowTime(Integer id) {
        return null;
    }

    public ShowTimeResponseDto updateShowTime(Integer id) {
        return null;
    }

    public ShowTimeResponseDto getMovieShowTime(Integer movieId, Integer showTimeId) {
        var showTimeOptional = this.showTimeRepository.findByMovieIdAndId(movieId, showTimeId);
        if (showTimeOptional.isEmpty()) {
            throw new EntityNotFoundException(
                    "Showtime with id: %s and movie id %s not found".formatted(showTimeId, movieId)
            );
        }
        return ShowTimeMapper.showTimeToShowTimeResponseDto(showTimeOptional.get());
    }

    public List<ShowTimeResponseDto> getMovieShowTimes() {
        return this.showTimeRepository
                .findAll()
                .stream()
                .map(ShowTimeMapper::showTimeToShowTimeResponseDto)
                .toList();
    }

    public ShowTimeResponseDto addMovieShowTime(Integer movieId, ShowTimeMovieCreateDto showTimeMovieCreateDto) {

        ShowTime showTime = ShowTimeMapper.showTimeFromShowTimeCreateDto(showTimeMovieCreateDto);
        return null;
    }
}
