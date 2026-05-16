package com.duikt.hotelroomsreservations.dto.user;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String name;
    private String email;
    private String password;
    private String role;
    private double balance;
}
