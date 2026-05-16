package com.duikt.hotelroomsreservations.service.impl;

import com.duikt.hotelroomsreservations.entity.AdditionalServices;
import com.duikt.hotelroomsreservations.exceptions.ServiceNotFoundException;
import com.duikt.hotelroomsreservations.repository.AdditionalServicesRepo;
import com.duikt.hotelroomsreservations.service.AdditionalServicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdditionalServicesServiceImpl implements AdditionalServicesService {

    private final AdditionalServicesService additionalServicesService;
    private final AdditionalServicesRepo additionalServiceRepo;

    @Override
    public AdditionalServices createAdditionalService(String roomType, double price) {
        AdditionalServices additionalServices = AdditionalServices.builder()
                .roomType(roomType)
                .price(price)
                .build();
        return additionalServiceRepo.save(additionalServices);
    }

    @Override
    public AdditionalServices updateAdditionalService(Long id, String roomType, double price) {
    AdditionalServices additionalServices = additionalServiceRepo.findById(id).orElseThrow(() ->
                new ServiceNotFoundException("Service not found with id " + id));
        additionalServices.setRoomType(roomType);
        additionalServices.setPrice(price);
    return additionalServiceRepo.save(additionalServices);
    }

    @Override
    public AdditionalServices getAdditionalServiceById(Long id) {
        return additionalServiceRepo.findById(id).orElseThrow(() ->
                new ServiceNotFoundException("Service not found with id " + id));
    }

    @Override
    public List<AdditionalServices> getAllAdditionalServices() {
        return additionalServiceRepo.findAll();
    }

    @Override
    public void deleteAdditionalService(Long id) {
        additionalServiceRepo.deleteById(id);
    }
}
