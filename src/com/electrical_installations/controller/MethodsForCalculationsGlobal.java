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
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Clase que contiene los métodos necesarios para realizar cálculos de la lógica de negocio.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-02
 */
public class MethodsForCalculationsGlobal {
    
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
     * Método para calcular Calibre Neutro para Secadora.
     * @param potency
     * @param voltage
     * @param material
     * @param temperature
     * @param powerFactor
     * @param phase
     * @return Retorna un calibre
     */
    public static Calibers calculateCaliberDryer(double potency,Voltage voltage,Material material,Temperature temperature, double powerFactor, int phase){
        return ServiceArea.find_caliber_iluminaria_power_point(new Calibers(0,material, temperature, new Intensity(0, null, (((potency * roominessToCalculateCalibresDishwasherCrusherAndDryer))/voltage.getVoltage()) * 0.7),null), 1);
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
     * Método para calcular el Interruptor a utilizar para Motores
     * @param intensity
     * @return Retorna el interruptor seleccionado
     */
    public static Breaker find_braker_motors(double intensity){
        return ServiceBreaker.find_breaker_by_capacity(new Breaker(0, intensity));
    }//Fin del métdodo.
      
    /**
     * Método para calcular el Interruptor a utilizar Secadoras
     * @param potency
     * @param voltage
     * @param material
     * @param powerFactor
     * @param phase
     * @param intensityDesign
     * @return Retorna el interruptor seleccionado
     */
    public static Breaker find_breaker_dryer(double potency,Voltage voltage,Material material,double powerFactor,int phase, Intensity intensityDesign){
        return ServiceBreaker.find_breaker_by_capacity(new Breaker(0, (((potency * roominessToCalculateCalibresDishwasherCrusherAndDryer)/voltage.getVoltage()) + intensityDesign.getIntensity()) / 2));
    }//Fin del métdodo.
    
    
      
    /**
     * Método para calcular el Interruptor a utilizar Secadoras
     * @param potency
     * @param voltage
     * @param material
     * @param powerFactor
     * @param phase
     * @param intensityDesign
     * @return Retorna el interruptor seleccionado
     */
    public static Breaker find_breaker_subfeeder(double potency,Voltage voltage,Material material,double powerFactor,int phase, Intensity intensityDesign){
        //return ServiceBreaker.find_breaker_by_capacity(new Breaker(0, ((intensity(potency, voltage.getVoltage(), powerFactor, phase) * 0.2) + intensityDesign.getIntensity()) / 2));
        return ServiceBreaker.find_breaker_by_capacity(new Breaker(0, ((potency/voltage.getVoltage()) + intensityDesign.getIntensity()) / 2));
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
        return Methods.round(Methods.round((((potency)/powerFactor)/1000), 5) * Methods.round(length/1000, 5) * ((Methods.round(resistance * powerFactor, 5) + Methods.round(reactance * Math.sin(angle),5))/Methods.round((10 * Math.pow(voltage/1000, 2)), 5)), 5);
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
    }//Fin del Método
    
    /**
     * Método para calcular la demanda de potencia y neutro para cocinas eléctricas.
     * @param quantity
     * @return Retorna un arreglo con la potencia total y neutral.
     */
    public static List<Double> calculateDemandForElectricKitchen(int quantity) {
        List<Double> data = new ArrayList<>();
        switch (quantity) {
            case 1:
                data.add(Double.valueOf(8 * 1000));
                data.add((8 * 1000) * 0.7);
                return data;
            case 2:
                data.add(Double.valueOf(11 * 1000));
                data.add((11 * 1000) * 0.7);
                return data;
            case 3:
                data.add(Double.valueOf(14 * 1000));
                data.add((14 * 1000) * 0.7);
                return data;
            case 4:
                data.add(Double.valueOf(17 * 1000));
                data.add((17 * 1000) * 0.7);
                return data;
            case 5:
                data.add(Double.valueOf(20 * 1000));
                data.add((20 * 1000) * 0.7);
                return data;
            case 6:
                data.add(Double.valueOf(21 * 1000));
                data.add((21 * 1000) * 0.7);
                return data;
            case 7: 
                data.add(Double.valueOf(22 * 1000));
                data.add((22 * 1000) * 0.7);
                return data;
            case 8:
                data.add(Double.valueOf(23 * 1000));
                data.add((23 * 1000) * 0.7);
                return data;
            case 9:
                data.add(Double.valueOf(24 * 1000));
                data.add((24 * 1000) * 0.7);
                return data;
            case 10:
                data.add(Double.valueOf(25 * 1000));
                data.add((25 * 1000) * 0.7);
                return data;
            case 11:
                data.add(Double.valueOf(26 * 1000));
                data.add((26 * 1000) * 0.7);
                return data;
            case 12:
                data.add(Double.valueOf(27 * 1000));
                data.add((27 * 1000) * 0.7);
                return data;
            case 13:
                data.add(Double.valueOf(28 * 1000));
                data.add((28 * 1000) * 0.7);
                return data;
            case 14:
                data.add(Double.valueOf(29 * 1000));
                data.add((29 * 1000) * 0.7);
                return data;
            case 15:
                data.add(Double.valueOf(30 * 1000));
                data.add((30 * 1000) * 0.7);
                return data;
            case 16:
                data.add(Double.valueOf(31 * 1000));
                data.add((31 * 1000) * 0.7);
                return data;
            case 17:
                data.add(Double.valueOf(32 * 1000));
                data.add((32 * 1000) * 0.7);
                return data;
            case 18:
                data.add(Double.valueOf(33 * 1000));
                data.add((33 * 1000) * 0.7);
                return data;
            case 19:
                data.add(Double.valueOf(34 * 1000));
                data.add((34 * 1000) * 0.7);
                return data;
            case 20:
                data.add(Double.valueOf(35 * 1000));
                data.add((35 * 1000) * 0.7);
                return data;
            case 21:
                data.add(Double.valueOf(36 * 1000));
                data.add((36 * 1000) * 0.7);
                return data;
            case 22:
                data.add(Double.valueOf(37 * 1000));
                data.add((37 * 1000) * 0.7);
                return data;
            case 23:
                data.add(Double.valueOf(38 * 1000));
                data.add((38 * 1000) * 0.7);
                return data;
            case 24:
                data.add(Double.valueOf(39 * 1000));
                data.add((39 * 1000) * 0.7);
                return data;
            case 25:
                data.add(Double.valueOf(40 * 1000));
                data.add((40 * 1000) * 0.7);
                return data;
            default:
                return data;
        }        
    }//Fin del método.  
    
    /**
     * Método para calcular la demanda de potencia y neutro secadoras.
     * @param quantity
     * @param potency
     * @return Retorna un arreglo con la potencia total y neutral.
     */
    public static List<Double> calculateDemandForDryer(int quantity, double potency) {
        List<Double> data = new ArrayList<>();
        double percentage = 0;
        switch (quantity) {
            case 1:
                data.add(1 * potency);
                data.add((1 * potency) * 0.7);
                return data;
            case 2:
                data.add(2 * potency);
                data.add((2 * potency) * 0.7);
                return data;
            case 3:
                data.add(3 * potency);
                data.add((3 * potency) * 0.7);
                return data;
            case 4:
                data.add(4 * potency);
                data.add((4 * potency) * 0.7);
                return data;
            case 5:
                data.add((5 * potency) * 0.85);
                data.add((5 * potency) * 0.7);
                return data;
            case 6:
                data.add((6 * potency) * 0.75);
                data.add((6 * potency) * 0.7);
                return data;
            case 7: 
                data.add((7 * potency) * 0.65);
                data.add((7 * potency) * 0.7);
                return data;
            case 8:
                data.add((8 * potency) * 0.6);
                data.add((8 * potency) * 0.7);
                return data;
            case 9:
                data.add((9 * potency) * 0.55);
                data.add((9 * potency) * 0.7);
                return data;
            case 10:
                data.add((10 * potency) * 0.5);
                data.add((10 * potency) * 0.7);
                return data;
            case 11:
                data.add((11 * potency) * 0.47);
                data.add((11 * potency) * 0.7);
                return data;
            case 12:
                percentage = 47 - (quantity - 11);
                data.add((quantity * potency) * percentage);
                data.add((quantity * potency) * 0.7);
                return data;
            case 13:
                percentage = 47 - (quantity - 11);
                data.add((quantity * potency) * percentage);
                data.add((quantity * potency) * 0.7);
                return data;
            case 14:
                percentage = 47 - (quantity - 11);
                data.add((quantity * potency) * percentage);
                data.add((quantity * potency) * 0.7);
                return data;
            case 15:
                percentage = 47 - (quantity - 11);
                data.add((quantity * potency) * percentage);
                data.add((quantity * potency) * 0.7);
                return data;
            case 16:
                percentage = 47 - (quantity - 11);
                data.add((quantity * potency) * percentage);
                data.add((quantity * potency) * 0.7);
                return data;
            case 17:
                percentage = 47 - (quantity - 11);
                data.add((quantity * potency) * percentage);
                data.add((quantity * potency) * 0.7);
                return data;
            case 18:
                percentage = 47 - (quantity - 11);
                data.add((quantity * potency) * percentage);
                data.add((quantity * potency) * 0.7);
                return data;
            case 19:
                percentage = 47 - (quantity - 11);
                data.add((quantity * potency) * percentage);
                data.add((quantity * potency) * 0.7);
                return data;
            case 20:
                percentage = 47 - (quantity - 11);
                data.add((quantity * potency) * percentage);
                data.add((quantity * potency) * 0.7);
                return data;
            case 21:
                percentage = 47 - (quantity - 11);
                data.add((quantity * potency) * percentage);
                data.add((quantity * potency) * 0.7);
                return data;
            case 22:
                percentage = 47 - (quantity - 11);
                data.add((quantity * potency) * percentage);
                data.add((quantity * potency) * 0.7);
                return data;
            case 23:
                data.add((23 * potency) * 0.33);
                data.add((23 * potency) * 0.7);
                return data;
            case 24:
                percentage = 33 - (0.5 * (quantity - 23));
                data.add((quantity * potency) * percentage);
                data.add((quantity * potency) * 0.7);
                return data;
            case 25:
                percentage = 33 - (0.5 * (quantity - 23));
                data.add((quantity * potency) * percentage);
                data.add((quantity * potency) * 0.7);
                return data;
            case 26:
                percentage = 33 - (0.5 * (quantity - 23));
                data.add((quantity * potency) * percentage);
                data.add((quantity * potency) * 0.7);
                return data;
            case 27:
                percentage = 33 - (0.5 * (quantity - 23));
                data.add((quantity * potency) * percentage);
                data.add((quantity * potency) * 0.7);
                return data;
            case 28:
                percentage = 33 - (0.5 * (quantity - 23));
                data.add((quantity * potency) * percentage);
                data.add((quantity * potency) * 0.7);
                return data;
            case 29:
                percentage = 33 - (0.5 * (quantity - 23));
                data.add((quantity * potency) * percentage);
                data.add((quantity * potency) * 0.7);
                return data;
            case 30:
                percentage = 33 - (0.5 * (quantity - 23));
                data.add((quantity * potency) * percentage);
                data.add((quantity * potency) * 0.7);
                return data;
            case 31:
                percentage = 33 - (0.5 * (quantity - 23));
                data.add((quantity * potency) * percentage);
                data.add((quantity * potency) * 0.7);
                return data;
            case 32:
                percentage = 33 - (0.5 * (quantity - 23));
                data.add((quantity * potency) * percentage);
                data.add((quantity * potency) * 0.7);
                return data;
            case 33:
                percentage = 33 - (0.5 * (quantity - 23));
                data.add((quantity * potency) * percentage);
                data.add((quantity * potency) * 0.7);
                return data;
            case 34:
                percentage = 33 - (0.5 * (quantity - 23));
                data.add((quantity * potency) * percentage);
                data.add((quantity * potency) * 0.7);
                return data;
            case 35:
                percentage = 33 - (0.5 * (quantity - 23));
                data.add((quantity * potency) * percentage);
                data.add((quantity * potency) * 0.7);
                return data;
            case 36:
                percentage = 33 - (0.5 * (quantity - 23));
                data.add((quantity * potency) * percentage);
                data.add((quantity * potency) * 0.7);
                return data;
            case 37:
                percentage = 33 - (0.5 * (quantity - 23));
                data.add((quantity * potency) * percentage);
                data.add((quantity * potency) * 0.7);
                return data;
            case 38:
                percentage = 33 - (0.5 * (quantity - 23));
                data.add((quantity * potency) * percentage);
                data.add((quantity * potency) * 0.7);
                return data;
            case 39:
                percentage = 33 - (0.5 * (quantity - 23));
                data.add((quantity * potency) * percentage);
                data.add((quantity * potency) * 0.7);
                return data;
            case 40:
                percentage = 33 - (0.5 * (quantity - 23));
                data.add((quantity * potency) * percentage);
                data.add((quantity * potency) * 0.7);
                return data;
            case 41:
                percentage = 33 - (0.5 * (quantity - 23));
                data.add((quantity * potency) * percentage);
                data.add((quantity * potency) * 0.7);
                return data;
            case 42:
                percentage = 33 - (0.5 * (quantity - 23));
                data.add((quantity * potency) * percentage);
                data.add((quantity * potency) * 0.7);
                return data;
            case 43:
                data.add((quantity * potency) * 0.23);
                data.add((quantity * potency) * 0.7);
                return data;
            default:
                return data;
        }
    }//Fin del método.
    
    /**
     * Método para calcular la demanda de potencia para motores.
     * @param squareRoot 
     * @param voltage 
     * @param total_current 
     * @return Retorna la demanda en potencia para motores.
     */
    public static double calculateDemandForMotors(double squareRoot, int voltage, double total_current) {
        return (Math.sqrt(squareRoot) * voltage) * total_current;
    }//Fin del método.
    
    /**
     * Método para calcular la demanda de potencia para iluminaria y toma corriente.
     * @param total_potency 
     * @return Retorna la demanda en potencia para iluminaria y toma corriente.
     */
    public static double calculateDemandForIluminariaPowerPoint(double total_potency) {
        total_potency = total_potency - 3000;
        total_potency = total_potency - 117000;
        total_potency = (total_potency * 0.25) + 3000 + (117000 * 0.35);
        return total_potency;
    }//Fin del método.
    
}
