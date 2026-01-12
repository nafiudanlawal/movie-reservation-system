package com.nafiu.moviereservationservice.reservation.service;


import com.nafiu.moviereservationservice.auth.model.UserPrincipal;
import com.nafiu.moviereservationservice.movies.model.ShowTime;
import com.nafiu.moviereservationservice.movies.repository.ShowTimeRepository;
import com.nafiu.moviereservationservice.reservation.dto.ReservationCreateDto;
import com.nafiu.moviereservationservice.reservation.dto.ReservationResponseDto;
import com.nafiu.moviereservationservice.reservation.exception.FullCapacityException;
import com.nafiu.moviereservationservice.reservation.mapper.ReservationMapper;
import com.nafiu.moviereservationservice.reservation.model.Reservation;
import com.nafiu.moviereservationservice.reservation.repository.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    private final ReservationMapper reservationMapper;
    private final ShowTimeRepository showTimeRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationMapper reservationMapper, ShowTimeRepository showTimeRepository, ReservationRepository reservationRepository) {
        this.reservationMapper = reservationMapper;
        this.showTimeRepository = showTimeRepository;
        this.reservationRepository = reservationRepository;
    }

    @Transactional
    public ReservationResponseDto createReservation(ReservationCreateDto reservationDto)
            throws FullCapacityException, EntityNotFoundException {
        // validate showtime
        ShowTime showTime = this.showTimeRepository.findById(reservationDto.showTimeId()).orElseThrow(() ->
                new EntityNotFoundException("ShowTime with id %d not found".formatted(reservationDto.showTimeId()))
        );

        Integer reservationCount = this.reservationRepository.countByShowTimeId(showTime.getId());
        // check if venue capacity is not full for showtime
        if (reservationCount >= showTime.getVenue().getCapacity()) {
            throw new FullCapacityException();
        }

        // get user from security context
        SecurityContext securityContext = SecurityContextHolder.getContext();

        UserPrincipal userPrincipal = (UserPrincipal) securityContext.getAuthentication().getPrincipal();

        Reservation reservation = new Reservation();
        reservation.setUser(userPrincipal.user());
        reservation.setShowTime(showTime);

        // add new reservation
        this.reservationRepository.save(reservation);

        return reservationMapper.reservationToReservationResponseDto(reservation);
    }

    public List<ReservationResponseDto> getReservations() {
        return this.reservationRepository
                .findAll()
                .stream()
                .map(reservationMapper::reservationToReservationResponseDto)
                .toList();
    }

    public ReservationResponseDto getReservation(Integer id) {
        Reservation reservation = this.reservationRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("reservation with Id: %d not found".formatted(id))
        );
        return reservationMapper.reservationToReservationResponseDto(reservation);
    }

    public ReservationResponseDto updateReservation(Integer id) {
        // TODO
        return null;
    }
}
