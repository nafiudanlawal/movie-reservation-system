package com.nafiu.moviereservationservice.reservation.model;


import com.nafiu.moviereservationservice.movies.model.Movie;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "show_times")
public class ShowTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    LocalDate date;

    LocalTime time;

    @ManyToOne
    Movie movie;

    @ManyToOne
    Venue venue;

    Double price;
}
