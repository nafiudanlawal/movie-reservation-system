package com.nafiu.moviereservationservice.reservation.service;


import com.nafiu.moviereservationservice.reservation.dto.VenueCreateDto;
import com.nafiu.moviereservationservice.reservation.dto.VenueResponseDto;
import com.nafiu.moviereservationservice.reservation.dto.VenueUpdateDto;
import com.nafiu.moviereservationservice.reservation.mapper.VenueMapper;
import com.nafiu.moviereservationservice.reservation.model.Venue;
import com.nafiu.moviereservationservice.reservation.repository.VenueRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueService {
    private final VenueRepository venueRepository;

    public VenueService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    public VenueResponseDto createVenue(VenueCreateDto venueDto) {
        Venue venue = VenueMapper.VenueFromVenueCreateDto(venueDto);
        this.venueRepository.save(venue);
        return VenueMapper.VenueToVenueResponseDto(venue);
    }

    public List<VenueResponseDto> getVenues() {
        return this.venueRepository
                .findAll()
                .stream()
                .map(VenueMapper::VenueToVenueResponseDto)
                .toList();
    }

    public VenueResponseDto getVenue(Integer id) throws EntityNotFoundException {
        Venue venue = this.venueRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Venue with id %d not found".formatted(id))
        );
        return VenueMapper.VenueToVenueResponseDto(venue);
    }

    public VenueResponseDto updateVenue(Integer id, VenueUpdateDto venueDto) throws EntityNotFoundException {
        Venue venue = this.venueRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Venue with id %d not found".formatted(id))
        );
        if (venueDto.name() != null) {
            venue.setName(venueDto.name());
        }
        if (venueDto.address() != null) {
            venue.setAddress(venueDto.address());
        }
        this.venueRepository.save(venue);
        return VenueMapper.VenueToVenueResponseDto(venue);
    }

    public void deleteVenue(Integer id) {
        this.venueRepository.deleteById(id);
    }
}
