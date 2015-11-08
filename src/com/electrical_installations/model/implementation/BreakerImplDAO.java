/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.implementation;

import com.electrical_installations.model.DataBaseConnection;
import com.electrical_installations.model.dao.BreakerDAO;
import com.electrical_installations.model.entity.masters.Breaker;
import com.electrical_installations.model.query.BreakerQueries;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que encapsula la capa Modelo para acceder a los datos de Interruptores, implementa la interfaz InterruptorDAO
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-17
 */
public class BreakerImplDAO implements BreakerDAO{
    
    //Objetos, variables y constantes de la clase.
    private static final DataBaseConnection connection = DataBaseConnection.getInstance();  
    private static BreakerImplDAO instance;
    private PreparedStatement preparedStatement;
    private ResultSet result;
    private Breaker breakerFound;

    /**
     * Constructor de la clase, es privado para cumplir con el patrón Singlenton.
     */
    private BreakerImplDAO() {
    }//Fin del Constructor
    
    /**
     * Este método permite obtener la instancia de la clase.
     * @return Retorna el objeto instance que es del mismo tipo que la clase.
     */
    public static synchronized BreakerImplDAO getInstance() {
        if (instance == null) {
            instance = new BreakerImplDAO();
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
     * Método para encontrar Interruptor por capacidad de corriente.
     * @param breaker
     * @return Retorna un Interruptor
     */
    @Override
    public Breaker find_breaker_by_capacity(Breaker breaker) { 
        breakerFound = null;
        try {
            preparedStatement = connection.getConexion().prepareStatement(BreakerQueries.FIND_BREAKER_BY_CAPACITY);
            preparedStatement.setDouble(1, breaker.getCapacity());
            result = preparedStatement.executeQuery();
            while(result.next()){
                breakerFound = new Breaker(result.getInt(1), result.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return breakerFound;
    }//Fin del Método
    
}
