/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.service;

import com.electrical_installations.configuration.Messages;
import com.electrical_installations.model.entity.masters.Voltage;
import com.electrical_installations.model.implementation.VoltageImplDAO;
import java.util.List;

/**
 * Clase que sirve la capa de servicios para Voltaje, los servicios se comunican
 * con la capa Modelo para acceder a los datos.
 *
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-10
 */
public class ServiceVoltage {

    //Objetos, variables y constantes
    private static final VoltageImplDAO voltageImplDAO = VoltageImplDAO.getInstance();
    private static final Messages messages = Messages.getInstance();

    /**
     * Constructor de la clase
     */
    public ServiceVoltage() {
    }

    /**
     * Servicio para solicitar datos de todos los Voltajes.
     *
     * @return Retorna una lista de Voltajes
     */
    public static List<Voltage> find_voltages() {
        return voltageImplDAO.find_voltages();
    }//Fin del servicio

}
