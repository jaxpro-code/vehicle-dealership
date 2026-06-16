package com.example.vehicle_dealership.repositories;

import com.example.vehicle_dealership.entities.Dealerships;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DealershipRepository extends JpaRepository<Dealerships, Long> {
    //provided crud, create a delership, update a dealership, find by id, find all,delete by id, delete dealership, check existance

    //by name
    Optional<Dealerships> findByNameIgnoreCase(String name);
}
