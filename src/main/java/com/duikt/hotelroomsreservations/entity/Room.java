package com.duikt.hotelroomsreservations.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String roomNumber;
    private String type;
    private double price;
    private double discountedPrice;
    private boolean isAvailable;
    private LocalDateTime buyDate;
    private LocalDateTime busyTo;
    private LocalDateTime discountTo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}