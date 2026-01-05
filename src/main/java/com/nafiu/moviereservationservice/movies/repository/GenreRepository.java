package com.nafiu.moviereservationservice.movies.repository;

import com.nafiu.moviereservationservice.movies.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {

}
