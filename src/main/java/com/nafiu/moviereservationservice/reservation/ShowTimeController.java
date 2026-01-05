package com.nafiu.moviereservationservice.reservation;

import com.nafiu.moviereservationservice.reservation.dto.ShowTimeCreateDto;
import com.nafiu.moviereservationservice.reservation.dto.ShowTimeResponseDto;
import com.nafiu.moviereservationservice.reservation.service.ShowTimeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/showTimes")
public class ShowTimeController {
    private final ShowTimeService service;

    public ShowTimeController(ShowTimeService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShowTimeResponseDto createShowTime(@RequestBody @Valid ShowTimeCreateDto showTimeCreateDto) {
        return this.service.createShowTime(showTimeCreateDto);
    }


    @GetMapping
    public List<ShowTimeResponseDto> getShowTimes() {
        return this.service.getShowTimes();
    }

    @GetMapping("/{id}")
    public ShowTimeResponseDto getShowTime(
            @PathVariable("id")
            Integer id
    ) {
        return this.service.getShowTime(id);
    }
}
