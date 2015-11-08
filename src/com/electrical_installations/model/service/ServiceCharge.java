/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.service;

import com.electrical_installations.configuration.Messages;
import com.electrical_installations.model.entity.Charge;
import com.electrical_installations.model.implementation.ChargeImplDAO;
import java.util.List;

/**
 * Clase que sirve la capa de servicios para Carga, los servicios se comunican con la capa Modelo para acceder a los datos.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-02
 */
public class ServiceCharge {
    
    //Objetos, variables y constantes
    private static final ChargeImplDAO chargeImplDAO = ChargeImplDAO.getInstance();  
    private static final Messages messages = Messages.getInstance();
    
    /**
     * Constructor de la clase
     */
    public ServiceCharge(){
    }
    
    /**
     * Servicio para solicitar datos de todas las cargas
     * @return Retorna una lista de cargas
     */
    public static List<Charge> find_charges(){
        return chargeImplDAO.find_charges();
    }//Fin del servicio
    
    /**
     * Servicio para consultar datos de cargas filtradas por nombre
     * @param charge 
     * @return Retorna una lista de cargas
     */
    public static List<Charge> filter_by_name(Charge charge){
        return chargeImplDAO.filter_by_name(charge);
    }//Fin del servicio   
    
}
