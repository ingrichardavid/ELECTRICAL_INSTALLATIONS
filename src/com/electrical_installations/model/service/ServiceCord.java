/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.service;

import com.electrical_installations.configuration.Messages;
import com.electrical_installations.model.entity.masters.Cord; 
import com.electrical_installations.model.implementation.CordImplDAO;
import java.util.List;

/**
 * Clase que sirve la capa de servicios para los Cables, los servicios se comunican con la capa Modelo para acceder a los datos.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-08
 */
public class ServiceCord {
    
    //Objetos, variables y constantes
    private static final CordImplDAO cordImplDAO = CordImplDAO.getInstance();
    private static final Messages messages = Messages.getInstance();
    
    /**
     * Constructor de la clase
     */
    public ServiceCord(){
    }
      
    /**
     * Servicio para solicitar datos de todos los Cables.
     * @return Retorna una lista de Cables.
     */
    public static List<Cord> find_cables(){
        return cordImplDAO.find_cables();
    }//Fin del servicio
                   
}
