package com.duikt.hotelroomsreservations.controller;

import com.duikt.hotelroomsreservations.dto.room.CreateRoomRequest;
import com.duikt.hotelroomsreservations.dto.room.PurchaseRoomRequest;
import com.duikt.hotelroomsreservations.dto.room.UpdateRoomRequest;
import com.duikt.hotelroomsreservations.entity.Room;
import com.duikt.hotelroomsreservations.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @PostMapping("/rooms")
    public ResponseEntity<Room> createRoom(@RequestBody CreateRoomRequest request) {
      Room createdRoom = roomService.createRoom(
                request.getRoomNumber(),
                request.getType(),
                request.getPrice(),
                request.getIsAvailable()
        );
        return ResponseEntity.ok(createdRoom);
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody UpdateRoomRequest request) {
     Room updateRoom = roomService.updateRoom(
                id,
                request.getRoomNumber(),
                request.getType(),
                request.getPrice(),
                request.isAvailable(),
                request.getBusyTo()
        );
        return ResponseEntity.ok(updateRoom);
    }

    @GetMapping("/rooms/{id}")
    public Room getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    @GetMapping("/rooms")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }


    @GetMapping("/rooms/users/{id}")
    public List<Room> getRoomsByUserId(@PathVariable Long id) {
        return roomService.getRoomByUserId(id);
    }


    @GetMapping("/rooms/number/{roomNumber}")
    public List<Room> getRoomsByRoomNumber(@PathVariable String roomNumber) {
        return roomService.getRoomByRoomNumber(roomNumber);
    }


    @PutMapping("/rooms/{roomId}/users/{userId}/buy")
    public ResponseEntity<Room> buyRoom(@PathVariable Long roomId, @PathVariable Long userId, @RequestBody UpdateRoomRequest request) {
     Room buy = roomService.buyRoom(roomId, userId, request.getType(),
                request.getPrice(), request.isAvailable(), request.getBusyTo());
        return ResponseEntity.ok(buy);
    }

    @DeleteMapping("/rooms/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.ok("Room deleted successfully");
    }
}
