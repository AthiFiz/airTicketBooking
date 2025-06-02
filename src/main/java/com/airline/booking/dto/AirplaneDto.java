package com.airline.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirplaneDto {

    private Long id;
    private String model;
    private String capacityClass; // SMALL, MEDIUM, LARGE
    private int firstClassCapacity;
    private int businessClassCapacity;
    private int economyClassCapacity;
}
