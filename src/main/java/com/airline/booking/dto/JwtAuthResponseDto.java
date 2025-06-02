package com.airline.booking.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class JwtAuthResponseDto {
    private final String accessToken;
    private String tokenType = "Bearer";
    private UserDto user; // Include user details in login response
}
