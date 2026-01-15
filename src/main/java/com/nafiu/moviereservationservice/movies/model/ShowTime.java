package com.nafiu.moviereservationservice.movies.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nafiu.moviereservationservice.reservation.model.Reservation;
import com.nafiu.moviereservationservice.reservation.model.Venue;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "show_times")
public class ShowTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate date;

    @JsonFormat(pattern = "HH:mm")
    LocalTime time;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "movie_id")
    Movie movie;

    @ManyToOne
    @JoinColumn(name = "venue_id")
    Venue venue;

    @OneToMany(mappedBy = "showTime", fetch = FetchType.LAZY) @JsonBackReference @JsonIgnore
    List<Reservation> reservations;

    Double price;

    public ShowTime(LocalDate date, LocalTime time, Double price, Movie movie, Venue venue) {
        this.date = date;
        this.time = time;
        this.movie = movie;
        this.venue = venue;
        this.price = price;
    }

    public ShowTime() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShowTime showTime = (ShowTime) o;
        return Objects.equals(id, showTime.id)
                && Objects.equals(date, showTime.date)
                && Objects.equals(time, showTime.time)
                && Objects.equals(movie.getId(), showTime.movie.getId())
                && Objects.equals(venue.getId(), showTime.venue.getId())
                && Objects.equals(price, showTime.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, time, movie.getId(), venue.getId(), price);
    }
}
