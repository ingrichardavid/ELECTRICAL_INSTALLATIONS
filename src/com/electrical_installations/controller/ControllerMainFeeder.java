/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.controller;
 
import com.electrical_installations.configuration.Messages;
import com.electrical_installations.configuration.MessagesStructure;
import com.electrical_installations.model.entity.Charge;
import com.electrical_installations.model.entity.ConductorsMainFeeder;
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
import com.electrical_installations.model.enums.TypeMaterials;
import com.electrical_installations.model.enums.TypeOfBranchCircuitInArea;
import com.electrical_installations.model.enums.TypePhases;
import com.electrical_installations.model.enums.TypeRush;
import com.electrical_installations.model.service.ServiceCaliber;
import com.electrical_installations.model.service.ServiceDuct;
import com.electrical_installations.model.service.ServiceMaterial;
import com.electrical_installations.model.service.ServicePhase;
import com.electrical_installations.model.service.ServiceProject;
import com.electrical_installations.model.service.ServiceTemperature;
import com.electrical_installations.model.service.ServiceVoltage; 
import com.electrical_installations.view.ViewMainFeeder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Controlador para la vista ViewVoltageInCarga
 *
 * @author Ing. Richard David
 * @version 1
 * @since 2015-11-17
 */
public class ControllerMainFeeder implements ActionListener, WindowListener , KeyListener, MouseListener, ChangeListener {

    //Objectos, variables y constantes
    private final ViewMainFeeder viewMainFeeder;
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
    private String materialPipeline, caliberPipeline, caliberPipelineNeutral;
    private CalibersHearth calibersHearthFound;
    private HorsesPowers horsesPowersFound;
    private String caliberPhase, caliberNeutral, caliberHearth;
    private double potency;
    private Intensity intensityDesignFound;
    private static final Messages messages = Messages.getInstance();
    private int conductorForPhase, conductorForNeutral;

    /**
     * Contructor de la clase, recibe un objeto ViewMainFeeder 
     * @param viewMainFeeder
     */
    public ControllerMainFeeder(ViewMainFeeder viewMainFeeder) {
        this.viewMainFeeder = viewMainFeeder;
        this.phasesFound = null;
        this.intensityDesignFound = null;
        this.potency = 0;
    }//Fin del constructor 
      
    /**
     * Método para llenar los combos con temperaturas.
     */
    public void fill_combos_temperatures() {
        temperaturesFound = ServiceTemperature.find_temperatures();
        if (temperaturesFound != null) {
            for (Temperature temperature : temperaturesFound) {
                viewMainFeeder.getCmbTemperature().addItem(temperature); 
            }
            viewMainFeeder.getCmbTemperature().setSelectedIndex(0); 
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.TEMPERATURES_NO_FOUND), MessagesStructure.justify));
            viewMainFeeder.dispose();
        }
    }//Fin del método

    /**
     * Método para llenar los combos con Fases.
     */
    public void fill_combos_phases() {
        phasesFound = ServicePhase.find_phases();
        if (phasesFound != null) {
            for (Phase phase : phasesFound) {
                if (phase.getName().equalsIgnoreCase(TypePhases.PHASE_FOUR_THREAD.getPhase())) {
            
                    viewMainFeeder.getCmbPhases().addItem(phase); 
            
                }
            }
            viewMainFeeder.getCmbPhases().setSelectedIndex(0); 
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.PHASES_NO_FOUND), MessagesStructure.justify));
            viewMainFeeder.dispose();
        }
    }//Fin del método

    /**
     * Método para llenar los combos con Voltages.
     */
    public void fill_combos_voltages() {
        voltagesFound = ServiceVoltage.find_voltages();
        if (voltagesFound != null) {
            viewMainFeeder.getCmbVoltage().removeAllItems();
            for (Voltage voltage : voltagesFound) {
                if (((Phase)viewMainFeeder.getCmbPhases().getSelectedItem()).getName().equalsIgnoreCase(TypePhases.SINGLE_PHASE_TWO_THREAD.getPhase())
                        && voltage.getVoltage() < 200){
                    viewMainFeeder.getCmbVoltage().addItem(voltage);                    
                } else if ((((Phase)viewMainFeeder.getCmbPhases().getSelectedItem()).getName().equalsIgnoreCase(TypePhases.SINGLE_PHASE_THREE_THREAD.getPhase())
                        || ((Phase)viewMainFeeder.getCmbPhases().getSelectedItem()).getName().equalsIgnoreCase(TypePhases.PHASE_FOUR_THREAD.getPhase()))
                        && voltage.getVoltage() >= 200){
                    viewMainFeeder.getCmbVoltage().addItem(voltage);                     
                }
            }
            viewMainFeeder.getCmbVoltage().setSelectedIndex(0); 
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.VOLTAGES_NO_FOUND), MessagesStructure.justify));
            viewMainFeeder.dispose();
        }
    }//Fin del método
    
    /**
     * Método para llenar los combos con Calibers.
     */
    public void fill_combos_calibers(){
        calibersFound = ServiceCaliber.find_caliber();
        if (calibersFound != null){
            for (Caliber caliber : calibersFound){
                viewMainFeeder.getCmbCaliber().addItem(caliber);
                viewMainFeeder.getCmbCalibersNeutral().addItem(caliber);
            }     
            viewMainFeeder.getCmbCaliber().setSelectedIndex(0);
                viewMainFeeder.getCmbCalibersNeutral().setSelectedIndex(0);
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.CALIBERS_NO_FOUND), MessagesStructure.justify));
            viewMainFeeder.dispose();
        }       
    }//Fin del método
    
    /**
     * Método para llenar los combos con Ductos.
     */
    public void fill_combos_ducts(){        
        ductsFound = ServiceDuct.find_ducts();
        if (ductsFound != null){
            for (Duct duct : ductsFound){
                viewMainFeeder.getCmbDuct().addItem(duct);
            }     
            viewMainFeeder.getCmbDuct().setSelectedIndex(0);
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.DUCTS_NO_FOUND), MessagesStructure.justify));
            viewMainFeeder.dispose();
        }       
    }//Fin del método
    
    /**
     * Método para llenar los combos con Materiales.
     */
    public void fill_combos_materials(){        
        materialsFound = ServiceMaterial.find_materials();
        if (materialsFound != null){
            for (Material material : materialsFound){
                viewMainFeeder.getCmbMaterial().addItem(material);
            }     
                viewMainFeeder.getCmbMaterial().setSelectedIndex(0);
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.MATERIALS_NO_FOUND), MessagesStructure.justify));
            viewMainFeeder.dispose();
        }       
    }//Fin del método
    
    /**
     * Método para calcular el conductor.
     */
    private void calculate_conductor() {        
        if (!viewMainFeeder.getrBtnAir().isSelected() && !viewMainFeeder.getrBtnGround().isSelected()){
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.RUSH_NO_FOUND), MessagesStructure.justify));
            viewMainFeeder.getrBtnGround().requestFocus();
        } else {
            caliberPhaseFound = MethodsForCalculationsGlobal.calculateCaliberForMainFeeder(
                    viewMainFeeder.getPotency_total(), 
                    (Voltage)viewMainFeeder.getCmbVoltage().getSelectedItem(), 
                    (Material)viewMainFeeder.getCmbMaterial().getSelectedItem(), 
                    (Temperature)viewMainFeeder.getCmbTemperature().getSelectedItem(), 
                    Double.valueOf(viewMainFeeder.getJspPowerFactor().getValue().toString()), 
                    2);

            caliberNeutralFound = MethodsForCalculationsGlobal.calculateCaliberForMainFeeder(
                    viewMainFeeder.getNeutral_total(), 
                    (Voltage)viewMainFeeder.getCmbVoltage().getSelectedItem(), 
                    (Material)viewMainFeeder.getCmbMaterial().getSelectedItem(), 
                    (Temperature)viewMainFeeder.getCmbTemperature().getSelectedItem(), 
                    Double.valueOf(viewMainFeeder.getJspPowerFactor().getValue().toString()), 
                    2);
            
            conductorForPhase = MethodsForCalculationsGlobal.calculateNumberConductorForPhase(
                    MethodsForCalculationsGlobal.intensity(
                            viewMainFeeder.getPotency_total(), 
                            ((Voltage)viewMainFeeder.getCmbVoltage().getSelectedItem()).getVoltage(), 
                            Double.valueOf(viewMainFeeder.getJspPowerFactor().getValue().toString()), 
                            2), 
                    475);
                    
            conductorForNeutral = MethodsForCalculationsGlobal.calculateNumberConductorForPhase(
                    MethodsForCalculationsGlobal.intensity(
                            viewMainFeeder.getNeutral_total(), 
                            ((Voltage)viewMainFeeder.getCmbVoltage().getSelectedItem()).getVoltage(), 
                            Double.valueOf(viewMainFeeder.getJspPowerFactor().getValue().toString()), 
                            2), 
                    475);
            
            caliberPipeline = MethodsForCalculationsGlobal.calculate_pipeline(
            caliberPhaseFound.getCaliber(), 
            null, 
            null, 
            (Phase)viewMainFeeder.getCmbPhases().getSelectedItem(), 
            viewMainFeeder.getCmbPipeline().getSelectedItem().toString());
            
            caliberPipelineNeutral = MethodsForCalculationsGlobal.calculate_pipeline(
            caliberNeutralFound.getCaliber(), 
            null, 
            null, 
            new Phase(0, TypePhases.SINGLE_PHASE_TWO_THREAD.getPhase()), 
            viewMainFeeder.getCmbPipeline().getSelectedItem().toString());
            
            intensityDesignFound = MethodsForCalculationsIluminariaPowerPoint.calculate_instensity_design(new Calibers(
                        0, 
                        (Material)viewMainFeeder.getCmbMaterial().getSelectedItem(), 
                        (Temperature)viewMainFeeder.getCmbTemperature().getSelectedItem(), 
                        null, 
                        caliberPhaseFound.getCaliber()));
                              
            breakerPhaseFound = MethodsForCalculationsGlobal.find_breaker_main_feeder(
                    conductorForPhase * intensityDesignFound.getIntensity(),
                    new Intensity(0, null, MethodsForCalculationsGlobal.intensity(
                            viewMainFeeder.getPotency_total(), 
                            ((Voltage)viewMainFeeder.getCmbVoltage().getSelectedItem()).getVoltage(), 
                            Double.valueOf(viewMainFeeder.getJspPowerFactor().getValue().toString()), 
                            2)));  
            
            calibersHearthFound = MethodsForCalculationsGlobal.calculate_calibersHearth(
            viewMainFeeder.getPotency_total(), 
            (Voltage)viewMainFeeder.getCmbVoltage().getSelectedItem(), 
            Double.valueOf(viewMainFeeder.getJspPowerFactor().getValue().toString()), 
            2);
            
            if (caliberPhaseFound == null){
                MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.CALIBER_NO_FOUND), MessagesStructure.justify));
            } else {
                viewMainFeeder.getCmbCaliber().setSelectedItem(caliberPhaseFound.getCaliber());
                viewMainFeeder.getCmbCalibersNeutral().setSelectedItem(caliberNeutralFound.getCaliber());
                if (((Material)viewMainFeeder.getCmbMaterial().getSelectedItem()).getName().equals(TypeMaterials.COOPER.getMaterial())){                
                    viewMainFeeder.getLblCaliberPhase().setText("3 Cables" + " #" + caliberPhaseFound.getCaliber().getName() + " Cu " + MethodsForCalculationsGlobal.typeCaliber(typeCaliber,(Temperature)viewMainFeeder.getCmbTemperature().getSelectedItem()) + " " + "3x" + breakerPhaseFound.getCapacity());
                    viewMainFeeder.getLblCaliberNeutral().setText("2 Cables" + " #" + caliberNeutralFound.getCaliber().getName() + " Cu " + MethodsForCalculationsGlobal.typeCaliber(typeCaliber,(Temperature)viewMainFeeder.getCmbTemperature().getSelectedItem()));
                } else if (((Material)viewMainFeeder.getCmbMaterial().getSelectedItem()).getName().equals(TypeMaterials.ALUMINIUM.getMaterial())) {
                    viewMainFeeder.getLblCaliberPhase().setText("3 Cables" + " #" + caliberPhaseFound.getCaliber().getName() + " Al " + MethodsForCalculationsGlobal.typeCaliber(typeCaliber,(Temperature)viewMainFeeder.getCmbTemperature().getSelectedItem()) + " " + "3x" + breakerPhaseFound.getCapacity());
                    viewMainFeeder.getLblCaliberNeutral().setText("2 Cables" + " #" + caliberNeutralFound.getCaliber().getName() + " Al " + MethodsForCalculationsGlobal.typeCaliber(typeCaliber,(Temperature)viewMainFeeder.getCmbTemperature().getSelectedItem()));
                }
                if (calibersHearthFound == null){
                    viewMainFeeder.getLblCaliberEarth().setText("No aplica");
                } else {
                    viewMainFeeder.getLblCaliberEarth().setText("1 Cable " + calibersHearthFound.getCaliber().getName() + " " + MethodsForCalculationsGlobal.typeCaliber(typeCaliber,(Temperature)viewMainFeeder.getCmbTemperature().getSelectedItem()));
                }
                viewMainFeeder.getBtnCalculateBreakdown().doClick(); 
            }               
        }
    }//Fin del método.
        
    /**
     * Método para calcular reactancia
     * @return Retorna un objeto ResistanceReactance que almacena el valor de la reactancia.
     */
    private ResistanceReactance calculate_reactance(TypeOfBranchCircuitInArea typeOfBranchCircuitInArea){   
        if (typeOfBranchCircuitInArea == TypeOfBranchCircuitInArea.NEUTRAL){
            return  MethodsForCalculationsGlobal.calculate_reactance(
                (Material)viewMainFeeder.getCmbMaterial().getSelectedItem(),
                (Caliber)viewMainFeeder.getCmbCalibersNeutral().getSelectedItem(), 
                (Duct)viewMainFeeder.getCmbDuct().getSelectedItem());  
        } else {
            return  MethodsForCalculationsGlobal.calculate_reactance(
                (Material)viewMainFeeder.getCmbMaterial().getSelectedItem(),
                (Caliber)viewMainFeeder.getCmbCaliber().getSelectedItem(), 
                (Duct)viewMainFeeder.getCmbDuct().getSelectedItem());        
        }             
    }//Fin del método
        
    /**
     * Método para calcular resistencia
     * @return Retorna un objeto ResistanceReactance que almacena el valor de la resistencia.
     */
    private ResistanceReactance calculate_resistance(TypeOfBranchCircuitInArea typeOfBranchCircuitInArea){
        if (typeOfBranchCircuitInArea == TypeOfBranchCircuitInArea.NEUTRAL){
            return MethodsForCalculationsGlobal.calculate_resistance(
                (Material)viewMainFeeder.getCmbMaterial().getSelectedItem(),
                (Caliber)viewMainFeeder.getCmbCalibersNeutral().getSelectedItem(), 
                (Duct)viewMainFeeder.getCmbDuct().getSelectedItem());
        } else {
            return MethodsForCalculationsGlobal.calculate_resistance(
                (Material)viewMainFeeder.getCmbMaterial().getSelectedItem(),
                (Caliber)viewMainFeeder.getCmbCaliber().getSelectedItem(), 
                (Duct)viewMainFeeder.getCmbDuct().getSelectedItem());            
        }
    }//Fin del método

    //Método para probar los conductores por caída de voltaje.
    private void calculate_breakDownVoltage() {
        if (caliberPhaseFound == null || caliberNeutralFound == null || calibersHearthFound == null){
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.AREA_CAPACITY_INTENSITY_NO_FOUND), MessagesStructure.justify));
        } else {
            if (MethodsForCalculationsGlobal.validate_caliber((Caliber)viewMainFeeder.getCmbCaliber().getSelectedItem())
                    && MethodsForCalculationsGlobal.validate_caliber((Caliber)viewMainFeeder.getCmbCalibersNeutral().getSelectedItem())){       
                resistance = calculate_resistance(TypeOfBranchCircuitInArea.ILUMINARIA);
                reactance  = calculate_reactance(TypeOfBranchCircuitInArea.ILUMINARIA);
                if (resistance != null){     
                    breakdownVoltage = MethodsForCalculationsGlobal.breakdownVoltage(
                            viewMainFeeder.getPotency_total(),  
                            Double.valueOf(viewMainFeeder.getJspLength().getValue().toString()), 
                            ((Voltage)viewMainFeeder.getCmbVoltage().getSelectedItem()).getVoltage(), 
                            reactance.getValue().getValour(), 
                            Double.valueOf(viewMainFeeder.getJspPowerFactor().getValue().toString()), 
                            resistance.getValue().getValour(), 
                            Double.valueOf(viewMainFeeder.getJspAngle().getValue().toString()));   
                    viewMainFeeder.getLblBreakdownVoltage().setText(String.valueOf(breakdownVoltage) + " %"); 
                    caliberSelected = (Caliber)viewMainFeeder.getCmbCaliber().getSelectedItem(); 
                       
                    intensityDesignFound = MethodsForCalculationsIluminariaPowerPoint.calculate_instensity_design(new Calibers(
                                0, 
                                (Material)viewMainFeeder.getCmbMaterial().getSelectedItem(), 
                                (Temperature)viewMainFeeder.getCmbTemperature().getSelectedItem(), 
                                null, 
                                caliberSelected));
                    
                    breakerPhasePersistFound = MethodsForCalculationsGlobal.find_breaker_main_feeder(
                    conductorForPhase * intensityDesignFound.getIntensity(),
                    new Intensity(0, null, MethodsForCalculationsGlobal.intensity(
                            viewMainFeeder.getPotency_total(), 
                            ((Voltage)viewMainFeeder.getCmbVoltage().getSelectedItem()).getVoltage(), 
                            Double.valueOf(viewMainFeeder.getJspPowerFactor().getValue().toString()), 
                            2)));
                                                                             
                    resistance = calculate_resistance(TypeOfBranchCircuitInArea.NEUTRAL);
                    reactance  = calculate_reactance(TypeOfBranchCircuitInArea.NEUTRAL);
                    if (resistance != null){                
                        breakdownVoltage = MethodsForCalculationsGlobal.breakdownVoltage(
                                viewMainFeeder.getNeutral_total(),  
                                Double.valueOf(viewMainFeeder.getJspLength().getValue().toString()), 
                                ((Voltage)viewMainFeeder.getCmbVoltage().getSelectedItem()).getVoltage(), 
                                reactance.getValue().getValour(), 
                                Double.valueOf(viewMainFeeder.getJspPowerFactor().getValue().toString()), 
                                resistance.getValue().getValour(), 
                                Double.valueOf(viewMainFeeder.getJspAngle().getValue().toString()));  
                        viewMainFeeder.getLblBreakdownVoltageNeutral().setText(String.valueOf(breakdownVoltage) + " %");                     
                        caliberSelectedNeutral = (Caliber)viewMainFeeder.getCmbCalibersNeutral().getSelectedItem(); 
                        caliberPhase = viewMainFeeder.getLblCaliberPhase().getText();
                        caliberPhase = caliberPhase.replace(
                                "#" + caliberPhaseFound.getCaliber().getName(), 
                                "#" + caliberSelected.getName());
                        caliberPhase = caliberPhase.replace(
                                "3x" + breakerPhaseFound.getCapacity(),
                                "3x" + breakerPhasePersistFound.getCapacity());
                        caliberNeutral = viewMainFeeder.getLblCaliberNeutral().getText();
                        caliberNeutral = caliberNeutral.replace(
                                "#" + caliberNeutralFound.getCaliber().getName(), 
                                "#" + caliberSelectedNeutral.getName());
                        caliberHearth = viewMainFeeder.getLblCaliberEarth().getText();
            
                        caliberPipeline = MethodsForCalculationsGlobal.calculate_pipeline(
                        caliberSelected, 
                        null, 
                        null, 
                        (Phase)viewMainFeeder.getCmbPhases().getSelectedItem(), 
                        viewMainFeeder.getCmbPipeline().getSelectedItem().toString());

                        caliberPipelineNeutral = MethodsForCalculationsGlobal.calculate_pipeline(
                        caliberSelectedNeutral, 
                        null, 
                        null, 
                        new Phase(0, TypePhases.SINGLE_PHASE_TWO_THREAD.getPhase()), 
                        viewMainFeeder.getCmbPipeline().getSelectedItem().toString());
                        
                    }
                }  
            }            
        }
    }//Fin del método.
    
        /**
     * Método para registrar carga dentro de un área.
     */
    private void loadRegister(){
        if (caliberPhaseFound == null || caliberNeutralFound == null || calibersHearthFound == null){    
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.AREA_CAPACITY_INTENSITY_NO_FOUND), MessagesStructure.justify));
        } else {
            if (ServiceProject.insert_update_conductors_main_feeder(new ConductorsMainFeeder(
                    viewMainFeeder.getProject(), 
                    caliberPhase, 
                    caliberNeutral, 
                    caliberHearth, 
                    caliberPipeline, 
                    caliberPipelineNeutral, 
                    conductorForPhase, 
                    conductorForNeutral))) {
                viewMainFeeder.dispose();
            }             
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(viewMainFeeder.getBtnAdd())) {  
            loadRegister();
        } else if (e.getSource().equals(viewMainFeeder.getBtnClose())) {
            viewMainFeeder.dispose();
        } else if (e.getSource().equals(viewMainFeeder.getBtnCalculateCurrentCapacity())){ 
            this.calculate_conductor();
        } else if (e.getSource().equals(viewMainFeeder.getBtnCalculateBreakdown())){ 
            this.calculate_breakDownVoltage();
        } else if (e.getSource().equals(viewMainFeeder.getCmbPhases())) {
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
    }

    @Override
    public void keyPressed(KeyEvent e) {
     }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
      
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
        if (e.getSource().equals(viewMainFeeder.getrBtnAir())){            
            if (viewMainFeeder.getrBtnAir().isSelected()){                
                typeCaliber = TypeRush.AIR;                
            }             
        } else if (e.getSource().equals(viewMainFeeder.getrBtnGround())){            
            if (viewMainFeeder.getrBtnGround().isSelected()){
                typeCaliber = TypeRush.UNDERGROUND;                
            }             
        }            
    }

}
