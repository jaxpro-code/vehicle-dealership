package com.example.vehicle_dealership.repositories;

import com.example.vehicle_dealership.entities.Vehicle;
import com.example.vehicle_dealership.entities.enums.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    //provided crud queries are based on the object as a whole, we have to write the ones that break up the object
    //provided crud, create a vehicle, update  a vehicle, find by id, find all, delete by id delete vehicle, check existace

    //by price
    List<Vehicle> findByPriceBetween(Double min, Double max);

    //by make
    List<Vehicle> findByMakeIgnoreCase(String make);

    //by model
    List<Vehicle> findByModelIgnoreCase(String model);

    //by year
    List<Vehicle> findByYearBetween(Integer min, Integer max);

    //by color
    List<Vehicle> findByColorIgnoreCase(String color);

    //by miles
    List<Vehicle> findByMilesBetween(Integer min, Integer max);

    //by vehicleType
    List<Vehicle> findByVehicleType(VehicleType vehicleType);

    //by dealership
    List<Vehicle> findByDealershipId(Long id);

}
