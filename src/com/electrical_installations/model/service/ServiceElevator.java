/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.service;

import com.electrical_installations.configuration.Messages;
import com.electrical_installations.model.entity.Elevator;
import com.electrical_installations.model.implementation.ElevatorImplDAO;
import java.util.List;

/**
 * Clase que sirve la capa de servicios para la entidad Elevador, los servicios se comunican con la capa Modelo para acceder a los datos.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-24
 */
public class ServiceElevator {
    
    //Objetos, variables y constantes
    private static final ElevatorImplDAO elevatorImplDAO = ElevatorImplDAO.getInstance();  
    private static final Messages messages = Messages.getInstance();
    
    /**
     * Constructor de la clase
     */
    public ServiceElevator(){
    }
    
    /**
     * Servicio para solicitar datos de todos los elevadores
     * @return Retorna una lista de elevadores
     */
    public static List<Elevator> find_elevators(){
        return elevatorImplDAO.find_elevators();
    }//Fin del servicio
    
    /**
     * Servicio para solicitar datos de elevadores filtrados por nombre
     * @return Retorna una lista de elevadores
     */
    public static List<Elevator> filter_by_name(Elevator elevator){
        return elevatorImplDAO.filter_by_name(elevator);
                
    }//Fin del servicio
    
}
