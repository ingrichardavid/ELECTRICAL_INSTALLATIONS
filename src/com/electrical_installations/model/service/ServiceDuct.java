/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.service;

import com.electrical_installations.configuration.Messages; 
import com.electrical_installations.model.entity.masters.Duct; 
import com.electrical_installations.model.implementation.DuctImplDAO;
import java.util.List;

/**
 * Clase que sirve la capa de servicios para los Ductos, los servicios se comunican con la capa Modelo para acceder a los datos.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-09
 */
public class ServiceDuct {
    
    //Objetos, variables y constantes
    private static final DuctImplDAO ductImplDAO = DuctImplDAO.getInstance();
    private static final Messages messages = Messages.getInstance();
    
    /**
     * Constructor de la clase
     */
    public ServiceDuct(){
    }
      
    /**
     * Servicio para solicitar datos de todos los Ductos.
     * @return Retorna una lista de Ductos.
     */
    public static List<Duct> find_ducts(){
        return ductImplDAO.find_ducts();
    }//Fin del servicio
                   
}
