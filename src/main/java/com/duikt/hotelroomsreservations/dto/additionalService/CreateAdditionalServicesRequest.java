package com.duikt.hotelroomsreservations.dto.additionalService;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateAdditionalServicesRequest {
    @NotBlank(message = "Service name is required")
    private String serviceName;
    @NotBlank(message = "Price is required")
    private double price;
}
