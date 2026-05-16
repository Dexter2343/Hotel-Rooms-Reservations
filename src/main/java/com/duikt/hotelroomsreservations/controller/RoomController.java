package com.duikt.hotelroomsreservations.controller;

import com.duikt.hotelroomsreservations.dto.room.CreateRoomRequest;
import com.duikt.hotelroomsreservations.dto.room.UpdateRoomRequest;
import com.duikt.hotelroomsreservations.entity.Room;
import com.duikt.hotelroomsreservations.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        roomService.createRoom(
                request.getRoomNumber(),
                request.getType(),
                request.getPrice(),
                request.getIsAvailable()
        );
        return ResponseEntity.ok().build();
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody UpdateRoomRequest request) {
        roomService.updateRoom(
                id,
                request.getRoomNumber(),
                request.getType(),
                request.getPrice(),
                request.isAvailable(),
                request.getBusyTo()
        );
        return ResponseEntity.ok().build();
    }

    @GetMapping("/rooms/{id}")
    public Room getRoom(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    @GetMapping("/rooms")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @DeleteMapping("/rooms/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.ok("Room deleted successfully");
    }
}
