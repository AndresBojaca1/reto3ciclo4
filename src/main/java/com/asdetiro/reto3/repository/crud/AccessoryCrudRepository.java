/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asdetiro.reto3.repository.crud;

import com.asdetiro.reto3.model.Accessory;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author andre
 */
public interface AccessoryCrudRepository extends MongoRepository<Accessory, String> {
    
}
