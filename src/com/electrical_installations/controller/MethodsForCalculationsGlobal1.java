/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.controller;

import com.electrical_installations.configuration.Messages;
import com.electrical_installations.configuration.MessagesStructure;
import com.electrical_installations.global.method.Methods;
import com.electrical_installations.model.entity.masters.Breaker;
import com.electrical_installations.model.entity.masters.Caliber;
import com.electrical_installations.model.entity.masters.Calibers;
import com.electrical_installations.model.entity.masters.CalibersHearth;
import com.electrical_installations.model.entity.masters.Duct;
import com.electrical_installations.model.entity.masters.Intensity;
import com.electrical_installations.model.entity.masters.Material;
import com.electrical_installations.model.entity.masters.Phase;
import com.electrical_installations.model.entity.masters.ResistanceReactance;
import com.electrical_installations.model.entity.masters.Temperature;
import com.electrical_installations.model.entity.masters.Voltage;
import com.electrical_installations.model.enums.TypeBrandsCalibers;
import com.electrical_installations.model.enums.TypeCalibers;
import com.electrical_installations.model.enums.TypeMaterials;
import com.electrical_installations.model.enums.TypeNumbersCalibers;
import com.electrical_installations.model.enums.TypeResistancesAndReactances;
import com.electrical_installations.model.enums.TypeRush;
import com.electrical_installations.model.enums.TypePhases;
import com.electrical_installations.model.enums.TypeTemperature;
import com.electrical_installations.model.service.ServiceArea;
import com.electrical_installations.model.service.ServiceBreaker;
import com.electrical_installations.model.service.ServiceCalibersHearth;
import com.electrical_installations.model.service.ServiceResistanceReactance;

/**
 * Clase que contiene los métodos necesarios para realizar cálculos de la lógica de negocio.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-02
 */
public class MethodsForCalculationsGlobal1 {
    
    //Objetos, variables y constantes.         
    private static final double squareRootOfN = 3;            
    private static final double roominessToCalculateCalibres = 0.8;
    private static final double roominessToCalculateCalibresDishwasherCrusherAndDryer = 1.25;
    public static final int constantHorsePower = 746;
    private static final Messages messages = Messages.getInstance();
    
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
    
    /**
     * Método para calcular Calibre.
     * @param potency
     * @param voltage
     * @param material
     * @param temperature
     * @param powerFactor
     * @param phase
     * @return Retorna un calibre
     */
    public static Calibers calculateCaliberForSubFeeder(double potency,Voltage voltage,Material material,Temperature temperature, double powerFactor, int phase){
        return ServiceArea.find_caliber_iluminaria_power_point(new Calibers(0,material, temperature, new Intensity(0, null, intensity(potency, voltage.getVoltage(), powerFactor, phase)),null), roominessToCalculateCalibres);
    }//Fin del método
       
    /**
     * Método para calcular Calibre de Secadoras.
     * @param potency
     * @param voltage
     * @param material
     * @param temperature
     * @param powerFactor
     * @param phase
     * @return Retorna un calibre
     */
    public static Calibers calculateCaliberForDryer(double potency,Voltage voltage,Material material,Temperature temperature, double powerFactor, int phase){
        return ServiceArea.find_caliber_iluminaria_power_point(new Calibers(0,material, temperature, new Intensity(0, null, intensity(potency, voltage.getVoltage(), powerFactor, phase) * roominessToCalculateCalibresDishwasherCrusherAndDryer),null), 1);
    }//Fin del método
    
    /**
     * Método para calcular Calibre para Secadora.
     * @param potency
     * @param voltage
     * @param material
     * @param temperature
     * @param powerFactor
     * @param phase
     * @return Retorna un calibre
     */
    public static Calibers calculateCaliberDryer(double potency,Voltage voltage,Material material,Temperature temperature, double powerFactor, int phase){
        return ServiceArea.find_caliber_iluminaria_power_point(new Calibers(0,material, temperature, new Intensity(0, null, intensity(potency * 0.7, voltage.getVoltage(), powerFactor, phase) * roominessToCalculateCalibresDishwasherCrusherAndDryer),null), 1);
    }//Fin del método
    
    /**
     * Método para calcular el calibre para Trituradoras y Lavaplatos
     * @param intensity
     * @param material
     * @param temperature
     * @return Retorna un Calibre
     */
    public static Calibers calculateCaliberDishwasherAndCrusher(double intensity,Material material,Temperature temperature){
        return ServiceArea.find_caliber_iluminaria_power_point(new Calibers(0,material, temperature, new Intensity(0, null, intensity * roominessToCalculateCalibresDishwasherCrusherAndDryer),null), 1);
    }//Fin del método
          
    /**
     * Método para encontrar la marca del cable
     * @param typeCaliber
     * @param temperature
     * @return Retorna la marca del cable
     */
    public static String typeCaliber(TypeRush typeCaliber, Temperature temperature){  
        switch (typeCaliber){        
            case UNDERGROUND:
                if (temperature.getQuantity() == TypeTemperature.TEMPERATURE_75.getTemperature()) {
                    return TypeBrandsCalibers.TTU.getBrand();
                } else if (temperature.getQuantity() == TypeTemperature.TEMPERATURE_60.getTemperature()){
                    return TypeBrandsCalibers.TW.getBrand();
                } else {
                    return TypeBrandsCalibers.NOT_FOUND.getBrand();
                }
            case AIR:
                if (temperature.getQuantity() == TypeTemperature.TEMPERATURE_75.getTemperature()) {
                    return TypeBrandsCalibers.THW.getBrand();
                } else if (temperature.getQuantity() == TypeTemperature.TEMPERATURE_60.getTemperature()){
                    return TypeBrandsCalibers.TW.getBrand();
                } else {
                    return TypeBrandsCalibers.NOT_FOUND.getBrand();
                }
            default:
                return TypeBrandsCalibers.NOT_FOUND.getBrand();                
        }        
    }//Fin del Método
        
    /**
     * Método para validar si el calibre seleccionado tiene Reactancias y Resistencias asociadas.
     * @param caliber
     * @return Retorna true en caso de que el calibre se encuentre en la tabla de reactancias y resistencias, false en caso contrario.
     */
    public static boolean validate_caliber(Caliber caliber){  
        boolean exist = false;
        for (TypeNumbersCalibers typeCalibers : TypeNumbersCalibers.values()) {
            if (typeCalibers.getNumberCaliber().equals(caliber.getName())){       
                exist = true;
            }
        }
        if (!exist){
            MessagesStructure.Warning(MessagesStructure.format(200,messages.getProperty(Messages.CALIBER_RESISTANCE_REACTANCE_NO_FOUND), MessagesStructure.justify));
        }
        return exist;
    }//Fin del método
    
    /**
     * Método para calcular el número de calibres.
     * @param phase
     * @param typeCalibers
     * @return retorna el número de calibres.
     */
    public static String number_of_calibers(Phase phase, TypeCalibers typeCalibers){
        switch(typeCalibers){
            case PHASE:
                if (phase.getName().equalsIgnoreCase(TypePhases.SINGLE_PHASE_TWO_THREAD.getPhase())){
                    return "1 Cable";
                } else if (phase.getName().equalsIgnoreCase(TypePhases.SINGLE_PHASE_THREE_THREAD.getPhase())){
                    return "2 Cables";
                } else if (phase.getName().equalsIgnoreCase(TypePhases.PHASE_FOUR_THREAD.getPhase())){
                    return "3 Cables";
                }
                break;
            case NEUTRO:
                return "1 Cable";
        }
        return "0 Cables";
    }//Fin del método.
    
    /**
     * Método para calcular el Interruptor a utilizar dependiendo de la capacidad de corriente.
     * @param potency
     * @param voltage
     * @param material
     * @param powerFactor
     * @param phase
     * @param intensityDesign
     * @return Retorna un Interruptor
     */
    public static Breaker find_breaker(double potency,Voltage voltage,Material material,double powerFactor,int phase, Intensity intensityDesign){
        return ServiceBreaker.find_breaker_by_capacity(new Breaker(0, (intensity(potency, voltage.getVoltage(), powerFactor, phase) + intensityDesign.getIntensity()) / 2));
    }//Fin del método
      
    /**
     * Método para calcular el Interruptor a utilizar para Trituradora y Lavaplatos
     * @param intensity
     * @param percentageSinglePhaseMotors
     * @return Retorna el interruptor seleccionado
     */
    public static Breaker find_braker_dishwasherAndCrusher(double intensity, double percentageSinglePhaseMotors){
        return ServiceBreaker.find_breaker_by_capacity(new Breaker(0, intensity * (percentageSinglePhaseMotors / 100)));
    }//Fin del métdodo.
      
    /**
     * Método para calcular el Interruptor a utilizar Secadoras
     * @param potency
     * @param voltage
     * @param material
     * @param powerFactor
     * @param phase
     * @return Retorna el interruptor seleccionado
     */
    public static Breaker find_breaker_dryer(double potency,Voltage voltage,Material material,double powerFactor,int phase, Intensity intensityDesign){
        return ServiceBreaker.find_breaker_by_capacity(new Breaker(0, ((intensity(potency, voltage.getVoltage(), powerFactor, phase) * 0.2) + intensityDesign.getIntensity()) / 2));
    }//Fin del métdodo.
    
    /**
     * Método para calcular número de interruptores.
     * @param phase
     * @param intensity
     * @return retorna el número de interruptores
     */
    public static String number_of_brakers(Phase phase,double intensity){
        if (phase.getName().equalsIgnoreCase(TypePhases.SINGLE_PHASE_TWO_THREAD.getPhase())){
            return "1x" + intensity;
        } else if (phase.getName().equalsIgnoreCase(TypePhases.SINGLE_PHASE_THREE_THREAD.getPhase())){
            return "2x" + intensity;
        } else if (phase.getName().equalsIgnoreCase(TypePhases.PHASE_FOUR_THREAD.getPhase())){
            return "3x" + intensity;
        }
        return "0x" + intensity;
    }//Fin del método.
    
    /**
     * Método para encontrar la Reactancia
     * @param material
     * @param caliber
     * @param duct
     * @return Retorna la reactancia encontrada.
     */
    public static ResistanceReactance calculate_reactance(Material material, Caliber caliber, Duct duct){
        return ServiceResistanceReactance.find_resistence_reactance(new ResistanceReactance(caliber, duct, material, null), TypeResistancesAndReactances.REACTANCE);
    }//Fin del método
    
    /**
     * Método para encontrar la Resistencia
     * @param material
     * @param caliber
     * @param duct
     * @return Retorna la resistencia encontrada
     */
    public static ResistanceReactance calculate_resistance(Material material, Caliber caliber, Duct duct){
        if (caliber.getName().equals(TypeNumbersCalibers.CALIBER_14.getNumberCaliber()) && material.getName().equals(TypeMaterials.ALUMINIUM.getMaterial())){            
            MessagesStructure.Warning(MessagesStructure.format(200,messages.getProperty(Messages.CALIBER_RESISTANCE_NO_FOUND), MessagesStructure.left));
            return null;
        } else {
            return ServiceResistanceReactance.find_resistence_reactance(new ResistanceReactance(caliber, duct, material, null), TypeResistancesAndReactances.RESISTENCE);
        }
    }//Fin del método
    
    /**
     * Método para la selección de conductores por capacidad de voltaje.
     * @param potency
     * @param length
     * @param voltage
     * @param reactance
     * @param powerFactor
     * @param resistance
     * @param angle
     * @return Retorna la caída de voltaje
     */
    public static double breakdownVoltage(double potency, double length, double voltage, double reactance, double powerFactor, double resistance, double angle){
        return Methods.round((((potency)/powerFactor)/1000) * (length/1000) * (((resistance * powerFactor) + (reactance * Math.sin(angle)))/(10 * Math.pow(voltage/1000, 2))), 5);
    }//Fin del Método
         
    /**
     * Método para la selección de conductores de tierra
     * @param potency
     * @param voltage
     * @param powerFactor
     * @param phase
     * @return Retorna un conductor de tierra
     */
    public static CalibersHearth calculate_calibersHearth(double potency,Voltage voltage, double powerFactor, int phase){
        return ServiceCalibersHearth.find_calibers_hearth(new CalibersHearth(0, new Intensity(0, null, intensity(potency, voltage.getVoltage(), powerFactor, phase)), null, null));
    }//Fin del Método
               
    /**
     * Método para seleccionar conductores de tierra para Trituradora, Lavaplatos y Secadora
     * @param intensity
     * @return Retorna el conductor de tierra
     */
    public static CalibersHearth calculate_caliberHearth_dishwasherAndCrusher(double intensity){
        return ServiceCalibersHearth.find_calibers_hearth(new CalibersHearth(0, new Intensity(0, null, intensity), null, null));
    }//Fin del Método
    
    /**
     * Método para obtener la potencia por caballo de potencia.
     * @param horsePower
     * @return Retorna una potencia.
     */
    public static double getPotencyHorsePower(double horsePower){
        return Methods.round(horsePower * constantHorsePower, 5);
    }//;Fin del Método
    
}
