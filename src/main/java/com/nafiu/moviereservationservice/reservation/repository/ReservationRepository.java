package com.nafiu.moviereservationservice.reservation.repository;

import com.nafiu.moviereservationservice.reservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
