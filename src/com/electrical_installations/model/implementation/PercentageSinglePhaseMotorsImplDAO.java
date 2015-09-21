/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.implementation;

import com.electrical_installations.model.DataBaseConnection;
import com.electrical_installations.model.dao.PercentageSinglePhaseMotorsDAO;
import com.electrical_installations.model.entity.masters.PercentageSinglePhaseMotors;
import com.electrical_installations.model.query.PercentageSinglePhaseMotorsQueries;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que encapsula la capa Modelo para acceder a los datos de Porcentajes de Motores Monofásicos, implementa la interfaz PercentageSinglePhaseMotors.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-20
 */
public class PercentageSinglePhaseMotorsImplDAO implements PercentageSinglePhaseMotorsDAO{
    
    //Objetos, variables y constantes de la clase.
    private static final DataBaseConnection connection = DataBaseConnection.getInstance();  
    private static PercentageSinglePhaseMotorsImplDAO instance;
    private PreparedStatement preparedStatement;
    private ResultSet result;
    private List<PercentageSinglePhaseMotors> percentageSinglePhaseMotorsFounds;

    /**
     * Constructor de la clase, es privado para cumplir con el patrón Singlenton.
     */
    private PercentageSinglePhaseMotorsImplDAO() {
    }//Fin del Constructor
    
    /**
     * Este método permite obtener la instancia de la clase.
     * @return Retorna el objeto instance que es del mismo tipo que la clase.
     */
    public static synchronized PercentageSinglePhaseMotorsImplDAO getInstance() {
        if (instance == null) {
            instance = new PercentageSinglePhaseMotorsImplDAO();
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
     * Método para encontrar todos los Porcentajes de Motores Monofásicos.
     * @return Retorna una lista de Porcentajes de Motores Monofásicos
     */
    @Override
    public List<PercentageSinglePhaseMotors> find_percentage_motors_single_phase() {
        percentageSinglePhaseMotorsFounds = new ArrayList<>();
        try {
            preparedStatement = connection.getConexion().prepareStatement(PercentageSinglePhaseMotorsQueries.SELECT_ALL);
            result = preparedStatement.executeQuery();
            while(result.next()){
                percentageSinglePhaseMotorsFounds.add(new PercentageSinglePhaseMotors(result.getInt(1), result.getString(2), result.getInt(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return percentageSinglePhaseMotorsFounds;
    }//Fin del método 
    
}
