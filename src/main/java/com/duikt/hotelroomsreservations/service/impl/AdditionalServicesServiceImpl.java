package com.duikt.hotelroomsreservations.service.impl;

import com.duikt.hotelroomsreservations.entity.AdditionalServices;
import com.duikt.hotelroomsreservations.entity.Room;
import com.duikt.hotelroomsreservations.entity.User;
import com.duikt.hotelroomsreservations.exceptions.ReservationException;
import com.duikt.hotelroomsreservations.exceptions.RoomNotFoudException;
import com.duikt.hotelroomsreservations.exceptions.ServiceNotFoundException;
import com.duikt.hotelroomsreservations.exceptions.UserNotFoundException;
import com.duikt.hotelroomsreservations.repository.AdditionalServicesRepo;
import com.duikt.hotelroomsreservations.repository.RoomRepo;
import com.duikt.hotelroomsreservations.repository.UserRepo;
import com.duikt.hotelroomsreservations.service.AdditionalServicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdditionalServicesServiceImpl implements AdditionalServicesService {

    private final AdditionalServicesRepo additionalServiceRepo;
    private final RoomRepo roomRepo;
    private final UserRepo userRepo;

    @Override
    public AdditionalServices createAdditionalService(String serviceName, double price) {
        AdditionalServices additionalServices = AdditionalServices.builder()
                .serviceName(serviceName)
                .price(price)
                .build();
        return additionalServiceRepo.save(additionalServices);
    }

    @Override
    public AdditionalServices updateAdditionalService(Long id, String serviceName, double price) {
    AdditionalServices additionalServices = additionalServiceRepo.findById(id).orElseThrow(() ->
                new ServiceNotFoundException("Service not found with id " + id));
        additionalServices.setServiceName(serviceName);
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

    @Override
    public AdditionalServices buyService(Long serviceId, Long roomId, Long userId, String serviceName, double price) {
        Room room = roomRepo.getRoomsByUserId(userId)
                .stream()
                .filter(r -> r.getId() == roomId)
                .findFirst()
                .orElseThrow(() -> new ReservationException("User has not reserved this room"));

        User user = userRepo.findById(userId).orElseThrow(()
                -> new UserNotFoundException("User not found"));

        AdditionalServices service = additionalServiceRepo.findById(serviceId).orElseThrow(()
                -> new ServiceNotFoundException("Service not found"));

        service.setRoom(room);
        service.setUser(user);
        service.setPrice(price);
        return additionalServiceRepo.save(service);
    }
}
