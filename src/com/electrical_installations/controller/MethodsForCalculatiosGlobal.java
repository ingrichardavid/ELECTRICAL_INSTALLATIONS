/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.controller;

import com.electrical_installations.global.method.Methods;
import com.electrical_installations.model.entity.masters.Calibers;
import com.electrical_installations.model.entity.masters.Intensity;
import com.electrical_installations.model.entity.masters.Material;
import com.electrical_installations.model.entity.masters.Temperature;
import com.electrical_installations.model.entity.masters.Voltage;
import com.electrical_installations.model.service.ServiceArea;


/**
 * Clase que contiene los métodos necesarios para realizar cálculos de la lógica de negocio.
 * @author Ing. Richard David
 * @version 1
 * @since 23/09/2015
 */

public class MethodsForCalculatiosGlobal {
    
    //Objetos, variables y constantes.   
    private static final double squareRootOfN = 3; 
    private static Calibers caliber;
    
    private static final double roominessToCalculateCalibres = 0.8;
    
      /**
     * Método para calcular intensidad.
     * @param potency
     * @param voltage
     * @param powerFactor
     * @param phase
     * @return Retorna la intensidad consumida.
     */
    public static double intensity(double potency,int voltage, double powerFactor, int phase){
        return (phase == 2) ? Methods.round(potency / (Math.sqrt(squareRootOfN) * voltage * powerFactor),5) : Methods.round((potency / voltage), 5);
       
    }// Fin del Método
    
    public static Calibers calculateCaliberIluminariaPowerPoint(double potency,Voltage voltage,Material material,Temperature temperature,double powerFactor,int phase){
        double intensity;
        intensity = intensity(potency, voltage.getVoltage(), powerFactor, phase);
        caliber = ServiceArea.find_caliber_iluminaria_power_point(new Calibers(0,material, temperature, new Intensity(0, null, intensity),null), roominessToCalculateCalibres);
        return caliber;        
    }//Fin del método
    
}
