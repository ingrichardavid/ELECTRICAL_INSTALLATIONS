/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.controller;

import com.electrical_installations.configuration.Messages;
import com.electrical_installations.configuration.MessagesStructure;
import com.electrical_installations.model.entity.Area;
import com.electrical_installations.model.entity.AreaIluminariaPowerPoint;
import com.electrical_installations.model.entity.Project;
import com.electrical_installations.model.entity.TypeOfInstallation;
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
import com.electrical_installations.model.enums.TypeCalibers;
import com.electrical_installations.model.enums.TypeMaterials;
import com.electrical_installations.model.enums.TypeRush;
import com.electrical_installations.model.enums.TypeOfBranchCircuitInArea;
import com.electrical_installations.model.enums.TypePhases;
import com.electrical_installations.model.service.ServiceArea;
import com.electrical_installations.model.service.ServiceCaliber;
import com.electrical_installations.model.service.ServiceDuct;
import com.electrical_installations.model.service.ServiceMaterial;
import com.electrical_installations.model.service.ServicePhase;
import com.electrical_installations.model.service.ServiceTemperature;
import com.electrical_installations.model.service.ServiceVoltage;
import com.electrical_installations.view.ViewArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Controlador para la vista ViewArea
 *
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-02
 */
public class ControllerArea implements ActionListener, KeyListener, WindowListener, ChangeListener {

    //Objetos, variables y constantes
    private final ViewArea viewArea;
    private static final Messages messages = Messages.getInstance();
    private Area area;
    private Calibers caliberFoundIluminaria,caliberFoundPowerPoint,caliberFoundSubFeeder,caliberFoundSubFeederNeutral;
    private String caliberIluminariaPipeline, caliberPowerPointPipeline, caliberSubFeederPipeline;
    private String materialCaliberIluminaria, materialCaliberPowerPoint, materialCaliberSubFeeder;
    private Breaker breakerFoundIluminaria,breakerFoundPorwerPoint,breakerFoundSubFeeder;
    private ResistanceReactance resistance,reactance;
    private char character;
    private List<Temperature> temperaturesFound;
    private List<Phase> phasesFound;
    private List<Voltage> voltagesFound;
    private List<Caliber> calibersFound;
    private List<Duct> ductsFound;
    private List<Material> materialsFound;
    private List<AreaIluminariaPowerPoint> areaIluminariaPowerPoints;
    private TypeRush typeCaliberIluminaria, typeCaliberPowerPoint, typeCaliberSubFeeder;
    private double breakdownVoltage, potencySubFeeder, potencyTotal, potencyTotalNeutral;
    private String caliberUseIluminaria, caliberUsePowerPoint, caliberUseSubFeeder, caliberUseSubFeederNeutral;
    private Caliber caliberSelectedIluminaria, caliberSelectedPowerPoint, caliberSelectedSubFeeder, caliberSelectedSubFeederNeutral;
    private Intensity intensityDesignFound;
    private int branchCircuitIluminaria, branchCircuitPowerPoint;
    
    /**
     * Contructor de la clase, recibe un objeto ViewArea
     *
     * @param viewArea
     */
    public ControllerArea(ViewArea viewArea) {
        this.viewArea = viewArea;
        this.caliberFoundIluminaria = null;
        this.caliberFoundPowerPoint = null;
        this.caliberFoundSubFeeder = null;
        this.caliberFoundSubFeederNeutral = null;
        this.potencySubFeeder = 0;
        this.potencyTotal = 0;
        this.potencyTotalNeutral = 0;
        this.intensityDesignFound = null;
        this.branchCircuitIluminaria = 0;
        this.branchCircuitPowerPoint = 0;
    }//Fin del constructor 

    /**
     * Método para validar que los campos obligatorios sean completados.     *
     * @return Retorna true si los campos obligatorios son completados.
     */
    private boolean validate_fields() {
        if (viewArea.getTxtName().getText().equals("")) {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.AREA_NAME_NO_FOUND), MessagesStructure.justify));
            viewArea.getTxtName().requestFocus();
            return false;
        } else if (caliberFoundIluminaria == null){
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.AREA_CONDUCTOR_ILUMINARIA_NO_FOUND), MessagesStructure.justify));
            viewArea.getJtpPanels().setSelectedIndex(0);
            return false;
        } else if (caliberFoundPowerPoint == null){
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.AREA_CONDUCTOR_POWER_POINT_NO_FOUND), MessagesStructure.justify));
            viewArea.getJtpPanels().setSelectedIndex(1);
            return false;
        } else if (caliberFoundSubFeeder == null || caliberFoundSubFeederNeutral == null){
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.AREA_CONDUCTOR_SUB_FEEDER_NO_FOUND), MessagesStructure.justify));
            viewArea.getJtpPanels().setSelectedIndex(2);
            return false;
        }
        return true;
    }//Fin del método
   
    /**
     * Método para llenar lista con Iluminaria y Toma corriente.
     * @return Rertorn una lista con Iluminaria y Toma corriente.
     */
    private List<AreaIluminariaPowerPoint> fill_area_iluminaria_powerPoint(){   
        areaIluminariaPowerPoints = new ArrayList<>();
        areaIluminariaPowerPoints.add(new AreaIluminariaPowerPoint(
                viewArea.getAreaIluminariaPowerPointsIluminaria() != null ? viewArea.getAreaIluminariaPowerPointsIluminaria().getCode() : 0,
                new Area(0),
                caliberFoundIluminaria,
                (Voltage)viewArea.getCmbVoltageIluminaria().getSelectedItem(),
                (Phase)viewArea.getCmbPhasesIluminaria().getSelectedItem(), 
                TypeOfBranchCircuitInArea.ILUMINARIA,
                Double.valueOf(viewArea.getJspArea().getValue().toString()),
                MethodsForCalculationsIluminariaPowerPoint.iluminariaConstantSinglefamilyHome,
                Double.valueOf(viewArea.getJspPowerFactor().getValue().toString()),
                caliberSelectedIluminaria,
                breakerFoundIluminaria,
                viewArea.getrBtnGroundIluminaria().isSelected() ? TypeRush.UNDERGROUND : TypeRush.AIR,
                Double.valueOf(viewArea.getJspLength().getValue().toString()),
                (Duct)viewArea.getCmbDuctIluminaria().getSelectedItem(),
                Double.valueOf(viewArea.getJspAngle().getValue().toString()),
                caliberUseIluminaria,
                branchCircuitIluminaria,
                caliberIluminariaPipeline,
                materialCaliberIluminaria));
        areaIluminariaPowerPoints.add(new AreaIluminariaPowerPoint(
                viewArea.getAreaIluminariaPowerPointsPowerPoint() != null ? viewArea.getAreaIluminariaPowerPointsPowerPoint().getCode() : 0,
                new Area(0),
                caliberFoundPowerPoint,
                (Voltage)viewArea.getCmbVoltagePowerPoint().getSelectedItem(),
                (Phase)viewArea.getCmbPhasesPowerPoint().getSelectedItem(), 
                TypeOfBranchCircuitInArea.POWER_POINT,
                Double.valueOf(viewArea.getTxtQuantityPowerPoint().getValue().toString()),
                MethodsForCalculationsIluminariaPowerPoint.powerPointConstant,
                Double.valueOf(viewArea.getJspPowerFactorPowerPoint().getValue().toString()),
                caliberSelectedPowerPoint,
                breakerFoundPorwerPoint,
                viewArea.getrBtnGroundPowerPoint().isSelected() ? TypeRush.UNDERGROUND : TypeRush.AIR,
                Double.valueOf(viewArea.getJspLengthPowerPoint().getValue().toString()),
                (Duct)viewArea.getCmbDuctPowerPoint().getSelectedItem(),
                Double.valueOf(viewArea.getJspAnglePowerPoint().getValue().toString()),
                caliberUsePowerPoint,
                branchCircuitPowerPoint,
                caliberPowerPointPipeline,
                materialCaliberPowerPoint)); 
        areaIluminariaPowerPoints.add(new AreaIluminariaPowerPoint(
                viewArea.getAreaSubFeeder() != null ? viewArea.getAreaSubFeeder().getCode() : 0,
                new Area(0),
                caliberFoundSubFeeder,
                (Voltage)viewArea.getCmbVoltageSubFeeder().getSelectedItem(),
                (Phase)viewArea.getCmbPhasesSubFeeder().getSelectedItem(), 
                TypeOfBranchCircuitInArea.SUB_FEEDER,
                0,
                1,
                Double.valueOf(viewArea.getJspPowerSubFeeder().getValue().toString()),
                caliberSelectedSubFeeder,
                breakerFoundSubFeeder,
                viewArea.getrBtnGroundSubFeeder().isSelected() ? TypeRush.UNDERGROUND : TypeRush.AIR,
                Double.valueOf(viewArea.getJspLengthSubFeeder().getValue().toString()),
                (Duct)viewArea.getCmbDuctSubFeeder().getSelectedItem(),
                Double.valueOf(viewArea.getJspAngleSubFeeder().getValue().toString()),
                caliberUseSubFeeder,
                0,
                caliberSubFeederPipeline,
                materialCaliberSubFeeder));   
        areaIluminariaPowerPoints.add(new AreaIluminariaPowerPoint(
                viewArea.getAreaSubFeederNeutral()!= null ? viewArea.getAreaSubFeederNeutral().getCode() : 0,
                new Area(0),
                caliberFoundSubFeederNeutral,
                (Voltage)viewArea.getCmbVoltageSubFeeder().getSelectedItem(),
                (Phase)viewArea.getCmbPhasesSubFeeder().getSelectedItem(), 
                TypeOfBranchCircuitInArea.NEUTRAL,
                0,
                1,
                Double.valueOf(viewArea.getJspPowerSubFeeder().getValue().toString()),
                caliberSelectedSubFeederNeutral,
                breakerFoundSubFeeder,
                viewArea.getrBtnGroundSubFeeder().isSelected() ? TypeRush.UNDERGROUND : TypeRush.AIR,
                Double.valueOf(viewArea.getJspLengthSubFeeder().getValue().toString()),
                (Duct)viewArea.getCmbDuctSubFeeder().getSelectedItem(),
                Double.valueOf(viewArea.getJspAngleSubFeeder().getValue().toString()),
                caliberUseSubFeederNeutral,
                0,
                caliberSubFeederPipeline,
                materialCaliberSubFeeder));  
        return areaIluminariaPowerPoints;
    }//Fin del método
    
    /**
     * Método para registrar un área.
     */
    private void area_registration() {
        if (validate_fields()) {
            if (ServiceArea.create_area(new Area( 
                    0, 
                    viewArea.getTxtName().getText(), 
                    new Project(
                            viewArea.getArea().getProject().getCode(), 
                            null, 
                            new TypeOfInstallation(
                                    viewArea.getArea().getProject().getTypeOfInstallation().getCode(),
                                    null), 
                            null, 
                            0, 
                            null), 
                    MethodsForCalculationsIluminariaPowerPoint.potencyInIluminaria(Double.valueOf(viewArea.getJspArea().getValue().toString())) +
                            MethodsForCalculationsIluminariaPowerPoint.potencyInPowerPoint(Integer.valueOf(viewArea.getTxtQuantityPowerPoint().getValue().toString())),                      
                    MethodsForCalculationsIluminariaPowerPoint.potencyInIluminaria(Double.valueOf(viewArea.getJspArea().getValue().toString())) +
                            MethodsForCalculationsIluminariaPowerPoint.potencyInPowerPoint(Integer.valueOf(viewArea.getTxtQuantityPowerPoint().getValue().toString())), 
                    Integer.valueOf(viewArea.getJspQuantity().getValue().toString())),fill_area_iluminaria_powerPoint())){            
                viewArea.dispose();
            }
        }
    }//Fin del método  
    
    /**
     * Método para modificar un área.
     */
    private void modify_area() {        
        if (validate_fields()) {
            if (ServiceArea.update(new Area(
                    viewArea.getArea().getCode(), 
                    viewArea.getTxtName().getText(), 
                    new Project(
                            viewArea.getArea().getProject().getCode(), 
                            null, 
                            new TypeOfInstallation(
                                    viewArea.getArea().getProject().getTypeOfInstallation().getCode(),
                                    null), 
                            null, 
                            0, 
                            null), 
                    (viewArea.getArea().getPotency_total() - viewArea.getPotencyOld()) + MethodsForCalculationsIluminariaPowerPoint.potencyInIluminariaAndPowerPoint(
                            MethodsForCalculationsIluminariaPowerPoint.potencyInIluminaria(Double.valueOf(viewArea.getJspArea().getValue().toString())),
                            MethodsForCalculationsIluminariaPowerPoint.potencyInPowerPoint(Integer.valueOf(viewArea.getTxtQuantityPowerPoint().getValue().toString()))),                     
                    (viewArea.getArea().getNeutral() - viewArea.getPotencyOld()) + MethodsForCalculationsIluminariaPowerPoint.potencyInIluminariaAndPowerPoint(
                            MethodsForCalculationsIluminariaPowerPoint.potencyInIluminaria(Double.valueOf(viewArea.getJspArea().getValue().toString())),
                            MethodsForCalculationsIluminariaPowerPoint.potencyInPowerPoint(Integer.valueOf(viewArea.getTxtQuantityPowerPoint().getValue().toString()))), 
                    0,
                    viewArea.getPotencyOld() * viewArea.getArea().getQuantity(),
                    MethodsForCalculationsIluminariaPowerPoint.potencyInIluminariaAndPowerPoint(
                            MethodsForCalculationsIluminariaPowerPoint.potencyInIluminaria(Double.valueOf(viewArea.getJspArea().getValue().toString())),
                            MethodsForCalculationsIluminariaPowerPoint.potencyInPowerPoint(Integer.valueOf(viewArea.getTxtQuantityPowerPoint().getValue().toString()))) * viewArea.getArea().getQuantity()),fill_area_iluminaria_powerPoint())){            
                viewArea.setModify(true);
                viewArea.dispose();
            }
        }
    }//Fin del método  
       
    /**
     * Método para llenar los combos con temperaturas.
     */
    public void fill_combos_temperatures(){
        temperaturesFound = ServiceTemperature.find_temperatures();
        if (temperaturesFound != null){
            for (Temperature temperature : temperaturesFound){
                viewArea.getCmbTemperatureIlimunaria().addItem(temperature);
                viewArea.getCmbTemperaturePowerPoint().addItem(temperature);
                viewArea.getCmbTemperatureSubFeeder().addItem(temperature);
            }     
            viewArea.getCmbTemperatureIlimunaria().setSelectedIndex(0);
            viewArea.getCmbTemperaturePowerPoint().setSelectedIndex(0);
            viewArea.getCmbTemperatureSubFeeder().setSelectedIndex(0);
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.TEMPERATURES_NO_FOUND), MessagesStructure.justify));
            viewArea.dispose();
        }       
    }//Fin del método
    
    /**
     * Método para llenar los combos con Fases.
     */
    public void fill_combos_phases(){
        phasesFound = ServicePhase.find_phases();
        if (phasesFound != null){
            for (Phase phase : phasesFound){
                viewArea.getCmbPhasesIluminaria().addItem(phase);
                viewArea.getCmbPhasesPowerPoint().addItem(phase);
                viewArea.getCmbPhasesSubFeeder().addItem(phase);
            }     
                viewArea.getCmbPhasesIluminaria().setSelectedIndex(0);
                viewArea.getCmbPhasesPowerPoint().setSelectedIndex(0);
                viewArea.getCmbPhasesSubFeeder().setSelectedIndex(0);
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.PHASES_NO_FOUND), MessagesStructure.justify));
            viewArea.dispose();
        }       
    }//Fin del método
    
    /**
     * Método para llenar los combos con Voltages de Iluminaria.
     */
    public void fill_combos_voltages_iluminaria(){ 
        voltagesFound = ServiceVoltage.find_voltages();
        if (voltagesFound != null){            
            viewArea.getCmbVoltageIluminaria().removeAllItems();
            for (Voltage voltage : voltagesFound){
                if (((Phase)viewArea.getCmbPhasesIluminaria().getSelectedItem()).getName().equalsIgnoreCase(TypePhases.SINGLE_PHASE_TWO_THREAD.getPhase())
                        && voltage.getVoltage() < 200){
                    viewArea.getCmbVoltageIluminaria().addItem(voltage);             
                } else if ((((Phase)viewArea.getCmbPhasesIluminaria().getSelectedItem()).getName().equalsIgnoreCase(TypePhases.SINGLE_PHASE_THREE_THREAD.getPhase())
                        || ((Phase)viewArea.getCmbPhasesIluminaria().getSelectedItem()).getName().equalsIgnoreCase(TypePhases.PHASE_FOUR_THREAD.getPhase()))
                        && voltage.getVoltage() >= 200){
                    viewArea.getCmbVoltageIluminaria().addItem(voltage);                  
                }
            }
            viewArea.getCmbVoltageIluminaria().setSelectedIndex(0);
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.VOLTAGES_NO_FOUND), MessagesStructure.justify));
            viewArea.dispose();
        }       
    }//Fin del método
    
    /**
     * Método para llenar los combos con Voltages de Toma Corriente.
    */
    public void fill_combos_voltages_powerpoint(){ 
        voltagesFound = ServiceVoltage.find_voltages();
        if (voltagesFound != null){            
            viewArea.getCmbVoltagePowerPoint().removeAllItems();
            for (Voltage voltage : voltagesFound){
                if (((Phase)viewArea.getCmbPhasesPowerPoint().getSelectedItem()).getName().equalsIgnoreCase(TypePhases.SINGLE_PHASE_TWO_THREAD.getPhase())
                        && voltage.getVoltage() < 200){
                    viewArea.getCmbVoltagePowerPoint().addItem(voltage);             
                } else if ((((Phase)viewArea.getCmbPhasesPowerPoint().getSelectedItem()).getName().equalsIgnoreCase(TypePhases.SINGLE_PHASE_THREE_THREAD.getPhase())
                        || ((Phase)viewArea.getCmbPhasesPowerPoint().getSelectedItem()).getName().equalsIgnoreCase(TypePhases.PHASE_FOUR_THREAD.getPhase()))
                        && voltage.getVoltage() >= 200){
                    viewArea.getCmbVoltagePowerPoint().addItem(voltage);                  
                }
            }
            viewArea.getCmbVoltagePowerPoint().setSelectedIndex(0);       
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.VOLTAGES_NO_FOUND), MessagesStructure.justify));
            viewArea.dispose();
        }       
    }//Fin del método
    
    /**
     * Método para llenar los combos con Voltages para Sub-Alimentador.
    */
    public void fill_combos_voltages_subfeeder(){ 
        voltagesFound = ServiceVoltage.find_voltages();
        if (voltagesFound != null){            
            viewArea.getCmbVoltageSubFeeder().removeAllItems();
            for (Voltage voltage : voltagesFound){  
                if (((Phase)viewArea.getCmbPhasesSubFeeder().getSelectedItem()).getName().equalsIgnoreCase(TypePhases.SINGLE_PHASE_TWO_THREAD.getPhase())
                        && voltage.getVoltage() < 200){
                    viewArea.getCmbVoltageSubFeeder().addItem(voltage);             
                } else if ((((Phase)viewArea.getCmbPhasesSubFeeder().getSelectedItem()).getName().equalsIgnoreCase(TypePhases.SINGLE_PHASE_THREE_THREAD.getPhase())
                        || ((Phase)viewArea.getCmbPhasesSubFeeder().getSelectedItem()).getName().equalsIgnoreCase(TypePhases.PHASE_FOUR_THREAD.getPhase()))
                        && voltage.getVoltage() >= 200){
                    viewArea.getCmbVoltageSubFeeder().addItem(voltage);                  
                }
            }
            viewArea.getCmbVoltageSubFeeder().setSelectedIndex(0);
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.VOLTAGES_NO_FOUND), MessagesStructure.justify));
            viewArea.dispose();
        }       
    }//Fin del método
    
    /**
     * Método para llenar los combos con Calibers.
     */
    public void fill_combos_calibers(){
        calibersFound = ServiceCaliber.find_caliber();
        if (calibersFound != null){
            for (Caliber caliber : calibersFound){
                viewArea.getCmbCalibersIluminaria().addItem(caliber);
                viewArea.getCmbCalibersPowerPoint().addItem(caliber);
                viewArea.getCmbCalibersSubFeeder().addItem(caliber);
                viewArea.getCmbCalibersSubFeederNeutral().addItem(caliber);
            }     
                viewArea.getCmbCalibersIluminaria().setSelectedIndex(0);
                viewArea.getCmbCalibersPowerPoint().setSelectedIndex(0);
                viewArea.getCmbCalibersSubFeeder().setSelectedIndex(0);
                viewArea.getCmbCalibersSubFeederNeutral().setSelectedIndex(0);
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.CALIBERS_NO_FOUND), MessagesStructure.justify));
            viewArea.dispose();
        }       
    }//Fin del método
    
    /**
     * Método para llenar los combos con Ductos.
     */
    public void fill_combos_ducts(){        
        ductsFound = ServiceDuct.find_ducts();
        if (ductsFound != null){
            for (Duct duct : ductsFound){
                viewArea.getCmbDuctIluminaria().addItem(duct);
                viewArea.getCmbDuctPowerPoint().addItem(duct);
                viewArea.getCmbDuctSubFeeder().addItem(duct);
            }     
                viewArea.getCmbDuctIluminaria().setSelectedIndex(0);
                viewArea.getCmbDuctPowerPoint().setSelectedIndex(0);
                viewArea.getCmbDuctSubFeeder().setSelectedIndex(0);
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.DUCTS_NO_FOUND), MessagesStructure.justify));
            viewArea.dispose();
        }       
    }//Fin del método
    
    /**
     * Método para llenar los combos con Materiales.
     */
    public void fill_combos_materials(){        
        materialsFound = ServiceMaterial.find_materials();
        if (materialsFound != null){
            for (Material material : materialsFound){
                viewArea.getCmbMaterialIluminaria().addItem(material);
                viewArea.getCmbMaterialPowerPoint().addItem(material);
                viewArea.getCmbMaterialSubFeeder().addItem(material);
            }     
                viewArea.getCmbMaterialIluminaria().setSelectedIndex(0);
                viewArea.getCmbMaterialPowerPoint().setSelectedIndex(0);
                viewArea.getCmbMaterialSubFeeder().setSelectedIndex(0);
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.MATERIALS_NO_FOUND), MessagesStructure.justify));
            viewArea.dispose();
        }       
    }//Fin del método
        
    /**
     * Método para calcular conductor en iluminaria o Toma Corriente.
     */
    private void calculate_conductor(TypeOfBranchCircuitInArea typeOfBranchCircuitInArea){ 
        switch (typeOfBranchCircuitInArea){                
            case ILUMINARIA:                    
                if (!viewArea.getrBtnAirIluminaria().isSelected() && !viewArea.getrBtnGroundIluminaria().isSelected()){
                    MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.RUSH_NO_FOUND), MessagesStructure.justify));
                    viewArea.getrBtnGroundIluminaria().requestFocus();
                } else {
                    caliberFoundIluminaria = MethodsForCalculationsIluminariaPowerPoint.calculateCaliberIluminariaPowerPoint(
                            TypeOfBranchCircuitInArea.ILUMINARIA, 
                            Double.valueOf(viewArea.getJspArea().getValue().toString()), 
                            (Voltage)viewArea.getCmbVoltageIluminaria().getSelectedItem(), 
                            (Material)viewArea.getCmbMaterialIluminaria().getSelectedItem(), 
                            (Temperature)viewArea.getCmbTemperatureIlimunaria().getSelectedItem(), 
                            Double.valueOf(viewArea.getJspPowerFactor().getValue().toString()), 
                            viewArea.getCmbPhasesIluminaria().getSelectedIndex());                    
                    
                    caliberIluminariaPipeline = MethodsForCalculationsIluminariaPowerPoint.calculate_pipeline_iluminaria_powerPoint(
                            new Caliber(caliberFoundIluminaria.getCaliber().getCode(), 0), 
                            (Phase)viewArea.getCmbPhasesIluminaria().getSelectedItem(), 
                            viewArea.getCmbPipelineIuminaria().getSelectedItem().toString());

                    materialCaliberIluminaria = viewArea.getCmbPipelineIuminaria().getSelectedItem().toString();
                    
                    intensityDesignFound = MethodsForCalculationsIluminariaPowerPoint.calculate_instensity_design(new Calibers(
                            0, 
                            (Material)viewArea.getCmbMaterialIluminaria().getSelectedItem(), 
                            (Temperature)viewArea.getCmbTemperatureIlimunaria().getSelectedItem(), 
                            null, 
                            caliberFoundIluminaria.getCaliber()));
                    
                    branchCircuitIluminaria = MethodsForCalculationsIluminariaPowerPoint.calculateBranchCircuit(
                            MethodsForCalculationsIluminariaPowerPoint.intensity(
                                    MethodsForCalculationsIluminariaPowerPoint.potencyInIluminaria(Double.valueOf(viewArea.getJspArea().getValue().toString())), 
                                    ((Voltage)viewArea.getCmbVoltageIluminaria().getSelectedItem()).getVoltage(), 
                                    Double.valueOf(viewArea.getJspPowerFactor().getValue().toString()), 
                                    viewArea.getCmbPhasesIluminaria().getSelectedIndex()), 
                            MethodsForCalculationsIluminariaPowerPoint.currentLimitIluminaria);
                    
                    breakerFoundIluminaria  = MethodsForCalculationsIluminariaPowerPoint.find_breaker(
                            TypeOfBranchCircuitInArea.ILUMINARIA, 
                            Double.valueOf(viewArea.getJspArea().getValue().toString()), 
                            (Voltage)viewArea.getCmbVoltageIluminaria().getSelectedItem(), 
                            (Material)viewArea.getCmbMaterialIluminaria().getSelectedItem(),  
                            Double.valueOf(viewArea.getJspPowerFactor().getValue().toString()), 
                            viewArea.getCmbPhasesIluminaria().getSelectedIndex(),
                            intensityDesignFound);
                    
                    if (caliberFoundIluminaria == null){  
                        MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.CALIBER_NO_FOUND), MessagesStructure.justify));
                    } else {
                        viewArea.getCmbCalibersIluminaria().setSelectedItem(caliberFoundIluminaria.getCaliber());
                        if (((Material)viewArea.getCmbMaterialIluminaria().getSelectedItem()).getName().equals(TypeMaterials.COOPER.getMaterial())){                
                            viewArea.getLblCaliber().setText(MethodsForCalculationsIluminariaPowerPoint.number_of_calibers((Phase)viewArea.getCmbPhasesIluminaria().getSelectedItem()) + " #" + caliberFoundIluminaria.getCaliber().getName() + " Cu " + MethodsForCalculationsIluminariaPowerPoint.typeCaliber(typeCaliberIluminaria, (Temperature)viewArea.getCmbTemperatureIlimunaria().getSelectedItem()) + " " + MethodsForCalculationsIluminariaPowerPoint.number_of_brakers((Phase)viewArea.getCmbPhasesIluminaria().getSelectedItem(), breakerFoundIluminaria.getCapacity()));
                        } else if (((Material)viewArea.getCmbMaterialIluminaria().getSelectedItem()).getName().equals(TypeMaterials.ALUMINIUM.getMaterial())) {
                            viewArea.getLblCaliber().setText(MethodsForCalculationsIluminariaPowerPoint.number_of_calibers((Phase)viewArea.getCmbPhasesIluminaria().getSelectedItem()) + " #" + caliberFoundIluminaria.getCaliber().getName() + " Al " + MethodsForCalculationsIluminariaPowerPoint.typeCaliber(typeCaliberIluminaria, (Temperature)viewArea.getCmbTemperatureIlimunaria().getSelectedItem()) + " " + MethodsForCalculationsIluminariaPowerPoint.number_of_brakers((Phase)viewArea.getCmbPhasesIluminaria().getSelectedItem(), breakerFoundIluminaria.getCapacity()));
                        }
                        viewArea.getLblBranchCircuitIluminaria().setText(String.valueOf(branchCircuitIluminaria));
                        viewArea.getBtnCalculateBreakdownIluminaria().doClick();
                        potencySubFeeder = MethodsForCalculationsIluminariaPowerPoint.potencyInIluminariaAndPowerPoint(
                                Double.valueOf(viewArea.getJspArea().getValue().toString()) * MethodsForCalculationsIluminariaPowerPoint.iluminariaConstantSinglefamilyHome,
                                Double.valueOf(viewArea.getTxtQuantityPowerPoint().getValue().toString()) * MethodsForCalculationsIluminariaPowerPoint.powerPointConstant);
                        potencyTotal = (viewArea.getArea().getPotency_total() - viewArea.getPotencyOld()) + potencySubFeeder;
                        potencyTotalNeutral = (viewArea.getArea().getNeutral()- viewArea.getPotencyOld()) + potencySubFeeder;
                        viewArea.getLblPotencyTotalSubFeeder().setText(String.valueOf((viewArea.getArea().getPotency_total() - viewArea.getPotencyOld()) + potencySubFeeder) + " W");
                        viewArea.getLblPotencyNeutralSubFeeder().setText(String.valueOf((viewArea.getArea().getNeutral()- viewArea.getPotencyOld()) + potencySubFeeder) + " W");    
                    }
                }
                break;
            case POWER_POINT:      
                if (!viewArea.getrBtnAirPowerPoint().isSelected() && !viewArea.getrBtnGroundPowerPoint().isSelected()){
                    MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.RUSH_NO_FOUND), MessagesStructure.justify));
                    viewArea.getrBtnGroundPowerPoint().requestFocus();
                } else {
                    caliberFoundPowerPoint = MethodsForCalculationsIluminariaPowerPoint.calculateCaliberIluminariaPowerPoint(
                            TypeOfBranchCircuitInArea.POWER_POINT, 
                            Double.valueOf(viewArea.getTxtQuantityPowerPoint().getValue().toString()), 
                            (Voltage)viewArea.getCmbVoltagePowerPoint().getSelectedItem(), 
                            (Material)viewArea.getCmbMaterialPowerPoint().getSelectedItem(), 
                            (Temperature)viewArea.getCmbTemperaturePowerPoint().getSelectedItem(), 
                            Double.valueOf(viewArea.getJspPowerFactorPowerPoint().getValue().toString()), 
                            viewArea.getCmbPhasesPowerPoint().getSelectedIndex());
                            
                    caliberPowerPointPipeline = MethodsForCalculationsIluminariaPowerPoint.calculate_pipeline_iluminaria_powerPoint(
                            new Caliber(caliberFoundPowerPoint.getCaliber().getCode(), 0), 
                            (Phase)viewArea.getCmbPhasesPowerPoint().getSelectedItem(), 
                            viewArea.getCmbPipelinePowerPoint().getSelectedItem().toString());
                    
                    materialCaliberPowerPoint = viewArea.getCmbPipelinePowerPoint().getSelectedItem().toString();
                    
                    intensityDesignFound = MethodsForCalculationsIluminariaPowerPoint.calculate_instensity_design(new Calibers(
                            0, 
                            (Material)viewArea.getCmbMaterialPowerPoint().getSelectedItem(), 
                            (Temperature)viewArea.getCmbTemperaturePowerPoint().getSelectedItem(), 
                            null, 
                            caliberFoundPowerPoint.getCaliber()));
                    
                    branchCircuitPowerPoint = MethodsForCalculationsIluminariaPowerPoint.calculateBranchCircuit(
                            MethodsForCalculationsIluminariaPowerPoint.intensity(
                                    MethodsForCalculationsIluminariaPowerPoint.potencyInPowerPoint(Integer.valueOf(viewArea.getTxtQuantityPowerPoint().getValue().toString())), 
                                    ((Voltage)viewArea.getCmbVoltageIluminaria().getSelectedItem()).getVoltage(), 
                                    Double.valueOf(viewArea.getJspPowerFactor().getValue().toString()), 
                                    viewArea.getCmbPhasesIluminaria().getSelectedIndex()), 
                            MethodsForCalculationsIluminariaPowerPoint.currentLimitPowerPoint);
                    
                    breakerFoundPorwerPoint = MethodsForCalculationsIluminariaPowerPoint.find_breaker(
                            TypeOfBranchCircuitInArea.POWER_POINT, 
                            Double.valueOf(viewArea.getTxtQuantityPowerPoint().getValue().toString()), 
                            (Voltage)viewArea.getCmbVoltagePowerPoint().getSelectedItem(), 
                            (Material)viewArea.getCmbMaterialPowerPoint().getSelectedItem(),  
                            Double.valueOf(viewArea.getJspPowerFactorPowerPoint().getValue().toString()), 
                            viewArea.getCmbPhasesPowerPoint().getSelectedIndex(),
                            intensityDesignFound);

                    if (caliberFoundPowerPoint.getCaliber() == null){
                        MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.CALIBER_NO_FOUND), MessagesStructure.justify));
                    } else {                    
                        viewArea.getCmbCalibersPowerPoint().setSelectedItem(caliberFoundPowerPoint.getCaliber());
                        if (((Material)viewArea.getCmbMaterialPowerPoint().getSelectedItem()).getName().equals(TypeMaterials.COOPER.getMaterial())){                
                            viewArea.getLblCaliberPowerPoint().setText(MethodsForCalculationsIluminariaPowerPoint.number_of_calibers((Phase)viewArea.getCmbPhasesPowerPoint().getSelectedItem()) + " #" + caliberFoundPowerPoint.getCaliber().getName() + " Cu " + MethodsForCalculationsIluminariaPowerPoint.typeCaliber(typeCaliberPowerPoint, (Temperature)viewArea.getCmbTemperaturePowerPoint().getSelectedItem()) + " " + MethodsForCalculationsIluminariaPowerPoint.number_of_brakers((Phase)viewArea.getCmbPhasesPowerPoint().getSelectedItem(), breakerFoundPorwerPoint.getCapacity()));
                        } else if (((Material)viewArea.getCmbMaterialPowerPoint().getSelectedItem()).getName().equals(TypeMaterials.ALUMINIUM.getMaterial())) {
                            viewArea.getLblCaliberPowerPoint().setText(MethodsForCalculationsIluminariaPowerPoint.number_of_calibers((Phase)viewArea.getCmbPhasesPowerPoint().getSelectedItem()) + " #" + caliberFoundPowerPoint.getCaliber().getName() + " Al " + MethodsForCalculationsIluminariaPowerPoint.typeCaliber(typeCaliberPowerPoint, (Temperature)viewArea.getCmbTemperaturePowerPoint().getSelectedItem()) + " " + MethodsForCalculationsIluminariaPowerPoint.number_of_brakers((Phase)viewArea.getCmbPhasesPowerPoint().getSelectedItem(), breakerFoundPorwerPoint.getCapacity()));
                        }               
                        viewArea.getLblBranchCircuitPowerPoint().setText(String.valueOf(branchCircuitPowerPoint));
                        viewArea.getBtnCalculateBreakdownPowerPoint().doClick();
                        potencySubFeeder = MethodsForCalculationsIluminariaPowerPoint.potencyInIluminariaAndPowerPoint(
                                Double.valueOf(viewArea.getJspArea().getValue().toString()) * MethodsForCalculationsIluminariaPowerPoint.iluminariaConstantSinglefamilyHome,
                                Double.valueOf(viewArea.getTxtQuantityPowerPoint().getValue().toString()) * MethodsForCalculationsIluminariaPowerPoint.powerPointConstant);
                        potencyTotal = (viewArea.getArea().getPotency_total() - viewArea.getPotencyOld()) + potencySubFeeder;
                        potencyTotalNeutral = (viewArea.getArea().getNeutral()- viewArea.getPotencyOld()) + potencySubFeeder;
                        viewArea.getLblPotencyTotalSubFeeder().setText(String.valueOf((viewArea.getArea().getPotency_total() - viewArea.getPotencyOld()) + potencySubFeeder) + " W");
                        viewArea.getLblPotencyNeutralSubFeeder().setText(String.valueOf((viewArea.getArea().getNeutral()- viewArea.getPotencyOld()) + potencySubFeeder) + " W");    
                    }
                }
                break;                
            case SUB_FEEDER:   
                if (potencyTotal == 0){
                    MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.AREA_POTENCY_TOTAL_NO_FOUND), MessagesStructure.justify));
                } else {                
                    if (!viewArea.getrBtnAirSubFeeder().isSelected() && !viewArea.getrBtnGroundSubFeeder().isSelected()){
                       MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.RUSH_NO_FOUND), MessagesStructure.justify));
                       viewArea.getrBtnGroundSubFeeder().requestFocus();
                    } else{        
                        caliberFoundSubFeeder = MethodsForCalculationsGlobal1.calculateCaliberForSubFeeder(
                                potencyTotal,
                                (Voltage)viewArea.getCmbVoltageSubFeeder().getSelectedItem(), 
                                (Material)viewArea.getCmbMaterialSubFeeder().getSelectedItem(), 
                                (Temperature)viewArea.getCmbTemperatureSubFeeder().getSelectedItem(), 
                                Double.valueOf(viewArea.getJspPowerSubFeeder().getValue().toString()), 
                                viewArea.getCmbPhasesSubFeeder().getSelectedIndex());  

                        caliberFoundSubFeederNeutral = MethodsForCalculationsGlobal1.calculateCaliberForSubFeeder(
                                potencyTotalNeutral,
                                (Voltage)viewArea.getCmbVoltageSubFeeder().getSelectedItem(), 
                                (Material)viewArea.getCmbMaterialSubFeeder().getSelectedItem(), 
                                (Temperature)viewArea.getCmbTemperatureSubFeeder().getSelectedItem(), 
                                Double.valueOf(viewArea.getJspPowerSubFeeder().getValue().toString()), 
                                viewArea.getCmbPhasesSubFeeder().getSelectedIndex());
                        
                        caliberSubFeederPipeline = MethodsForCalculationsIluminariaPowerPoint.calculate_pipeline(
                                new Caliber(caliberFoundSubFeeder.getCaliber().getCode(), 0), 
                                new Caliber(caliberFoundSubFeederNeutral.getCaliber().getCode(), 0), 
                                null, 
                                (Phase)viewArea.getCmbPhasesSubFeeder().getSelectedItem(), 
                                viewArea.getCmbPipelineSubFeeder().getSelectedItem().toString());
                        
                        intensityDesignFound = MethodsForCalculationsIluminariaPowerPoint.calculate_instensity_design(new Calibers(
                                0, 
                                (Material)viewArea.getCmbMaterialSubFeeder().getSelectedItem(), 
                                (Temperature)viewArea.getCmbTemperatureSubFeeder().getSelectedItem(), 
                                null, 
                                caliberFoundSubFeeder.getCaliber()));
                        
                        breakerFoundSubFeeder = MethodsForCalculationsGlobal1.find_breaker_subfeeder(
                                potencyTotal,
                                (Voltage)viewArea.getCmbVoltageSubFeeder().getSelectedItem(), 
                                (Material)viewArea.getCmbMaterialSubFeeder().getSelectedItem(), 
                                Double.valueOf(viewArea.getJspPowerSubFeeder().getValue().toString()), 
                                viewArea.getCmbPhasesSubFeeder().getSelectedIndex(),
                                intensityDesignFound);

                        if (caliberFoundSubFeeder == null){
                            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.CALIBER_NO_FOUND), MessagesStructure.justify));
                        } else { 
                            viewArea.getCmbCalibersSubFeeder().setSelectedItem(caliberFoundSubFeeder.getCaliber());
                            viewArea.getCmbCalibersSubFeederNeutral().setSelectedItem(caliberFoundSubFeederNeutral.getCaliber());                    
                            if (((Material)viewArea.getCmbMaterialSubFeeder().getSelectedItem()).getName().equals(TypeMaterials.COOPER.getMaterial())){                
                                viewArea.getLblCaliberSubFeeder().setText(MethodsForCalculationsGlobal1.number_of_calibers((Phase)viewArea.getCmbPhasesSubFeeder().getSelectedItem(), TypeCalibers.PHASE) + " #" + caliberFoundSubFeeder.getCaliber().getName() + " Cu " + MethodsForCalculationsGlobal1.typeCaliber(typeCaliberSubFeeder,(Temperature)viewArea.getCmbTemperatureSubFeeder().getSelectedItem()) + " " + MethodsForCalculationsGlobal1.number_of_brakers((Phase)viewArea.getCmbPhasesSubFeeder().getSelectedItem(), breakerFoundSubFeeder.getCapacity()));
                                viewArea.getLblCaliberNeutral().setText("1 Cable" + " #" + caliberFoundSubFeederNeutral.getCaliber().getName() + " Cu " + MethodsForCalculationsGlobal1.typeCaliber(typeCaliberSubFeeder,(Temperature)viewArea.getCmbTemperatureSubFeeder().getSelectedItem()));
                            } else if (((Material)viewArea.getCmbMaterialSubFeeder().getSelectedItem()).getName().equals(TypeMaterials.ALUMINIUM.getMaterial())) {
                                viewArea.getLblCaliberSubFeeder().setText(MethodsForCalculationsGlobal1.number_of_calibers((Phase)viewArea.getCmbPhasesSubFeeder().getSelectedItem(), TypeCalibers.PHASE) + " #" + caliberFoundSubFeeder.getCaliber().getName() + " Al " + MethodsForCalculationsGlobal1.typeCaliber(typeCaliberSubFeeder,(Temperature)viewArea.getCmbTemperatureSubFeeder().getSelectedItem()) + " " + MethodsForCalculationsGlobal1.number_of_brakers((Phase)viewArea.getCmbPhasesSubFeeder().getSelectedItem(), breakerFoundSubFeeder.getCapacity()));
                                viewArea.getLblCaliberNeutral().setText("1 Cable" + " #" + caliberFoundSubFeederNeutral.getCaliber().getName() + " Al " + MethodsForCalculationsGlobal1.typeCaliber(typeCaliberSubFeeder,(Temperature)viewArea.getCmbTemperatureSubFeeder().getSelectedItem()));
                            }
                            viewArea.getBtnCalculateBreakdownSubFeeder().doClick();                           
                        }          
                    }                     
                }
                break;
            default:
                break;
        }            
    }//Fin del método
    
    /**
     * Método para calcular reactancia
     * @return Retorna un objeto ResistanceReactance que almacena el valor de la reactancia.
     */
    private ResistanceReactance calculate_reactance(TypeOfBranchCircuitInArea typeOfBranchCircuitInArea){
        switch(typeOfBranchCircuitInArea){
            case ILUMINARIA:
                return MethodsForCalculationsIluminariaPowerPoint.calculate_reactance(
                    (Material)viewArea.getCmbMaterialIluminaria().getSelectedItem(),
                    (Caliber)viewArea.getCmbCalibersIluminaria().getSelectedItem(), 
                    (Duct)viewArea.getCmbDuctIluminaria().getSelectedItem());
            case POWER_POINT:                
                return  MethodsForCalculationsIluminariaPowerPoint.calculate_reactance(
                    (Material)viewArea.getCmbMaterialPowerPoint().getSelectedItem(),
                    (Caliber)viewArea.getCmbCalibersPowerPoint().getSelectedItem(), 
                    (Duct)viewArea.getCmbDuctPowerPoint().getSelectedItem());
            case SUB_FEEDER:                
                return  MethodsForCalculationsGlobal1.calculate_reactance(
                    (Material)viewArea.getCmbMaterialSubFeeder().getSelectedItem(),
                    (Caliber)viewArea.getCmbCalibersSubFeeder().getSelectedItem(), 
                    (Duct)viewArea.getCmbDuctSubFeeder().getSelectedItem());
            case NEUTRAL:
                return  MethodsForCalculationsGlobal1.calculate_reactance(
                    (Material)viewArea.getCmbMaterialSubFeeder().getSelectedItem(),
                    (Caliber)viewArea.getCmbCalibersSubFeederNeutral().getSelectedItem(), 
                    (Duct)viewArea.getCmbDuctSubFeeder().getSelectedItem());
            default:
                return null;
        }
    }//Fin del método
        
    /**
     * Método para calcular resistencia
     * @return Retorna un objeto ResistanceReactance que almacena el valor de la resistencia.
     */
    private ResistanceReactance calculate_resistance(TypeOfBranchCircuitInArea typeOfBranchCircuitInArea){
        switch(typeOfBranchCircuitInArea){
            case ILUMINARIA:
                return MethodsForCalculationsIluminariaPowerPoint.calculate_resistance(
                    (Material)viewArea.getCmbMaterialIluminaria().getSelectedItem(),
                    (Caliber)viewArea.getCmbCalibersIluminaria().getSelectedItem(), 
                    (Duct)viewArea.getCmbDuctIluminaria().getSelectedItem());
            case POWER_POINT:
                return MethodsForCalculationsIluminariaPowerPoint.calculate_resistance(
                    (Material)viewArea.getCmbMaterialPowerPoint().getSelectedItem(),
                    (Caliber)viewArea.getCmbCalibersPowerPoint().getSelectedItem(), 
                    (Duct)viewArea.getCmbDuctPowerPoint().getSelectedItem());
            case SUB_FEEDER:                
                return  MethodsForCalculationsGlobal1.calculate_resistance(
                    (Material)viewArea.getCmbMaterialSubFeeder().getSelectedItem(),
                    (Caliber)viewArea.getCmbCalibersSubFeeder().getSelectedItem(), 
                    (Duct)viewArea.getCmbDuctSubFeeder().getSelectedItem());
            case NEUTRAL:
                return  MethodsForCalculationsGlobal1.calculate_resistance(
                    (Material)viewArea.getCmbMaterialSubFeeder().getSelectedItem(),
                    (Caliber)viewArea.getCmbCalibersSubFeederNeutral().getSelectedItem(), 
                    (Duct)viewArea.getCmbDuctSubFeeder().getSelectedItem());
            default:
                return null;
        }
    }//Fin del método
    
    /**
     * Método para calcular la caída de voltage.
     */
    private void calculate_breakdownVoltage(TypeOfBranchCircuitInArea typeOfBranchCircuitInArea){
        switch(typeOfBranchCircuitInArea){
            case ILUMINARIA:
                if (caliberFoundIluminaria == null){
                    MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.AREA_CAPACITY_INTENSITY_NO_FOUND), MessagesStructure.justify));
                } else {
                    if (MethodsForCalculationsIluminariaPowerPoint.validate_caliber((Caliber)viewArea.getCmbCalibersIluminaria().getSelectedItem())){            
                        resistance = calculate_resistance(typeOfBranchCircuitInArea);
                        reactance  = calculate_reactance(typeOfBranchCircuitInArea);
                        if (resistance != null){            
                            breakdownVoltage = MethodsForCalculationsIluminariaPowerPoint.breakdownVoltage(
                                    Double.valueOf(viewArea.getJspArea().getValue().toString()), 
                                    MethodsForCalculationsIluminariaPowerPoint.iluminariaConstantSinglefamilyHome, 
                                    Double.valueOf(viewArea.getJspLength().getValue().toString()), 
                                    ((Voltage)viewArea.getCmbVoltageIluminaria().getSelectedItem()).getVoltage(), 
                                    reactance.getValue().getValour(), 
                                    Double.valueOf(viewArea.getJspPowerFactor().getValue().toString()), 
                                    resistance.getValue().getValour(), 
                                    Double.valueOf(viewArea.getJspAngle().getValue().toString()));
                            viewArea.getLblBreakdownVoltage().setText(String.valueOf(breakdownVoltage) + " %");
                            caliberSelectedIluminaria = (Caliber)viewArea.getCmbCalibersIluminaria().getSelectedItem(); 
                            
                            caliberIluminariaPipeline = MethodsForCalculationsIluminariaPowerPoint.calculate_pipeline_iluminaria_powerPoint(
                                    new Caliber(caliberSelectedIluminaria.getCode(), 0), 
                                    (Phase)viewArea.getCmbPhasesIluminaria().getSelectedItem(), 
                                    viewArea.getCmbPipelineIuminaria().getSelectedItem().toString());
                                                        
                            materialCaliberSubFeeder = viewArea.getCmbPipelineSubFeeder().getSelectedItem().toString();
                            
                            intensityDesignFound = MethodsForCalculationsIluminariaPowerPoint.calculate_instensity_design(new Calibers(
                                    0, 
                                    (Material)viewArea.getCmbMaterialIluminaria().getSelectedItem(), 
                                    (Temperature)viewArea.getCmbTemperatureIlimunaria().getSelectedItem(), 
                                    null, 
                                    caliberSelectedIluminaria));

                            breakerFoundIluminaria  = MethodsForCalculationsIluminariaPowerPoint.find_breaker(
                                    TypeOfBranchCircuitInArea.ILUMINARIA, 
                                    Double.valueOf(viewArea.getJspArea().getValue().toString()), 
                                    (Voltage)viewArea.getCmbVoltageIluminaria().getSelectedItem(), 
                                    (Material)viewArea.getCmbMaterialIluminaria().getSelectedItem(),  
                                    Double.valueOf(viewArea.getJspPowerFactor().getValue().toString()), 
                                    viewArea.getCmbPhasesIluminaria().getSelectedIndex(),
                                    intensityDesignFound);
                            
                            if (((Material)viewArea.getCmbMaterialIluminaria().getSelectedItem()).getName().equals(TypeMaterials.COOPER.getMaterial())){                
                                caliberUseIluminaria = String.valueOf(MethodsForCalculationsIluminariaPowerPoint.number_of_calibers((Phase)viewArea.getCmbPhasesIluminaria().getSelectedItem()) + " #" + caliberSelectedIluminaria.getName() + " Cu " + MethodsForCalculationsIluminariaPowerPoint.typeCaliber(typeCaliberIluminaria, (Temperature)viewArea.getCmbTemperatureIlimunaria().getSelectedItem()) + " " + MethodsForCalculationsIluminariaPowerPoint.number_of_brakers((Phase)viewArea.getCmbPhasesIluminaria().getSelectedItem(), breakerFoundIluminaria.getCapacity()));
                            } else if (((Material)viewArea.getCmbMaterialIluminaria().getSelectedItem()).getName().equals(TypeMaterials.ALUMINIUM.getMaterial())) {
                                caliberUseIluminaria = String.valueOf(MethodsForCalculationsIluminariaPowerPoint.number_of_calibers((Phase)viewArea.getCmbPhasesIluminaria().getSelectedItem()) + " #" + caliberSelectedIluminaria.getName() + " Al " + MethodsForCalculationsIluminariaPowerPoint.typeCaliber(typeCaliberIluminaria, (Temperature)viewArea.getCmbTemperatureIlimunaria().getSelectedItem()) + " " + MethodsForCalculationsIluminariaPowerPoint.number_of_brakers((Phase)viewArea.getCmbPhasesIluminaria().getSelectedItem(), breakerFoundIluminaria.getCapacity()));
                            }
                        }
                    }
                }
                break;
            case POWER_POINT:
                if (caliberFoundPowerPoint == null){
                    MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.AREA_CAPACITY_INTENSITY_NO_FOUND), MessagesStructure.justify));
                } else {
                    if (MethodsForCalculationsIluminariaPowerPoint.validate_caliber((Caliber)viewArea.getCmbCalibersPowerPoint().getSelectedItem())){            
                        resistance = calculate_resistance(typeOfBranchCircuitInArea);
                        reactance  = calculate_reactance(typeOfBranchCircuitInArea);
                        if (resistance != null){            
                            breakdownVoltage = MethodsForCalculationsIluminariaPowerPoint.breakdownVoltage(
                                    Double.valueOf(viewArea.getTxtQuantityPowerPoint().getValue().toString()), 
                                    MethodsForCalculationsIluminariaPowerPoint.powerPointConstant, 
                                    Double.valueOf(viewArea.getJspLengthPowerPoint().getValue().toString()), 
                                    ((Voltage)viewArea.getCmbVoltagePowerPoint().getSelectedItem()).getVoltage(), 
                                    reactance.getValue().getValour(), 
                                    Double.valueOf(viewArea.getJspPowerFactorPowerPoint().getValue().toString()), 
                                    resistance.getValue().getValour(), 
                                    Double.valueOf(viewArea.getJspAnglePowerPoint().getValue().toString()));
                            viewArea.getLblBreakdownVoltagePowerPoint().setText(String.valueOf(breakdownVoltage) + " %");
                            caliberSelectedPowerPoint = (Caliber)viewArea.getCmbCalibersPowerPoint().getSelectedItem(); 
                            
                            caliberPowerPointPipeline = MethodsForCalculationsIluminariaPowerPoint.calculate_pipeline_iluminaria_powerPoint(
                                    new Caliber(caliberSelectedPowerPoint.getCode(), 0), 
                                    (Phase)viewArea.getCmbPhasesPowerPoint().getSelectedItem(), 
                                    viewArea.getCmbPipelinePowerPoint().getSelectedItem().toString());
                            
                            intensityDesignFound = MethodsForCalculationsIluminariaPowerPoint.calculate_instensity_design(new Calibers(
                                    0, 
                                    (Material)viewArea.getCmbMaterialPowerPoint().getSelectedItem(), 
                                    (Temperature)viewArea.getCmbTemperaturePowerPoint().getSelectedItem(), 
                                    null, 
                                    caliberSelectedPowerPoint));

                            breakerFoundPorwerPoint = MethodsForCalculationsIluminariaPowerPoint.find_breaker(
                                    TypeOfBranchCircuitInArea.POWER_POINT, 
                                    Double.valueOf(viewArea.getTxtQuantityPowerPoint().getValue().toString()), 
                                    (Voltage)viewArea.getCmbVoltagePowerPoint().getSelectedItem(), 
                                    (Material)viewArea.getCmbMaterialPowerPoint().getSelectedItem(),  
                                    Double.valueOf(viewArea.getJspPowerFactorPowerPoint().getValue().toString()), 
                                    viewArea.getCmbPhasesPowerPoint().getSelectedIndex(),
                                    intensityDesignFound);
                            
                            if (((Material)viewArea.getCmbMaterialPowerPoint().getSelectedItem()).getName().equals(TypeMaterials.COOPER.getMaterial())){                
                                caliberUsePowerPoint = String.valueOf(MethodsForCalculationsIluminariaPowerPoint.number_of_calibers((Phase)viewArea.getCmbPhasesPowerPoint().getSelectedItem()) + " #" + caliberSelectedPowerPoint.getName() + " Cu " + MethodsForCalculationsIluminariaPowerPoint.typeCaliber(typeCaliberPowerPoint, (Temperature)viewArea.getCmbTemperaturePowerPoint().getSelectedItem()) + " " + MethodsForCalculationsIluminariaPowerPoint.number_of_brakers((Phase)viewArea.getCmbPhasesPowerPoint().getSelectedItem(), breakerFoundPorwerPoint.getCapacity()));
                            } else if (((Material)viewArea.getCmbMaterialPowerPoint().getSelectedItem()).getName().equals(TypeMaterials.ALUMINIUM.getMaterial())) {
                                caliberUsePowerPoint = String.valueOf(MethodsForCalculationsIluminariaPowerPoint.number_of_calibers((Phase)viewArea.getCmbPhasesPowerPoint().getSelectedItem()) + " #" + caliberSelectedPowerPoint.getName() + " Al " + MethodsForCalculationsIluminariaPowerPoint.typeCaliber(typeCaliberPowerPoint, (Temperature)viewArea.getCmbTemperaturePowerPoint().getSelectedItem()) + " " + MethodsForCalculationsIluminariaPowerPoint.number_of_brakers((Phase)viewArea.getCmbPhasesPowerPoint().getSelectedItem(), breakerFoundPorwerPoint.getCapacity()));
                            } 
                        }
                    }
                } 
                break;
            case SUB_FEEDER:
                if (caliberFoundSubFeeder == null){
                    MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.AREA_CAPACITY_INTENSITY_NO_FOUND), MessagesStructure.justify));
                } else {
                    if (MethodsForCalculationsGlobal1.validate_caliber((Caliber)viewArea.getCmbCalibersSubFeeder().getSelectedItem()) 
                            && MethodsForCalculationsGlobal1.validate_caliber((Caliber)viewArea.getCmbCalibersSubFeederNeutral().getSelectedItem())){   
                        if (resistance != null){           
                            resistance = calculate_resistance(typeOfBranchCircuitInArea);
                            reactance  = calculate_reactance(typeOfBranchCircuitInArea);  
                            breakdownVoltage = MethodsForCalculationsGlobal1.breakdownVoltage(
                                    potencyTotal,                                      
                                    Double.valueOf(viewArea.getJspLengthSubFeeder().getValue().toString()), 
                                    ((Voltage)viewArea.getCmbVoltageSubFeeder().getSelectedItem()).getVoltage(), 
                                    reactance.getValue().getValour(), 
                                    Double.valueOf(viewArea.getJspPowerSubFeeder().getValue().toString()), 
                                    resistance.getValue().getValour(), 
                                    Double.valueOf(viewArea.getJspAngleSubFeeder().getValue().toString()));
                            viewArea.getLblBreakdownVoltageSubFeeder().setText(String.valueOf(breakdownVoltage) + " %");
                            caliberSelectedSubFeeder = (Caliber)viewArea.getCmbCalibersSubFeeder().getSelectedItem(); 
                            
                            intensityDesignFound = MethodsForCalculationsIluminariaPowerPoint.calculate_instensity_design(new Calibers(
                                    0, 
                                    (Material)viewArea.getCmbMaterialSubFeeder().getSelectedItem(), 
                                    (Temperature)viewArea.getCmbTemperatureSubFeeder().getSelectedItem(), 
                                    null, 
                                    caliberSelectedSubFeeder));

                            breakerFoundSubFeeder = MethodsForCalculationsGlobal1.find_breaker_dryer(
                                    potencyTotal,
                                    (Voltage)viewArea.getCmbVoltageSubFeeder().getSelectedItem(), 
                                    (Material)viewArea.getCmbMaterialSubFeeder().getSelectedItem(), 
                                    Double.valueOf(viewArea.getJspPowerSubFeeder().getValue().toString()), 
                                    viewArea.getCmbPhasesSubFeeder().getSelectedIndex(),
                                    intensityDesignFound);
                            
                            resistance = calculate_resistance(TypeOfBranchCircuitInArea.NEUTRAL);
                            reactance  = calculate_reactance(TypeOfBranchCircuitInArea.NEUTRAL);  
                            breakdownVoltage = MethodsForCalculationsGlobal1.breakdownVoltage(
                                    potencyTotalNeutral,  
                                    Double.valueOf(viewArea.getJspLengthSubFeeder().getValue().toString()), 
                                    ((Voltage)viewArea.getCmbVoltageSubFeeder().getSelectedItem()).getVoltage(), 
                                    reactance.getValue().getValour(), 
                                    Double.valueOf(viewArea.getJspPowerSubFeeder().getValue().toString()), 
                                    resistance.getValue().getValour(), 
                                    Double.valueOf(viewArea.getJspAngleSubFeeder().getValue().toString()));
                            viewArea.getLblBreakdownVoltageSubFeederNeutral().setText(String.valueOf(breakdownVoltage) + " %");
                            caliberSelectedSubFeederNeutral = (Caliber)viewArea.getCmbCalibersSubFeederNeutral().getSelectedItem();  
                                            
                            caliberSubFeederPipeline = MethodsForCalculationsIluminariaPowerPoint.calculate_pipeline(
                                    new Caliber(caliberSelectedSubFeeder.getCode(), 0), 
                                    new Caliber(caliberSelectedSubFeederNeutral.getCode(), 0), 
                                    null, 
                                    (Phase)viewArea.getCmbPhasesSubFeeder().getSelectedItem(), 
                                    viewArea.getCmbPipelineSubFeeder().getSelectedItem().toString());
                            
                            if (((Material)viewArea.getCmbMaterialSubFeeder().getSelectedItem()).getName().equals(TypeMaterials.COOPER.getMaterial())){                
                                caliberUseSubFeeder = String.valueOf(MethodsForCalculationsGlobal1.number_of_calibers((Phase)viewArea.getCmbPhasesSubFeeder().getSelectedItem(), TypeCalibers.PHASE) + " #" + caliberSelectedSubFeeder.getName() + " Cu " + MethodsForCalculationsGlobal1.typeCaliber(typeCaliberSubFeeder,(Temperature)viewArea.getCmbTemperatureSubFeeder().getSelectedItem()) + " " + MethodsForCalculationsGlobal1.number_of_brakers((Phase)viewArea.getCmbPhasesSubFeeder().getSelectedItem(), breakerFoundSubFeeder.getCapacity()));
                                caliberUseSubFeederNeutral = String.valueOf("1 Cable" + " #" + caliberSelectedSubFeederNeutral.getName() + " Cu " + MethodsForCalculationsGlobal1.typeCaliber(typeCaliberSubFeeder,(Temperature)viewArea.getCmbTemperatureSubFeeder().getSelectedItem()));
                            } else if (((Material)viewArea.getCmbMaterialSubFeeder().getSelectedItem()).getName().equals(TypeMaterials.ALUMINIUM.getMaterial())) {
                                caliberUseSubFeeder = String.valueOf(MethodsForCalculationsGlobal1.number_of_calibers((Phase)viewArea.getCmbPhasesSubFeeder().getSelectedItem(), TypeCalibers.PHASE) + " #" + caliberSelectedSubFeeder.getName() + " Al " + MethodsForCalculationsGlobal1.typeCaliber(typeCaliberSubFeeder,(Temperature)viewArea.getCmbTemperatureSubFeeder().getSelectedItem()) + " " + MethodsForCalculationsGlobal1.number_of_brakers((Phase)viewArea.getCmbPhasesSubFeeder().getSelectedItem(), breakerFoundSubFeeder.getCapacity()));
                                caliberUseSubFeederNeutral = String.valueOf("1 Cable" + " #" + caliberSelectedSubFeederNeutral.getName() + " Al " + MethodsForCalculationsGlobal1.typeCaliber(typeCaliberSubFeeder,(Temperature)viewArea.getCmbTemperatureSubFeeder().getSelectedItem()));
                            }
                        }
                    }
                }
                break;
            default:
                break;
        }
     }//Fin del método
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(viewArea.getBtnRegister())) {
            area_registration();            
        } else if (e.getSource().equals(viewArea.getBtnCalculateCurrentCapacityIlumiaria())){
            calculate_conductor(TypeOfBranchCircuitInArea.ILUMINARIA);
        } else if (e.getSource().equals(viewArea.getBtnCalculateCurrentCapacityPowerPoint())){
            calculate_conductor(TypeOfBranchCircuitInArea.POWER_POINT);
        } else if(e.getSource().equals(viewArea.getBtnCalculateCurrentCapacitySubFeeder())){
            calculate_conductor(TypeOfBranchCircuitInArea.SUB_FEEDER);
        } else if (e.getSource().equals(viewArea.getBtnCalculateBreakdownIluminaria())){
            calculate_breakdownVoltage(TypeOfBranchCircuitInArea.ILUMINARIA);
        } else if (e.getSource().equals(viewArea.getBtnCalculateBreakdownPowerPoint())){
            calculate_breakdownVoltage(TypeOfBranchCircuitInArea.POWER_POINT);
        } else if (e.getSource().equals(viewArea.getBtnCalculateBreakdownSubFeeder())){
            calculate_breakdownVoltage(TypeOfBranchCircuitInArea.SUB_FEEDER);
        } else if (e.getSource().equals(viewArea.getBtnModify())) {
            modify_area();
        } else if (e.getSource().equals(viewArea.getBtnClose())) {
            viewArea.setModify(false);
            viewArea.dispose();
        } else if (e.getSource().equals(viewArea.getCmbPhasesIluminaria())) {
            this.fill_combos_voltages_iluminaria();
        } else if (e.getSource().equals(viewArea.getCmbPhasesPowerPoint())) {
            this.fill_combos_voltages_powerpoint();
        } else if (e.getSource().equals(viewArea.getCmbPhasesSubFeeder())){
            this.fill_combos_voltages_subfeeder();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        character = e.getKeyChar(); 
        if (e.getSource().equals(viewArea.getTxtName())) {
            if (viewArea.getTxtName().getText().length() == 50) {
                viewArea.getToolkit().beep();
                e.consume();
            }
        } 
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void windowOpened(WindowEvent e) {
        //potencyIluminariaPowerPointOld();
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
    public void stateChanged(ChangeEvent e) {    
        if (e.getSource().equals(viewArea.getrBtnAirIluminaria())){            
            if (viewArea.getrBtnAirIluminaria().isSelected()){                
                typeCaliberIluminaria = TypeRush.AIR;                
            }             
        } else if (e.getSource().equals(viewArea.getrBtnGroundIluminaria())){            
            if (viewArea.getrBtnGroundIluminaria().isSelected()){
                typeCaliberIluminaria = TypeRush.UNDERGROUND;                
            }             
        } else if (e.getSource().equals(viewArea.getrBtnAirPowerPoint())){            
            if (viewArea.getrBtnAirPowerPoint().isSelected()){
                typeCaliberPowerPoint = TypeRush.AIR;                
            }             
        } else if (e.getSource().equals(viewArea.getrBtnGroundPowerPoint())){            
            if (viewArea.getrBtnGroundPowerPoint().isSelected()){
                typeCaliberPowerPoint = TypeRush.UNDERGROUND;                
            }             
        } else if (e.getSource().equals(viewArea.getrBtnAirSubFeeder())){            
            if (viewArea.getrBtnAirSubFeeder().isSelected()){
                typeCaliberSubFeeder = TypeRush.AIR;                
            }             
        } else if (e.getSource().equals(viewArea.getrBtnGroundSubFeeder())){            
            if (viewArea.getrBtnGroundSubFeeder().isSelected()){
                typeCaliberSubFeeder = TypeRush.UNDERGROUND;                
            }             
        }     
    }

}
