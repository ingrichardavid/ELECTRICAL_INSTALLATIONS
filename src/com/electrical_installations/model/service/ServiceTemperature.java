/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.service;

import com.electrical_installations.configuration.Messages;
import com.electrical_installations.configuration.MessagesStructure;
import com.electrical_installations.model.entity.masters.Temperature;
import com.electrical_installations.model.implementation.TemperatureImplDAO;
import java.util.List;

/**
 * Clase que sirve la capa de servicios para Temperatura, los servicios se comunican con la capa Modelo para acceder a los datos.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-06
 */
public class ServiceTemperature {
    
    //Objetos, variables y constantes
    private static final TemperatureImplDAO temperatureImplDAO = TemperatureImplDAO.getInstance();
    private static final Messages messages = Messages.getInstance();
    
    /**
     * Constructor de la clase
     */
    public ServiceTemperature(){
    }
      
    /**
     * Servicio para solicitar datos de todas las temperaturas.
     * @return Retorna una lista de Temperatura
     */
    public static List<Temperature> find_temperatures(){
        return temperatureImplDAO.find_temperatures();
    }//Fin del servicio
                   
}
