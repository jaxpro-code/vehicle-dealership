package com.example.vehicle_dealership.services;

import com.example.vehicle_dealership.entities.Dealerships;
import com.example.vehicle_dealership.repositories.DealershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DealershipService {
    @Autowired
    private DealershipRepository dealershipRepository;

    //create
    public Dealerships create(Dealerships dealerships){
        return dealershipRepository.save(dealerships);
    }

    //read
    public List<Dealerships> read(){
        return dealershipRepository.findAll();
    }

    //update
    public Dealerships update(Long id, Dealerships dealerships){
        Optional<Dealerships> updatedealership = dealershipRepository.findById(id);

        if(updatedealership.isEmpty()){
            return null;
        }

        Dealerships dealershiptoUpdate = updatedealership.get();

        dealershiptoUpdate.setName(dealerships.getName());
        dealershiptoUpdate.setAddress(dealerships.getAddress());
        dealershiptoUpdate.setPhone(dealerships.getPhone());

        dealershipRepository.save(dealershiptoUpdate);

        return dealershiptoUpdate;
    }

    //delete
    public boolean delete(Long id){
        Optional<Dealerships> dealershipsToDelete = dealershipRepository.findById(id);

        if(dealershipsToDelete.isEmpty()){
            return false;
        }

        dealershipRepository.delete(dealershipsToDelete.get());
        return true;
    }

    //by id
    public Optional<Dealerships> byId(Long id){
        var dealership = dealershipRepository.findById(id);

        return dealership;
    }

    //by name
    public Optional<Dealerships> byName(String name){
        return dealershipRepository.findByNameIgnoreCase(name);
    }

}
