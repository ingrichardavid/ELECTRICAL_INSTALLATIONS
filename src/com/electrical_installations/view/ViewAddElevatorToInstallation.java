/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.view;

import com.electrical_installations.controller.ControllerElevatorInInstallation;
import com.electrical_installations.controller.ControllerVoltageInCharge;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

/**
 * Clave ViewAddElevatorToInstallation.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-26
 */
public class ViewAddElevatorToInstallation extends javax.swing.JDialog {

    private final ControllerElevatorInInstallation controller;
    private int voltage;
    private String temperature;
    private String material;
    private String phase;
    
    /**
     * Constructor de ViewAddElevatorToInstallation, por ser subclase de JDialog recibe como parámetro el padre y true en caso de que sea modal.
     * @param parent
     * @param modal 
     */    
    public ViewAddElevatorToInstallation(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
 
        this.voltage = 0;
         
        this.rBtnCu.setSelected(true);
        this.btnGroupMaterial.add(rBtnCu);
        this.btnGroupMaterial.add(rBtnAl);
                        
        controller = new ControllerElevatorInInstallation(this);
        this.btnAdd.addActionListener(controller);
        this.btnClose.addActionListener(controller);
        
        this.setLocationRelativeTo(null);
        
    }
    
    //Getters y Setters

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

    public JRadioButton getrBtnAl() {
        return rBtnAl;
    }

    public void setrBtnAl(JRadioButton rBtnAl) {
        this.rBtnAl = rBtnAl;
    }

    public JRadioButton getrBtnCu() {
        return rBtnCu;
    }

    public void setrBtnCu(JRadioButton rBtnCu) {
        this.rBtnCu = rBtnCu;
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
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        btnGroupMaterial = new javax.swing.ButtonGroup();
        p1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmbVoltage = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmbTemperature = new javax.swing.JComboBox();
        cmbPhases = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        rBtnCu = new javax.swing.JRadioButton();
        rBtnAl = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        pa = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel16 = new javax.swing.JLabel();
        jSpinner2 = new javax.swing.JSpinner();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel14 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar Voltaje");
        setMinimumSize(new java.awt.Dimension(490, 130));
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        p1.setLayout(new java.awt.GridBagLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Capacidad de Corriente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 1, 12))); // NOI18N
        java.awt.GridBagLayout jPanel4Layout = new java.awt.GridBagLayout();
        jPanel4Layout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0};
        jPanel4Layout.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
        jPanel4.setLayout(jPanel4Layout);

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel2.setText("Potencia:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel4.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel3.setText("Voltaje:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel4.add(jLabel3, gridBagConstraints);

        cmbVoltage.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbVoltage.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "110 V", "115 V", "208 V", "220 V", "240 V", "440 V" }));
        cmbVoltage.setMinimumSize(new java.awt.Dimension(100, 21));
        cmbVoltage.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 3);
        jPanel4.add(cmbVoltage, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel6.setText("Temperatura:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 4, 0);
        jPanel4.add(jLabel6, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel7.setText("Material:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 4, 0);
        jPanel4.add(jLabel7, gridBagConstraints);

        cmbTemperature.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbTemperature.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "60 ºC", "75 ºC", "85 ºC", "90 ºC" }));
        cmbTemperature.setEnabled(false);
        cmbTemperature.setMinimumSize(new java.awt.Dimension(100, 21));
        cmbTemperature.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 4, 3);
        jPanel4.add(cmbTemperature, gridBagConstraints);

        cmbPhases.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbPhases.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Monofásica 2 hilos", "Monofásica 3 hilos", "Trifásica 4 hilos" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 16, 0, 0);
        jPanel4.add(cmbPhases, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel8.setText("Fases:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel4.add(jLabel8, gridBagConstraints);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        rBtnCu.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        rBtnCu.setText("Cobre(Cu)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel6.add(rBtnCu, gridBagConstraints);

        rBtnAl.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        rBtnAl.setText("Aluminio(Al)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel6.add(rBtnAl, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 12, 4, 0);
        jPanel4.add(jPanel6, gridBagConstraints);

        jLabel15.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel15.setText("Acometida:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 4, 0);
        jPanel4.add(jLabel15, gridBagConstraints);

        pa.setLayout(new java.awt.GridBagLayout());

        jRadioButton1.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jRadioButton1.setText("Aerea");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        pa.add(jRadioButton1, gridBagConstraints);

        jRadioButton2.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jRadioButton2.setText("SubTerranea");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        pa.add(jRadioButton2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 4, 0);
        jPanel4.add(pa, gridBagConstraints);

        jLabel16.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel16.setText("F. Potencia:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 0);
        jPanel4.add(jLabel16, gridBagConstraints);

        jSpinner2.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jSpinner2.setMinimumSize(new java.awt.Dimension(100, 21));
        jSpinner2.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 3);
        jPanel4.add(jSpinner2, gridBagConstraints);

        jLabel17.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel17.setText("Conductor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 0, 0);
        jPanel4.add(jLabel17, gridBagConstraints);

        jLabel18.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jLabel18.setText("HE1 ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 16, 0, 0);
        jPanel4.add(jLabel18, gridBagConstraints);

        jButton1.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/agregar.png"))); // NOI18N
        jButton1.setText("Calcular");
        jButton1.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 3);
        jPanel4.add(jButton1, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jLabel1.setText("HASDA");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 16, 0, 0);
        jPanel4.add(jLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        p1.add(jPanel4, gridBagConstraints);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Caída de Voltaje", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 1, 12))); // NOI18N
        java.awt.GridBagLayout jPanel8Layout = new java.awt.GridBagLayout();
        jPanel8Layout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0};
        jPanel8Layout.rowHeights = new int[] {0, 0, 0, 0, 0};
        jPanel8.setLayout(jPanel8Layout);

        jLabel12.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel12.setText("Conductores:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel8.add(jLabel12, gridBagConstraints);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "230 +THW" }));
        jComboBox1.setMinimumSize(new java.awt.Dimension(100, 21));
        jComboBox1.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel8.add(jComboBox1, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel13.setText("Longitud:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 3, 0, 0);
        jPanel8.add(jLabel13, gridBagConstraints);

        jSpinner1.setMinimumSize(new java.awt.Dimension(100, 21));
        jSpinner1.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        jPanel8.add(jSpinner1, gridBagConstraints);

        jLabel14.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel14.setText("Ángulo:");
        jLabel14.setMaximumSize(new java.awt.Dimension(84, 15));
        jLabel14.setMinimumSize(new java.awt.Dimension(84, 15));
        jLabel14.setPreferredSize(new java.awt.Dimension(84, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        jPanel8.add(jLabel14, gridBagConstraints);

        jTextField1.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 13, 0, 3);
        jPanel8.add(jTextField1, gridBagConstraints);

        jButton2.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/agregar.png"))); // NOI18N
        jButton2.setText("Calcular");
        jButton2.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 3);
        jPanel8.add(jButton2, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel4.setText("%:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 0);
        jPanel8.add(jLabel4, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jLabel5.setText("HE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel8.add(jLabel5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        p1.add(jPanel8, gridBagConstraints);

        getContentPane().add(p1, new java.awt.GridBagConstraints());

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
    private javax.swing.JButton btnClose;
    private javax.swing.ButtonGroup btnGroupMaterial;
    private javax.swing.JComboBox cmbPhases;
    private javax.swing.JComboBox cmbTemperature;
    private javax.swing.JComboBox cmbVoltage;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel p1;
    private javax.swing.JPanel pa;
    private javax.swing.JRadioButton rBtnAl;
    private javax.swing.JRadioButton rBtnCu;
    // End of variables declaration//GEN-END:variables
}
