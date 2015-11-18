/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.controller;
 
import com.electrical_installations.configuration.Messages;
import com.electrical_installations.configuration.MessagesStructure;
import com.electrical_installations.model.entity.Charge;
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
import com.electrical_installations.model.enums.TypePhases;
import com.electrical_installations.model.enums.TypeRush;
import com.electrical_installations.model.service.ServiceCaliber;
import com.electrical_installations.model.service.ServiceDuct;
import com.electrical_installations.model.service.ServiceMaterial;
import com.electrical_installations.model.service.ServicePercentageSinglePhaseMotors;
import com.electrical_installations.model.service.ServicePhase;
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
    private String materialPipeline, caliberPipeline;
    private CalibersHearth calibersHearthFound;
    private HorsesPowers horsesPowersFound;
    private String caliberPhase, caliberNeutral, caliberHearth;
    private double potency;
    private Intensity intensityDesignFound;
    private static final Messages messages = Messages.getInstance();

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
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(viewMainFeeder.getBtnAdd())) {  
            
        } else if (e.getSource().equals(viewMainFeeder.getBtnClose())) {
            viewMainFeeder.dispose();
        } else if (e.getSource().equals(viewMainFeeder.getBtnCalculateCurrentCapacity())){ 
        } else if (e.getSource().equals(viewMainFeeder.getBtnCalculateBreakdown())){ 
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