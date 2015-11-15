/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.controller;
import com.electrical_installations.configuration.Messages;
import com.electrical_installations.configuration.MessagesStructure;
import com.electrical_installations.model.entity.InstallationMotors;
import com.electrical_installations.model.entity.Project;
import com.electrical_installations.model.entity.TypeOfInstallation;
import com.electrical_installations.model.entity.masters.Breaker;
import com.electrical_installations.model.entity.masters.Caliber;
import com.electrical_installations.model.entity.masters.Calibers;
import com.electrical_installations.model.entity.masters.CalibersHearth;
import com.electrical_installations.model.entity.masters.Duct;
import com.electrical_installations.model.entity.masters.HorsePower;
import com.electrical_installations.model.entity.masters.HorsesPowers;
import com.electrical_installations.model.entity.masters.Intensity;
import com.electrical_installations.model.entity.masters.Material;
import com.electrical_installations.model.entity.masters.PercentageOfThreePhaseMotors;
import com.electrical_installations.model.entity.masters.PercentageSinglePhaseMotors;
import com.electrical_installations.model.entity.masters.Phase;
import com.electrical_installations.model.entity.masters.ResistanceReactance;
import com.electrical_installations.model.entity.masters.Temperature;
import com.electrical_installations.model.entity.masters.Voltage;
import com.electrical_installations.model.enums.TypeCalibers;
import com.electrical_installations.model.enums.TypeMaterials;
import com.electrical_installations.model.enums.TypeOfBranchCircuitInArea;
import com.electrical_installations.model.enums.TypePhases;
import com.electrical_installations.model.enums.TypeRush;
import com.electrical_installations.model.service.ServiceCaliber;
import com.electrical_installations.model.service.ServiceDuct;
import com.electrical_installations.model.service.ServiceHorsePorwer;
import com.electrical_installations.model.service.ServiceInstallationMotors;
import com.electrical_installations.model.service.ServiceMaterial;
import com.electrical_installations.model.service.ServicePercentageOfThreePhaseMotors;
import com.electrical_installations.model.service.ServicePercentageSinglePhaseMotors;
import com.electrical_installations.model.service.ServicePhase;
import com.electrical_installations.model.service.ServiceTemperature;
import com.electrical_installations.model.service.ServiceVoltage;
import com.electrical_installations.view.ViewAddMotorToInstallation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Controlador para la vista ViewAddElevatorToInstallation
 *
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-22
 */
public class ControllerElevatorInInstallation implements ActionListener, ChangeListener , ItemListener{

    //Objectos, variables y constantes
    private final ViewAddMotorToInstallation viewAddElevatorToInstallation;
    private String material;
    private List<HorsePower> horsesPowerFound;
    private static final Messages messages = Messages.getInstance();
    private List<PercentageOfThreePhaseMotors> percentageOfThreePhaseMotorsFounds;
    private List<PercentageSinglePhaseMotors> percentageSinglePhaseMotorsFound;
    private List<Material> materialsFound;
    private List<Temperature> temperaturesFound;
    private List<Phase> phasesFound;
    private List<Duct> ductsFound;
    private List<Voltage> voltagesFound;
    private List<Caliber> calibersFound;
    private HorsesPowers horsesPowersFound;
    private Breaker breakerPhaseFound, breakerPhasePersistFound;
    private Calibers caliberPhaseFound, caliberNeutralFound;
    private CalibersHearth calibersHearthFound;
    private String caliberPhase, caliberNeutral, caliberHearth;
    private TypeRush typeCaliber;
    private ResistanceReactance resistance,reactance;
    private Caliber caliberSelected, caliberSelectedNeutral;
    private double potency;
    private double breakdownVoltage;
    private Intensity intensityDesignFound;
    
    /**
     * Contructor de la clase, recibe un objeto ViewAddElevatorToInstallation
     *
     * @param viewAddElevatorToInstallation
     */
    public ControllerElevatorInInstallation(ViewAddMotorToInstallation viewAddElevatorToInstallation) {
        this.viewAddElevatorToInstallation = viewAddElevatorToInstallation;
    }//Fin del constructor 
    
     /**
     * Método para llenar el combo HP.
     */
    public void fill_single_phases_HP() {
        horsesPowerFound = ServiceHorsePorwer.find_horses_power();
        if (horsesPowerFound != null) {
            for (HorsePower horsePower : horsesPowerFound) {
                viewAddElevatorToInstallation.getCmbHP().addItem(horsePower); 
            }
            viewAddElevatorToInstallation.getCmbHP().setSelectedIndex(0); 
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.HORSES_POWER_NO_FOUND), MessagesStructure.justify));
            viewAddElevatorToInstallation.dispose();
        }
    }//Fin del método
    
    /**
     * Método para llenar el combo porcentaje de motores Monofásico.
     */
    public void fill_combo_percentage_single_phase_motors() {
        percentageSinglePhaseMotorsFound = ServicePercentageSinglePhaseMotors.find_percentage_motors_single_phase();
        if (percentageSinglePhaseMotorsFound != null) {
            for (PercentageSinglePhaseMotors  percentageSinglePhaseMotors : percentageSinglePhaseMotorsFound) {
                viewAddElevatorToInstallation.getCmbPercentageSinglePhaseMotors().addItem(percentageSinglePhaseMotors); 
            }
            viewAddElevatorToInstallation.getCmbPercentageSinglePhaseMotors().setSelectedIndex(0); 
        } else { 
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.PERCENTAGE_SINGLE_PHASE_MOTORS_NO_FOUND), MessagesStructure.justify));
            viewAddElevatorToInstallation.dispose();
        }
    }//Fin del método
    
    /**
     * Método para llenar el combo HP cuando se ha selecionado Trifásica.
     */
    public void fill_three_phases_HP() {
        horsesPowerFound = ServiceHorsePorwer.find_horses_power_threephases();
        if (horsesPowerFound != null) {
            for (HorsePower horsePower : horsesPowerFound) {
                viewAddElevatorToInstallation.getCmbHP().addItem(horsePower); 
            }
            viewAddElevatorToInstallation.getCmbHP().setSelectedIndex(0); 
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.HORSES_POWER_NO_FOUND), MessagesStructure.justify));
            viewAddElevatorToInstallation.dispose();
        }
    }//Fin del método

    /**
     * Método para llenar el combo porcentaje de motores trifásicos.
     * @param percentageOfThreePhaseMotors 
     */
    public void fill_combo_percentaje_three_phase_motors(PercentageOfThreePhaseMotors percentageOfThreePhaseMotors){
        percentageOfThreePhaseMotorsFounds = ServicePercentageOfThreePhaseMotors.find_percentage_motors_three_phase(percentageOfThreePhaseMotors);
        if (percentageOfThreePhaseMotorsFounds != null) {
            for (PercentageOfThreePhaseMotors percentageOfThreePhaseMotorsFound : percentageOfThreePhaseMotorsFounds) {
                viewAddElevatorToInstallation.getCmbPercentageSinglePhaseMotors().addItem(percentageOfThreePhaseMotorsFound); 
            }
            viewAddElevatorToInstallation.getCmbPercentageSinglePhaseMotors().setSelectedIndex(0); 
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200,messages.getProperty(Messages.PERCENTAGE_THREE_PHASE_MOTORS_NO_FOUND), MessagesStructure.justify));
            viewAddElevatorToInstallation.dispose();
        }
    }//Fin del Método.
    
     /**
     * Método para llenar los combos con Materiales.
     */
    public void fill_combos_materials(){        
        materialsFound = ServiceMaterial.find_materials();
        if (materialsFound != null){
            for (Material material : materialsFound){
                viewAddElevatorToInstallation.getCmbMaterial().addItem(material);
            }     
                viewAddElevatorToInstallation.getCmbMaterial().setSelectedIndex(0);
        } else { 
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.MATERIALS_NO_FOUND), MessagesStructure.justify));
            viewAddElevatorToInstallation.dispose();
        }       
    }//Fin del método
    
        /**
     * Método para llenar los combos con temperaturas.
     */
    public void fill_combos_temperatures() {
        temperaturesFound = ServiceTemperature.find_temperatures();
        if (temperaturesFound != null) {
            for (Temperature temperature : temperaturesFound) {
                viewAddElevatorToInstallation.getCmbTemperature().addItem(temperature); 
            }
            viewAddElevatorToInstallation.getCmbTemperature().setSelectedIndex(0); 
        } else { 
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.TEMPERATURES_NO_FOUND), MessagesStructure.justify));
            viewAddElevatorToInstallation.dispose();
        }
    }//Fin del método
    
    /**
     * Método para llenar los combos con Fases.
     * @param typePhases
     */
    public void fill_combos_phases(TypePhases typePhases) {        
        phasesFound = ServicePhase.find_phases();
        List<Phase> phases = new ArrayList<>();
        if (phasesFound != null) {
            for (Phase phase : phasesFound) {
                if (typePhases.equals(TypePhases.PHASE_FOUR_THREAD) 
                        && phase.getName().equalsIgnoreCase(TypePhases.PHASE_FOUR_THREAD.getPhase())){
                    viewAddElevatorToInstallation.getCmbPhases().addItem(phase);
                } else {
                    if (! typePhases.equals(TypePhases.PHASE_FOUR_THREAD) && !phase.getName().equalsIgnoreCase(TypePhases.PHASE_FOUR_THREAD.getPhase())){
                        viewAddElevatorToInstallation.getCmbPhases().addItem(phase); 
                    }                   
                }
            }
            viewAddElevatorToInstallation.getCmbPhases().setSelectedIndex(0); 
        } else { 
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.PHASES_NO_FOUND), MessagesStructure.justify));
            viewAddElevatorToInstallation.dispose();
        }
    }//Fin del método
    
     /**
     * Método para llenar los combos con Ductos.
     */
    public void fill_combos_ducts(){        
        ductsFound = ServiceDuct.find_ducts();
        if (ductsFound != null){
            for (Duct duct : ductsFound){
                viewAddElevatorToInstallation.getCmbDuct().addItem(duct);
            }     
            viewAddElevatorToInstallation.getCmbDuct().setSelectedIndex(0);
        } else { 
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.DUCTS_NO_FOUND), MessagesStructure.justify));
            viewAddElevatorToInstallation.dispose();
        }       
    }//Fin del método
    
    /**
     * Método para llenar los combos con Voltages.
     */
    public void fill_combos_voltages() {
        voltagesFound = ServiceVoltage.find_voltages();
        if (voltagesFound != null) {
            viewAddElevatorToInstallation.getCmbVoltage().removeAllItems();
            for (Voltage voltage : voltagesFound) {
                if (((Phase)viewAddElevatorToInstallation.getCmbPhases().getSelectedItem()).getName().equalsIgnoreCase(TypePhases.SINGLE_PHASE_TWO_THREAD.getPhase())
                        && voltage.getVoltage() < 200){
                    viewAddElevatorToInstallation.getCmbVoltage().addItem(voltage);                    
                } else if ((((Phase)viewAddElevatorToInstallation.getCmbPhases().getSelectedItem()).getName().equalsIgnoreCase(TypePhases.SINGLE_PHASE_THREE_THREAD.getPhase())
                        || ((Phase)viewAddElevatorToInstallation.getCmbPhases().getSelectedItem()).getName().equalsIgnoreCase(TypePhases.PHASE_FOUR_THREAD.getPhase()))
                        && voltage.getVoltage() >= 200){
                    viewAddElevatorToInstallation.getCmbVoltage().addItem(voltage);                     
                }
            }
            viewAddElevatorToInstallation.getCmbVoltage().setSelectedIndex(0); 
        } else { 
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.VOLTAGES_NO_FOUND), MessagesStructure.justify));
            viewAddElevatorToInstallation.dispose();
        }
    }//Fin del método
    
 
    /**
     * Método para llenar los combos con Voltages para Mótores Trifásicos.
     */
    public void fill_combos_voltages_motores_trifasicos() {
        voltagesFound = ServiceVoltage.find_voltages_motors_three_phases();
        if (voltagesFound != null) {
            viewAddElevatorToInstallation.getCmbVoltage().removeAllItems();
            for (Voltage voltage : voltagesFound) {
                if (((Phase)viewAddElevatorToInstallation.getCmbPhases().getSelectedItem()).getName().equalsIgnoreCase(TypePhases.SINGLE_PHASE_TWO_THREAD.getPhase())
                        && voltage.getVoltage() < 200){
                    viewAddElevatorToInstallation.getCmbVoltage().addItem(voltage);                    
                } else if ((((Phase)viewAddElevatorToInstallation.getCmbPhases().getSelectedItem()).getName().equalsIgnoreCase(TypePhases.SINGLE_PHASE_THREE_THREAD.getPhase())
                        || ((Phase)viewAddElevatorToInstallation.getCmbPhases().getSelectedItem()).getName().equalsIgnoreCase(TypePhases.PHASE_FOUR_THREAD.getPhase()))
                        && voltage.getVoltage() >= 200){
                    viewAddElevatorToInstallation.getCmbVoltage().addItem(voltage);                     
                }
            }
            viewAddElevatorToInstallation.getCmbVoltage().setSelectedIndex(0); 
        } else { 
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.VOLTAGES_NO_FOUND), MessagesStructure.justify));
            viewAddElevatorToInstallation.dispose();
        }
    }//Fin del método
  

     /**
     * Método para llenar los combos con Calibers.
     */
    public void fill_combos_calibers(){
        calibersFound = ServiceCaliber.find_caliber();
        if (calibersFound != null){
            for (Caliber caliber : calibersFound){
                viewAddElevatorToInstallation.getCmbCaliber().addItem(caliber);
                viewAddElevatorToInstallation.getCmbCalibersNeutral().addItem(caliber);
            }     
            viewAddElevatorToInstallation.getCmbCaliber().setSelectedIndex(0);
                viewAddElevatorToInstallation.getCmbCalibersNeutral().setSelectedIndex(0);
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.CALIBERS_NO_FOUND), MessagesStructure.justify));
            viewAddElevatorToInstallation.dispose();
        }       
    }//Fin del método
    
    /**
     * Método para calcular el conductor.
     */
    
    private void calculate_conductor(){        
        if (!viewAddElevatorToInstallation.getrBtnAir().isSelected() && !viewAddElevatorToInstallation.getrBtnGround().isSelected()){
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.RUSH_NO_FOUND), MessagesStructure.justify));
            viewAddElevatorToInstallation.getrBtnGround().requestFocus();
        }else{
            potency = MethodsForCalculationsGlobal1.getPotencyHorsePower(((HorsePower)viewAddElevatorToInstallation.getCmbHP().getSelectedItem()).getValue());
            horsesPowersFound = ServiceHorsePorwer.find_intensity_horses_power(
                    new HorsesPowers(
                            0, 
                            new HorsePower(
                                    ((HorsePower)viewAddElevatorToInstallation.getCmbHP().getSelectedItem()).getCode(), 
                                    ((HorsePower)viewAddElevatorToInstallation.getCmbHP().getSelectedItem()).getName(), 
                                    0), 
                            (Voltage)viewAddElevatorToInstallation.getCmbVoltage().getSelectedItem(), 
                            null,
                            viewAddElevatorToInstallation.getTypePhase()));
            if (horsesPowersFound == null){
                MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.HORSES_POWER_INTENSITY_NO_FOUND), MessagesStructure.justify));
            } else {
                caliberPhaseFound = MethodsForCalculationsGlobal1.calculateCaliberDishwasherAndCrusher(
                    horsesPowersFound.getIntensity().getIntensity(),
                    (Material)viewAddElevatorToInstallation.getCmbMaterial().getSelectedItem(), 
                    (Temperature)viewAddElevatorToInstallation.getCmbTemperature().getSelectedItem());  

                caliberNeutralFound = caliberPhaseFound;

                calibersHearthFound = MethodsForCalculationsGlobal1.calculate_caliberHearth_dishwasherAndCrusher(horsesPowersFound.getIntensity().getIntensity());

                if (viewAddElevatorToInstallation.getTypePhases().equals(TypePhases.SINGLE_PHASE_THREE_THREAD)){
                  breakerPhaseFound = MethodsForCalculationsGlobal1.find_braker_dishwasherAndCrusher(
                        horsesPowersFound.getIntensity().getIntensity(), 
                        ((PercentageSinglePhaseMotors)viewAddElevatorToInstallation.getCmbPercentageSinglePhaseMotors().getSelectedItem()).getPercentage());                    
                } else {
                  breakerPhaseFound = MethodsForCalculationsGlobal1.find_braker_dishwasherAndCrusher(
                        horsesPowersFound.getIntensity().getIntensity(), 
                        ((PercentageOfThreePhaseMotors)viewAddElevatorToInstallation.getCmbPercentageSinglePhaseMotors().getSelectedItem()).getPercentage());                    
                }

                if (caliberPhaseFound == null){
                    MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.CALIBER_NO_FOUND), MessagesStructure.justify));
                } else {
                    viewAddElevatorToInstallation.getCmbCaliber().setSelectedItem(caliberPhaseFound.getCaliber());
                    viewAddElevatorToInstallation.getCmbCalibersNeutral().setSelectedItem(caliberPhaseFound.getCaliber());
                    if (((Material)viewAddElevatorToInstallation.getCmbMaterial().getSelectedItem()).getName().equals(TypeMaterials.COOPER.getMaterial())){                
                        viewAddElevatorToInstallation.getLblCaliberPhase().setText(MethodsForCalculationsGlobal1.number_of_calibers((Phase)viewAddElevatorToInstallation.getCmbPhases().getSelectedItem(), TypeCalibers.PHASE) + " #" + caliberPhaseFound.getCaliber().getName() + " Cu " + MethodsForCalculationsGlobal1.typeCaliber(typeCaliber,(Temperature)viewAddElevatorToInstallation.getCmbTemperature().getSelectedItem()) + " " + MethodsForCalculationsGlobal1.number_of_brakers((Phase)viewAddElevatorToInstallation.getCmbPhases().getSelectedItem(), breakerPhaseFound.getCapacity()));
                        viewAddElevatorToInstallation.getLblCaliberNeutral().setText("1 Cable" + " #" + caliberPhaseFound.getCaliber().getName() + " Cu " + MethodsForCalculationsGlobal1.typeCaliber(typeCaliber,(Temperature)viewAddElevatorToInstallation.getCmbTemperature().getSelectedItem()));
                    } else if (((Material)viewAddElevatorToInstallation.getCmbMaterial().getSelectedItem()).getName().equals(TypeMaterials.ALUMINIUM.getMaterial())) {
                        viewAddElevatorToInstallation.getLblCaliberPhase().setText(MethodsForCalculationsGlobal1.number_of_calibers((Phase)viewAddElevatorToInstallation.getCmbPhases().getSelectedItem(), TypeCalibers.PHASE) + " #" + caliberPhaseFound.getCaliber().getName() + " Al " + MethodsForCalculationsGlobal1.typeCaliber(typeCaliber,(Temperature)viewAddElevatorToInstallation.getCmbTemperature().getSelectedItem()) + " " + MethodsForCalculationsGlobal1.number_of_brakers((Phase)viewAddElevatorToInstallation.getCmbPhases().getSelectedItem(), breakerPhaseFound.getCapacity()));
                        viewAddElevatorToInstallation.getLblCaliberNeutral().setText("1 Cable" + " #" + caliberPhaseFound.getCaliber().getName() + " Al " + MethodsForCalculationsGlobal1.typeCaliber(typeCaliber,(Temperature)viewAddElevatorToInstallation.getCmbTemperature().getSelectedItem()));
                    }
                    if (calibersHearthFound == null){
                        viewAddElevatorToInstallation.getLblCaliberEarth().setText("No aplica");
                    } else {
                        viewAddElevatorToInstallation.getLblCaliberEarth().setText("1 Cable " + calibersHearthFound.getCaliber().getName() + " " + MethodsForCalculationsGlobal1.typeCaliber(typeCaliber,(Temperature)viewAddElevatorToInstallation.getCmbTemperature().getSelectedItem()));
                    }
                    viewAddElevatorToInstallation.getBtnCalculateBreakdown().doClick(); 
                }                     
            }
        }
    }
    
     /**
     * Método para calcular reactancia
     * @return Retorna un objeto ResistanceReactance que almacena el valor de la reactancia.
     */
    private ResistanceReactance calculate_reactance(TypeOfBranchCircuitInArea typeOfBranchCircuitInArea){   
        if (typeOfBranchCircuitInArea == TypeOfBranchCircuitInArea.NEUTRAL){
            return  MethodsForCalculationsGlobal1.calculate_reactance(
                (Material)viewAddElevatorToInstallation.getCmbMaterial().getSelectedItem(),
                (Caliber)viewAddElevatorToInstallation.getCmbCalibersNeutral().getSelectedItem(), 
                (Duct)viewAddElevatorToInstallation.getCmbDuct().getSelectedItem());  
        } else {
            return  MethodsForCalculationsGlobal1.calculate_reactance(
                (Material)viewAddElevatorToInstallation.getCmbMaterial().getSelectedItem(),
                (Caliber)viewAddElevatorToInstallation.getCmbCaliber().getSelectedItem(), 
                (Duct)viewAddElevatorToInstallation.getCmbDuct().getSelectedItem());        
        }             
    }//Fin del método
    
     /**
     * Método para calcular resistencia
     * @return Retorna un objeto ResistanceReactance que almacena el valor de la resistencia.
     */
    private ResistanceReactance calculate_resistance(TypeOfBranchCircuitInArea typeOfBranchCircuitInArea){
        if (typeOfBranchCircuitInArea == TypeOfBranchCircuitInArea.NEUTRAL){
            return MethodsForCalculationsGlobal1.calculate_resistance(
                (Material)viewAddElevatorToInstallation.getCmbMaterial().getSelectedItem(),
                (Caliber)viewAddElevatorToInstallation.getCmbCalibersNeutral().getSelectedItem(), 
                (Duct)viewAddElevatorToInstallation.getCmbDuct().getSelectedItem());
        } else {
            return MethodsForCalculationsGlobal1.calculate_resistance(
                (Material)viewAddElevatorToInstallation.getCmbMaterial().getSelectedItem(),
                (Caliber)viewAddElevatorToInstallation.getCmbCaliber().getSelectedItem(), 
                (Duct)viewAddElevatorToInstallation.getCmbDuct().getSelectedItem());            
        }
    }//Fin del método
    
     /**
     * Método para calcular la caída de voltage.
     */
    private void calculate_breakdownVoltage(){
        if (caliberPhaseFound == null || caliberNeutralFound == null || calibersHearthFound == null){
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.AREA_CAPACITY_INTENSITY_NO_FOUND), MessagesStructure.justify));
        } else {
            if (MethodsForCalculationsGlobal1.validate_caliber((Caliber)viewAddElevatorToInstallation.getCmbCaliber().getSelectedItem())
                    && MethodsForCalculationsGlobal1.validate_caliber((Caliber)viewAddElevatorToInstallation.getCmbCalibersNeutral().getSelectedItem())){       
                resistance = calculate_resistance(TypeOfBranchCircuitInArea.ILUMINARIA);
                reactance  = calculate_reactance(TypeOfBranchCircuitInArea.ILUMINARIA);
                if (resistance != null){     
                    breakdownVoltage = MethodsForCalculationsGlobal1.breakdownVoltage(
                            potency,  
                            Double.valueOf(viewAddElevatorToInstallation.getJspLength().getValue().toString()), 
                            ((Voltage)viewAddElevatorToInstallation.getCmbVoltage().getSelectedItem()).getVoltage(), 
                            reactance.getValue().getValour(), 
                            Double.valueOf(viewAddElevatorToInstallation.getJspPowerFactor().getValue().toString()), 
                            resistance.getValue().getValour(), 
                            Double.valueOf(viewAddElevatorToInstallation.getJspAngle().getValue().toString()));   
                    viewAddElevatorToInstallation.getLblBreakdownVoltage().setText(String.valueOf(breakdownVoltage) + " %"); 
                    caliberSelected = (Caliber)viewAddElevatorToInstallation.getCmbCaliber().getSelectedItem(); 
                    
                    if (viewAddElevatorToInstallation.getTypePhases().equals(TypePhases.SINGLE_PHASE_THREE_THREAD)){
                        breakerPhasePersistFound = MethodsForCalculationsGlobal1.find_braker_dishwasherAndCrusher(
                            horsesPowersFound.getIntensity().getIntensity(), 
                            ((PercentageSinglePhaseMotors)viewAddElevatorToInstallation.getCmbPercentageSinglePhaseMotors().getSelectedItem()).getPercentage());                    
                    } else {
                        breakerPhasePersistFound = MethodsForCalculationsGlobal1.find_braker_dishwasherAndCrusher(
                          horsesPowersFound.getIntensity().getIntensity(), 
                          ((PercentageOfThreePhaseMotors)viewAddElevatorToInstallation.getCmbPercentageSinglePhaseMotors().getSelectedItem()).getPercentage());                    
                    }
                                                            
                    resistance = calculate_resistance(TypeOfBranchCircuitInArea.NEUTRAL);
                    reactance  = calculate_reactance(TypeOfBranchCircuitInArea.NEUTRAL);
                    if (resistance != null){                
                        breakdownVoltage = MethodsForCalculationsGlobal1.breakdownVoltage(
                                potency,  
                                Double.valueOf(viewAddElevatorToInstallation.getJspLength().getValue().toString()), 
                                ((Voltage)viewAddElevatorToInstallation.getCmbVoltage().getSelectedItem()).getVoltage(), 
                                reactance.getValue().getValour(), 
                                Double.valueOf(viewAddElevatorToInstallation.getJspPowerFactor().getValue().toString()), 
                                resistance.getValue().getValour(), 
                                Double.valueOf(viewAddElevatorToInstallation.getJspAngle().getValue().toString()));  
                        viewAddElevatorToInstallation.getLblBreakdownVoltageNeutral().setText(String.valueOf(breakdownVoltage) + " %");                     
                        caliberSelectedNeutral = (Caliber)viewAddElevatorToInstallation.getCmbCalibersNeutral().getSelectedItem(); 
                        caliberPhase = viewAddElevatorToInstallation.getLblCaliberPhase().getText();
                        caliberPhase = caliberPhase.replace(
                                "#" + caliberPhaseFound.getCaliber().getName(), 
                                "#" + caliberSelected.getName());
                        caliberPhase = caliberPhase.replace(
                                MethodsForCalculationsGlobal1.number_of_brakers((Phase)viewAddElevatorToInstallation.getCmbPhases().getSelectedItem(),breakerPhaseFound.getCapacity()),
                                MethodsForCalculationsGlobal1.number_of_brakers((Phase)viewAddElevatorToInstallation.getCmbPhases().getSelectedItem(), breakerPhasePersistFound.getCapacity()));
                        caliberNeutral = viewAddElevatorToInstallation.getLblCaliberNeutral().getText();
                        caliberNeutral = caliberNeutral.replace(
                                "#" + caliberNeutralFound.getCaliber().getName(), 
                                "#" + caliberSelectedNeutral.getName());
                        caliberHearth = viewAddElevatorToInstallation.getLblCaliberEarth().getText();
                    }
                }  
            }            
        }
    }//Fin del método
    
    private void register_intallation_motors(){
        if (caliberPhaseFound == null || caliberNeutralFound == null || calibersHearthFound == null){    
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.AREA_CAPACITY_INTENSITY_NO_FOUND), MessagesStructure.justify));
        } else {
            if (ServiceInstallationMotors.create_installation_motors(new InstallationMotors(
                    0, 
                    new Project(
                            viewAddElevatorToInstallation.getProject().getCode(), 
                            null, 
                            new TypeOfInstallation(
                                    viewAddElevatorToInstallation.getProject().getTypeOfInstallation().getCode(), 
                                    null), 
                            null, 
                            0, 
                            null), 
                    viewAddElevatorToInstallation.getLblDescription().getText(), 
                    horsesPowersFound.getIntensity().getIntensity(), 
                    Integer.valueOf(viewAddElevatorToInstallation.getJspQuantity().getValue().toString()), 
                    caliberPhase, 
                    caliberNeutral, 
                    caliberHearth,
                    viewAddElevatorToInstallation.getTypePhase(),
                    viewAddElevatorToInstallation.getCmbHP().getSelectedItem().toString(),
                    breakerPhaseFound.getCapacity()))){
                this.viewAddElevatorToInstallation.dispose();
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(viewAddElevatorToInstallation.getBtnAdd())) {
            register_intallation_motors();
        } else if(e.getSource().equals(viewAddElevatorToInstallation.getBtnCalculateCurrentCapacity())){
            calculate_conductor();
        } else if (e.getSource().equals(viewAddElevatorToInstallation.getBtnCalculateBreakdown())) {
            calculate_breakdownVoltage();
        } else if (e.getSource().equals(viewAddElevatorToInstallation.getBtnClose())) {
            viewAddElevatorToInstallation.dispose();
        }  else if (e.getSource().equals(viewAddElevatorToInstallation.getCmbPhases())) {
            this.fill_combos_voltages();
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {    
        if (e.getSource().equals(viewAddElevatorToInstallation.getrBtnAir())){            
            if (viewAddElevatorToInstallation.getrBtnAir().isSelected()){                
                typeCaliber = TypeRush.AIR;                
            }             
        } else if (e.getSource().equals(viewAddElevatorToInstallation.getrBtnGround())){            
            if (viewAddElevatorToInstallation.getrBtnGround().isSelected()){
                typeCaliber = TypeRush.UNDERGROUND;                
            }             
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
         
    }

}
