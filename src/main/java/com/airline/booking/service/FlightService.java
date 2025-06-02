package com.airline.booking.service;

import com.airline.booking.dto.FlightDto;
import com.airline.booking.dto.FlightSearchRequestDto;

import java.util.List;

public interface FlightService {

    FlightDto scheduleFlight(FlightDto flightDto); // Requires complex validation
    FlightDto getFlightById(Long flightId);
    List<FlightDto> searchDirectFlights(FlightSearchRequestDto searchRequest);
    // List<List<FlightDto>> searchTransitFlights(FlightSearchRequestDto searchRequest); // More complex
    // Other methods: updateFlightStatus, etc.
}
