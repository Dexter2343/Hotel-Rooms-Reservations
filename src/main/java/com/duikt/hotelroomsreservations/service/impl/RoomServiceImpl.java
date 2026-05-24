package com.duikt.hotelroomsreservations.service.impl;

import com.duikt.hotelroomsreservations.entity.Room;
import com.duikt.hotelroomsreservations.entity.User;
import com.duikt.hotelroomsreservations.exceptions.BalanceException;
import com.duikt.hotelroomsreservations.exceptions.ReservationException;
import com.duikt.hotelroomsreservations.exceptions.RoomNotFoudException;
import com.duikt.hotelroomsreservations.exceptions.UserNotFoundException;
import com.duikt.hotelroomsreservations.repository.RoomRepo;
import com.duikt.hotelroomsreservations.repository.UserRepo;
import com.duikt.hotelroomsreservations.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepo roomRepo;
    private final UserRepo userRepo;

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
    public Room updateRoom(Long id, String roomNumber, String type, double price, boolean isAvailable, LocalDateTime busyTo) {
        Room room = roomRepo.findById(id).orElseThrow(()
                -> new RoomNotFoudException("Room not found with id " + id));
        room.setRoomNumber(roomNumber);
        room.setType(type);
        room.setPrice(price);
        room.setBusyTo(busyTo);
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
    public Room buyRoom(Long roomId, Long userId, String type, double price, boolean isAvailable, LocalDateTime busyTo) {
        Room room = roomRepo.findById(roomId).orElseThrow(()
                -> new RoomNotFoudException("Room not found with id " + roomId));

        User user = userRepo.findById(userId).orElseThrow(()
                -> new UserNotFoundException("User not found with id " + userId));

        if (!room.isAvailable()) {
            throw new ReservationException("Room is not available");
        }

        if (user.getBalance() < price) {
            throw new BalanceException("Not enough balance");
        }

        LocalDateTime buyDate = LocalDateTime.now();

        room.setUser(user);
        room.setIsAvailable(false);
        room.setBuyDate(buyDate);
        room.setBusyTo(busyTo);
        room.setPrice(price);
        user.setBalance(user.getBalance() - price);

        return roomRepo.save(room);
    }

    @Override
    public List<Room> getRoomByUserId(Long userId) {
        return roomRepo.getRoomsByUserId(userId);
    }

    @Override
    public List<Room> getRoomByRoomNumber(String roomNumber) {
        return roomRepo.getRoomsByRoomNumber(roomNumber);
    }

    @Scheduled(fixedRate = 60000)
    public void freeExpiredRooms(){
        List<Room> expiredRooms = roomRepo.findAll()
                .stream()
                .filter(r -> r.getBusyTo() != null
                        && r.getBusyTo().isBefore(LocalDateTime.now())
                        && !r.isAvailable())
                .toList();
        expiredRooms.forEach(room -> {
            room.setUser(null);
            room.setIsAvailable(true);
            room.setBusyTo(null);
            room.setBuyDate(null);
        });

        roomRepo.saveAll(expiredRooms);
    }

    @Override
    public void deleteRoom(Long id) {
        roomRepo.deleteById(id);
    }
}
