/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.service;

import com.electrical_installations.model.entity.masters.HorsePower;
import com.electrical_installations.model.entity.masters.HorsesPowers;
import com.electrical_installations.model.implementation.HorsePowerImplDAO;
import java.util.List;

/**
 * Clase que sirve la capa de servicios para Caballo de Potencia, los servicios se comunican con la capa Modelo para acceder a los datos.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-20
 */
public class ServiceHorsePorwer {
    
    //Objetos, variables y constantes
    private static final HorsePowerImplDAO horsePowerImplDAO = HorsePowerImplDAO.getInstance();
    
    /**
     * Constructor de la clase
     */
    public ServiceHorsePorwer(){
    }//Fin del constructor
     
    /**
     * Servicio para solicitar datos de todos los Caballo de Potencia
     * @return Retorna una lista de Caballo de Potencia.
     */
    public static List<HorsePower> find_horses_power(){
        return horsePowerImplDAO.find_horses_power();
    }//Fin del servicio
        
    /**
     * Servicio para buscar intensidad por Caballo de fuerza y voltaje.
     * @param horsesPowers
     * @return Rertorna un objeto HorsesPowers con la intensidad 
     */
    public static HorsesPowers find_intensity_horses_power(HorsesPowers horsesPowers){
        return horsePowerImplDAO.find_intensity_horses_power(horsesPowers);
    }//Fin del servicio
         
}
