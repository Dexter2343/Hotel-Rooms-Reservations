package com.duikt.hotelroomsreservations.service;

import com.duikt.hotelroomsreservations.entity.User;

import java.util.List;

public interface UserService {
    User createUser(String name, String email, String password, String role, double balance);
    User updateUser(Long id, String name, String email, String password, String role,  double balance);
    User getUserById(Long id);
    List<User> getAllUsers();
    void deleteUser(Long id);
}
