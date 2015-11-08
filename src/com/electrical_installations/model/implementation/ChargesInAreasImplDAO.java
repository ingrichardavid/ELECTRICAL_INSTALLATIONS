/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.implementation;

import com.electrical_installations.configuration.Messages;
import com.electrical_installations.model.DataBaseConnection;
import com.electrical_installations.model.dao.ChargesInAreasDAO;
import com.electrical_installations.model.entity.Area;
import com.electrical_installations.model.entity.Charge;
import com.electrical_installations.model.entity.ChargesInAreas;
import com.electrical_installations.model.entity.TypeCharges;
import com.electrical_installations.model.entity.masters.Phase;
import com.electrical_installations.model.enums.TypeSubTypeCharge;
import com.electrical_installations.model.query.ChargesInAreasQueries;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public boolean insert_charge_in_area(ChargesInAreas chargesInAreas, Area area) {
        System.out.println(chargesInAreas.getCharge().getTypeCharges().getType());
        try {      
            connection.getConexion().setAutoCommit(false);
            preparedStatement = connection.getConexion().prepareStatement(ChargesInAreasQueries.INSERT_CHARGE_IN_AREA);
            preparedStatement.setInt(1, chargesInAreas.getCharge().getCode());
            preparedStatement.setInt(2, chargesInAreas.getArea().getCode());
            preparedStatement.setDouble(3, chargesInAreas.getPotency());
            preparedStatement.setInt(4, chargesInAreas.getQuantity());
            preparedStatement.setString(5, chargesInAreas.getCaliberPhase());
            preparedStatement.setString(6, chargesInAreas.getCaliberNeutral());
            preparedStatement.setString(7, chargesInAreas.getCaliberHearth());
            preparedStatement.setInt(8, chargesInAreas.getPhase().getCode());
            if (preparedStatement.executeUpdate() > 0){
                preparedStatement.close();
                preparedStatement = connection.getConexion().prepareStatement(ChargesInAreasQueries.UPDATE_AREA_AFTER_INSERT_CHARGE);
                preparedStatement.setDouble(1, area.getPotency_total());
                preparedStatement.setDouble(2, area.getNeutral());
                preparedStatement.setInt(3, area.getCode());
                if (preparedStatement.executeUpdate() > 0){
                    preparedStatement.close();
                    preparedStatement = connection.getConexion().prepareStatement(ChargesInAreasQueries.VALIDATE_MAIN_FEEDER_EXIST);
                    preparedStatement.setInt(1, chargesInAreas.getArea().getProject().getCode());
                    preparedStatement.setInt(2, chargesInAreas.getArea().getProject().getTypeOfInstallation().getCode());
                    preparedStatement.setInt(3, chargesInAreas.getCharge().getTypeCharges().getCode());
                    result = preparedStatement.executeQuery();
                    int code = 0;
                    while (result.next()){
                        code = result.getInt(1);
                    }
                    if (code > 0) {
                        preparedStatement.close();
                        preparedStatement = connection.getConexion().prepareStatement(ChargesInAreasQueries.UPDATE_MAIN_FEEDER_TYPE_CHARGE);
                        if (chargesInAreas.getCharge().getTypeCharges().getType().equalsIgnoreCase(TypeSubTypeCharge.POTENCY.getSubTypeCharge())){
                            preparedStatement.setDouble(1, chargesInAreas.getArea().getPotency_total() * chargesInAreas.getQuantity());
                            preparedStatement.setInt(2, 0);
                            preparedStatement.setDouble(3, 0); 
                        } else if (chargesInAreas.getCharge().getTypeCharges().getType().equalsIgnoreCase(TypeSubTypeCharge.QUANTITY.getSubTypeCharge())){
                            preparedStatement.setDouble(1, 0);
                            preparedStatement.setInt(2, (int)chargesInAreas.getArea().getPotency_total());
                            preparedStatement.setDouble(3, 0); 
                        } else {                            
                            preparedStatement.setDouble(1, 0);
                            preparedStatement.setInt(2, 0);
                            preparedStatement.setDouble(3, 1); 
                        }
                        preparedStatement.setInt(4, chargesInAreas.getArea().getProject().getCode());
                        preparedStatement.setInt(5, chargesInAreas.getArea().getProject().getTypeOfInstallation().getCode());
                        preparedStatement.setInt(6, chargesInAreas.getCharge().getTypeCharges().getCode());
                        if (preparedStatement.executeUpdate() > 0){
                            connection.getConexion().commit();
                            return true;
                        }else {
                            connection.getConexion().rollback();
                            return false;
                        }
                    }else{
                        preparedStatement.close();
                        preparedStatement = connection.getConexion().prepareStatement(ChargesInAreasQueries.INSERT_MAIN_FEEDER_TYPE_CHARGE);
                        preparedStatement.setInt(1, chargesInAreas.getArea().getProject().getCode());
                        preparedStatement.setInt(2, chargesInAreas.getArea().getProject().getTypeOfInstallation().getCode());
                        preparedStatement.setInt(3, chargesInAreas.getCharge().getTypeCharges().getCode());
                        if (chargesInAreas.getCharge().getTypeCharges().getType().equalsIgnoreCase(TypeSubTypeCharge.POTENCY.getSubTypeCharge())){
                            preparedStatement.setDouble(4, chargesInAreas.getArea().getPotency_total() * chargesInAreas.getQuantity());
                            preparedStatement.setInt(5, 0);
                            preparedStatement.setDouble(6, 0); 
                        } else if (chargesInAreas.getCharge().getTypeCharges().getType().equalsIgnoreCase(TypeSubTypeCharge.QUANTITY.getSubTypeCharge())){
                            preparedStatement.setDouble(4, 0);
                            preparedStatement.setInt(5, (int)chargesInAreas.getArea().getPotency_total());
                            preparedStatement.setDouble(6, 0); 
                        } else {                            
                            preparedStatement.setDouble(4, 0);
                            preparedStatement.setInt(5, 0);
                            preparedStatement.setDouble(6, 1); 
                        } 
                        if (preparedStatement.executeUpdate() > 0){
                            connection.getConexion().commit();
                            return true;
                        } else { 
                            connection.getConexion().rollback();
                            return false;
                        } 
                    }
                } else { 
                    connection.getConexion().rollback();
                    return false;
                }
            } else { 
                connection.getConexion().rollback();
                return false;
            }   
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.getConexion().setAutoCommit(true);
            } catch (SQLException ex) {
            ex.printStackTrace();
                Logger.getLogger(ChargesInAreasImplDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connection.closeConnection();
        }
    }//Fin del método
    
    /**
     * Método para eliminar carga de un área.
     * @return Retorna true si la operación tiene éxito.
     */
    @Override
    public boolean delete_charge_in_area(ChargesInAreas chargesInAreas, Area area) {
        try {
            connection.getConexion().setAutoCommit(false);
            preparedStatement = connection.getConexion().prepareStatement(ChargesInAreasQueries.DELETE_CHARGE_IN_AREA);
            preparedStatement.setInt(1, chargesInAreas.getCharge().getCode());
            preparedStatement.setInt(2, chargesInAreas.getArea().getCode());
            if (preparedStatement.executeUpdate() > 0){
                preparedStatement.close();
                preparedStatement = connection.getConexion().prepareStatement(ChargesInAreasQueries.UPDATE_AREA_AFTER_DELETE_CHARGE);
                preparedStatement.setDouble(1, area.getPotency_total());
                preparedStatement.setDouble(2, area.getNeutral());
                preparedStatement.setInt(3, area.getCode());
                if (preparedStatement.executeUpdate() > 0){
                    if (delete_charge_main_feeder(chargesInAreas, area, connection)){                    
                        connection.getConexion().commit();
                        return true;
                    } else {                    
                        connection.getConexion().rollback();
                        return false;
                    }
                } else {
                    connection.getConexion().rollback();
                    return false;
                }
            } else {
                connection.getConexion().rollback();
                return false;
            }  
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.getConexion().setAutoCommit(true);
            } catch (SQLException ex) {
            ex.printStackTrace();
                Logger.getLogger(ChargesInAreasImplDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connection.closeConnection();
        }
    }//Fin del método
            
    /**
     * Eliminar un tipo de carga de la entidad alimentador principal
     * @param chargesInAreas
     * @param area
     * @param dataBaseConnection
     * @return 
     */
    @Override
    public boolean delete_charge_main_feeder(ChargesInAreas chargesInAreas, Area area, DataBaseConnection dataBaseConnection) {        
        try {
            preparedStatement = dataBaseConnection.getConexion().prepareStatement(ChargesInAreasQueries.DELETE_CHARGE_UPDATE_MAIN_FEEDER_TYPE_CHARGE);
            if (chargesInAreas.getCharge().getTypeCharges().getType().equalsIgnoreCase(TypeSubTypeCharge.POTENCY.getSubTypeCharge())){
                preparedStatement.setDouble(1, chargesInAreas.getPotency());
                preparedStatement.setInt(2, 0);
                preparedStatement.setDouble(3, 0); 
                
            } else if (chargesInAreas.getCharge().getTypeCharges().getType().equalsIgnoreCase(TypeSubTypeCharge.QUANTITY.getSubTypeCharge())){
                preparedStatement.setDouble(1, 0);
                preparedStatement.setInt(2, (int)chargesInAreas.getPotency());
                preparedStatement.setDouble(3, 0); 
            } else {                      
                preparedStatement.setDouble(1, 0);
                preparedStatement.setInt(2, 0);
                preparedStatement.setDouble(3, chargesInAreas.getPotency()); 
            }
            preparedStatement.setInt(4, area.getProject().getCode());
            preparedStatement.setInt(5, area.getProject().getTypeOfInstallation().getCode());
            preparedStatement.setInt(6, chargesInAreas.getCharge().getTypeCharges().getCode());    
 
            if (preparedStatement.executeUpdate() > 0){
                preparedStatement.close();
                preparedStatement = dataBaseConnection.getConexion().prepareStatement(ChargesInAreasQueries.VALIDATE_MAIN_FEEDER_I_P_C);
                preparedStatement.setInt(1, area.getProject().getCode());
                preparedStatement.setInt(2, area.getProject().getTypeOfInstallation().getCode());
                preparedStatement.setInt(3, chargesInAreas.getCharge().getTypeCharges().getCode()); 
                result = preparedStatement.executeQuery();
                int code_project = 0;
                while (result.next()){
                    code_project = result.getInt(1);
                }
                if (code_project > 0){
                    preparedStatement.close();  
                    preparedStatement = dataBaseConnection.getConexion().prepareStatement(ChargesInAreasQueries.DELETE_CHARGE_MAIN_FEEDER);
                    preparedStatement.setInt(1, area.getProject().getCode());
                    preparedStatement.setInt(2, area.getProject().getTypeOfInstallation().getCode());
                    preparedStatement.setInt(3, chargesInAreas.getCharge().getTypeCharges().getCode());
                    return preparedStatement.executeUpdate() > 0;
                } else {
                    return true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            
            Logger.getLogger(ChargesInAreasImplDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
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
                chargesInAreasFounds.add(new ChargesInAreas(
                        new Charge(result.getInt(2), result.getString(3), 0, true, true, true,new TypeCharges(result.getInt(7), null, result.getString(8))), 
                        new Area(result.getInt(1)), 
                        result.getDouble(5), 
                        result.getInt(4), 
                        null, 
                        null, 
                        null,                         
                        new Phase(result.getInt(6), null)));
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
                chargesInAreasFounds.add(new ChargesInAreas(
                        new Charge(result.getInt(2), result.getString(3), 0, true, true ,true, new TypeCharges(result.getInt(7), null, result.getString(8))), 
                        new Area(result.getInt(1)), 
                        result.getDouble(5), 
                        result.getInt(4), 
                        null, 
                        null, 
                        null, 
                        new Phase(result.getInt(6), null)));}
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return chargesInAreasFounds;
    }//Fin del método
    
    /**
     * Validar que una carga ya no haya sido asignada a un área.
     * @param chargesInAreas
     * @return Retorna true en caso de que la carga ya haya sido asignada. Caso contrario retorna false.
     */
    @Override
    public boolean validate_charge_in_area(ChargesInAreas chargesInAreas){
        try {
            preparedStatement = connection.getConexion().prepareStatement(ChargesInAreasQueries.VALIDATE_CHARGE_IN_AREA);
            preparedStatement.setInt(1, chargesInAreas.getCharge().getCode());
            preparedStatement.setInt(2, chargesInAreas.getArea().getCode());
            result = preparedStatement.executeQuery();
            while (result.next()){
                if (result.getInt(1) > 0) return true;
            }
        } catch (Exception e) {
        } finally {
            connection.closeConnection();
        }
        return false;
    }//Fin del método
 
    /**
     * Método para contar el número de cargas asignadas a un área.
     * @param area
     * @return Retorna el número de áreas asignadas a un área.
     */
    @Override
    public int count_charges_in_area(Area area){
        int quantity = 0;
        try {
            preparedStatement = connection.getConexion().prepareStatement(ChargesInAreasQueries.COUNT_CHARGES_IN_AREA);
            preparedStatement.setInt(1, area.getCode());
            result = preparedStatement.executeQuery();
            while (result.next()){
                quantity = result.getInt(1);
            }
        } catch (Exception e) {
        }
        return quantity;
    }//Fin del método
            
}
