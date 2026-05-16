package com.duikt.hotelroomsreservations.service;

import com.duikt.hotelroomsreservations.entity.AdditionalServices;

import java.util.List;

public interface AdditionalServicesService {
    AdditionalServices createAdditionalService(String serviceName, double price);
    AdditionalServices updateAdditionalService(Long id, String serviceName, double price);
    AdditionalServices getAdditionalServiceById(Long id);
    List<AdditionalServices> getAllAdditionalServices();
    void deleteAdditionalService(Long id);
}
