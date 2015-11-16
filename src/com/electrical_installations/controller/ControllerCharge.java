/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.controller;
 
import com.electrical_installations.configuration.Messages;
import com.electrical_installations.configuration.MessagesStructure;
import com.electrical_installations.global.method.Methods;
import com.electrical_installations.model.entity.Area;
import com.electrical_installations.model.entity.Charge;
import com.electrical_installations.model.entity.ChargesInAreas;
import com.electrical_installations.model.entity.Project;
import com.electrical_installations.model.entity.TypeCharges;
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
import com.electrical_installations.model.entity.masters.PercentageSinglePhaseMotors;
import com.electrical_installations.model.entity.masters.Phase;
import com.electrical_installations.model.entity.masters.ResistanceReactance;
import com.electrical_installations.model.entity.masters.Temperature;
import com.electrical_installations.model.entity.masters.Voltage;
import com.electrical_installations.model.enums.TypeCalibers;
import com.electrical_installations.model.enums.TypeMaterials;
import com.electrical_installations.model.enums.TypeOfBranchCircuitInArea;
import com.electrical_installations.model.enums.TypePhase;
import com.electrical_installations.model.enums.TypePhases;
import com.electrical_installations.model.enums.TypeRush;
import com.electrical_installations.model.enums.TypeSubTypeCharge;
import com.electrical_installations.model.service.ServiceCaliber;
import com.electrical_installations.model.service.ServiceCharge;
import com.electrical_installations.model.service.ServiceChargesInAreas;
import com.electrical_installations.model.service.ServiceDuct;
import com.electrical_installations.model.service.ServiceHorsePorwer;
import com.electrical_installations.model.service.ServiceMaterial;
import com.electrical_installations.model.service.ServicePercentageSinglePhaseMotors;
import com.electrical_installations.model.service.ServicePhase;
import com.electrical_installations.model.service.ServiceTemperature;
import com.electrical_installations.model.service.ServiceVoltage;
import com.electrical_installations.view.ViewCharge; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

/**
 * Controlador para la vista ViewVoltageInCarga
 *
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-22
 */
public class ControllerCharge implements ActionListener, WindowListener , KeyListener, MouseListener, ChangeListener {

    //Objectos, variables y constantes
    private final ViewCharge viewVoltageInCharge;
    private String material;
    private List<Charge> charges;
    private List<Temperature> temperaturesFound;
    private List<Phase> phasesFound;
    private List<Voltage> voltagesFound;
    private List<Caliber> calibersFound;
    private List<Duct> ductsFound;
    private List<Material> materialsFound;
    private List<HorsePower> horsesPowerFound;
    private Calibers caliberPhaseFound, caliberNeutralFound;
    private ResistanceReactance resistance,reactance;
    private Breaker breakerPhaseFound, breakerPhasePersistFound;
    private List<PercentageSinglePhaseMotors> percentageSinglePhaseMotorsFound;
    private char character;
    private TypeRush typeCaliber;
    private double breakdownVoltage;
    private Caliber caliberSelected, caliberSelectedNeutral;
    private String caliberUsePhase;
    private String materialPipeline, caliberPipeline;
    private CalibersHearth calibersHearthFound;
    private HorsesPowers horsesPowersFound;
    private String caliberPhase, caliberNeutral, caliberHearth;
    private double potency;
    private Intensity intensityDesignFound;
    private static final Messages messages = Messages.getInstance();

    /**
     * Contructor de la clase, recibe un objeto ViewArea
     *
     * @param viewVoltageInCharge
     */
    public ControllerCharge(ViewCharge viewVoltageInCharge) {
        this.viewVoltageInCharge = viewVoltageInCharge;
        this.phasesFound = null;
        this.intensityDesignFound = null;
        this.potency = 0;
    }//Fin del constructor 

    /**
     * Método para llenar tabla Cargas.
     */
    public void fill_table_charges() {
        charges = ServiceCharge.find_charges();
        if (charges != null) {
            Methods.removeRows(viewVoltageInCharge.getTblCharges());
            for (Charge charge_data : charges) {
                Object[] data = {charge_data.getCode(), charge_data.getName(), charge_data.getPotency(), charge_data.isHorsePower(), charge_data.isDryer(), charge_data.isElectricKitchen(),charge_data.getTypeCharges().getCode(),charge_data.getTypeCharges().getType()};
                ((DefaultTableModel) viewVoltageInCharge.getTblCharges().getModel()).addRow(data);
            }
        }
    }//Fin del método

    /**
     * Método para llenar los combos con temperaturas.
     */
    public void fill_combos_temperatures() {
        temperaturesFound = ServiceTemperature.find_temperatures();
        if (temperaturesFound != null) {
            for (Temperature temperature : temperaturesFound) {
                viewVoltageInCharge.getCmbTemperature().addItem(temperature); 
            }
            viewVoltageInCharge.getCmbTemperature().setSelectedIndex(0); 
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.TEMPERATURES_NO_FOUND), MessagesStructure.justify));
            viewVoltageInCharge.dispose();
        }
    }//Fin del método

    /**
     * Método para llenar los combos con Fases.
     */
    public void fill_combos_phases() {
        phasesFound = ServicePhase.find_phases();
        if (phasesFound != null) {
            for (Phase phase : phasesFound) {
                viewVoltageInCharge.getCmbPhases().addItem(phase); 
            }
            viewVoltageInCharge.getCmbPhases().setSelectedIndex(0); 
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.PHASES_NO_FOUND), MessagesStructure.justify));
            viewVoltageInCharge.dispose();
        }
    }//Fin del método

    /**
     * Método para llenar los combos con Voltages.
     */
    public void fill_combos_voltages() {
        voltagesFound = ServiceVoltage.find_voltages();
        if (voltagesFound != null) {
            viewVoltageInCharge.getCmbVoltage().removeAllItems();
            for (Voltage voltage : voltagesFound) {
                if (((Phase)viewVoltageInCharge.getCmbPhases().getSelectedItem()).getName().equalsIgnoreCase(TypePhases.SINGLE_PHASE_TWO_THREAD.getPhase())
                        && voltage.getVoltage() < 200){
                    viewVoltageInCharge.getCmbVoltage().addItem(voltage);                    
                } else if ((((Phase)viewVoltageInCharge.getCmbPhases().getSelectedItem()).getName().equalsIgnoreCase(TypePhases.SINGLE_PHASE_THREE_THREAD.getPhase())
                        || ((Phase)viewVoltageInCharge.getCmbPhases().getSelectedItem()).getName().equalsIgnoreCase(TypePhases.PHASE_FOUR_THREAD.getPhase()))
                        && voltage.getVoltage() >= 200){
                    viewVoltageInCharge.getCmbVoltage().addItem(voltage);                     
                }
            }
            viewVoltageInCharge.getCmbVoltage().setSelectedIndex(0); 
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.VOLTAGES_NO_FOUND), MessagesStructure.justify));
            viewVoltageInCharge.dispose();
        }
    }//Fin del método
    
    /**
     * Método para llenar los combos con Calibers.
     */
    public void fill_combos_calibers(){
        calibersFound = ServiceCaliber.find_caliber();
        if (calibersFound != null){
            for (Caliber caliber : calibersFound){
                viewVoltageInCharge.getCmbCaliber().addItem(caliber);
                viewVoltageInCharge.getCmbCalibersNeutral().addItem(caliber);
            }     
            viewVoltageInCharge.getCmbCaliber().setSelectedIndex(0);
                viewVoltageInCharge.getCmbCalibersNeutral().setSelectedIndex(0);
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.CALIBERS_NO_FOUND), MessagesStructure.justify));
            viewVoltageInCharge.dispose();
        }       
    }//Fin del método
    
    /**
     * Método para llenar los combos con Ductos.
     */
    public void fill_combos_ducts(){        
        ductsFound = ServiceDuct.find_ducts();
        if (ductsFound != null){
            for (Duct duct : ductsFound){
                viewVoltageInCharge.getCmbDuct().addItem(duct);
            }     
            viewVoltageInCharge.getCmbDuct().setSelectedIndex(0);
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.DUCTS_NO_FOUND), MessagesStructure.justify));
            viewVoltageInCharge.dispose();
        }       
    }//Fin del método
    
    /**
     * Método para llenar los combos con Materiales.
     */
    public void fill_combos_materials(){        
        materialsFound = ServiceMaterial.find_materials();
        if (materialsFound != null){
            for (Material material : materialsFound){
                viewVoltageInCharge.getCmbMaterial().addItem(material);
            }     
                viewVoltageInCharge.getCmbMaterial().setSelectedIndex(0);
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.MATERIALS_NO_FOUND), MessagesStructure.justify));
            viewVoltageInCharge.dispose();
        }       
    }//Fin del método
    
    /**
     * Método para llenar el combo HP.
     */
    public void fill_combobox_HP() {
        horsesPowerFound = ServiceHorsePorwer.find_horses_power();
        if (horsesPowerFound != null) {
            for (HorsePower horsePower : horsesPowerFound) {
                viewVoltageInCharge.getCmbHP().addItem(horsePower); 
            }
            viewVoltageInCharge.getCmbHP().setSelectedIndex(0); 
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.HORSES_POWER_NO_FOUND), MessagesStructure.justify));
            viewVoltageInCharge.dispose();
        }
    }//Fin del método
    
    /**
     * Método para llenar el combo HP.
     */
    public void fill_combobox_percentage_single_phase_motors() {
        percentageSinglePhaseMotorsFound = ServicePercentageSinglePhaseMotors.find_percentage_motors_single_phase();
        if (percentageSinglePhaseMotorsFound != null) {
            for (PercentageSinglePhaseMotors  percentageSinglePhaseMotors : percentageSinglePhaseMotorsFound) {
                viewVoltageInCharge.getCmbPercentageSinglePhaseMotors().addItem(percentageSinglePhaseMotors); 
            }
            viewVoltageInCharge.getCmbPercentageSinglePhaseMotors().setSelectedIndex(0); 
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.PERCENTAGE_SINGLE_PHASE_MOTORS_NO_FOUND), MessagesStructure.justify));
            viewVoltageInCharge.dispose();
        }
    }//Fin del método
    
    /**
     * Método para llenar tabla con datos de cargas filtrados por nombre.
     * @param name 
     */
    public void fill_table_names_of_charges(String name){        
        charges = ServiceCharge.filter_by_name(new Charge(name));
        if (charges != null){         
            Methods.removeRows(viewVoltageInCharge.getTblCharges());
            for (Charge charge_data : charges) {
                Object[] data = {charge_data.getCode(),charge_data.getName(),charge_data.getPotency(), charge_data.isHorsePower(),charge_data.isDryer(), charge_data.isElectricKitchen(),charge_data.getTypeCharges().getCode(),charge_data.getTypeCharges().getType()};
                ((DefaultTableModel) viewVoltageInCharge.getTblCharges().getModel()).addRow(data);
            }
        }
    }//Fin del método 
    
    /**
     * Método para calcular conductor.
     */
    private void calculate_conductor(){  
        if (viewVoltageInCharge.getCharge() == null){    
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.CHARGE_NOT_SELECTED), MessagesStructure.justify));
            viewVoltageInCharge.getTblCharges().requestFocus();        
        } else if (!viewVoltageInCharge.getrBtnAir().isSelected() && !viewVoltageInCharge.getrBtnGround().isSelected()){
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.RUSH_NO_FOUND), MessagesStructure.justify));
            viewVoltageInCharge.getrBtnGround().requestFocus();
        } else {            
            if (viewVoltageInCharge.getCharge().isHorsePower()){
                potency = MethodsForCalculationsGlobal1.getPotencyHorsePower(((HorsePower)viewVoltageInCharge.getCmbHP().getSelectedItem()).getValue());
                horsesPowersFound = ServiceHorsePorwer.find_intensity_horses_power(
                        new HorsesPowers(
                                0, 
                                new HorsePower(
                                        0, 
                                        ((HorsePower)viewVoltageInCharge.getCmbHP().getSelectedItem()).getName(), 
                                        0), 
                                (Voltage)viewVoltageInCharge.getCmbVoltage().getSelectedItem(), 
                                null,
                                TypePhase.SINGLE_PHASE));
                if (horsesPowersFound == null){
                    MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.HORSES_POWER_INTENSITY_NO_FOUND), MessagesStructure.justify));
                } else {
                    caliberPhaseFound = MethodsForCalculationsGlobal1.calculateCaliberDishwasherAndCrusher(
                        horsesPowersFound.getIntensity().getIntensity(),
                        (Material)viewVoltageInCharge.getCmbMaterial().getSelectedItem(), 
                        (Temperature)viewVoltageInCharge.getCmbTemperature().getSelectedItem());  

                    caliberNeutralFound = caliberPhaseFound;
                    
                    calibersHearthFound = MethodsForCalculationsGlobal1.calculate_caliberHearth_dishwasherAndCrusher(horsesPowersFound.getIntensity().getIntensity());
                
                    materialPipeline = viewVoltageInCharge.getCmbPipeline().getSelectedItem().toString();
                    
                    caliberPipeline = MethodsForCalculationsIluminariaPowerPoint.calculate_pipeline(
                            caliberPhaseFound.getCaliber(), 
                            caliberNeutralFound.getCaliber(), 
                            calibersHearthFound.getCaliber(), 
                            (Phase)viewVoltageInCharge.getCmbPhases().getSelectedItem(), 
                            materialPipeline);
                    
                    breakerPhaseFound = MethodsForCalculationsGlobal1.find_braker_dishwasherAndCrusher(
                            horsesPowersFound.getIntensity().getIntensity(), 
                            ((PercentageSinglePhaseMotors)viewVoltageInCharge.getCmbPercentageSinglePhaseMotors().getSelectedItem()).getPercentage());                    
                    if (caliberPhaseFound == null){
                        MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.CALIBER_NO_FOUND), MessagesStructure.justify));
                    } else {
                        viewVoltageInCharge.getCmbCaliber().setSelectedItem(caliberPhaseFound.getCaliber());
                        viewVoltageInCharge.getCmbCalibersNeutral().setSelectedItem(caliberPhaseFound.getCaliber());
                        if (((Material)viewVoltageInCharge.getCmbMaterial().getSelectedItem()).getName().equals(TypeMaterials.COOPER.getMaterial())){                
                            viewVoltageInCharge.getLblCaliberPhase().setText(MethodsForCalculationsGlobal1.number_of_calibers((Phase)viewVoltageInCharge.getCmbPhases().getSelectedItem(), TypeCalibers.PHASE) + " #" + caliberPhaseFound.getCaliber().getName() + " Cu " + MethodsForCalculationsGlobal1.typeCaliber(typeCaliber,(Temperature)viewVoltageInCharge.getCmbTemperature().getSelectedItem()) + " " + MethodsForCalculationsGlobal1.number_of_brakers((Phase)viewVoltageInCharge.getCmbPhases().getSelectedItem(), breakerPhaseFound.getCapacity()));
                            viewVoltageInCharge.getLblCaliberNeutral().setText("1 Cable" + " #" + caliberPhaseFound.getCaliber().getName() + " Cu " + MethodsForCalculationsGlobal1.typeCaliber(typeCaliber,(Temperature)viewVoltageInCharge.getCmbTemperature().getSelectedItem()));
                        } else if (((Material)viewVoltageInCharge.getCmbMaterial().getSelectedItem()).getName().equals(TypeMaterials.ALUMINIUM.getMaterial())) {
                            viewVoltageInCharge.getLblCaliberPhase().setText(MethodsForCalculationsGlobal1.number_of_calibers((Phase)viewVoltageInCharge.getCmbPhases().getSelectedItem(), TypeCalibers.PHASE) + " #" + caliberPhaseFound.getCaliber().getName() + " Al " + MethodsForCalculationsGlobal1.typeCaliber(typeCaliber,(Temperature)viewVoltageInCharge.getCmbTemperature().getSelectedItem()) + " " + MethodsForCalculationsGlobal1.number_of_brakers((Phase)viewVoltageInCharge.getCmbPhases().getSelectedItem(), breakerPhaseFound.getCapacity()));
                            viewVoltageInCharge.getLblCaliberNeutral().setText("1 Cable" + " #" + caliberPhaseFound.getCaliber().getName() + " Al " + MethodsForCalculationsGlobal1.typeCaliber(typeCaliber,(Temperature)viewVoltageInCharge.getCmbTemperature().getSelectedItem()));
                        }
                        if (calibersHearthFound == null){
                            viewVoltageInCharge.getLblCaliberEarth().setText("No aplica");
                        } else {
                            viewVoltageInCharge.getLblCaliberEarth().setText("1 Cable " + calibersHearthFound.getCaliber().getName() + " " + MethodsForCalculationsGlobal1.typeCaliber(typeCaliber,(Temperature)viewVoltageInCharge.getCmbTemperature().getSelectedItem()));
                        }
                        viewVoltageInCharge.getBtnCalculateBreakdown().doClick(); 
                    }                     
                }
            } else if (viewVoltageInCharge.getCharge().isDryer()){
                potency = viewVoltageInCharge.getCharge().getPotency();
                caliberPhaseFound = MethodsForCalculationsGlobal1.calculateCaliberForDryer(
                        viewVoltageInCharge.getCharge().getPotency(), 
                        (Voltage)viewVoltageInCharge.getCmbVoltage().getSelectedItem(), 
                        (Material)viewVoltageInCharge.getCmbMaterial().getSelectedItem(), 
                        (Temperature)viewVoltageInCharge.getCmbTemperature().getSelectedItem(), 
                        Double.valueOf(viewVoltageInCharge.getJspPowerFactor().getValue().toString()), 
                        viewVoltageInCharge.getCmbPhases().getSelectedIndex()); 
                
                caliberNeutralFound = MethodsForCalculationsGlobal1.calculateCaliberDryer(
                        viewVoltageInCharge.getCharge().getPotency(), 
                        (Voltage)viewVoltageInCharge.getCmbVoltage().getSelectedItem(), 
                        (Material)viewVoltageInCharge.getCmbMaterial().getSelectedItem(), 
                        (Temperature)viewVoltageInCharge.getCmbTemperature().getSelectedItem(), 
                        Double.valueOf(viewVoltageInCharge.getJspPowerFactor().getValue().toString()), 
                        viewVoltageInCharge.getCmbPhases().getSelectedIndex());
                
                calibersHearthFound = MethodsForCalculationsGlobal1.calculate_calibersHearth(
                        viewVoltageInCharge.getCharge().getPotency(), 
                        (Voltage)viewVoltageInCharge.getCmbVoltage().getSelectedItem(), 
                        Double.valueOf(viewVoltageInCharge.getJspPowerFactor().getValue().toString()), 
                        viewVoltageInCharge.getCmbPhases().getSelectedIndex());
                
                materialPipeline = viewVoltageInCharge.getCmbPipeline().getSelectedItem().toString();
                
                caliberPipeline = MethodsForCalculationsIluminariaPowerPoint.calculate_pipeline(
                        caliberPhaseFound.getCaliber(), 
                        caliberNeutralFound.getCaliber(), 
                        calibersHearthFound.getCaliber(), 
                        (Phase)viewVoltageInCharge.getCmbPhases().getSelectedItem(), 
                        materialPipeline);
                
                intensityDesignFound = MethodsForCalculationsIluminariaPowerPoint.calculate_instensity_design(new Calibers(
                        0, 
                        (Material)viewVoltageInCharge.getCmbMaterial().getSelectedItem(), 
                        (Temperature)viewVoltageInCharge.getCmbTemperature().getSelectedItem(), 
                        null, 
                        caliberPhaseFound.getCaliber()));
                
                breakerPhaseFound = MethodsForCalculationsGlobal1.find_breaker_dryer(
                        viewVoltageInCharge.getCharge().getPotency(), 
                        (Voltage)viewVoltageInCharge.getCmbVoltage().getSelectedItem(),                     
                        (Material)viewVoltageInCharge.getCmbMaterial().getSelectedItem(),  
                        Double.valueOf(viewVoltageInCharge.getJspPowerFactor().getValue().toString()), 
                        viewVoltageInCharge.getCmbPhases().getSelectedIndex(),
                        intensityDesignFound);    
                
                if (caliberPhaseFound == null){
                    MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.CALIBER_NO_FOUND), MessagesStructure.justify));
                } else {
                    viewVoltageInCharge.getCmbCaliber().setSelectedItem(caliberPhaseFound.getCaliber());
                    viewVoltageInCharge.getCmbCalibersNeutral().setSelectedItem(caliberNeutralFound.getCaliber());
                    if (((Material)viewVoltageInCharge.getCmbMaterial().getSelectedItem()).getName().equals(TypeMaterials.COOPER.getMaterial())){                
                        viewVoltageInCharge.getLblCaliberPhase().setText(MethodsForCalculationsGlobal1.number_of_calibers((Phase)viewVoltageInCharge.getCmbPhases().getSelectedItem(), TypeCalibers.PHASE) + " #" + caliberPhaseFound.getCaliber().getName() + " Cu " + MethodsForCalculationsGlobal1.typeCaliber(typeCaliber,(Temperature)viewVoltageInCharge.getCmbTemperature().getSelectedItem()) + " " + MethodsForCalculationsGlobal1.number_of_brakers((Phase)viewVoltageInCharge.getCmbPhases().getSelectedItem(), breakerPhaseFound.getCapacity()));
                        if (!viewVoltageInCharge.getCharge().isHorsePower()){
                           viewVoltageInCharge.getLblCaliberNeutral().setText("1 Cable" + " #" + caliberNeutralFound.getCaliber().getName() + " Cu " + MethodsForCalculationsGlobal1.typeCaliber(typeCaliber,(Temperature)viewVoltageInCharge.getCmbTemperature().getSelectedItem()));
                        }
                    } else if (((Material)viewVoltageInCharge.getCmbMaterial().getSelectedItem()).getName().equals(TypeMaterials.ALUMINIUM.getMaterial())) {
                        viewVoltageInCharge.getLblCaliberPhase().setText(MethodsForCalculationsGlobal1.number_of_calibers((Phase)viewVoltageInCharge.getCmbPhases().getSelectedItem(), TypeCalibers.PHASE) + " #" + caliberPhaseFound.getCaliber().getName() + " Al " + MethodsForCalculationsGlobal1.typeCaliber(typeCaliber,(Temperature)viewVoltageInCharge.getCmbTemperature().getSelectedItem()) + " " + MethodsForCalculationsGlobal1.number_of_brakers((Phase)viewVoltageInCharge.getCmbPhases().getSelectedItem(), breakerPhaseFound.getCapacity()));
                        if (!viewVoltageInCharge.getCharge().isHorsePower()){
                           viewVoltageInCharge.getLblCaliberNeutral().setText("1 Cable" + " #" + caliberNeutralFound.getCaliber().getName() + " Al " + MethodsForCalculationsGlobal1.typeCaliber(typeCaliber,(Temperature)viewVoltageInCharge.getCmbTemperature().getSelectedItem()));
                        }
                    }
                    if (calibersHearthFound == null){
                        viewVoltageInCharge.getLblCaliberEarth().setText("No aplica");
                    } else {
                        viewVoltageInCharge.getLblCaliberEarth().setText("1 Cable " + calibersHearthFound.getCaliber().getName() + " " + MethodsForCalculationsGlobal1.typeCaliber(typeCaliber,(Temperature)viewVoltageInCharge.getCmbTemperature().getSelectedItem()));
                    }
                    viewVoltageInCharge.getBtnCalculateBreakdown().doClick(); 
                }                 
            } else if (viewVoltageInCharge.getCharge().isElectricKitchen()) {                
                potency = viewVoltageInCharge.getCharge().getPotency();
                caliberPhaseFound = MethodsForCalculationsGlobal1.calculateCaliberForSubFeeder(
                        viewVoltageInCharge.getCharge().getPotency(), 
                        (Voltage)viewVoltageInCharge.getCmbVoltage().getSelectedItem(), 
                        (Material)viewVoltageInCharge.getCmbMaterial().getSelectedItem(), 
                        (Temperature)viewVoltageInCharge.getCmbTemperature().getSelectedItem(), 
                        Double.valueOf(viewVoltageInCharge.getJspPowerFactor().getValue().toString()), 
                        viewVoltageInCharge.getCmbPhases().getSelectedIndex());
                
                caliberNeutralFound = MethodsForCalculationsGlobal1.calculateCaliberDryer(
                        viewVoltageInCharge.getCharge().getPotency(), 
                        (Voltage)viewVoltageInCharge.getCmbVoltage().getSelectedItem(), 
                        (Material)viewVoltageInCharge.getCmbMaterial().getSelectedItem(), 
                        (Temperature)viewVoltageInCharge.getCmbTemperature().getSelectedItem(), 
                        Double.valueOf(viewVoltageInCharge.getJspPowerFactor().getValue().toString()), 
                        viewVoltageInCharge.getCmbPhases().getSelectedIndex());

                calibersHearthFound = MethodsForCalculationsGlobal1.calculate_calibersHearth(
                        viewVoltageInCharge.getCharge().getPotency(), 
                        (Voltage)viewVoltageInCharge.getCmbVoltage().getSelectedItem(), 
                        Double.valueOf(viewVoltageInCharge.getJspPowerFactor().getValue().toString()), 
                        viewVoltageInCharge.getCmbPhases().getSelectedIndex());

                materialPipeline = viewVoltageInCharge.getCmbPipeline().getSelectedItem().toString();
                
                caliberPipeline = MethodsForCalculationsIluminariaPowerPoint.calculate_pipeline(
                        caliberPhaseFound.getCaliber(), 
                        caliberNeutralFound.getCaliber(), 
                        calibersHearthFound.getCaliber(), 
                        (Phase)viewVoltageInCharge.getCmbPhases().getSelectedItem(), 
                        materialPipeline);
                
                intensityDesignFound = MethodsForCalculationsIluminariaPowerPoint.calculate_instensity_design(new Calibers(
                        0, 
                        (Material)viewVoltageInCharge.getCmbMaterial().getSelectedItem(), 
                        (Temperature)viewVoltageInCharge.getCmbTemperature().getSelectedItem(), 
                        null, 
                        caliberPhaseFound.getCaliber()));
                
                breakerPhaseFound = MethodsForCalculationsGlobal1.find_breaker(
                        viewVoltageInCharge.getCharge().getPotency(), 
                        (Voltage)viewVoltageInCharge.getCmbVoltage().getSelectedItem(),                     
                        (Material)viewVoltageInCharge.getCmbMaterial().getSelectedItem(),  
                        Double.valueOf(viewVoltageInCharge.getJspPowerFactor().getValue().toString()), 
                        viewVoltageInCharge.getCmbPhases().getSelectedIndex(),
                        intensityDesignFound);
                if (caliberPhaseFound == null){
                    MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.CALIBER_NO_FOUND), MessagesStructure.justify));
                } else {
                    viewVoltageInCharge.getCmbCaliber().setSelectedItem(caliberPhaseFound.getCaliber());
                    viewVoltageInCharge.getCmbCalibersNeutral().setSelectedItem(caliberPhaseFound.getCaliber());
                    if (((Material)viewVoltageInCharge.getCmbMaterial().getSelectedItem()).getName().equals(TypeMaterials.COOPER.getMaterial())){                
                        viewVoltageInCharge.getLblCaliberPhase().setText(MethodsForCalculationsGlobal1.number_of_calibers((Phase)viewVoltageInCharge.getCmbPhases().getSelectedItem(), TypeCalibers.PHASE) + " #" + caliberPhaseFound.getCaliber().getName() + " Cu " + MethodsForCalculationsGlobal1.typeCaliber(typeCaliber,(Temperature)viewVoltageInCharge.getCmbTemperature().getSelectedItem()) + " " + MethodsForCalculationsGlobal1.number_of_brakers((Phase)viewVoltageInCharge.getCmbPhases().getSelectedItem(), breakerPhaseFound.getCapacity()));
                        if (!viewVoltageInCharge.getCharge().isHorsePower()){
                           viewVoltageInCharge.getLblCaliberNeutral().setText("1 Cable" + " #" + caliberNeutralFound.getCaliber().getName() + " Cu " + MethodsForCalculationsGlobal1.typeCaliber(typeCaliber,(Temperature)viewVoltageInCharge.getCmbTemperature().getSelectedItem()));
                        }
                    } else if (((Material)viewVoltageInCharge.getCmbMaterial().getSelectedItem()).getName().equals(TypeMaterials.ALUMINIUM.getMaterial())) {
                        viewVoltageInCharge.getLblCaliberPhase().setText(MethodsForCalculationsGlobal1.number_of_calibers((Phase)viewVoltageInCharge.getCmbPhases().getSelectedItem(), TypeCalibers.PHASE) + " #" + caliberPhaseFound.getCaliber().getName() + " Al " + MethodsForCalculationsGlobal1.typeCaliber(typeCaliber,(Temperature)viewVoltageInCharge.getCmbTemperature().getSelectedItem()) + " " + MethodsForCalculationsGlobal1.number_of_brakers((Phase)viewVoltageInCharge.getCmbPhases().getSelectedItem(), breakerPhaseFound.getCapacity()));
                        if (!viewVoltageInCharge.getCharge().isHorsePower()){
                           viewVoltageInCharge.getLblCaliberNeutral().setText("1 Cable" + " #" + caliberNeutralFound.getCaliber().getName() + " Al " + MethodsForCalculationsGlobal1.typeCaliber(typeCaliber,(Temperature)viewVoltageInCharge.getCmbTemperature().getSelectedItem()));
                        }
                    }
                    if (calibersHearthFound == null){
                        viewVoltageInCharge.getLblCaliberEarth().setText("No aplica");
                    } else {
                        viewVoltageInCharge.getLblCaliberEarth().setText("1 Cable " + calibersHearthFound.getCaliber().getName() + " " + MethodsForCalculationsGlobal1.typeCaliber(typeCaliber,(Temperature)viewVoltageInCharge.getCmbTemperature().getSelectedItem()));
                    }
                    viewVoltageInCharge.getBtnCalculateBreakdown().doClick(); 
                }                
            } else {   
                potency = viewVoltageInCharge.getCharge().getPotency();
                caliberPhaseFound = MethodsForCalculationsGlobal1.calculateCaliberForSubFeeder(
                        viewVoltageInCharge.getCharge().getPotency(), 
                        (Voltage)viewVoltageInCharge.getCmbVoltage().getSelectedItem(), 
                        (Material)viewVoltageInCharge.getCmbMaterial().getSelectedItem(), 
                        (Temperature)viewVoltageInCharge.getCmbTemperature().getSelectedItem(), 
                        Double.valueOf(viewVoltageInCharge.getJspPowerFactor().getValue().toString()), 
                        viewVoltageInCharge.getCmbPhases().getSelectedIndex());

                caliberNeutralFound = caliberPhaseFound;

                calibersHearthFound = MethodsForCalculationsGlobal1.calculate_calibersHearth(
                        viewVoltageInCharge.getCharge().getPotency(), 
                        (Voltage)viewVoltageInCharge.getCmbVoltage().getSelectedItem(), 
                        Double.valueOf(viewVoltageInCharge.getJspPowerFactor().getValue().toString()), 
                        viewVoltageInCharge.getCmbPhases().getSelectedIndex());

                materialPipeline = viewVoltageInCharge.getCmbPipeline().getSelectedItem().toString();
                
                caliberPipeline = MethodsForCalculationsIluminariaPowerPoint.calculate_pipeline(
                        caliberPhaseFound.getCaliber(), 
                        caliberNeutralFound.getCaliber(), 
                        calibersHearthFound.getCaliber(), 
                        (Phase)viewVoltageInCharge.getCmbPhases().getSelectedItem(), 
                        materialPipeline);
                
                intensityDesignFound = MethodsForCalculationsIluminariaPowerPoint.calculate_instensity_design(new Calibers(
                        0, 
                        (Material)viewVoltageInCharge.getCmbMaterial().getSelectedItem(), 
                        (Temperature)viewVoltageInCharge.getCmbTemperature().getSelectedItem(), 
                        null, 
                        caliberPhaseFound.getCaliber()));
                
                breakerPhaseFound = MethodsForCalculationsGlobal1.find_breaker(
                        viewVoltageInCharge.getCharge().getPotency(), 
                        (Voltage)viewVoltageInCharge.getCmbVoltage().getSelectedItem(),                     
                        (Material)viewVoltageInCharge.getCmbMaterial().getSelectedItem(),  
                        Double.valueOf(viewVoltageInCharge.getJspPowerFactor().getValue().toString()), 
                        viewVoltageInCharge.getCmbPhases().getSelectedIndex(),
                        intensityDesignFound);
                if (caliberPhaseFound == null){
                    MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.CALIBER_NO_FOUND), MessagesStructure.justify));
                } else {
                    viewVoltageInCharge.getCmbCaliber().setSelectedItem(caliberPhaseFound.getCaliber());
                    viewVoltageInCharge.getCmbCalibersNeutral().setSelectedItem(caliberPhaseFound.getCaliber());
                    if (((Material)viewVoltageInCharge.getCmbMaterial().getSelectedItem()).getName().equals(TypeMaterials.COOPER.getMaterial())){                
                        viewVoltageInCharge.getLblCaliberPhase().setText(MethodsForCalculationsGlobal1.number_of_calibers((Phase)viewVoltageInCharge.getCmbPhases().getSelectedItem(), TypeCalibers.PHASE) + " #" + caliberPhaseFound.getCaliber().getName() + " Cu " + MethodsForCalculationsGlobal1.typeCaliber(typeCaliber,(Temperature)viewVoltageInCharge.getCmbTemperature().getSelectedItem()) + " " + MethodsForCalculationsGlobal1.number_of_brakers((Phase)viewVoltageInCharge.getCmbPhases().getSelectedItem(), breakerPhaseFound.getCapacity()));
                        if (!viewVoltageInCharge.getCharge().isHorsePower()){
                           viewVoltageInCharge.getLblCaliberNeutral().setText("1 Cable" + " #" + caliberPhaseFound.getCaliber().getName() + " Cu " + MethodsForCalculationsGlobal1.typeCaliber(typeCaliber,(Temperature)viewVoltageInCharge.getCmbTemperature().getSelectedItem()));
                        }
                    } else if (((Material)viewVoltageInCharge.getCmbMaterial().getSelectedItem()).getName().equals(TypeMaterials.ALUMINIUM.getMaterial())) {
                        viewVoltageInCharge.getLblCaliberPhase().setText(MethodsForCalculationsGlobal1.number_of_calibers((Phase)viewVoltageInCharge.getCmbPhases().getSelectedItem(), TypeCalibers.PHASE) + " #" + caliberPhaseFound.getCaliber().getName() + " Al " + MethodsForCalculationsGlobal1.typeCaliber(typeCaliber,(Temperature)viewVoltageInCharge.getCmbTemperature().getSelectedItem()) + " " + MethodsForCalculationsGlobal1.number_of_brakers((Phase)viewVoltageInCharge.getCmbPhases().getSelectedItem(), breakerPhaseFound.getCapacity()));
                        if (!viewVoltageInCharge.getCharge().isHorsePower()){
                           viewVoltageInCharge.getLblCaliberNeutral().setText("1 Cable" + " #" + caliberPhaseFound.getCaliber().getName() + " Al " + MethodsForCalculationsGlobal1.typeCaliber(typeCaliber,(Temperature)viewVoltageInCharge.getCmbTemperature().getSelectedItem()));
                        }
                    }
                    if (calibersHearthFound == null){
                        viewVoltageInCharge.getLblCaliberEarth().setText("No aplica");
                    } else {
                        viewVoltageInCharge.getLblCaliberEarth().setText("1 Cable " + calibersHearthFound.getCaliber().getName() + " " + MethodsForCalculationsGlobal1.typeCaliber(typeCaliber,(Temperature)viewVoltageInCharge.getCmbTemperature().getSelectedItem()));
                    }
                    viewVoltageInCharge.getBtnCalculateBreakdown().doClick(); 
                }            
            }
        }
    }//Fin del método
        
    /**
     * Método para calcular reactancia
     * @return Retorna un objeto ResistanceReactance que almacena el valor de la reactancia.
     */
    private ResistanceReactance calculate_reactance(TypeOfBranchCircuitInArea typeOfBranchCircuitInArea){   
        if (typeOfBranchCircuitInArea == TypeOfBranchCircuitInArea.NEUTRAL){
            return  MethodsForCalculationsGlobal1.calculate_reactance(
                (Material)viewVoltageInCharge.getCmbMaterial().getSelectedItem(),
                (Caliber)viewVoltageInCharge.getCmbCalibersNeutral().getSelectedItem(), 
                (Duct)viewVoltageInCharge.getCmbDuct().getSelectedItem());  
        } else {
            return  MethodsForCalculationsGlobal1.calculate_reactance(
                (Material)viewVoltageInCharge.getCmbMaterial().getSelectedItem(),
                (Caliber)viewVoltageInCharge.getCmbCaliber().getSelectedItem(), 
                (Duct)viewVoltageInCharge.getCmbDuct().getSelectedItem());        
        }             
    }//Fin del método
        
    /**
     * Método para calcular resistencia
     * @return Retorna un objeto ResistanceReactance que almacena el valor de la resistencia.
     */
    private ResistanceReactance calculate_resistance(TypeOfBranchCircuitInArea typeOfBranchCircuitInArea){
        if (typeOfBranchCircuitInArea == TypeOfBranchCircuitInArea.NEUTRAL){
            return MethodsForCalculationsGlobal1.calculate_resistance(
                (Material)viewVoltageInCharge.getCmbMaterial().getSelectedItem(),
                (Caliber)viewVoltageInCharge.getCmbCalibersNeutral().getSelectedItem(), 
                (Duct)viewVoltageInCharge.getCmbDuct().getSelectedItem());
        } else {
            return MethodsForCalculationsGlobal1.calculate_resistance(
                (Material)viewVoltageInCharge.getCmbMaterial().getSelectedItem(),
                (Caliber)viewVoltageInCharge.getCmbCaliber().getSelectedItem(), 
                (Duct)viewVoltageInCharge.getCmbDuct().getSelectedItem());            
        }
    }//Fin del método
    
    /**
     * Método para calcular la caída de voltage.
     */
    private void calculate_breakdownVoltage(){
        if (caliberPhaseFound == null || caliberNeutralFound == null || calibersHearthFound == null){
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.AREA_CAPACITY_INTENSITY_NO_FOUND), MessagesStructure.justify));
        } else {
            if (MethodsForCalculationsGlobal1.validate_caliber((Caliber)viewVoltageInCharge.getCmbCaliber().getSelectedItem())
                    && MethodsForCalculationsGlobal1.validate_caliber((Caliber)viewVoltageInCharge.getCmbCalibersNeutral().getSelectedItem())){       
                resistance = calculate_resistance(TypeOfBranchCircuitInArea.ILUMINARIA);
                reactance  = calculate_reactance(TypeOfBranchCircuitInArea.ILUMINARIA);
                if (resistance != null){     
                    breakdownVoltage = MethodsForCalculationsGlobal1.breakdownVoltage(
                            potency,  
                            Double.valueOf(viewVoltageInCharge.getJspLength().getValue().toString()), 
                            ((Voltage)viewVoltageInCharge.getCmbVoltage().getSelectedItem()).getVoltage(), 
                            reactance.getValue().getValour(), 
                            Double.valueOf(viewVoltageInCharge.getJspPowerFactor().getValue().toString()), 
                            resistance.getValue().getValour(), 
                            Double.valueOf(viewVoltageInCharge.getJspAngle().getValue().toString()));   
                    viewVoltageInCharge.getLblBreakdownVoltage().setText(String.valueOf(breakdownVoltage) + " %"); 
                    caliberSelected = (Caliber)viewVoltageInCharge.getCmbCaliber().getSelectedItem(); 
                    
                    if (viewVoltageInCharge.getCharge().isHorsePower()){
                        breakerPhasePersistFound = MethodsForCalculationsGlobal1.find_braker_dishwasherAndCrusher(
                            horsesPowersFound.getIntensity().getIntensity(), 
                            ((PercentageSinglePhaseMotors)viewVoltageInCharge.getCmbPercentageSinglePhaseMotors().getSelectedItem()).getPercentage());                    
                    } else if (viewVoltageInCharge.getCharge().isDryer()){                             
                        intensityDesignFound = MethodsForCalculationsIluminariaPowerPoint.calculate_instensity_design(new Calibers(
                                0, 
                                (Material)viewVoltageInCharge.getCmbMaterial().getSelectedItem(), 
                                (Temperature)viewVoltageInCharge.getCmbTemperature().getSelectedItem(), 
                                null, 
                                (Caliber)viewVoltageInCharge.getCmbCaliber().getSelectedItem()));
                        breakerPhasePersistFound = MethodsForCalculationsGlobal1.find_breaker_dryer(
                                potency, 
                                (Voltage)viewVoltageInCharge.getCmbVoltage().getSelectedItem(),                     
                                (Material)viewVoltageInCharge.getCmbMaterial().getSelectedItem(),  
                                Double.valueOf(viewVoltageInCharge.getJspPowerFactor().getValue().toString()), 
                                viewVoltageInCharge.getCmbPhases().getSelectedIndex(),
                                intensityDesignFound);                    
                    } else if (viewVoltageInCharge.getCharge().isElectricKitchen()){ 
                        intensityDesignFound = MethodsForCalculationsIluminariaPowerPoint.calculate_instensity_design(new Calibers(
                                0, 
                                (Material)viewVoltageInCharge.getCmbMaterial().getSelectedItem(), 
                                (Temperature)viewVoltageInCharge.getCmbTemperature().getSelectedItem(), 
                                null, 
                                (Caliber)viewVoltageInCharge.getCmbCaliber().getSelectedItem()));

                        breakerPhasePersistFound = MethodsForCalculationsGlobal1.find_breaker(
                                potency, 
                                (Voltage)viewVoltageInCharge.getCmbVoltage().getSelectedItem(),                     
                                (Material)viewVoltageInCharge.getCmbMaterial().getSelectedItem(),  
                                Double.valueOf(viewVoltageInCharge.getJspPowerFactor().getValue().toString()), 
                                viewVoltageInCharge.getCmbPhases().getSelectedIndex(),
                                intensityDesignFound);
                    } else {   
                        intensityDesignFound = MethodsForCalculationsIluminariaPowerPoint.calculate_instensity_design(new Calibers(
                                0, 
                                (Material)viewVoltageInCharge.getCmbMaterial().getSelectedItem(), 
                                (Temperature)viewVoltageInCharge.getCmbTemperature().getSelectedItem(), 
                                null, 
                                (Caliber)viewVoltageInCharge.getCmbCaliber().getSelectedItem()));

                        breakerPhasePersistFound = MethodsForCalculationsGlobal1.find_breaker(
                                potency, 
                                (Voltage)viewVoltageInCharge.getCmbVoltage().getSelectedItem(),                     
                                (Material)viewVoltageInCharge.getCmbMaterial().getSelectedItem(),  
                                Double.valueOf(viewVoltageInCharge.getJspPowerFactor().getValue().toString()), 
                                viewVoltageInCharge.getCmbPhases().getSelectedIndex(),
                                intensityDesignFound);            
                    }
                    
                    resistance = calculate_resistance(TypeOfBranchCircuitInArea.NEUTRAL);
                    reactance  = calculate_reactance(TypeOfBranchCircuitInArea.NEUTRAL);
                    if (resistance != null){                
                        breakdownVoltage = MethodsForCalculationsGlobal1.breakdownVoltage(
                                potency,  
                                Double.valueOf(viewVoltageInCharge.getJspLength().getValue().toString()), 
                                ((Voltage)viewVoltageInCharge.getCmbVoltage().getSelectedItem()).getVoltage(), 
                                reactance.getValue().getValour(), 
                                Double.valueOf(viewVoltageInCharge.getJspPowerFactor().getValue().toString()), 
                                resistance.getValue().getValour(), 
                                Double.valueOf(viewVoltageInCharge.getJspAngle().getValue().toString()));  
                        viewVoltageInCharge.getLblBreakdownVoltageNeutral().setText(String.valueOf(breakdownVoltage) + " %");                     
                        caliberSelectedNeutral = (Caliber)viewVoltageInCharge.getCmbCalibersNeutral().getSelectedItem(); 
                        caliberPhase = viewVoltageInCharge.getLblCaliberPhase().getText();
                        caliberPhase = caliberPhase.replace(
                                "#" + caliberPhaseFound.getCaliber().getName(), 
                                "#" + caliberSelected.getName());
                        caliberPhase = caliberPhase.replace(
                                MethodsForCalculationsGlobal1.number_of_brakers((Phase)viewVoltageInCharge.getCmbPhases().getSelectedItem(),breakerPhaseFound.getCapacity()),
                                MethodsForCalculationsGlobal1.number_of_brakers((Phase)viewVoltageInCharge.getCmbPhases().getSelectedItem(), breakerPhasePersistFound.getCapacity()));
                        caliberNeutral = viewVoltageInCharge.getLblCaliberNeutral().getText();
                        caliberNeutral = caliberNeutral.replace(
                                "#" + caliberNeutralFound.getCaliber().getName(), 
                                "#" + caliberSelectedNeutral.getName());
                        caliberHearth = viewVoltageInCharge.getLblCaliberEarth().getText();
                        
                        materialPipeline = viewVoltageInCharge.getCmbPipeline().getSelectedItem().toString();

                        caliberPipeline = MethodsForCalculationsIluminariaPowerPoint.calculate_pipeline(
                                caliberSelected, 
                                caliberSelectedNeutral, 
                                calibersHearthFound.getCaliber(), 
                                (Phase)viewVoltageInCharge.getCmbPhases().getSelectedItem(), 
                                materialPipeline);
                        
                    }
                }  
            }            
        }
    }//Fin del método

    /**
     * Método para registrar carga dentro de un área.
     */
    private void loadRegister(){
        if (caliberPhaseFound == null || caliberNeutralFound == null || calibersHearthFound == null){    
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.AREA_CAPACITY_INTENSITY_NO_FOUND), MessagesStructure.justify));
        } else {
            Area area;
            if (((Phase)viewVoltageInCharge.getCmbPhases().getSelectedItem()).getName().equalsIgnoreCase(TypePhases.SINGLE_PHASE_TWO_THREAD.getPhase())){
                area = new Area(
                        viewVoltageInCharge.getArea().getCode(), 
                        null, 
                        null, 
                        potency * Integer.valueOf(viewVoltageInCharge.getJspQuantity().getValue().toString()), 
                        potency * Integer.valueOf(viewVoltageInCharge.getJspQuantity().getValue().toString()), 
                        viewVoltageInCharge.getArea().getQuantity());
            } else {
                area = new Area(
                        viewVoltageInCharge.getArea().getCode(),
                        null,
                        null, 
                        potency * Integer.valueOf(viewVoltageInCharge.getJspQuantity().getValue().toString()),
                        viewVoltageInCharge.getCharge().isDryer() || viewVoltageInCharge.getCharge().isElectricKitchen() ? 
                                (potency * 0.7) * Integer.valueOf(viewVoltageInCharge.getJspQuantity().getValue().toString()) :
                                0,
                        viewVoltageInCharge.getArea().getQuantity());
            }
            if (ServiceChargesInAreas.validate_charge_in_area(
                    new ChargesInAreas(
                            new Charge(viewVoltageInCharge.getCharge().getCode(), null, 0, false, false, false,null), 
                            new Area(viewVoltageInCharge.getArea().getCode()), 
                            0, 
                            0, 
                            null, 
                            null, 
                            null,
                            null,
                            null,
                            null))){
                MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.CHARGE_SELECTED), MessagesStructure.justify));
            } else {
                if (ServiceChargesInAreas.create_charge_in_area(
                        new ChargesInAreas(
                                new Charge(viewVoltageInCharge.getCharge().getCode(), null, 0, false, false, false, new TypeCharges(viewVoltageInCharge.getCharge().getTypeCharges().getCode(), null, viewVoltageInCharge.getCharge().getTypeCharges().getType())), 
                                new Area(
                                        viewVoltageInCharge.getArea().getCode(), 
                                        null,
                                        new Project(viewVoltageInCharge.getArea().getProject().getCode(), null, new TypeOfInstallation(viewVoltageInCharge.getArea().getProject().getTypeOfInstallation().getCode(), null), null, 0, null), 
                                        viewVoltageInCharge.getCharge().getTypeCharges().getType().equalsIgnoreCase(TypeSubTypeCharge.POTENCY.getSubTypeCharge()) ? 
                                                potency : viewVoltageInCharge.getCharge().getTypeCharges().getType().equalsIgnoreCase(TypeSubTypeCharge.QUANTITY.getSubTypeCharge()) ? 
                                                        Integer.valueOf(viewVoltageInCharge.getJspQuantity().getValue().toString()) : 0, 
                                        0, 
                                        0), 
                                !viewVoltageInCharge.getCharge().isHorsePower() || viewVoltageInCharge.getCharge().isElectricKitchen() ? 
                                        viewVoltageInCharge.getCharge().getPotency()
                                        : ((HorsePower)viewVoltageInCharge.getCmbHP().getSelectedItem()).getValue() * MethodsForCalculationsGlobal1.constantHorsePower, 
                                Integer.valueOf(viewVoltageInCharge.getJspQuantity().getValue().toString()), 
                                caliberPhase, 
                                caliberNeutral, 
                                caliberHearth,
                                (Phase)viewVoltageInCharge.getCmbPhases().getSelectedItem(),
                                caliberPipeline,
                                materialPipeline),area)){ 
                    viewVoltageInCharge.dispose();
                }             
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(viewVoltageInCharge.getBtnAdd())) { 
            loadRegister();
        } else if (e.getSource().equals(viewVoltageInCharge.getBtnClose())) {
            viewVoltageInCharge.dispose();
        } else if (e.getSource().equals(viewVoltageInCharge.getBtnCalculateCurrentCapacity())){
            calculate_conductor();
        } else if (e.getSource().equals(viewVoltageInCharge.getBtnCalculateBreakdown())){
            calculate_breakdownVoltage();
        } else if (e.getSource().equals(viewVoltageInCharge.getCmbPhases())) {
            this.fill_combos_voltages();
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {      
        character = e.getKeyChar();
        if (e.getSource().equals(viewVoltageInCharge.getTxtFindCharge())) {
            if (viewVoltageInCharge.getTxtFindCharge().getText().length() == 98) {
                viewVoltageInCharge.getToolkit().beep();
                e.consume();                
            } 
        }             
     }

    @Override
    public void keyPressed(KeyEvent e) {
     }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource().equals(viewVoltageInCharge.getTxtFindCharge())){
            this.fill_table_names_of_charges(viewVoltageInCharge.getTxtFindCharge().getText());            
        }
     }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(viewVoltageInCharge.getTblCharges())){
            if (e.getClickCount() == 2 && viewVoltageInCharge.getTblCharges().getSelectedColumn() == 2){
                this.caliberNeutralFound = null;
                this.caliberPhaseFound = null;
                this.calibersHearthFound = null;
                this.viewVoltageInCharge.cleanFields();
                int row = viewVoltageInCharge.getTblCharges().getSelectedRow();
                viewVoltageInCharge.setCharge(new Charge(
                        Integer.valueOf(viewVoltageInCharge.getTblCharges().getValueAt(row, 0).toString()),
                        viewVoltageInCharge.getTblCharges().getValueAt(row, 1).toString(),
                        Integer.valueOf(viewVoltageInCharge.getTblCharges().getValueAt(row, 2).toString()),
                        Boolean.valueOf(viewVoltageInCharge.getTblCharges().getValueAt(row, 3).toString()),
                        Boolean.valueOf(viewVoltageInCharge.getTblCharges().getValueAt(row, 4).toString()),
                        Boolean.valueOf(viewVoltageInCharge.getTblCharges().getValueAt(row, 5).toString()),
                        new TypeCharges(Integer.valueOf(viewVoltageInCharge.getTblCharges().getValueAt(row, 6).toString()),
                        null, 
                        viewVoltageInCharge.getTblCharges().getValueAt(row, 7).toString())));
                viewVoltageInCharge.getLblPotency().setText(viewVoltageInCharge.getTblCharges().getValueAt(row, 2).toString() + " W");
                if (Boolean.valueOf(viewVoltageInCharge.getTblCharges().getValueAt(row, 3).toString())){ 
                    this.viewVoltageInCharge.hiddenFields(false);
                } else {
                    this.viewVoltageInCharge.hiddenFields(true);
                }
                if (Boolean.valueOf(viewVoltageInCharge.getTblCharges().getValueAt(row, 5).toString())){
                    this.viewVoltageInCharge.getCharge().setPotency(8000);
                    this.viewVoltageInCharge.getLblPotency().setText("8000" + " W");
                    SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel();
                    spinnerNumberModel.setMaximum(2);
                    spinnerNumberModel.setMinimum(1);
                    spinnerNumberModel.setValue(1);
                    this.viewVoltageInCharge.getJspQuantity().setModel(spinnerNumberModel);
                } else {
                    SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel();
                    spinnerNumberModel.setMaximum(9999999);
                    spinnerNumberModel.setMinimum(1);
                    spinnerNumberModel.setValue(1);
                    this.viewVoltageInCharge.getJspQuantity().setModel(spinnerNumberModel);
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void stateChanged(ChangeEvent e) {    
        if (e.getSource().equals(viewVoltageInCharge.getrBtnAir())){            
            if (viewVoltageInCharge.getrBtnAir().isSelected()){                
                typeCaliber = TypeRush.AIR;                
            }             
        } else if (e.getSource().equals(viewVoltageInCharge.getrBtnGround())){            
            if (viewVoltageInCharge.getrBtnGround().isSelected()){
                typeCaliber = TypeRush.UNDERGROUND;                
            }             
        } else if (e.getSource().equals(viewVoltageInCharge.getJspQuantity()) 
                && viewVoltageInCharge.getCharge() != null
                && viewVoltageInCharge.getCharge().isElectricKitchen()){
            if (Integer.valueOf(viewVoltageInCharge.getJspQuantity().getValue().toString()) == 1){
                this.viewVoltageInCharge.getCharge().setPotency(8000);
                this.viewVoltageInCharge.getLblPotency().setText("8000 W");
            } else if (Integer.valueOf(viewVoltageInCharge.getJspQuantity().getValue().toString()) == 2){
                this.viewVoltageInCharge.getCharge().setPotency(11000);
                System.out.println(this.viewVoltageInCharge.getCharge().getPotency());
                this.viewVoltageInCharge.getLblPotency().setText("11000 W");
            } 
        }        
    }

}
