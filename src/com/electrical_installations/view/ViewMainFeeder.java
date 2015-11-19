/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.view;

import com.electrical_installations.controller.ControllerMainFeeder;
import com.electrical_installations.model.entity.Project;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;

/**
 * Clase ViewMainFeeder
 * @author JRichard
 * @version 1
 * @since 2015-11-17
 */
public class ViewMainFeeder extends javax.swing.JDialog {
    
    private final ControllerMainFeeder controllerMainFeeder;
    private double potency_total;
    private double neutral_total;
    private Project project;

    /**
     * Creates new form ViewMainFeeder
     * @param parent
     * @param modal
     */
    public ViewMainFeeder(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        controllerMainFeeder = new ControllerMainFeeder(this);
        controllerMainFeeder.fill_combos_phases();
        controllerMainFeeder.fill_combos_voltages();
        controllerMainFeeder.fill_combos_temperatures();
        controllerMainFeeder.fill_combos_calibers();
        controllerMainFeeder.fill_combos_ducts();
        controllerMainFeeder.fill_combos_materials();
                
        this.btnAdd.addActionListener(controllerMainFeeder);
        this.btnClose.addActionListener(controllerMainFeeder);
        this.btnCalculateCurrentCapacity.addActionListener(controllerMainFeeder);
        this.btnCalculateBreakdown.addActionListener(controllerMainFeeder);
        this.cmbPhases.addActionListener(controllerMainFeeder);
        this.rBtnAir.addChangeListener(controllerMainFeeder);
        this.rBtnGround.addChangeListener(controllerMainFeeder);
        this.addWindowListener(controllerMainFeeder);
        
        this.setLocationRelativeTo(null);
        
    }
        
    //Getters y Setters

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
    
    public double getPotency_total() {    
        return potency_total;
    }

    public void setPotency_total(double potency_total) {
        this.potency_total = potency_total;
    }

    public double getNeutral_total() {
        return neutral_total;
    } 
    
    public void setNeutral_total(double neutral_total) {
        this.neutral_total = neutral_total;
    }

    public JButton getBtnAdd() {
        return btnAdd;
    }

    public void setBtnAdd(JButton btnAdd) {
        this.btnAdd = btnAdd;
    }

    public JButton getBtnCalculateBreakdown() {
        return btnCalculateBreakdown;
    }

    public void setBtnCalculateBreakdown(JButton btnCalculateBreakdown) {
        this.btnCalculateBreakdown = btnCalculateBreakdown;
    }

    public JButton getBtnCalculateCurrentCapacity() {
        return btnCalculateCurrentCapacity;
    }

    public void setBtnCalculateCurrentCapacity(JButton btnCalculateCurrentCapacity) {
        this.btnCalculateCurrentCapacity = btnCalculateCurrentCapacity;
    }

    public JButton getBtnClose() {
        return btnClose;
    }

    public void setBtnClose(JButton btnClose) {
        this.btnClose = btnClose;
    }

    public JComboBox getCmbCaliber() {
        return cmbCaliber;
    }

    public void setCmbCaliber(JComboBox cmbCaliber) {
        this.cmbCaliber = cmbCaliber;
    }

    public JComboBox getCmbCalibersNeutral() {
        return cmbCalibersNeutral;
    }

    public void setCmbCalibersNeutral(JComboBox cmbCalibersNeutral) {
        this.cmbCalibersNeutral = cmbCalibersNeutral;
    }

    public JComboBox getCmbDuct() {
        return cmbDuct;
    }

    public void setCmbDuct(JComboBox cmbDuct) {
        this.cmbDuct = cmbDuct;
    }

    public JComboBox getCmbMaterial() {
        return cmbMaterial;
    }

    public void setCmbMaterial(JComboBox cmbMaterial) {
        this.cmbMaterial = cmbMaterial;
    }

    public JComboBox getCmbPhases() {
        return cmbPhases;
    }

    public void setCmbPhases(JComboBox cmbPhases) {
        this.cmbPhases = cmbPhases;
    }

    public JComboBox getCmbPipeline() {
        return cmbPipeline;
    }

    public void setCmbPipeline(JComboBox cmbPipeline) {
        this.cmbPipeline = cmbPipeline;
    }

    public JComboBox getCmbTemperature() {
        return cmbTemperature;
    }

    public void setCmbTemperature(JComboBox cmbTemperature) {
        this.cmbTemperature = cmbTemperature;
    }

    public JComboBox getCmbVoltage() {
        return cmbVoltage;
    }

    public void setCmbVoltage(JComboBox cmbVoltage) {
        this.cmbVoltage = cmbVoltage;
    }

    public JSpinner getJspAngle() {
        return jspAngle;
    }

    public void setJspAngle(JSpinner jspAngle) {
        this.jspAngle = jspAngle;
    }

    public JSpinner getJspLength() {
        return jspLength;
    }

    public void setJspLength(JSpinner jspLength) {
        this.jspLength = jspLength;
    }

    public JSpinner getJspPowerFactor() {
        return jspPowerFactor;
    }

    public void setJspPowerFactor(JSpinner jspPowerFactor) {
        this.jspPowerFactor = jspPowerFactor;
    }

    public JLabel getLblBreakdownVoltage() {
        return lblBreakdownVoltage;
    }

    public void setLblBreakdownVoltage(JLabel lblBreakdownVoltage) {
        this.lblBreakdownVoltage = lblBreakdownVoltage;
    }

    public JLabel getLblBreakdownVoltageNeutral() {
        return lblBreakdownVoltageNeutral;
    }

    public void setLblBreakdownVoltageNeutral(JLabel lblBreakdownVoltageNeutral) {
        this.lblBreakdownVoltageNeutral = lblBreakdownVoltageNeutral;
    }

    public JLabel getLblCaliberEarth() {
        return lblCaliberEarth;
    }

    public void setLblCaliberEarth(JLabel lblCaliberEarth) {
        this.lblCaliberEarth = lblCaliberEarth;
    }

    public JLabel getLblCaliberNeutral() {
        return lblCaliberNeutral;
    }

    public void setLblCaliberNeutral(JLabel lblCaliberNeutral) {
        this.lblCaliberNeutral = lblCaliberNeutral;
    }

    public JLabel getLblCaliberPhase() {
        return lblCaliberPhase;
    }

    public void setLblCaliberPhase(JLabel lblCaliberPhase) {
        this.lblCaliberPhase = lblCaliberPhase;
    }

    public JLabel getLblPotencyMainFeeder() {
        return lblPotencyMainFeeder;
    }

    public void setLblPotencyMainFeeder(JLabel lblPotencyMainFeeder) {
        this.lblPotencyMainFeeder = lblPotencyMainFeeder;
    }

    public JLabel getLblPotencyNeutral() {
        return lblPotencyNeutral;
    }

    public void setLblPotencyNeutral(JLabel lblPotencyNeutral) {
        this.lblPotencyNeutral = lblPotencyNeutral;
    }

    public JRadioButton getrBtnAir() {
        return rBtnAir;
    }

    public void setrBtnAir(JRadioButton rBtnAir) {
        this.rBtnAir = rBtnAir;
    }

    public JRadioButton getrBtnGround() {
        return rBtnGround;
    } 
    
    public void setrBtnGround(JRadioButton rBtnGround) {
        this.rBtnGround = rBtnGround;
    }//Fin Getters y Setters

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    @SuppressWarnings(value = "unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        p1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cmbVoltage = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmbTemperature = new javax.swing.JComboBox();
        cmbPhases = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        cmbMaterial = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        pa = new javax.swing.JPanel();
        rBtnAir = new javax.swing.JRadioButton();
        rBtnGround = new javax.swing.JRadioButton();
        jLabel16 = new javax.swing.JLabel();
        jspPowerFactor = new javax.swing.JSpinner();
        jLabel17 = new javax.swing.JLabel();
        lblCaliberPhase = new javax.swing.JLabel();
        btnCalculateCurrentCapacity = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblCaliberNeutral = new javax.swing.JLabel();
        lblCaliberEarth = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cmbPipeline = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        lblPotencyMainFeeder = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lblPotencyNeutral = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        cmbCaliber = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jspLength = new javax.swing.JSpinner();
        jLabel14 = new javax.swing.JLabel();
        btnCalculateBreakdown = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lblBreakdownVoltage = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cmbDuct = new javax.swing.JComboBox();
        jspAngle = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        cmbCalibersNeutral = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        lblBreakdownVoltageNeutral = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(97, 21), new java.awt.Dimension(97, 21), new java.awt.Dimension(97, 21));
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Alimentador Principal");
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        p1.setLayout(new java.awt.GridBagLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Capacidad de Corriente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 1, 12))); // NOI18N
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel3.setText("Voltaje:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel4.add(jLabel3, gridBagConstraints);

        cmbVoltage.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbVoltage.setMinimumSize(new java.awt.Dimension(100, 21));
        cmbVoltage.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 3);
        jPanel4.add(cmbVoltage, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel6.setText("Temperatura:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 7, 0);
        jPanel4.add(jLabel6, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel7.setText("Material:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 3, 7, 0);
        jPanel4.add(jLabel7, gridBagConstraints);

        cmbTemperature.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbTemperature.setMinimumSize(new java.awt.Dimension(100, 21));
        cmbTemperature.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 7, 3);
        jPanel4.add(cmbTemperature, gridBagConstraints);

        cmbPhases.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbPhases.setMinimumSize(new java.awt.Dimension(148, 21));
        cmbPhases.setPreferredSize(new java.awt.Dimension(148, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 15);
        jPanel4.add(cmbPhases, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel8.setText("Fases:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel4.add(jLabel8, gridBagConstraints);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        cmbMaterial.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbMaterial.setMinimumSize(new java.awt.Dimension(148, 21));
        cmbMaterial.setPreferredSize(new java.awt.Dimension(148, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel6.add(cmbMaterial, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(7, 12, 7, 15);
        jPanel4.add(jPanel6, gridBagConstraints);

        jLabel15.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel15.setText("Acometida:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 4, 0);
        jPanel4.add(jLabel15, gridBagConstraints);

        pa.setLayout(new java.awt.GridBagLayout());

        buttonGroup1.add(rBtnAir);
        rBtnAir.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        rBtnAir.setText("Aérea");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        pa.add(rBtnAir, gridBagConstraints);

        buttonGroup1.add(rBtnGround);
        rBtnGround.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        rBtnGround.setText("Subterránea");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        pa.add(rBtnGround, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 4, 15);
        jPanel4.add(pa, gridBagConstraints);

        jLabel16.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel16.setText("F. Potencia:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel4.add(jLabel16, gridBagConstraints);

        jspPowerFactor.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jspPowerFactor.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.1f), Float.valueOf(0.1f), Float.valueOf(3.0f), Float.valueOf(0.1f)));
        jspPowerFactor.setMaximumSize(new java.awt.Dimension(100, 21));
        jspPowerFactor.setMinimumSize(new java.awt.Dimension(100, 21));
        jspPowerFactor.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        jPanel4.add(jspPowerFactor, gridBagConstraints);

        jLabel17.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel17.setText("Conductor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(8, 3, 0, 0);
        jPanel4.add(jLabel17, gridBagConstraints);

        lblCaliberPhase.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 12, 0, 15);
        jPanel4.add(lblCaliberPhase, gridBagConstraints);

        btnCalculateCurrentCapacity.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnCalculateCurrentCapacity.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/agregar.png"))); // NOI18N
        btnCalculateCurrentCapacity.setText("Calcular");
        btnCalculateCurrentCapacity.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 3);
        jPanel4.add(btnCalculateCurrentCapacity, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel1.setText("C. Neutro:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        jPanel4.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel2.setText("C. Tierra:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 3, 0, 0);
        jPanel4.add(jLabel2, gridBagConstraints);

        lblCaliberNeutral.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        lblCaliberNeutral.setMinimumSize(new java.awt.Dimension(140, 15));
        lblCaliberNeutral.setPreferredSize(new java.awt.Dimension(140, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 3);
        jPanel4.add(lblCaliberNeutral, gridBagConstraints);

        lblCaliberEarth.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        jPanel4.add(lblCaliberEarth, gridBagConstraints);

        jLabel18.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel18.setText("Tubería:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        jPanel4.add(jLabel18, gridBagConstraints);

        cmbPipeline.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbPipeline.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EMT", "PVC" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        jPanel4.add(cmbPipeline, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel11.setText("Potencia:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 0);
        jPanel4.add(jLabel11, gridBagConstraints);

        lblPotencyMainFeeder.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        lblPotencyMainFeeder.setText("0");
        lblPotencyMainFeeder.setMaximumSize(new java.awt.Dimension(100, 15));
        lblPotencyMainFeeder.setMinimumSize(new java.awt.Dimension(100, 15));
        lblPotencyMainFeeder.setPreferredSize(new java.awt.Dimension(100, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 12, 0, 0);
        jPanel4.add(lblPotencyMainFeeder, gridBagConstraints);

        jLabel20.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel20.setText("P. Neutro:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel4.add(jLabel20, gridBagConstraints);

        lblPotencyNeutral.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        lblPotencyNeutral.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPotencyNeutral.setText("jLabel21");
        lblPotencyNeutral.setMaximumSize(new java.awt.Dimension(100, 15));
        lblPotencyNeutral.setMinimumSize(new java.awt.Dimension(100, 15));
        lblPotencyNeutral.setPreferredSize(new java.awt.Dimension(100, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 3);
        jPanel4.add(lblPotencyNeutral, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        p1.add(jPanel4, gridBagConstraints);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Caída de Voltaje", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 1, 12))); // NOI18N
        jPanel8.setLayout(new java.awt.GridBagLayout());

        jLabel12.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel12.setText("Conductores:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel8.add(jLabel12, gridBagConstraints);

        cmbCaliber.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbCaliber.setMaximumSize(new java.awt.Dimension(100, 21));
        cmbCaliber.setMinimumSize(new java.awt.Dimension(100, 21));
        cmbCaliber.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel8.add(cmbCaliber, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel13.setText("Longitud:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 3, 0, 0);
        jPanel8.add(jLabel13, gridBagConstraints);

        jspLength.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jspLength.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.1f), Float.valueOf(0.1f), Float.valueOf(9999999.0f), Float.valueOf(0.1f)));
        jspLength.setMaximumSize(new java.awt.Dimension(100, 21));
        jspLength.setMinimumSize(new java.awt.Dimension(100, 21));
        jspLength.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(7, 0, 0, 0);
        jPanel8.add(jspLength, gridBagConstraints);

        jLabel14.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel14.setText("Ángulo:");
        jLabel14.setMaximumSize(new java.awt.Dimension(84, 15));
        jLabel14.setMinimumSize(new java.awt.Dimension(84, 15));
        jLabel14.setPreferredSize(new java.awt.Dimension(84, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 3, 0, 23);
        jPanel8.add(jLabel14, gridBagConstraints);

        btnCalculateBreakdown.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnCalculateBreakdown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/agregar.png"))); // NOI18N
        btnCalculateBreakdown.setText("Calcular");
        btnCalculateBreakdown.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 3);
        jPanel8.add(btnCalculateBreakdown, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel4.setText("%:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(8, 3, 0, 0);
        jPanel8.add(jLabel4, gridBagConstraints);

        lblBreakdownVoltage.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        lblBreakdownVoltage.setText("0,0 %");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        jPanel8.add(lblBreakdownVoltage, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Ducto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 27);
        jPanel8.add(jLabel9, gridBagConstraints);

        cmbDuct.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbDuct.setMinimumSize(new java.awt.Dimension(100, 21));
        cmbDuct.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 3);
        jPanel8.add(cmbDuct, gridBagConstraints);

        jspAngle.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jspAngle.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(9999999.0f), Float.valueOf(0.1f)));
        jspAngle.setMinimumSize(new java.awt.Dimension(100, 21));
        jspAngle.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(7, 0, 0, 3);
        jPanel8.add(jspAngle, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel5.setText("C. Neutros:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 4);
        jPanel8.add(jLabel5, gridBagConstraints);

        cmbCalibersNeutral.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbCalibersNeutral.setMinimumSize(new java.awt.Dimension(100, 21));
        cmbCalibersNeutral.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        jPanel8.add(cmbCalibersNeutral, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel10.setText("%N:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        jPanel8.add(jLabel10, gridBagConstraints);

        lblBreakdownVoltageNeutral.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        lblBreakdownVoltageNeutral.setText("0,0 %");
        lblBreakdownVoltageNeutral.setMaximumSize(new java.awt.Dimension(140, 15));
        lblBreakdownVoltageNeutral.setMinimumSize(new java.awt.Dimension(140, 15));
        lblBreakdownVoltageNeutral.setPreferredSize(new java.awt.Dimension(140, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        jPanel8.add(lblBreakdownVoltageNeutral, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        jPanel8.add(filler1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        p1.add(jPanel8, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        getContentPane().add(p1, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        java.awt.GridBagLayout jPanel3Layout = new java.awt.GridBagLayout();
        jPanel3Layout.columnWidths = new int[] {0, 5, 0};
        jPanel3Layout.rowHeights = new int[] {0};
        jPanel3.setLayout(jPanel3Layout);

        btnAdd.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/registrar.png"))); // NOI18N
        btnAdd.setMnemonic('A');
        btnAdd.setText("Agregar");
        btnAdd.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 5, 0);
        jPanel3.add(btnAdd, gridBagConstraints);

        btnClose.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/cerrar.png"))); // NOI18N
        btnClose.setMnemonic('C');
        btnClose.setText("Cerrar");
        btnClose.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 5, 0);
        jPanel3.add(btnClose, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel1.add(jPanel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(jPanel1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCalculateBreakdown;
    private javax.swing.JButton btnCalculateCurrentCapacity;
    private javax.swing.JButton btnClose;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbCaliber;
    private javax.swing.JComboBox cmbCalibersNeutral;
    private javax.swing.JComboBox cmbDuct;
    private javax.swing.JComboBox cmbMaterial;
    private javax.swing.JComboBox cmbPhases;
    private javax.swing.JComboBox<String> cmbPipeline;
    private javax.swing.JComboBox cmbTemperature;
    private javax.swing.JComboBox cmbVoltage;
    private javax.swing.Box.Filler filler1;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JSpinner jspAngle;
    private javax.swing.JSpinner jspLength;
    private javax.swing.JSpinner jspPowerFactor;
    private javax.swing.JLabel lblBreakdownVoltage;
    private javax.swing.JLabel lblBreakdownVoltageNeutral;
    private javax.swing.JLabel lblCaliberEarth;
    private javax.swing.JLabel lblCaliberNeutral;
    private javax.swing.JLabel lblCaliberPhase;
    private javax.swing.JLabel lblPotencyMainFeeder;
    private javax.swing.JLabel lblPotencyNeutral;
    private javax.swing.JPanel p1;
    private javax.swing.JPanel pa;
    private javax.swing.JRadioButton rBtnAir;
    private javax.swing.JRadioButton rBtnGround;
    // End of variables declaration//GEN-END:variables
}
