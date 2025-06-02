package com.airline.booking.service;

import com.airline.booking.dto.JwtAuthResponseDto;
import com.airline.booking.dto.LoginRequestDto;
import com.airline.booking.dto.RegisterRequestDto;
import com.airline.booking.dto.UserDto;
import com.airline.booking.entity.Role;
import com.airline.booking.entity.User;
import com.airline.booking.exception.BadRequestException;
import com.airline.booking.repository.RoleRepository;
import com.airline.booking.repository.UserRepository;
import com.airline.booking.util.jwt.JwtTokenProvider;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public JwtAuthResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUsername(),
                        loginRequestDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);

        // Fetch user details to include in the response
        User user = userRepository.findByUsername(loginRequestDto.getUsername())
                .orElseThrow(() -> new BadRequestException("User not found after authentication")); // Should not happen

        UserDto userDto = convertToUserDto(user);
        return new JwtAuthResponseDto(token, userDto);
    }

    @Override
    @Transactional
    public UserDto register(RegisterRequestDto registerRequestDto) {
        if (userRepository.existsByUsername(registerRequestDto.getUsername())) {
            throw new BadRequestException("Username is already taken!");
        }

        if (userRepository.existsByEmail(registerRequestDto.getEmail())) {
            throw new BadRequestException("Email is already taken!");
        }

        User user = new User();
        user.setUsername(registerRequestDto.getUsername());
        user.setEmail(registerRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
        user.setFirstName(registerRequestDto.getFirstName());
        user.setLastName(registerRequestDto.getLastName());

        Role userRole = roleRepository.findByName("ROLE_CUSTOMER")
                .orElseThrow(() -> new RuntimeException("Error: Role CUSTOMER is not found."));
        user.setRole(userRole);
        user.setActive(true);

        User savedUser = userRepository.save(user);
        return convertToUserDto(savedUser);
    }

    // Helper method to convert User entity to UserDto
    private UserDto convertToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setRole(user.getRole().getName());
        userDto.setActive(user.isActive());
        return userDto;
    }

}
