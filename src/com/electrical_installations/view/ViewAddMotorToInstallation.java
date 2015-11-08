/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.view;

import com.electrical_installations.controller.ControllerElevatorInInstallation;
import com.electrical_installations.model.entity.Project;
import com.electrical_installations.model.entity.masters.PercentageOfThreePhaseMotors;
import com.electrical_installations.model.enums.TypePhase;
import com.electrical_installations.model.enums.TypePhases;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;

/**
 * Clave ViewAddMotorToInstallation.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-26
 */
public class ViewAddMotorToInstallation extends javax.swing.JDialog {

    private final ControllerElevatorInInstallation controller;
    private int voltage;
    private String temperature;
    private String material;
    private String phase;
    private TypePhases typePhases;
    private Project project;
    private TypePhase typePhase;
    
    
    /**
     * Constructor de ViewAddElevatorToInstallation, por ser subclase de JDialog recibe como parámetro el padre y true en caso de que sea modal.
     * @param parent
     * @param modal 
     */    
    public ViewAddMotorToInstallation(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
         
        this.voltage = 0;
                         
        controller = new ControllerElevatorInInstallation(this);
        this.btnAdd.addActionListener(controller);
        this.btnClose.addActionListener(controller); 
        this.btnCalculateCurrentCapacity.addActionListener(controller);
        this.btnCalculateBreakdown.addActionListener(controller);
        this.rBtnAir.addChangeListener(controller);
        this.rBtnGround.addChangeListener(controller);
        this.setLocationRelativeTo(null);
        
    }
    
    public void configuration(TypePhases typePhases, PercentageOfThreePhaseMotors percentageOfThreePhaseMotors) {
        if (typePhases.getPhase().equalsIgnoreCase(TypePhases.PHASE_FOUR_THREAD.getPhase())) {
            controller.fill_three_phases_HP();
            controller.fill_combos_phases(typePhases);
            controller.fill_combo_percentaje_three_phase_motors(percentageOfThreePhaseMotors);
            controller.fill_combos_voltages_motores_trifasicos();
        } else {
            controller.fill_combos_phases(typePhases);
            controller.fill_single_phases_HP();
            controller.fill_combo_percentage_single_phase_motors();
            controller.fill_combos_voltages();
        }
        controller.fill_combos_materials();
        controller.fill_combos_temperatures();         
        controller.fill_combos_ducts();
        controller.fill_combos_calibers();

    }

    //Getters y Setters
    
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public TypePhases getTypePhases() {
        return typePhases;
    }

    public void setTypePhases(TypePhases typePhases) {
        this.typePhases = typePhases;
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

    public ButtonGroup getBtnGroupMaterial() {
        return btnGroupRush;
    }

    public void setBtnGroupMaterial(ButtonGroup btnGroupMaterial) {
        this.btnGroupRush = btnGroupMaterial;
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

    public JSpinner getJspQuantity() {
        return jspQuantity;
    }

    public void setJspQuantity(JSpinner jspQuantity) {
        this.jspQuantity = jspQuantity;
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

    public JLabel getLblHP() {
        return lblHP;
    }

    public void setLblHP(JLabel lblHP) {
        this.lblHP = lblHP;
    }

    public JLabel getLblIn() {
        return lblIn;
    }

    public void setLblIn(JLabel lblIn) {
        this.lblIn = lblIn;
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
    }
    
    public JLabel getLblDescription() {
        return lblDescription;
    }

    public void setLblDescription(JLabel lblDescription) {
        this.lblDescription = lblDescription;
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
    
    public JComboBox getCmbHP() {
        return cmbHP;
    }

    public void setCmbHP(JComboBox cmbHP) {
        this.cmbHP = cmbHP;
    } 
    
    public int getVoltage() {
        return voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }
        
    public JComboBox getCmbPhases() {
        return cmbPhases;
    }

    public void setCmbPhases(JComboBox cmbPhases) {
        this.cmbPhases = cmbPhases;
    }

    public JComboBox getCmbTemperature() {
        return cmbTemperature;
    }

    public void setCmbTemperature(JComboBox cmbTemperature) {
        this.cmbTemperature = cmbTemperature;
    }

    public JButton getBtnAdd() {
        return btnAdd;
    }

    public void setBtnAdd(JButton btnAdd) {
        this.btnAdd = btnAdd;
    }

    public JButton getBtnClose() {
        return btnClose;
    }

    public void setBtnClose(JButton btnClose) {
        this.btnClose = btnClose;
    }

    public JComboBox getCmbVoltage() {
        return cmbVoltage;
    }

    public void setCmbVoltage(JComboBox cmbVoltage) {
        this.cmbVoltage = cmbVoltage;
    }

    public JComboBox getCmbPercentageSinglePhaseMotors() {
        return cmbPercentageSinglePhaseMotors;
    }

    public void setCmbPercentageSinglePhaseMotors(JComboBox cmbPercentageSinglePhaseMotors) {
        this.cmbPercentageSinglePhaseMotors = cmbPercentageSinglePhaseMotors;
    }

    public TypePhase getTypePhase() {
        return typePhase;
    }

    public void setTypePhase(TypePhase typePhase) {
        this.typePhase = typePhase;
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

        btnGroupRush = new javax.swing.ButtonGroup();
        p1 = new javax.swing.JPanel();
        p2 = new javax.swing.JPanel();
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
        lblIn = new javax.swing.JLabel();
        cmbPercentageSinglePhaseMotors = new javax.swing.JComboBox();
        lblHP = new javax.swing.JLabel();
        cmbHP = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblCaliberNeutral = new javax.swing.JLabel();
        lblCaliberEarth = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jspQuantity = new javax.swing.JSpinner();
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
        jLabel11 = new javax.swing.JLabel();
        lblDescription = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar Voltaje");
        setMinimumSize(new java.awt.Dimension(490, 130));
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        p1.setLayout(new java.awt.GridBagLayout());

        p2.setLayout(new java.awt.GridBagLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Capacidad de Corriente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 1, 12))); // NOI18N
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel3.setText("Voltaje:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel4.add(jLabel3, gridBagConstraints);

        cmbVoltage.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbVoltage.setMinimumSize(new java.awt.Dimension(100, 21));
        cmbVoltage.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 3);
        jPanel4.add(cmbVoltage, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel6.setText("Temperatura:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(7, 0, 7, 0);
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
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(7, 0, 7, 3);
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
        jLabel15.setMaximumSize(new java.awt.Dimension(95, 15));
        jLabel15.setMinimumSize(new java.awt.Dimension(95, 15));
        jLabel15.setPreferredSize(new java.awt.Dimension(95, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 4, 0);
        jPanel4.add(jLabel15, gridBagConstraints);

        pa.setLayout(new java.awt.GridBagLayout());

        btnGroupRush.add(rBtnAir);
        rBtnAir.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        rBtnAir.setText("Aérea");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        pa.add(rBtnAir, gridBagConstraints);

        btnGroupRush.add(rBtnGround);
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
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 0);
        jPanel4.add(jLabel16, gridBagConstraints);

        jspPowerFactor.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jspPowerFactor.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.1f), Float.valueOf(0.1f), Float.valueOf(3.0f), Float.valueOf(0.1f)));
        jspPowerFactor.setMaximumSize(new java.awt.Dimension(100, 21));
        jspPowerFactor.setMinimumSize(new java.awt.Dimension(100, 21));
        jspPowerFactor.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 3);
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
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 3);
        jPanel4.add(btnCalculateCurrentCapacity, gridBagConstraints);

        lblIn.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        lblIn.setText("% In:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(7, 3, 0, 0);
        jPanel4.add(lblIn, gridBagConstraints);

        cmbPercentageSinglePhaseMotors.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(7, 12, 0, 3);
        jPanel4.add(cmbPercentageSinglePhaseMotors, gridBagConstraints);

        lblHP.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        lblHP.setText("HP:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel4.add(lblHP, gridBagConstraints);

        cmbHP.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbHP.setMinimumSize(new java.awt.Dimension(100, 21));
        cmbHP.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(3, 12, 0, 15);
        jPanel4.add(cmbHP, gridBagConstraints);

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
        lblCaliberNeutral.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCaliberNeutral.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblCaliberNeutral.setMinimumSize(new java.awt.Dimension(160, 15));
        lblCaliberNeutral.setPreferredSize(new java.awt.Dimension(160, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 3);
        jPanel4.add(lblCaliberNeutral, gridBagConstraints);

        lblCaliberEarth.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        lblCaliberEarth.setPreferredSize(new java.awt.Dimension(250, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 12, 0, 0);
        jPanel4.add(lblCaliberEarth, gridBagConstraints);

        jLabel19.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel19.setText("Cantidad:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 3, 0, 0);
        jPanel4.add(jLabel19, gridBagConstraints);

        jspQuantity.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jspQuantity.setModel(new javax.swing.SpinnerNumberModel(1, 1, 9999999, 1));
        jspQuantity.setMinimumSize(new java.awt.Dimension(100, 21));
        jspQuantity.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 12, 0, 0);
        jPanel4.add(jspQuantity, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        p2.add(jPanel4, gridBagConstraints);

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
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel8.add(cmbCaliber, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel13.setText("Longitud:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 3, 0, 0);
        jPanel8.add(jLabel13, gridBagConstraints);

        jspLength.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jspLength.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.1f), Float.valueOf(0.1f), Float.valueOf(9999999.0f), Float.valueOf(0.1f)));
        jspLength.setMaximumSize(new java.awt.Dimension(100, 21));
        jspLength.setMinimumSize(new java.awt.Dimension(100, 21));
        jspLength.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
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
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 3, 0, 23);
        jPanel8.add(jLabel14, gridBagConstraints);

        btnCalculateBreakdown.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnCalculateBreakdown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/agregar.png"))); // NOI18N
        btnCalculateBreakdown.setText("Calcular");
        btnCalculateBreakdown.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 3);
        jPanel8.add(btnCalculateBreakdown, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel4.setText("%:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(8, 3, 0, 0);
        jPanel8.add(jLabel4, gridBagConstraints);

        lblBreakdownVoltage.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        lblBreakdownVoltage.setText("0,0 %");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        jPanel8.add(lblBreakdownVoltage, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Ducto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 27);
        jPanel8.add(jLabel9, gridBagConstraints);

        cmbDuct.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbDuct.setMinimumSize(new java.awt.Dimension(100, 21));
        cmbDuct.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 3);
        jPanel8.add(cmbDuct, gridBagConstraints);

        jspAngle.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jspAngle.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(9999999.0f), Float.valueOf(0.1f)));
        jspAngle.setMinimumSize(new java.awt.Dimension(100, 21));
        jspAngle.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(7, 0, 0, 3);
        jPanel8.add(jspAngle, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel5.setText("C. Neutros:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 4);
        jPanel8.add(jLabel5, gridBagConstraints);

        cmbCalibersNeutral.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbCalibersNeutral.setMinimumSize(new java.awt.Dimension(100, 21));
        cmbCalibersNeutral.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        jPanel8.add(cmbCalibersNeutral, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel10.setText("%N:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        jPanel8.add(jLabel10, gridBagConstraints);

        lblBreakdownVoltageNeutral.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        lblBreakdownVoltageNeutral.setText("0,0 %");
        lblBreakdownVoltageNeutral.setMaximumSize(new java.awt.Dimension(140, 15));
        lblBreakdownVoltageNeutral.setMinimumSize(new java.awt.Dimension(160, 15));
        lblBreakdownVoltageNeutral.setPreferredSize(new java.awt.Dimension(160, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        jPanel8.add(lblBreakdownVoltageNeutral, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        jPanel8.add(filler1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        p2.add(jPanel8, gridBagConstraints);

        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] {0, 5, 0};
        jPanel1Layout.rowHeights = new int[] {0};
        jPanel1.setLayout(jPanel1Layout);

        jLabel11.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel11.setText("Descripción:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel1.add(jLabel11, gridBagConstraints);

        lblDescription.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        lblDescription.setForeground(new java.awt.Color(0, 0, 153));
        lblDescription.setText("1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel1.add(lblDescription, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        p2.add(jPanel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        p1.add(p2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(p1, gridBagConstraints);

        java.awt.GridBagLayout jPanel3Layout = new java.awt.GridBagLayout();
        jPanel3Layout.columnWidths = new int[] {0, 5, 0, 5, 0};
        jPanel3Layout.rowHeights = new int[] {0};
        jPanel3.setLayout(jPanel3Layout);

        btnAdd.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/registrar.png"))); // NOI18N
        btnAdd.setMnemonic('A');
        btnAdd.setText("Agregar");
        btnAdd.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
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
        gridBagConstraints.gridx = 4;
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
        getContentPane().add(jPanel3, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCalculateBreakdown;
    private javax.swing.JButton btnCalculateCurrentCapacity;
    private javax.swing.JButton btnClose;
    private javax.swing.ButtonGroup btnGroupRush;
    private javax.swing.JComboBox cmbCaliber;
    private javax.swing.JComboBox cmbCalibersNeutral;
    private javax.swing.JComboBox cmbDuct;
    private javax.swing.JComboBox cmbHP;
    private javax.swing.JComboBox cmbMaterial;
    private javax.swing.JComboBox cmbPercentageSinglePhaseMotors;
    private javax.swing.JComboBox cmbPhases;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JSpinner jspQuantity;
    private javax.swing.JLabel lblBreakdownVoltage;
    private javax.swing.JLabel lblBreakdownVoltageNeutral;
    private javax.swing.JLabel lblCaliberEarth;
    private javax.swing.JLabel lblCaliberNeutral;
    private javax.swing.JLabel lblCaliberPhase;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblHP;
    private javax.swing.JLabel lblIn;
    private javax.swing.JPanel p1;
    private javax.swing.JPanel p2;
    private javax.swing.JPanel pa;
    private javax.swing.JRadioButton rBtnAir;
    private javax.swing.JRadioButton rBtnGround;
    // End of variables declaration//GEN-END:variables
}
