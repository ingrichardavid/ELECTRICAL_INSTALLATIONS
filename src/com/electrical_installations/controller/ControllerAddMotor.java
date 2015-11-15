/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.controller;

import com.electrical_installations.configuration.Messages;
import com.electrical_installations.configuration.MessagesStructure;
import com.electrical_installations.model.entity.InstallationMotors;
import com.electrical_installations.model.entity.Project;
import com.electrical_installations.model.entity.TypeOfInstallation;
import com.electrical_installations.model.entity.masters.PercentageOfThreePhaseMotors;
import com.electrical_installations.model.enums.TypePhase;
import com.electrical_installations.model.enums.TypePhases;
import com.electrical_installations.model.service.ServiceInstallationMotors;
import com.electrical_installations.model.service.ServicePercentageOfThreePhaseMotors;
import com.electrical_installations.view.ViewAddMotor; 
import com.electrical_installations.view.ViewAddMotorToInstallation;
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
 * Controlador para la vista ViewAddMotor
 * @author JRichard
 * @version 1
 * @since 2015-10-27 
 */
public class ControllerAddMotor implements ActionListener, KeyListener, WindowListener, ChangeListener {

    //Objetos, variables y constantes
    private final ViewAddMotor viewAddMotor;
    private static final Messages messages = Messages.getInstance(); 
    private List<PercentageOfThreePhaseMotors> percentageOfThreePhaseMotorses;
    private ViewAddMotorToInstallation viewAddMotorToInstallation; 
    private char character;

    /**
     * Metodo principal del Controlador AddMotor.
     * @param viewAddMotor 
     */
    public ControllerAddMotor(ViewAddMotor viewAddMotor) {
        this.viewAddMotor = viewAddMotor;
    }
        
    /**
     * Metodo para llamar a la vista ViewAddMotorToInstallation.
     */    
    public void newViewAddMotorToInstallation(){
        if((viewAddMotor.getTxtDescription().getText().trim() != null) && (!viewAddMotor.getTxtDescription().getText().trim().isEmpty())){
            if(ServiceInstallationMotors.validate_description(
                    new InstallationMotors(
                            0, new Project(viewAddMotor.getProject().getCode(),
                    new TypeOfInstallation(viewAddMotor.getProject().getTypeOfInstallation().getCode(),
                            null)
                            ,0),
                    viewAddMotor.getTxtDescription().getText(),
                            0, 
                            0, 
                            null,
                            null, 
                            null,
                            null, 
                            null,
                            0,
                            null,
                            null))){            
            }else{
                viewAddMotorToInstallation = new ViewAddMotorToInstallation(null, true);
                viewAddMotor.dispose();
                if (viewAddMotor.getrBtnSinglePhase().isSelected()){        
                    viewAddMotorToInstallation.configuration(TypePhases.SINGLE_PHASE_THREE_THREAD,null);
                    viewAddMotorToInstallation.setTypePhases(TypePhases.SINGLE_PHASE_THREE_THREAD);
                    viewAddMotorToInstallation.setTypePhase(TypePhase.SINGLE_PHASE);
                } else {
                    viewAddMotorToInstallation.configuration(TypePhases.PHASE_FOUR_THREAD,
                            (PercentageOfThreePhaseMotors)viewAddMotor.getCmbTypeThreePhase().getSelectedItem()); 
                    viewAddMotorToInstallation.setTypePhases(TypePhases.PHASE_FOUR_THREAD);     
                    viewAddMotorToInstallation.setTypePhase(TypePhase.THREE_PHASE);
                }
                viewAddMotorToInstallation.setProject(viewAddMotor.getProject());
                viewAddMotorToInstallation.getLblDescription().setText(viewAddMotor.getTxtDescription().getText());
                viewAddMotorToInstallation.setVisible(true);
            }  
        }else{
           MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.MOTORES_DESCRIPTION_NO_FOUND), MessagesStructure.justify));
           viewAddMotor.getTxtDescription().requestFocus();
       }       
    }
      
    /**
     * Con este metodo se obtine los tipos de la clase Porcentaje Trifásica de Motores.
     */
    public void fill_types(){        
        percentageOfThreePhaseMotorses = ServicePercentageOfThreePhaseMotors.find_types();
        if (percentageOfThreePhaseMotorses != null){
            for (PercentageOfThreePhaseMotors percentageOfThreePhaseMotorse : percentageOfThreePhaseMotorses){
                viewAddMotor.getCmbTypeThreePhase().addItem(percentageOfThreePhaseMotorse);
            }     
                viewAddMotor.getCmbTypeThreePhase().setSelectedIndex(0);
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.PERCENTAGE_THREE_PHASE_MOTORS_NO_FOUND), MessagesStructure.justify));
            viewAddMotor.dispose();
        }       
    }//Fin del método
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
  
        if(e.getSource().equals(viewAddMotor.getBtnAdd())){ 
            newViewAddMotorToInstallation();
        } else if(e.getSource().equals(viewAddMotor.getBtnClose())){
            viewAddMotor.dispose();
        }
        
    }

    @Override
    public void keyTyped(KeyEvent e) {        
        character = e.getKeyChar(); 
        if (e.getSource().equals(viewAddMotor.getTxtDescription())) {
            if (viewAddMotor.getTxtDescription().getText().trim().length() == 50) {
                viewAddMotor.getToolkit().beep();
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
        
        if (e.getSource().equals(viewAddMotor.getrBtnSinglePhase())){            
            if (viewAddMotor.getrBtnSinglePhase().isSelected()){                
                viewAddMotor.Phases(false, false,448, 145);
                viewAddMotor.getCmbTypeThreePhase().setSelectedIndex(0);
            }             
        } else if (e.getSource().equals(viewAddMotor.getrBtnThreePhase())){            
            if (viewAddMotor.getrBtnThreePhase().isSelected()){
                viewAddMotor.Phases(true, true,448, 165);            
              
                
            }             
        }
        
    }

}
