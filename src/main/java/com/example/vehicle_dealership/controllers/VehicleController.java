package com.example.vehicle_dealership.controllers;

import com.example.vehicle_dealership.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    private VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    //post create vehicle
    //get all vehicles
    //put update vehicle
    //delete by id
    //get by id
    //get by price
    //get by make
    //get by model
    //get by year
    //get by color
    //get by miles
    //get by type
    //get by dealership id
}
