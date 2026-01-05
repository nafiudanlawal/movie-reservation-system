package com.nafiu.moviereservationservice.movies.repository;

import com.nafiu.moviereservationservice.movies.dto.ShowTimeResponseDto;
import com.nafiu.moviereservationservice.movies.model.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowTimeRepository extends JpaRepository<ShowTime, Integer> {
    Optional<ShowTime> findByMovieIdAndId(Integer movieId, Integer showTimeId);
}
