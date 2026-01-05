package com.nafiu.moviereservationservice.reservation;

import com.nafiu.moviereservationservice.reservation.dto.*;
import com.nafiu.moviereservationservice.reservation.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venues")
public class VenueController {
    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VenueResponseDto createSeat(@RequestBody VenueCreateDto venueDto){
        return this.venueService.createVenue(venueDto);
    }

    @PostMapping("/{id}/seats")
    @ResponseStatus(HttpStatus.CREATED)
    public SeatResponseDto createVenueSeat(
            @PathVariable("id")
            Integer venueId,
            @RequestBody
            SeatCreateDto seatCreateDto
    ){
        return this.venueService.addSeatToVenue(venueId, seatCreateDto);
    }

    @GetMapping("/{id}/seats")
    public List<SeatResponseDto> getVenueSeat(
            @PathVariable("id")
            Integer venueId
    ){
        return this.venueService.getVenueSeats(venueId);
    }
    @GetMapping
    public List<VenueResponseDto> getVenues(){
        return this.venueService.getVenues();
    }
    @GetMapping("/{id}")
    public VenueResponseDto getVenue(@PathVariable("id")Integer id){
        return this.venueService.getVenue(id);
    }
}
