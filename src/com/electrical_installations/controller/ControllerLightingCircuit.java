/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.controller;

import com.electrical_installations.configuration.Messages;
import com.electrical_installations.configuration.MessagesStructure;
import com.electrical_installations.model.entity.AreaIluminariaPowerPoint;
import com.electrical_installations.model.entity.masters.Caliber;
import com.electrical_installations.model.entity.masters.Duct;
import com.electrical_installations.model.entity.masters.Material;
import com.electrical_installations.model.entity.masters.Phase;
import com.electrical_installations.model.entity.masters.Temperature;
import com.electrical_installations.model.entity.masters.Voltage;
import com.electrical_installations.model.enums.TypeRush;
import com.electrical_installations.model.enums.TypeOfBranchCircuitInArea;
import com.electrical_installations.model.enums.TypePhases;
import com.electrical_installations.model.service.ServiceCaliber;
import com.electrical_installations.model.service.ServiceDuct;
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
 
    
    /**
     * Contructor de la clase, recibe un objeto ViewLightingCircuit
     *
     * @param viewLightingCircuit 
     */
    public ControllerLightingCircuit(ViewLightingCircuit viewLightingCircuit) {
        this.viewLightingCircuit = viewLightingCircuit;
    }

    //Fin del constructor
    /**
     * Método para validar que los campos obligatorios sean completados.     *
     * @return Retorna true si los campos obligatorios son completados.
     */
    private boolean validate_fields() {
        if (viewLightingCircuit.getTxtName().getText().equals("")) {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.AREA_NAME_NO_FOUND), MessagesStructure.justify));
            viewLightingCircuit.getTxtName().requestFocus();
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
        
 
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(viewLightingCircuit.getBtnRegister())) { 
            
        } else if (e.getSource().equals(viewLightingCircuit.getBtnCalculateCurrentCapacityLightingCircuit())){ 
        
        } else if (e.getSource().equals(viewLightingCircuit.getBtnCalculateBreakdownLightingCircuit())){
             
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
