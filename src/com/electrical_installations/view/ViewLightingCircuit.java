/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.view;

import com.electrical_installations.controller.ControllerLightingCircuit;
import com.electrical_installations.model.entity.Project;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;

/**
 * Clave ViewArea.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-01
 */
public final class ViewLightingCircuit extends javax.swing.JDialog {

    //Objetos, variables, constantes
    private final ControllerLightingCircuit controllerLightingCircuit;
    private Project project; 
    
    /**
     * Constructor de ViewArea, por ser subclase de JDialog recibe como parámetro el padre y true en caso de que sea modal.
     * @param parent
     * @param modal  
     */    
    public ViewLightingCircuit(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
         
            
        controllerLightingCircuit = new ControllerLightingCircuit(this);       
        controllerLightingCircuit.fill_combos_calibers();
        controllerLightingCircuit.fill_combos_phases();
        controllerLightingCircuit.fill_combos_voltages_iluminaria(); 
        controllerLightingCircuit.fill_combos_ducts();
        controllerLightingCircuit.fill_combos_materials();
        controllerLightingCircuit.fill_combos_temperatures(); 
        
        btnClose.addActionListener(controllerLightingCircuit); 
        btnCalculateCurrentCapacityLightingCircuit.addActionListener(controllerLightingCircuit);
        btnCalculateBreakdownLightingCircuit.addActionListener(controllerLightingCircuit);
        btnRegister.addActionListener(controllerLightingCircuit);  
        rBtnAirLightingCircuit.addChangeListener(controllerLightingCircuit);
        rBtnGroundLightingCircuit.addChangeListener(controllerLightingCircuit);
        cmbPhasesLightingCircuit.addActionListener(controllerLightingCircuit);
        
        txtName.addKeyListener(controllerLightingCircuit);
        this.addWindowListener(controllerLightingCircuit);     
        
        this.txtName.requestFocus();
        this.setLocationRelativeTo(null);
  
        
    }
    
    /**
     * Método para colocar visibles o invisibles los botones
     * @param register 
     * @param close 
     */
    public void visible_buttons(boolean register, boolean close){
        this.btnRegister.setVisible(register); 
        this.btnClose.setVisible(close);
    }//Fin del método
    
    /**
     * Método para habilitar y deshabilitar los botones
     * @param register 
     * @param close 
     */
    public void enable_buttons(boolean register, boolean close){
        this.btnRegister.setEnabled(register); 
        this.btnClose.setEnabled(close);
    }//Fin del método
    
    

    //Getters y Setters
    
    public Project getProject() {
        return project;
    }
 
    public void setProject(Project project) {
        this.project = project;
    }

    public JButton getBtnCalculateBreakdownLightingCircuit() {
        return btnCalculateBreakdownLightingCircuit;
    }

    public void setBtnCalculateBreakdownLightingCircuit(JButton btnCalculateBreakdownLightingCircuit) {
        this.btnCalculateBreakdownLightingCircuit = btnCalculateBreakdownLightingCircuit;
    }

    public JButton getBtnCalculateCurrentCapacityLightingCircuit() {
        return btnCalculateCurrentCapacityLightingCircuit;
    }

    public void setBtnCalculateCurrentCapacityLightingCircuit(JButton btnCalculateCurrentCapacityLightingCircuit) {
        this.btnCalculateCurrentCapacityLightingCircuit = btnCalculateCurrentCapacityLightingCircuit;
    }

    public JButton getBtnClose() {
        return btnClose;
    }

    public void setBtnClose(JButton btnClose) {
        this.btnClose = btnClose;
    }

    public JButton getBtnRegister() {
        return btnRegister;
    }

    public void setBtnRegister(JButton btnRegister) {
        this.btnRegister = btnRegister;
    }

    public JComboBox getCmbCalibersLightingCircuit() {
        return cmbCalibersLightingCircuit;
    }

    public void setCmbCalibersLightingCircuit(JComboBox cmbCalibersLightingCircuit) {
        this.cmbCalibersLightingCircuit = cmbCalibersLightingCircuit;
    }

    public JComboBox getCmbDuctLightingCircuit() {
        return cmbDuctLightingCircuit;
    }

    public void setCmbDuctLightingCircuit(JComboBox cmbDuctLightingCircuit) {
        this.cmbDuctLightingCircuit = cmbDuctLightingCircuit;
    }

    public JComboBox getCmbMaterialLightingCircuit() {
        return cmbMaterialLightingCircuit;
    }

    public void setCmbMaterialLightingCircuit(JComboBox cmbMaterialLightingCircuit) {
        this.cmbMaterialLightingCircuit = cmbMaterialLightingCircuit;
    }

    public JComboBox getCmbPhasesLightingCircuit() {
        return cmbPhasesLightingCircuit;
    }

    public void setCmbPhasesLightingCircuit(JComboBox cmbPhasesLightingCircuit) {
        this.cmbPhasesLightingCircuit = cmbPhasesLightingCircuit;
    }

    public JComboBox<String> getCmbPipelineLightingCircuit() {
        return cmbPipelineLightingCircuit;
    }

    public void setCmbPipelineLightingCircuit(JComboBox<String> cmbPipelineLightingCircuit) {
        this.cmbPipelineLightingCircuit = cmbPipelineLightingCircuit;
    }

    public JComboBox getCmbTemperatureLightingCircuit() {
        return cmbTemperatureLightingCircuit;
    }

    public void setCmbTemperatureLightingCircuit(JComboBox cmbTemperatureLightingCircuit) {
        this.cmbTemperatureLightingCircuit = cmbTemperatureLightingCircuit;
    }

    public JComboBox getCmbVoltageLightingCircuit() {
        return cmbVoltageLightingCircuit;
    }

    public void setCmbVoltageLightingCircuit(JComboBox cmbVoltageLightingCircuit) {
        this.cmbVoltageLightingCircuit = cmbVoltageLightingCircuit;
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

    public JSpinner getJspLengthLightingCircuit() {
        return jspLengthLightingCircuit;
    }

    public void setJspLengthLightingCircuit(JSpinner jspLengthLightingCircuit) {
        this.jspLengthLightingCircuit = jspLengthLightingCircuit;
    }

    public JSpinner getJspPowerFactor() {
        return jspPowerFactor;
    }

    public void setJspPowerFactor(JSpinner jspPowerFactor) {
        this.jspPowerFactor = jspPowerFactor;
    }

    public JLabel getLblBranchCircuitLightingCircuit() {
        return lblBranchCircuitLightingCircuit;
    }

    public void setLblBranchCircuitLightingCircuit(JLabel lblBranchCircuitLightingCircuit) {
        this.lblBranchCircuitLightingCircuit = lblBranchCircuitLightingCircuit;
    }

    public JLabel getLblBreakdownVoltageLightingCircuit() {
        return lblBreakdownVoltageLightingCircuit;
    }

    public void setLblBreakdownVoltageLightingCircuit(JLabel lblBreakdownVoltageLightingCircuit) {
        this.lblBreakdownVoltageLightingCircuit = lblBreakdownVoltageLightingCircuit;
    }

    public JLabel getLblCaliberLightingCircuit() {
        return lblCaliberLightingCircuit;
    }

    public void setLblCaliberLightingCircuit(JLabel lblCaliberLightingCircuit) {
        this.lblCaliberLightingCircuit = lblCaliberLightingCircuit;
    }

    public JRadioButton getrBtnAirLightingCircuit() {
        return rBtnAirLightingCircuit;
    }

    public void setrBtnAirLightingCircuit(JRadioButton rBtnAirLightingCircuit) {
        this.rBtnAirLightingCircuit = rBtnAirLightingCircuit;
    }

    public JRadioButton getrBtnGroundLightingCircuit() {
        return rBtnGroundLightingCircuit;
    }

    public void setrBtnGroundLightingCircuit(JRadioButton rBtnGroundLightingCircuit) {
        this.rBtnGroundLightingCircuit = rBtnGroundLightingCircuit;
    }

    public JTextField getTxtName() {
        return txtName;
    }

    public void setTxtName(JTextField txtName) {
        this.txtName = txtName;
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

        btnGroupRushLightingCircuit = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnRegister = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        p4 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        cmbVoltageLightingCircuit = new javax.swing.JComboBox();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        cmbTemperatureLightingCircuit = new javax.swing.JComboBox();
        cmbPhasesLightingCircuit = new javax.swing.JComboBox();
        jLabel54 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        cmbMaterialLightingCircuit = new javax.swing.JComboBox();
        jLabel55 = new javax.swing.JLabel();
        pa3 = new javax.swing.JPanel();
        rBtnAirLightingCircuit = new javax.swing.JRadioButton();
        rBtnGroundLightingCircuit = new javax.swing.JRadioButton();
        jLabel56 = new javax.swing.JLabel();
        jspPowerFactor = new javax.swing.JSpinner();
        jLabel57 = new javax.swing.JLabel();
        lblCaliberLightingCircuit = new javax.swing.JLabel();
        btnCalculateCurrentCapacityLightingCircuit = new javax.swing.JButton();
        jspArea = new javax.swing.JSpinner();
        jLabel58 = new javax.swing.JLabel();
        lblBranchCircuitLightingCircuit = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        cmbPipelineLightingCircuit = new javax.swing.JComboBox<String>();
        jPanel15 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        cmbCalibersLightingCircuit = new javax.swing.JComboBox();
        jLabel61 = new javax.swing.JLabel();
        jspLengthLightingCircuit = new javax.swing.JSpinner();
        jLabel62 = new javax.swing.JLabel();
        btnCalculateBreakdownLightingCircuit = new javax.swing.JButton();
        jLabel63 = new javax.swing.JLabel();
        lblBreakdownVoltageLightingCircuit = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        cmbDuctLightingCircuit = new javax.swing.JComboBox();
        jspAngle = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar Circuito de Iluminación");
        setMinimumSize(new java.awt.Dimension(620, 440));
        setModal(true);
        setPreferredSize(new java.awt.Dimension(521, 440));
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

        p4.setLayout(new java.awt.GridBagLayout());

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Capacidad de Corriente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 1, 12))); // NOI18N
        java.awt.GridBagLayout jPanel13Layout = new java.awt.GridBagLayout();
        jPanel13Layout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0};
        jPanel13Layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        jPanel13.setLayout(jPanel13Layout);

        jLabel50.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel50.setText("Área:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 16);
        jPanel13.add(jLabel50, gridBagConstraints);

        jLabel51.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel51.setText("Voltaje:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel13.add(jLabel51, gridBagConstraints);

        cmbVoltageLightingCircuit.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbVoltageLightingCircuit.setMinimumSize(new java.awt.Dimension(100, 21));
        cmbVoltageLightingCircuit.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel13.add(cmbVoltageLightingCircuit, gridBagConstraints);

        jLabel52.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel52.setText("Temperatura:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel13.add(jLabel52, gridBagConstraints);

        jLabel53.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel53.setText("Material:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 16);
        jPanel13.add(jLabel53, gridBagConstraints);

        cmbTemperatureLightingCircuit.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbTemperatureLightingCircuit.setMinimumSize(new java.awt.Dimension(100, 21));
        cmbTemperatureLightingCircuit.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel13.add(cmbTemperatureLightingCircuit, gridBagConstraints);

        cmbPhasesLightingCircuit.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbPhasesLightingCircuit.setMinimumSize(new java.awt.Dimension(148, 21));
        cmbPhasesLightingCircuit.setPreferredSize(new java.awt.Dimension(148, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel13.add(cmbPhasesLightingCircuit, gridBagConstraints);

        jLabel54.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel54.setText("Fases:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 16);
        jPanel13.add(jLabel54, gridBagConstraints);

        jPanel14.setLayout(new java.awt.GridBagLayout());

        cmbMaterialLightingCircuit.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbMaterialLightingCircuit.setMinimumSize(new java.awt.Dimension(148, 21));
        cmbMaterialLightingCircuit.setPreferredSize(new java.awt.Dimension(148, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel14.add(cmbMaterialLightingCircuit, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel13.add(jPanel14, gridBagConstraints);

        jLabel55.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel55.setText("Acometida:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 16);
        jPanel13.add(jLabel55, gridBagConstraints);

        pa3.setLayout(new java.awt.GridBagLayout());

        btnGroupRushLightingCircuit.add(rBtnAirLightingCircuit);
        rBtnAirLightingCircuit.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        rBtnAirLightingCircuit.setText("Aérea");
        rBtnAirLightingCircuit.setMinimumSize(new java.awt.Dimension(63, 21));
        rBtnAirLightingCircuit.setPreferredSize(new java.awt.Dimension(63, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        pa3.add(rBtnAirLightingCircuit, gridBagConstraints);

        btnGroupRushLightingCircuit.add(rBtnGroundLightingCircuit);
        rBtnGroundLightingCircuit.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        rBtnGroundLightingCircuit.setText("Subterránea");
        rBtnGroundLightingCircuit.setMinimumSize(new java.awt.Dimension(105, 21));
        rBtnGroundLightingCircuit.setPreferredSize(new java.awt.Dimension(105, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        pa3.add(rBtnGroundLightingCircuit, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel13.add(pa3, gridBagConstraints);

        jLabel56.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel56.setText("F. Potencia:");
        jLabel56.setToolTipText("Factor de Potencia");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel13.add(jLabel56, gridBagConstraints);

        jspPowerFactor.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jspPowerFactor.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.1f), Float.valueOf(0.1f), Float.valueOf(3.0f), Float.valueOf(0.1f)));
        jspPowerFactor.setMinimumSize(new java.awt.Dimension(100, 21));
        jspPowerFactor.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel13.add(jspPowerFactor, gridBagConstraints);

        jLabel57.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel57.setText("Conductor:");
        jLabel57.setMinimumSize(new java.awt.Dimension(82, 21));
        jLabel57.setPreferredSize(new java.awt.Dimension(82, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 16);
        jPanel13.add(jLabel57, gridBagConstraints);

        lblCaliberLightingCircuit.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        lblCaliberLightingCircuit.setMinimumSize(new java.awt.Dimension(8, 21));
        lblCaliberLightingCircuit.setPreferredSize(new java.awt.Dimension(8, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanel13.add(lblCaliberLightingCircuit, gridBagConstraints);

        btnCalculateCurrentCapacityLightingCircuit.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnCalculateCurrentCapacityLightingCircuit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/agregar.png"))); // NOI18N
        btnCalculateCurrentCapacityLightingCircuit.setText("Calcular");
        btnCalculateCurrentCapacityLightingCircuit.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel13.add(btnCalculateCurrentCapacityLightingCircuit, gridBagConstraints);

        jspArea.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jspArea.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.1f), Float.valueOf(0.1f), Float.valueOf(9999999.0f), Float.valueOf(0.1f)));
        jspArea.setMinimumSize(new java.awt.Dimension(100, 21));
        jspArea.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel13.add(jspArea, gridBagConstraints);

        jLabel58.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel58.setText("C. Ramal:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel13.add(jLabel58, gridBagConstraints);

        lblBranchCircuitLightingCircuit.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel13.add(lblBranchCircuitLightingCircuit, gridBagConstraints);

        jLabel59.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel59.setText("Tubería:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel13.add(jLabel59, gridBagConstraints);

        cmbPipelineLightingCircuit.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbPipelineLightingCircuit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "EMT", "PVC" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel13.add(cmbPipelineLightingCircuit, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        p4.add(jPanel13, gridBagConstraints);

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Caída de Voltaje", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 1, 12))); // NOI18N
        java.awt.GridBagLayout jPanel15Layout = new java.awt.GridBagLayout();
        jPanel15Layout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0};
        jPanel15Layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0};
        jPanel15.setLayout(jPanel15Layout);

        jLabel60.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel60.setText("Conductores:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel15.add(jLabel60, gridBagConstraints);

        cmbCalibersLightingCircuit.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbCalibersLightingCircuit.setMinimumSize(new java.awt.Dimension(100, 21));
        cmbCalibersLightingCircuit.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel15.add(cmbCalibersLightingCircuit, gridBagConstraints);

        jLabel61.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel61.setText("Longitud:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel15.add(jLabel61, gridBagConstraints);

        jspLengthLightingCircuit.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jspLengthLightingCircuit.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.1f), Float.valueOf(0.1f), Float.valueOf(9999999.0f), Float.valueOf(0.1f)));
        jspLengthLightingCircuit.setMinimumSize(new java.awt.Dimension(100, 21));
        jspLengthLightingCircuit.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel15.add(jspLengthLightingCircuit, gridBagConstraints);

        jLabel62.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel62.setText("Ángulo:");
        jLabel62.setMaximumSize(new java.awt.Dimension(84, 15));
        jLabel62.setMinimumSize(new java.awt.Dimension(84, 15));
        jLabel62.setPreferredSize(new java.awt.Dimension(84, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 13);
        jPanel15.add(jLabel62, gridBagConstraints);

        btnCalculateBreakdownLightingCircuit.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnCalculateBreakdownLightingCircuit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/agregar.png"))); // NOI18N
        btnCalculateBreakdownLightingCircuit.setText("Calcular");
        btnCalculateBreakdownLightingCircuit.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel15.add(btnCalculateBreakdownLightingCircuit, gridBagConstraints);

        jLabel63.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel63.setText("%:");
        jLabel63.setMinimumSize(new java.awt.Dimension(17, 21));
        jLabel63.setPreferredSize(new java.awt.Dimension(17, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel15.add(jLabel63, gridBagConstraints);

        lblBreakdownVoltageLightingCircuit.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        lblBreakdownVoltageLightingCircuit.setText("0.0 %");
        lblBreakdownVoltageLightingCircuit.setMinimumSize(new java.awt.Dimension(35, 21));
        lblBreakdownVoltageLightingCircuit.setPreferredSize(new java.awt.Dimension(35, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        jPanel15.add(lblBreakdownVoltageLightingCircuit, gridBagConstraints);

        jLabel64.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel64.setText("Ducto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 13);
        jPanel15.add(jLabel64, gridBagConstraints);

        cmbDuctLightingCircuit.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel15.add(cmbDuctLightingCircuit, gridBagConstraints);

        jspAngle.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jspAngle.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(9999999.0f), Float.valueOf(0.1f)));
        jspAngle.setMinimumSize(new java.awt.Dimension(100, 21));
        jspAngle.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel15.add(jspAngle, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        p4.add(jPanel15, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(p4, gridBagConstraints);

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalculateBreakdownLightingCircuit;
    private javax.swing.JButton btnCalculateCurrentCapacityLightingCircuit;
    private javax.swing.JButton btnClose;
    private javax.swing.ButtonGroup btnGroupRushLightingCircuit;
    private javax.swing.JButton btnRegister;
    private javax.swing.JComboBox cmbCalibersLightingCircuit;
    private javax.swing.JComboBox cmbDuctLightingCircuit;
    private javax.swing.JComboBox cmbMaterialLightingCircuit;
    private javax.swing.JComboBox cmbPhasesLightingCircuit;
    private javax.swing.JComboBox<String> cmbPipelineLightingCircuit;
    private javax.swing.JComboBox cmbTemperatureLightingCircuit;
    private javax.swing.JComboBox cmbVoltageLightingCircuit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSpinner jspAngle;
    private javax.swing.JSpinner jspArea;
    private javax.swing.JSpinner jspLengthLightingCircuit;
    private javax.swing.JSpinner jspPowerFactor;
    private javax.swing.JLabel lblBranchCircuitLightingCircuit;
    private javax.swing.JLabel lblBreakdownVoltageLightingCircuit;
    private javax.swing.JLabel lblCaliberLightingCircuit;
    private javax.swing.JPanel p4;
    private javax.swing.JPanel pa3;
    private javax.swing.JRadioButton rBtnAirLightingCircuit;
    private javax.swing.JRadioButton rBtnGroundLightingCircuit;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
