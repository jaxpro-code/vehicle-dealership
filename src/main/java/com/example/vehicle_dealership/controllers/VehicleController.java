package com.example.vehicle_dealership.controllers;

import com.example.vehicle_dealership.entities.Vehicle;
import com.example.vehicle_dealership.entities.enums.VehicleType;
import com.example.vehicle_dealership.services.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    private VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    //post create vehicle
    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@RequestBody @Valid Vehicle vehicle){
        Vehicle newVehicle = this.vehicleService.create(vehicle);

        return new ResponseEntity<>(newVehicle, HttpStatus.CREATED);
    }

    //get all vehicles
    //confirmed
    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles(){
        List<Vehicle> vehicles = this.vehicleService.read();

        return new ResponseEntity<>(vehicles,HttpStatus.OK);
    }

    //put update vehicle
    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@RequestBody @Valid Vehicle vehicle, @PathVariable Long id){
        Vehicle updatedVehicle = this.vehicleService.update(id,vehicle);

        if(updatedVehicle == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(updatedVehicle, HttpStatus.OK);
        }
    }

    //delete by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id){
        Boolean deleteSuccessful = this.vehicleService.delete(id);

        if(!deleteSuccessful){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            //successful deletes do not return 200, they return 204 no content
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    //get by id
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id){
        Optional<Vehicle> vehicle = this.vehicleService.byId(id);

        //either there is a vehicle with this is or there is not
        if(vehicle.isPresent()){
            return new ResponseEntity<>(vehicle.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    //get by price
    @GetMapping("/price-range")
    public ResponseEntity<List<Vehicle>> getVehicleByPrice(@RequestParam( value = "minPrice",required = true) Double minPrice, @RequestParam(value = "maxPrice") Double maxPrice){
        List<Vehicle> vehicle = this.vehicleService.byPrice(minPrice, maxPrice);

        if(vehicle.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(vehicle,HttpStatus.OK);
        }
    }

    //get by make
    @GetMapping("/find-make")
    public ResponseEntity<List<Vehicle>> getVehicleByMake (@RequestParam (value = "make")String make){
        List<Vehicle> vehicle = this.vehicleService.byMake(make);

        if(vehicle.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(vehicle,HttpStatus.OK);
        }

    }

    //get by model
    @GetMapping("/find-model")
    public ResponseEntity<List<Vehicle>> getVehicleByModel (@RequestParam (value = "model")String model){
        List<Vehicle> vehicle = this.vehicleService.byModel(model);

        if(vehicle.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(vehicle,HttpStatus.OK);
        }

    }
    //get by year
    @GetMapping("/year-range")
    public ResponseEntity<List<Vehicle>> getVehicleByYear(@RequestParam( value = "minYear",required = true) Integer minYear, @RequestParam(value = "maxYear") Integer maxYear){
        List<Vehicle> vehicle = this.vehicleService.byYear(minYear, maxYear);

        if(vehicle.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(vehicle,HttpStatus.OK);
        }
    }

    //get by color
    @GetMapping("/find-color")
    public ResponseEntity<List<Vehicle>> getVehicleByColor (@RequestParam (value = "color")String color){
        List<Vehicle> vehicle = this.vehicleService.byColor(color);

        if(vehicle.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(vehicle,HttpStatus.OK);
        }

    }

    //get by miles
    @GetMapping("/mile-range")
    public ResponseEntity<List<Vehicle>> getVehicleByMiles(@RequestParam( value = "minMile",required = true) Integer minMile, @RequestParam(value = "maxMile") Integer maxMile){
        List<Vehicle> vehicle = this.vehicleService.byMiles(minMile, maxMile);

        if(vehicle.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(vehicle,HttpStatus.OK);
        }
    }

    //get by type
    @GetMapping("/vehicle-type")
    public ResponseEntity<List<Vehicle>> getVehicleByType (@RequestParam (value = "vehicleType")VehicleType vehicleType){
        List<Vehicle> vehicle = this.vehicleService.byType(vehicleType);

        if(vehicle.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(vehicle,HttpStatus.OK);
        }

    }

    //get by dealership id
    @GetMapping("/dealership/{id}")
    public ResponseEntity<List<Vehicle>> getVehicleByDealershipId(@PathVariable Long id){
        List<Vehicle> vehicle = this.vehicleService.byDealership(id);

        if(vehicle.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(vehicle,HttpStatus.OK);
        }
    }


}
