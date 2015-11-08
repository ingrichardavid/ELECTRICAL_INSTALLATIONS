/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.service;

import com.electrical_installations.configuration.Messages; 
import com.electrical_installations.model.entity.masters.CalibersHearth;
import com.electrical_installations.model.implementation.CalibersHearthImplDAO;

/**
 * Clase que sirve la capa de servicios para los Conductores de Tierra, los servicios se comunican con la capa Modelo para acceder a los datos.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-10-04
 */
public class ServiceCalibersHearth {
    
    //Objetos, variables y constantes
    private static final CalibersHearthImplDAO calibersHearthImplDAO = CalibersHearthImplDAO.getInstance();
    private static final Messages messages = Messages.getInstance();
    
    /**
     * Constructor de la clase
     */
    public ServiceCalibersHearth(){
                
    }//Fin del constructor
      
    /**
     * Servicio para solicitar datos de un Conductor de Tierra.
     * @param calibersHearth
     * @return Retorna un conductor de tierra
     */
    public static CalibersHearth find_calibers_hearth(CalibersHearth calibersHearth){
        return calibersHearthImplDAO.find_caliber_hearth(calibersHearth);
    }//Fin del servicio
                   
}
