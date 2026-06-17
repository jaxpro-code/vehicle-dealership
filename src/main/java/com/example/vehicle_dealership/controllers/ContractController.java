package com.example.vehicle_dealership.controllers;

import com.example.vehicle_dealership.entities.Contract;
import com.example.vehicle_dealership.entities.enums.ContractType;
import com.example.vehicle_dealership.services.ContractService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {
    private ContractService contractService;

    @Autowired
    public ContractController(ContractService contractService){
        this.contractService = contractService;
    }

    //post create
    @PostMapping
    public ResponseEntity<Contract> createContract(@RequestBody @Valid Contract contract){
        Contract newContact = this.contractService.create(contract);

        return new ResponseEntity<>(newContact, HttpStatus.CREATED);
    }

    //read
    @GetMapping
    public ResponseEntity<List<Contract>> getAllContract(){
        List<Contract> contracts = this.contractService.read();

        return new ResponseEntity<>(contracts,HttpStatus.OK);
    }

    //update
    @PutMapping("/{id}")
    public ResponseEntity<Contract> updateContract(@RequestBody @Valid Contract contract,@PathVariable Long id){
        Contract updatedContract = this.contractService.update(id, contract);

        if(updatedContract == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(updatedContract,HttpStatus.OK);
        }
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContract(@PathVariable Long id){
        Boolean deleteSuccessful = this.contractService.delete(id);

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
    public ResponseEntity<Contract> getContractById(@PathVariable Long id){
        Optional<Contract> contracts = this.contractService.byId(id);

        if(contracts.isPresent()){
            return new ResponseEntity<>(contracts.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    //by contract type
    @GetMapping("/by-type")
    public ResponseEntity<List<Contract>> getContractByType (@RequestParam (value = "type") ContractType type){
        List<Contract> contracts = this.contractService.byType(type);

        if(contracts.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(contracts, HttpStatus.OK);
        }
    }
    // by vehicle vin
    //not sure how to code this, im intentionally leaving this method bare
}
