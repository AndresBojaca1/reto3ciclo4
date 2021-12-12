/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asdetiro.reto3.service;

import com.asdetiro.reto3.model.Accessory;
import com.asdetiro.reto3.repository.AccessoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author andre
 */
@Service
public class AccessoryService {
    @Autowired
    private AccessoryRepository accessoryRepository;
        
    public List<Accessory> getAll() {
        return accessoryRepository.getAll();
    }
    
    public Optional<Accessory> getAccessory(String reference) {
        return accessoryRepository.getAccessory(reference);
    }
    
    public Accessory create(Accessory accessory) {
        if (accessory.getReference() == null) {
            return accessory;
        } else {
            return accessoryRepository.create(accessory);
        }
    }
    
    public Accessory update(Accessory accessory) {

        if (accessory.getReference() != null) {
            Optional<Accessory> accessoryDb = accessoryRepository.getAccessory(accessory.getReference());
            if (!accessoryDb.isEmpty()) {
                if (accessory.getBrand() != null) {
                    accessoryDb.get().setBrand(accessory.getBrand());
                }
                if (accessory.getCategory() != null) {
                    accessoryDb.get().setCategory(accessory.getCategory());
                }
                if (accessory.getMaterial() != null) {
                    accessoryDb.get().setMaterial(accessory.getMaterial());
                }
                if (accessory.getDescription() != null) {
                    accessoryDb.get().setDescription(accessory.getDescription());
                }
                if (accessory.getPrice() != 0.0) {
                    accessoryDb.get().setPrice(accessory.getPrice());
                }
                if (accessory.getQuantity() != 0) {
                    accessoryDb.get().setQuantity(accessory.getQuantity());
                }
                if (accessory.getPhotography() != null) {
                    accessoryDb.get().setPhotography(accessory.getPhotography());
                }
                accessoryDb.get().setAvailability(accessory.isAvailability());
                accessoryRepository.update(accessoryDb.get());
                return accessoryDb.get();
            } else {
                return accessory;
            }
        } else {
            return accessory;
        }
    }
    
    public boolean delete(String reference) {
        Boolean aBoolean = getAccessory(reference).map(accesory -> {
            accessoryRepository.delete(accesory);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
