package com.nafiu.moviereservationservice.reservation.service;


import com.nafiu.moviereservationservice.reservation.dto.*;
import com.nafiu.moviereservationservice.reservation.mapper.SeatMapper;
import com.nafiu.moviereservationservice.reservation.mapper.VenueMapper;
import com.nafiu.moviereservationservice.reservation.model.Seat;
import com.nafiu.moviereservationservice.reservation.model.Venue;
import com.nafiu.moviereservationservice.reservation.repository.SeatRepository;
import com.nafiu.moviereservationservice.reservation.repository.VenueRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueService {
    private final VenueRepository venueRepository;
    private final SeatRepository seatRepository;

    public VenueService(VenueRepository venueRepository, SeatRepository seatRepository) {
        this.venueRepository = venueRepository;
        this.seatRepository = seatRepository;
    }

    public VenueResponseDto createVenue(VenueCreateDto venueDto) {
        Venue venue = VenueMapper.VenueFromVenueCreateDto(venueDto);
        this.venueRepository.save(venue);
        return VenueMapper.VenueToVenueResponseDto(venue);
    }

    public SeatVenueResponseDto addSeatToVenue(Integer venueId, VenueSeatCreateDto venueSeatCreateDto) throws EntityNotFoundException {
        var venueOptional = this.venueRepository.findById(venueId);
        if (venueOptional.isEmpty()) {
            throw new EntityNotFoundException("Venue with id %d not found".formatted(venueId));
        }
        Venue venue = venueOptional.get();
        Seat seat = SeatMapper.SeatFromVenueSeatCreateDto(venueSeatCreateDto, venue);
        this.seatRepository.save(seat);
        return SeatMapper.SeatToSeatVenueResponseDto(seat);
    }

    public List<VenueResponseDto> getVenues() {
        return this.venueRepository
                .findAll()
                .stream()
                .map(VenueMapper::VenueToVenueResponseDto)
                .toList();
    }

    public VenueResponseDto getVenue(Integer id) throws EntityNotFoundException {
        var venueOptional = this.venueRepository.findById(id);
        if (venueOptional.isEmpty()) {
            throw new EntityNotFoundException("Venue with id %d not found".formatted(id));
        }
        return VenueMapper.VenueToVenueResponseDto(venueOptional.get());
    }

    public VenueResponseDto updateVenue(Integer id, VenueUpdateDto venueDto) throws EntityNotFoundException {
        var venueOptional = this.venueRepository.findById(id);
        if (venueOptional.isEmpty()) {
            throw new EntityNotFoundException("Venue with id %d not found".formatted(id));
        }
        Venue venue = venueOptional.get();
        if (venueDto.name() != null) {
            venue.setName(venueDto.name());
        }
        if (venueDto.address() != null) {
            venue.setAddress(venueDto.address());
        }
        this.venueRepository.save(venue);
        return VenueMapper.VenueToVenueResponseDto(venue);
    }

    public List<SeatVenueResponseDto> getVenueSeats(Integer venueId) throws EntityNotFoundException {
        var venueOptional = this.venueRepository.findById(venueId);
        if (venueOptional.isEmpty()) {
            throw new EntityNotFoundException("Venue with id %d not found".formatted(venueId));
        }
        return venueOptional.get()
                .getSeats()
                .stream()
                .map(SeatMapper::SeatToSeatVenueResponseDto)
                .toList();
    }

    public void deleteVenue(Integer id) {
        this.venueRepository.deleteById(id);
    }

    public void deleteVenueSeat(Integer venueId, Integer seatId) {
        this.seatRepository.deleteByVenueIdAndId(venueId, seatId);
    }

    public SeatVenueResponseDto getVenueSeat(Integer venueId, Integer seatId)
            throws EntityNotFoundException {
        var seatOptional = this.seatRepository.findByVenueIdAndId(venueId, seatId);
        if (seatOptional.isEmpty()) {
            throw new EntityNotFoundException("Seat with id %d and venue %d not found".formatted(seatId, venueId));
        }
        return SeatMapper.SeatToSeatVenueResponseDto(seatOptional.get());
    }

    public SeatVenueResponseDto updateVenueSeat(Integer venueId, Integer seatId, SeatVenueUpdateDto seatVenueUpdateDto)
            throws EntityNotFoundException {
        var seatOptional = this.seatRepository.findByVenueIdAndId(venueId, seatId);
        if (seatOptional.isEmpty()) {
            throw new EntityNotFoundException("Seat with id %d and venue %d not found".formatted(seatId, venueId));
        }
        Seat seat = seatOptional.get();
        if (seatVenueUpdateDto.label() != null) {
            seat.setLabel(seatVenueUpdateDto.label());
        }
        if (seatVenueUpdateDto.description() != null) {
            seat.setDescription(seatVenueUpdateDto.description());
        }
        this.seatRepository.save(seat);
        return SeatMapper.SeatToSeatVenueResponseDto(seat);
    }
}
