package com.nafiu.moviereservationservice.reservation.model;

import com.nafiu.moviereservationservice.auth.model.User;
import com.nafiu.moviereservationservice.movies.model.ShowTime;
import jakarta.persistence.*;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    User user;

    @ManyToOne
    ShowTime showTime;

    @ManyToOne
    Seat seat;
}
