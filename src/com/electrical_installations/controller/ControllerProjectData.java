/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.controller;

import com.electrical_installations.configuration.Messages;
import com.electrical_installations.configuration.MessagesStructure;
import com.electrical_installations.global.method.Methods;
import com.electrical_installations.model.entity.Area;
import com.electrical_installations.model.entity.Charge;
import com.electrical_installations.model.entity.ChargesInAreas;
import com.electrical_installations.model.entity.InstallationMotors;
import com.electrical_installations.model.entity.Project;
import com.electrical_installations.model.entity.TypeCharges;
import com.electrical_installations.model.entity.TypeOfInstallation;
import com.electrical_installations.model.entity.masters.Breaker;
import com.electrical_installations.model.entity.masters.Intensity;
import com.electrical_installations.model.enums.TypeHorsePowerSinglePhases;
import com.electrical_installations.model.enums.TypeHorsePowerThreePhases;
import com.electrical_installations.model.enums.TypePhase;
import com.electrical_installations.model.enums.TypeSubTypeCharge;
import com.electrical_installations.model.service.ServiceArea;
import com.electrical_installations.model.service.ServiceChargesInAreas;
import com.electrical_installations.model.service.ServiceInstallationMotors;
import com.electrical_installations.view.ViewAddMotor;
import com.electrical_installations.view.ViewAddMotorToInstallation;
import com.electrical_installations.view.ViewArea;
import com.electrical_installations.view.ViewProyectData;
import com.electrical_installations.view.ViewCharge;
import com.electrical_installations.view.ViewLightingCircuit;
import com.electrical_installations.view.ViewSubFeederMotors;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 * Controlador para la vista ViewProjectData
 * @author Ing. Richard David
 * @version 1
 * @since 2015-07-30
 */
public class ControllerProjectData implements ActionListener, WindowListener, KeyListener, MouseListener{

    //Objetos, variables y constantes
    private final ViewProyectData viewProjectData;
    private static final Messages messages = Messages.getInstance();
    private ViewArea viewArea;
    private ViewCharge viewCharge;
    private ViewLightingCircuit viewLightingCircuit;
    private ViewAddMotorToInstallation viewAddElevatorToInstallation;
    private ViewSubFeederMotors viewSubFeederMotors;
    private ViewAddMotor  viewAddMotor;
    private Area area;
    private List<Charge> charges;
    private List<Area> areas;
    private List<InstallationMotors> installationMotors;
    private List<ChargesInAreas> chargesInAreas;
    private char character;
    private final double potencyTotalRoominess = 0.35;
        
    /**
     * Constructor de la clase, recibe un objeto ViewProjectDatau
     * @param viewProjectData 
     */
    public ControllerProjectData(ViewProyectData viewProjectData){
        this.viewProjectData = viewProjectData;
    }//Fin del constructor 
    
    /**
     * Método para llamar a la vista ViewLightingCircuit
     */
    private void newViewLightingCircuit(){
        viewLightingCircuit = new ViewLightingCircuit(null, true);
        viewLightingCircuit.setVisible(true);
    }//fin del Método 
    
    
    /**
     * Metodo para llamar a la vista ViewAddMotors.
     */
    private void newViewAddMotors(){
        viewAddMotor = new ViewAddMotor(null, true);
        viewAddMotor.setProject(new Project(
                viewProjectData.getProjectCode(), 
                null, 
                new TypeOfInstallation(
                        viewProjectData.getType_installation_code(), 
                        null), 
                null, 
                0, 
                null));
        viewAddMotor.setVisible(true);
        this.fill_installation_motors();
    }
    
   
     
    /**
     * Método para llenar tabla con datos cargas en áreas filtrados por nombres.
     * @param name 
     */
    private void fill_table_charges_in_area(String name){        
        try {
            int row = viewProjectData.getTblArea().getSelectedRow();
            chargesInAreas = ServiceChargesInAreas.filter_by_name(new ChargesInAreas(new Charge(0, name, 0,true,false,false,null), new Area(Integer.valueOf(viewProjectData.getTblArea().getValueAt(row, 0).toString())), 0,0, null, null, null, null));
            if (chargesInAreas != null){            
                Methods.removeRows(viewProjectData.getTblAreasCharges());
                for (ChargesInAreas charges_in_areas_data : chargesInAreas) {
                    Object[] data = {charges_in_areas_data.getArea().getCode(),charges_in_areas_data.getCharge().getCode(),charges_in_areas_data.getCharge().getName(),charges_in_areas_data.getQuantity(),charges_in_areas_data.getPotency(),charges_in_areas_data.getPhase().getCode(),charges_in_areas_data.getCharge().getTypeCharges().getType()};
                    ((DefaultTableModel) viewProjectData.getTblAreasCharges().getModel()).addRow(data);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.AREA_NOT_SELECTED), MessagesStructure.justify));
            viewProjectData.getTxtFindAreasCharge().setText("");
            viewProjectData.getTblArea().requestFocus();       
        }
    }//Fin del método 
    
    /**
     * Método para llenar tabla Áreas.
     */
    public void fill_areas(){
        areas = ServiceArea.find_areas(new Area(
                0, 
                null, 
                0,
                new Project(
                        viewProjectData.getProjectCode(), 
                        null, 
                        new TypeOfInstallation(viewProjectData.getType_installation_code(), 
                                null), 
                        null, 
                        0, 
                        null)));
        if (areas != null){            
            Methods.removeRows(viewProjectData.getTblArea());
            viewProjectData.getLblPowerTotal().setText("0");
            double potency_total = 0;
            for (Area area_data : areas) {
                Object[] data = {area_data.getCode(),area_data.getName(),Methods.round(area_data.getPotency_total(), 5),Methods.round(area_data.getNeutral(), 5),area_data.getQuantity()};
                ((DefaultTableModel) viewProjectData.getTblArea().getModel()).addRow(data);
                potency_total = potency_total + area_data.getPotency_total();
            }
            viewProjectData.getLblPowerTotal().setText(Methods.customFormat("###,###.###",potency_total));
        }
    }//Fin del método

    /**
     * Método para llenar tabla Motores en Instalación.
     */
    public void fill_installation_motors(){
        installationMotors = ServiceInstallationMotors.find_installation_motors(new InstallationMotors(
                0, 
                  new Project(
                        viewProjectData.getProjectCode(), 
                        null, 
                        new TypeOfInstallation(viewProjectData.getType_installation_code(), 
                                null), 
                        null, 
                        0, 
                        null), 
                null, 
                0, 
                0, 
                null, 
                null, 
                null,
                null,
                null,
                0));
         
        if (installationMotors != null){            
            Methods.removeRows(viewProjectData.getTblInstallationEngines());  
            for (InstallationMotors installation_Motors : installationMotors) { 
                Object[] data = {installation_Motors.getCode(),installation_Motors.getDescription(),installation_Motors.getTypePhase().getPhase(),installation_Motors.getHorse_power(),installation_Motors.getIntensity(),installation_Motors.getBreaker(),installation_Motors.getQuantity()};
                ((DefaultTableModel) viewProjectData.getTblInstallationEngines().getModel()).addRow(data);
            }           
        }
    }//Fin del método
    
    /**
     * Método para llenar tabla con datos de áreas filtrados por nombres.
     *
     * @param name
     */
    private void fill_installation_motors_filter_name() {
        installationMotors = ServiceInstallationMotors.find_installation_motors_filter_name(new InstallationMotors(
                0, 
                  new Project(
                        viewProjectData.getProjectCode(), 
                        null, 
                        new TypeOfInstallation(viewProjectData.getType_installation_code(), 
                                null), 
                        null, 
                        0, 
                        null), 
                viewProjectData.getTxtFindInstallationEngines().getText(), 
                0, 
                0, 
                null, 
                null, 
                null,
                null,
                null,
                0));
        
        if (installationMotors != null) {
            Methods.removeRows(viewProjectData.getTblInstallationEngines());
            for (InstallationMotors installation_Motors : installationMotors) {
                 Object[] data = {installation_Motors.getCode(),installation_Motors.getDescription(),installation_Motors.getTypePhase().getPhase(),installation_Motors.getHorse_power(),installation_Motors.getIntensity(),installation_Motors.getBreaker(),installation_Motors.getQuantity()};
                ((DefaultTableModel) viewProjectData.getTblInstallationEngines().getModel()).addRow(data);
            }
        }
    }//Fin del método
        
    /**
     * Método para llenar tabla con datos de áreas filtrados por nombres.
     * @param name 
     */
    private void fill_table_names_of_areas(String name){        
        areas = ServiceArea.filter_by_name(new Area(0,name,0,new Project(viewProjectData.getProjectCode(), null,new TypeOfInstallation(viewProjectData.getType_installation_code(), null), null, 0, null)));
        if (areas != null){            
            Methods.removeRows(viewProjectData.getTblArea());
            for (Area area_data : areas) {
                Object[] data = {area_data.getCode(),area_data.getName(),Methods.round(area_data.getPotency_total(), 5),Methods.round(area_data.getNeutral(), 5),area_data.getQuantity()};
                ((DefaultTableModel) viewProjectData.getTblArea().getModel()).addRow(data);
            }
        }
    }//Fin del método
    
    /**
     * Método para llenar tabla cargas en áreas.
     */
    private void fill_table_charges_in_areas(){   
        try {
            int row = viewProjectData.getTblArea().getSelectedRow();
            chargesInAreas = ServiceChargesInAreas.find_charges_in_areas(new ChargesInAreas(null, new Area(Integer.valueOf(viewProjectData.getTblArea().getValueAt(row, 0).toString())), 0,0, null, null, null, null));
            if (chargesInAreas != null){            
                Methods.removeRows(viewProjectData.getTblAreasCharges());
                for (ChargesInAreas charges_in_areas_data : chargesInAreas) {
                    Object[] data = {charges_in_areas_data.getArea().getCode(),charges_in_areas_data.getCharge().getCode(),charges_in_areas_data.getCharge().getName(),charges_in_areas_data.getQuantity(),charges_in_areas_data.getPotency(),charges_in_areas_data.getPhase().getCode(),charges_in_areas_data.getCharge().getTypeCharges().getCode(),charges_in_areas_data.getCharge().getTypeCharges().getType()};
                    ((DefaultTableModel) viewProjectData.getTblAreasCharges().getModel()).addRow(data);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.NOT_SELECT_ROW), MessagesStructure.justify));
            viewProjectData.getTblArea().requestFocus();       
        }
    }//Fin del método
    
    /**
     * Método para desplegar la Vista Cargas y agregar una nueva carga a una Área seleccionada.
     */
    private void add_charge(){    
        try {
            int row = viewProjectData.getTblArea().getSelectedRow();
            viewCharge = new ViewCharge(null, true);
            area = new Area(
                    Integer.valueOf(viewProjectData.getTblArea().getValueAt(row, 0).toString()), 
                    viewProjectData.getTblArea().getValueAt(row, 1).toString(), 
                    new Project(viewProjectData.getProjectCode(), null, new TypeOfInstallation(viewProjectData.getType_installation_code(), null), null, 0, null), 
                    Double.valueOf(viewProjectData.getTblArea().getValueAt(row, 2).toString()), 
                    Double.valueOf(viewProjectData.getTblArea().getValueAt(row, 3).toString()), 
                    Integer.valueOf(viewProjectData.getTblArea().getValueAt(row, 4).toString()));
            viewCharge.setArea(area);
            viewCharge.setTitle("Área: " + area.getName() + ". " + viewCharge.getTitle());
            viewCharge.setVisible(true);  
            this.fill_areas();
            viewProjectData.getTblArea().setRowSelectionInterval(row, row); 
            this.fill_table_charges_in_areas();
        } catch (ArrayIndexOutOfBoundsException e) {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.NOT_SELECT_ROW), MessagesStructure.justify));
            viewProjectData.getTblArea().requestFocus();
        }  
    }//Fin del Método.
    
    /**
     * Método para desplegar la vista ViewArea y registrar una nueva área.
     */
    private void area_register(){
        viewArea = new ViewArea(null, true,"Agregar Área");
        viewArea.setArea(new Area(0, null, new Project(viewProjectData.getProjectCode(), null, new TypeOfInstallation(viewProjectData.getType_installation_code(), null), null, 0, null), 0, 0, 0));
        viewArea.visible_buttons(false, true, true);
        viewArea.setVisible(true);
        Methods.removeRows(viewProjectData.getTblAreasCharges());
        this.fill_areas();      
    }//Fin del método
    
    /**
     * Método para desplegar la vista ViewArea y modificar una nueva área.
     */
    private void modify_area(){
        try {
            int row = viewProjectData.getTblArea().getSelectedRow();
            area = new Area(
                    Integer.valueOf(viewProjectData.getTblArea().getValueAt(row, 0).toString()), 
                    viewProjectData.getTblArea().getValueAt(row, 1).toString(), 
                    new Project(viewProjectData.getProjectCode(), null, new TypeOfInstallation(viewProjectData.getType_installation_code(), null), null, 0, null), 
                    Double.valueOf(viewProjectData.getTblArea().getValueAt(row, 2).toString()), 
                    Double.valueOf(viewProjectData.getTblArea().getValueAt(row, 3).toString()), 
                    Integer.valueOf(viewProjectData.getTblArea().getValueAt(row, 4).toString()));
            viewArea = new ViewArea(null, true,"Modificar Área");
            viewArea.setArea(area);
            viewArea.setAreaIluminariaPowerPoints(ServiceArea.find_iluminaria_powerPoint(area));
            viewArea.fill_fields(area,viewArea.getAreaIluminariaPowerPoints());
            viewArea.fill_fields_combos(viewArea.getAreaIluminariaPowerPoints());
            viewArea.visible_buttons(true, false, true);
            viewArea.getLblQuantity().setVisible(false);
            viewArea.getJspQuantity().setVisible(false);
            viewArea.setVisible(true);
            if (viewArea.getModify()){                
                Methods.removeRows(viewProjectData.getTblAreasCharges());
                this.fill_areas();
                viewProjectData.getTblArea().setRowSelectionInterval(row, row);
                this.fill_table_charges_in_areas(); 
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.NOT_SELECT_ROW), MessagesStructure.justify));
            viewProjectData.getTblArea().requestFocus();
        }    
    }//Fin del método
    
    /**
     * Método para eliminar un Área.
     */
    private void remove_area(){        
        try {
            int row = viewProjectData.getTblArea().getSelectedRow();
            if (ServiceArea.delete_area(new Area(
                    Integer.parseInt(viewProjectData.getTblArea().getValueAt(row, 0).toString()), 
                    null, 
                    new Project(
                            viewProjectData.getProjectCode(), 
                            new TypeOfInstallation(
                                    viewProjectData.getType_installation_code(), 
                                    null), 
                            0), 
                    0, 
                    0, 
                    0))){                
                Methods.removeRows(viewProjectData.getTblAreasCharges());
                this.fill_areas();
            }            
        } catch (ArrayIndexOutOfBoundsException e) {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.NOT_SELECT_ROW), MessagesStructure.justify));
            viewProjectData.getTblArea().requestFocus();
        }
    }//Fin del método
    
    /**
     * Método para eliminar Cargas de un Áreas.
     */
    private void remove_charges_in_areas() { 
        int row_area = viewProjectData.getTblArea().getSelectedRow();
        double potency_total = Double.valueOf(viewProjectData.getTblArea().getValueAt(row_area, 2).toString());
        double neutral = Double.valueOf(viewProjectData.getTblArea().getValueAt(row_area, 3).toString());
        int[] rows = viewProjectData.getTblAreasCharges().getSelectedRows(); 
        try {       
            for (int i = 0; i < rows.length; i++) {    
                if (Integer.valueOf(viewProjectData.getTblAreasCharges().getValueAt(rows[i], 5).toString()) == 1){
                   potency_total = potency_total - ((Integer.valueOf(viewProjectData.getTblAreasCharges().getValueAt(rows[i], 3).toString())) * (Double.valueOf(viewProjectData.getTblAreasCharges().getValueAt(rows[i], 4).toString())));
                   neutral = neutral - ((Integer.valueOf(viewProjectData.getTblAreasCharges().getValueAt(rows[i], 3).toString())) * (Double.valueOf(viewProjectData.getTblAreasCharges().getValueAt(rows[i], 4).toString())));
                } else {
                   potency_total = potency_total - ((Integer.valueOf(viewProjectData.getTblAreasCharges().getValueAt(rows[i], 3).toString())) * (Double.valueOf(viewProjectData.getTblAreasCharges().getValueAt(rows[i], 4).toString())));
                   neutral = (Integer.valueOf(viewProjectData.getTblAreasCharges().getValueAt(rows[i], 6).toString()) == 3) || 
                           (Integer.valueOf(viewProjectData.getTblAreasCharges().getValueAt(rows[i], 6).toString()) == 11) ? 
                           neutral - ((Integer.valueOf(viewProjectData.getTblAreasCharges().getValueAt(rows[i], 3).toString())) * (Double.valueOf(viewProjectData.getTblAreasCharges().getValueAt(rows[i], 4).toString()) * 0.7)) :
                           neutral - 0;                    
                }          
                Area area_to_modify = new Area(
                            Integer.valueOf(viewProjectData.getTblArea().getValueAt(row_area, 0).toString()), 
                            viewProjectData.getTblArea().getValueAt(row_area, 1).toString(), 
                            new Project(viewProjectData.getProjectCode(), null, new TypeOfInstallation(viewProjectData.getType_installation_code(), null), null, 0, null), 
                            potency_total, 
                            neutral, 
                            Integer.valueOf(viewProjectData.getTblArea().getValueAt(row_area, 4).toString()));
                double quantityPotencyIntensity = 0;
                if (viewProjectData.getTblAreasCharges().getValueAt(rows[i], 7).toString().equalsIgnoreCase(TypeSubTypeCharge.POTENCY.getSubTypeCharge())){
                    quantityPotencyIntensity = Integer.valueOf(viewProjectData.getTblAreasCharges().getValueAt(rows[i], 3).toString()) * 
                                        Double.valueOf(viewProjectData.getTblAreasCharges().getValueAt(rows[i], 4).toString());                         
                } else if (viewProjectData.getTblAreasCharges().getValueAt(rows[i], 7).toString().equalsIgnoreCase(TypeSubTypeCharge.QUANTITY.getSubTypeCharge())) {
                    quantityPotencyIntensity = Double.valueOf(viewProjectData.getTblAreasCharges().getValueAt(rows[i], 3).toString());
                } else {
                    quantityPotencyIntensity = Integer.valueOf(viewProjectData.getTblAreasCharges().getValueAt(rows[i], 3).toString()) * 
                                        Double.valueOf(viewProjectData.getTblAreasCharges().getValueAt(rows[i], 4).toString());
                }
                ServiceChargesInAreas.delete_charge_in_area(new ChargesInAreas(
                        new Charge(Integer.valueOf(viewProjectData.getTblAreasCharges().getValueAt(rows[i], 1).toString()), 
                                null, 
                                0,
                                true,
                                false,
                                false,
                                new TypeCharges(Integer.valueOf(viewProjectData.getTblAreasCharges().getValueAt(rows[i], 6).toString()), null, viewProjectData.getTblAreasCharges().getValueAt(rows[i], 7).toString())), 
                        new Area(Integer.parseInt(viewProjectData.getTblAreasCharges().getValueAt(rows[i], 0).toString())), 
                        quantityPotencyIntensity,
                        0, 
                        null, 
                        null, 
                        null, 
                        null),
                        area_to_modify);                
            }
            Methods.removeRows(viewProjectData.getTblAreasCharges());
            this.fill_areas(); 
            viewProjectData.getTblArea().setRowSelectionInterval(row_area, row_area); 
            this.fill_table_charges_in_areas();           
        } catch (Exception e) {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.NOT_SELECT_ROW), MessagesStructure.justify));
            viewProjectData.getTblAreasCharges().requestFocus();            
        }
    }//Fin del método
    
    /**
     * Método para eliminar un motor de una instalación.
     */
    private void remove_intallation_motor(){
        try {
            int[] rows = viewProjectData.getTblInstallationEngines().getSelectedRows();
            for (int i = 0; i < rows.length; i++) { 
                if (ServiceInstallationMotors.delete_motor(new InstallationMotors(
                    Integer.valueOf(viewProjectData.getTblInstallationEngines().getValueAt(rows[i], 0).toString()), 
                    new Project(
                            viewProjectData.getProjectCode(), 
                            new TypeOfInstallation(
                                    viewProjectData.getType_installation_code(), 
                                    null),
                            0), 
                    Double.valueOf(viewProjectData.getTblInstallationEngines().getValueAt(rows[i], 4).toString()), 
                    Integer.valueOf(viewProjectData.getTblInstallationEngines().getValueAt(rows[i], 6).toString())))){
                }    
            }                            
            Methods.removeRows(viewProjectData.getTblInstallationEngines());            
            this.fill_installation_motors();
        } catch (ArrayIndexOutOfBoundsException e) {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.NOT_SELECT_ROW), MessagesStructure.justify));
            viewProjectData.getTblInstallationEngines().requestFocus();            
        }
    }//Fin del Método
     
    /**
     * Método para calcular el sub-alimentador de motores.
     */
    private void calculate_sub_feeder(){ 
         double total_intensity = 0;
         double intensity_single_phase = 0;
         int priority_single_phase = 0;
         double intensity_three_phase = 0;
         int priority_three_phase = 0;
         double breaker_higher = 0;
         double intensity_breaker_higher = 0;         
          try {       
             int count = viewProjectData.getTblInstallationEngines().getRowCount();                        
              for (int i = 0; i < count; i++) {                  
                  total_intensity += Double.valueOf(viewProjectData.getTblInstallationEngines().getValueAt(i, 4).toString()) * Integer.valueOf(viewProjectData.getTblInstallationEngines().getValueAt(i, 6).toString());
                 
                  if (Double.valueOf(viewProjectData.getTblInstallationEngines().getValueAt(i, 5).toString()) > breaker_higher){
                      breaker_higher = Double.valueOf(viewProjectData.getTblInstallationEngines().getValueAt(i, 5).toString());
                      intensity_breaker_higher = Double.valueOf(viewProjectData.getTblInstallationEngines().getValueAt(i, 4).toString()) * Integer.valueOf(viewProjectData.getTblInstallationEngines().getValueAt(i, 6).toString());
                  } 
                  
                  if(viewProjectData.getTblInstallationEngines().getValueAt(i, 2).toString().equals(TypePhase.SINGLE_PHASE.getPhase())){
                      int value = 0;
                      for (TypeHorsePowerSinglePhases horsePowerSinglePhases : TypeHorsePowerSinglePhases.values()){
                          if (viewProjectData.getTblInstallationEngines().getValueAt(i, 3).equals(horsePowerSinglePhases.getName())){
                             value = horsePowerSinglePhases.getPriority();
                             if (value > priority_single_phase){
                                priority_single_phase = value; 
                                intensity_single_phase = Double.valueOf(viewProjectData.getTblInstallationEngines().getValueAt(i, 4).toString()) * Integer.valueOf(viewProjectData.getTblInstallationEngines().getValueAt(i, 6).toString());
                             }                             
                          }
                      } 
                  }else{
                      int value = 0;
                      for (TypeHorsePowerThreePhases horsePowerThreePhases : TypeHorsePowerThreePhases.values()){
                          if (viewProjectData.getTblInstallationEngines().getValueAt(i, 3).equals(horsePowerThreePhases.getName())){
                             value = horsePowerThreePhases.getPriority();
                             if (value > priority_three_phase){
                                priority_three_phase = value;
                                intensity_three_phase = Double.valueOf(viewProjectData.getTblInstallationEngines().getValueAt(i, 4).toString());
                             }                             
                          }
                      }                       
                  }                  
            }   
               
            if(intensity_three_phase > 0){ 
                viewSubFeederMotors = new ViewSubFeederMotors(null, true);
                viewSubFeederMotors.setBreaker(new Breaker(0, Methods.round((total_intensity - intensity_breaker_higher) + breaker_higher, 5)));
                viewSubFeederMotors.getLblBreakers().setText(String.valueOf(viewSubFeederMotors.getBreaker().getCapacity()));
                double total = (total_intensity - intensity_three_phase)+ (intensity_three_phase * 1.25);
                total = total > 0 ? total:total_intensity;   
                viewSubFeederMotors.getLblIntensity().setText(String.valueOf(Methods.round(total, 5)));                                                       
                viewSubFeederMotors.setIntensity(new Intensity(0, null, total_intensity));    
                viewSubFeederMotors.setProject(new Project(
                        viewProjectData.getProjectCode(),
                        new TypeOfInstallation(viewProjectData.getType_installation_code(), null),
                        null,
                        null,
                        total));
                viewSubFeederMotors.getController().calculate_conductor();
                viewSubFeederMotors.setVisible(true);
              }else{ 
                  viewSubFeederMotors = new ViewSubFeederMotors(null, true); 
                  viewSubFeederMotors.setBreaker(new Breaker(0, Methods.round((total_intensity - intensity_breaker_higher) + breaker_higher, 5)));
                  viewSubFeederMotors.getLblBreakers().setText(String.valueOf(viewSubFeederMotors.getBreaker().getCapacity()));
                  double total = (total_intensity - intensity_single_phase) + (intensity_single_phase * 1.25);
                  total = total > 0 ? total:total_intensity;       
                  viewSubFeederMotors.getLblIntensity().setText(String.valueOf(Methods.round(total, 5)));  
                  viewSubFeederMotors.setIntensity(new Intensity(0, null, total_intensity));   
                  viewSubFeederMotors.setProject(new Project(
                          viewProjectData.getProjectCode(),
                          new TypeOfInstallation(viewProjectData.getType_installation_code(), null),
                          null,
                          null,
                          total));
                  viewSubFeederMotors.getController().calculate_conductor();
                  viewSubFeederMotors.setVisible(true);
              }               
          } catch (Exception e) {
              e.printStackTrace();
          }                
      }//fin del método 
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(viewProjectData.getBtnNew())){
            area_register();
        } else if (e.getSource().equals(viewProjectData.getBtnModify())){       
            modify_area();
        } else if (e.getSource().equals(viewProjectData.getBtnDelete())){
            remove_area();
        } else if (e.getSource().equals(viewProjectData.getBtnClose())){
            viewProjectData.dispose();
        } 
        else if (e.getSource().equals(viewProjectData.getBtnAddCharge())){          
            add_charge();
        }
        else if (e.getSource().equals(viewProjectData.getBtnDeleteChargesInAreas())){
            remove_charges_in_areas();
        } else if (e.getSource().equals(viewProjectData.getBtnDeleteInstallationMotor())){
            remove_intallation_motor();
        } else if(e.getSource().equals(viewProjectData.getBtnAddInstallationEngines())){
            newViewAddMotors();
        } else if(e.getSource().equals(viewProjectData.getBtnSubAlimentador())){
            calculate_sub_feeder(); 
        } else if(e.getSource().equals(viewProjectData.getBtnLightingCircuit())){
            newViewLightingCircuit();
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
////        if (e.getSource().equals(viewProjectData.getTxtFindCharge())) {
////            if (viewProjectData.getTxtFindCharge().getText().length() == 98) {
////                viewProjectData.getToolkit().beep();
////                e.consume();                
////            } 
////        } else
            if (e.getSource().equals(viewProjectData.getTxtFindAreas())) {
            if (viewProjectData.getTxtFindAreas().getText().length() == 48) {
                viewProjectData.getToolkit().beep();
                e.consume();                
            } 
        } else if (e.getSource().equals(viewProjectData.getTxtFindAreasCharge())) {
            if (viewProjectData.getTxtFindAreasCharge().getText().length() == 98) {
                viewProjectData.getToolkit().beep();
                e.consume();                
            } 
        }  else if (e.getSource().equals(viewProjectData.getTxtFindInstallationEngines())) {
            if (viewProjectData.getTxtFindInstallationEngines().getText().length() == 98) {
                viewProjectData.getToolkit().beep();
                e.consume();                
            } 
        }
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource().equals(viewProjectData.getTxtFindAreas())){
            this.fill_table_names_of_areas(viewProjectData.getTxtFindAreas().getText());            
        } else if (e.getSource().equals(viewProjectData.getTxtFindAreasCharge())){
            this.fill_table_charges_in_area(viewProjectData.getTxtFindAreasCharge().getText());
        }  else if (e.getSource().equals(viewProjectData.getTxtFindInstallationEngines())){
            this.fill_installation_motors_filter_name();
        } 
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(viewProjectData.getTblArea())){
            if (e.getClickCount() == 2){
                this.fill_table_charges_in_areas();
            }
        }
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
    
}
