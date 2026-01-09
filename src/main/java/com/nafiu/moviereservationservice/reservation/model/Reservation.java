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


    public Reservation(User user, ShowTime showTime) {
        this.user = user;
        this.showTime = showTime;
    }

    public Reservation() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ShowTime getShowTime() {
        return showTime;
    }

    public void setShowTime(ShowTime showTime) {
        this.showTime = showTime;
    }
}
