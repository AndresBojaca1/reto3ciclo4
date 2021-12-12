/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asdetiro.reto3.service;

import com.asdetiro.reto3.model.Order;
import com.asdetiro.reto3.repository.OrderRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author andre
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAll() {
        return orderRepository.getAll();
    }

    public Optional<Order> getOrder(int id) {
        return orderRepository.getOrder(id);
    }

    public Order create(Order order) {
        Optional<Order> maxIdOrder = orderRepository.lastUserId();
        if (order.getId() == null) {
            if (maxIdOrder.isEmpty()) {
                order.setId(1);
            } else {
                order.setId(maxIdOrder.get().getId() + 1);
            }
        }
        Optional<Order> orden = orderRepository.getOrder(order.getId());
        if (orden.isEmpty()) {
            return orderRepository.create(order);
        } else {
            return order;
        }
    }
    
    public Order update(Order order) {
        if (order.getId() != null) {
            Optional<Order> orderDb = orderRepository.getOrder(order.getId());
            if(!orderDb.isEmpty()) {
                if (order.getStatus() != null) {
                    orderDb.get().setStatus(order.getStatus());
                }
                orderRepository.update(orderDb.get());
                return orderDb.get();
            } else {
                return order;
            }
        } else {
            return order;
        }
    }
    
    public boolean delete(int id) {
        Boolean aBoolean = getOrder(id).map(order -> {
            orderRepository.delete(order);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
    //Ordenes de pedido que se encuentran asociadas a los asesores de X zona.
    public List<Order> findByZone(String zone) {
        return orderRepository.findByZone(zone);
    }
    
    //Reto 4 Listar ordenes de pedido de un asesor
    public List<Order> ordersSalesManByID(Integer id) {
       return orderRepository.ordersSalesManByID(id);
    }
    
    //Reto 4 Listar ordenes de pedido por x estado y asesor
    public List<Order> ordersSalesManByState(String state, Integer id) {
        return orderRepository.ordersSalesManByState(state,id);
    }
    
    //Reto 4: Ordenes de un asesor x Fecha
    public List<Order> ordersSalesManByDate(String dateStr, Integer id){
        return orderRepository.ordersSalesManByDate(dateStr, id);
    }
}
