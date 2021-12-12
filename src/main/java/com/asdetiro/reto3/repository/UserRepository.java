/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asdetiro.reto3.repository;

import com.asdetiro.reto3.model.User;
import com.asdetiro.reto3.repository.crud.UserCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author andre
 */
@Repository
public class UserRepository {
    
     @Autowired
    private UserCrudRepository crudInterface;

    public List<User> listAll() {
        return crudInterface.findAll();
    }

    public Optional<User> getUser(int id) {
        return crudInterface.findById(id);
    }

    public User create(User user) {
        return crudInterface.save(user);
    }

    public void update(User user) {
        crudInterface.save(user);
    }
    
    public void delete(User user) {
        crudInterface.delete(user);
    }

    public boolean emailExist(String email) {
        Optional<User> usuario = crudInterface.findByEmail(email);

        return !usuario.isEmpty();
    }

    public Optional<User> autenticateUser(String email, String password) {
        return crudInterface.findByEmailAndPassword(email, password);
    }
    
     public Optional<User> lastUserId(){
        return crudInterface.findTopByOrderByIdDesc();
    }
     
    public List<User> listBirthtDayMonth(String month){
        return crudInterface.findByMonthBirthtDay(month);
    }
    
//    //MI CODIGO
//    @Autowired
//    private UserCrudRepository userCrudRepository;
//    
//    public List<User> getAll(){
//        return (List<User>) userCrudRepository.findAll();
//    }
//    
//    public Optional<User> getUser(int id){
//        return userCrudRepository.findById(id);
//    }
//    
//    public User create(User user){
//        return userCrudRepository.save(user);
//    }
//    
//    public void update(User user) {
//        userCrudRepository.save(user);
//    }
//    
//    public void delete(User user) {
//        userCrudRepository.delete(user);
//    }
//    
//    public boolean emailExists(String email) {
//        Optional<User> usuario = userCrudRepository.findByEmail(email);
//        return !usuario.isEmpty();
//    }
//    
//    public Optional<User> authenticateUser(String email, String password) {
//        return userCrudRepository.findByEmailAndPassword(email, password);
//    }
//    
//    public Optional<User> lastUserId() {
//        return userCrudRepository.findTopByOrderByIdDesc();
//    }
//    
//    public List<User> listBirthDayMonth(String month) {
//        return userCrudRepository.findByMonthBirthday(month);
//    }
}
