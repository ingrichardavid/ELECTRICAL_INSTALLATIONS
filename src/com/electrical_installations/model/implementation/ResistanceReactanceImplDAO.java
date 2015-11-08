/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.implementation;

import com.electrical_installations.model.DataBaseConnection;
import com.electrical_installations.model.dao.ResistanceReactanceDAO;
import com.electrical_installations.model.entity.masters.ResistanceReactance;
import com.electrical_installations.model.entity.masters.Value;
import com.electrical_installations.model.enums.TypeResistancesAndReactances;
import com.electrical_installations.model.query.ResistanceReactanceQueries;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase que encapsula la capa Modelo para acceder a los datos de Resistencia o Reactancias, implementa la interfaz ResistenceReactanceDAO
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-13
 */
public class ResistanceReactanceImplDAO implements ResistanceReactanceDAO{
    
    //Objetos, variables y constantes de la clase.
    private static final DataBaseConnection connection = DataBaseConnection.getInstance();  
    private static ResistanceReactanceImplDAO instance;
    private PreparedStatement preparedStatement;
    private ResultSet result;
    private ResistanceReactance resistanceReactanceFound;

    /**
     * Constructor de la clase, es privado para cumplir con el patrón Singlenton.
     */
    private ResistanceReactanceImplDAO() {
    }//Fin del Constructor
    
    /**
     * Este método permite obtener la instancia de la clase.
     * @return Retorna el objeto instance que es del mismo tipo que la clase.
     */
    public static synchronized ResistanceReactanceImplDAO getInstance() {
        if (instance == null) {
            instance = new ResistanceReactanceImplDAO();
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
     * Método para encontrar una Resistencia o Reactancia
     * @param resistanceReactance
     * @return Retorna una resistencia o reactancia encontrada
     */
    @Override
    public ResistanceReactance find_resistanceReactance(ResistanceReactance resistanceReactance, TypeResistancesAndReactances resistancesAndReactances) { 
        resistanceReactanceFound = null;                
        try {
            switch (resistancesAndReactances){            
                case REACTANCE:
                    preparedStatement = connection.getConexion().prepareStatement(ResistanceReactanceQueries.FIND_REACTANCE);
                    preparedStatement.setString(1, resistancesAndReactances.getType());
                    preparedStatement.setInt(2, resistanceReactance.getCaliber().getCode());
                    preparedStatement.setInt(3, resistanceReactance.getDuct().getCode());
                    result = preparedStatement.executeQuery();
                    while(result.next()){
                        resistanceReactanceFound = new ResistanceReactance(result.getInt(1), new Value(result.getInt(2), result.getDouble(3)));
                    }
                    break;
                case RESISTENCE:
                    preparedStatement = connection.getConexion().prepareStatement(ResistanceReactanceQueries.FIND_RESISTANCE);
                    preparedStatement.setString(1, resistancesAndReactances.getType());
                    preparedStatement.setInt(2, resistanceReactance.getMaterial().getCode());
                    preparedStatement.setInt(3, resistanceReactance.getCaliber().getCode());
                    preparedStatement.setInt(4, resistanceReactance.getDuct().getCode());
                    result = preparedStatement.executeQuery();
                    while(result.next()){
                        resistanceReactanceFound = new ResistanceReactance(result.getInt(1), new Value(result.getInt(2), result.getDouble(3)));
                    }
                    break;
                default:
                    break;
            }                 
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return resistanceReactanceFound;
    }//Fin del Método
    
}
