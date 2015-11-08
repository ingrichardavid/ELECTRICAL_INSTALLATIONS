/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.implementation;

import com.electrical_installations.model.DataBaseConnection;
import com.electrical_installations.model.dao.ChargeDAO;
import com.electrical_installations.model.entity.Charge;
import com.electrical_installations.model.entity.TypeCharges;
import com.electrical_installations.model.query.ChargeQueries;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que encapsula la capa Modelo para acceder a los datos de las Cargas, implementa la interfaz ChargeDAO
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-02
 */
public class ChargeImplDAO implements ChargeDAO{
    
    //Objetos, variables y constantes de la clase.
    private static final DataBaseConnection connection = DataBaseConnection.getInstance();    
    private static ChargeImplDAO instance;
    private PreparedStatement preparedStatement;
    private ResultSet result;
    private List<Charge> charges;

    /**
     * Constructor de la clase, es privado para cumplir con el patrón Singlenton.
     */
    private ChargeImplDAO() {
    }//Fin del Constructor
    
    /**
     * Este método permite obtener la instancia de la clase.
     * @return Retorna el objeto instance que es del mismo tipo que la clase.
     */
    public static synchronized ChargeImplDAO getInstance() {
        if (instance == null) {
            instance = new ChargeImplDAO();
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
     * Método para encontrar todos las Cargas
     * @return Retorna una lista de Cargas
     */
    @Override
    public List<Charge> find_charges() {
        charges = new ArrayList<>();
        try {
            preparedStatement = connection.getConexion().prepareStatement(ChargeQueries.SELECT_ALL);
            result = preparedStatement.executeQuery();
            while(result.next()){
                charges.add(new Charge(result.getInt(1), result.getString(2),result.getInt(3),result.getBoolean(4),result.getBoolean(5),result.getBoolean(6),new TypeCharges(result.getInt(7), null, result.getString(8))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return charges;
    }//Fin del método
    
    /**
     * Método para encontrar todos las Cargas filtradas por nombre
     * @return Retorna una lista de Cargas
     */
    @Override
    public List<Charge> filter_by_name(Charge charge) {
        charges = new ArrayList<>();
        try {
            preparedStatement = connection.getConexion().prepareStatement(ChargeQueries.FILTER_BY_NAME);
            preparedStatement.setString(1,"%"+charge.getName()+"%");
            result = preparedStatement.executeQuery();
            while(result.next()){
                charges.add(new Charge(result.getInt(1), result.getString(2),result.getInt(3),result.getBoolean(4),result.getBoolean(5),result.getBoolean(6),new TypeCharges(result.getInt(7), null, result.getString(8))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return charges;
    }//Fin del método
}
