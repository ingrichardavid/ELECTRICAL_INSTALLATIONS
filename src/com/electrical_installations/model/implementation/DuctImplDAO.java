/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.implementation;

import com.electrical_installations.model.DataBaseConnection;
import com.electrical_installations.model.dao.DuctDAO;
import com.electrical_installations.model.entity.masters.Duct;
import com.electrical_installations.model.query.DuctQueries;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que encapsula la capa Modelo para acceder a los datos de Ductos,
 * implementa la interfaz DuctDAO
 *
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-09
 */
public class DuctImplDAO implements DuctDAO {

    //Objetos, variables y constantes de la clase.
    private static final DataBaseConnection connection = DataBaseConnection.getInstance();
    private static DuctImplDAO instance;
    private PreparedStatement preparedStatement;
    private ResultSet result;
    private List<Duct> ductsFound;

    /**
     * Constructor de la clase, es privado para cumplir con el patrón
     * Singlenton.
     */
    private DuctImplDAO() {
    }//Fin del Constructor

    /**
     * Este método permite obtener la instancia de la clase.
     *
     * @return Retorna el objeto instance que es del mismo tipo que la clase.
     */
    public static synchronized DuctImplDAO getInstance() {
        if (instance == null) {
            instance = new DuctImplDAO();
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
     * Método para encontrar todos los nombres de los ductos.
     *
     * @return Retorna una lista de ductos
     */
    @Override
    public List<Duct> find_ducts() {
        ductsFound = new ArrayList<>();
        try {
            preparedStatement = connection.getConexion().prepareStatement(DuctQueries.SELECT_ALL);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                ductsFound.add(new Duct(result.getInt(1), result.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return ductsFound;
    }//Fin del método     

}
