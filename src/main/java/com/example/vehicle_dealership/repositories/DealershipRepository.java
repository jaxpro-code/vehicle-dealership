package com.example.vehicle_dealership.repositories;

import com.example.vehicle_dealership.entities.Dealerships;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealershipRepository extends JpaRepository<Dealerships, Long> {

}
