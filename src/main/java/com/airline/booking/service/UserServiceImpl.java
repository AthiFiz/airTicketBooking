package com.airline.booking.service;

import com.airline.booking.dto.UserDto;
import com.airline.booking.dto.UserUpdateRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserDto createUser(UserDto userDto, String roleName) {
        return null;
    }

    @Override
    public UserDto getUserById(Long userId) {
        return null;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return List.of();
    }

    @Override
    public UserDto updateUser(Long userId, UserUpdateRequestDto userUpdateRequestDto) {
        return null;
    }

    @Override
    public void deactivateUser(Long userId) {

    }

    @Override
    public void activateUser(Long userId) {

    }
}
