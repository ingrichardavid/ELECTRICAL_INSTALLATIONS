/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.view;

import com.electrical_installations.controller.ControllerArea;
import com.electrical_installations.controller.MethodsForCalculationsIluminariaPowerPoint;
import com.electrical_installations.model.entity.Area;
import com.electrical_installations.model.entity.AreaIluminariaPowerPoint;
import com.electrical_installations.model.entity.masters.Voltage;
import com.electrical_installations.model.enums.TypeRush;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 * Clave ViewArea.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-01
 */
public final class ViewArea extends javax.swing.JDialog {

    //Objetos, variables, constantes
    private ControllerArea controllerArea;
    private List<AreaIluminariaPowerPoint> areaIluminariaPowerPoints;
    private AreaIluminariaPowerPoint areaIluminariaPowerPointsIluminaria, areaIluminariaPowerPointsPowerPoint, areaSubFeeder, areaSubFeederNeutral;
    private Area area;
    private double potencyOld;
    private boolean modify;
    
    /**
     * Constructor de ViewArea, por ser subclase de JDialog recibe como parámetro el padre y true en caso de que sea modal.
     * @param parent
     * @param modal 
     * @param title
     */    
    public ViewArea(java.awt.Frame parent, boolean modal,String title) {
        super(parent, modal);
        initComponents();

        this.setTitle(title);
        
        this.modify = false;
                
        this.btnGroupRushIluminaria.add(rBtnAirIluminaria);
        this.btnGroupRushIluminaria.add(rBtnGroundIluminaria);
        this.btnGroupRushPowerPoint.add(rBtnAirPowerPoint);
        this.btnGroupRushPowerPoint.add(rBtnGroundPowerPoint);
        this.btnGroupRushSubFeeder.add(rBtnAirSubFeeder);
        this.btnGroupRushSubFeeder.add(rBtnGroundSubFeeder);
        
        controllerArea = new ControllerArea(this);       
        controllerArea.fill_combos_calibers();
        controllerArea.fill_combos_phases();
        controllerArea.fill_combos_voltages_iluminaria();
        controllerArea.fill_combos_voltages_powerpoint();
        controllerArea.fill_combos_voltages_subfeeder();
        controllerArea.fill_combos_ducts();
        controllerArea.fill_combos_materials();
        controllerArea.fill_combos_temperatures(); 
        
        btnClose.addActionListener(controllerArea);
        btnModify.addActionListener(controllerArea);
        btnRegister.addActionListener(controllerArea);
        btnCalculateCurrentCapacityIluminaria.addActionListener(controllerArea);
        btnCalculateBreakdownIluminaria.addActionListener(controllerArea);
        rBtnAirIluminaria.addChangeListener(controllerArea);
        rBtnGroundIluminaria.addChangeListener(controllerArea);
        btnCalculateCurrentCapacityPowerPoint.addActionListener(controllerArea);
        btnCalculateBreakdownPowerPoint.addActionListener(controllerArea);
        btnCalculateCurrentCapacitySubFeeder.addActionListener(controllerArea);
        btnCalculateBreakdownSubFeeder.addActionListener(controllerArea);
        rBtnAirPowerPoint.addChangeListener(controllerArea);
        rBtnGroundPowerPoint.addChangeListener(controllerArea);
        rBtnAirSubFeeder.addChangeListener(controllerArea);
        rBtnGroundSubFeeder.addChangeListener(controllerArea);
        cmbPhasesIluminaria.addActionListener(controllerArea);
        cmbPhasesPowerPoint.addActionListener(controllerArea);
        cmbPhasesSubFeeder.addActionListener(controllerArea); 
        txtName.addKeyListener(controllerArea);
        this.addWindowListener(controllerArea);     
        
        this.txtName.requestFocus();
        this.setLocationRelativeTo(null);
        
    }
    
    /**
     * Método para colocar visibles o invisibles los botones
     * @param register
     * @param modify
     * @param close 
     */
    public void visible_buttons(boolean modify, boolean register, boolean close){
        this.btnRegister.setVisible(register);
        this.btnModify.setVisible(modify);
        this.btnClose.setVisible(close);
    }//Fin del método
    
    /**
     * Método para habilitar y deshabilitar los botones
     * @param register
     * @param modify
     * @param close 
     */
    public void enable_buttons(boolean modify, boolean register, boolean close){
        this.btnRegister.setEnabled(register);
        this.btnModify.setEnabled(modify);
        this.btnClose.setEnabled(close);
    }//Fin del método
    
    /**
     * Métodos para llenar campos de la vista
     * @param area 
     * @param areaIluminariaPowerPoints 
    */
    public void fill_fields(Area area, List<AreaIluminariaPowerPoint> areaIluminariaPowerPoints){
        this.txtName.setText(area.getName());
        for (AreaIluminariaPowerPoint areaIluminariaPowerPoint : areaIluminariaPowerPoints) {
            switch(areaIluminariaPowerPoint.getTypeOfBranchCircuitInArea()){
                case ILUMINARIA:
                    setAreaIluminariaPowerPointsIluminaria(areaIluminariaPowerPoint);
                    this.jspArea.setValue((float)areaIluminariaPowerPoint.getAreaOrQuantity());                  
                    this.cmbPhasesIluminaria.setSelectedItem(areaIluminariaPowerPoint.getPhase());
                    this.cmbVoltageIluminaria.setSelectedItem((Voltage)areaIluminariaPowerPoint.getVoltage());
                    this.cmbMaterialIluminaria.setSelectedItem(areaIluminariaPowerPoint.getCalibers().getMaterial());
                    this.cmbTemperatureIlimunaria.setSelectedItem(areaIluminariaPowerPoint.getCalibers().getTemperature());
                    this.jspPowerFactor.setValue((float)areaIluminariaPowerPoint.getPowerFactor());
                    if (areaIluminariaPowerPoint.getTypeRush().equals(TypeRush.UNDERGROUND)){
                        this.rBtnGroundIluminaria.setSelected(true);
                    } else if (areaIluminariaPowerPoint.getTypeRush().equals(TypeRush.AIR)) {
                        this.rBtnAirIluminaria.setSelected(true);
                    }
                    this.jspLengthIluminaria.setValue((float)areaIluminariaPowerPoint.getLength());
                    this.cmbDuctIluminaria.setSelectedItem(areaIluminariaPowerPoint.getDuct());
                    this.jspAngle.setValue((float)areaIluminariaPowerPoint.getAngle());
                    this.cmbCalibersIluminaria.setSelectedItem(areaIluminariaPowerPoint.getCaliber());
                    this.cmbPipelineIuminaria.setSelectedItem(areaIluminariaPowerPoint.getMaterialPipeline());
                    break;
                case POWER_POINT:
                    setAreaIluminariaPowerPointsPowerPoint(areaIluminariaPowerPoint);
                    this.txtQuantityPowerPoint.setValue((int)areaIluminariaPowerPoint.getAreaOrQuantity());
                    this.cmbPhasesPowerPoint.setSelectedItem(areaIluminariaPowerPoint.getPhase());                    
                    this.cmbVoltagePowerPoint.setSelectedItem(areaIluminariaPowerPoint.getVoltage());
                    this.cmbMaterialPowerPoint.setSelectedItem(areaIluminariaPowerPoint.getCalibers().getMaterial());
                    this.cmbTemperaturePowerPoint.setSelectedItem(areaIluminariaPowerPoint.getCalibers().getTemperature());
                    this.jspPowerFactorPowerPoint.setValue((float)areaIluminariaPowerPoint.getPowerFactor());
                    if (areaIluminariaPowerPoint.getTypeRush().equals(TypeRush.UNDERGROUND)){
                        this.rBtnGroundPowerPoint.setSelected(true);
                    } else if (areaIluminariaPowerPoint.getTypeRush().equals(TypeRush.AIR)) {
                        this.rBtnAirPowerPoint.setSelected(true);
                    }
                    this.jspLengthPowerPoint.setValue((float)areaIluminariaPowerPoint.getLength());
                    this.cmbDuctPowerPoint.setSelectedItem(areaIluminariaPowerPoint.getDuct());
                    this.jspAnglePowerPoint.setValue((float)areaIluminariaPowerPoint.getAngle());
                    this.cmbCalibersPowerPoint.setSelectedItem(areaIluminariaPowerPoint.getCaliber());
                    this.cmbPipelinePowerPoint.setSelectedItem(areaIluminariaPowerPoint.getMaterialPipeline());
                    break;
                case SUB_FEEDER:
                    setAreaSubFeeder(areaIluminariaPowerPoint);  
                    this.cmbPhasesSubFeeder.setSelectedItem(areaIluminariaPowerPoint.getPhase());
                    this.cmbVoltageSubFeeder.setSelectedItem(areaIluminariaPowerPoint.getVoltage());
                    this.cmbMaterialSubFeeder.setSelectedItem(areaIluminariaPowerPoint.getCalibers().getMaterial());
                    this.cmbTemperatureSubFeeder.setSelectedItem(areaIluminariaPowerPoint.getCalibers().getTemperature());
                    this.jspPowerSubFeeder.setValue((float)areaIluminariaPowerPoint.getPowerFactor());
                    if (areaIluminariaPowerPoint.getTypeRush().equals(TypeRush.UNDERGROUND)){
                        this.rBtnGroundSubFeeder.setSelected(true);
                    } else if (areaIluminariaPowerPoint.getTypeRush().equals(TypeRush.AIR)) {
                        this.rBtnAirSubFeeder.setSelected(true);
                    }
                    this.jspLengthSubFeeder.setValue((float)areaIluminariaPowerPoint.getLength());
                    this.cmbDuctSubFeeder.setSelectedItem(areaIluminariaPowerPoint.getDuct());
                    this.jspAngleSubFeeder.setValue((float)areaIluminariaPowerPoint.getAngle());
                    this.cmbCalibersSubFeeder.setSelectedItem(areaIluminariaPowerPoint.getCaliber());
                    this.cmbPipelineSubFeeder.setSelectedItem(areaIluminariaPowerPoint.getMaterialPipeline());
                    break;
                case NEUTRAL:
                    setAreaSubFeederNeutral(areaIluminariaPowerPoint);   
                    this.cmbCalibersSubFeederNeutral.setSelectedItem(areaIluminariaPowerPoint.getCaliber());
                    break;
                default:
                    break;                    
            }
        }      
    }//Fin del Método
         
    /**
     * Método para llenar combos de Voltages en la Vista
     * @param areaIluminariaPowerPoints 
     */
    public void fill_fields_combos(List<AreaIluminariaPowerPoint> areaIluminariaPowerPoints){
        for (AreaIluminariaPowerPoint areaIluminariaPowerPoint : areaIluminariaPowerPoints) {
            switch(areaIluminariaPowerPoint.getTypeOfBranchCircuitInArea()){
                case ILUMINARIA:
                    this.cmbVoltageIluminaria.setSelectedItem((Voltage)areaIluminariaPowerPoint.getVoltage());
                    break;
                case POWER_POINT:
                    this.cmbVoltagePowerPoint.setSelectedItem((Voltage)areaIluminariaPowerPoint.getVoltage());
                    break;
                case SUB_FEEDER:
                    this.cmbVoltageSubFeeder.setSelectedItem((Voltage)areaIluminariaPowerPoint.getVoltage());
                    break;
                default:
                    break;                    
            }
        }       
        this.setPotencyOld(MethodsForCalculationsIluminariaPowerPoint.potencyInIluminariaAndPowerPoint(
            MethodsForCalculationsIluminariaPowerPoint.potencyInIluminaria(Double.valueOf(this.getJspArea().getValue().toString())),
            MethodsForCalculationsIluminariaPowerPoint.potencyInPowerPoint(Integer.valueOf(this.getTxtQuantityPowerPoint().getValue().toString()))));
        this.lblPotencyTotalSubFeeder.setText(String.valueOf(area.getPotency_total()) + " W");
        this.lblPotencyNeutralSubFeeder.setText(String.valueOf(area.getNeutral()) + " W");        
        this.btnCalculateCurrentCapacityIluminaria.doClick();
        this.btnCalculateCurrentCapacityPowerPoint.doClick();
        this.btnCalculateCurrentCapacitySubFeeder.doClick();
        for (AreaIluminariaPowerPoint areaIluminariaPowerPoint : areaIluminariaPowerPoints) {
            switch(areaIluminariaPowerPoint.getTypeOfBranchCircuitInArea()){
                case ILUMINARIA:
                    this.cmbCalibersIluminaria.setSelectedItem(areaIluminariaPowerPoint.getCaliber());
                    break;
                case POWER_POINT:
                    this.cmbCalibersPowerPoint.setSelectedItem(areaIluminariaPowerPoint.getCaliber());
                    break;
                case SUB_FEEDER:
                    this.cmbCalibersSubFeeder.setSelectedItem(areaIluminariaPowerPoint.getCaliber());
                    break;
                case NEUTRAL:
                    setAreaSubFeederNeutral(areaIluminariaPowerPoint);   
                    this.cmbCalibersSubFeederNeutral.setSelectedItem(areaIluminariaPowerPoint.getCaliber());
                    break;
                default:
                    break;
            }
        }          
        this.btnCalculateBreakdownIluminaria.doClick();
        this.btnCalculateBreakdownPowerPoint.doClick();  
        this.btnCalculateBreakdownSubFeeder.doClick(); 
    }//Fin del método
    
    //Getters y Setters

    public JComboBox getCmbPipelineIuminaria() {
        return cmbPipelineIuminaria;
    }

    public void setCmbPipelineIuminaria(JComboBox cmbPipelineIuminaria) {
        this.cmbPipelineIuminaria = cmbPipelineIuminaria;
    }

    public JComboBox getCmbPipelinePowerPoint() {
        return cmbPipelinePowerPoint;
    }

    public void setCmbPipelinePowerPoint(JComboBox cmbPipelinePowerPoint) {
        this.cmbPipelinePowerPoint = cmbPipelinePowerPoint;
    }

    public JComboBox getCmbPipelineSubFeeder() {
        return cmbPipelineSubFeeder;
    }

    public void setCmbPipelineSubFeeder(JComboBox cmbPipelineSubFeeder) {
        this.cmbPipelineSubFeeder = cmbPipelineSubFeeder;
    }

    public JLabel getLblQuantity() {
        return lblQuantity;
    }

    public void setLblQuantity(JLabel lblQuantity) {
        this.lblQuantity = lblQuantity;
    }

    public JSpinner getJspQuantity() {
        return jspQuantity;
    }

    public void setJspQuantity(JSpinner jspQuantity) {
        this.jspQuantity = jspQuantity;
    }
     
    public JLabel getLblBranchCircuitPowerPoint() {
        return lblBranchCircuitPowerPoint;
    }

    public void setLblBranchCircuitPowerPoint(JLabel lblBranchCircuitPowerPoint) {
        this.lblBranchCircuitPowerPoint = lblBranchCircuitPowerPoint;
    }

    public JLabel getLblBranchCircuitIluminaria() {
        return lblBranchCircuitIluminaria;
    }

    public void setLblBranchCircuitIluminaria(JLabel lblBranchCircuitIluminaria) {
        this.lblBranchCircuitIluminaria = lblBranchCircuitIluminaria;
    }

    public ControllerArea getControllerArea() {
        return controllerArea;
    }

    public void setControllerArea(ControllerArea controllerArea) {
        this.controllerArea = controllerArea;
    }
    
    public double getPotencyOld() {
        return potencyOld;
    }

    public void setPotencyOld(double potencyOld) {
        this.potencyOld = potencyOld;
    }

    public AreaIluminariaPowerPoint getAreaSubFeederNeutral() {
        return areaSubFeederNeutral;
    }

    public void setAreaSubFeederNeutral(AreaIluminariaPowerPoint areaSubFeederNeutral) {
        this.areaSubFeederNeutral = areaSubFeederNeutral;
    }
    
    public AreaIluminariaPowerPoint getAreaSubFeeder() {
        return areaSubFeeder;
    }

    public void setAreaSubFeeder(AreaIluminariaPowerPoint areaSubFeeder) {
        this.areaSubFeeder = areaSubFeeder;
    }

    public JComboBox getCmbCalibersSubFeederNeutral() {
        return cmbCalibersSubFeederNeutral;
    }

    public void setCmbCalibersSubFeederNeutral(JComboBox cmbCalibersSubFeederNeutral) {
        this.cmbCalibersSubFeederNeutral = cmbCalibersSubFeederNeutral;
    }

    public JLabel getLblBreakdownVoltageSubFeederNeutral() {
        return lblBreakdownVoltageSubFeederNeutral;
    }

    public void setLblBreakdownVoltageSubFeederNeutral(JLabel lblBreakdownVoltageSubFeederNeutral) {
        this.lblBreakdownVoltageSubFeederNeutral = lblBreakdownVoltageSubFeederNeutral;
    }
    
    public JLabel getLblCaliberNeutral() {
        return lblCaliberNeutral;
    }

    public void setLblCaliberNeutral(JLabel lblCaliberNeutral) {
        this.lblCaliberNeutral = lblCaliberNeutral;
    }
    
    public JLabel getLblPotencyNeutralSubFeeder() {
        return lblPotencyNeutralSubFeeder;
    }

    public void setLblPotencyNeutralSubFeeder(JLabel lblPotencyNeutralSubFeeder) {
        this.lblPotencyNeutralSubFeeder = lblPotencyNeutralSubFeeder;
    }

    public JButton getBtnCalculateBreakdownSubFeeder() {
        return btnCalculateBreakdownSubFeeder;
    }

    public void setBtnCalculateBreakdownSubFeeder(JButton btnCalculateBreakdownSubFeeder) {
        this.btnCalculateBreakdownSubFeeder = btnCalculateBreakdownSubFeeder;
    }

    public JButton getBtnCalculateCurrentCapacitySubFeeder() {
        return btnCalculateCurrentCapacitySubFeeder;
    }

    public void setBtnCalculateCurrentCapacitySubFeeder(JButton btnCalculateCurrentCapacitySubFeeder) {
        this.btnCalculateCurrentCapacitySubFeeder = btnCalculateCurrentCapacitySubFeeder;
    }

    public ButtonGroup getBtnGroupRushIluminaria() {
        return btnGroupRushIluminaria;
    }

    public void setBtnGroupRushIluminaria(ButtonGroup btnGroupRushIluminaria) {
        this.btnGroupRushIluminaria = btnGroupRushIluminaria;
    }

    public ButtonGroup getBtnGroupRushPowerPoint() {
        return btnGroupRushPowerPoint;
    }

    public void setBtnGroupRushPowerPoint(ButtonGroup btnGroupRushPowerPoint) {
        this.btnGroupRushPowerPoint = btnGroupRushPowerPoint;
    }

    public JComboBox getCmbCalibersSubFeeder() {
        return cmbCalibersSubFeeder;
    }

    public void setCmbCalibersSubFeeder(JComboBox cmbCalibersSubFeeder) {
        this.cmbCalibersSubFeeder = cmbCalibersSubFeeder;
    }

    public JComboBox getCmbDuctSubFeeder() {
        return cmbDuctSubFeeder;
    }

    public void setCmbDuctSubFeeder(JComboBox cmbDuctSubFeeder) {
        this.cmbDuctSubFeeder = cmbDuctSubFeeder;
    }

    public JComboBox getCmbMaterialSubFeeder() {
        return cmbMaterialSubFeeder;
    }

    public void setCmbMaterialSubFeeder(JComboBox cmbMaterialSubFeeder) {
        this.cmbMaterialSubFeeder = cmbMaterialSubFeeder;
    }

    public JComboBox getCmbPhasesSubFeeder() {
        return cmbPhasesSubFeeder;
    }

    public void setCmbPhasesSubFeeder(JComboBox cmbPhasesSubFeeder) {
        this.cmbPhasesSubFeeder = cmbPhasesSubFeeder;
    }

    public JComboBox getCmbTemperatureSubFeeder() {
        return cmbTemperatureSubFeeder;
    }

    public void setCmbTemperatureSubFeeder(JComboBox cmbTemperatureSubFeeder) {
        this.cmbTemperatureSubFeeder = cmbTemperatureSubFeeder;
    }

    public JComboBox getCmbVoltageSubFeeder() {
        return cmbVoltageSubFeeder;
    }

    public void setCmbVoltageSubFeeder(JComboBox cmbVoltageSubFeeder) {
        this.cmbVoltageSubFeeder = cmbVoltageSubFeeder;
    }

    public JSpinner getJspAngleSubFeeder() {
        return jspAngleSubFeeder;
    }

    public void setJspAngleSubFeeder(JSpinner jspAngleSubFeeder) {
        this.jspAngleSubFeeder = jspAngleSubFeeder;
    }

    public JSpinner getJspLengthSubFeeder() {
        return jspLengthSubFeeder;
    }

    public void setJspLengthSubFeeder(JSpinner jspLengthSubFeeder) {
        this.jspLengthSubFeeder = jspLengthSubFeeder;
    }

    public JSpinner getJspPowerSubFeeder() {
        return jspPowerSubFeeder;
    }

    public void setJspPowerSubFeeder(JSpinner jspPowerSubFeeder) {
        this.jspPowerSubFeeder = jspPowerSubFeeder;
    }

    public JLabel getLblBreakdownVoltageSubFeeder() {
        return lblBreakdownVoltageSubFeeder;
    }

    public void setLblBreakdownVoltageSubFeeder(JLabel lblBreakdownVoltageSubFeeder) {
        this.lblBreakdownVoltageSubFeeder = lblBreakdownVoltageSubFeeder;
    }

    public JLabel getLblCaliberSubFeeder() {
        return lblCaliberSubFeeder;
    }

    public void setLblCaliberSubFeeder(JLabel lblCaliberSubFeeder) {
        this.lblCaliberSubFeeder = lblCaliberSubFeeder;
    }

    public JLabel getLblPotencyTotalSubFeeder() {
        return lblPotencyTotalSubFeeder;
    }

    public void setLblPotencyTotalSubFeeder(JLabel lblPotencyTotalSubFeeder) {
        this.lblPotencyTotalSubFeeder = lblPotencyTotalSubFeeder;
    }

    public JRadioButton getrBtnAirSubFeeder() {
        return rBtnAirSubFeeder;
    }

    public void setrBtnAirSubFeeder(JRadioButton rBtnAirSubFeeder) {
        this.rBtnAirSubFeeder = rBtnAirSubFeeder;
    }

    public JRadioButton getrBtnGroundSubFeeder() {
        return rBtnGroundSubFeeder;
    }

    public void setrBtnGroundSubFeeder(JRadioButton rBtnGroundSubFeeder) {
        this.rBtnGroundSubFeeder = rBtnGroundSubFeeder;
    }
    
        
    public AreaIluminariaPowerPoint getAreaIluminariaPowerPointsIluminaria() {
        return areaIluminariaPowerPointsIluminaria;
    }

    public void setAreaIluminariaPowerPointsIluminaria(AreaIluminariaPowerPoint areaIluminariaPowerPointsIluminaria) {
        this.areaIluminariaPowerPointsIluminaria = areaIluminariaPowerPointsIluminaria;
    }

    public AreaIluminariaPowerPoint getAreaIluminariaPowerPointsPowerPoint() {
        return areaIluminariaPowerPointsPowerPoint;
    }

 
    public void setAreaIluminariaPowerPointsPowerPoint(AreaIluminariaPowerPoint areaIluminariaPowerPointsPowerPoint) {
        this.areaIluminariaPowerPointsPowerPoint = areaIluminariaPowerPointsPowerPoint;
    }

    public List<AreaIluminariaPowerPoint> getAreaIluminariaPowerPoints() {
        return areaIluminariaPowerPoints;
    }

    public void setAreaIluminariaPowerPoints(List<AreaIluminariaPowerPoint> areaIluminariaPowerPoints) {
        this.areaIluminariaPowerPoints = areaIluminariaPowerPoints;
    }

    public JSpinner getJspAngle() {
        return jspAngle;
    }

    public void setJspAngle(JSpinner jspAngle) {
        this.jspAngle = jspAngle;
    }

    public JSpinner getJspArea() {
        return jspArea;
    }

    public void setJspArea(JSpinner jspArea) {
        this.jspArea = jspArea;
    }

    public JTabbedPane getJtpPanels() {
        return jtpPanels;
    }

    public void setJtpPanels(JTabbedPane jtpPanels) {
        this.jtpPanels = jtpPanels;
    }
    
    public JSpinner getTxtQuantityPowerPoint() {
        return txtQuantityPowerPoint;
    }

    public void setTxtQuantityPowerPoint(JSpinner txtQuantityPowerPoint) {
        this.txtQuantityPowerPoint = txtQuantityPowerPoint;
    }
    
    public JButton getBtnCalculateBreakdownPowerPoint() {
        return btnCalculateBreakdownPowerPoint;
    }

    public void setBtnCalculateBreakdownPowerPoint(JButton btnCalculateBreakdownPowerPoint) {
        this.btnCalculateBreakdownPowerPoint = btnCalculateBreakdownPowerPoint;
    }

    public JButton getBtnCalculateCurrentCapacityPowerPoint() {
        return btnCalculateCurrentCapacityPowerPoint;
    }

    public void setBtnCalculateCurrentCapacityPowerPoint(JButton btnCalculateCurrentCapacityPowerPoint) {
        this.btnCalculateCurrentCapacityPowerPoint = btnCalculateCurrentCapacityPowerPoint;
    }

    public JSpinner getJspLengthPowerPoint() {
        return jspLengthPowerPoint;
    }

    public void setJspLengthPowerPoint(JSpinner jspLengthPowerPoint) {
        this.jspLengthPowerPoint = jspLengthPowerPoint;
    }

    public JSpinner getJspPowerFactorPowerPoint() {
        return jspPowerFactorPowerPoint;
    }

    public void setJspPowerFactorPowerPoint(JSpinner jspPowerFactorPowerPoint) {
        this.jspPowerFactorPowerPoint = jspPowerFactorPowerPoint;
    }

    public JLabel getLblBreakdownVoltagePowerPoint() {
        return lblBreakdownVoltagePowerPoint;
    }

    public void setLblBreakdownVoltagePowerPoint(JLabel lblBreakdownVoltagePowerPoint) {
        this.lblBreakdownVoltagePowerPoint = lblBreakdownVoltagePowerPoint;
    }

    public JLabel getLblCaliberPowerPoint() {
        return lblCaliberPowerPoint;
    }

    public void setLblCaliberPowerPoint(JLabel lblCaliberPowerPoint) {
        this.lblCaliberPowerPoint = lblCaliberPowerPoint;
    }

    public JRadioButton getrBtnAirPowerPoint() {
        return rBtnAirPowerPoint;
    }

    public void setrBtnAirPowerPoint(JRadioButton rBtnAirPowerPoint) {
        this.rBtnAirPowerPoint = rBtnAirPowerPoint;
    }

    public JRadioButton getrBtnGroundPowerPoint() {
        return rBtnGroundPowerPoint;
    }

    public void setrBtnGroundPowerPoint(JRadioButton rBtnGroundPowerPoint) {
        this.rBtnGroundPowerPoint = rBtnGroundPowerPoint;
    }

    public JSpinner getJspAnglePowerPoint() {
        return jspAnglePowerPoint;
    }

    public void setJspAnglePowerPoint(JSpinner jspAnglePowerPoint) {
        this.jspAnglePowerPoint = jspAnglePowerPoint;
    }

    public JButton getBtnCalculateBreakdownIluminaria() {
        return btnCalculateBreakdownIluminaria;
    }

    public void setBtnCalculateBreakdownIluminaria(JButton btnCalculateBreakdownIluminaria) {
        this.btnCalculateBreakdownIluminaria = btnCalculateBreakdownIluminaria;
    }

    public JSpinner getJspLength() {
        return jspLengthIluminaria;
    }

    public void setJspLength(JSpinner jspLength) {
        this.jspLengthIluminaria = jspLength;
    }
    
    public JButton getBtnCalculateCurrentCapacityIlumiaria() {
        return btnCalculateCurrentCapacityIluminaria;
    }

    public JLabel getLblBreakdownVoltage() {
        return lblBreakdownVoltageIlumiaria;
    }

    public void setLblBreakdownVoltage(JLabel lblBreakdownVoltage) {
        this.lblBreakdownVoltageIlumiaria = lblBreakdownVoltage;
    }

    public void setBtnCalculateCurrentCapacityIluminaria(JButton btnCalculateCurrentCapacityIluminaria) {
        this.btnCalculateCurrentCapacityIluminaria = btnCalculateCurrentCapacityIluminaria;
    }

    public JRadioButton getrBtnAirIluminaria() {
        return rBtnAirIluminaria;
    }

    public void setrBtnAirIluminaria(JRadioButton rBtnAirIluminaria) {
        this.rBtnAirIluminaria = rBtnAirIluminaria;
    }

    public JRadioButton getrBtnGroundIluminaria() {
        return rBtnGroundIluminaria;
    }

    public void setrBtnGroundIluminaria(JRadioButton rBtnGroundIluminaria) {
        this.rBtnGroundIluminaria = rBtnGroundIluminaria;
    }

    public JLabel getLblCaliber() {
        return lblCaliberIluminaria;
    }

    public void setLblCaliber(JLabel lblCaliber) {
        this.lblCaliberIluminaria = lblCaliber;
    }
    
    public JSpinner getJspPowerFactor() {
        return jspPowerFactor;
    }

    public void setJspPowerFactor(JSpinner jspPowerFactor) {
        this.jspPowerFactor = jspPowerFactor;
    }

    public JComboBox getCmbMaterialIluminaria() {
        return cmbMaterialIluminaria;
    }

    public void setCmbMaterialIluminaria(JComboBox cmbMaterialIluminaria) {
        this.cmbMaterialIluminaria = cmbMaterialIluminaria;
    }

    public JComboBox getCmbMaterialPowerPoint() {
        return cmbMaterialPowerPoint;
    }

    public void setCmbMaterialPowerPoint(JComboBox cmbMaterialPowerPoint) {
        this.cmbMaterialPowerPoint = cmbMaterialPowerPoint;
    }

    public JComboBox getCmbPhasesIluminaria() {
        return cmbPhasesIluminaria;
    }

    public void setCmbPhasesIluminaria(JComboBox cmbPhasesIluminaria) {
        this.cmbPhasesIluminaria = cmbPhasesIluminaria;
    }

    public JComboBox getCmbPhasesPowerPoint() {
        return cmbPhasesPowerPoint;
    }

    public void setCmbPhasesPowerPoint(JComboBox cmbPhasesPowerPoint) {
        this.cmbPhasesPowerPoint = cmbPhasesPowerPoint;
    }

    public JComboBox getCmbTemperatureIlimunaria() {
        return cmbTemperatureIlimunaria;
    }

    public void setCmbTemperatureIlimunaria(JComboBox cmbTemperatureIlimunaria) {
        this.cmbTemperatureIlimunaria = cmbTemperatureIlimunaria;
    }

    public JComboBox getCmbTemperaturePowerPoint() {
        return cmbTemperaturePowerPoint;
    }

    public void setCmbTemperaturePowerPoint(JComboBox cmbTemperaturePowerPoint) {
        this.cmbTemperaturePowerPoint = cmbTemperaturePowerPoint;
    }
    
    public boolean getModify(){
        return modify;
    }
    
    public void setModify(boolean modify){
        this.modify = modify;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
    
    public JButton getBtnClose() {
        return btnClose;
    }

    public void setBtnClose(JButton btnClose) {
        this.btnClose = btnClose;
    }

    public JButton getBtnModify() {
        return btnModify;
    }

    public void setBtnModify(JButton btnModify) {
        this.btnModify = btnModify;
    }

    public JButton getBtnRegister() {
        return btnRegister;
    }

    public void setBtnRegister(JButton btnRegister) {
        this.btnRegister = btnRegister;
    }

    public JTextField getTxtName() {
        return txtName;
    }

    public void setTxtName(JTextField txtName) {
        this.txtName = txtName;
    }

    public JComboBox getCmbVoltageIluminaria() {
        return cmbVoltageIluminaria;
    }

    public void setCmbVoltageIluminaria(JComboBox cmbVoltageIluminaria) {
        this.cmbVoltageIluminaria = cmbVoltageIluminaria;
    }

    public JComboBox getCmbVoltagePowerPoint() {
        return cmbVoltagePowerPoint;
    }

    public void setCmbVoltagePowerPoint(JComboBox cmbVoltagePowerPoint) {
        this.cmbVoltagePowerPoint = cmbVoltagePowerPoint;
    }

    public JComboBox getCmbCalibersIluminaria() {
        return cmbCalibersIluminaria;
    }

    public void setCmbCalibersIluminaria(JComboBox cmbCalibersIluminaria) {
        this.cmbCalibersIluminaria = cmbCalibersIluminaria;
    }

    public JComboBox getCmbCalibersPowerPoint() {
        return cmbCalibersPowerPoint;
    }

    public void setCmbCalibersPowerPoint(JComboBox cmbCalibersPowerPoint) {
        this.cmbCalibersPowerPoint = cmbCalibersPowerPoint;
    }

    public JComboBox getCmbDuctIluminaria() {
        return cmbDuctIluminaria;
    }

    public void setCmbDuctIluminaria(JComboBox cmbDuctIluminaria) {
        this.cmbDuctIluminaria = cmbDuctIluminaria;
    }

    public JComboBox getCmbDuctPowerPoint() {
        return cmbDuctPowerPoint;
    }

    public void setCmbDuctPowerPoint(JComboBox cmbDuctPowerPoint) {
        this.cmbDuctPowerPoint = cmbDuctPowerPoint;
    }
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        btnGroupRushIluminaria = new javax.swing.ButtonGroup();
        btnGroupRushPowerPoint = new javax.swing.ButtonGroup();
        btnGroupRushSubFeeder = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jspQuantity = new javax.swing.JSpinner();
        lblQuantity = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnModify = new javax.swing.JButton();
        btnRegister = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        jtpPanels = new javax.swing.JTabbedPane();
        p1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmbVoltageIluminaria = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmbTemperatureIlimunaria = new javax.swing.JComboBox();
        cmbPhasesIluminaria = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        cmbMaterialIluminaria = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        pa = new javax.swing.JPanel();
        rBtnAirIluminaria = new javax.swing.JRadioButton();
        rBtnGroundIluminaria = new javax.swing.JRadioButton();
        jLabel16 = new javax.swing.JLabel();
        jspPowerFactor = new javax.swing.JSpinner();
        jLabel17 = new javax.swing.JLabel();
        lblCaliberIluminaria = new javax.swing.JLabel();
        btnCalculateCurrentCapacityIluminaria = new javax.swing.JButton();
        jspArea = new javax.swing.JSpinner();
        jLabel42 = new javax.swing.JLabel();
        lblBranchCircuitIluminaria = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        cmbPipelineIuminaria = new javax.swing.JComboBox<String>();
        jPanel8 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        cmbCalibersIluminaria = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jspLengthIluminaria = new javax.swing.JSpinner();
        jLabel14 = new javax.swing.JLabel();
        btnCalculateBreakdownIluminaria = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lblBreakdownVoltageIlumiaria = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cmbDuctIluminaria = new javax.swing.JComboBox();
        jspAngle = new javax.swing.JSpinner();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(21, 21), new java.awt.Dimension(21, 21), new java.awt.Dimension(21, 21));
        p2 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        cmbVoltagePowerPoint = new javax.swing.JComboBox();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        cmbTemperaturePowerPoint = new javax.swing.JComboBox();
        cmbPhasesPowerPoint = new javax.swing.JComboBox();
        jLabel23 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        cmbMaterialPowerPoint = new javax.swing.JComboBox();
        jLabel24 = new javax.swing.JLabel();
        pa1 = new javax.swing.JPanel();
        rBtnAirPowerPoint = new javax.swing.JRadioButton();
        rBtnGroundPowerPoint = new javax.swing.JRadioButton();
        jLabel25 = new javax.swing.JLabel();
        jspPowerFactorPowerPoint = new javax.swing.JSpinner();
        jLabel26 = new javax.swing.JLabel();
        lblCaliberPowerPoint = new javax.swing.JLabel();
        btnCalculateCurrentCapacityPowerPoint = new javax.swing.JButton();
        txtQuantityPowerPoint = new javax.swing.JSpinner();
        jLabel44 = new javax.swing.JLabel();
        lblBranchCircuitPowerPoint = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        cmbPipelinePowerPoint = new javax.swing.JComboBox<String>();
        jPanel11 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        cmbCalibersPowerPoint = new javax.swing.JComboBox();
        jLabel29 = new javax.swing.JLabel();
        jspLengthPowerPoint = new javax.swing.JSpinner();
        jLabel30 = new javax.swing.JLabel();
        btnCalculateBreakdownPowerPoint = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        lblBreakdownVoltagePowerPoint = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        cmbDuctPowerPoint = new javax.swing.JComboBox();
        jspAnglePowerPoint = new javax.swing.JSpinner();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(21, 21), new java.awt.Dimension(21, 21), new java.awt.Dimension(21, 21));
        p3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cmbVoltageSubFeeder = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        cmbTemperatureSubFeeder = new javax.swing.JComboBox();
        cmbPhasesSubFeeder = new javax.swing.JComboBox();
        jLabel32 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        cmbMaterialSubFeeder = new javax.swing.JComboBox();
        jLabel33 = new javax.swing.JLabel();
        pa2 = new javax.swing.JPanel();
        rBtnAirSubFeeder = new javax.swing.JRadioButton();
        rBtnGroundSubFeeder = new javax.swing.JRadioButton();
        jLabel34 = new javax.swing.JLabel();
        jspPowerSubFeeder = new javax.swing.JSpinner();
        jLabel35 = new javax.swing.JLabel();
        lblCaliberSubFeeder = new javax.swing.JLabel();
        btnCalculateCurrentCapacitySubFeeder = new javax.swing.JButton();
        lblPotencyTotalSubFeeder = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        lblPotencyNeutralSubFeeder = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        lblCaliberNeutral = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        cmbPipelineSubFeeder = new javax.swing.JComboBox<String>();
        jPanel12 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        cmbCalibersSubFeeder = new javax.swing.JComboBox();
        jLabel37 = new javax.swing.JLabel();
        jspLengthSubFeeder = new javax.swing.JSpinner();
        jLabel38 = new javax.swing.JLabel();
        btnCalculateBreakdownSubFeeder = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        lblBreakdownVoltageSubFeeder = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        cmbDuctSubFeeder = new javax.swing.JComboBox();
        jspAngleSubFeeder = new javax.swing.JSpinner();
        jLabel45 = new javax.swing.JLabel();
        lblBreakdownVoltageSubFeederNeutral = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        cmbCalibersSubFeederNeutral = new javax.swing.JComboBox();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(97, 21), new java.awt.Dimension(97, 21), new java.awt.Dimension(97, 21));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(630, 520));
        setModal(true);
        setPreferredSize(new java.awt.Dimension(630, 520));
        setResizable(false);
        getContentPane().setLayout(new javax.swing.OverlayLayout(getContentPane()));

        jPanel1.setLayout(new java.awt.GridBagLayout());

        java.awt.GridBagLayout jPanel2Layout = new java.awt.GridBagLayout();
        jPanel2Layout.columnWidths = new int[] {0, 5, 0};
        jPanel2Layout.rowHeights = new int[] {0, 5, 0};
        jPanel2.setLayout(jPanel2Layout);

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel1.setText("Nombre:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 1, 0);
        jPanel2.add(jLabel1, gridBagConstraints);

        txtName.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        txtName.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel2.add(txtName, gridBagConstraints);

        jspQuantity.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jspQuantity.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));
        jspQuantity.setMinimumSize(new java.awt.Dimension(100, 21));
        jspQuantity.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel2.add(jspQuantity, gridBagConstraints);

        lblQuantity.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        lblQuantity.setText("Cantidad:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel2.add(lblQuantity, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel1.add(jPanel2, gridBagConstraints);

        java.awt.GridBagLayout jPanel3Layout = new java.awt.GridBagLayout();
        jPanel3Layout.columnWidths = new int[] {0, 5, 0, 5, 0};
        jPanel3Layout.rowHeights = new int[] {0};
        jPanel3.setLayout(jPanel3Layout);

        btnModify.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnModify.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/modificar.png"))); // NOI18N
        btnModify.setMnemonic('M');
        btnModify.setText("Modificar");
        btnModify.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 5, 0);
        jPanel3.add(btnModify, gridBagConstraints);

        btnRegister.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnRegister.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/registrar.png"))); // NOI18N
        btnRegister.setMnemonic('A');
        btnRegister.setText("Agregar");
        btnRegister.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 5, 0);
        jPanel3.add(btnRegister, gridBagConstraints);

        btnClose.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/cerrar.png"))); // NOI18N
        btnClose.setMnemonic('C');
        btnClose.setText("Cerrar");
        btnClose.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 5, 0);
        jPanel3.add(btnClose, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel1.add(jPanel3, gridBagConstraints);

        jtpPanels.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N

        java.awt.GridBagLayout p1Layout = new java.awt.GridBagLayout();
        p1Layout.columnWidths = new int[] {0};
        p1Layout.rowHeights = new int[] {0, 0, 0};
        p1.setLayout(p1Layout);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Capacidad de Corriente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 1, 12))); // NOI18N
        java.awt.GridBagLayout jPanel4Layout = new java.awt.GridBagLayout();
        jPanel4Layout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0};
        jPanel4Layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        jPanel4.setLayout(jPanel4Layout);

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel2.setText("Área:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 16);
        jPanel4.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel3.setText("Voltaje:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel4.add(jLabel3, gridBagConstraints);

        cmbVoltageIluminaria.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbVoltageIluminaria.setMinimumSize(new java.awt.Dimension(100, 21));
        cmbVoltageIluminaria.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel4.add(cmbVoltageIluminaria, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel6.setText("Temperatura:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel4.add(jLabel6, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel7.setText("Material:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 16);
        jPanel4.add(jLabel7, gridBagConstraints);

        cmbTemperatureIlimunaria.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbTemperatureIlimunaria.setMinimumSize(new java.awt.Dimension(100, 21));
        cmbTemperatureIlimunaria.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel4.add(cmbTemperatureIlimunaria, gridBagConstraints);

        cmbPhasesIluminaria.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbPhasesIluminaria.setMinimumSize(new java.awt.Dimension(148, 21));
        cmbPhasesIluminaria.setPreferredSize(new java.awt.Dimension(148, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(cmbPhasesIluminaria, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel8.setText("Fases:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 16);
        jPanel4.add(jLabel8, gridBagConstraints);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        cmbMaterialIluminaria.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbMaterialIluminaria.setMinimumSize(new java.awt.Dimension(148, 21));
        cmbMaterialIluminaria.setPreferredSize(new java.awt.Dimension(148, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel6.add(cmbMaterialIluminaria, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(jPanel6, gridBagConstraints);

        jLabel15.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel15.setText("Acometida:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 16);
        jPanel4.add(jLabel15, gridBagConstraints);

        pa.setLayout(new java.awt.GridBagLayout());

        rBtnAirIluminaria.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        rBtnAirIluminaria.setText("Aérea");
        rBtnAirIluminaria.setMinimumSize(new java.awt.Dimension(63, 21));
        rBtnAirIluminaria.setPreferredSize(new java.awt.Dimension(63, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        pa.add(rBtnAirIluminaria, gridBagConstraints);

        rBtnGroundIluminaria.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        rBtnGroundIluminaria.setText("Subterránea");
        rBtnGroundIluminaria.setMinimumSize(new java.awt.Dimension(105, 21));
        rBtnGroundIluminaria.setPreferredSize(new java.awt.Dimension(105, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        pa.add(rBtnGroundIluminaria, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(pa, gridBagConstraints);

        jLabel16.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel16.setText("F. Potencia:");
        jLabel16.setToolTipText("Factor de Potencia");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel4.add(jLabel16, gridBagConstraints);

        jspPowerFactor.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jspPowerFactor.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.1f), Float.valueOf(0.1f), Float.valueOf(3.0f), Float.valueOf(0.1f)));
        jspPowerFactor.setMinimumSize(new java.awt.Dimension(100, 21));
        jspPowerFactor.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel4.add(jspPowerFactor, gridBagConstraints);

        jLabel17.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel17.setText("Conductor:");
        jLabel17.setMaximumSize(new java.awt.Dimension(80, 21));
        jLabel17.setMinimumSize(new java.awt.Dimension(80, 21));
        jLabel17.setPreferredSize(new java.awt.Dimension(80, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 16);
        jPanel4.add(jLabel17, gridBagConstraints);

        lblCaliberIluminaria.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        lblCaliberIluminaria.setMinimumSize(new java.awt.Dimension(8, 21));
        lblCaliberIluminaria.setPreferredSize(new java.awt.Dimension(8, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanel4.add(lblCaliberIluminaria, gridBagConstraints);

        btnCalculateCurrentCapacityIluminaria.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnCalculateCurrentCapacityIluminaria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/agregar.png"))); // NOI18N
        btnCalculateCurrentCapacityIluminaria.setText("Calcular");
        btnCalculateCurrentCapacityIluminaria.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel4.add(btnCalculateCurrentCapacityIluminaria, gridBagConstraints);

        jspArea.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jspArea.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.1f), Float.valueOf(0.1f), Float.valueOf(9999999.0f), Float.valueOf(0.1f)));
        jspArea.setMinimumSize(new java.awt.Dimension(100, 21));
        jspArea.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(jspArea, gridBagConstraints);

        jLabel42.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel42.setText("C. Ramal:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 16);
        jPanel4.add(jLabel42, gridBagConstraints);

        lblBranchCircuitIluminaria.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel4.add(lblBranchCircuitIluminaria, gridBagConstraints);

        jLabel46.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel46.setText("Tubería:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel4.add(jLabel46, gridBagConstraints);

        cmbPipelineIuminaria.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbPipelineIuminaria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "EMT", "PVC" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel4.add(cmbPipelineIuminaria, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        p1.add(jPanel4, gridBagConstraints);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Caída de Voltaje", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 1, 12))); // NOI18N
        java.awt.GridBagLayout jPanel8Layout = new java.awt.GridBagLayout();
        jPanel8Layout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0};
        jPanel8Layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0};
        jPanel8.setLayout(jPanel8Layout);

        jLabel12.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel12.setText("Conductores:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel8.add(jLabel12, gridBagConstraints);

        cmbCalibersIluminaria.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbCalibersIluminaria.setMinimumSize(new java.awt.Dimension(100, 21));
        cmbCalibersIluminaria.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel8.add(cmbCalibersIluminaria, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel13.setText("Longitud:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel8.add(jLabel13, gridBagConstraints);

        jspLengthIluminaria.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jspLengthIluminaria.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.1f), Float.valueOf(0.1f), Float.valueOf(9999999.0f), Float.valueOf(0.1f)));
        jspLengthIluminaria.setMinimumSize(new java.awt.Dimension(100, 21));
        jspLengthIluminaria.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel8.add(jspLengthIluminaria, gridBagConstraints);

        jLabel14.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel14.setText("Ángulo:");
        jLabel14.setMaximumSize(new java.awt.Dimension(84, 15));
        jLabel14.setMinimumSize(new java.awt.Dimension(84, 15));
        jLabel14.setPreferredSize(new java.awt.Dimension(84, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 13);
        jPanel8.add(jLabel14, gridBagConstraints);

        btnCalculateBreakdownIluminaria.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnCalculateBreakdownIluminaria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/agregar.png"))); // NOI18N
        btnCalculateBreakdownIluminaria.setText("Calcular");
        btnCalculateBreakdownIluminaria.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel8.add(btnCalculateBreakdownIluminaria, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel4.setText("%:");
        jLabel4.setMinimumSize(new java.awt.Dimension(17, 21));
        jLabel4.setPreferredSize(new java.awt.Dimension(17, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel8.add(jLabel4, gridBagConstraints);

        lblBreakdownVoltageIlumiaria.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        lblBreakdownVoltageIlumiaria.setText("0.0 %");
        lblBreakdownVoltageIlumiaria.setMinimumSize(new java.awt.Dimension(35, 21));
        lblBreakdownVoltageIlumiaria.setPreferredSize(new java.awt.Dimension(35, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        jPanel8.add(lblBreakdownVoltageIlumiaria, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel11.setText("Ducto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 13);
        jPanel8.add(jLabel11, gridBagConstraints);

        cmbDuctIluminaria.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel8.add(cmbDuctIluminaria, gridBagConstraints);

        jspAngle.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jspAngle.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(9999999.0f), Float.valueOf(0.1f)));
        jspAngle.setMinimumSize(new java.awt.Dimension(100, 21));
        jspAngle.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel8.add(jspAngle, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        jPanel8.add(filler2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        p1.add(jPanel8, gridBagConstraints);

        jtpPanels.addTab("Iluminaria", p1);

        java.awt.GridBagLayout p2Layout = new java.awt.GridBagLayout();
        p2Layout.columnWidths = new int[] {0};
        p2Layout.rowHeights = new int[] {0, 0, 0};
        p2.setLayout(p2Layout);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Capacidad de Corriente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 1, 12))); // NOI18N
        java.awt.GridBagLayout jPanel9Layout = new java.awt.GridBagLayout();
        jPanel9Layout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0};
        jPanel9Layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        jPanel9.setLayout(jPanel9Layout);

        jLabel19.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel19.setText("Cantidad:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 16);
        jPanel9.add(jLabel19, gridBagConstraints);

        jLabel20.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel20.setText("Voltaje:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel9.add(jLabel20, gridBagConstraints);

        cmbVoltagePowerPoint.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbVoltagePowerPoint.setMinimumSize(new java.awt.Dimension(100, 21));
        cmbVoltagePowerPoint.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel9.add(cmbVoltagePowerPoint, gridBagConstraints);

        jLabel21.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel21.setText("Temperatura:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel9.add(jLabel21, gridBagConstraints);

        jLabel22.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel22.setText("Material:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 16);
        jPanel9.add(jLabel22, gridBagConstraints);

        cmbTemperaturePowerPoint.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbTemperaturePowerPoint.setMinimumSize(new java.awt.Dimension(100, 21));
        cmbTemperaturePowerPoint.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel9.add(cmbTemperaturePowerPoint, gridBagConstraints);

        cmbPhasesPowerPoint.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbPhasesPowerPoint.setMinimumSize(new java.awt.Dimension(148, 21));
        cmbPhasesPowerPoint.setPreferredSize(new java.awt.Dimension(148, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel9.add(cmbPhasesPowerPoint, gridBagConstraints);

        jLabel23.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel23.setText("Fases:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 16);
        jPanel9.add(jLabel23, gridBagConstraints);

        jPanel10.setLayout(new java.awt.GridBagLayout());

        cmbMaterialPowerPoint.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbMaterialPowerPoint.setMinimumSize(new java.awt.Dimension(148, 21));
        cmbMaterialPowerPoint.setPreferredSize(new java.awt.Dimension(148, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel10.add(cmbMaterialPowerPoint, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel9.add(jPanel10, gridBagConstraints);

        jLabel24.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel24.setText("Acometida:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 16);
        jPanel9.add(jLabel24, gridBagConstraints);

        pa1.setLayout(new java.awt.GridBagLayout());

        rBtnAirPowerPoint.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        rBtnAirPowerPoint.setText("Aérea");
        rBtnAirPowerPoint.setMinimumSize(new java.awt.Dimension(63, 21));
        rBtnAirPowerPoint.setPreferredSize(new java.awt.Dimension(63, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        pa1.add(rBtnAirPowerPoint, gridBagConstraints);

        rBtnGroundPowerPoint.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        rBtnGroundPowerPoint.setText("Subterránea");
        rBtnGroundPowerPoint.setMinimumSize(new java.awt.Dimension(105, 21));
        rBtnGroundPowerPoint.setPreferredSize(new java.awt.Dimension(105, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        pa1.add(rBtnGroundPowerPoint, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel9.add(pa1, gridBagConstraints);

        jLabel25.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel25.setText("F. Potencia:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel9.add(jLabel25, gridBagConstraints);

        jspPowerFactorPowerPoint.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jspPowerFactorPowerPoint.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.1f), Float.valueOf(0.1f), Float.valueOf(3.0f), Float.valueOf(0.1f)));
        jspPowerFactorPowerPoint.setMinimumSize(new java.awt.Dimension(100, 21));
        jspPowerFactorPowerPoint.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel9.add(jspPowerFactorPowerPoint, gridBagConstraints);

        jLabel26.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel26.setText("Conductor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 16);
        jPanel9.add(jLabel26, gridBagConstraints);

        lblCaliberPowerPoint.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        lblCaliberPowerPoint.setMinimumSize(new java.awt.Dimension(24, 21));
        lblCaliberPowerPoint.setPreferredSize(new java.awt.Dimension(24, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel9.add(lblCaliberPowerPoint, gridBagConstraints);

        btnCalculateCurrentCapacityPowerPoint.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnCalculateCurrentCapacityPowerPoint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/agregar.png"))); // NOI18N
        btnCalculateCurrentCapacityPowerPoint.setText("Calcular");
        btnCalculateCurrentCapacityPowerPoint.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel9.add(btnCalculateCurrentCapacityPowerPoint, gridBagConstraints);

        txtQuantityPowerPoint.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        txtQuantityPowerPoint.setModel(new javax.swing.SpinnerNumberModel(1, 1, 9999999, 1));
        txtQuantityPowerPoint.setMinimumSize(new java.awt.Dimension(100, 21));
        txtQuantityPowerPoint.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel9.add(txtQuantityPowerPoint, gridBagConstraints);

        jLabel44.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel44.setText("C. Ramal:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel9.add(jLabel44, gridBagConstraints);

        lblBranchCircuitPowerPoint.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel9.add(lblBranchCircuitPowerPoint, gridBagConstraints);

        jLabel48.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel48.setText("Tubería:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel9.add(jLabel48, gridBagConstraints);

        cmbPipelinePowerPoint.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbPipelinePowerPoint.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "EMT", "PVC" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel9.add(cmbPipelinePowerPoint, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        p2.add(jPanel9, gridBagConstraints);

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Caída de Voltaje", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 1, 12))); // NOI18N
        java.awt.GridBagLayout jPanel11Layout = new java.awt.GridBagLayout();
        jPanel11Layout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0};
        jPanel11Layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0};
        jPanel11.setLayout(jPanel11Layout);

        jLabel28.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel28.setText("Conductores:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel11.add(jLabel28, gridBagConstraints);

        cmbCalibersPowerPoint.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbCalibersPowerPoint.setMinimumSize(new java.awt.Dimension(100, 21));
        cmbCalibersPowerPoint.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel11.add(cmbCalibersPowerPoint, gridBagConstraints);

        jLabel29.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel29.setText("Longitud:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel11.add(jLabel29, gridBagConstraints);

        jspLengthPowerPoint.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jspLengthPowerPoint.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.1f), Float.valueOf(0.1f), Float.valueOf(9999999.0f), Float.valueOf(0.1f)));
        jspLengthPowerPoint.setMinimumSize(new java.awt.Dimension(100, 21));
        jspLengthPowerPoint.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel11.add(jspLengthPowerPoint, gridBagConstraints);

        jLabel30.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel30.setText("Ángulo:");
        jLabel30.setMaximumSize(new java.awt.Dimension(84, 15));
        jLabel30.setMinimumSize(new java.awt.Dimension(84, 15));
        jLabel30.setPreferredSize(new java.awt.Dimension(84, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 13);
        jPanel11.add(jLabel30, gridBagConstraints);

        btnCalculateBreakdownPowerPoint.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnCalculateBreakdownPowerPoint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/agregar.png"))); // NOI18N
        btnCalculateBreakdownPowerPoint.setText("Calcular");
        btnCalculateBreakdownPowerPoint.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel11.add(btnCalculateBreakdownPowerPoint, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel9.setText("%:");
        jLabel9.setPreferredSize(new java.awt.Dimension(17, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel11.add(jLabel9, gridBagConstraints);

        lblBreakdownVoltagePowerPoint.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        lblBreakdownVoltagePowerPoint.setText("0.0 %");
        lblBreakdownVoltagePowerPoint.setMinimumSize(new java.awt.Dimension(35, 21));
        lblBreakdownVoltagePowerPoint.setPreferredSize(new java.awt.Dimension(35, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel11.add(lblBreakdownVoltagePowerPoint, gridBagConstraints);

        jLabel31.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel31.setText("Ducto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 13);
        jPanel11.add(jLabel31, gridBagConstraints);

        cmbDuctPowerPoint.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbDuctPowerPoint.setMinimumSize(new java.awt.Dimension(100, 21));
        cmbDuctPowerPoint.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel11.add(cmbDuctPowerPoint, gridBagConstraints);

        jspAnglePowerPoint.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jspAnglePowerPoint.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(9999999.0f), Float.valueOf(0.1f)));
        jspAnglePowerPoint.setMinimumSize(new java.awt.Dimension(100, 21));
        jspAnglePowerPoint.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel11.add(jspAnglePowerPoint, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        jPanel11.add(filler1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        p2.add(jPanel11, gridBagConstraints);

        jtpPanels.addTab("Toma Corriente", p2);

        p3.setLayout(new java.awt.GridBagLayout());

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Capacidad de Corriente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 1, 12))); // NOI18N
        java.awt.GridBagLayout jPanel5Layout = new java.awt.GridBagLayout();
        jPanel5Layout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0};
        jPanel5Layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        jPanel5.setLayout(jPanel5Layout);

        jLabel5.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel5.setText("Potencia Total:");
        jLabel5.setMaximumSize(new java.awt.Dimension(107, 107));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel5.add(jLabel5, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel10.setText("Voltaje:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel5.add(jLabel10, gridBagConstraints);

        cmbVoltageSubFeeder.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbVoltageSubFeeder.setMinimumSize(new java.awt.Dimension(100, 21));
        cmbVoltageSubFeeder.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel5.add(cmbVoltageSubFeeder, gridBagConstraints);

        jLabel18.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel18.setText("Temperatura:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel5.add(jLabel18, gridBagConstraints);

        jLabel27.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel27.setText("Material:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel5.add(jLabel27, gridBagConstraints);

        cmbTemperatureSubFeeder.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbTemperatureSubFeeder.setMinimumSize(new java.awt.Dimension(100, 21));
        cmbTemperatureSubFeeder.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel5.add(cmbTemperatureSubFeeder, gridBagConstraints);

        cmbPhasesSubFeeder.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbPhasesSubFeeder.setMinimumSize(new java.awt.Dimension(148, 21));
        cmbPhasesSubFeeder.setPreferredSize(new java.awt.Dimension(148, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel5.add(cmbPhasesSubFeeder, gridBagConstraints);

        jLabel32.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel32.setText("Fases:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel5.add(jLabel32, gridBagConstraints);

        jPanel7.setLayout(new java.awt.GridBagLayout());

        cmbMaterialSubFeeder.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbMaterialSubFeeder.setMinimumSize(new java.awt.Dimension(148, 21));
        cmbMaterialSubFeeder.setPreferredSize(new java.awt.Dimension(148, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel7.add(cmbMaterialSubFeeder, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel5.add(jPanel7, gridBagConstraints);

        jLabel33.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel33.setText("Acometida:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel5.add(jLabel33, gridBagConstraints);

        pa2.setLayout(new java.awt.GridBagLayout());

        rBtnAirSubFeeder.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        rBtnAirSubFeeder.setText("Aérea");
        rBtnAirSubFeeder.setMinimumSize(new java.awt.Dimension(63, 21));
        rBtnAirSubFeeder.setPreferredSize(new java.awt.Dimension(63, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        pa2.add(rBtnAirSubFeeder, gridBagConstraints);

        rBtnGroundSubFeeder.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        rBtnGroundSubFeeder.setText("Subterránea");
        rBtnGroundSubFeeder.setMinimumSize(new java.awt.Dimension(105, 21));
        rBtnGroundSubFeeder.setPreferredSize(new java.awt.Dimension(105, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        pa2.add(rBtnGroundSubFeeder, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel5.add(pa2, gridBagConstraints);

        jLabel34.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel34.setText("F. Potencia:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel5.add(jLabel34, gridBagConstraints);

        jspPowerSubFeeder.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jspPowerSubFeeder.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.1f), Float.valueOf(0.1f), Float.valueOf(3.0f), Float.valueOf(0.1f)));
        jspPowerSubFeeder.setMinimumSize(new java.awt.Dimension(100, 21));
        jspPowerSubFeeder.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel5.add(jspPowerSubFeeder, gridBagConstraints);

        jLabel35.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel35.setText("Conductor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel5.add(jLabel35, gridBagConstraints);

        lblCaliberSubFeeder.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        lblCaliberSubFeeder.setPreferredSize(new java.awt.Dimension(8, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel5.add(lblCaliberSubFeeder, gridBagConstraints);

        btnCalculateCurrentCapacitySubFeeder.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnCalculateCurrentCapacitySubFeeder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/agregar.png"))); // NOI18N
        btnCalculateCurrentCapacitySubFeeder.setText("Calcular");
        btnCalculateCurrentCapacitySubFeeder.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel5.add(btnCalculateCurrentCapacitySubFeeder, gridBagConstraints);

        lblPotencyTotalSubFeeder.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        lblPotencyTotalSubFeeder.setText("0 W");
        lblPotencyTotalSubFeeder.setMinimumSize(new java.awt.Dimension(23, 21));
        lblPotencyTotalSubFeeder.setPreferredSize(new java.awt.Dimension(23, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel5.add(lblPotencyTotalSubFeeder, gridBagConstraints);

        jLabel41.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel41.setText("P. Neutra:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel5.add(jLabel41, gridBagConstraints);

        lblPotencyNeutralSubFeeder.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        lblPotencyNeutralSubFeeder.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPotencyNeutralSubFeeder.setText("0 W");
        lblPotencyNeutralSubFeeder.setPreferredSize(new java.awt.Dimension(23, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel5.add(lblPotencyNeutralSubFeeder, gridBagConstraints);

        jLabel43.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel43.setText("Neutro:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel5.add(jLabel43, gridBagConstraints);

        lblCaliberNeutral.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        lblCaliberNeutral.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCaliberNeutral.setMinimumSize(new java.awt.Dimension(150, 21));
        lblCaliberNeutral.setPreferredSize(new java.awt.Dimension(150, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel5.add(lblCaliberNeutral, gridBagConstraints);

        jLabel49.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel49.setText("Tubería:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel5.add(jLabel49, gridBagConstraints);

        cmbPipelineSubFeeder.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbPipelineSubFeeder.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "EMT", "PVC" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel5.add(cmbPipelineSubFeeder, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        p3.add(jPanel5, gridBagConstraints);

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Caída de Voltaje", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 1, 12))); // NOI18N
        java.awt.GridBagLayout jPanel12Layout = new java.awt.GridBagLayout();
        jPanel12Layout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0};
        jPanel12Layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0};
        jPanel12.setLayout(jPanel12Layout);

        jLabel36.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel36.setText("Conductores:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 12);
        jPanel12.add(jLabel36, gridBagConstraints);

        cmbCalibersSubFeeder.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbCalibersSubFeeder.setMinimumSize(new java.awt.Dimension(100, 21));
        cmbCalibersSubFeeder.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel12.add(cmbCalibersSubFeeder, gridBagConstraints);

        jLabel37.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel37.setText("Longitud:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 12);
        jPanel12.add(jLabel37, gridBagConstraints);

        jspLengthSubFeeder.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jspLengthSubFeeder.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.1f), Float.valueOf(0.1f), Float.valueOf(9999999.0f), Float.valueOf(0.1f)));
        jspLengthSubFeeder.setMinimumSize(new java.awt.Dimension(100, 21));
        jspLengthSubFeeder.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel12.add(jspLengthSubFeeder, gridBagConstraints);

        jLabel38.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel38.setText("Ángulo:");
        jLabel38.setMaximumSize(new java.awt.Dimension(84, 15));
        jLabel38.setMinimumSize(new java.awt.Dimension(84, 15));
        jLabel38.setPreferredSize(new java.awt.Dimension(84, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 12);
        jPanel12.add(jLabel38, gridBagConstraints);

        btnCalculateBreakdownSubFeeder.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnCalculateBreakdownSubFeeder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/agregar.png"))); // NOI18N
        btnCalculateBreakdownSubFeeder.setText("Calcular");
        btnCalculateBreakdownSubFeeder.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel12.add(btnCalculateBreakdownSubFeeder, gridBagConstraints);

        jLabel39.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel39.setText("%:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 12);
        jPanel12.add(jLabel39, gridBagConstraints);

        lblBreakdownVoltageSubFeeder.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        lblBreakdownVoltageSubFeeder.setText("0.0 %");
        lblBreakdownVoltageSubFeeder.setPreferredSize(new java.awt.Dimension(35, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        jPanel12.add(lblBreakdownVoltageSubFeeder, gridBagConstraints);

        jLabel40.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel40.setText("Ducto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel12.add(jLabel40, gridBagConstraints);

        cmbDuctSubFeeder.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbDuctSubFeeder.setMinimumSize(new java.awt.Dimension(100, 21));
        cmbDuctSubFeeder.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel12.add(cmbDuctSubFeeder, gridBagConstraints);

        jspAngleSubFeeder.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jspAngleSubFeeder.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(9999999.0f), Float.valueOf(0.1f)));
        jspAngleSubFeeder.setMinimumSize(new java.awt.Dimension(100, 21));
        jspAngleSubFeeder.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        jPanel12.add(jspAngleSubFeeder, gridBagConstraints);

        jLabel45.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel45.setText("%N:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel12.add(jLabel45, gridBagConstraints);

        lblBreakdownVoltageSubFeederNeutral.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        lblBreakdownVoltageSubFeederNeutral.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblBreakdownVoltageSubFeederNeutral.setText("0.0 %");
        lblBreakdownVoltageSubFeederNeutral.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblBreakdownVoltageSubFeederNeutral.setMaximumSize(new java.awt.Dimension(100, 21));
        lblBreakdownVoltageSubFeederNeutral.setMinimumSize(new java.awt.Dimension(150, 21));
        lblBreakdownVoltageSubFeederNeutral.setPreferredSize(new java.awt.Dimension(150, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel12.add(lblBreakdownVoltageSubFeederNeutral, gridBagConstraints);

        jLabel47.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel47.setText("C. Neutros:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel12.add(jLabel47, gridBagConstraints);

        cmbCalibersSubFeederNeutral.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbCalibersSubFeederNeutral.setMinimumSize(new java.awt.Dimension(100, 21));
        cmbCalibersSubFeederNeutral.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel12.add(cmbCalibersSubFeederNeutral, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel12.add(filler3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        p3.add(jPanel12, gridBagConstraints);

        jtpPanels.addTab("Sub-Alimentador", p3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel1.add(jtpPanels, gridBagConstraints);

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalculateBreakdownIluminaria;
    private javax.swing.JButton btnCalculateBreakdownPowerPoint;
    private javax.swing.JButton btnCalculateBreakdownSubFeeder;
    private javax.swing.JButton btnCalculateCurrentCapacityIluminaria;
    private javax.swing.JButton btnCalculateCurrentCapacityPowerPoint;
    private javax.swing.JButton btnCalculateCurrentCapacitySubFeeder;
    private javax.swing.JButton btnClose;
    private javax.swing.ButtonGroup btnGroupRushIluminaria;
    private javax.swing.ButtonGroup btnGroupRushPowerPoint;
    private javax.swing.ButtonGroup btnGroupRushSubFeeder;
    private javax.swing.JButton btnModify;
    private javax.swing.JButton btnRegister;
    private javax.swing.JComboBox cmbCalibersIluminaria;
    private javax.swing.JComboBox cmbCalibersPowerPoint;
    private javax.swing.JComboBox cmbCalibersSubFeeder;
    private javax.swing.JComboBox cmbCalibersSubFeederNeutral;
    private javax.swing.JComboBox cmbDuctIluminaria;
    private javax.swing.JComboBox cmbDuctPowerPoint;
    private javax.swing.JComboBox cmbDuctSubFeeder;
    private javax.swing.JComboBox cmbMaterialIluminaria;
    private javax.swing.JComboBox cmbMaterialPowerPoint;
    private javax.swing.JComboBox cmbMaterialSubFeeder;
    private javax.swing.JComboBox cmbPhasesIluminaria;
    private javax.swing.JComboBox cmbPhasesPowerPoint;
    private javax.swing.JComboBox cmbPhasesSubFeeder;
    private javax.swing.JComboBox<String> cmbPipelineIuminaria;
    private javax.swing.JComboBox<String> cmbPipelinePowerPoint;
    private javax.swing.JComboBox<String> cmbPipelineSubFeeder;
    private javax.swing.JComboBox cmbTemperatureIlimunaria;
    private javax.swing.JComboBox cmbTemperaturePowerPoint;
    private javax.swing.JComboBox cmbTemperatureSubFeeder;
    private javax.swing.JComboBox cmbVoltageIluminaria;
    private javax.swing.JComboBox cmbVoltagePowerPoint;
    private javax.swing.JComboBox cmbVoltageSubFeeder;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSpinner jspAngle;
    private javax.swing.JSpinner jspAnglePowerPoint;
    private javax.swing.JSpinner jspAngleSubFeeder;
    private javax.swing.JSpinner jspArea;
    private javax.swing.JSpinner jspLengthIluminaria;
    private javax.swing.JSpinner jspLengthPowerPoint;
    private javax.swing.JSpinner jspLengthSubFeeder;
    private javax.swing.JSpinner jspPowerFactor;
    private javax.swing.JSpinner jspPowerFactorPowerPoint;
    private javax.swing.JSpinner jspPowerSubFeeder;
    private javax.swing.JSpinner jspQuantity;
    private javax.swing.JTabbedPane jtpPanels;
    private javax.swing.JLabel lblBranchCircuitIluminaria;
    private javax.swing.JLabel lblBranchCircuitPowerPoint;
    private javax.swing.JLabel lblBreakdownVoltageIlumiaria;
    private javax.swing.JLabel lblBreakdownVoltagePowerPoint;
    private javax.swing.JLabel lblBreakdownVoltageSubFeeder;
    private javax.swing.JLabel lblBreakdownVoltageSubFeederNeutral;
    private javax.swing.JLabel lblCaliberIluminaria;
    private javax.swing.JLabel lblCaliberNeutral;
    private javax.swing.JLabel lblCaliberPowerPoint;
    private javax.swing.JLabel lblCaliberSubFeeder;
    private javax.swing.JLabel lblPotencyNeutralSubFeeder;
    private javax.swing.JLabel lblPotencyTotalSubFeeder;
    private javax.swing.JLabel lblQuantity;
    private javax.swing.JPanel p1;
    private javax.swing.JPanel p2;
    private javax.swing.JPanel p3;
    private javax.swing.JPanel pa;
    private javax.swing.JPanel pa1;
    private javax.swing.JPanel pa2;
    private javax.swing.JRadioButton rBtnAirIluminaria;
    private javax.swing.JRadioButton rBtnAirPowerPoint;
    private javax.swing.JRadioButton rBtnAirSubFeeder;
    private javax.swing.JRadioButton rBtnGroundIluminaria;
    private javax.swing.JRadioButton rBtnGroundPowerPoint;
    private javax.swing.JRadioButton rBtnGroundSubFeeder;
    private javax.swing.JTextField txtName;
    private javax.swing.JSpinner txtQuantityPowerPoint;
    // End of variables declaration//GEN-END:variables
}
