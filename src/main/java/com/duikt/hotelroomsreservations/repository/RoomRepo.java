package com.duikt.hotelroomsreservations.repository;

import com.duikt.hotelroomsreservations.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepo extends JpaRepository<Room, Long> {
    List<Room> getRoomsByRoomNumber(String roomNumber);
    List<Room> getRoomsByUserId(Long userId);
}
