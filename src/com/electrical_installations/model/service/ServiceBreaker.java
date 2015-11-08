/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.service;

import com.electrical_installations.configuration.Messages;
import com.electrical_installations.model.entity.masters.Breaker;
import com.electrical_installations.model.implementation.BreakerImplDAO;

/**
 * Clase que sirve la capa de servicios para Interruptores, los servicios se comunican con la capa Modelo para acceder a los datos.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-17
 */
public class ServiceBreaker {
    
    //Objetos, variables y constantes
    private static final BreakerImplDAO breakerImplDAO = BreakerImplDAO.getInstance();
    private static final Messages messages = Messages.getInstance();
    
    /**
     * Constructor de la clase
     */
    public ServiceBreaker(){
    }//Fin del constructor
         
    /**
     * Método para encontrar Interruptor por capacidad de corriente.
     * @param breaker
     * @return Retorna un Interruptor
     */
    public static Breaker find_breaker_by_capacity(Breaker breaker){
        return breakerImplDAO.find_breaker_by_capacity(breaker);
    }//Fin del Mètodo
    
}
