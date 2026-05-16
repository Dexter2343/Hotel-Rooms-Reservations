package com.duikt.hotelroomsreservations.service.impl;

import com.duikt.hotelroomsreservations.entity.Room;
import com.duikt.hotelroomsreservations.entity.User;
import com.duikt.hotelroomsreservations.exceptions.RoomNotFoudException;
import com.duikt.hotelroomsreservations.repository.RoomRepo;
import com.duikt.hotelroomsreservations.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomService roomService;
    private final RoomRepo roomRepo;

    @Override
    public Room createRoom(String roomNumber, String type, double price, boolean isAvailable) {
        Room room = Room.builder()
                .roomNumber(roomNumber)
                .type(type)
                .price(price)
                .isAvailable(isAvailable)
                .build();
        return roomRepo.save(room);
    }

    @Override
    public Room updateRoom(Long id, String roomNumber, String type, double price, boolean isAvailable) {
        Room room = roomRepo.findById(id).orElseThrow(()
                -> new RoomNotFoudException("Room not found with id " + id));
        room.setRoomNumber(roomNumber);
        room.setType(type);
        room.setPrice(price);
        room.setIsAvailable(isAvailable);
        return roomRepo.save(room);
    }

    @Override
    public Room getRoomById(Long id) {
        return roomRepo.findById(id).orElseThrow(()
                -> new RoomNotFoudException("Room not found with id " + id));


    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepo.findAll();
    }

    @Override
    public Room buyRoom(Long id, User user, String type, double price, boolean isAvailable, LocalDateTime buyDate, LocalDateTime busyTo) {
        Room room = roomRepo.findById(id).orElseThrow(()
                -> new RoomNotFoudException("Room not found with id " + id)
        );
        room.setIsAvailable(false);
        room.setBuyDate(buyDate);
        room.setBusyTo(busyTo);
        room.setPrice(price);
    return roomRepo.save(room);
    }

    @Override
    public void deleteRoom(Long id) {
        roomRepo.deleteById(id);
    }
}
