package com.duikt.hotelroomsreservations.dto.room;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateRoomRequest {
    private String roomNumber;
    private String type;
    private double price;
    private boolean isAvailable;
    private LocalDateTime busyTo;
    private LocalDateTime discountTo;
}
