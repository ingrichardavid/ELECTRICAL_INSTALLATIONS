/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.implementation;

import com.electrical_installations.model.DataBaseConnection;
import com.electrical_installations.model.dao.PhaseDAO;
import com.electrical_installations.model.entity.masters.Phase;
import com.electrical_installations.model.query.PhaseQueries;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que encapsula la capa Modelo para acceder a los datos de Fases,
 * implementa la interfaz PhaseDAO
 *
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-09
 */
public class PhaseImplDAO implements PhaseDAO {

    //Objetos, variables y constantes de la clase.
    private static final DataBaseConnection connection = DataBaseConnection.getInstance();
    private static PhaseImplDAO instance;
    private PreparedStatement preparedStatement;
    private ResultSet result;
    private List<Phase> phasesFound;

    /**
     * Constructor de la clase, es privado para cumplir con el patrón
     * Singlenton.
     */
    private PhaseImplDAO() {
    }//Fin del Constructor

    /**
     * Este método permite obtener la instancia de la clase.
     *
     * @return Retorna el objeto instance que es del mismo tipo que la clase.
     */
    public static synchronized PhaseImplDAO getInstance() {
        if (instance == null) {
            instance = new PhaseImplDAO();
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
     * Método para encontrar todos los nombres de las fases.
     *
     * @return Retorna una lista de fases
     */
    @Override
    public List<Phase> find_phases() {
        phasesFound = new ArrayList<>();
        try {
            preparedStatement = connection.getConexion().prepareStatement(PhaseQueries.SELECT_ALL);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                phasesFound.add(new Phase(result.getInt(1), result.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return phasesFound;

    }//Fin del método     

}
