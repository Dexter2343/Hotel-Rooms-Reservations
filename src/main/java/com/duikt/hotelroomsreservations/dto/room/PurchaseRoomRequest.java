package com.duikt.hotelroomsreservations.dto.room;

import lombok.Data;

import java.time.LocalDateTime;
 @Data
public class PurchaseRoomRequest {
    private LocalDateTime busyTo;
    private LocalDateTime discountTo;
    private double discountedPrice;
}
