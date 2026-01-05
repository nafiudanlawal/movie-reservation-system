package com.nafiu.moviereservationservice.reservation;

import com.nafiu.moviereservationservice.reservation.dto.*;
import com.nafiu.moviereservationservice.reservation.service.VenueService;
import jakarta.validation.Valid;
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
    public VenueResponseDto createSeat(@RequestBody @Valid VenueCreateDto venueDto) {
        return this.venueService.createVenue(venueDto);
    }

    @PatchMapping("/{id}")
    public VenueResponseDto updateSeat(
            @PathVariable("id") Integer id,
            @RequestBody @Valid VenueUpdateDto venueDto
    ) {
        return this.venueService.updateVenue(id, venueDto);
    }

    @GetMapping
    public List<VenueResponseDto> getVenues() {
        return this.venueService.getVenues();
    }

    @GetMapping("/{id}")
    public VenueResponseDto getVenue(@PathVariable("id") Integer id) {
        return this.venueService.getVenue(id);
    }

    @DeleteMapping("/{id}")
    public void deleteVenue(@PathVariable("id") Integer id) {
        this.venueService.deleteVenue(id);
    }

    @PostMapping("/{id}/seats")
    @ResponseStatus(HttpStatus.CREATED)
    public SeatVenueResponseDto createVenueSeat(
            @PathVariable("id")
            Integer venueId,
            @RequestBody @Valid
            VenueSeatCreateDto venueSeatCreateDto
    ) {
        return this.venueService.addSeatToVenue(venueId, venueSeatCreateDto);
    }

    @GetMapping("/{id}/seats")
    public List<SeatVenueResponseDto> getVenueSeats(
            @PathVariable("id")
            Integer venueId
    ) {
        return this.venueService.getVenueSeats(venueId);
    }

    @DeleteMapping("/{venueId}/seats/{seatId}")
    public void deleteVenueSeat(@PathVariable("venueId") Integer venueId, @PathVariable("seatId") Integer seatId) {
        this.venueService.deleteVenueSeat(venueId, seatId);
    }

    @GetMapping("/{venueId}/seats/{seatId}")
    public SeatVenueResponseDto getVenueSeat(
            @PathVariable("venueId") Integer venueId,
            @PathVariable("seatId") Integer seatId
    ) {
        return this.venueService.getVenueSeat(venueId, seatId);
    }

    @PatchMapping("/{venueId}/seats/{seatId}")
    public SeatVenueResponseDto getVenueSeat(
            @PathVariable("venueId") Integer venueId,
            @PathVariable("seatId") Integer seatId,
            @RequestBody @Valid SeatVenueUpdateDto seatVenueUpdateDto
    ) {
        return this.venueService.updateVenueSeat(venueId, seatId, seatVenueUpdateDto);
    }
}
