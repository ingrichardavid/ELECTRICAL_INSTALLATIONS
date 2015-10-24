/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.implementation;

import com.electrical_installations.model.DataBaseConnection;
import com.electrical_installations.model.dao.ElevatorDAO;
import com.electrical_installations.model.entity.masters.Elevator;
import com.electrical_installations.model.entity.masters.PersonNumber;
import com.electrical_installations.model.entity.masters.Potency;
import com.electrical_installations.model.entity.masters.Speed;
import com.electrical_installations.model.query.ElevatorQueries;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que encapsula la capa Modelo para acceder a los datos de los Elevadores, implementa la interfaz ElevatorDAO
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-24
 */
public class ElevatorImplDAO implements ElevatorDAO{
    
    //Objetos, variables y constantes de la clase.
    private static final DataBaseConnection connection = DataBaseConnection.getInstance();    
    private static ElevatorImplDAO instance;
    private PreparedStatement preparedStatement;
    private ResultSet result;
    private List<Elevator> elevators;

    /**
     * Constructor de la clase, es privado para cumplir con el patrón Singlenton.
     */
    private ElevatorImplDAO() {
    }//Fin del Constructor
    
    /**
     * Este método permite obtener la instancia de la clase.
     * @return Retorna el objeto instance que es del mismo tipo que la clase.
     */
    public static synchronized ElevatorImplDAO getInstance() {
        if (instance == null) {
            instance = new ElevatorImplDAO();
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
     * Método para encontrar todos los Tipos de Instalación
     * @return Retorna una lista de instalaciones
     */
    @Override
    public List<Elevator> find_elevators() {
        elevators = new ArrayList<>();
        try {
            preparedStatement = connection.getConexion().prepareStatement(ElevatorQueries.SELECT_ALL);
            result = preparedStatement.executeQuery();
            while(result.next()){
                elevators.add(new Elevator(result.getInt(1),new PersonNumber(0, result.getInt(2)), new Speed(0, result.getDouble(3)), new Potency(0, null, result.getInt(4))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return elevators;
    }//Fin del método
    
    /**
     * Método para encontrar todos los Elevadores filtradas por nombre
     * @return Retorna una lista de elevadores
     */
    @Override
    public List<Elevator> filter_by_name(Elevator elevator) {
        elevators = new ArrayList<>();
        try {
            preparedStatement = connection.getConexion().prepareStatement(ElevatorQueries.FILTER_BY_NAME);
            preparedStatement.setString(1,"%"+elevator.getPotency().getUnit().getName()+"%");
            result = preparedStatement.executeQuery();
            while(result.next()){
                elevators.add(new Elevator(result.getInt(1),new PersonNumber(0, result.getInt(2)), new Speed(0, result.getDouble(3)), new Potency(0, null, result.getInt(4))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return elevators;
    }//Fin del método
}
