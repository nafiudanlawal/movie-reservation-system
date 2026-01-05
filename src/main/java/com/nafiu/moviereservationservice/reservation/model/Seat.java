package com.nafiu.moviereservationservice.reservation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String description;
    String label;
    @ManyToOne
    @JsonBackReference
    Venue venue;

    public Seat() {}
    public Seat( String label, String description, Venue venue) {
        this.description = description;
        this.label = label;
        this.venue = venue;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return Objects.equals(id, seat.id) && Objects.equals(description, seat.description) && Objects.equals(label, seat.label) && Objects.equals(venue, seat.venue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, label, venue);
    }
}
