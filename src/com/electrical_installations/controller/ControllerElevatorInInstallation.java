/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.controller;
import com.electrical_installations.view.ViewAddElevatorToInstallation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlador para la vista ViewAddElevatorToInstallation
 *
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-22
 */
public class ControllerElevatorInInstallation implements ActionListener {

    //Objectos, variables y constantes
    private final ViewAddElevatorToInstallation viewAddElevatorToInstallation;
    private String material;
    
    /**
     * Contructor de la clase, recibe un objeto ViewAddElevatorToInstallation
     *
     * @param viewAddElevatorToInstallation
     */
    public ControllerElevatorInInstallation(ViewAddElevatorToInstallation viewAddElevatorToInstallation) {
        this.viewAddElevatorToInstallation = viewAddElevatorToInstallation;
    }//Fin del constructor 

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(viewAddElevatorToInstallation.getBtnAdd())) {
            viewAddElevatorToInstallation.setVoltage(Integer.valueOf(viewAddElevatorToInstallation.getCmbVoltage().getSelectedItem().toString().substring(0, 3)));
            viewAddElevatorToInstallation.setTemperature(viewAddElevatorToInstallation.getCmbTemperature().getSelectedItem().toString());
            viewAddElevatorToInstallation.setPhase(viewAddElevatorToInstallation.getCmbPhases().getSelectedItem().toString());
            if (viewAddElevatorToInstallation.getrBtnCu().isSelected()){
                viewAddElevatorToInstallation.setMaterial("Cobre");
            } else {
                viewAddElevatorToInstallation.setMaterial("Aluminio");
            } 
            viewAddElevatorToInstallation.dispose();
        } else if (e.getSource().equals(viewAddElevatorToInstallation.getBtnClose())) {
            viewAddElevatorToInstallation.dispose();
        }
    }

}
