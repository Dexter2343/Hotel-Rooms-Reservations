package com.duikt.hotelroomsreservations.repository;

import com.duikt.hotelroomsreservations.entity.AdditionalServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalServicesRepo extends JpaRepository<AdditionalServices, Long> {

}
