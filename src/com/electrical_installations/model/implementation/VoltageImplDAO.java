/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.implementation;

import com.electrical_installations.model.DataBaseConnection;
import com.electrical_installations.model.dao.VoltageDAO;
import com.electrical_installations.model.entity.masters.Unit;
import com.electrical_installations.model.entity.masters.Voltage;
import com.electrical_installations.model.query.VoltageQueries;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que encapsula la capa Modelo para acceder a los datos de Voltaje,
 * implementa la interfaz VoltageDao
 *
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-10
 */
public class VoltageImplDAO implements VoltageDAO {

    //Objetos, variables y constantes de la clase.
    private static final DataBaseConnection connection = DataBaseConnection.getInstance();
    private static VoltageImplDAO instance;
    private PreparedStatement preparedStatement;
    private ResultSet result;
    private List<Voltage> voltagesFound;

    /**
     * Constructor de la clase, es privado para cumplir con el patrón
     * Singlenton.
     */
    private VoltageImplDAO() {
    }//Fin del Constructor

    /**
     * Este método permite obtener la instancia de la clase.
     *
     * @return Retorna el objeto instance que es del mismo tipo que la clase.
     */
    public static synchronized VoltageImplDAO getInstance() {
        if (instance == null) {
            instance = new VoltageImplDAO();
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
     * Método para encontrar todos los Voltajes
     *
     * @return Retorna una lista de Voltajes
     */
    @Override
    public List<Voltage> find_voltages() {
        voltagesFound = new ArrayList<>();
        try {
            preparedStatement = connection.getConexion().prepareStatement(VoltageQueries.SELECT_ALL);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                voltagesFound.add(new Voltage(result.getInt(1), result.getInt(2), new Unit(0, result.getString(3))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return voltagesFound;
    }//Fin del método     

}
