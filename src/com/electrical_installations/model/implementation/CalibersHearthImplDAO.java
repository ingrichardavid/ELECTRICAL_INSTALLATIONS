/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.implementation;

import com.electrical_installations.model.DataBaseConnection;
import com.electrical_installations.model.dao.CalibersHearthDAO;
import com.electrical_installations.model.entity.masters.Caliber;
import com.electrical_installations.model.entity.masters.CalibersHearth;
import com.electrical_installations.model.entity.masters.Intensity;
import com.electrical_installations.model.query.CalibersHearthQueries;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase que encapsula la capa Modelo para acceder a los datos de los Conductores de tierra,
 * implementa la interfaz CalibersHearthDAO *
 * @author Ing. Richard David
 * @version 1
 * @since 2015-10-04
 */
public class CalibersHearthImplDAO implements CalibersHearthDAO {

    //Objetos, variables y constantes de la clase.
    private static final DataBaseConnection connection = DataBaseConnection.getInstance();
    private static CalibersHearthImplDAO instance;
    private PreparedStatement preparedStatement;
    private ResultSet result;
    private CalibersHearth calibersHearthFound;

    /**
     * Constructor de la clase, es privado para cumplir con el patrón
     * Singlenton.
     */
    private CalibersHearthImplDAO() {
    }//Fin del Constructor

    /**
     * Este método permite obtener la instancia de la clase.
     *
     * @return Retorna el objeto instance que es del mismo tipo que la clase.
     */
    public static synchronized CalibersHearthImplDAO getInstance() {
        if (instance == null) {
            instance = new CalibersHearthImplDAO();
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
     * Método para encontrar un conductor de tierra.
     * @param calibersHearth
     * @return Retorna un conductor de tierra
     */
    @Override
    public CalibersHearth find_caliber_hearth(CalibersHearth calibersHearth) {
        calibersHearthFound = null;
        try {
            preparedStatement = connection.getConexion().prepareStatement(CalibersHearthQueries.FIND_CALIBER_HEARTH);
            preparedStatement.setDouble(1, calibersHearth.getIntensity().getIntensity());
            result = preparedStatement.executeQuery();
            while (result.next()) {
                calibersHearthFound = new CalibersHearth(0, null, new Caliber(result.getInt(4), result.getString(5)), null);
            }
        } catch (Exception e) {
        } finally {
            connection.closeConnection();
        }
        return calibersHearthFound;
    }
    
}
