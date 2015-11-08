/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.implementation;

import com.electrical_installations.model.DataBaseConnection;
import com.electrical_installations.model.dao.PercentageOfThreePhaseMotorsDAO;
import com.electrical_installations.model.entity.masters.PercentageOfThreePhaseMotors;
import com.electrical_installations.model.query.PercentageOfThreePhaseMotorsQueries;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List; 

/**
 * Clase que encapsula la capa Modelo para acceder a los datos de Porcentaje trifasico de Motores
 * implementa la interfaz PercentageOfThreePhaseMotorsDAO
 * @author JRichard
 * @version 1
 * @since 2015-10-27
 */
public class PercentageOfThreePhaseMotorsImplDAO implements PercentageOfThreePhaseMotorsDAO{
    
    //Objetos, variables y constantes de la clase.
    private static final DataBaseConnection connection = DataBaseConnection.getInstance();
    private static PercentageOfThreePhaseMotorsImplDAO instance;
    private PreparedStatement preparedStatement;
    private ResultSet result;
    private List<PercentageOfThreePhaseMotors> types, percentageOfThreePhaseMotorsFounds;
    
    /**
    * Constructor de la clase, es privado para cumplir con el patrón
    * Singlenton.
    */
    private PercentageOfThreePhaseMotorsImplDAO() {
    }
     
    /**
     * Este método permite obtener la instancia de la clase.
     *
     * @return Retorna el objeto instance que es del mismo tipo que la clase.
     */
    public static synchronized PercentageOfThreePhaseMotorsImplDAO getInstance() {
        if (instance == null) {
            instance = new PercentageOfThreePhaseMotorsImplDAO();
        }
        return instance;
    }//Fin del método getConexion

    /**
     * Este método cierra la conexión con la base de datos, para que esto sea
     * posible se elimina la referencia en memoria para el objeto instance.
     */
    public void closeConnection() {
        instance = null;
    }//Fin del método
    
    /**
     * Metodo para obtener todos los tipos de la tabla Porcentaje Trifásico en Motores.
     * @return Retorna una lista de tipos extraido Porcentaje Trifásico en Motores.
     */ 
    
    @Override
    public List<PercentageOfThreePhaseMotors> find_type() {
         types = new ArrayList<>();
        try {
            preparedStatement = connection.getConexion().prepareStatement(PercentageOfThreePhaseMotorsQueries.SELECT_TYPE_MOTOR);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                types.add(new PercentageOfThreePhaseMotors(0, result.getString(1), null, 0, 0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return types;
     }
    
    /**
     * Método para encontrar todos los Porcentajes de Motores Trifásicos.
     * @param percentageOfThreePhaseMotors
     * @return Retorna una lista de Porcentajes de Motores Trifásicos
     */
    @Override
    public List<PercentageOfThreePhaseMotors> find_percentage_motors_three_phase(PercentageOfThreePhaseMotors percentageOfThreePhaseMotors) {
        percentageOfThreePhaseMotorsFounds = new ArrayList<>();
        try {
            preparedStatement = connection.getConexion().prepareStatement(PercentageOfThreePhaseMotorsQueries.SELECT_ALL);
            preparedStatement.setString(1, percentageOfThreePhaseMotors.getTypeOfMotor());
            result = preparedStatement.executeQuery();
            while(result.next()){
                percentageOfThreePhaseMotorsFounds.add(new PercentageOfThreePhaseMotors(result.getInt(1), null,result.getString(2),result.getInt(3),0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return percentageOfThreePhaseMotorsFounds;
    }//Fin del método 
    
}
