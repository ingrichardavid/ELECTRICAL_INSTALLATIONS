/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.implementation;

import com.electrical_installations.model.DataBaseConnection; 
import com.electrical_installations.model.dao.MaterialDAO; 
import com.electrical_installations.model.entity.masters.Material; 
import com.electrical_installations.model.query.MaterialQueries;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que encapsula la capa Modelo para acceder a los datos de Materiales,
 * implementa la interfaz MaterialDAO
 *
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-08
 */
public class MaterialImplDAO implements MaterialDAO {

    //Objetos, variables y constantes de la clase.
    private static final DataBaseConnection connection = DataBaseConnection.getInstance();
    private static MaterialImplDAO instance;
    private PreparedStatement preparedStatement;
    private ResultSet result;
    private List<Material> materialesFound;

    /**
     * Constructor de la clase, es privado para cumplir con el patrón
     * Singlenton.
     */
    private MaterialImplDAO() {
    }//Fin del Constructor

    /**
     * Este método permite obtener la instancia de la clase.
     *
     * @return Retorna el objeto instance que es del mismo tipo que la clase.
     */
    public static synchronized MaterialImplDAO getInstance() {
        if (instance == null) {
            instance = new MaterialImplDAO();
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
     * Método para encontrar todos los nombres de los materiales
     *
     * @return Retorna una lista de materiales
     */ 
    @Override
    public List<Material> find_materials() {
        materialesFound = new ArrayList<>();
           try {
            preparedStatement = connection.getConexion().prepareStatement(MaterialQueries.SELECT_ALL);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                materialesFound.add(new Material(result.getInt(1), result.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        } 
        return materialesFound; 
    }//Fin del método     

  

}
