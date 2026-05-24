package com.duikt.hotelroomsreservations.dto.room;

import lombok.Data;

import java.time.LocalDateTime;
 /**
    * TODO Додати сезонні знижки для кімнат, якщо дата спливає кидати DiscountException
  */
 @Data
public class PurchaseRoomRequest {
    private String roomNumber;
    private String type;
    private double price;
    private LocalDateTime buyDate;
    private LocalDateTime busyTo;
    private boolean isAvailable;
}
