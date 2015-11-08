/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.service;

import com.electrical_installations.configuration.Messages;
import com.electrical_installations.configuration.MessagesStructure;
import com.electrical_installations.model.entity.InstallationMotors;
import com.electrical_installations.model.implementation.InstallationMotorsImplDAO;
import java.util.List;

/**
 * Clase que sirve la capa de servicios para Motores en la instalación, los servicios se comunican con la capa Modelo para acceder a los datos.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-10-31
 */
public class ServiceInstallationMotors {
    
    //Objetos, variables y constantes
    private static final InstallationMotorsImplDAO installationMotorsImplDAO = InstallationMotorsImplDAO.getInstance();
    private static final Messages messages = Messages.getInstance();
    
    /**
     * Constructor de la clase
     */
    public ServiceInstallationMotors(){
    }//Fin del constructor
    
    /**
     * Servicio para la creación de Motores en una instalación, recibe un objeto IntallationMotors.
     * @param installationMotors 
     * @return Retorna true si el proceso de registro a finalizado con éxito 
     */
    public static boolean create_installation_motors(InstallationMotors installationMotors){
        if (MessagesStructure.ConfirmationMessage(MessagesStructure.format(200, messages.getProperty(Messages.CONFIRM), MessagesStructure.justify)) == 0){        
            if (installationMotorsImplDAO.insert(installationMotors)){
                return true;
            } else {
                MessagesStructure.ErrorMessage(MessagesStructure.format(200, messages.getProperty(Messages.OPERATION_ERROR), MessagesStructure.justify));
                return false;
            }
        }
        return false;
    }//Fin del servicio
    
    
    /**
     * Servicio para solicitar datos de todos los motores de una instalación.
     * @param installationMotors
     * @return Retorna una lista de motores de la instalación.
     */    
    public static List<InstallationMotors> find_installation_motors(InstallationMotors installationMotors){
        return installationMotorsImplDAO.find_instalation_motors(installationMotors);
    }//Fin del servicio
    
      /**
     * Servicio para solicitar datos de todos los motores de una instalación filtrado por nombre.
     * @param installationMotors
     * @return Retorna una lista de motores de la instalación filtrada por nombre.
     */    
    public static List<InstallationMotors> find_installation_motors_filter_name(InstallationMotors installationMotors){
        return installationMotorsImplDAO.find_instalation_motors_filter_name(installationMotors);
    }//Fin del servicio
    
    /**
     * Servicio para eliminar un motor de una instalación
     * @param installationMotors
     * @return Retorna true si el motor a sido eliminado.
     */
    public static boolean delete_motor(InstallationMotors installationMotors){
        return installationMotorsImplDAO.delete(installationMotors);
    }//Fin del servicio
    
  
    /**
     * Servicio para saber si una descripción de motores de una area esta repetido o no.
     * @param installationMotors 
     * @return Retorna true si el proceso de registro a finalizado con exito 
     */
    public static boolean validate_description(InstallationMotors installationMotors){         
            if (installationMotorsImplDAO.validate_description(installationMotors) != null){
                MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.AREA_FOUND), MessagesStructure.justify));
                return true;
            }   
        return false;
    }//Fin del servicio
      
}
