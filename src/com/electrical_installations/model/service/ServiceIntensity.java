/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.service;

import com.electrical_installations.configuration.Messages; 
import com.electrical_installations.model.entity.masters.Intensity;
import com.electrical_installations.model.implementation.IntensityImplDAO;
import java.util.List;

/**
 * Clase que sirve la capa de servicios para las Intensidades, los servicios se comunican con la capa Modelo para acceder a los datos.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-08
 */
public class ServiceIntensity {
    
    //Objetos, variables y constantes
    private static final IntensityImplDAO intensityImplDAO = IntensityImplDAO.getInstance();
    private static final Messages messages = Messages.getInstance();
    
    /**
     * Constructor de la clase
     */
    public ServiceIntensity(){
    }
      
    /**
     * Servicio para solicitar datos de todas las intensidades.
     * @return Retorna una lista de Intensidades
     */
    public static List<Intensity> find_intensitys(){
        return intensityImplDAO.find_Intensitys();
    }//Fin del servicio
                   
}
