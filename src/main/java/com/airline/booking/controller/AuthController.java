package com.airline.booking.controller;

import com.airline.booking.dto.JwtAuthResponseDto;
import com.airline.booking.dto.LoginRequestDto;
import com.airline.booking.dto.RegisterRequestDto;
import com.airline.booking.dto.UserDto;
import com.airline.booking.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponseDto> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        JwtAuthResponseDto jwtAuthResponseDto = authService.login(loginRequestDto);
        return ResponseEntity.ok(jwtAuthResponseDto);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
        UserDto registeredUser = authService.register(registerRequestDto);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

}
