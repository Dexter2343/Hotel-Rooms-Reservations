package com.duikt.hotelroomsreservations.dto.room;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateRoomRequest {

    @NotBlank(message = "Room number is required")
    private String roomNumber;

    @NotBlank(message = "Room type is required")
    private String type;

    @NotNull(message = "Price is required")
    @DecimalMax(value = "100000.0", message = "Price must be less than 100000")
    private Double price;

    @NotNull(message = "Availability is required")
    private Boolean isAvailable;

    @NotNull(message = "Buy date is required")
    private LocalDateTime buyDate;

    @NotNull(message = "Busy to date is required")
    private LocalDateTime busyTo;

    private LocalDateTime discountTo;
}