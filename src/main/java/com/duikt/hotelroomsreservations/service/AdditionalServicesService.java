package com.duikt.hotelroomsreservations.service;

import com.duikt.hotelroomsreservations.entity.AdditionalServices;

import java.util.List;

public interface AdditionalServicesService {
    AdditionalServices createAdditionalService(String type, double price);
    AdditionalServices updateAdditionalService(Long id, String type, double price);
    AdditionalServices getAdditionalServiceById(Long id);
    List<AdditionalServices> getAllAdditionalServices();
    void deleteAdditionalService(Long id);
}
