/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.service;

import com.electrical_installations.configuration.Messages;
import com.electrical_installations.model.entity.MainFeeder;
import com.electrical_installations.model.implementation.MainFeederImplDAO;
import java.util.List;

/**
 * Clase que sirve la capa de servicios para Alimentador Principal los servicios se comunican con la capa Modelo para acceder a los datos.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-11-17
 */
public class ServiceMainFeeder {
    
    //Objetos, variables y constantes
    private static final MainFeederImplDAO mainFeederImplDAO = MainFeederImplDAO.getInstance();
    private static final Messages messages = Messages.getInstance();
    
    /**
     * Constructor de la clase
     */
    public ServiceMainFeeder(){
    }//Fin del constructor
    
     
    /**
     * Servicio para encontrar todos los alimentadores principales asociados a un proyecto. 
     * @param mainFeeder
     * @return Retorna una lista de alimentadores principales.
     */
    public static List<MainFeeder> find_mainFeeder(MainFeeder mainFeeder) {
        return mainFeederImplDAO.find_main_feeder(mainFeeder);
    }//Fin del Servicio.
    
     /**
     * Servicio para encontrar todos los alimentadores principales asociados a un proyecto filtrado por nombre. 
     * @param mainFeeder
     * @return Retorna una lista de alimentadores principales filtrado por nombre.
     */
    public static List<MainFeeder> find_mainFeeder_filter_by_name(MainFeeder mainFeeder) {
        return mainFeederImplDAO.find_main_feeder_filter_by_name(mainFeeder);
    }//Fin del Servicio.
  
}
