package com.duikt.hotelroomsreservations.dto.additionalService;

import lombok.Data;

@Data
public class UpdateAdditionalServiceRequest {
    private String serviceName;
    private double price;
}
