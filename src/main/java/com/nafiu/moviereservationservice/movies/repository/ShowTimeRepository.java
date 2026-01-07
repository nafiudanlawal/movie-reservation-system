package com.nafiu.moviereservationservice.movies.repository;

import com.nafiu.moviereservationservice.movies.model.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShowTimeRepository extends JpaRepository<ShowTime, Integer> {
    Optional<ShowTime> findByMovieIdAndId(Integer movieId, Integer showTimeId);

    List<ShowTime> findByMovieId(Integer movieId);

    List<ShowTime> findByMovieIdAndDate(Integer movieId, LocalDate date);

    List<ShowTime> findByDate(LocalDate date);
}
