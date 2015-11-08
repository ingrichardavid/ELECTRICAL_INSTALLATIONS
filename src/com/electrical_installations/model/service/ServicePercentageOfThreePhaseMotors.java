/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.service;

import com.electrical_installations.configuration.Messages;
import com.electrical_installations.model.entity.masters.PercentageOfThreePhaseMotors;
import com.electrical_installations.model.implementation.PercentageOfThreePhaseMotorsImplDAO;
import java.util.List;

/**
 * Clase que sirve la capa de servicios para Porcentaje trifásico de Motores, los servicios se comunican con la capa Modelo para acceder a los datos.
 * @author JRichard
 * @version 1
 * @since 2015-10-27
 */
public class ServicePercentageOfThreePhaseMotors {
     //Objetos, variables y constantes
    private static final PercentageOfThreePhaseMotorsImplDAO percentageOfThreePhaseMotorsImplDAO = PercentageOfThreePhaseMotorsImplDAO.getInstance();
    private static final Messages messages = Messages.getInstance();

    /**
     * Constructor de la clase.
     */    
    public ServicePercentageOfThreePhaseMotors() {        
    }//fin del constructor
        
    /**
     * Servicio para consultar (los Tipos) de los datos de Porcentaje trifásico de motores. 
     * @param types
     * @return retorna una lista de tipos de la tabla Porcentaje Trifásico de Motores
     */
    public static List<PercentageOfThreePhaseMotors> find_types(){
        return percentageOfThreePhaseMotorsImplDAO.find_type();
    }
        
    /**
     * Servicio para consultar los porcentajes de motores trifásicos. 
     * @param percentageOfThreePhaseMotors
     * @return retorna una lista de porcentjes de motores trifásicos
     */
    public static List<PercentageOfThreePhaseMotors> find_percentage_motors_three_phase(PercentageOfThreePhaseMotors percentageOfThreePhaseMotors) {
        return percentageOfThreePhaseMotorsImplDAO.find_percentage_motors_three_phase(percentageOfThreePhaseMotors);
    }//Fin del Servicio.
    
}
