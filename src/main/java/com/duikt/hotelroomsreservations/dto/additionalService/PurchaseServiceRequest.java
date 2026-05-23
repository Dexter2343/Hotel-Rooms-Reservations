package com.duikt.hotelroomsreservations.dto.additionalService;

import lombok.Data;

@Data
public class PurchaseServiceRequest {
    private String serviceName;
    private double price;
}
