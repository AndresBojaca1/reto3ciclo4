/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asdetiro.reto3.repository.crud;

import com.asdetiro.reto3.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author andre
 */
public interface UserCrudRepository extends MongoRepository<User, Integer> {
    //aqui defino consultas adicionales que no estan predeterminadas en la Interfaz "MongoRepository"
    
    public Optional <User> findByEmail(String email);
    
    public Optional<User> findByEmailAndPassword(String email, String password);
    
    //Para seleccionar el usuario con el id maximo
    //db.users.find().limit(1).sort({$natural:-1}) en mongo db
    Optional<User> findTopByOrderByIdDesc();
    
    //Reto 5: Listado de cumpleaños del mes
    List<User> findByMonthBirthtDay(String month);
    
    
    
    
//    //MI CODIGO
//    //Permite hallar el usuario de un correo
//    Optional<User> findByEmail(String email);
//    //Permite validar email y password
//    Optional<User> findByEmailAndPassword(String email, String password);
//    //Permite generar de orden descendente una lsita de ID de mayor a menor
//    Optional<User> findTopByOrderByIdDesc();
//    //Para el reto 5: Permite listar los cumpleaños del mes
//    List<User> findByMonthBirthday(String month);
}
