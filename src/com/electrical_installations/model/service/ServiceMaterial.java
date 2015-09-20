/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.service;

import com.electrical_installations.configuration.Messages;  
import com.electrical_installations.model.entity.masters.Material;
import com.electrical_installations.model.implementation.MaterialImplDAO;
import java.util.List;

/**
 * Clase que sirve la capa de servicios para los Materiales, los servicios se comunican con la capa Modelo para acceder a los datos.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-08
 */
public class ServiceMaterial {
    
    //Objetos, variables y constantes
    private static final MaterialImplDAO materialImplDAO = MaterialImplDAO.getInstance();
    private static final Messages messages = Messages.getInstance();
    
    /**
     * Constructor de la clase
     */
    public ServiceMaterial(){
    }
      
    /**
     * Servicio para solicitar datos de todos los Materiales.
     * @return Retorna una lista de Materiales.
     */
    public static List<Material> find_materials(){
        return materialImplDAO.find_materials();
    }//Fin del servicio
                   
}
