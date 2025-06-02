package com.airline.booking.service;

import com.airline.booking.dto.FlightDto;
import com.airline.booking.dto.FlightSearchRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {
    @Override
    public FlightDto scheduleFlight(FlightDto flightDto) {
        return null;
    }

    @Override
    public FlightDto getFlightById(Long flightId) {
        return null;
    }

    @Override
    public List<FlightDto> searchDirectFlights(FlightSearchRequestDto searchRequest) {
        return List.of();
    }
}
