/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.service;

import com.electrical_installations.configuration.Messages;
import com.electrical_installations.model.entity.TypeOfInstallation;
import com.electrical_installations.model.implementation.TypeOfInstallationImplDAO;
import java.util.List;

/**
 * Clase que sirve la capa de servicios para Tipo de Instalación, los servicios se comunican con la capa Modelo para acceder a los datos.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-07-27
 */
public class ServiceTypeOfInstallation {
    
    //Objetos, variables y constantes
    private static final TypeOfInstallationImplDAO typeOfInstallationImplDAO = TypeOfInstallationImplDAO.getInstance();  
    private static final Messages messages = Messages.getInstance();
    
    /**
     * Constructor de la clase
     */
    public ServiceTypeOfInstallation(){
    }
    
    /**
     * Servicio para solicitar datos de todas las instalaciones
     * @return Retorna una lista de instalaciones
     */
    public static List<TypeOfInstallation> find_type_of_installations(){
        return typeOfInstallationImplDAO.find_installations();
    }//Fin del servicio
    
}
