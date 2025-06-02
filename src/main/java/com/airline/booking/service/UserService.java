package com.airline.booking.service;

import com.airline.booking.dto.UserDto;
import com.airline.booking.dto.UserUpdateRequestDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto, String roleName); // Admin creates user with specific role
    UserDto getUserById(Long userId);
    List<UserDto> getAllUsers();
    UserDto updateUser(Long userId, UserUpdateRequestDto userUpdateRequestDto); // Admin updates user
    void deactivateUser(Long userId); // Admin deactivates user
    void activateUser(Long userId); // Admin activates user
}
