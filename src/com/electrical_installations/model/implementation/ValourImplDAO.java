/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.implementation;

import com.electrical_installations.model.DataBaseConnection; 
import com.electrical_installations.model.dao.ValourDAO;
import com.electrical_installations.model.entity.masters.Value;
import com.electrical_installations.model.query.ValourQueries;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que encapsula la capa Modelo para acceder a los datos de Valor,
 * implementa la interfaz ValourDAO
 *
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-08
 */
public class ValourImplDAO implements ValourDAO {

    //Objetos, variables y constantes de la clase.
    private static final DataBaseConnection connection = DataBaseConnection.getInstance();
    private static ValourImplDAO instance;
    private PreparedStatement preparedStatement;
    private ResultSet result;
    private List<Value> valoursFound;

    /**
     * Constructor de la clase, es privado para cumplir con el patrón
     * Singlenton.
     */
    private ValourImplDAO() {
    }//Fin del Constructor

    /**
     * Este método permite obtener la instancia de la clase.
     *
     * @return Retorna el objeto instance que es del mismo tipo que la clase.
     */
    public static synchronized ValourImplDAO getInstance() {
        if (instance == null) {
            instance = new ValourImplDAO();
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
     * Método para encontrar todos los valores de Valor
     *
     * @return Retorna una lista valores
     */
    @Override
    public List<Value> find_valores() {
        valoursFound = new ArrayList<>();
        try {
            preparedStatement = connection.getConexion().prepareStatement(ValourQueries.SELECT_ALL);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                valoursFound.add(new Value(result.getInt(1), result.getDouble(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return valoursFound;
    }//Fin del método     
 

}
