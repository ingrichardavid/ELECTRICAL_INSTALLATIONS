/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.controller;
 
import com.electrical_installations.configuration.Messages;
import com.electrical_installations.configuration.MessagesStructure; 
import com.electrical_installations.model.entity.masters.Phase;
import com.electrical_installations.model.entity.masters.Voltage;
import com.electrical_installations.model.enums.TypePhases;
import com.electrical_installations.model.service.ServiceVoltage; 
import com.electrical_installations.view.ViewCalculateIntensityMotors;
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
public class ControllerCalculateIntensityMotors implements ActionListener, WindowListener , KeyListener, MouseListener, ChangeListener {

    //Objectos, variables y constantes
    private final ViewCalculateIntensityMotors viewCalculateIntensityMotors; 
    private List<Voltage> voltagesFound;
 
 
    private static final Messages messages = Messages.getInstance();

    /**
     * Contructor de la clase, recibe un objeto ViewMainFeeder  
     * @param viewCalculateIntensityMotors
     */
    public ControllerCalculateIntensityMotors(ViewCalculateIntensityMotors viewCalculateIntensityMotors) {
        this.viewCalculateIntensityMotors = viewCalculateIntensityMotors; 
    }//Fin del constructor 
 
    /**
     * Método para llenar los combos con Voltages.
     */
    public void fill_combos_voltages() {
        voltagesFound = ServiceVoltage.find_voltages();
        if (voltagesFound != null) {
            viewCalculateIntensityMotors.getCmbVoltage().removeAllItems();
            for (Voltage voltage : voltagesFound) { 
                   if(voltage.getVoltage() >= 200){
                       viewCalculateIntensityMotors.getCmbVoltage().addItem(voltage);  
                   } 
            }
            viewCalculateIntensityMotors.getCmbVoltage().setSelectedIndex(0); 
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.VOLTAGES_NO_FOUND), MessagesStructure.justify));
            viewCalculateIntensityMotors.dispose();
        }
    }//Fin del método
    
     
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(viewCalculateIntensityMotors.getBtnAdd())) {  
            
        } else if (e.getSource().equals(viewCalculateIntensityMotors.getBtnClose())) {
            viewCalculateIntensityMotors.dispose();
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
             
    }

}
