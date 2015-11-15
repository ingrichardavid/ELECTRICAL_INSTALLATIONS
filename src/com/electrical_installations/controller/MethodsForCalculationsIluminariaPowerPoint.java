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
import com.electrical_installations.model.enums.TypeOccupancyRate;
import com.electrical_installations.model.enums.TypeResistancesAndReactances;
import com.electrical_installations.model.enums.TypeRush;
import com.electrical_installations.model.enums.TypeOfBranchCircuitInArea;
import com.electrical_installations.model.enums.TypePhases;
import com.electrical_installations.model.enums.TypeTemperature;
import com.electrical_installations.model.service.ServiceArea;
import com.electrical_installations.model.service.ServiceBreaker;
import com.electrical_installations.model.service.ServiceCaliber;
import com.electrical_installations.model.service.ServiceIntensity;
import com.electrical_installations.model.service.ServiceResistanceReactance;

/**
 * Clase que contiene los métodos necesarios para realizar cálculos de la lógica de negocio.
 * @author Ing. Richard David
 * @version 1
 * @since 02/09/2015
 */
public class MethodsForCalculationsIluminariaPowerPoint {
    
    //Objetos, variables y constantes.     
    protected static final int iluminariaConstantSinglefamilyHome = 25;
    protected static final int powerPointConstant = 180;
    
    private static final double potencyTotalRoominess = 0.35;
    
    private static final double squareRootOfN = 3;    
    
    public static final int currentLimitIluminaria = 20;
    public static final int currentLimitPowerPoint = 15;
    
    private static final double roominessToCalculateCalibres = 0.8;
    private static Calibers caliber;
    private static Breaker breaker;
    
    private static final Messages messages = Messages.getInstance();
    
    /**
     * Método para cálcular la potencia consumida en iluminaria.
     * @param area
     * @return Retorna la potencia calculada para la iluminaria.
     */
    public static double potencyInIluminaria(double area){
        return Methods.round((area * iluminariaConstantSinglefamilyHome),5);
    }//Fin del método
    
    /**
     * Método para cálcular la potencia consumida en toma corrientes.
     * @param quantity
     * @return Retorna la potencia cálculada en toma corrientes.
     */
    public static double potencyInPowerPoint(int quantity){
        return Methods.round((quantity * powerPointConstant), 5);
    }//Fin del método
    
    /**
     * Método para calcular la potencia total acumulada en iluminaria y toma corrientes.
     * @param potencyIluminaria
     * @param potencyPowerPoint
     * @return Retorna la potencia total calculada.
     */
    public static double potencyInIluminariaAndPowerPoint(double potencyIluminaria, double potencyPowerPoint){
        return (potencyIluminaria + potencyPowerPoint) > 3000 ? (((potencyIluminaria + potencyPowerPoint) - 3000) * potencyTotalRoominess) + 3000 : potencyIluminaria + potencyPowerPoint; 
    }//Fin del método.
    
    /**
     * Método para selección de conductores por capacidad de corriente.
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
     * Método para calcular la intensidad de diseño a partir de un calibre dado.
     * @param calibers
     * @return 
     */
    public static Intensity calculate_instensity_design(Calibers calibers){
        return ServiceIntensity.find_intensity_design(calibers);
    }
    
    /**
     * Método para calcular la intensidad final.
     * @param intensity
     * @param limit
     * @return Retorn la intensidad final.
     */
    public static double calculateIntesityFinal(double intensity, int limit){        
        int branchCircuit = 1;
        while ((intensity / branchCircuit) > limit){
            branchCircuit++;
        }        
        return Methods.round((intensity / branchCircuit),5);        
    }//Fin del Método
    
    /**
     * Método para calcular calibres.
     * @param type
     * @param areaOrQuantityPowerPoint
     * @param voltage
     * @param material
     * @param temperature
     * @param powerFactor
     * @param phase
     * @return Retorna un calibre
     */
    public static Calibers calculateCaliberIluminariaPowerPoint(TypeOfBranchCircuitInArea type,double areaOrQuantityPowerPoint,Voltage voltage,Material material,Temperature temperature,double powerFactor,int phase){
        double intensity;
        caliber = null;
        if (type.equals(TypeOfBranchCircuitInArea.ILUMINARIA)) {
            
            intensity = calculateIntesityFinal(intensity(potencyInIluminaria(areaOrQuantityPowerPoint), voltage.getVoltage(), powerFactor, phase), currentLimitIluminaria);
            caliber = ServiceArea.find_caliber_iluminaria_power_point(new Calibers(0,material, temperature, new Intensity(0, null, intensity),null), roominessToCalculateCalibres);
            caliber.setBranchCircuit(calculateBranchCircuit(intensity(potencyInIluminaria(areaOrQuantityPowerPoint), voltage.getVoltage(), powerFactor, phase), currentLimitIluminaria));
            
        } else if (type.equals(TypeOfBranchCircuitInArea.POWER_POINT)) {
            
            intensity = calculateIntesityFinal(intensity(potencyInPowerPoint((int)areaOrQuantityPowerPoint), voltage.getVoltage(), powerFactor, phase), currentLimitPowerPoint);
            caliber = ServiceArea.find_caliber_iluminaria_power_point(new Calibers(0,material, temperature, new Intensity(0, null, intensity),null), roominessToCalculateCalibres);
            caliber.setBranchCircuit(calculateBranchCircuit(intensity(potencyInIluminaria(areaOrQuantityPowerPoint), voltage.getVoltage(), powerFactor, phase), currentLimitPowerPoint));

        }        
        return caliber;        
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
     * Método para validar si el calibre seleccionado tiene Reactancias y Resistencais asociadas.
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
     * @return retorna el número de calibres.
     */
    public static String number_of_calibers(Phase phase){
        if (phase.getName().equalsIgnoreCase(TypePhases.SINGLE_PHASE_TWO_THREAD.getPhase())){
            return "2 Cables";
        } else if (phase.getName().equalsIgnoreCase(TypePhases.SINGLE_PHASE_THREE_THREAD.getPhase())){
            return "3 Cables";
        } else if (phase.getName().equalsIgnoreCase(TypePhases.PHASE_FOUR_THREAD.getPhase())){
            return "4 Cables";
        }
        return "0 Cables";
    }//Fin del método.
    
    /**
     * Método para calcular el Interruptor a utilizar dependiendo de la capacidad de corriente.
     * @param type
     * @param areaOrQuantityPowerPoint
     * @param voltage
     * @param material
     * @param powerFactor
     * @param phase
     * @param intensityDesign
     * @return Retorna un Interruptor
     */
    public static Breaker find_breaker(TypeOfBranchCircuitInArea type,double areaOrQuantityPowerPoint,Voltage voltage,Material material,double powerFactor,int phase, Intensity intensityDesign){
        breaker = null;
        if (type.equals(TypeOfBranchCircuitInArea.ILUMINARIA)) {
            breaker = ServiceBreaker.find_breaker_by_capacity(new Breaker(0, (calculateIntesityFinal(intensity(potencyInIluminaria(areaOrQuantityPowerPoint), voltage.getVoltage(), powerFactor, phase), currentLimitIluminaria) + intensityDesign.getIntensity()) / 2));
        } else if (type.equals(TypeOfBranchCircuitInArea.POWER_POINT)) {
            breaker = ServiceBreaker.find_breaker_by_capacity(new Breaker(0, (calculateIntesityFinal(intensity(potencyInIluminaria(areaOrQuantityPowerPoint), voltage.getVoltage(), powerFactor, phase), currentLimitPowerPoint) + intensityDesign.getIntensity()) / 2));
        }  
        return breaker;
    }//Fin del método
      
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
     * @param area
     * @param constant
     * @param length
     * @param voltage
     * @param reactance
     * @param powerFactor
     * @param resistance
     * @param angle
     * @return Retorna la caída de voltaje
     */
    public static double breakdownVoltage(double area, int constant, double length, double voltage, double reactance, double powerFactor, double resistance, double angle){
        return Methods.round((((area * constant)/powerFactor)/1000) * (length/1000) * (((resistance * powerFactor) + (reactance * Math.sin(angle)))/(10 * Math.pow(voltage/1000, 2))), 5);
    }//Fin del Método
    
    /**
     * Cálcula el número de circuítos ramales.
     * @param intensity
     * @param limit
     * @return Retorna el número de circuítos ramales a utilizar.
     */
    public static int calculateBranchCircuit(double intensity, int limit){        
        int iterations = 1;
        while ((intensity / iterations) > limit){
            iterations++;
        }        
        return iterations;        
    }//Fin del método.
    
    /**
     * Método para calcular tubería para iluminaria y toma corriente.
     * @param caliber
     * @param phase
     * @param materialPipeline
     * @return Retorna la tubería seleccionada para iluminaria o toma corriente.
     */
    public static String calculate_pipeline_iluminaria_powerPoint(Caliber caliber, Phase phase, String materialPipeline) {
        double area = 0;
        int number_calibers = 0;
        if (phase.getName().equalsIgnoreCase(TypePhases.SINGLE_PHASE_TWO_THREAD.getPhase())) {
            number_calibers = 2;
        } else if (phase.getName().equalsIgnoreCase(TypePhases.SINGLE_PHASE_THREE_THREAD.getPhase())) {
            number_calibers = 3;
        } else if (phase.getName().equalsIgnoreCase(TypePhases.PHASE_FOUR_THREAD.getPhase())) {
            number_calibers = 4;
        }
        area = ServiceCaliber.find_area(caliber) * number_calibers;
        if (number_calibers > 2) {
            area = area / TypeOccupancyRate.FORTY.getPercentage();
            return "1 Φ " + (ServiceCaliber.find_pipeline(new Caliber(0, area), TypeOccupancyRate.FORTY)).getSize() + "\" " + materialPipeline; 
        } else {
            area = area / TypeOccupancyRate.THIRTY_ONE_PERCENT.getPercentage();
            return "1 Φ " + (ServiceCaliber.find_pipeline(new Caliber(0, area), TypeOccupancyRate.THIRTY_ONE_PERCENT)).getSize() + "\" " + materialPipeline;
        }
    }//Fin del Método.
    
    /**
     * Método para calcular tubería.
     * @param caliberPhase
     * @param caliberNeutral
     * @param caliberHeart
     * @param phase
     * @param materialPipeline
     * @return Retorna la tubería seleccionada.
     */
    public static String calculate_pipeline(Caliber caliberPhase, Caliber caliberNeutral, Caliber caliberHeart,Phase phase, String materialPipeline) {
        double total_area = 0;
        int number_calibers = 0;
        if (phase.getName().equalsIgnoreCase(TypePhases.SINGLE_PHASE_TWO_THREAD.getPhase())) {
            number_calibers = 1;
        } else if (phase.getName().equalsIgnoreCase(TypePhases.SINGLE_PHASE_THREE_THREAD.getPhase())) {
            number_calibers = 2;
        } else if (phase.getName().equalsIgnoreCase(TypePhases.PHASE_FOUR_THREAD.getPhase())) {
            number_calibers = 3;
        }
        total_area = (ServiceCaliber.find_area(caliberPhase) * number_calibers) + ServiceCaliber.find_area(caliberNeutral) + (caliberHeart == null ? 0 : ServiceCaliber.find_area(caliberHeart)); 
        number_calibers = number_calibers + 1 + (caliberHeart == null ? 0 : 1);
        if (number_calibers > 2) {
            total_area = total_area / TypeOccupancyRate.FORTY.getPercentage();
            return "1 Φ " + (ServiceCaliber.find_pipeline(new Caliber(0, total_area), TypeOccupancyRate.FORTY)).getSize() + "\" " + materialPipeline; 
        } else {
            total_area = total_area / TypeOccupancyRate.THIRTY_ONE_PERCENT.getPercentage();
            return "1 Φ " + (ServiceCaliber.find_pipeline(new Caliber(0, total_area), TypeOccupancyRate.THIRTY_ONE_PERCENT)).getSize() + "\" " + materialPipeline;
        }
    }//Fin del Método.
    
}
