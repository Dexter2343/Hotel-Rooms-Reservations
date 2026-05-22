package com.duikt.hotelroomsreservations.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
    private double balance;

    @OneToMany(mappedBy = "user")
    private List<Room> rooms;

    @OneToMany(mappedBy = "user")
    private List<AdditionalServices> additionalServices;
}
