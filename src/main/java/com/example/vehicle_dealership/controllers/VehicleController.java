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
    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles(@RequestParam( value = "minPrice",required = false) Double minPrice, @RequestParam(value = "maxPrice",required = false) Double maxPrice,
                                                        @RequestParam (value = "make",required = false)String make,@RequestParam (value = "model", required = false)String model,
                                                        @RequestParam( value = "minYear",required = false) Integer minYear, @RequestParam(value = "maxYear",required = false) Integer maxYear,
                                                        @RequestParam (value = "color",required = false)String color,@RequestParam( value = "minMile",required = false) Integer minMile,
                                                        @RequestParam(value = "maxMile",required = false) Integer maxMile, @RequestParam (value = "vehicleType",required = false)String vehicleType,
                                                        @RequestParam (value = "dealershipid",required = false) Long dealershipId){
        //region minPrice or maxPrice
        if(minPrice != null || maxPrice != null){
            if(minPrice == null){
                minPrice = 0.0;
            }
            if(maxPrice == null){
                maxPrice = Double.MAX_VALUE;
            }
            List<Vehicle> vehicle = this.vehicleService.byPrice(minPrice, maxPrice);
            return new ResponseEntity<>(vehicle,HttpStatus.OK);
        }
        //endregion
        //region byMake
        if(make != null){
            List<Vehicle> vehicle = this.vehicleService.byMake(make);
            return new ResponseEntity<>(vehicle,HttpStatus.OK);
        }
        //endregion
        //region byModel
        if(model != null){
            List<Vehicle> vehicle = this.vehicleService.byModel(model);
            return new ResponseEntity<>(vehicle,HttpStatus.OK);
        }
        //endregion
        //region byYear
        if(minYear != null || maxYear != null){
            if(minYear == null){
                minYear = 1886;
            }
            if(maxYear == null){
                maxYear = 2028;
            }
            List<Vehicle> vehicle = this.vehicleService.byYear(minYear, maxYear);
            return new ResponseEntity<>(vehicle,HttpStatus.OK);
        }
        //endregion
        //region byColor
        if(color != null){
            List<Vehicle> vehicle = this.vehicleService.byColor(color);
            return new ResponseEntity<>(vehicle,HttpStatus.OK);
        }
        //endregion
        //region byMiles
        if(minMile != null || maxMile != null){
            if(minMile == null){
                minMile = 0;
            }
            if(maxMile == null){
                maxMile = Integer.MAX_VALUE;
            }
            List<Vehicle> vehicle = this.vehicleService.byMiles(minMile, maxMile);
            return new ResponseEntity<>(vehicle,HttpStatus.OK);
        }
        //endregion
        //region byType
        if(vehicleType != null){
            VehicleType typeEnum;
            try {
                typeEnum = VehicleType.valueOf(vehicleType.toString().toUpperCase());
            } catch (IllegalArgumentException e) {
                List<Vehicle> allVehicles = this.vehicleService.read();
                return new ResponseEntity<>(allVehicles, HttpStatus.OK);
            }
            List<Vehicle> vehicle = this.vehicleService.byType(typeEnum);
            return new ResponseEntity<>(vehicle,HttpStatus.OK);
        }
        //endregion
        //region ByDealershipId
        if(dealershipId != null){
            List<Vehicle> vehicle = this.vehicleService.byDealership(dealershipId);
            return new ResponseEntity<>(vehicle,HttpStatus.OK);
        }
        //endregion
        //region AllVehicles
        else{
            List<Vehicle> vehicles = this.vehicleService.read();
            return new ResponseEntity<>(vehicles,HttpStatus.OK);
        }
        //endregion
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

}
