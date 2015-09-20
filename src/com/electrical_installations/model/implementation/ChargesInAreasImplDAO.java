/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.implementation;

import com.electrical_installations.configuration.Messages;
import com.electrical_installations.configuration.MessagesStructure;
import com.electrical_installations.model.DataBaseConnection;
import com.electrical_installations.model.dao.ChargesInAreasDAO;
import com.electrical_installations.model.entity.Area;
import com.electrical_installations.model.entity.Charge;
import com.electrical_installations.model.entity.ChargesInAreas;
import com.electrical_installations.model.query.ChargesInAreasQueries;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * Clase que encapsula la capa Modelo para acceder a los datos de las Cargas en Áreas, implementa la interfaz ChargesInAreasDAO
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-02
 */
public class ChargesInAreasImplDAO implements ChargesInAreasDAO{
    
    //Objetos, variables y constantes de la clase.
    private static final DataBaseConnection connection = DataBaseConnection.getInstance();   
    private static final Messages messages = Messages.getInstance(); 
    private static ChargesInAreasImplDAO instance;
    private PreparedStatement preparedStatement;
    private ResultSet result;
    private List<ChargesInAreas> chargesInAreasFounds;

    /**
     * Constructor de la clase, es privado para cumplir con el patrón Singlenton.
     */
    private ChargesInAreasImplDAO() {
    }//Fin del Constructor
    
    /**
     * Este método permite obtener la instancia de la clase.
     * @return Retorna el objeto instance que es del mismo tipo que la clase.
     */
    public static synchronized ChargesInAreasImplDAO getInstance() {
        if (instance == null) {
            instance = new ChargesInAreasImplDAO();
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
     * Método para insertar carga en área
     * @return Retorna true si la operación tiene éxito
     */
    @Override
    public boolean insert_charge_in_area(ChargesInAreas chargesInAreas) {
        try {            
            preparedStatement = connection.getConexion().prepareStatement(ChargesInAreasQueries.INSERT_CHARGE_IN_AREA);
            preparedStatement.setInt(1, chargesInAreas.getCharge().getCode());
            preparedStatement.setInt(2, chargesInAreas.getArea().getCode());
            preparedStatement.setInt(3, chargesInAreas.getQuantity());
            preparedStatement.setInt(4, chargesInAreas.getCharge().getPotency());
            preparedStatement.setInt(5, chargesInAreas.getVoltage());
            preparedStatement.setString(6, chargesInAreas.getTemperature());
            preparedStatement.setString(7, chargesInAreas.getMaterial());
            preparedStatement.setString(8, chargesInAreas.getPhase());
            if (preparedStatement.executeUpdate() > 0) return true;   
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                modify_charge_in_area(chargesInAreas);
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
     * Método para modificar carga de un área.
     * @return Retorna true si la operación tiene éxito
     */
    @Override
    public boolean modify_charge_in_area(ChargesInAreas chargesInAreas) {
        try {
            preparedStatement = connection.getConexion().prepareStatement(ChargesInAreasQueries.UPDATE_CHARGE_IN_AREA);
            preparedStatement.setInt(1, chargesInAreas.getCharge().getCode());
            preparedStatement.setInt(2, chargesInAreas.getArea().getCode());
            if (preparedStatement.executeUpdate() > 0) return true;   
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return false;
    }//Fin del método
    
    /**
     * Método para eliminar carga de un área.
     * @return Retorna true si la operación tiene éxito
     */
    @Override
    public boolean delete_charge_in_area(ChargesInAreas chargesInAreas) {
        try {
            preparedStatement = connection.getConexion().prepareStatement(ChargesInAreasQueries.DELETE_CHARGE_IN_AREA);
            preparedStatement.setInt(1, chargesInAreas.getCharge().getCode());
            preparedStatement.setInt(2, chargesInAreas.getArea().getCode());
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
     * @param chargesInAreas
     * @return Retorna una lista de cargas asociadas a un área
     */
    @Override
    public List<ChargesInAreas> find_charges_in_areas(ChargesInAreas chargesInAreas) {
        chargesInAreasFounds = new ArrayList<>();
        try {
            preparedStatement = connection.getConexion().prepareStatement(ChargesInAreasQueries.SELECT_ALL_CHARGES_IN_AREA);
            preparedStatement.setInt(1, chargesInAreas.getArea().getCode());
            result = preparedStatement.executeQuery();
            while(result.next()){
                chargesInAreasFounds.add(new ChargesInAreas(new Charge(result.getInt(2), result.getString(3),result.getInt(5)),new Area(result.getInt(1)),result.getInt(4),result.getInt(6),null,null,null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return chargesInAreasFounds;
    }//Fin del método
    
    /**
     * Consultar todas las cargas asociadas a un área filtradas por nombre.
     * @param chargesInAreas
     * @return Retorna una lista de cargas asociadas a un área
     */
    @Override
    public List<ChargesInAreas> filter_by_name(ChargesInAreas chargesInAreas) {
        chargesInAreasFounds = new ArrayList<>();
        try {
            preparedStatement = connection.getConexion().prepareStatement(ChargesInAreasQueries.FILTER_BY_NAME);
            preparedStatement.setString(1, "%"+chargesInAreas.getCharge().getName()+"%");
            preparedStatement.setInt(2, chargesInAreas.getArea().getCode());
            result = preparedStatement.executeQuery();
            while(result.next()){
                chargesInAreasFounds.add(new ChargesInAreas(new Charge(result.getInt(2), result.getString(3),result.getInt(5)),new Area(result.getInt(1)),result.getInt(4),result.getInt(6),null,null,null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return chargesInAreasFounds;
    }//Fin del método
    
}
