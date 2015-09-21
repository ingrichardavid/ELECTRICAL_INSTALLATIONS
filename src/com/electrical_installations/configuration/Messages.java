/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.configuration;



import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que contiene objetos estáticos para la emisión de mensajes en la aplicación.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-01
 */
public class Messages {
    
    //Objecto, variables y constantes.
    Properties properties = null;
    
    /**
     * Ruta hacia el archivo de propiedades que contiene los mensajes.
     */
    public final static String FILE_ROUTE = "com/electrical_installations/configuration/messages.properties";
    
    //Mensajes Globales
    
    /**
     * ¿Los datos ingresados son correctos?.
     */
    public final static String CONFIRM = "CONFIRM";    
    /**
     * Error al ejecutar esta operación.
     */
    public final static String OPERATION_ERROR = "OPERATION_ERROR";    
    /**
     * ¿Desea salir?.
     */
    public final static String LOG_OUT = "LOG_OUT";    
    /**
     * Error de Conexión con la Base de Datos. 
     */
    public final static String CONNECTION_ERROR = "CONNECTION_ERROR";    
    /**
     * Error al solicitar conector JDBC.
     */
    public final static String ERROR_CONNECTOR = "ERROR_CONNECTOR";    
    /**
     * Por favor, seleccione un registro.
     */
    public final static String NOT_SELECT_ROW = "NOT_SELECT_ROW";

    //Mensajes Inicio de Sesión    
        
    /**
     * Acceso denegado.
     */
    public final static String ACCESS_DENIED = "ACCESS_DENIED";    
    /**
     * Acceso Autorizado.
     */
    public final static String ACCESS_AUTHORIZED = "ACCESS_AUTHORIZED";    
    /**
     * Por favor, introduzca el nombre de usuario.
     */
    public final static String EMPTY_USERNAME = "EMPTY_USERNAME";    
    /**
     * Por favor, introduzca la clave de usuario.
     */
    public final static String EMPTY_PASSWORD = "EMPTY_PASSWORD";

    //Mensajes de Usuario    
        
    /**
     * Usuario registrado.
     */
    public final static String USER_REGISTERED  = "USER_REGISTERED";    
    /**
     * Usuario modificado.
     */
    public final static String USER_MODIFIED = "USER_MODIFIED";    
    /**
     * Datos de seguridad modificados.
     */
    public final static String USER_MODIFIED_SECURITY = "USER_MODIFIED_SECURITY";    
    /**
     * Usuario eliminado.
     */
    public final static String USER_DELETED = "USER_DELETED";    
    /**
     * ¿Desea eliminar el usuario?.
     */
    public final static String USER_CONFIRM_DELETED = "USER_CONFIRM_DELETED";    
    /**
     * El usuario ya se encuentra registrado.
     */
    public final static String USER_FOUND = "USER_FOUND";    
    /**
     * El usuario no existe.
     */
    public final static String USER_NO_FOUND = "USER_NO_FOUND";    
    /**
     * El nombre de usuario esta siendo utilizado.
     */
    public final static String USER_USERNAME_EXISTS = "USER_USERNAME_EXISTS";    
    /**
     * Acceso autorizado.
     */
    public final static String USER_ACCESS_AUTHORIZED = "USER_ACCESS_AUTHORIZED";    
    /**
     * Acceso denegado.
     */
    public final static String USER_ACCESS_DENIED = "USER_ACCESS_DENIED";    
    /**
     * Por favor, introduza la cédula.
     */
    public final static String USER_DNI_NO_FOUND = "USER_DNI_NO_FOUND";    
    /**
     * Por favor, introduza el nombre.
     */
    public final static String USER_NAME_NO_FOUND = "USER_NAME_NO_FOUND";    
    /**
     * Por favor, introduza el apellido.
     */
    public final static String USER_LASTNAME_NO_FOUND = "USER_LASTNAME_NO_FOUND";    
    /**
     * Por favor, introduza el nombre de usuario.
     */
    public final static String USER_USERNAME_NO_FOUND = "USER_USERNAME_NO_FOUND";    
    /**
     * Por favor, introduza la clave.
     */
    public final static String USER_PASSWORD_NO_FOUND = "USER_PASSWORD_NO_FOUND";    
    /**
     * Por favor, confirme la clave.
     */
    public final static String USER_CONFIRMPASSWORD_NO_FOUND = "USER_CONFIRMPASSWORD_NO_FOUND";    
    /**
     * Por favor, introduza la clave actual.
     */
    public final static String USER_PASSWORD_PREVIOUS_NO_FOUND = "USER_PASSWORD_PREVIOUS_NO_FOUND";    
    /**
     * La clave y su confirmación no coinciden.
     */
    public final static String USER_KEY_MISMATCHED = "USER_KEY_MISMATCHED";    
    /**
     * Clave anterior incorrecta.
     */
    public final static String USER_PREVIOUS_WRONG_KEY = "USER_PREVIOUS_WRONG_KEY";    
    /**
     * Los datos básicos de este usuario no pueden ser modificados.
     */
    public final static String USER_NOT_BE_MODIFIED = "USER_NOT_BE_MODIFIED";    
    /**
     * Este usuario no puede ser eliminado.
     */
    public final static String USER_NOT_BE_REMOVED = "USER_NOT_BE_REMOVED";
    /**
     * El usuario no puede ser eliminado ya que posee proyectos asociados.
     */
    public final static String USER_NOT_BE_REMOVED_ASSOCIATED_PROJECTS = "USER_NOT_BE_REMOVED_ASSOCIATED_PROJECTS";
    
    //Mensajes de Tipo de Instalación
            
    /**
     * No existen tipos de instalaciones registradas, registre al menos una.
     */
    public final static String TYPE_INSTALLATIONS_NO_FOUND = "TYPE_INSTALLATIONS_NO_FOUND";
    
    //Mensajes de Proyecto
            
    /**
     * Proyecto registrado.
     */
    public final static String PROJECT_REGISTERED = "PROJECT_REGISTERED";    
    /**
     * Proyecto modificado.
     */
    public final static String PROJECT_MODIFIED = "PROJECT_MODIFIED";    
    /**
     * Proyecto eliminado.
     */
    public final static String PROJECT_DELETED = "PROJECT_DELETED";    
    /**
     * ¿Desea eliminar el proyecto?
     */
    public final static String PROJECT_CONFIRM_DELETED = "PROJECT_CONFIRM_DELETED";    
    /**
     * El proyecto no existe.
     */
    public final static String PROJECT_NO_FOUND = "PROJECT_NO_FOUND";    
    /**
     * El nombre de proyecto esta siendo utilizado.
     */
    public final static String PROJECT_NAME_EXISTS = "PROJECT_NAME_EXISTS";    
    /**
     * Por favor, introduza el nombre.
     */
    public final static String PROJECT_NAME_NO_FOUND = "PROJECT_NAME_NO_FOUND";    
    /**
     * Por favor, seleccione el tipo.
     */
    public final static String PROJECT_TYPE_NO_FOUND = "PROJECT_TYPE_NO_FOUND";
    
    //Mensajes de para la entidad Área
    
    /**
     * Área registrada.
     */
    public final static String AREA_REGISTERED = "AREA_REGISTERED";
        
    /**
     * Área modificada.
     */
    public final static String AREA_MODIFIED = "AREA_MODIFIED";
    
    /**
     * Área eliminada.
     */
    public final static String AREA_DELETED = "AREA_DELETED";
    
    /**
     * ¿Desea eliminar el área?.
     */
    public final static String AREA_CONFIRM_DELETED = "AREA_CONFIRM_DELETED";
    
    /**
     * El área ya se encuentra registrada.
     */
    public final static String AREA_FOUND = "AREA_FOUND";
    
    /**
     * El área no existe.
     */
    public final static String AREA_NO_FOUND = "AREA_NO_FOUND";
    
    /**
     * El nombre de área esta siendo utilizada.
     */
    public final static String AREA_NAME_EXISTS = "AREA_NAME_EXISTS";
    
    /**
     * Por favor, introduza el nombre.
     */
    public final static String AREA_NAME_NO_FOUND = "AREA_NAME_NO_FOUND";
    
    /**
     * Por favor, calcule el conductor para Iluminaria.
     */
    public final static String AREA_CONDUCTOR_ILUMINARIA_NO_FOUND = "AREA_CONDUCTOR_ILUMINARIA_NO_FOUND";
        
    /**
     * Por favor, calcule el conductor para Toma Corrientes.
     */
    public final static String AREA_CONDUCTOR_POWER_POINT_NO_FOUND = "AREA_CONDUCTOR_POWER_POINT_NO_FOUND";
    
    /**
     * Por favor, introduza el área en iluminaria.
     */    
    public final static String AREA_AREA_NO_FOUND = "AREA_AREA_NO_FOUND";
    
    /**
     * Por favor, introduza el voltaje en iluminaria.
     */    
    public final static String AREA_VOLTAGE_ILUMINARIA_NO_FOUND = "AREA_VOLTAGE_ILUMINARIA_NO_FOUND";
    
    /**
     * Por favor, introduza la cantidad en toma corrientes.
     */    
    public final static String AREA_QUANTIY_NO_FOUND = "AREA_QUANTIY_NO_FOUND";
    
    /**
     * Por favor, introduza el voltaje en toma corrientes.
     */    
    public final static String AREA_VOLTAGE_POWERPOINT_NO_FOUND = "AREA_VOLTAGE_POWERPOINT_NO_FOUND";
        
    /**
     * Por favor, seleccione un área.
     */
    public final static String AREA_NOT_SELECTED = "AREA_NOT_SELECTED";
    
    //Mensajes de Carga
    
    /**
     * Por favor, seleccione al menos una carga.
     */
    public final static String CHARGE_NOT_SELECTED = "CHARGE_NOT_SELECTED";
       
    /**
     * La carga ya fue seleccionada.
     */
    public final static String CHARGE_SELECTED = "CHARGE_SELECTED";
       
    //Mensajes para Temperatura
    
    /**
     * No existen temperaturas registradas.
     */
    public final static String TEMPERATURES_NO_FOUND = "TEMPERATURES_NO_FOUND";
       
    //Mensajes para Fase
    
    /**
     * No existen fases registradas.
     */
    public final static String PHASES_NO_FOUND = "PHASES_NO_FOUND";
       
    //Mensajes para Voltaje
    
    /**
     * No existen voltajes registrados.
     */
    public final static String VOLTAGES_NO_FOUND = "VOLTAGES_NO_FOUND";
       
    //Mensajes para Calibre
    
    /**
     * No existen calibres registrados
     */
    public final static String CALIBERS_NO_FOUND = "CALIBERS_NO_FOUND";
    
    /**
     * El calibre seleccionado no posee resistencia asociada para el tipo de material elegido. 
     */
    public final static String CALIBER_RESISTANCE_NO_FOUND = "CALIBER_RESISTANCE_NO_FOUND";
    
    /**
     * El calibre seleccionado no posee reactancia ni resistencia asociada. 
     */
    public final static String CALIBER_RESISTANCE_REACTANCE_NO_FOUND = "CALIBER_RESISTANCE_REACTANCE_NO_FOUND";
       
    //Mensajes para Ducto
    
    /**
     * No existen ductos registrados
     */
    public final static String DUCTS_NO_FOUND = "DUCTS_NO_FOUND";
       
    //Mensajes para Materiales
    
    /**
     * No existen materiales registrados.
     */
    public final static String MATERIALS_NO_FOUND = "MATERIALS_NO_FOUND";
       
    //Mensajes para Acometida
    
    /**
     * Por favor, seleccione la acometida.
     */    
    public final static String RUSH_NO_FOUND = "RUSH_NO_FOUND"; 
    
    //Mesajes para Caballo de Potencia
    
    /**
     * No existen caballo de potencia registrados.
     */
    public final static String HORSES_POWER_NO_FOUND = "HORSES_POWER_NO_FOUND";   
    
    //Mesajes para Porcentajes de Motores Monofásicos.
    
    /**
     * No existen porcentajes de motores monofásicos registrados.
     */
    public final static String PERCENTAGE_SINGLE_PHASE_MOTORS_NO_FOUND = "PERCENTAGE_SINGLE_PHASE_MOTORS_NO_FOUND";   
           
    /**
     * Constructor de la clase, es privado para cumplir con el patrón Singlenton.
     */
    private Messages() {
        try {
            this.properties = new Properties();
            properties.load(Messages.class.getClassLoader().getResourceAsStream(FILE_ROUTE));
        } catch (IOException ex) {
            Logger.getLogger(Messages.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//Fin del Constructor
    
    /**
     * Este método permite obtener la instancia de la clase.
     * @return Retorna el objeto instance que es del mismo tipo que la clase.
     */    
    public static Messages getInstance() {
        return ConfigurationHolder.INSTANCE;
    }
 
    /**
     * Método privado que crea una instancia de la clase.
     */
    private static class ConfigurationHolder { 
        private static final Messages INSTANCE = new Messages();
    }
 
    /**
     * Método para obtener el nombre del archivo de propiedades que contiene los mensajes.
     * @param key
     * @return 
     */
    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }    
    
}
