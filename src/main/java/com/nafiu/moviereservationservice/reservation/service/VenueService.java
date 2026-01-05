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

    public VenueResponseDto createVenue(VenueCreateDto venueDto){
        Venue venue = VenueMapper.VenueFromVenueCreateDto(venueDto);
        this.venueRepository.save(venue);
        return VenueMapper.VenueToVenueResponseDto(venue);
    }

    public SeatResponseDto addSeatToVenue(Integer venueId, SeatCreateDto seatCreateDto){
        var venueOptional = this.venueRepository.findById(venueId);
        if(venueOptional.isEmpty()){
            throw new EntityNotFoundException("Venue with id %d not found".formatted(venueId));
        }
        Venue venue = venueOptional.get();
        Seat seat = SeatMapper.SeatFromSeatCreateDto(seatCreateDto, venue);
        this.seatRepository.save(seat);
        return SeatMapper.SeatToSeatResponseDto(seat);
    }

    public List<VenueResponseDto> getVenues(){
        return this.venueRepository
                .findAll()
                .stream()
                .map(VenueMapper::VenueToVenueResponseDto)
                .toList();
    }

    public VenueResponseDto getVenue(Integer id){
        var venueOptional = this.venueRepository.findById(id);
        if(venueOptional.isEmpty()){
            throw new EntityNotFoundException("Venue with id %d not found".formatted(id));
        }
        return VenueMapper.VenueToVenueResponseDto(venueOptional.get());
    }

    public VenueResponseDto updateVenue(Integer id, VenueUpdateDto venueDto){
        var venueOptional = this.venueRepository.findById(id);
        if(venueOptional.isEmpty()){
            throw new EntityNotFoundException("Venue with id %d not found".formatted(id));
        }
        Venue venue = venueOptional.get();
        if(venueDto.name() != null){
            venue.setName(venueDto.name());
        }
        if(venueDto.address() != null){
            venue.setAddress(venueDto.address());
        }
        this.venueRepository.save(venue);
        return VenueMapper.VenueToVenueResponseDto(venue);
    }

    public List<SeatResponseDto> getVenueSeats(Integer venueId) {
        var venueOptional = this.venueRepository.findById(venueId);
        if(venueOptional.isEmpty()){
            throw new EntityNotFoundException("Venue with id %d not found".formatted(venueId));
        }
        return venueOptional.get()
                .getSeats()
                .stream()
                .map(SeatMapper::SeatToSeatResponseDto)
                .toList();
    }
}
