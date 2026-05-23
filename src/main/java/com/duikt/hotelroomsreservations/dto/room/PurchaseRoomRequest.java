package com.duikt.hotelroomsreservations.dto.room;

import lombok.Data;

import java.time.LocalDateTime;
 /**
  * TODO зробити логіку покупки кімнат з терміном орденди(мусить ставати доступною після кінця оренди і видалення юзера з кімнати)
  * TODO Зробити логіку замовлення додаткових послуг для кімнат
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
