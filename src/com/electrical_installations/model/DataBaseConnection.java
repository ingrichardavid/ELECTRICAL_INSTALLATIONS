
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
                String message = "<html>" + "                                 <body>"
                    + "                                     <div width='290px' align='justify'>"
                    + "                                         <h3 align='center'>"
                    + "                                             <strong><u>ERROR DE CONEXIÓN</u></strong>"
                    + "                                         </h3><br>"
                    + "                                         <p>No se puede iniciar el Sistema debido alguna de las siguientes causas:</p>"
                    + "                                          <ul>"
                    + "                                             <li>El Sistema Manejador de Base de Datos PostgreSQL(9.4) no se encuentra instalado o en ejecución.</li>"
                    + "                                             <li>El usuario postgres ha sido eliminado.</li>"
                    + "                                             <li>La clave del usuario postgres ha sido modificada.</li>"
                    + "                                             <li>La Base de Datos ha sido eliminada.</li>"
                    + "                                         </ul>"
                    + "                                         " + "                                     </div>"
                    + "                                 </body>" + "                             </html>";
                if (ex.getSQLState().equalsIgnoreCase("3D000")) {
                    
                } else {
                    MessagesStructure.ErrorMessage(MessagesStructure.format(290, message, MessagesStructure.justify));
                }
                System.exit(0);
            }
        } catch (ClassNotFoundException ex) {
            String message = "<html>" + "                                 <body>"
                + "                                     <div width='290px' align='justify'>"
                + "                                         <h3 align='center'>"
                + "                                             <strong><u>ERROR DE CONEXIÓN</u></strong>"
                + "                                         </h3><br>"
                + "                                         <p>No se puede iniciar el Sistema debido alguna de las siguientes causas:</p>"
                + "                                          <ul>"
                + "                                             <li>La versión del Manejador de Base de datos PostgreSQL no es la 9.4.</li>"
                + "                                             <li>El conector JDBC ha sido eliminado.</li>"
                + "                                         </ul>"
                + "                                         " + "                                     </div>"
                + "                                 </body>" + "                             </html>";
            MessagesStructure.ErrorMessage(MessagesStructure.format(290, message, MessagesStructure.justify));
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
