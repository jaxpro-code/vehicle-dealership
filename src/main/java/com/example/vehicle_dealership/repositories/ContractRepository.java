package com.example.vehicle_dealership.repositories;

import com.example.vehicle_dealership.entities.Contract;
import com.example.vehicle_dealership.entities.enums.ContractType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    //provided crud, create a contract, update a contract, find by id, find all, delete by id, delete contract, check existance

    //by type
    List<Contract> findByTypeIgnoreCase(ContractType type);

    //by vin
    List<Contract> findByVehicleVinContainingIgnoreCase(String vin);

}
