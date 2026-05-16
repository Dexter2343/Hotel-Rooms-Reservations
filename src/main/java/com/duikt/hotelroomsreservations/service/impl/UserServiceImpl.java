package com.duikt.hotelroomsreservations.service.impl;

import com.duikt.hotelroomsreservations.entity.User;
import com.duikt.hotelroomsreservations.exceptions.UserNotFoundException;
import com.duikt.hotelroomsreservations.repository.UserRepo;
import com.duikt.hotelroomsreservations.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserService userService;
    private final UserRepo userRepo;

    @Override
    public User createUser(String name, String email, String password, String role, double balance) {
        User user = User.builder()
                .name(name)
                .email(email)
                .password(password)
                .role(role)
                .balance(balance)
                .build();
        return userRepo.save(user);
    }

    @Override
    public User updateUser(Long id, String name, String email, String password, String role, double balance) {
        User user = userRepo.findById(id).orElseThrow(()
                -> new UserNotFoundException("User not found with id " + id));
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        user.setBalance(balance);
    return userRepo.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepo.findById(id).orElseThrow(()
                -> new UserNotFoundException("User not found  with id " + id));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}
