package com.duikt.hotelroomsreservations.service;

import com.duikt.hotelroomsreservations.entity.Room;

import java.time.LocalDateTime;
import java.util.List;

public interface RoomService {
    Room createRoom(String roomNumber, String type, double price, boolean isAvailable);
    Room updateRoom(Long id, String roomNumber, String type, double price, boolean isAvailable, LocalDateTime busyTo);
    Room getRoomById(Long id);
    List<Room> getAllRooms();
    Room buyRoom(Long roomId, Long userId, LocalDateTime busyTo);
    Room setDiscountToRoom(Long roomId, LocalDateTime discountTo, double discountPercent);
    List<Room> getRoomByUserId(Long userId);
    List<Room> getRoomByRoomNumber(String roomNumber);
    void deleteRoom(Long id);

}
