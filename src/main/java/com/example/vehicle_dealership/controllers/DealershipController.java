package com.example.vehicle_dealership.controllers;

import com.example.vehicle_dealership.services.DealershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dealerships")
public class DealershipController {
    private DealershipService dealershipService;

    @Autowired
    public DealershipController(DealershipService dealershipService){
        this.dealershipService = dealershipService;
    }

    //create
    //read
    //update
    //delete
    //by id
    //by name
}
