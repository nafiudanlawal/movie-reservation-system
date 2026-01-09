package com.nafiu.moviereservationservice.reservation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    Integer capacity = 0;

    @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL)
    @JsonBackReference
    List<Seat> seats;

    public Venue() {}

    public Venue(String name) {
        this.name = name;
        this.address = "";
        this.capacity = 0;
    }
    public Venue(String name, String address, Integer capacity) {
        this.name = name;
        this.address = address;
        this.capacity = capacity;
    }

    public Venue(String name, String address, List<Seat> seats) {
        this.name = name;
        this.address = address;
        this.seats = seats;
        this.capacity = 0;
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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
