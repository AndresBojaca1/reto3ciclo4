/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asdetiro.reto3.repository.crud;

import com.asdetiro.reto3.model.Order;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 *
 * @author andre
 */
public interface OrderCrudRepository extends MongoRepository<Order, Integer>{
    
    //Retorna las ordenes de pedido que coincidan con la zona recibida como parametro
    @Query("{'salesMan.zone' : ?0}")
    List<Order> findByZone(final String zone);
    
    //Retorna las ordenes por estado
    @Query("{status : ?0}")
    List<Order> findByStatus(final String status);
    
    //Permite organizar las ID de mayor a menor 
    Optional<Order> findTopByOrderByIdDesc();
}
