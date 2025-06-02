package com.airline.booking.controller;

import com.airline.booking.dto.FlightDto;
import com.airline.booking.dto.FlightSearchRequestDto;
import com.airline.booking.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    // Example: Admin schedules a flight
    @PostMapping("/schedule")
    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'OPERATOR')") // Only admin or operator can schedule
    public ResponseEntity<FlightDto> scheduleFlight(@Valid @RequestBody FlightDto flightDto) {
        // Implementation in FlightService will handle creation of Flight and FlightSeat entities
        FlightDto scheduledFlight = flightService.scheduleFlight(flightDto);
        return ResponseEntity.ok(scheduledFlight);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightDto> getFlightById(@PathVariable Long id) {
        FlightDto flight = flightService.getFlightById(id);
        return ResponseEntity.ok(flight);
    }

    // Public search endpoint
    @PostMapping("/search/direct") // Using POST to send search criteria in body
    public ResponseEntity<List<FlightDto>> searchDirectFlights(@Valid @RequestBody FlightSearchRequestDto searchRequest) {
        List<FlightDto> flights = flightService.searchDirectFlights(searchRequest);
        return ResponseEntity.ok(flights);
    }

    // TODO: Endpoint for searching transit flights (more complex)
    // @PostMapping("/search/transit")
    // public ResponseEntity<List<List<FlightDto>>> searchTransitFlights(@Valid @RequestBody FlightSearchRequestDto searchRequest) {
    //     // ...
    // }

}
