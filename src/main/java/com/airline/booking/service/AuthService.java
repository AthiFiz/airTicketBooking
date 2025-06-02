package com.airline.booking.service;

import com.airline.booking.dto.JwtAuthResponseDto;
import com.airline.booking.dto.LoginRequestDto;
import com.airline.booking.dto.RegisterRequestDto;
import com.airline.booking.dto.UserDto;

public interface AuthService {

    JwtAuthResponseDto login(LoginRequestDto loginRequestDto);
    UserDto register(RegisterRequestDto registerRequestDto);
    // UserDto registerOperator(RegisterRequest registerRequest); // Could be an admin function
    // UserDto registerAdmin(RegisterRequest registerRequest); // Could be an admin function

}
