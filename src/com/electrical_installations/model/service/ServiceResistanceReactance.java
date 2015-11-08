/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.service;

import com.electrical_installations.configuration.Messages;
import com.electrical_installations.model.implementation.ResistanceReactanceImplDAO;
import com.electrical_installations.model.entity.masters.ResistanceReactance;
import com.electrical_installations.model.enums.TypeResistancesAndReactances;

/**
 * Clase que sirve la capa de servicios para Resistencias y Reactacias, los servicios se comunican con la capa Modelo para acceder a los datos.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-13
 */
public class ServiceResistanceReactance {
    
    //Objetos, variables y constantes
    private static final ResistanceReactanceImplDAO resistenceReactanceImplDAO = ResistanceReactanceImplDAO.getInstance();
    private static final Messages messages = Messages.getInstance();
    
    /**
     * Constructor de la clase
     */
    public ServiceResistanceReactance(){
    }//Fin del constructor
            
    /**
     * Método para encontrar Resitencia o Reactancia.
     * @param resistanceReactance
     * @param resistancesAndReactances
     * @return Retorna la Resistencia o Reactancia encontrada.
     */
    public static ResistanceReactance find_resistence_reactance(ResistanceReactance resistanceReactance, TypeResistancesAndReactances resistancesAndReactances){
        return resistenceReactanceImplDAO.find_resistanceReactance(resistanceReactance, resistancesAndReactances);
    }//Fin del Mètodo
        
}
