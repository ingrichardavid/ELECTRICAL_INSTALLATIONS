
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model;

import com.electrical_installations.configuration.Messages;
import com.electrical_installations.configuration.MessagesStructure;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que contiene la conexión hacia la Base de Datos.
 * @author Ing. Richard David 
 * @version 1 
 * @since 2015-07-10
 */
public class DataBaseConnection {

    //Objetos, variables y constantes.
    public static DataBaseConnection instance;
    private static final Messages messages = Messages.getInstance();
    private Connection connection;

    /**
     * Constructor de la clase, es privado para cumplir con el patrón Singlenton.
     */
    private DataBaseConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            try {
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/instalaciones_electricas","postgres","12345");
            } catch (SQLException ex) {    
                MessagesStructure.ErrorMessage(MessagesStructure.format(200, messages.getProperty(Messages.CONNECTION_ERROR), MessagesStructure.justify));
                System.exit(0);
            }
        } catch (ClassNotFoundException ex) {
            MessagesStructure.ErrorMessage(MessagesStructure.format(200, messages.getProperty(Messages.ERROR_CONNECTOR), MessagesStructure.justify));
            System.exit(0);
        }
    }//Fin del Constructor
    
    /**
     * Este método permite obtener la instancia de la clase.
     * @return Retorna el objeto instance que es del mismo tipo que la clase.
     */
    public synchronized static DataBaseConnection getInstance() {        
        if (instance == null) {
            instance = new DataBaseConnection();
        }
        return instance;        
    }//Fin del método getInstance

    /**
     * Método para obtener la conexión hacia la base de datos.
     * @return Retorna el objeto tipo Connection.
     */
    public Connection getConexion() {
        return connection;
    }//Fin del método getConexion

    /**
     * Este método cierra la conexión con la base de datos, para que esto sea posible
     * se elimina la referencia en memoria para el objeto instance.
     */
    public void closeConnection() {
        instance = null;
    }

}
