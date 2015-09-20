/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.implementation;

import com.electrical_installations.model.DataBaseConnection;
import com.electrical_installations.model.dao.IntensityDAO;
import com.electrical_installations.model.entity.masters.Intensity;
import com.electrical_installations.model.entity.masters.Unit;
import com.electrical_installations.model.query.IntensityQueries;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que encapsula la capa Modelo para acceder a los datos de Intensidad,
 * implementa la interfaz TemperatureDAO
 *
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-06
 */
public class IntensityImplDAO implements IntensityDAO {

    //Objetos, variables y constantes de la clase.
    private static final DataBaseConnection connection = DataBaseConnection.getInstance();
    private static IntensityImplDAO instance;
    private PreparedStatement preparedStatement;
    private ResultSet result;
    private List<Intensity> intensitysFound;

    /**
     * Constructor de la clase, es privado para cumplir con el patrón
     * Singlenton.
     */
    private IntensityImplDAO() {
    }//Fin del Constructor

    /**
     * Este método permite obtener la instancia de la clase.
     *
     * @return Retorna el objeto instance que es del mismo tipo que la clase.
     */
    public static synchronized IntensityImplDAO getInstance() {
        if (instance == null) {
            instance = new IntensityImplDAO();
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
     * Método para encontrar todos las Intensidades
     *
     * @return Retorna una lista de Intensidades
     */
    @Override
    public List<Intensity> find_Intensitys() {
        intensitysFound = new ArrayList<>();
        try {
            preparedStatement = connection.getConexion().prepareStatement(IntensityQueries.SELECT_ALL);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                intensitysFound.add(new Intensity(result.getInt(1), new Unit(0, result.getString(2)), 0));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return intensitysFound;
    }//Fin del método     

}
