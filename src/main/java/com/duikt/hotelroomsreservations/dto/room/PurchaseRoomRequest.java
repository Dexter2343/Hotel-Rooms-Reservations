package com.duikt.hotelroomsreservations.dto.room;

import lombok.Data;

import java.time.LocalDateTime;
 /**
    * TODO Рефактор оновлення кімнат: прибрати булеан та термін оренди з оновлнення
  *  TODO Зробити оновлення ціни зі знижки на дефолтну яка була до знижки
  *
  */
 @Data
public class PurchaseRoomRequest {
    private LocalDateTime busyTo;
    private LocalDateTime discountTo;
}
