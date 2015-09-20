/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.dao;

import com.electrical_installations.model.entity.User;
import java.util.List;

/**
 * Intefaz para el acoplamiento del Usuario
 * @author Ing. Richard David
 * @version 1
 * @since 2015-07-25
 */
public interface UserDAO {

    /**
     * Crear usuario.
     * @param user
     * @return Retorna un valor booleano 
     */
    public boolean insert(User user);

    /**
     * Modificar datos básicos de usuario
     * @param user
     * @return Retorna un valor booleano
     */
    public boolean update(User user);
    
    /**
     * Modificar datos de seguridad de Usuario
     * @param user
     * @return Retorna un booleano
     */
    public boolean update_user(User user);

    /**
     * Eliminar un Usuario
     * @param user
     * @return Retorna un booleano
     */
    public boolean delete(User user);

    /**
     * Encontrar un Usuario
     * @param user
     * @return Retorna un objeto User
     */
    public User find(User user);
    
    /**
     * Listar todos los usuarios
     * @return Retorna una lista de usuario
     */
    public List<User> find_users();
    
    /**
     * Encontrar usuarios filtrados por nombre
     * @param user
     * @return Retorna una lista de usuarios
     */
    public List<User> filter_by_name(User user);

    /**
     * Encontrar usuarios filtrados por apellido
     * @param user
     * @return Retorna una lista de usuarios
     */
    public List<User> filter_by_lastName(User user);
    
    /**
     * Encontrar usuarios por nombre de usuario
     * @param user
     * @return Retorna una lista de usuarios
     */
    public List<User> filter_by_userName(User user);
    
    /**
     * Inicio de sesión
     * @param user
     * @return Retorna un objeto User
     */
    public User login(User user);
    
    /**
     * Validar existencia de un usuario.
     * @param user
     * @return Retorna un entero
     */
    public int validate_existence(User user);

    /**
     * Validar nombre de usuario
     * @param user
     * @return Retorna un entero
     */
    public User validate_username(User user);

    /**
     * Validar que un usuario sea el mismo
     * @param user
     * @return Retorna un objeto String
     */
    public String validate_user(User user);
    
    /**
     * Validar clave de usuario
     * @param user
     * @return Retorna un entero
     */
    public int validate_key(User user);
    
}
