package com.example.vehicle_dealership.services;

import com.example.vehicle_dealership.entities.Vehicle;
import com.example.vehicle_dealership.entities.enums.VehicleType;
import com.example.vehicle_dealership.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    //create
    public Vehicle create(Vehicle vehicle){
        Vehicle newVehicle = vehicleRepository.save(vehicle);

        return newVehicle;
    }

    //read
    public List<Vehicle> read(){
        List<Vehicle> vehicleList = vehicleRepository.findAll();

        return vehicleList;
    }

    //update
    public Vehicle update(Long id, Vehicle vehicle){
        Optional<Vehicle>  updateVehicle = vehicleRepository.findById(id);

        if(updateVehicle.isEmpty()){
            return null;
        }

        Vehicle vehicleToUpdate = updateVehicle.get();

        vehicleToUpdate.setVin(vehicle.getVin());
        vehicleToUpdate.setYear(vehicle.getYear());
        vehicleToUpdate.setMake(vehicle.getMake());
        vehicleToUpdate.setModel(vehicle.getModel());
        vehicleToUpdate.setColor(vehicle.getColor());
        vehicleToUpdate.setVehicleType(vehicle.getVehicleType());
        vehicleToUpdate.setMiles(vehicle.getMiles());
        vehicleToUpdate.setPrice(vehicle.getPrice());

        vehicleRepository.save(vehicleToUpdate);

        return vehicleToUpdate;
    }

    //delete
    public boolean delete(Long id){
        Optional<Vehicle> vehicleToDelete = vehicleRepository.findById(id);

        if(vehicleToDelete.isEmpty()){
            return false;
        }

        vehicleRepository.delete(vehicleToDelete.get());
        return true;
    }

    //by id
    public Optional<Vehicle> byId(Long id){
        var vehicle = vehicleRepository.findById(id);

        return vehicle;
    }

    //by price
    public List<Vehicle> byPrice(Double minPrice, Double maxPrice){
        return vehicleRepository.findByPriceBetween(minPrice, maxPrice);

    }

    //by make
    public List<Vehicle> byMake(String make){
        return vehicleRepository.findByMakeIgnoreCase(make);

    }

    //by model
    public List<Vehicle> byModel(String model){
        List<Vehicle> vehicles = vehicleRepository.findByModelIgnoreCase(model);

        return vehicles;
    }

    //by year
    public List<Vehicle> byYear(Integer minYear, Integer maxYear){
        List<Vehicle> vehicles = vehicleRepository.findByYearBetween(minYear, maxYear);

        return vehicles;
    }

    //by color
    public List<Vehicle> byColor(String color){
        List<Vehicle> vehicles = vehicleRepository.findByColorIgnoreCase(color);

        return vehicles;
    }

    //by miles
    public List<Vehicle> byMiles(Integer minMile, Integer maxMile){
        List<Vehicle> vehicles = vehicleRepository.findByMilesBetween(minMile, maxMile);

        return vehicles;
    }

    //by type
    public List<Vehicle> byType(VehicleType type){
        List<Vehicle> vehicles = vehicleRepository.findByVehicleType(type);

        return vehicles;
    }

    //by dealership
    public List<Vehicle> byDealership(Long id){
        List<Vehicle> vehicles = vehicleRepository.findByDealershipId(id);

        return vehicles;
    }
}
