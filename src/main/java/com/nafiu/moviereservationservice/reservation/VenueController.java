package com.nafiu.moviereservationservice.reservation;

import com.nafiu.moviereservationservice.reservation.dto.VenueCreateDto;
import com.nafiu.moviereservationservice.reservation.dto.VenueResponseDto;
import com.nafiu.moviereservationservice.reservation.dto.VenueUpdateDto;
import com.nafiu.moviereservationservice.reservation.service.VenueService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        value = "/venues",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class VenueController {
    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public VenueResponseDto createSeat(@RequestBody @Valid VenueCreateDto venueDto) {
        return this.venueService.createVenue(venueDto);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteVenue(@PathVariable("id") Integer id) {
        this.venueService.deleteVenue(id);
    }
}
