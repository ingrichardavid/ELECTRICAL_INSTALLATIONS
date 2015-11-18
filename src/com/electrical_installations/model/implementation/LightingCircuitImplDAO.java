/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.implementation;

import com.electrical_installations.model.DataBaseConnection;
import com.electrical_installations.model.dao.lightingCircuitDAO;
import com.electrical_installations.model.entity.LightingCircuit;

import com.electrical_installations.model.query.LightingCircuitQueries;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que encapsula la capa Modelo para acceder a los datos de circuito de iluminación,
 * implementa la interfaz LightingCircuitDAO
 *
 * @author Ing. Richard David
 * @version 1
 * @since 2015-11-16
 */
public class LightingCircuitImplDAO implements lightingCircuitDAO {

    //Objetos, variables y constantes de la clase.
    private static final DataBaseConnection connection = DataBaseConnection.getInstance();
    private static LightingCircuitImplDAO instance;
    private PreparedStatement preparedStatement;
    private ResultSet result; 
    private List<LightingCircuit> lightingCircuitsFounds;

    /**
     * Constructor de la clase, es privado para cumplir con el patrón
     * Singlenton.
     */
    private LightingCircuitImplDAO() {
    }//Fin del Constructor

    /**
     * Este método permite obtener la instancia de la clase.
     *
     * @return Retorna el objeto instance que es del mismo tipo que la clase.
     */
    public static synchronized LightingCircuitImplDAO getInstance() {
        if (instance == null) {
            instance = new LightingCircuitImplDAO();
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
     * Método para insertar circuito de iluminación.
     * @param lightingCircuit
     * @return Retorna un valor booleano.
     */
    @Override
    public boolean insert(LightingCircuit lightingCircuit) {
        try {
            preparedStatement = connection.getConexion().prepareStatement(LightingCircuitQueries.INSERT);
            preparedStatement.setInt(1, lightingCircuit.getProject().getCode());            
            preparedStatement.setInt(2, lightingCircuit.getProject().getTypeOfInstallation().getCode());
            preparedStatement.setString(3, lightingCircuit.getCaliberPhaseNeutral()); 
            preparedStatement.setString(4, lightingCircuit.getPipeline());
            preparedStatement.setString(5, lightingCircuit.getDescription());
            preparedStatement.setDouble(6, lightingCircuit.getIntensity_total()); 
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(LightingCircuitImplDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            connection.closeConnection();
        } 
        return false;
     }

    @Override
    public List<LightingCircuit> find_lightingCircuit(LightingCircuit lightingCircuit) {
        lightingCircuitsFounds = new ArrayList<>();
        try {
            preparedStatement = connection.getConexion().prepareStatement(LightingCircuitQueries.SELECT);
            preparedStatement.setInt(1, lightingCircuit.getProject().getCode());
            preparedStatement.setInt(2, lightingCircuit.getProject().getTypeOfInstallation().getCode());
            result = preparedStatement.executeQuery();
            while(result.next()) {
                lightingCircuitsFounds.add(new LightingCircuit(
                        result.getInt(1), 
                        null, 
                        result.getString(2), 
                        result.getString(3), 
                        result.getString(4), 
                        result.getDouble(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }    
        return lightingCircuitsFounds;
    }
    
    @Override
    public List<LightingCircuit> find_lightingCircuit_filter_name(LightingCircuit lightingCircuit) {
        lightingCircuitsFounds = new ArrayList<>();
        try {
            preparedStatement = connection.getConexion().prepareStatement(LightingCircuitQueries.SELECT_FOR_NAME);
            preparedStatement.setString(1, "%"+lightingCircuit.getDescription()+"%");
            preparedStatement.setInt(2, lightingCircuit.getProject().getCode());
            preparedStatement.setInt(3, lightingCircuit.getProject().getTypeOfInstallation().getCode());
            result = preparedStatement.executeQuery();
            while(result.next()) {
                lightingCircuitsFounds.add(new LightingCircuit(
                        result.getInt(1), 
                        null, 
                        result.getString(2), 
                        result.getString(3), 
                        result.getString(4), 
                        result.getDouble(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }    
        return lightingCircuitsFounds;
    }

    /**
     * Método para eliminar un circuito de iluminación.
     * @param lightingCircuit
     * @return Retorna true si el circuito es eliminado.
     */
    @Override
    public boolean delete(LightingCircuit lightingCircuit) {
        try {
            preparedStatement = connection.getConexion().prepareStatement(LightingCircuitQueries.DELETE);
            preparedStatement.setInt(1, lightingCircuit.getCode());
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return false;
    }//Fin del método     
    
}