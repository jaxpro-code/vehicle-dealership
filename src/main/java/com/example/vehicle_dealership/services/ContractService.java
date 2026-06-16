package com.example.vehicle_dealership.services;

import com.example.vehicle_dealership.entities.Contract;
import com.example.vehicle_dealership.entities.Dealerships;
import com.example.vehicle_dealership.entities.enums.ContractType;
import com.example.vehicle_dealership.repositories.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ContractService {
    @Autowired
    private ContractRepository contractRepository;

    //create
    public Contract create(Contract contract){
        return contractRepository.save(contract);
    }

    //read
    public List<Contract> read(){
        return contractRepository.findAll();
    }

    //update
    public Contract update(Long id, Contract contract){
        Optional<Contract> updateContract = contractRepository.findById(id);

        if(updateContract.isEmpty()){
            return null;
        }

        Contract contactToUpdate = updateContract.get();

        contactToUpdate.setDate(contract.getDate());
        contactToUpdate.setVehicle(contract.getVehicle());
        contactToUpdate.setType(contract.getType());
        contactToUpdate.setCustomerName(contract.getCustomerName());
        contactToUpdate.setCustomerEmail(contract.getCustomerEmail());
        contactToUpdate.setCustomerNumber(contract.getCustomerNumber());

        contractRepository.save(contactToUpdate);

        return contactToUpdate;
    }

    //delete
    public boolean delete(Long id){
        Optional<Contract> contractToDelete = contractRepository.findById(id);

        if(contractToDelete.isEmpty()){
            return false;
        }

        contractRepository.delete(contractToDelete.get());
        return true;
    }

    //by id
    public Optional<Contract> byId(Long id){
        var contract = contractRepository.findById(id);

        return contract;
    }

    //by contract type
    public List<Contract> byType(ContractType type){
        List<Contract> contracts = contractRepository.findByTypeIgnoreCase(type);

        return contracts;
    }

    //by vehicle vin
    public List<Contract> byVehicleVin(String vin){
        return contractRepository.findByVehicleVinContainingIgnoreCase(vin);
    }
}
