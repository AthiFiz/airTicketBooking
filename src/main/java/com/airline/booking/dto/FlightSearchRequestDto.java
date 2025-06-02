package com.airline.booking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FlightSearchRequestDto {
    @NotBlank
    private String departureAirportIataCode;
    @NotBlank
    private String arrivalAirportIataCode;
    @NotNull
    private LocalDate departureDate;
    private String seatClass; // Optional: FIRST, BUSINESS, ECONOMY
    private int passengerCount = 1; // Default to 1
}
