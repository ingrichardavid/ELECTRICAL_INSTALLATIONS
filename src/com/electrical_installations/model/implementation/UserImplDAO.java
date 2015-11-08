/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.implementation;

import com.electrical_installations.configuration.Messages;
import com.electrical_installations.configuration.MessagesStructure;
import com.electrical_installations.model.DataBaseConnection;
import com.electrical_installations.model.dao.UserDAO;
import com.electrical_installations.model.entity.User;
import com.electrical_installations.model.query.UserQueries;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que encapsula la capa Modelo para acceder a los datos del usuario, implementa la interfaz UserDAO
 * @author Ing. Richard David
 * @version 1
 * @since 2015-07-20
 */
public class UserImplDAO implements UserDAO{
    
    //Objetos, variables y constantes de la clase.
    private static final DataBaseConnection connection = DataBaseConnection.getInstance();  
    private static final Messages messages = Messages.getInstance();  
    private static UserImplDAO instance;
    private PreparedStatement preparedStatement;
    private ResultSet result;
    private User userFound;
    private List<User> usersFound;

    /**
     * Constructor de la clase, es privado para cumplir con el patrón Singlenton.
     */
    private UserImplDAO() {
    }//Fin del Constructor
    
    /**
     * Este método permite obtener la instancia de la clase.
     * @return Retorna el objeto instance que es del mismo tipo que la clase.
     */
    public static synchronized UserImplDAO getInstance() {
        if (instance == null) {
            instance = new UserImplDAO();
        }
        return instance;
    }//Fin del método getConexion

    /**
     * Este método cierra la conexión con la base de datos, para que esto sea posible
     * se elimina la referencia en memoria para el objeto instance.
     */
    public void closeConnection() {
        instance = null;
    }//Fin del método

    /**
     * Método para insertar un Usuario
     * @param user
     * @return Retorna true en caso de que el procedimiento sea exitoso 
     */
    @Override
    public boolean insert(User user) {
        try {
            preparedStatement = connection.getConexion().prepareStatement(UserQueries.INSERT);
            preparedStatement.setString(1, user.getNationality());
            preparedStatement.setInt(2, user.getDni());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getAddress()); 
            preparedStatement.setString(6, user.getUserName());
            preparedStatement.setString(7, user.getPassword());           
            preparedStatement.setString(8, user.getPhone());
            preparedStatement.setBoolean(9, user.isStatus());
            if (preparedStatement.executeUpdate() > 0) return true;   
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return false;
    }//Fin del método insert

    /**
     * Método para modificar un Usuario
     * @param user
     * @return Retorna true en caso de que el procedimiento sea exitoso
     */
    @Override
    public boolean update(User user) {
        try {
            preparedStatement = connection.getConexion().prepareStatement(UserQueries.UPDATE);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getAddress());           
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setBoolean(5, user.isStatus());
            preparedStatement.setString(6, user.getNationality());
            preparedStatement.setInt(7, user.getDni());
            if (preparedStatement.executeUpdate() > 0) return true;   
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return false;
    }//Fin del método

    /**
     * Método para modificar Nombre y clave de Usuario
     * @param user
     * @return Retorna true en caso de que el procedimiento sea exitoso.
     */
    @Override
    public boolean update_user(User user){
        try {
            preparedStatement = connection.getConexion().prepareStatement(UserQueries.UPDATE_USER);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getNationality());
            preparedStatement.setInt(4, user.getDni());
            if (preparedStatement.executeUpdate() > 0) return true;   
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return false;        
    }//Fin del método
    
    /**
     * Método para eliminar un Usuario
     * @param user
     * @return Retorna true en caso de que el procedimiento sea exitoso
     */
    @Override
    public boolean delete(User user) {
        try {
            preparedStatement = connection.getConexion().prepareStatement(UserQueries.DELETE);
            preparedStatement.setString(1, user.getNationality());
            preparedStatement.setInt(2, user.getDni());
            if (preparedStatement.executeUpdate() > 0) return true;   
        } catch (SQLException e) {
            if (e.getSQLState().equals("23503")){
                MessagesStructure.ErrorMessage(MessagesStructure.format(200, messages.getProperty(Messages.USER_NOT_BE_REMOVED_ASSOCIATED_PROJECTS), MessagesStructure.justify));
                return false;
            } else {
                MessagesStructure.ErrorMessage(MessagesStructure.format(200, messages.getProperty(Messages.OPERATION_ERROR), MessagesStructure.justify));
                e.printStackTrace();
                return false;
            }
        } finally {
            connection.closeConnection();
        }
        return false;
    }//Fin del Método

    /**
     * Método para encontrar un Usuario
     * @param user
     * @return Retorna un objeto User
     */
    @Override
    public User find(User user) { 
        userFound = null;
        try {
            preparedStatement = connection.getConexion().prepareStatement(UserQueries.SELECT);
            preparedStatement.setString(1, user.getNationality());
            preparedStatement.setInt(2, user.getDni());
            result = preparedStatement.executeQuery();
            while(result.next()){
                userFound = new User(result.getString(1), result.getInt(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getString(7), result.getBoolean(8),result.getString(9));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return userFound;
    }//Fin del Método

    /**
     * Método para filtrar usuarios por nombres
     * @param user
     * @return Retorna un arreglo de usuarios
     */
    @Override
    public List<User> filter_by_name(User user) { 
        usersFound = new ArrayList<>();
        try {
            preparedStatement = connection.getConexion().prepareStatement(UserQueries.FILTER_BY_NAME);
            preparedStatement.setString(1, "%"+user.getName()+"%");
            result = preparedStatement.executeQuery();
            while(result.next()){
                usersFound.add(new User(result.getString(1), result.getString(2), result.getString(3), result.getString(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return usersFound;
    }//Fin del Método

    /**
     * Método para filtrar usuarios por apellidos
     * @param user
     * @return Retorna un arreglo de usuarios
     */
    @Override
    public List<User> filter_by_lastName(User user) { 
        usersFound = new ArrayList<>();
        try {
            preparedStatement = connection.getConexion().prepareStatement(UserQueries.FILTER_BY_LASTNAME);
            preparedStatement.setString(1, "%"+user.getLastName()+"%");
            result = preparedStatement.executeQuery();
            while(result.next()){
                usersFound.add(new User(result.getString(1), result.getString(2), result.getString(3), result.getString(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return usersFound;
    }//Fin del Método

    /**
     * Método para filtrar usuarios por nombre de usuarios
     * @param user
     * @return Retorna un arreglo de usuarios
     */
    @Override
    public List<User> filter_by_userName(User user) { 
        usersFound = new ArrayList<>();
        try {
            preparedStatement = connection.getConexion().prepareStatement(UserQueries.FILTER_BY_USERNAME);
            preparedStatement.setString(1, "%"+user.getUserName()+"%");
            result = preparedStatement.executeQuery();
            while(result.next()){
                usersFound.add(new User(result.getString(1), result.getString(2), result.getString(3), result.getString(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return usersFound;
    }//Fin del Método    
    
    /**
     * Método para loguearse en el sistema
     * @param user
     * @return 1 en caso de que el procedimiento sea exitoso, caso contrario retorna 0
     */
    @Override
    public User login(User user) {
        userFound = null;
        try {
            preparedStatement = connection.getConexion().prepareStatement(UserQueries.LOGIN);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            result = preparedStatement.executeQuery();
            while(result.next()) return userFound = new User(result.getString(1), result.getInt(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getString(7));            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return userFound;
    }//Fin del Método    
    
    /**
     * Método para validar la existencia de un usuario por cédula
     * @param user
     * @return 1 en caso de que el procedimiento sea exitoso, caso contrario retorna 0
     */
    @Override
    public int validate_existence(User user) {
        try {
            preparedStatement = connection.getConexion().prepareStatement(UserQueries.VALIDATE_EXISTENCE);
            preparedStatement.setString(1, user.getNationality());
            preparedStatement.setInt(2, user.getDni());
            result = preparedStatement.executeQuery();
            while(result.next()) return result.getInt(1);            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return 0;
    }//Fin del Método    
    
    /**
     * Método para validar la existencia de un nombre de usuario.
     * @param user
     * @return Retorna un objeto User
     */
    @Override
    public User validate_username(User user) {
        userFound = null;
        try {
            preparedStatement = connection.getConexion().prepareStatement(UserQueries.VALIDATE_USERNAME);
            preparedStatement.setString(1, user.getUserName());
            result = preparedStatement.executeQuery();
            while(result.next()) userFound = new User(result.getString(1), result.getInt(2));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return userFound;
    }//Fin del Método    
    
    /**
     * Método para validar la existencia de una cédula de usuario.
     * @param user
     * @return Retorn un objeto String
     */
    @Override
    public String validate_user(User user) {
        try {
            preparedStatement = connection.getConexion().prepareStatement(UserQueries.VALIDATE_USER);
            preparedStatement.setString(1, user.getNationality());
            preparedStatement.setInt(2, user.getDni());
            result = preparedStatement.executeQuery();
            while(result.next()) return result.getString(1);          
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return null;    
    }//Fin del Método
    
    /**
     * Método para validar la clave de un usuario
     * @param user
     * @return Retorna true en caso de que la clave sea correcta.
     */
    @Override
    public int validate_key(User user) {
        try {
            preparedStatement = connection.getConexion().prepareStatement(UserQueries.VALIDATE_KEY);
            preparedStatement.setString(1, user.getNationality());
            preparedStatement.setInt(2, user.getDni());
            preparedStatement.setString(3, user.getPassword());
            result = preparedStatement.executeQuery();
            while(result.next()) return result.getInt(1);            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return 0;
    }//Fin del Método
    
    /**
     * Método para encontrar todos los usuarios
     * @return Retorna una lista de usuarios
     */
    @Override
    public List<User> find_users() {
        usersFound = new ArrayList<>();
        try {
            preparedStatement = connection.getConexion().prepareStatement(UserQueries.SELECT_ALL);
            result = preparedStatement.executeQuery();
            while(result.next()){
                usersFound.add(new User(result.getString(1), result.getString(2), result.getString(3), result.getString(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return usersFound;
    }//Fin del método
}
