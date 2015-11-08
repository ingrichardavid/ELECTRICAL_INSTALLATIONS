/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.service;

import com.electrical_installations.model.entity.masters.PercentageSinglePhaseMotors;
import com.electrical_installations.model.implementation.PercentageSinglePhaseMotorsImplDAO;
import java.util.List;

/**
 * Clase que sirve la capa de servicios para Porcentaje de Motores Monofásicos, los servicios se comunican con la capa Modelo para acceder a los datos.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-20
 */
public class ServicePercentageSinglePhaseMotors {
    
    //Objetos, variables y constantes
    private static final PercentageSinglePhaseMotorsImplDAO percentageSinglePhaseMotorsImplDAO = PercentageSinglePhaseMotorsImplDAO.getInstance();
    
    /**
     * Constructor de la clase
     */
    public ServicePercentageSinglePhaseMotors(){
    }//Fin del constructor
     
    /**
     * Servicio para solicitar datos de todos los Porcentaje de Motores Monofásicos.
     * @return Retorna una lista de Porcentajes de Motores Monofásicos.
     */
    public static List<PercentageSinglePhaseMotors> find_percentage_motors_single_phase(){
        return percentageSinglePhaseMotorsImplDAO.find_percentage_motors_single_phase();
    }//Fin del servicio
         
}
