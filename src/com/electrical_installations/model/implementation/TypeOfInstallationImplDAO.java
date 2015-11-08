/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.implementation;

import com.electrical_installations.model.DataBaseConnection;
import com.electrical_installations.model.dao.TypeOfInstallationDAO;
import com.electrical_installations.model.entity.TypeOfInstallation;
import com.electrical_installations.model.query.TypeOfInstallationQueries;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que encapsula la capa Modelo para acceder a los datos de los tipos de instalación, implementa la interfaz TypeOfInstallationDAO
 * @author Ing. Richard David
 * @version 1
 * @since 2015-07-20
 */
public class TypeOfInstallationImplDAO implements TypeOfInstallationDAO{
    
    //Objetos, variables y constantes de la clase.
    private static final DataBaseConnection connection = DataBaseConnection.getInstance();    
    private static TypeOfInstallationImplDAO instance;
    private PreparedStatement preparedStatement;
    private ResultSet result;
    private List<TypeOfInstallation> typeOfInstallations;

    /**
     * Constructor de la clase, es privado para cumplir con el patrón Singlenton.
     */
    private TypeOfInstallationImplDAO() {
    }//Fin del Constructor
    
    /**
     * Este método permite obtener la instancia de la clase.
     * @return Retorna el objeto instance que es del mismo tipo que la clase.
     */
    public static synchronized TypeOfInstallationImplDAO getInstance() {
        if (instance == null) {
            instance = new TypeOfInstallationImplDAO();
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
     * Método para encontrar todos los Tipos de Instalación
     * @return Retorna una lista de instalaciones
     */
    @Override
    public List<TypeOfInstallation> find_installations() {
        typeOfInstallations = new ArrayList<>();
        try {
            preparedStatement = connection.getConexion().prepareStatement(TypeOfInstallationQueries.SELECT_ALL);
            result = preparedStatement.executeQuery();
            while(result.next()){
                typeOfInstallations.add(new TypeOfInstallation(result.getInt(1), result.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return typeOfInstallations;
    }//Fin del método
}
