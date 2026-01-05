package com.nafiu.moviereservationservice.reservation.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "venues")
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;

    String address;

    @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL)
    List<Seat> seats;

    public Venue() {}

    public Venue(String name) {
        this.name = name;
        this.address = "";
    }
    public Venue(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Venue(String name, String address, List<Seat> seats) {
        this.name = name;
        this.address = address;
        this.seats = seats;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
