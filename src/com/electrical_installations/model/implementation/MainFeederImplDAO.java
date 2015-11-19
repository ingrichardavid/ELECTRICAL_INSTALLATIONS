/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.implementation;

import com.electrical_installations.model.DataBaseConnection;
import com.electrical_installations.model.dao.MainFeederDAO;
import com.electrical_installations.model.entity.Charge;
import com.electrical_installations.model.entity.MainFeeder;
import com.electrical_installations.model.entity.Project;
import com.electrical_installations.model.entity.TypeOfInstallation;
import com.electrical_installations.model.query.MainFeederQueries;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que encapsula la capa Modelo para acceder a los datos de Alimentador Principal, implementa la interfaz AreaDAO
 * @author Ing. Richard David
 * @version 1
 * @since 2015-11-17
 */
public class MainFeederImplDAO implements MainFeederDAO{
    
    //Objetos, variables y constantes de la clase.
    private static final DataBaseConnection connection = DataBaseConnection.getInstance();  
    private static MainFeederImplDAO instance;
    private PreparedStatement preparedStatement;
    private ResultSet result; 
    private List<MainFeeder> mainFeederFound; 
    private List<MainFeeder> mainFeederIntensity; 

    /**
     * Constructor de la clase, es privado para cumplir con el patrón Singlenton.
     */
    private MainFeederImplDAO() {
    }//Fin del Constructor
    
    /**
     * Este método permite obtener la instancia de la clase.
     * @return Retorna el objeto instance que es del mismo tipo que la clase.
     */
    public static synchronized MainFeederImplDAO getInstance() {
        if (instance == null) {
            instance = new MainFeederImplDAO();
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
     * Método para encontrar todos los Alimentadores Principales
     * @param mainFeeder
     * @return Retorna una lista de alimentadores pricipales
     */
    @Override
    public List<MainFeeder> find_main_feeder(MainFeeder mainFeeder) {
           mainFeederFound = new ArrayList<>();
        try {
            preparedStatement = connection.getConexion().prepareStatement(MainFeederQueries.SELECT_ALL_MAIN_FEEDER);
            preparedStatement.setInt(1, mainFeeder.getProject().getCode());
            preparedStatement.setInt(2, mainFeeder.getProject().getTypeOfInstallation().getCode());
            result = preparedStatement.executeQuery();
            while(result.next()){
                mainFeederFound.add(new MainFeeder(
                        new Project(result.getInt(1), new TypeOfInstallation(result.getInt(2), null), 0),
                        new Charge(result.getInt(3), result.getString(4)),
                        result.getDouble(5),
                        result.getDouble(6),
                        result.getInt(7),
                        result.getDouble(8)));
       
                    
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return mainFeederFound;
    }//Fin del método 

    
    /**
     * Método para encontrar todos los Alimentadores Principales Filtrado por Nombre
     * @param mainFeeder
     * @return Retorna una lista de alimentadores pricipales filtrado por nombre
     */
    @Override
    public List<MainFeeder> find_main_feeder_filter_by_name(MainFeeder mainFeeder) {
                mainFeederFound = new ArrayList<>();
        try {
            preparedStatement = connection.getConexion().prepareStatement(MainFeederQueries.SELECT_ALL_MAIN_FEEDER_FILTER_BY_NAME);
            preparedStatement.setString(1, "%"+mainFeeder.getCharge().getName()+"%");
            preparedStatement.setInt(2, mainFeeder.getProject().getCode());
            preparedStatement.setInt(3, mainFeeder.getProject().getTypeOfInstallation().getCode());
            result = preparedStatement.executeQuery();
            while(result.next()){
                mainFeederFound.add(new MainFeeder(
                        new Project(result.getInt(1), new TypeOfInstallation(result.getInt(2), null), 0),
                        new Charge(result.getInt(3), result.getString(4)),
                        result.getDouble(5),
                        result.getDouble(6),
                        result.getInt(7),
                        result.getDouble(8)));
       
                    
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return mainFeederFound;
     }//Fin del Método

    
    /**
     * Método para encontrar la intensidad de entre la suma de la tabla motores en instalación y circuito de iluminación.
     * @param mainFeeder
     * @return Retorna una intensidad
     */
    @Override
    public double find_Intensity(MainFeeder mainFeeder) {
         double intensity  = 0;
        try {
            preparedStatement = connection.getConexion().prepareStatement(MainFeederQueries.SELECT_SUM_INTENSITY);
            preparedStatement.setInt(1, mainFeeder.getProject().getCode());
            preparedStatement.setInt(2, mainFeeder.getProject().getTypeOfInstallation().getCode());
            preparedStatement.setInt(3, mainFeeder.getProject().getCode());
            preparedStatement.setInt(4, mainFeeder.getProject().getTypeOfInstallation().getCode());
            result = preparedStatement.executeQuery();
            while(result.next()){
                  intensity = result.getDouble(1);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(MainFeederImplDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.closeConnection();
        }
        return intensity;
     }
    
    
    
}
