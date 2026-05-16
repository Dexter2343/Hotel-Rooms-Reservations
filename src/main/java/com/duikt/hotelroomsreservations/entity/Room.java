package com.duikt.hotelroomsreservations.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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


    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
