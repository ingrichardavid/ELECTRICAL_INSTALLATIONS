/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.service;

import com.electrical_installations.configuration.Messages; 
import com.electrical_installations.model.entity.Pipeline;
import com.electrical_installations.model.entity.masters.Caliber;
import com.electrical_installations.model.enums.TypeOccupancyRate;
import com.electrical_installations.model.implementation.CaliberImplDAO;
import java.util.List;

/**
 * Clase que sirve la capa de servicios para los Calibres, los servicios se comunican con la capa Modelo para acceder a los datos.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-08
 */
public class ServiceCaliber {
    
    //Objetos, variables y constantes
    private static final CaliberImplDAO caliberImplDAO = CaliberImplDAO.getInstance();
    private static final Messages messages = Messages.getInstance();
    
    /**
     * Constructor de la clase
     */
    public ServiceCaliber(){
    }//Fin del constructor
      
    /**
     * Servicio para solicitar datos de todos los Calibres.
     * @return Retorna una lista de Calibres.
     */
    public static List<Caliber> find_caliber(){
        return caliberImplDAO.find_caliber();
    }//Fin del servicio
          
    /**
     * Servicio para encontrar el área de un calibre.
     * @param caliber
     * @return Retorna el área del calibre.
     */
    public static double find_area(Caliber caliber) {
        return caliberImplDAO.find_area(caliber);
    }//Fin del Servicio.
    
    /**
     * Servicio para encontrar el tipo de tubería a utilizar.
     * @param caliber
     * @param typeOccupancyRate
     * @return Retorna la tubería a utilizar.
     */
    public static Pipeline find_pipeline(Caliber caliber, TypeOccupancyRate typeOccupancyRate) {
        return caliberImplDAO.find_pipeline(caliber, typeOccupancyRate);
    }//Fin del Servicio.
    
}
