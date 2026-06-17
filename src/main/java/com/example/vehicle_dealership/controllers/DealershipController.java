package com.example.vehicle_dealership.controllers;

import com.example.vehicle_dealership.entities.Dealerships;
import com.example.vehicle_dealership.entities.Vehicle;
import com.example.vehicle_dealership.services.DealershipService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dealerships")
public class DealershipController {
    private DealershipService dealershipService;

    @Autowired
    public DealershipController(DealershipService dealershipService){
        this.dealershipService = dealershipService;
    }

    //post create
    @PostMapping
    public ResponseEntity<Dealerships> createDealership(@RequestBody @Valid Dealerships dealerships){
        Dealerships newDealership = this.dealershipService.create(dealerships);

        return new ResponseEntity<>(newDealership, HttpStatus.CREATED);
    }

    //read
    @GetMapping
    public ResponseEntity<List<Dealerships>> getAllDealerships(){
        List<Dealerships> dealerships = this.dealershipService.read();

        return new ResponseEntity<>(dealerships,HttpStatus.OK);
    }

    //update
    @PutMapping("/{id}")
    public ResponseEntity<Dealerships> updateDealership(@RequestBody @Valid Dealerships dealerships,@PathVariable Long id){
        Dealerships updatedDealership = this.dealershipService.update(id, dealerships);

        if(updatedDealership == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(updatedDealership,HttpStatus.OK);
        }
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDealership(@PathVariable Long id){
        Boolean deleteSuccessful = this.dealershipService.delete(id);

        if(!deleteSuccessful){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            //successful deletes do not return 200, they return 204 no content
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    //by id
    @GetMapping("/{id}")
    public ResponseEntity<Dealerships> getDealershipById(@PathVariable Long id){
        Optional<Dealerships> dealerships = this.dealershipService.byId(id);

        if(dealerships.isPresent()){
            return new ResponseEntity<>(dealerships.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    //by name
    @GetMapping("/find")
    public ResponseEntity<Dealerships> getDealershipByName (@RequestParam (value = "name")String name){
        Optional<Dealerships> dealerships = this.dealershipService.byName(name);

        if(dealerships.isPresent()){
            return new ResponseEntity<>(dealerships.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
