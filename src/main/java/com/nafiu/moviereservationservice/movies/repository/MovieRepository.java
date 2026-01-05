package com.nafiu.moviereservationservice.movies.repository;

import com.nafiu.moviereservationservice.movies.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> getMovieByGenre_Id(Integer genreId);
}