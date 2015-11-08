/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.controller;

import com.electrical_installations.configuration.Messages;
import com.electrical_installations.configuration.MessagesStructure;
import com.electrical_installations.global.method.Methods;
import com.electrical_installations.model.entity.masters.Material;
import com.electrical_installations.model.entity.masters.Phase;
import com.electrical_installations.model.entity.masters.Temperature;
import com.electrical_installations.model.enums.TypeRush;
import com.electrical_installations.model.service.ServiceMaterial;
import com.electrical_installations.model.service.ServicePhase;
import com.electrical_installations.model.service.ServiceTemperature;
import com.electrical_installations.view.ViewSubFeederMotors;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

/**
 * Controlador para la vista ViewSubFeederMotors
 * @author JRichard
 * @version 1
 * @since 2015-11-05
 */
public class ControllerSubFeederMotors implements ActionListener, WindowListener {
    
    //Objetos, variables y constantes
    private final ViewSubFeederMotors viewSubFeederMotors;
    private List<Temperature> temperaturesFound;
    private List<Material> materialsFound;
    private List<Phase> phasesFound;
    private static final Messages messages = Messages.getInstance();
    
    
    /**
     * Constructor de la clase que recibe un objeto viewSubFeederMotors.
     * @param viewSubFeederMotors 
     */    
    public ControllerSubFeederMotors(ViewSubFeederMotors viewSubFeederMotors) {
        this.viewSubFeederMotors = viewSubFeederMotors;
    }
    
    
     /**
     * Método para llenar los combos con temperaturas.
     */
    public void fill_combos_temperatures() {
        temperaturesFound = ServiceTemperature.find_temperatures();
        if (temperaturesFound != null) {
            for (Temperature temperature : temperaturesFound) {
                viewSubFeederMotors.getCmbTemperature().addItem(temperature); 
            }
            viewSubFeederMotors.getCmbTemperature().setSelectedIndex(0); 
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.TEMPERATURES_NO_FOUND), MessagesStructure.justify));
            viewSubFeederMotors.dispose();
        }
    }//Fin del método
    
        /**
     * Método para llenar los combos con Materiales.
     */
    public void fill_combos_materials(){        
        materialsFound = ServiceMaterial.find_materials();
        if (materialsFound != null){
            for (Material material : materialsFound){
                viewSubFeederMotors.getCmbMaterial().addItem(material);
            }     
                viewSubFeederMotors.getCmbMaterial().setSelectedIndex(0);
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.MATERIALS_NO_FOUND), MessagesStructure.justify));
            viewSubFeederMotors.dispose();
        }       
    }//Fin del método
    
    /**
     * Método para llenar los combos con Fases.
     */
    public void fill_combos_phases() {
        phasesFound = ServicePhase.find_phases();
        if (phasesFound != null) {
            for (Phase phase : phasesFound) {
                viewSubFeederMotors.getCmbPhases().addItem(phase); 
            }
            viewSubFeederMotors.getCmbPhases().setSelectedIndex(0); 
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.PHASES_NO_FOUND), MessagesStructure.justify));
            viewSubFeederMotors.dispose();
        }
    }//Fin del método
    
    
//    MethodsForCalculationsGlobal1.typeCaliber (TypeRush.UNDERGROUND, null);
    

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource().equals(viewSubFeederMotors.getBtnClose())){
            viewSubFeederMotors.dispose();            
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
    
    
    
}
