/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.service;

import com.electrical_installations.configuration.Messages;
import com.electrical_installations.model.entity.ElevatorInInstallation;
import com.electrical_installations.model.implementation.ElevatorsInInstallationImplDAO;
import java.util.List;

/**
 * Clase que sirve la capa de servicios para la entidad Elevadores en instalación, los servicios se comunican con la capa Modelo para acceder a los datos.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-26
 */
public class ServiceElevatorInInstallation {
    
    //Objetos, variables y constantes
    private static final ElevatorsInInstallationImplDAO elevatorInInstallationImplDAO = ElevatorsInInstallationImplDAO.getInstance();
    private static final Messages messages = Messages.getInstance();
    
    /**
     * Constructor de la clase
     */
    public ServiceElevatorInInstallation(){
    }//Fin del constructor
    
    /**
     * Servicio para la creación de elevadores en una instalación, recibe un objeto ElevatorInInstallation.
     * @param elevatorInInstallation 
     * @return Retorna true si el proceso de registro a finalizado con exito 
     */
    public static boolean insert_elevator_in_installation(ElevatorInInstallation elevatorInInstallation){
        return elevatorInInstallationImplDAO.insert_elevator_in_installation(elevatorInInstallation);  
    }//Fin del servicio
           
    /**
     * Servicio para consultar todos los elevadores de una instalación.
     * @param elevatorInInstallation
     * @return Retorna una lista de elevadores asociados a una instalación
     */
    public static List<ElevatorInInstallation> find_elevators_in_installation(ElevatorInInstallation elevatorInInstallation){
        return elevatorInInstallationImplDAO.find_elevators_in_installation(elevatorInInstallation);
    }//Fin del Servicio
           
    /**
     * Servicio para consultar todos los elevadores de una instalación filtrados por nombre.
     * @param elevatorInInstallation
     * @return Retorna una lista de elevadores asociados a una instalación
     */
    public static List<ElevatorInInstallation> filter_by_name(ElevatorInInstallation elevatorInInstallation){
        return elevatorInInstallationImplDAO.filter_by_name(elevatorInInstallation);
    }//Fin del Servicio
      
    /**
     * Servicio para la eliminar elevadores de una instalación, recibe un objeto ElevatorInInstallation.
     * @param elevatorInIntallation 
     * @return Retorna true si el proceso de registro a finalizado con exito 
     */
    public static boolean delete_elevator_in_installation(ElevatorInInstallation elevatorInIntallation){
        return elevatorInInstallationImplDAO.delete_elevator_in_installation(elevatorInIntallation);  
    }//Fin del servicio
    
}
