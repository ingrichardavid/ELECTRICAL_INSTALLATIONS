/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.service;

import com.electrical_installations.configuration.Messages; 
import com.electrical_installations.model.entity.masters.Value;
import com.electrical_installations.model.implementation.ValourImplDAO;
import java.util.List;

/**
 * Clase que sirve la capa de servicios para los Valores, los servicios se comunican con la capa Modelo para acceder a los datos.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-08
 */
public class ServiceValour {
    
    //Objetos, variables y constantes
    private static final ValourImplDAO valourImplDAO = ValourImplDAO.getInstance();
    private static final Messages messages = Messages.getInstance();
    
    /**
     * Constructor de la clase
     */
    public ServiceValour(){
    }
      
    /**
     * Servicio para solicitar datos de todos los Valores.
     * @return Retorna una lista de Valores.
     */
    public static List<Value> find_valours(){
        return valourImplDAO.find_valores();
    }//Fin del servicio
                   
}
