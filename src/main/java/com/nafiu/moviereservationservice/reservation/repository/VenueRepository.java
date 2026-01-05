package com.nafiu.moviereservationservice.reservation.repository;

import com.nafiu.moviereservationservice.reservation.model.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueRepository extends JpaRepository<Venue, Integer> {
}
