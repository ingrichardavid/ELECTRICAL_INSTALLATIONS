/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.implementation;

import com.electrical_installations.model.DataBaseConnection;
import com.electrical_installations.model.dao.CaliberDAO;
import com.electrical_installations.model.entity.Pipeline;
import com.electrical_installations.model.entity.masters.Caliber;
import com.electrical_installations.model.enums.TypeOccupancyRate;
import com.electrical_installations.model.query.CaliberQueries;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que encapsula la capa Modelo para acceder a los datos de Calibre,
 * implementa la interfaz CaliberDAO
 *
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-08
 */
public class CaliberImplDAO implements CaliberDAO {

    //Objetos, variables y constantes de la clase.
    private static final DataBaseConnection connection = DataBaseConnection.getInstance();
    private static CaliberImplDAO instance;
    private PreparedStatement preparedStatement;
    private ResultSet result;
    private List<Caliber> calibersFound;
    private Pipeline pipelineFound;

    /**
     * Constructor de la clase, es privado para cumplir con el patrón
     * Singlenton.
     */
    private CaliberImplDAO() {
    }//Fin del Constructor

    /**
     * Este método permite obtener la instancia de la clase.
     *
     * @return Retorna el objeto instance que es del mismo tipo que la clase.
     */
    public static synchronized CaliberImplDAO getInstance() {
        if (instance == null) {
            instance = new CaliberImplDAO();
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
     * Método para encontrar todos los nombres de los calibres     *
     * @return Retorna una lista de calibres
     */
  
    @Override
    public List<Caliber> find_caliber() {
        calibersFound = new ArrayList<>();
        try {
            preparedStatement = connection.getConexion().prepareStatement(CaliberQueries.SELECT_ALL);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                calibersFound.add(new Caliber(result.getInt(1), result.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return calibersFound;
    }//Fin del método     

    /**
     * Método para encontrar el área de un calibre.
     * @param caliber
     * @return Retorna el área de un calibre.
     */
    @Override
    public double find_area(Caliber caliber) {
        double area = 0;
        try {
            preparedStatement = connection.getConexion().prepareStatement(CaliberQueries.FIND_AREA);
            preparedStatement.setInt(1, caliber.getCode());
            result = preparedStatement.executeQuery();
            while (result.next()) {
                area = result.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return area;
    }//Fin del método.

    /**
     * Método para encontrar tubería.
     * @param caliber
     * @param typeOccupancyRate
     * @return Retorna una tubería.
     */
    @Override
    public Pipeline find_pipeline(Caliber caliber, TypeOccupancyRate typeOccupancyRate) {
        pipelineFound = null;
        try {
            if (typeOccupancyRate == TypeOccupancyRate.THIRTY_ONE_PERCENT) {
                preparedStatement = connection.getConexion().prepareStatement(CaliberQueries.FIND_PIPELINE_TWO_DRIVERS);
                preparedStatement.setDouble(1, caliber.getArea());
                result = preparedStatement.executeQuery();
                while (result.next()) {
                    pipelineFound = new Pipeline(result.getString(1));
                }
            } else {
                preparedStatement = connection.getConexion().prepareStatement(CaliberQueries.FIND_PIPELINE_THREE_OR_MORE_DRIVERS);
                preparedStatement.setDouble(1, caliber.getArea());
                result = preparedStatement.executeQuery();
                while (result.next()) {
                    pipelineFound = new Pipeline(result.getString(1));
                }
            }            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return pipelineFound;
    }

}
