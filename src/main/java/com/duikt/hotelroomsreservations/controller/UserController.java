package com.duikt.hotelroomsreservations.controller;

import com.duikt.hotelroomsreservations.dto.user.CreateUserRequest;
import com.duikt.hotelroomsreservations.dto.user.UpdateUserRequest;
import com.duikt.hotelroomsreservations.entity.User;
import com.duikt.hotelroomsreservations.repository.UserRepo;
import com.duikt.hotelroomsreservations.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserRepo userRepo;
    private final UserServiceImpl userService;


    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserRequest request) {
        User user = userService.createUser(
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                request.getRole(),
                request.getBalance()
        );
        return ResponseEntity.ok(user);
    }


    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest request) {
        User user = userService.updateUser(
                id,
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                request.getRole(),
                request.getBalance()
        );
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}
