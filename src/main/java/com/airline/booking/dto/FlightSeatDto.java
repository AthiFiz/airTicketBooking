package com.airline.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightSeatDto {

    private Long id;
    private String seatClass; // FIRST, BUSINESS, ECONOMY
    private int totalSeats;
    private int bookedSeats;
    private int availableSeats; // Calculated: totalSeats - bookedSeats

}
