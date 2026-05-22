package com.duikt.hotelroomsreservations.dto.room;

import lombok.Data;

import java.time.LocalDateTime;
 /**
  * TODO зробити логіку покупки кімнат з терміном орденди
  * TODO Зробити логіку замовлення додаткових послуг для кімнат
  */
 @Data
public class PurchaseRoomRequest {
    private Long roomId;
    private String roomNumber;
    private String type;
    private double price;
    private LocalDateTime buyDate;
    private LocalDateTime busyTo;
    private boolean isAvailable;
}
