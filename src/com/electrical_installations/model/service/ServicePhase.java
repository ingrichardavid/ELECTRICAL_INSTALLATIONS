/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.service;

import com.electrical_installations.configuration.Messages;  
import com.electrical_installations.model.entity.masters.Phase;
import com.electrical_installations.model.implementation.PhaseImplDAO;
import java.util.List;

/**
 * Clase que sirve la capa de servicios para las Fases, los servicios se comunican con la capa Modelo para acceder a los datos.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-09
 */
public class ServicePhase {
    
    //Objetos, variables y constantes
    private static final PhaseImplDAO phaseImplDAO = PhaseImplDAO.getInstance();
    private static final Messages messages = Messages.getInstance();
    
    /**
     * Constructor de la clase
     */
    public ServicePhase(){
    }
      
    /**
     * Servicio para solicitar datos de todas las Fases.
     * @return Retorna una lista de Fases.
     */
    public static List<Phase> find_phases(){
        return phaseImplDAO.find_phases();
    }//Fin del servicio
                   
}
