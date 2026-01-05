package com.nafiu.moviereservationservice.reservation.repository;

import com.nafiu.moviereservationservice.reservation.model.Seat;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
    @Transactional
    void deleteByVenueIdAndId(Integer venueId, Integer seatId);

    Optional<Seat> findByVenueIdAndId(Integer venueId, Integer seatId);
}
