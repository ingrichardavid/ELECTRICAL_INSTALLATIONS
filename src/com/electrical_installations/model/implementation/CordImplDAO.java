/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.implementation;

import com.electrical_installations.model.DataBaseConnection;
import com.electrical_installations.model.dao.CordDAO;
import com.electrical_installations.model.entity.masters.Cord;
import com.electrical_installations.model.query.CordQueries;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que encapsula la capa Modelo para acceder a los datos de Cable,
 * implementa la interfaz CordDAO
 *
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-08
 */
public class CordImplDAO implements CordDAO {

    //Objetos, variables y constantes de la clase.
    private static final DataBaseConnection connection = DataBaseConnection.getInstance();
    private static CordImplDAO instance;
    private PreparedStatement preparedStatement;
    private ResultSet result;
    private List<Cord> cordsFound;

    /**
     * Constructor de la clase, es privado para cumplir con el patrón
     * Singlenton.
     */
    private CordImplDAO() {
    }//Fin del Constructor

    /**
     * Este método permite obtener la instancia de la clase.
     *
     * @return Retorna el objeto instance que es del mismo tipo que la clase.
     */
    public static synchronized CordImplDAO getInstance() {
        if (instance == null) {
            instance = new CordImplDAO();
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
     * Método para encontrar todos los nombres de los cables
     *
     * @return Retorna una lista de cables
     */
    @Override
    public List<Cord> find_cables() {
        cordsFound = new ArrayList<>();
        try {
            preparedStatement = connection.getConexion().prepareStatement(CordQueries.SELECT_ALL);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                cordsFound.add(new Cord(result.getInt(1), result.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return cordsFound;
    }//Fin del método     

}
