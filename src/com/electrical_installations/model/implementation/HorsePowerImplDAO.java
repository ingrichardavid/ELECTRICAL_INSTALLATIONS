/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.implementation;

import com.electrical_installations.model.DataBaseConnection;
import com.electrical_installations.model.dao.HorsePowerDAO;
import com.electrical_installations.model.entity.masters.HorsePower;
import com.electrical_installations.model.entity.masters.HorsesPowers;
import com.electrical_installations.model.entity.masters.Intensity;
import com.electrical_installations.model.query.HorsePowerQueries;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que encapsula la capa Modelo para acceder a los datos de Caballo de Potencia, implementa la interfaz HorsePowerDAO.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-20
 */
public class HorsePowerImplDAO implements HorsePowerDAO{
    
    //Objetos, variables y constantes de la clase.
    private static final DataBaseConnection connection = DataBaseConnection.getInstance();  
    private static HorsePowerImplDAO instance;
    private PreparedStatement preparedStatement;
    private ResultSet result;
    private List<HorsePower> horsePorwerFounds;
    private HorsesPowers horsesPowersFound;

    /**
     * Constructor de la clase, es privado para cumplir con el patrón Singlenton.
     */
    private HorsePowerImplDAO() {
    }//Fin del Constructor
    
    /**
     * Este método permite obtener la instancia de la clase.
     * @return Retorna el objeto instance que es del mismo tipo que la clase.
     */
    public static synchronized HorsePowerImplDAO getInstance() {
        if (instance == null) {
            instance = new HorsePowerImplDAO();
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
     * Método para encontrar todos los Caballos de Potencia.
     * @return Retorna una lista de Caballos de Potencia
     */
    @Override
    public List<HorsePower> find_horses_power() {
        horsePorwerFounds = new ArrayList<>();
        try {
            preparedStatement = connection.getConexion().prepareStatement(HorsePowerQueries.SELECT_ALL);
            result = preparedStatement.executeQuery();
            while(result.next()){
                horsePorwerFounds.add(new HorsePower(result.getInt(1), result.getString(2),result.getDouble(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return horsePorwerFounds;
    }//Fin del método 
    
    /**
     * Buscar intensidad por Caballo de fuerza y voltaje.
     * @param horsesPowers
     * @return Rertorna un objeto HorsesPowers con la intensidad 
     */
    @Override
    public HorsesPowers find_intensity_horses_power(HorsesPowers horsesPowers) {
        horsesPowersFound = null;
        try {
            preparedStatement = connection.getConexion().prepareStatement(HorsePowerQueries.SELECT_INTENSITY_HORSE_POWER);
            preparedStatement.setString(1, horsesPowers.getHorsePower().getName());
            preparedStatement.setInt(2, horsesPowers.getVoltage().getVoltage());
            result = preparedStatement.executeQuery();
            while(result.next()){
                horsesPowersFound = new HorsesPowers(result.getInt(1), null, null, new Intensity(result.getInt(2), null, result.getDouble(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return horsesPowersFound;
    }//Fin del método
    
}
