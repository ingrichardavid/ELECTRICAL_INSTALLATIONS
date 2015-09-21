/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.controller;

 
import com.electrical_installations.configuration.Messages;
import com.electrical_installations.configuration.MessagesStructure;
import com.electrical_installations.global.method.Methods;
import com.electrical_installations.model.entity.Charge;
import com.electrical_installations.model.entity.masters.Caliber;
import com.electrical_installations.model.entity.masters.Duct;
import com.electrical_installations.model.entity.masters.HorsePower;
import com.electrical_installations.model.entity.masters.Material;
import com.electrical_installations.model.entity.masters.PercentageSinglePhaseMotors;
import com.electrical_installations.model.entity.masters.Phase;
import com.electrical_installations.model.entity.masters.Temperature;
import com.electrical_installations.model.entity.masters.Voltage;
import com.electrical_installations.model.service.ServiceCaliber;
import com.electrical_installations.model.service.ServiceCharge;
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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 * Controlador para la vista ViewVoltageInCarga
 *
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-22
 */
public class ControllerCharge implements ActionListener, WindowListener , KeyListener {

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
    private List<PercentageSinglePhaseMotors> percentageSinglePhaseMotorsFound;
    private char character;
    private static final Messages messages = Messages.getInstance();

    /**
     * Contructor de la clase, recibe un objeto ViewArea
     *
     * @param viewVoltageInCharge
     */
    public ControllerCharge(ViewCharge viewVoltageInCharge) {
        this.viewVoltageInCharge = viewVoltageInCharge;
    }//Fin del constructor 

    /**
     * Método para llenar tabla Cargas.
     */
    public void fill_table_charges() {
        charges = ServiceCharge.find_charges();
        if (charges != null) {
            Methods.removeRows(viewVoltageInCharge.getTblCharges());
            for (Charge charge_data : charges) {
                Object[] data = {charge_data.getCode(), charge_data.getName(), charge_data.getPotency()};
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
            for (Voltage voltage : voltagesFound) {
                viewVoltageInCharge.getCmbVoltage().addItem(voltage); 
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
            }     
                viewVoltageInCharge.getCmbCaliber().setSelectedIndex(0);
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
                Object[] data = {charge_data.getCode(),charge_data.getName(),charge_data.getPotency()};
                ((DefaultTableModel) viewVoltageInCharge.getTblCharges().getModel()).addRow(data);
            }
        }
    }//Fin del método 

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(viewVoltageInCharge.getBtnAdd())) {
            viewVoltageInCharge.setVoltage(Integer.valueOf(viewVoltageInCharge.getCmbVoltage().getSelectedItem().toString().substring(0, 3)));
            viewVoltageInCharge.setTemperature(viewVoltageInCharge.getCmbTemperature().getSelectedItem().toString());
            viewVoltageInCharge.setPhase(viewVoltageInCharge.getCmbPhases().getSelectedItem().toString());            
            viewVoltageInCharge.dispose();
        } else if (e.getSource().equals(viewVoltageInCharge.getBtnClose())) {
            viewVoltageInCharge.dispose();
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

}
