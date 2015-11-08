/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.service;

import com.electrical_installations.configuration.Messages;
import com.electrical_installations.configuration.MessagesStructure;
import com.electrical_installations.model.entity.User;
import com.electrical_installations.model.implementation.UserImplDAO;
import java.util.List;

/**
 * Clase que sirve la capa de servicios para Usuario, los servicios se comunican con la capa Modelo para acceder a los datos.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-07-25
 */
public class ServiceUser {
    
    //Objetos, variables y constantes
    private static final UserImplDAO userImplDAO = UserImplDAO.getInstance();
    private static final Messages messages = Messages.getInstance();
    
    /**
     * Constructor de la clase
     */
    public ServiceUser(){
    }
    
    /**
     * Servicio para la creación de usuarios, recibe un objeto User.
     * @param user 
     * @return Retorna true si el proceso de registro a finalizado con exito 
     */
    public static boolean create_user(User user){
        if (MessagesStructure.ConfirmationMessage(MessagesStructure.format(200, messages.getProperty(Messages.CONFIRM), MessagesStructure.justify)) == 0){        
            if (userImplDAO.validate_existence(user) > 0){
                MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.USER_FOUND), MessagesStructure.justify));
                return false;
            } else if (userImplDAO.validate_username(user) != null){
                MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.USER_USERNAME_EXISTS), MessagesStructure.justify));
                return false;
            } else {
                if (userImplDAO.insert(user)){
                    MessagesStructure.InformationMessage(MessagesStructure.format(200, messages.getProperty(Messages.USER_REGISTERED), MessagesStructure.justify));
                    return true;
                } else {
                    MessagesStructure.ErrorMessage(MessagesStructure.format(200, messages.getProperty(Messages.OPERATION_ERROR), MessagesStructure.justify));
                    return false;
                }
            }
        }
        return false;
    }//Fin del servicio
    
    /**
     * Servio para la modificación de usuarios, recibe un objeto User.
     * @param user
     * @return Retorna true si el proceso de modificación a finalizado con exito
     */
    public static boolean update(User user){
        if (MessagesStructure.ConfirmationMessage(MessagesStructure.format(200, messages.getProperty(Messages.CONFIRM), MessagesStructure.justify)) == 0){  
            if (userImplDAO.validate_existence(user) == 0){
                MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.USER_NO_FOUND), MessagesStructure.justify));
                return false;
            } else {
                if (userImplDAO.update(user)){
                    MessagesStructure.InformationMessage(MessagesStructure.format(200, messages.getProperty(Messages.USER_MODIFIED), MessagesStructure.justify));
                    return true;
                } else {
                    MessagesStructure.ErrorMessage(MessagesStructure.format(200, messages.getProperty(Messages.OPERATION_ERROR), MessagesStructure.justify));
                    return false;
                }
            }
        }
        return false;
    }//Fin del servicio
    
    /**
     * Servio para modificar datos de seguridad de un usuario, recibe un objeto User.
     * @param user
     * @return Retorna true si el proceso de modificación a finalizado con exito
     */
    public static boolean update_user(User user){  
        User userFound = userImplDAO.validate_username(user);
        if (userImplDAO.validate_existence(user) == 0){
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.USER_NO_FOUND), MessagesStructure.justify));
            return false;
        } else if (userFound != null && !(userFound.getNationality()+'-'+userFound.getDni()).equals(user.getNationality()+'-'+user.getDni())){
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.USER_USERNAME_EXISTS), MessagesStructure.justify));
            return false;
        } else {
            if (userImplDAO.update_user(user)){
                MessagesStructure.InformationMessage(MessagesStructure.format(200, messages.getProperty(Messages.USER_MODIFIED_SECURITY), MessagesStructure.justify));
                return true;
            } else {
                MessagesStructure.ErrorMessage(MessagesStructure.format(200, messages.getProperty(Messages.OPERATION_ERROR), MessagesStructure.justify));
                return false;
            }
        }
    }//Fin del servicio
    
    /**
     * Servicio para solicitar datos de todos los usuarios.
     * @return Retorna una lista de Usuarios
     */
    public static List<User> find_users(){
        return userImplDAO.find_users();
    }//Fin del servicio
    
    /**
     * Servicio para solicitar datos de un usuario, recibe un objeto User
     * @param user
     * @return 
     */
    public static User find_user(User user){
        User userFound = userImplDAO.find(user);
        if (userFound == null){
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.USER_NO_FOUND), MessagesStructure.justify));            
        } 
        return userFound;
    }//Fin del servicio
    
    /**
     * Servicio para consultar datos de usuarios filtrados por nombre, recibe un objeto User
     * @param user 
     * @return Retorna una lista de usuarios filtrada por nombre
     */
    public static List<User> filter_by_name(User user){
        return userImplDAO.filter_by_name(user);
    }//Fin del servicio   
    
    /**
     * Servicio para consultar datos de usuarios filtrados por apellido, recibe un objeto User
     * @param user 
     * @return Retorna una lista de usuarios filtrada por apellido
     */
    public static List<User> filter_by_lastName(User user){
        return userImplDAO.filter_by_lastName(user);
    }//Fin del servicio
    
    /**
     * Servicio para filtrar usuario por nombre de usuario, recibe un objeto user
     * @param user 
     * @return Retorna una lista de usuarios filtrada por nombre de usuario 
     */
    public static List<User> filter_by_userName(User user){
        return userImplDAO.filter_by_userName(user);
    }//Fin del servicio
    
    /**
     * Servicio para eliminar usuarios, recibe un objeto User
     * @param user 
     * @return Retorna true en caso de que la operación sea exitosa 
     */   
    public static boolean delete_user(User user){
        if (MessagesStructure.ConfirmationMessage(MessagesStructure.format(200, messages.getProperty(Messages.USER_CONFIRM_DELETED), MessagesStructure.justify)) == 0){  
            if (userImplDAO.validate_existence(user) == 0){
                MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.USER_NO_FOUND), MessagesStructure.justify));
                return false;
            } else {
                if (userImplDAO.delete(user)){
                    MessagesStructure.InformationMessage(MessagesStructure.format(200, messages.getProperty(Messages.USER_DELETED), MessagesStructure.justify));
                    return true;
                } 
            }
        }
        return false;
    }//Fin del servicio
    
    /**
     * Serivicio para iniciar sesión.
     * @param user
     * @return Retorna true si los datos de usuario son correctos
     */
    public static User login(User user){        
        User userFound = userImplDAO.login(user);
        if (userFound != null){
            return userFound;
        } else {
            MessagesStructure.ErrorMessage(MessagesStructure.format(100, messages.getProperty(Messages.USER_ACCESS_DENIED),"justify"));
            return null;
        }
    }//Fin del Servicio
    
    /**
     * Servicio para verificar clave de usuario.
     * @param user
     * @return Retorna true si la clave es correcta
     */
    public static boolean verify_key(User user){
        if (userImplDAO.validate_key(user) > 0){
            return true;
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.USER_PREVIOUS_WRONG_KEY),"justify"));
            return false;
        }
    }//Fin del Servicio
    
}
