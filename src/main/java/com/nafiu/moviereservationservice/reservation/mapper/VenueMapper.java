package com.nafiu.moviereservationservice.reservation.mapper;

import com.nafiu.moviereservationservice.reservation.dto.VenueCreateDto;
import com.nafiu.moviereservationservice.reservation.dto.VenueResponseDto;
import com.nafiu.moviereservationservice.reservation.model.Venue;

public class VenueMapper {
    public static Venue VenueFromVenueCreateDto(VenueCreateDto venueCreateDto){
        return new Venue(
                venueCreateDto.name(),
                venueCreateDto.address(),
                venueCreateDto.capacity()
        );
    }

    public static VenueResponseDto VenueToVenueResponseDto(Venue venue){
        return new VenueResponseDto(
                venue.getId(),
                venue.getName(),
                venue.getAddress(),
                venue.getCapacity(),
                venue.getSeats()
        );
    }
}
