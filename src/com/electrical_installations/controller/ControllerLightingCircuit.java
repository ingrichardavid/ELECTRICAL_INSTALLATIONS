/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.controller;

import com.electrical_installations.configuration.Messages;
import com.electrical_installations.configuration.MessagesStructure; 
import com.electrical_installations.model.entity.LightingCircuit;
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
import com.electrical_installations.model.enums.TypeMaterials;
import com.electrical_installations.model.enums.TypeRush;
import com.electrical_installations.model.enums.TypeOfBranchCircuitInArea;
import com.electrical_installations.model.enums.TypePhases;
import com.electrical_installations.model.service.ServiceCaliber;
import com.electrical_installations.model.service.ServiceDuct;
import com.electrical_installations.model.service.ServiceLightingCircuit;
import com.electrical_installations.model.service.ServiceMaterial;
import com.electrical_installations.model.service.ServicePhase;
import com.electrical_installations.model.service.ServiceTemperature;
import com.electrical_installations.model.service.ServiceVoltage;
import com.electrical_installations.view.ViewLightingCircuit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
public class ControllerLightingCircuit implements ActionListener, KeyListener, WindowListener, ChangeListener {

    //Objetos, variables y constantes
    private final ViewLightingCircuit viewLightingCircuit;
    private static final Messages messages = Messages.getInstance();
    private char character;
    private List<Temperature> temperaturesFound;
    private List<Phase> phasesFound;
    private List<Voltage> voltagesFound;
    private List<Caliber> calibersFound;
    private List<Duct> ductsFound;
    private List<Material> materialsFound; 
    private TypeRush typeCaliberIluminaria;
    private Calibers caliberFoundIluminaria;
    private String caliberUseIluminaria, caliberLightingCircuitPipeline;
    private String materialCaliberLightingCircuit;
    private Intensity intensityDesignFound;
    private Caliber caliberSelectedIluminaria;
    private int branchCircuitLightingCircuit;
    private Breaker breakerFoundLightingCircuit;
    private double breakdownVoltage, potencySubFeeder,potencyTotal,potencyTotalNeutral;
    private ResistanceReactance resistance,reactance;
    
    /**
     * Contructor de la clase, recibe un objeto ViewLightingCircuit
     *
     * @param viewLightingCircuit 
     */
    public ControllerLightingCircuit(ViewLightingCircuit viewLightingCircuit) {
        this.viewLightingCircuit = viewLightingCircuit;
    }//Fin del constructor
    
    private void register_ligthting_circuit(){
        if(validate_fields()){
              if(ServiceLightingCircuit.create_lighting_circuit(new LightingCircuit(
                    0,
                    new Project(viewLightingCircuit.getProject().getCode(),
                                new TypeOfInstallation(viewLightingCircuit.getProject().getTypeOfInstallation().getCode(),
                                null),
                                0),
                    caliberUseIluminaria,
                    caliberLightingCircuitPipeline,
                    viewLightingCircuit.getTxtName().getText(),
                    caliberFoundIluminaria.getIntensity().getIntensity()))){
                  viewLightingCircuit.dispose();
            }
        }
    }    
    
    /**
     * Método para validar que los campos obligatorios sean completados.     *
     * @return Retorna true si los campos obligatorios son completados.
     */
    private boolean validate_fields() {
        if (viewLightingCircuit.getTxtName().getText().equals("")) {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.AREA_NAME_NO_FOUND), MessagesStructure.justify));
            viewLightingCircuit.getTxtName().requestFocus();
            return false;
        } else if (caliberFoundIluminaria == null){
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.AREA_CONDUCTOR_LIGHTING_CIRCUIT_NO_FOUND), MessagesStructure.justify));
            return false;
        }   
        return true;
    } //Fin del método
    
    /**
     * Método para llenar los combos con temperaturas.
     */
    public void fill_combos_temperatures(){
        temperaturesFound = ServiceTemperature.find_temperatures();
        if (temperaturesFound != null){
            for (Temperature temperature : temperaturesFound){
                viewLightingCircuit.getCmbTemperatureLightingCircuit().addItem(temperature); 
            }     
            viewLightingCircuit.getCmbTemperatureLightingCircuit().setSelectedIndex(0); 
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.TEMPERATURES_NO_FOUND), MessagesStructure.justify));
            viewLightingCircuit.dispose();
        }       
    }//Fin del método
    
    /**
     * Método para llenar los combos con Fases.
     */
    public void fill_combos_phases(){
        phasesFound = ServicePhase.find_phases();
        if (phasesFound != null){
            for (Phase phase : phasesFound){
                if(phase.getName().equalsIgnoreCase(TypePhases.PHASE_FOUR_THREAD.getPhase())){
                    viewLightingCircuit.getCmbPhasesLightingCircuit().addItem(phase);  
                }               
            }     
                viewLightingCircuit.getCmbPhasesLightingCircuit().setSelectedIndex(0); 
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.PHASES_NO_FOUND), MessagesStructure.justify));
            viewLightingCircuit.dispose();
        }       
    }//Fin del método
    
    /**
     * Método para llenar los combos con Voltages de Iluminaria.
     */
    public void fill_combos_voltages_iluminaria(){ 
        voltagesFound = ServiceVoltage.find_voltages();
        if (voltagesFound != null){            
            viewLightingCircuit.getCmbVoltageLightingCircuit().removeAllItems();
            for (Voltage voltage : voltagesFound){
                if (((Phase)viewLightingCircuit.getCmbPhasesLightingCircuit().getSelectedItem()).getName().equalsIgnoreCase(TypePhases.SINGLE_PHASE_TWO_THREAD.getPhase())
                        && voltage.getVoltage() < 200){
                    viewLightingCircuit.getCmbVoltageLightingCircuit().addItem(voltage);             
                } else if ((((Phase)viewLightingCircuit.getCmbPhasesLightingCircuit().getSelectedItem()).getName().equalsIgnoreCase(TypePhases.SINGLE_PHASE_THREE_THREAD.getPhase())
                        || ((Phase)viewLightingCircuit.getCmbPhasesLightingCircuit().getSelectedItem()).getName().equalsIgnoreCase(TypePhases.PHASE_FOUR_THREAD.getPhase()))
                        && voltage.getVoltage() >= 200){
                    viewLightingCircuit.getCmbVoltageLightingCircuit().addItem(voltage);                  
                }
            }
            viewLightingCircuit.getCmbVoltageLightingCircuit().setSelectedIndex(0);
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.VOLTAGES_NO_FOUND), MessagesStructure.justify));
            viewLightingCircuit.dispose();
        }       
    }//Fin del método
     
    /**
     * Método para llenar los combos con Calibers.
     */
    public void fill_combos_calibers(){
        calibersFound = ServiceCaliber.find_caliber();
        if (calibersFound != null){
            for (Caliber caliber : calibersFound){
                viewLightingCircuit.getCmbCalibersLightingCircuit().addItem(caliber); 
            }     
                viewLightingCircuit.getCmbCalibersLightingCircuit().setSelectedIndex(0); 
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.CALIBERS_NO_FOUND), MessagesStructure.justify));
            viewLightingCircuit.dispose();
        }       
    }//Fin del método
    
    /**
     * Método para llenar los combos con Ductos.
     */
    public void fill_combos_ducts(){        
        ductsFound = ServiceDuct.find_ducts();
        if (ductsFound != null){
            for (Duct duct : ductsFound){
                viewLightingCircuit.getCmbDuctLightingCircuit().addItem(duct); 
            }     
                viewLightingCircuit.getCmbDuctLightingCircuit().setSelectedIndex(0); 
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.DUCTS_NO_FOUND), MessagesStructure.justify));
            viewLightingCircuit.dispose();
        }       
    }//Fin del método
    
    /**
     * Método para llenar los combos con Materiales.
     */
    public void fill_combos_materials(){        
        materialsFound = ServiceMaterial.find_materials();
        if (materialsFound != null){
            for (Material material : materialsFound){
                viewLightingCircuit.getCmbMaterialLightingCircuit().addItem(material); 
            }     
                viewLightingCircuit.getCmbMaterialLightingCircuit().setSelectedIndex(0); 
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.MATERIALS_NO_FOUND), MessagesStructure.justify));
            viewLightingCircuit.dispose();
        }       
    }//Fin del método
 
    /**
     * Método para calcular reactancia
     * @return Retorna un objeto ResistanceReactance que almacena el valor de la reactancia.
     */
    private ResistanceReactance calculate_reactance(){
     
                return MethodsForCalculationsIluminariaPowerPoint.calculate_reactance(
                    (Material)viewLightingCircuit.getCmbMaterialLightingCircuit().getSelectedItem(),
                    (Caliber)viewLightingCircuit.getCmbCalibersLightingCircuit().getSelectedItem(), 
                    (Duct)viewLightingCircuit.getCmbDuctLightingCircuit().getSelectedItem());
        
       
    
    }//Fin del método
        
    /**
     * Método para calcular resistencia
     * @return Retorna un objeto ResistanceReactance que almacena el valor de la resistencia.
     */
    private ResistanceReactance calculate_resistance(){ 
                return MethodsForCalculationsIluminariaPowerPoint.calculate_resistance(
                    (Material)viewLightingCircuit.getCmbMaterialLightingCircuit().getSelectedItem(),
                    (Caliber)viewLightingCircuit.getCmbCalibersLightingCircuit().getSelectedItem(), 
                    (Duct)viewLightingCircuit.getCmbDuctLightingCircuit().getSelectedItem());         
          
    }//Fin del método
 
    /**
     * Método para calcular conductor.
     */
    private void calculate_conductor(){
                if (!viewLightingCircuit.getrBtnAirLightingCircuit().isSelected() && !viewLightingCircuit.getrBtnGroundLightingCircuit().isSelected()){
                    MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.RUSH_NO_FOUND), MessagesStructure.justify));
                    viewLightingCircuit.getrBtnGroundLightingCircuit().requestFocus();
                } else {
                    caliberFoundIluminaria = MethodsForCalculationsIluminariaPowerPoint.calculateCaliberIluminariaPowerPoint(
                            TypeOfBranchCircuitInArea.ILUMINARIA, 
                            Double.valueOf(viewLightingCircuit.getJspArea().getValue().toString()), 
                            (Voltage)viewLightingCircuit.getCmbVoltageLightingCircuit().getSelectedItem(), 
                            (Material)viewLightingCircuit.getCmbMaterialLightingCircuit().getSelectedItem(), 
                            (Temperature)viewLightingCircuit.getCmbTemperatureLightingCircuit().getSelectedItem(), 
                            Double.valueOf(viewLightingCircuit.getJspPowerFactor().getValue().toString()), 
                            2);                    
                    
                    caliberLightingCircuitPipeline = MethodsForCalculationsIluminariaPowerPoint.calculate_pipeline_iluminaria_powerPoint(
                            new Caliber(caliberFoundIluminaria.getCaliber().getCode(), 0), 
                            (Phase)viewLightingCircuit.getCmbPhasesLightingCircuit().getSelectedItem(), 
                            viewLightingCircuit.getCmbPipelineLightingCircuit().getSelectedItem().toString());

                    materialCaliberLightingCircuit = viewLightingCircuit.getCmbPipelineLightingCircuit().getSelectedItem().toString();
                    
                    intensityDesignFound = MethodsForCalculationsIluminariaPowerPoint.calculate_instensity_design(new Calibers(
                            0, 
                            (Material)viewLightingCircuit.getCmbMaterialLightingCircuit().getSelectedItem(), 
                            (Temperature)viewLightingCircuit.getCmbTemperatureLightingCircuit().getSelectedItem(), 
                            null, 
                            caliberFoundIluminaria.getCaliber()));
                    
                    branchCircuitLightingCircuit = MethodsForCalculationsIluminariaPowerPoint.calculateBranchCircuit(
                            MethodsForCalculationsIluminariaPowerPoint.intensity(
                                    MethodsForCalculationsIluminariaPowerPoint.potencyInIluminaria(Double.valueOf(viewLightingCircuit.getJspArea().getValue().toString())), 
                                    ((Voltage)viewLightingCircuit.getCmbVoltageLightingCircuit().getSelectedItem()).getVoltage(), 
                                    Double.valueOf(viewLightingCircuit.getJspPowerFactor().getValue().toString()), 
                                    viewLightingCircuit.getCmbPhasesLightingCircuit().getSelectedIndex()), 
                            MethodsForCalculationsIluminariaPowerPoint.currentLimitIluminaria);
                    
                    breakerFoundLightingCircuit  = MethodsForCalculationsIluminariaPowerPoint.find_breaker(
                            TypeOfBranchCircuitInArea.ILUMINARIA, 
                            Double.valueOf(viewLightingCircuit.getJspArea().getValue().toString()), 
                            (Voltage)viewLightingCircuit.getCmbVoltageLightingCircuit().getSelectedItem(), 
                            (Material)viewLightingCircuit.getCmbMaterialLightingCircuit().getSelectedItem(),  
                            Double.valueOf(viewLightingCircuit.getJspPowerFactor().getValue().toString()), 
                            viewLightingCircuit.getCmbPhasesLightingCircuit().getSelectedIndex(),
                            intensityDesignFound);
                    
                    if (caliberFoundIluminaria == null){  
                        MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.CALIBER_NO_FOUND), MessagesStructure.justify));
                    } else {
                        viewLightingCircuit.getCmbCalibersLightingCircuit().setSelectedItem(caliberFoundIluminaria.getCaliber());
                        if (((Material)viewLightingCircuit.getCmbMaterialLightingCircuit().getSelectedItem()).getName().equals(TypeMaterials.COOPER.getMaterial())){                
                            viewLightingCircuit.getLblCaliberLightingCircuit().setText(MethodsForCalculationsIluminariaPowerPoint.number_of_calibers((Phase)viewLightingCircuit.getCmbPhasesLightingCircuit().getSelectedItem()) + " #" + caliberFoundIluminaria.getCaliber().getName() + " Cu " + MethodsForCalculationsIluminariaPowerPoint.typeCaliber(typeCaliberIluminaria, (Temperature)viewLightingCircuit.getCmbTemperatureLightingCircuit().getSelectedItem()) + " " + MethodsForCalculationsIluminariaPowerPoint.number_of_brakers((Phase)viewLightingCircuit.getCmbPhasesLightingCircuit().getSelectedItem(), breakerFoundLightingCircuit.getCapacity()));
                        } else if (((Material)viewLightingCircuit.getCmbMaterialLightingCircuit().getSelectedItem()).getName().equals(TypeMaterials.ALUMINIUM.getMaterial())) {
                            viewLightingCircuit.getLblCaliberLightingCircuit().setText(MethodsForCalculationsIluminariaPowerPoint.number_of_calibers((Phase)viewLightingCircuit.getCmbPhasesLightingCircuit().getSelectedItem()) + " #" + caliberFoundIluminaria.getCaliber().getName() + " Al " + MethodsForCalculationsIluminariaPowerPoint.typeCaliber(typeCaliberIluminaria, (Temperature)viewLightingCircuit.getCmbTemperatureLightingCircuit().getSelectedItem()) + " " + MethodsForCalculationsIluminariaPowerPoint.number_of_brakers((Phase)viewLightingCircuit.getCmbPhasesLightingCircuit().getSelectedItem(), breakerFoundLightingCircuit.getCapacity()));
                        }
                        calculate_breakdownVoltage();
                        viewLightingCircuit.getLblBranchCircuitLightingCircuit().setText(String.valueOf(branchCircuitLightingCircuit));
                        viewLightingCircuit.getBtnCalculateBreakdownLightingCircuit().doClick(); 
                       }
                }
    }//fin del método

    /**
     * Método para calcular la caída de voltage.
     */             
    private void calculate_breakdownVoltage(){
                if (caliberFoundIluminaria == null){
                    MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.AREA_CAPACITY_INTENSITY_NO_FOUND), MessagesStructure.justify));
                } else {
                    if (MethodsForCalculationsIluminariaPowerPoint.validate_caliber((Caliber)viewLightingCircuit.getCmbCalibersLightingCircuit().getSelectedItem())){            
                        resistance = calculate_resistance();
                        reactance  = calculate_reactance();
                        if (resistance != null){            
                            breakdownVoltage = MethodsForCalculationsIluminariaPowerPoint.breakdownVoltage(
                                    Double.valueOf(viewLightingCircuit.getJspArea().getValue().toString()), 
                                    MethodsForCalculationsIluminariaPowerPoint.iluminariaConstantSinglefamilyHome, 
                                    Double.valueOf(viewLightingCircuit.getJspLengthLightingCircuit().getValue().toString()), 
                                    ((Voltage)viewLightingCircuit.getCmbVoltageLightingCircuit().getSelectedItem()).getVoltage(), 
                                    reactance.getValue().getValour(), 
                                    Double.valueOf(viewLightingCircuit.getJspPowerFactor().getValue().toString()), 
                                    resistance.getValue().getValour(), 
                                    Double.valueOf(viewLightingCircuit.getJspAngle().getValue().toString()));
                            viewLightingCircuit.getLblBreakdownVoltageLightingCircuit().setText(String.valueOf(breakdownVoltage) + " %");
                            caliberSelectedIluminaria = (Caliber)viewLightingCircuit.getCmbCalibersLightingCircuit().getSelectedItem(); 
                            
                            caliberLightingCircuitPipeline = MethodsForCalculationsIluminariaPowerPoint.calculate_pipeline_iluminaria_powerPoint(
                                    new Caliber(caliberSelectedIluminaria.getCode(), 0), 
                                    (Phase)viewLightingCircuit.getCmbPhasesLightingCircuit().getSelectedItem(), 
                                    viewLightingCircuit.getCmbPipelineLightingCircuit().getSelectedItem().toString());
                                                        
                            materialCaliberLightingCircuit = viewLightingCircuit.getCmbPipelineLightingCircuit().getSelectedItem().toString();
                            
                            intensityDesignFound = MethodsForCalculationsIluminariaPowerPoint.calculate_instensity_design(new Calibers(
                                    0, 
                                    (Material)viewLightingCircuit.getCmbMaterialLightingCircuit().getSelectedItem(), 
                                    (Temperature)viewLightingCircuit.getCmbTemperatureLightingCircuit().getSelectedItem(), 
                                    null, 
                                    caliberSelectedIluminaria));

                            breakerFoundLightingCircuit  = MethodsForCalculationsIluminariaPowerPoint.find_breaker(
                                    TypeOfBranchCircuitInArea.ILUMINARIA, 
                                    Double.valueOf(viewLightingCircuit.getJspArea().getValue().toString()), 
                                    (Voltage)viewLightingCircuit.getCmbVoltageLightingCircuit().getSelectedItem(), 
                                    (Material)viewLightingCircuit.getCmbMaterialLightingCircuit().getSelectedItem(),  
                                    Double.valueOf(viewLightingCircuit.getJspPowerFactor().getValue().toString()), 
                                    viewLightingCircuit.getCmbPhasesLightingCircuit().getSelectedIndex(),
                                    intensityDesignFound);
                            
                            if (((Material)viewLightingCircuit.getCmbMaterialLightingCircuit().getSelectedItem()).getName().equals(TypeMaterials.COOPER.getMaterial())){                
                                caliberUseIluminaria = String.valueOf(MethodsForCalculationsIluminariaPowerPoint.number_of_calibers((Phase)viewLightingCircuit.getCmbPhasesLightingCircuit().getSelectedItem()) + " #" + caliberSelectedIluminaria.getName() + " Cu " + MethodsForCalculationsIluminariaPowerPoint.typeCaliber(typeCaliberIluminaria, (Temperature)viewLightingCircuit.getCmbTemperatureLightingCircuit().getSelectedItem()) + " " + MethodsForCalculationsIluminariaPowerPoint.number_of_brakers((Phase)viewLightingCircuit.getCmbPhasesLightingCircuit().getSelectedItem(), breakerFoundLightingCircuit.getCapacity()));
                            } else if (((Material)viewLightingCircuit.getCmbMaterialLightingCircuit().getSelectedItem()).getName().equals(TypeMaterials.ALUMINIUM.getMaterial())) {
                                caliberUseIluminaria = String.valueOf(MethodsForCalculationsIluminariaPowerPoint.number_of_calibers((Phase)viewLightingCircuit.getCmbPhasesLightingCircuit().getSelectedItem()) + " #" + caliberSelectedIluminaria.getName() + " Al " + MethodsForCalculationsIluminariaPowerPoint.typeCaliber(typeCaliberIluminaria, (Temperature)viewLightingCircuit.getCmbTemperatureLightingCircuit().getSelectedItem()) + " " + MethodsForCalculationsIluminariaPowerPoint.number_of_brakers((Phase)viewLightingCircuit.getCmbPhasesLightingCircuit().getSelectedItem(), breakerFoundLightingCircuit.getCapacity()));
                            }
                        }
                    }
                }
    }//fin del método
 
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(viewLightingCircuit.getBtnRegister())) { 
            register_ligthting_circuit();
        } else if (e.getSource().equals(viewLightingCircuit.getBtnCalculateCurrentCapacityLightingCircuit())){  
            calculate_conductor();            
        } else if (e.getSource().equals(viewLightingCircuit.getBtnCalculateBreakdownLightingCircuit())){
             calculate_breakdownVoltage(); 
        } else if (e.getSource().equals(viewLightingCircuit.getBtnClose())) {
            viewLightingCircuit.dispose();
        } else if (e.getSource().equals(viewLightingCircuit.getCmbPhasesLightingCircuit())){
           this.fill_combos_voltages_iluminaria();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        character = e.getKeyChar(); 
        if (e.getSource().equals(viewLightingCircuit.getTxtName())) {
            if (viewLightingCircuit.getTxtName().getText().length() == 50) {
                viewLightingCircuit.getToolkit().beep();
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
        if (e.getSource().equals(viewLightingCircuit.getrBtnAirLightingCircuit())){            
            if (viewLightingCircuit.getrBtnAirLightingCircuit().isSelected()){                
                typeCaliberIluminaria = TypeRush.AIR;                
            }             
        } else if (e.getSource().equals(viewLightingCircuit.getrBtnGroundLightingCircuit())){            
            if (viewLightingCircuit.getrBtnGroundLightingCircuit().isSelected()){
                typeCaliberIluminaria = TypeRush.UNDERGROUND;                
            }             
                     
        }     
    }

}
