/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.implementation;

import com.electrical_installations.configuration.Messages;
import com.electrical_installations.model.DataBaseConnection;
import com.electrical_installations.model.dao.ElevatorsInInstallationDAO;
import com.electrical_installations.model.entity.Project;
import com.electrical_installations.model.entity.Elevator;
import com.electrical_installations.model.entity.ElevatorInInstallation;
import com.electrical_installations.model.entity.TypeOfInstallation;
import com.electrical_installations.model.query.ElevatorsInInstallationQueries;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * Clase que encapsula la capa Modelo para acceder a los datos de los Elevadores de una instalación, implementa la interfaz ElevatorsInInstallationDAO
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-26
 */
public class ElevatorsInInstallationImplDAO implements ElevatorsInInstallationDAO{
    
    //Objetos, variables y constantes de la clase.
    private static final DataBaseConnection connection = DataBaseConnection.getInstance();   
    private static final Messages messages = Messages.getInstance(); 
    private static ElevatorsInInstallationImplDAO instance;
    private PreparedStatement preparedStatement;
    private ResultSet result;
    private List<ElevatorInInstallation> elevatorsInInstallationFounds;

    /**
     * Constructor de la clase, es privado para cumplir con el patrón Singlenton.
     */
    private ElevatorsInInstallationImplDAO() {
    }//Fin del Constructor
    
    /**
     * Este método permite obtener la instancia de la clase.
     * @return Retorna el objeto instance que es del mismo tipo que la clase.
     */
    public static synchronized ElevatorsInInstallationImplDAO getInstance() {
        if (instance == null) {
            instance = new ElevatorsInInstallationImplDAO();
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
     * Método para insertar un elevador en una instalación
     * @param elevatorInInstallation
     * @return Retorna true si la operación tiene éxito
     */
    @Override
    public boolean insert_elevator_in_installation(ElevatorInInstallation elevatorInInstallation) {
        try {            
            preparedStatement = connection.getConexion().prepareStatement(ElevatorsInInstallationQueries.INSERT_ELEVATOR_IN_INSTALLATION);
            preparedStatement.setInt(1, elevatorInInstallation.getProject().getCode());
            preparedStatement.setInt(2, elevatorInInstallation.getProject().getTypeOfInstallation().getCode());
            preparedStatement.setInt(3, elevatorInInstallation.getElevator().getCode());
            preparedStatement.setInt(4, elevatorInInstallation.getQuantity());
            preparedStatement.setInt(5, elevatorInInstallation.getPotency());
            preparedStatement.setString(6, elevatorInInstallation.getTemperature());
            preparedStatement.setString(7, elevatorInInstallation.getMaterial());
            preparedStatement.setString(8, elevatorInInstallation.getPhase());
            preparedStatement.setInt(9, elevatorInInstallation.getVoltage());
            if (preparedStatement.executeUpdate() > 0) return true;   
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                modify_elevator_in_installation(elevatorInInstallation);
                return true;
            } else {
                e.printStackTrace();
                return false;
            } 
        } finally {
            connection.closeConnection();
        }
        return false;
    }//Fin del método
    
    /**
     * Método para modificar un elevador de una instalación.
     * @return Retorna true si la operación tiene éxito
     */
    @Override
    public boolean modify_elevator_in_installation(ElevatorInInstallation elevatorInInstallation) {
        try {
            preparedStatement = connection.getConexion().prepareStatement(ElevatorsInInstallationQueries.UPDATE_ELEVATOR_IN_INSTALLATION);
            preparedStatement.setInt(1, elevatorInInstallation.getProject().getCode());
            preparedStatement.setInt(2, elevatorInInstallation.getProject().getTypeOfInstallation().getCode());
            preparedStatement.setInt(3, elevatorInInstallation.getElevator().getCode());
            if (preparedStatement.executeUpdate() > 0) return true;   
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return false;
    }//Fin del método
    
    /**
     * Método para eliminar un elevador de una instalación.
     * @return Retorna true si la operación tiene éxito
     */
    @Override
    public boolean delete_elevator_in_installation(ElevatorInInstallation elevatorInInstallation) {
        try {
            preparedStatement = connection.getConexion().prepareStatement(ElevatorsInInstallationQueries.DELETE_ELEVATOR_IN_INSTALLATION);
            preparedStatement.setInt(1, elevatorInInstallation.getProject().getCode());
            preparedStatement.setInt(2, elevatorInInstallation.getProject().getTypeOfInstallation().getCode());
            preparedStatement.setInt(3, elevatorInInstallation.getElevator().getCode());
            if (preparedStatement.executeUpdate() > 0) return true;   
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return false;
    }//Fin del método
    
    /**
     * Consultar todas las cargas asociadas a un área.
     * @param elevatorInInstallation
     * @return Retorna una lista de cargas asociadas a un área
     */
    @Override
    public List<ElevatorInInstallation> find_elevators_in_installation(ElevatorInInstallation elevatorInInstallation) {
        elevatorsInInstallationFounds = new ArrayList<>();
        try {
            preparedStatement = connection.getConexion().prepareStatement(ElevatorsInInstallationQueries.SELECT_ALL_ELEVATORS_IN_INSTALLATION);
            preparedStatement.setInt(1, elevatorInInstallation.getProject().getCode());
            preparedStatement.setInt(2, elevatorInInstallation.getProject().getTypeOfInstallation().getCode());
            result = preparedStatement.executeQuery();
            while(result.next()){
                elevatorsInInstallationFounds.add(new ElevatorInInstallation(new Project(result.getInt(1), null, new TypeOfInstallation(result.getInt(2), null), null, 0, null), new Elevator(result.getInt(3), result.getInt(10), 0, 0), result.getInt(4), result.getInt(5), result.getString(6), result.getString(7), result.getString(8),result.getInt(9)));
            }
        } catch (SQLException e) {
        } finally {
            connection.closeConnection();
        }
        return elevatorsInInstallationFounds;
    }//Fin del método
    
    /**
     * Listar todas los elevadores asignados a una instalación filtrados por nombre.
     * @param elevatorInInstallation
     * @return Retorna una lista de cargas asociadas a un área
     */
    @Override
    public List<ElevatorInInstallation> filter_by_name(ElevatorInInstallation elevatorInInstallation) {
        elevatorsInInstallationFounds = new ArrayList<>();
        try {
            preparedStatement = connection.getConexion().prepareStatement(ElevatorsInInstallationQueries.FILTER_BY_NAME);
            preparedStatement.setString(1, "%"+elevatorInInstallation.getProject().getTypeOfInstallation().getName()+"%");
            preparedStatement.setInt(2, elevatorInInstallation.getProject().getCode());
            preparedStatement.setInt(3, elevatorInInstallation.getProject().getTypeOfInstallation().getCode());
            result = preparedStatement.executeQuery();
            while(result.next()){
                elevatorsInInstallationFounds.add(new ElevatorInInstallation(new Project(result.getInt(1), null, new TypeOfInstallation(result.getInt(2), null), null, 0, null), new Elevator(result.getInt(3), result.getInt(10), 0, 0), result.getInt(4), result.getInt(5), result.getString(6), result.getString(7), result.getString(8),result.getInt(9)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return elevatorsInInstallationFounds;
    }//Fin del método
  
    
}
