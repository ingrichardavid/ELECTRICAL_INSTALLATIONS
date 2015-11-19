/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.controller;

import com.electrical_installations.configuration.Messages;
import com.electrical_installations.configuration.MessagesStructure;
import com.electrical_installations.model.entity.Project;
import com.electrical_installations.model.entity.TypeOfInstallation;
import com.electrical_installations.model.entity.masters.Breaker;
import com.electrical_installations.model.entity.masters.Calibers;
import com.electrical_installations.model.entity.masters.CalibersHearth;
import com.electrical_installations.model.entity.masters.Material;
import com.electrical_installations.model.entity.masters.Phase;
import com.electrical_installations.model.entity.masters.Temperature;
import com.electrical_installations.model.enums.TypeCalibers;
import com.electrical_installations.model.enums.TypeMaterials;
import com.electrical_installations.model.enums.TypeRush;
import com.electrical_installations.model.service.ServiceMaterial;
import com.electrical_installations.model.service.ServicePhase;
import com.electrical_installations.model.service.ServiceProject;
import com.electrical_installations.model.service.ServiceTemperature;
import com.electrical_installations.view.ViewSubFeederMotors;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Controlador para la vista ViewSubFeederMotors
 * @author JRichard
 * @version 1
 * @since 2015-11-05
 */
public class ControllerSubFeederMotors implements ActionListener, WindowListener, ChangeListener, ItemListener {
    
    //Objetos, variables y constantes
    private final ViewSubFeederMotors viewSubFeederMotors;
    private List<Temperature> temperaturesFound;
    private List<Material> materialsFound;
    private List<Phase> phasesFound;
    private Calibers caliberPhaseFound;
    private CalibersHearth calibersHearthFound;
    private Breaker breakerPhaseFound;
    private TypeRush typeCaliber;
    private String caliberPipeline;
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
    
    /**
     * Método para calcular conductor.
     */
    public void calculate_conductor(){
        caliberPhaseFound = MethodsForCalculationsGlobal.calculateCaliberDishwasherAndCrusher(
            viewSubFeederMotors.getIntensity().getIntensity(),
            (Material)viewSubFeederMotors.getCmbMaterial().getSelectedItem(), 
            (Temperature)viewSubFeederMotors.getCmbTemperature().getSelectedItem());  

        calibersHearthFound = MethodsForCalculationsGlobal.calculate_caliberHearth_dishwasherAndCrusher(viewSubFeederMotors.getIntensity().getIntensity());
        
        breakerPhaseFound = MethodsForCalculationsGlobal.find_braker_motors(viewSubFeederMotors.getBreaker().getCapacity());
        
        caliberPipeline = MethodsForCalculationsIluminariaPowerPoint.calculate_pipeline(
                            caliberPhaseFound.getCaliber(), 
                            null, 
                            calibersHearthFound.getCaliber(), 
                            (Phase)viewSubFeederMotors.getCmbPhases().getSelectedItem(), 
                            "EMT");
        
        if (caliberPhaseFound == null){
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.CALIBER_NO_FOUND), MessagesStructure.justify));
        } else {
            if (((Material)viewSubFeederMotors.getCmbMaterial().getSelectedItem()).getName().equals(TypeMaterials.COOPER.getMaterial())){                
                viewSubFeederMotors.getLblCaliberPhase().setText(MethodsForCalculationsGlobal.number_of_calibers((Phase)viewSubFeederMotors.getCmbPhases().getSelectedItem(), TypeCalibers.PHASE) + " #" + caliberPhaseFound.getCaliber().getName() + " Cu " + MethodsForCalculationsGlobal.typeCaliber(typeCaliber,(Temperature)viewSubFeederMotors.getCmbTemperature().getSelectedItem()) + " " + MethodsForCalculationsGlobal.number_of_brakers((Phase)viewSubFeederMotors.getCmbPhases().getSelectedItem(), breakerPhaseFound.getCapacity()));
            } else if (((Material)viewSubFeederMotors.getCmbMaterial().getSelectedItem()).getName().equals(TypeMaterials.ALUMINIUM.getMaterial())) {
                viewSubFeederMotors.getLblCaliberPhase().setText(MethodsForCalculationsGlobal.number_of_calibers((Phase)viewSubFeederMotors.getCmbPhases().getSelectedItem(), TypeCalibers.PHASE) + " #" + caliberPhaseFound.getCaliber().getName() + " Al " + MethodsForCalculationsGlobal.typeCaliber(typeCaliber,(Temperature)viewSubFeederMotors.getCmbTemperature().getSelectedItem()) + " " + MethodsForCalculationsGlobal.number_of_brakers((Phase)viewSubFeederMotors.getCmbPhases().getSelectedItem(), breakerPhaseFound.getCapacity()));
            }
            if (calibersHearthFound == null){
                viewSubFeederMotors.getLblCaliberEarth().setText("No aplica");
            } else {
                viewSubFeederMotors.getLblCaliberEarth().setText("1 Cable " + calibersHearthFound.getCaliber().getName() + " " + MethodsForCalculationsGlobal.typeCaliber(typeCaliber,(Temperature)viewSubFeederMotors.getCmbTemperature().getSelectedItem()));
            }
        }          
    }//Fin del método
    
    /**
     *  
     */
    public void update_project(){
        
        if (ServiceProject.update_project_phase_earth(new Project(
                viewSubFeederMotors.getProject().getCode(),
                new TypeOfInstallation(viewSubFeederMotors.getProject().getTypeOfInstallation().getCode(),null),
                viewSubFeederMotors.getLblCaliberPhase().getText(),
                viewSubFeederMotors.getLblCaliberEarth().getText(),
                viewSubFeederMotors.getIntensity().getIntensity(),
                caliberPipeline))) { 
                viewSubFeederMotors.dispose();
            
        }          
    }//fin del metodo
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource().equals(viewSubFeederMotors.getBtnClose())){
            viewSubFeederMotors.dispose();            
        }else if(e.getSource().equals(viewSubFeederMotors.getBtnAdd())){
            update_project();
        }else if(e.getSource().equals(viewSubFeederMotors.getrBtnAir())){
            calculate_conductor(); 
        } else if(e.getSource().equals(viewSubFeederMotors.getrBtnGround())){ 
            calculate_conductor();  
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
    public void stateChanged(ChangeEvent e) {               
        if (e.getSource().equals(viewSubFeederMotors.getrBtnAir())){ 
            if (viewSubFeederMotors.getrBtnAir().isSelected()){  
                typeCaliber = TypeRush.AIR;  
            }             
        } else if (e.getSource().equals(viewSubFeederMotors.getrBtnGround())){  
            if (viewSubFeederMotors.getrBtnGround().isSelected()){ 
                typeCaliber = TypeRush.UNDERGROUND;    
            }             
        } 
    }    

    @Override
    public void itemStateChanged(ItemEvent e) {
       if(e.getSource().equals(viewSubFeederMotors.getCmbMaterial())||e.getSource().equals(viewSubFeederMotors.getCmbPhases())||e.getSource().equals(viewSubFeederMotors.getCmbTemperature())){ 
                calculate_conductor();
              
           
        }
    }
}
