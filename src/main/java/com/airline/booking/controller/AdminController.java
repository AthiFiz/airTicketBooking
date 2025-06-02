package com.airline.booking.controller;

import com.airline.booking.dto.UserDto;
import com.airline.booking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@PreAuthorize("hasRole('ADMINISTRATOR')") // All methods here require ADMIN role
public class AdminController {

    @Autowired
    private UserService userService;

    // @PostMapping
    // public ResponseEntity<UserDto> createUser(@RequestBody UserCreateRequest createRequest) {
    //     UserDto newUser = userService.createUser(createRequest); // Adjust service method
    //     return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    // }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // @PutMapping("/{id}")
    // public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest updateRequest) {
    //     return ResponseEntity.ok(userService.updateUser(id, updateRequest));
    // }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateUser(@PathVariable Long id) {
        userService.deactivateUser(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activateUser(@PathVariable Long id) {
        userService.activateUser(id);
        return ResponseEntity.noContent().build();
    }



}
