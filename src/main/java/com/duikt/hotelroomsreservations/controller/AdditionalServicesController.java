package com.duikt.hotelroomsreservations.controller;

import com.duikt.hotelroomsreservations.dto.additionalService.CreateAdditionalServicesRequest;
import com.duikt.hotelroomsreservations.dto.additionalService.PurchaseServiceRequest;
import com.duikt.hotelroomsreservations.dto.additionalService.UpdateAdditionalServiceRequest;
import com.duikt.hotelroomsreservations.entity.AdditionalServices;
import com.duikt.hotelroomsreservations.service.AdditionalServicesService;
import com.duikt.hotelroomsreservations.service.impl.AdditionalServicesServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AdditionalServicesController {
    private final AdditionalServicesServiceImpl additionalServicesService;

    @PostMapping("/additional-services")
    public ResponseEntity<AdditionalServices> createService(@RequestBody CreateAdditionalServicesRequest request) {
        AdditionalServices dto  = additionalServicesService.createAdditionalService(
                request.getServiceName(),
                request.getPrice()
        );
        return ResponseEntity.ok().build();
    }

    @PutMapping("additional-services/{id}")
    public ResponseEntity<AdditionalServices> updateService(@PathVariable Long id, @RequestBody UpdateAdditionalServiceRequest request) {
        AdditionalServices dto  = additionalServicesService.updateAdditionalService(
                id,
                request.getServiceName(),
                request.getPrice()
        );
        return ResponseEntity.ok(dto);
    }

    @PutMapping("additional-services/buy/{serviceId}/room/{roomId}/user/{userId}")
    public ResponseEntity<AdditionalServices> buyService(@PathVariable Long serviceId,
                                                         @PathVariable Long roomId,
                                                         @PathVariable Long userId,
                                                         @RequestBody PurchaseServiceRequest request) {
        AdditionalServices dto  = additionalServicesService.buyService(serviceId, roomId, userId,
                request.getServiceName(), request.getPrice());

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/additional-services/{id}")
    public AdditionalServices getServiceById(@PathVariable Long id) {
        return additionalServicesService.getAdditionalServiceById(id);
    }

    @GetMapping("/additional-services")
    public List<AdditionalServices> getAllServices() {
        return additionalServicesService.getAllAdditionalServices();
    }

    @DeleteMapping("additional-services/{id}")
    public ResponseEntity<AdditionalServices> deleteService(@PathVariable Long id){
        additionalServicesService.deleteAdditionalService(id);
        return ResponseEntity.ok().build();
    }
}