/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.view;

import com.electrical_installations.controller.ControllerCharge;
import com.electrical_installations.model.entity.Area;
import com.electrical_installations.model.entity.Charge;
import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Clase ViewCharge
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-01
 */
public class ViewCharge extends javax.swing.JDialog {

    private final ControllerCharge controller;
    private Area area;
    private Charge charge;
    private int voltage;
    private String temperature;
    private String material;
    private String phase;
    private final Font fuente;

    /**
     * Constructor de ViewCharge, por ser subclase de JDialog recibe como
     * parámetro el padre y true en caso de que sea modal.
     *
     * @param parent
     * @param modal
     */
    public ViewCharge(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        //Tabla de Cargas 
        fuente = new Font("DejaVu Sans", Font.BOLD, 11);
        tblCharges.getTableHeader().setFont(fuente);
        tblCharges.getColumnModel().getColumn(0).setMaxWidth(0);
        tblCharges.getColumnModel().getColumn(0).setMinWidth(0);
        tblCharges.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblCharges.getColumnModel().getColumn(3).setMaxWidth(0);
        tblCharges.getColumnModel().getColumn(3).setMinWidth(0);
        tblCharges.getColumnModel().getColumn(3).setPreferredWidth(0);
        tblCharges.getColumnModel().getColumn(4).setMaxWidth(0);
        tblCharges.getColumnModel().getColumn(4).setMinWidth(0);
        tblCharges.getColumnModel().getColumn(4).setPreferredWidth(0);
        tblCharges.getColumnModel().getColumn(5).setMaxWidth(0);
        tblCharges.getColumnModel().getColumn(5).setMinWidth(0);
        tblCharges.getColumnModel().getColumn(5).setPreferredWidth(0);
        tblCharges.getColumnModel().getColumn(6).setMaxWidth(0);
        tblCharges.getColumnModel().getColumn(6).setMinWidth(0);
        tblCharges.getColumnModel().getColumn(6).setPreferredWidth(0);
        tblCharges.getColumnModel().getColumn(7).setMaxWidth(0);
        tblCharges.getColumnModel().getColumn(7).setMinWidth(0);
        tblCharges.getColumnModel().getColumn(7).setPreferredWidth(0);
        
        int[] anchos3 = {0, 170, 30, 0, 0, 0,0,0};
        for (int i = 0; i < tblCharges.getColumnCount(); i++) {
            tblCharges.getColumnModel().getColumn(i).setPreferredWidth(anchos3[i]);
        }

        voltage = 0;
        
        btnGroupRush.add(rBtnAir);
        btnGroupRush.add(rBtnGround);

        controller = new ControllerCharge(this);
        controller.fill_table_charges();
        controller.fill_combobox_HP();
        controller.fill_combobox_percentage_single_phase_motors();
        controller.fill_combos_phases();
        controller.fill_combos_voltages();
        controller.fill_combos_temperatures();
        controller.fill_combos_calibers();
        controller.fill_combos_ducts();
        controller.fill_combos_materials();
        
        this.tblCharges.addMouseListener(controller);
        this.txtFindCharge.addKeyListener(controller);
        this.btnAdd.addActionListener(controller);
        this.btnClose.addActionListener(controller);
        this.txtFindCharge.addKeyListener(controller);
        this.rBtnAir.addChangeListener(controller);
        this.rBtnGround.addChangeListener(controller);
        this.btnCalculateCurrentCapacity.addActionListener(controller);
        this.btnCalculateBreakdown.addActionListener(controller);
        this.cmbPhases.addActionListener(controller);
        this.jspQuantity.addChangeListener(controller);
        this.addWindowListener(controller);

        hiddenFields(true);
        this.setLocationRelativeTo(null);

    }

    //Métodos
    
    /**
     * Método para limpiar todos los campos 
     */
    public void cleanFields(){
        this.lblPotency.setText("0 W");
        this.cmbVoltage.setSelectedIndex(0);
        this.cmbMaterial.setSelectedIndex(0);
        this.cmbTemperature.setSelectedIndex(0);
        this.btnGroupRush.clearSelection();
        this.jspPowerFactor.setValue((float)((double)Double.valueOf("0.1")));
        this.cmbPhases.setSelectedIndex(0);
        this.cmbPercentageSinglePhaseMotors.setSelectedIndex(0);
        this.lblCaliberPhase.setText("");
        this.lblCaliberEarth.setText("");
        this.lblCaliberNeutral.setText("");
        this.jspQuantity.setValue((int)((double)Double.valueOf("1")));
        this.cmbCaliber.setSelectedIndex(0);
        this.cmbCalibersNeutral.setSelectedIndex(0);
        this.jspLength.setValue((float)((double)Double.valueOf("0.1")));
        this.cmbDuct.setSelectedIndex(0);
        this.jspAngle.setValue((float)((double)Double.valueOf("0")));
        this.lblBreakdownVoltage.setText("0,0 %");
        this.lblBreakdownVoltageNeutral.setText("0,0 %");
    }//Fin del método
    
    /**
     * Método para ocultar y mostrar campos según el tipo de carga seleccionada
     * @param hidden 
     */
    public void hiddenFields(boolean hidden){
        if (hidden){ 
            this.lblPotency.setVisible(true);
            this.lblStaticPotency.setVisible(true); 
            this.cmbHP.setVisible(false);
            this.cmbPercentageSinglePhaseMotors.setVisible(false);
            this.lblIn.setVisible(false);
            this.lblHP.setVisible(false);
        } else {   
            this.lblPotency.setVisible(false);
            this.lblStaticPotency.setVisible(false); 
            this.cmbHP.setVisible(true);
            this.cmbPercentageSinglePhaseMotors.setVisible(true);
            this.lblIn.setVisible(true);
            this.lblHP.setVisible(true);
        }
    }
    
    //Getters y Setters

    public JComboBox getCmbPipeline() {
        return cmbPipeline;
    }

    public void setCmbPipeline(JComboBox cmbPipeline) {
        this.cmbPipeline = cmbPipeline;
    }

    public ButtonGroup getBtnGroupRush() {
        return btnGroupRush;
    }

    public void setBtnGroupRush(ButtonGroup btnGroupRush) {
        this.btnGroupRush = btnGroupRush;
    }

    public JLabel getLblBreakdownVoltageNeutral() {
        return lblBreakdownVoltageNeutral;
    }

    public void setLblBreakdownVoltageNeutral(JLabel lblBreakdownVoltageNeutral) {
        this.lblBreakdownVoltageNeutral = lblBreakdownVoltageNeutral;
    }

    public JSpinner getJspQuantity() {
        return jspQuantity;
    }

    public void setJspQuantity(JSpinner jspQuantity) {
        this.jspQuantity = jspQuantity;
    }
    
    public JComboBox getCmbCalibersNeutral() {
        return cmbCalibersNeutral;
    }

    public void setCmbCalibersNeutral(JComboBox cmbCalibersNeutral) {
        this.cmbCalibersNeutral = cmbCalibersNeutral;
    }
    
    public JLabel getLblCaliberNeutral() {
        return lblCaliberNeutral;
    }

    public void setLblCaliberNeutral(JLabel lblCaliberNeutral) {
        this.lblCaliberNeutral = lblCaliberNeutral;
    }

    public JLabel getLblCaliberEarth() {
        return lblCaliberEarth;
    }

    public void setLblCaliberEarth(JLabel lblCaliberEarth) {
        this.lblCaliberEarth = lblCaliberEarth;
    }
    
    public JLabel getLblBreakdownVoltage() {
        return lblBreakdownVoltage;
    }

    public void setLblBreakdownVoltage(JLabel lblBreakdownVoltage) {
        this.lblBreakdownVoltage = lblBreakdownVoltage;
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
    
    public JLabel getLblCaliberPhase() {
        return lblCaliberPhase;
    }

    public void setLblCaliberPhase(JLabel lblCaliberPhase) {
        this.lblCaliberPhase = lblCaliberPhase;
    }
   
    public JSpinner getJspPowerFactor() {
        return jspPowerFactor;
    }

    public void setJspPowerFactor(JSpinner jspPowerFactor) {
        this.jspPowerFactor = jspPowerFactor;
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

    public JLabel getLblStaticPotency() {
        return lblStaticPotency;
    }

    public void setLblStaticPotency(JLabel lblStaticPotency) {
        this.lblStaticPotency = lblStaticPotency;
    }

    public JLabel getLblPotency() {
        return lblPotency;
    }

    public void setLblPotency(JLabel lblPotency) {
        this.lblPotency = lblPotency;
    }

    public Charge getCharge() {
        return charge;
    }

    public void setCharge(Charge charge) {
        this.charge = charge;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
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
    
    public JComboBox getCmbPercentageSinglePhaseMotors() {
        return cmbPercentageSinglePhaseMotors;
    }

    public void setCmbPercentageSinglePhaseMotors(JComboBox cmbPercentageSinglePhaseMotors) {
        this.cmbPercentageSinglePhaseMotors = cmbPercentageSinglePhaseMotors;
    }

    public JComboBox getCmbHP() {
        return cmbHP;
    }

    public void setCmbHP(JComboBox cmbHP) {
        this.cmbHP = cmbHP;
    }
    
    public JComboBox getCmbCaliber() {
        return cmbCaliber;
    }

    public void setCmbCaliber(JComboBox cmbCaliber) {
        this.cmbCaliber = cmbCaliber;
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
    
    public JTable getTblCharges() {
        return tblCharges;
    }

    public void setTblCharges(JTable tblCharges) {
        this.tblCharges = tblCharges;
    }

    public JTextField getTxtFindCharge() {
        return txtFindCharge;
    }

    public void setTxtFindCharge(JTextField txtFindCharge) {
        this.txtFindCharge = txtFindCharge;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
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

    public int getVoltage() {
        return voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
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

        btnGroupRush = new javax.swing.ButtonGroup();
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
        lblIn = new javax.swing.JLabel();
        cmbPercentageSinglePhaseMotors = new javax.swing.JComboBox();
        lblStaticPotency = new javax.swing.JLabel();
        lblHP = new javax.swing.JLabel();
        lblPotency = new javax.swing.JLabel();
        cmbHP = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblCaliberNeutral = new javax.swing.JLabel();
        lblCaliberEarth = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jspQuantity = new javax.swing.JSpinner();
        jLabel18 = new javax.swing.JLabel();
        cmbPipeline = new javax.swing.JComboBox<String>();
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
        jPanel7 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblCharges = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtFindCharge = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar Carga");
        setMinimumSize(new java.awt.Dimension(1000, 530));
        setModal(true);
        setPreferredSize(new java.awt.Dimension(1000, 530));
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        p1.setLayout(new java.awt.GridBagLayout());

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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 4, 0);
        jPanel4.add(jLabel15, gridBagConstraints);

        pa.setLayout(new java.awt.GridBagLayout());

        rBtnAir.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        rBtnAir.setText("Aérea");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        pa.add(rBtnAir, gridBagConstraints);

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

        lblStaticPotency.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        lblStaticPotency.setText("Potencia:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel4.add(lblStaticPotency, gridBagConstraints);

        lblHP.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        lblHP.setText("HP:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel4.add(lblHP, gridBagConstraints);

        lblPotency.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        lblPotency.setText("0 W");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 12, 0, 0);
        jPanel4.add(lblPotency, gridBagConstraints);

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

        jLabel18.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel18.setText("Tubería:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel4.add(jLabel18, gridBagConstraints);

        cmbPipeline.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbPipeline.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "EMT", "PVC" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel4.add(cmbPipeline, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        p1.add(jPanel4, gridBagConstraints);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Caída de Voltaje", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 1, 12))); // NOI18N
        java.awt.GridBagLayout jPanel8Layout = new java.awt.GridBagLayout();
        jPanel8Layout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0};
        jPanel8Layout.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
        jPanel8.setLayout(jPanel8Layout);

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
        gridBagConstraints.gridx = 1;
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
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cargas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 1, 12))); // NOI18N
        jPanel7.setMinimumSize(new java.awt.Dimension(125, 75));
        jPanel7.setLayout(new java.awt.GridBagLayout());

        tblCharges.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        tblCharges.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Carga", "Potencia(W)", "Caballo de Fuerza", "Secadora", "Cocina Eléctrica", "codigo tipo de carga", "Tipo de Carga"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCharges.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblCharges.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tblCharges);
        if (tblCharges.getColumnModel().getColumnCount() > 0) {
            tblCharges.getColumnModel().getColumn(3).setResizable(false);
            tblCharges.getColumnModel().getColumn(4).setResizable(false);
            tblCharges.getColumnModel().getColumn(5).setResizable(false);
        }

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel7.add(jScrollPane4, gridBagConstraints);

        java.awt.GridBagLayout jPanel10Layout = new java.awt.GridBagLayout();
        jPanel10Layout.columnWidths = new int[] {0, 5, 0};
        jPanel10Layout.rowHeights = new int[] {0};
        jPanel10.setLayout(jPanel10Layout);

        jLabel11.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel11.setText("Buscar:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel10.add(jLabel11, gridBagConstraints);

        txtFindCharge.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        txtFindCharge.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel10.add(txtFindCharge, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel7.add(jPanel10, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        getContentPane().add(jPanel7, gridBagConstraints);

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
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSpinner jspAngle;
    private javax.swing.JSpinner jspLength;
    private javax.swing.JSpinner jspPowerFactor;
    private javax.swing.JSpinner jspQuantity;
    private javax.swing.JLabel lblBreakdownVoltage;
    private javax.swing.JLabel lblBreakdownVoltageNeutral;
    private javax.swing.JLabel lblCaliberEarth;
    private javax.swing.JLabel lblCaliberNeutral;
    private javax.swing.JLabel lblCaliberPhase;
    private javax.swing.JLabel lblHP;
    private javax.swing.JLabel lblIn;
    private javax.swing.JLabel lblPotency;
    private javax.swing.JLabel lblStaticPotency;
    private javax.swing.JPanel p1;
    private javax.swing.JPanel pa;
    private javax.swing.JRadioButton rBtnAir;
    private javax.swing.JRadioButton rBtnGround;
    private javax.swing.JTable tblCharges;
    private javax.swing.JTextField txtFindCharge;
    // End of variables declaration//GEN-END:variables
}
