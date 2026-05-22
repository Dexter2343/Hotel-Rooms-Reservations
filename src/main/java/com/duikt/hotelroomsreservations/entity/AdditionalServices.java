package com.duikt.hotelroomsreservations.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "additional_services")
public class AdditionalServices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String serviceName;
    private double price;

    @ManyToOne()
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
}
