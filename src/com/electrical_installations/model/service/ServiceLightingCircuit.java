/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.service;

import com.electrical_installations.configuration.Messages;
import com.electrical_installations.configuration.MessagesStructure;
import com.electrical_installations.model.entity.Area;
import com.electrical_installations.model.entity.AreaIluminariaPowerPoint;
import com.electrical_installations.model.entity.LightingCircuit;
import com.electrical_installations.model.entity.masters.Calibers;
import com.electrical_installations.model.implementation.AreaImplDAO;
import com.electrical_installations.model.implementation.LightingCircuitImplDAO;
import java.util.List;

/**
 * Clase que sirve la capa de servicios para Circuito de Iluminación los servicios se comunican con la capa Modelo para acceder a los datos.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-11-16
 */
public class ServiceLightingCircuit {
    
    //Objetos, variables y constantes
    private static final LightingCircuitImplDAO lightingCircuitImplDAO = LightingCircuitImplDAO.getInstance();
    private static final Messages messages = Messages.getInstance();
    
    /**
     * Constructor de la clase
     */
    public ServiceLightingCircuit(){
    }//Fin del constructor
    
    /**
     * Servicio para la creación de circuito de iluminación, recibe un objeto circuito de iluminación.
     * @param lightingCircuit
     * @return Retorna true si el proceso de registro a finalizado con exito
     */
    public static boolean create_lighting_circuit(LightingCircuit lightingCircuit){ 
        if (lightingCircuitImplDAO.insert(lightingCircuit)){
            return true;
        } else {
            MessagesStructure.ErrorMessage(MessagesStructure.format(200, messages.getProperty(Messages.OPERATION_ERROR), MessagesStructure.justify));
            return false;
        }       
    }//Fin del Servicio.
    
    /**
     * Servicio para encontrar todos los circuitos de iluminaria asociados a un proyecto.
     * @param lightingCircuit
     * @return Retorna una lista de circuitos de iluminaria.
     */
    public static List<LightingCircuit> find_lightingCircuits(LightingCircuit lightingCircuit) {
        return lightingCircuitImplDAO.find_lightingCircuit(lightingCircuit);
    }//Fin del Servicio.
     
    /**
     * Servicio para eliminar un circuito de iluminación
     * @param lightingCircuit
     * @return Retorna true si el circuito es eliminado.
     */
    public static boolean delete(LightingCircuit lightingCircuit) {
        return lightingCircuitImplDAO.delete(lightingCircuit);
    }//Fin del Servicio.
    
}
