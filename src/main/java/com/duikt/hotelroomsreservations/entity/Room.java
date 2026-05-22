package com.duikt.hotelroomsreservations.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String roomNumber;
    private String type;
    private double price;
    private boolean isAvailable;
    private LocalDateTime buyDate;
    private LocalDateTime busyTo;
    private LocalDateTime discountTo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
