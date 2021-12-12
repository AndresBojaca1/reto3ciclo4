/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asdetiro.reto3.service;

import com.asdetiro.reto3.model.User;
import com.asdetiro.reto3.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author andre
 */
@Service
public class UserService {
    
    @Autowired
    private UserRepository repositorio;

    public Optional<User> getUser(int id) {
        return repositorio.getUser(id);
    }

    public User create(User user) {
        //obtiene el maximo id existente en la coleccion
        Optional<User> userIdMaximo = repositorio.lastUserId();
        
        //si el id del Usaurio que se recibe como parametro es nulo, entonces valida el maximo id existente en base de datos
        if (user.getId() == null) {
            //valida el maximo id generado, si no hay ninguno aun el primer id sera 1
            if (userIdMaximo.isEmpty())
                user.setId(1);
            //si retorna informacion suma 1 al maximo id existente y lo asigna como el codigo del usuario
            else
                user.setId(userIdMaximo.get().getId() + 1);
        }
        
        Optional<User> e = repositorio.getUser(user.getId());
        if (e.isEmpty()) {
            if (emailExist(user.getEmail())==false){
                return repositorio.create(user);
            }else{
                return user;
            }
        }else{
            return user;
        }
    }
    
    public User update(User user) {

        if (user.getId() != null) {
            Optional<User> userDb = repositorio.getUser(user.getId());
            if (!userDb.isEmpty()) {
                if (user.getIdentification() != null) {
                    userDb.get().setIdentification(user.getIdentification());
                }
                if (user.getName() != null) {
                    userDb.get().setName(user.getName());
                }
                if (user.getAddress() != null) {
                    userDb.get().setAddress(user.getAddress());
                }
                if (user.getCellPhone() != null) {
                    userDb.get().setCellPhone(user.getCellPhone());
                }
                if (user.getEmail() != null) {
                    userDb.get().setEmail(user.getEmail());
                }
                if (user.getPassword() != null) {
                    userDb.get().setPassword(user.getPassword());
                }
                if (user.getZone() != null) {
                    userDb.get().setZone(user.getZone());
                }

                repositorio.update(userDb.get());
                return userDb.get();
            } else {
                return user;
            }
        } else {
            return user;
        }
    }
    
    public boolean delete(int userId) {
        Boolean aBoolean = getUser(userId).map(user -> {
            repositorio.delete(user);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
    public List<User> listAll() {
        return repositorio.listAll();
    }

    public boolean emailExist(String email) {
        return repositorio.emailExist(email);
    }

    public User autenticateUser(String email, String password) {
        Optional<User> usuario = repositorio.autenticateUser(email, password);

        if (usuario.isEmpty()) {
            return new User();
        } else {
            return usuario.get();
        }
    }
    
    
    public List<User> listBirthtDayMonth(String month){
        return repositorio.listBirthtDayMonth(month);
    }
    

//    //MI CODIGO
//    @Autowired
//    private UserRepository userRepository;
//
//    public List<User> getAll() {
//        return userRepository.getAll();
//    }
//
//    public Optional<User> getUser(int id) {
//        return userRepository.getUser(id);
//    }
//
//    public boolean emailExists(String email) {
//        return userRepository.emailExists(email);
//    }
//
//    public User create(User user) {
//        Optional<User> maxIdUser = userRepository.lastUserId();
//
//        //Si la id del usuario no existe
//        if (user.getId() == null) {
//            //Asignele 1 (para iniciar el conteo de ids desde dicho valor)
//            user.setId(1);
//        } else {
//            //Si la id del usuario si existe, obtener la maxima ID y a esa sumarle 1 para continuar con el autoincremental
//            user.setId(maxIdUser.get().getId() + 1);
//        }
//
//        //Obtengo el usuario del repositorio a partir del get de una Id
//        Optional<User> usuario = userRepository.getUser(user.getId());
//
//        //¿No Existe el usuario?
//        if (usuario.isEmpty()) {
//            //si no existe el usuario entonces valideme si el correo del usuario ingresado existe
//            if (emailExists(user.getEmail()) == false) {
//                //si el correo del usuario no existe entonces creeme el nuevo usuario
//                return userRepository.create(user);
//            } else {
//                //si el correo del usuario ya existe entonces retorne el usuario (no se guarda nada)
//                return user;
//            }
//        } else {
//            //si el usuario ya existe entonces retorne el usuario (no guarda nada)
//            return user;
//        }
//    }
//
//    public User update(User user) {
//        //Si la id del usuario NO es nula, es decir este existe en la base de datos
//        if (user.getId() != null) {
//            //Obtengame toda la informacion del usuario a partir de la id ingresada
//            Optional<User> userDb = userRepository.getUser(user.getId());
//            //Si la informacion del usuario NO esta vacia entonces:
//            if (!userDb.isEmpty()) {
//                //Si la identificacion del usuario NO es nula
//                if (user.getIdentification() != null) {
//                    //Setee la identificacion a partir de la identificacion ingresada
//                    userDb.get().setIdentification(user.getIdentification());
//                }
//                //Si el nombre del usuario NO es nulo
//                if (user.getName() != null) {
//                    //Setee el nombre a partir del nombre ingresado
//                    userDb.get().setName(user.getName());
//                }
//                //Si la direccion del usuario NO es nula
//                if (user.getAddress() != null) {
//                    //Setee la direccion a partir de la direccion ingresada
//                    userDb.get().setAddress(user.getAddress());
//                }
//                //Si el numero de celular del usuario NO es nulo
//                if (user.getCellPhone() != null) {
//                    //Setee el numero de celular a partir de el numero de celular ingresado
//                    userDb.get().setCellPhone(user.getCellPhone());
//                }
//                //Si el correo del usuario NO es nula
//                if (user.getEmail() != null) {
//                    //Setee el correo a partir del correo ingresado
//                    userDb.get().setEmail(user.getEmail());
//                }
//                //Si la contraseña del usuario NO es nula
//                if (user.getPassword() != null) {
//                    //Setee la contraseña a partir de la contraseña ingresada
//                    userDb.get().setPassword(user.getPassword());
//                }
//                //Si la Zona del usuario NO es nula
//                if (user.getZone() != null) {
//                    //Setee la Zona a partir de la Zona ingresada
//                    userDb.get().setZone(user.getZone());
//                }
//                //Cuando se valide todo esto entonces actualice el usuario a partir de lo que se seteo
//                userRepository.update(userDb.get());
//                //y retorneme el get del usuario actualizado
//                return userDb.get();
//            } else {
//                //Si la informacion del usuario esta vacia entonces retorne el usuario (no actualiza nada)
//                return user;
//            }
//        } else {
//            //Si el usuario NO existe en la base de datos retorne el usuario (no se actualiza nada)
//            return user;
//        }
//    }
//    
//    public boolean delete(int userId) {
//        Boolean aBoolean = getUser(userId).map(user -> {
//            userRepository.delete(user);
//            return true;
//        }).orElse(false);
//        return aBoolean;
//    }
//    
//    public User authenticateUser(String email, String password){
//        //Genereme una autenticacion de usuario
//        Optional<User> usuario = userRepository.authenticateUser(email, password);
//        //Si lo que obtengo del usuario es vacio
//        if (usuario.isEmpty()) {
//            //entonces genereme un nuevo usuario con toda la informacion nula
//            return new User();
//        } else {
//            //si obtengo un usuario con datos y estos coinciden entonces retorneme los datos de ese usuario
//            return usuario.get();
//        }
//    }

}
