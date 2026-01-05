package com.nafiu.moviereservationservice.reservation.repository;

import com.nafiu.moviereservationservice.reservation.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
}
