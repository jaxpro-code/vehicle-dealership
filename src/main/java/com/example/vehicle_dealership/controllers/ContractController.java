package com.example.vehicle_dealership.controllers;

import com.example.vehicle_dealership.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {
    private ContractService contractService;

    @Autowired
    public ContractController(ContractService contractService){
        this.contractService = contractService;
    }

    //create
    //read
    //update
    //delete
    //by id
    //by contract type
    // by vehicle vin
}
