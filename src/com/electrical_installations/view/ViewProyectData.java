/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.view;

import com.electrical_installations.controller.ControllerProjectData;
import com.electrical_installations.model.entity.Project;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Clave ViewProjectData.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-07-30
 */
public class ViewProyectData extends javax.swing.JDialog {

    //Objectos, variables y constantes
    public final ControllerProjectData controllerProjectData;
    private final Font fuente;
    private int projectCode;
    private int type_installation_code;

    /**
     * Constructor de ViewProjectData, por ser subclase de JDialog recibe como parámetro el padre y true en caso de que sea modal.
     * @param parent
     * @param modal 
     */    
    public ViewProyectData(JFrame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        //Tabla Área
        fuente = new Font("DejaVu Sans", Font.BOLD, 11);
        tblArea.getTableHeader().setFont(fuente);
        tblArea.getColumnModel().getColumn(0).setMaxWidth(0);
        tblArea.getColumnModel().getColumn(0).setMinWidth(0);
        tblArea.getColumnModel().getColumn(0).setPreferredWidth(0);                
        int[] anchos = {0, 100,70,50,50};
        for (int i = 0; i < tblArea.getColumnCount(); i++) {
            tblArea.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        
        //Tabla de Alimentador Principal
        tblInstallationMainFeeder.getTableHeader().setFont(fuente);
        tblInstallationMainFeeder.getColumnModel().getColumn(0).setMaxWidth(0);
        tblInstallationMainFeeder.getColumnModel().getColumn(0).setMinWidth(0);
        tblInstallationMainFeeder.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblInstallationMainFeeder.getColumnModel().getColumn(1).setMaxWidth(0);
        tblInstallationMainFeeder.getColumnModel().getColumn(1).setMinWidth(0);
        tblInstallationMainFeeder.getColumnModel().getColumn(1).setPreferredWidth(0); 
        tblInstallationMainFeeder.getColumnModel().getColumn(2).setMaxWidth(0);
        tblInstallationMainFeeder.getColumnModel().getColumn(2).setMinWidth(0);
        tblInstallationMainFeeder.getColumnModel().getColumn(2).setPreferredWidth(0);  
        tblInstallationMainFeeder.getColumnModel().getColumn(7).setMaxWidth(0);
        tblInstallationMainFeeder.getColumnModel().getColumn(7).setMinWidth(0);
        tblInstallationMainFeeder.getColumnModel().getColumn(7).setPreferredWidth(0);  
        int[] anchos2 = {0, 0, 0, 100, 20, 20, 20,0};
        for (int i = 0; i < tblInstallationMainFeeder.getColumnCount(); i++) {
            tblInstallationMainFeeder.getColumnModel().getColumn(i).setPreferredWidth(anchos2[i]);
        }

        //Tabla de Cargas Eléctricas asignadas a Áreas
        tblAreasCharges.getTableHeader().setFont(fuente);
        tblAreasCharges.getColumnModel().getColumn(0).setMaxWidth(0);
        tblAreasCharges.getColumnModel().getColumn(0).setMinWidth(0);
        tblAreasCharges.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblAreasCharges.getColumnModel().getColumn(1).setMaxWidth(0);
        tblAreasCharges.getColumnModel().getColumn(1).setMinWidth(0);
        tblAreasCharges.getColumnModel().getColumn(1).setPreferredWidth(0); 
        tblAreasCharges.getColumnModel().getColumn(5).setMaxWidth(0);
        tblAreasCharges.getColumnModel().getColumn(5).setMinWidth(0);
        tblAreasCharges.getColumnModel().getColumn(5).setPreferredWidth(0); 
        tblAreasCharges.getColumnModel().getColumn(6).setMaxWidth(0);
        tblAreasCharges.getColumnModel().getColumn(6).setMinWidth(0);
        tblAreasCharges.getColumnModel().getColumn(6).setPreferredWidth(0); 
        tblAreasCharges.getColumnModel().getColumn(7).setMaxWidth(0);
        tblAreasCharges.getColumnModel().getColumn(7).setMinWidth(0);
        tblAreasCharges.getColumnModel().getColumn(7).setPreferredWidth(0); 
        int[] anchos3 = {0, 0, 100, 30, 45, 0, 0, 0};
        for (int i = 0; i < tblAreasCharges.getColumnCount(); i++) {
            tblAreasCharges.getColumnModel().getColumn(i).setPreferredWidth(anchos3[i]);
        }

        //Tabla Motores en la Instalación 
        tblInstallationEngines.getTableHeader().setFont(fuente);
        tblInstallationEngines.getColumnModel().getColumn(0).setMaxWidth(0);
        tblInstallationEngines.getColumnModel().getColumn(0).setMinWidth(0);
        tblInstallationEngines.getColumnModel().getColumn(0).setPreferredWidth(0);   
        int[] anchos5 = {0,400,30,5,10,5,5};
        for (int i = 0; i < tblInstallationEngines.getColumnCount(); i++) {
            tblInstallationEngines.getColumnModel().getColumn(i).setPreferredWidth(anchos5[i]);
        }
        
        controllerProjectData = new ControllerProjectData(this);
        
        this.btnCalculateMainFeeder.addActionListener(controllerProjectData);
        this.btnSubAlimentador.addActionListener(controllerProjectData);
        this.btnLightingCircuit.addActionListener(controllerProjectData);
        this.btnNew.addActionListener(controllerProjectData);
        this.btnModify.addActionListener(controllerProjectData);
        this.btnDelete.addActionListener(controllerProjectData);
        this.btnClose.addActionListener(controllerProjectData);
        this.btnAddCharges.addActionListener(controllerProjectData);
        this.btnAddCharge.addActionListener(controllerProjectData);
        this.btnDeleteChargesInAreas.addActionListener(controllerProjectData);
        this.btnAddInstallationEngines.addActionListener(controllerProjectData);
        this.txtFindAreas.addKeyListener(controllerProjectData);    
        this.txtFindMainFeeder.addKeyListener(controllerProjectData);
        this.txtFindAreasCharge.addKeyListener(controllerProjectData); 
        this.txtFindInstallationEngines.addKeyListener(controllerProjectData);        
        this.tblArea.addMouseListener(controllerProjectData);
        this.tblAreasCharges.addMouseListener(controllerProjectData); 
        this.txtFindInstallationEngines.addKeyListener(controllerProjectData);
        this.btnDeleteInstallationMotor.addActionListener(controllerProjectData);
        this.addWindowListener(controllerProjectData);

        this.btnNew.requestFocus();
        this.setLocationRelativeTo(null);
        
    }    
    
    //Métodos
    
    /**
     * Método para llenar datos básicos de un Proyecto
     * @param project 
     */
    public void fill_basic_data(Project project){
        this.projectCode = project.getCode();
        this.lblName.setText(project.getName());
        this.type_installation_code = project.getTypeOfInstallation().getCode();
        this.lblType.setText(project.getTypeOfInstallation().getName());
        this.lblDate.setText(project.getRegistration_date());
        this.lblPowerTotal.setText(String.valueOf(project.getPowerTotal()));
    }//Fin del método 
        
    
    
    //Getters y Setters  

    public JMenuItem getBtnCalculateMainFeeder() {
        return btnCalculateMainFeeder;
    }

    public void setBtnCalculateMainFeeder(JMenuItem btnCalculateMainFeeder) {
        this.btnCalculateMainFeeder = btnCalculateMainFeeder;
    } 
    
    public JTable getTblInstallationMainFeeder() {
        return tblInstallationMainFeeder;
    }

    public void setTblInstallationMainFeeder(JTable tblInstallationMainFeeder) {
        this.tblInstallationMainFeeder = tblInstallationMainFeeder;
    }

    public JTextField getTxtFindMainFeeder() {
        return txtFindMainFeeder;
    }

    public void setTxtFindMainFeeder(JTextField txtFindMainFeeder) {
        this.txtFindMainFeeder = txtFindMainFeeder;
    }
    
    public JMenuItem getBtnAddCharge() {
        return btnAddCharge;
    }

    public void setBtnAddCharge(JMenuItem btnAddCharge) {
        this.btnAddCharge = btnAddCharge;
    }

    public JMenuItem getBtnDeleteInstallationMotor() {
        return btnDeleteInstallationMotor;
    }

    public void setBtnDeleteInstallationMotor(JMenuItem btnDeleteInstallationMotor) {
        this.btnDeleteInstallationMotor = btnDeleteInstallationMotor;
    }
    
    public JMenuItem getBtnDeleteChargesInAreas() {
        return btnDeleteChargesInAreas;
    }

    public void setBtnDeleteChargesInAreas(JMenuItem btnDeleteChargesInAreas) {
        this.btnDeleteChargesInAreas = btnDeleteChargesInAreas;
    }
    
    public int getType_installation_code() {
        return type_installation_code;
    }

    public JMenuItem getBtnAddCharges() {
        return btnAddCharges;
    }

    public void setBtnAddCharges(JMenuItem btnAddCharges) {
        this.btnAddCharges = btnAddCharges;
    }

    public void setType_installation_code(int type_installation_code) {
        this.type_installation_code = type_installation_code;
    }
    
    public int getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(int projectCode) {
        this.projectCode = projectCode;
    }

    public JButton getBtnNew() {
        return btnNew;
    }

    public void setBtnNew(JButton btnNew) {
        this.btnNew = btnNew;
    }
    
    public JPopupMenu getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(JPopupMenu SubMenu) {
        this.subMenu = SubMenu;
    }

    public JButton getBtnClose() {
        return btnClose;
    }

    public void setBtnClose(JButton btnClose) {
        this.btnClose = btnClose;
    }

    public JMenuItem getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(JMenuItem btnDelete) {
        this.btnDelete = btnDelete;
    }

    public JMenuItem getBtnModify() {
        return btnModify;
    }

    public void setBtnModify(JMenuItem btnModify) {
        this.btnModify = btnModify;
    }

    public JMenuItem getBtnRemove() {
        return btnDeleteInstallationMotor;
    }

    public void setBtnRemove(JMenuItem btnRemove) {
        this.btnDeleteInstallationMotor = btnRemove;
    }

    public JLabel getLblAmperage() {
        return lblAmperage;
    }

    public void setLblAmperage(JLabel lblAmperage) {
        this.lblAmperage = lblAmperage;
    }

    public JLabel getLblDate() {
        return lblDate;
    }

    public void setLblDate(JLabel lblDate) {
        this.lblDate = lblDate;
    }

    public JLabel getLblName() {
        return lblName;
    }

    public void setLblName(JLabel lblName) {
        this.lblName = lblName;
    }

    public JLabel getLblPowerTotal() {
        return lblPowerTotal;
    }

    public void setLblPowerTotal(JLabel lblPowerTotal) {
        this.lblPowerTotal = lblPowerTotal;
    }

    public JLabel getLblType() {
        return lblType;
    }

    public void setLblType(JLabel lblType) {
        this.lblType = lblType;
    }

    public JPopupMenu getSubMenuMotors() {
        return subMenuMotors;
    }

    public void setSubMenuMotors(JPopupMenu subMenuMotors) {
        this.subMenuMotors = subMenuMotors;
    }

    public JTable getTblArea() {
        return tblArea;
    }

    public void setTblArea(JTable tblArea) {
        this.tblArea = tblArea;
    }

    public JTable getTblAreasCharges() {
        return tblAreasCharges;
    }

    public void setTblAreasCharges(JTable tblAreasCharges) {
        this.tblAreasCharges = tblAreasCharges;
    }
 
    public JTextField getTxtFindAreas() {
        return txtFindAreas;
    }

    public void setTxtFindAreas(JTextField txtFindAreas) {
        this.txtFindAreas = txtFindAreas;
    }

    public JTextField getTxtFindAreasCharge() {
        return txtFindAreasCharge;
    }

    public void setTxtFindAreasCharge(JTextField txtFindAreasCharge) {
        this.txtFindAreasCharge = txtFindAreasCharge;
    } 

    public JButton getBtnAddInstallationEngines() {
        return btnAddInstallationEngines;
    }

    public void setBtnAddInstallationEngines(JButton btnAddInstallationEngines) {
        this.btnAddInstallationEngines = btnAddInstallationEngines;
    }

    public JTable getTblInstallationEngines() {
        return tblInstallationEngines;
    }

    public void setTblInstallationEngines(JTable tblInstallationEngines) {
        this.tblInstallationEngines = tblInstallationEngines;
    }

    public JTextField getTxtFindInstallationEngines() {
        return txtFindInstallationEngines;
    }

    public void setTxtFindInstallationEngines(JTextField txtFindInstallationEngines) {
        this.txtFindInstallationEngines = txtFindInstallationEngines;
    }

    public JMenuItem getBtnSubAlimentador() {
        return btnSubAlimentador;
    }

    public void setBtnSubAlimentador(JMenuItem btnSubAlimentador) {
        this.btnSubAlimentador = btnSubAlimentador;
    }

    public JMenuItem getBtnLightingCircuit() {
        return btnLightingCircuit;
    }

    public void setBtnLightingCircuit(JMenuItem btnLightingCircuit) {
        this.btnLightingCircuit = btnLightingCircuit;
    }//fin Getters y Setters
     
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        subMenu = new javax.swing.JPopupMenu();
        btnAddCharge = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        btnModify = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        btnDelete = new javax.swing.JMenuItem();
        subMenuMotors = new javax.swing.JPopupMenu();
        btnLightingCircuit = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        btnSubAlimentador = new javax.swing.JMenuItem();
        subMenuCharges = new javax.swing.JPopupMenu();
        btnAddCharges = new javax.swing.JMenuItem();
        subMenuChargesInAreas = new javax.swing.JPopupMenu();
        btnDeleteChargesInAreas = new javax.swing.JMenuItem();
        subMenuMainFeeder = new javax.swing.JPopupMenu();
        btnCalculateMainFeeder = new javax.swing.JMenuItem();
        subMenuMotosDelete = new javax.swing.JPopupMenu();
        btnDeleteInstallationMotor = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblType = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblPowerTotal = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanelConfigArea = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblArea = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtFindAreas = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnNew = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblAreasCharges = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtFindAreasCharge = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        lblAmperage = new javax.swing.JLabel();
        jPanelElevadores = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtFindInstallationEngines = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInstallationEngines = new javax.swing.JTable();
        btnAddInstallationEngines = new javax.swing.JButton();
        jPanelMainFeeder = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtFindMainFeeder = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblInstallationMainFeeder = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnClose = new javax.swing.JButton();

        btnAddCharge.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnAddCharge.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/agregar.png"))); // NOI18N
        btnAddCharge.setText("Agregar Carga");
        subMenu.add(btnAddCharge);
        subMenu.add(jSeparator1);

        btnModify.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnModify.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/modificar.png"))); // NOI18N
        btnModify.setText("Modificar");
        subMenu.add(btnModify);
        subMenu.add(jSeparator3);

        btnDelete.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/eliminar.png"))); // NOI18N
        btnDelete.setText("Eliminar");
        subMenu.add(btnDelete);

        btnLightingCircuit.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnLightingCircuit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/Iluminaria.png"))); // NOI18N
        btnLightingCircuit.setText("Circuito de Iluminación");
        btnLightingCircuit.setToolTipText("");
        subMenuMotors.add(btnLightingCircuit);
        subMenuMotors.add(jSeparator2);

        btnSubAlimentador.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnSubAlimentador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/subAlimentador.png"))); // NOI18N
        btnSubAlimentador.setText("Sub-Alimentador");
        subMenuMotors.add(btnSubAlimentador);

        btnAddCharges.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnAddCharges.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/agregar.png"))); // NOI18N
        btnAddCharges.setText("Agregar");
        subMenuCharges.add(btnAddCharges);

        btnDeleteChargesInAreas.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnDeleteChargesInAreas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/eliminar.png"))); // NOI18N
        btnDeleteChargesInAreas.setText("Eliminar");
        subMenuChargesInAreas.add(btnDeleteChargesInAreas);

        btnCalculateMainFeeder.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnCalculateMainFeeder.setText("Calcular Alimentador Principal");
        subMenuMainFeeder.add(btnCalculateMainFeeder);

        btnDeleteInstallationMotor.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnDeleteInstallationMotor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/eliminar.png"))); // NOI18N
        btnDeleteInstallationMotor.setText("Eliminar");
        subMenuMotosDelete.add(btnDeleteInstallationMotor);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 600));
        setPreferredSize(new java.awt.Dimension(900, 600));
        getContentPane().setLayout(new javax.swing.OverlayLayout(getContentPane()));

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jTabbedPane1.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N

        java.awt.GridBagLayout jPanel2Layout = new java.awt.GridBagLayout();
        jPanel2Layout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0};
        jPanel2Layout.rowHeights = new int[] {0, 0, 0};
        jPanel2.setLayout(jPanel2Layout);

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel1.setText("Proyecto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        jPanel2.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel2.setText("Tipo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        jPanel2.add(jLabel2, gridBagConstraints);

        lblName.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        jPanel2.add(lblName, gridBagConstraints);

        lblType.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        jPanel2.add(lblType, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel7.setText("Potencia Total:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        jPanel2.add(jLabel7, gridBagConstraints);

        lblPowerTotal.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel2.add(lblPowerTotal, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel5.setText("F. de Registro:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        jPanel2.add(jLabel5, gridBagConstraints);

        lblDate.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel2.add(lblDate, gridBagConstraints);

        jTabbedPane1.addTab("Datos del Proyecto", jPanel2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jTabbedPane1, gridBagConstraints);

        jTabbedPane2.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N

        jPanelConfigArea.setLayout(new java.awt.GridBagLayout());

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Áreas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 1, 12))); // NOI18N
        jPanel7.setMinimumSize(new java.awt.Dimension(75, 75));
        jPanel7.setLayout(new java.awt.GridBagLayout());

        tblArea.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        tblArea.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Áreas", "Potencia(W)", "Neutro", "Cantidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblArea.setComponentPopupMenu(subMenu);
        tblArea.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblArea.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(tblArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel7.add(jScrollPane5, gridBagConstraints);

        java.awt.GridBagLayout jPanel8Layout = new java.awt.GridBagLayout();
        jPanel8Layout.columnWidths = new int[] {0, 5, 0};
        jPanel8Layout.rowHeights = new int[] {0};
        jPanel8.setLayout(jPanel8Layout);

        jLabel9.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel9.setText("Buscar:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel8.add(jLabel9, gridBagConstraints);

        txtFindAreas.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        txtFindAreas.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel8.add(txtFindAreas, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel7.add(jPanel8, gridBagConstraints);

        java.awt.GridBagLayout jPanel3Layout = new java.awt.GridBagLayout();
        jPanel3Layout.columnWidths = new int[] {0};
        jPanel3Layout.rowHeights = new int[] {0};
        jPanel3.setLayout(jPanel3Layout);

        btnNew.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/agregar.png"))); // NOI18N
        btnNew.setMnemonic('A');
        btnNew.setText("Agregar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel3.add(btnNew, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel7.add(jPanel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelConfigArea.add(jPanel7, gridBagConstraints);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cargas de Áreas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 1, 12))); // NOI18N
        jPanel5.setMinimumSize(new java.awt.Dimension(250, 75));
        jPanel5.setPreferredSize(new java.awt.Dimension(250, 100));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        tblAreasCharges.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        tblAreasCharges.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código de Área", "Código de Carga", "Carga", "Cantidad", "Potencia(W)", "Código Fase", "Código Tipo de Carga", "Tipo de Carga"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAreasCharges.setComponentPopupMenu(subMenuChargesInAreas);
        tblAreasCharges.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(tblAreasCharges);
        if (tblAreasCharges.getColumnModel().getColumnCount() > 0) {
            tblAreasCharges.getColumnModel().getColumn(5).setResizable(false);
            tblAreasCharges.getColumnModel().getColumn(6).setResizable(false);
            tblAreasCharges.getColumnModel().getColumn(7).setResizable(false);
        }

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel5.add(jScrollPane6, gridBagConstraints);

        java.awt.GridBagLayout jPanel9Layout = new java.awt.GridBagLayout();
        jPanel9Layout.columnWidths = new int[] {0, 5, 0};
        jPanel9Layout.rowHeights = new int[] {0};
        jPanel9.setLayout(jPanel9Layout);

        jLabel10.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel10.setText("Buscar:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel9.add(jLabel10, gridBagConstraints);

        txtFindAreasCharge.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        txtFindAreasCharge.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel9.add(txtFindAreasCharge, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel5.add(jPanel9, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelConfigArea.add(jPanel5, gridBagConstraints);

        java.awt.GridBagLayout jPanel15Layout = new java.awt.GridBagLayout();
        jPanel15Layout.columnWidths = new int[] {0, 5, 0};
        jPanel15Layout.rowHeights = new int[] {0};
        jPanel15.setLayout(jPanel15Layout);

        lblAmperage.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel15.add(lblAmperage, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelConfigArea.add(jPanel15, gridBagConstraints);

        jTabbedPane2.addTab("Configuración de Áreas", jPanelConfigArea);

        java.awt.GridBagLayout jPanelElevadoresLayout = new java.awt.GridBagLayout();
        jPanelElevadoresLayout.columnWidths = new int[] {0, 5, 0};
        jPanelElevadoresLayout.rowHeights = new int[] {0};
        jPanelElevadores.setLayout(jPanelElevadoresLayout);

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Motores en la Instalación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 1, 12))); // NOI18N
        jPanel11.setLayout(new java.awt.GridBagLayout());

        java.awt.GridBagLayout jPanel13Layout = new java.awt.GridBagLayout();
        jPanel13Layout.columnWidths = new int[] {0, 5, 0};
        jPanel13Layout.rowHeights = new int[] {0};
        jPanel13.setLayout(jPanel13Layout);

        jLabel12.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel12.setText("Buscar:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel13.add(jLabel12, gridBagConstraints);

        txtFindInstallationEngines.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        txtFindInstallationEngines.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtFindInstallationEngines.setMinimumSize(new java.awt.Dimension(300, 22));
        txtFindInstallationEngines.setPreferredSize(new java.awt.Dimension(300, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel13.add(txtFindInstallationEngines, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel11.add(jPanel13, gridBagConstraints);

        tblInstallationEngines.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        tblInstallationEngines.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripción", "Fase", "Hp", "Intensidad", "Breaker", "Cantidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblInstallationEngines.setComponentPopupMenu(subMenuMotosDelete);
        tblInstallationEngines.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblInstallationEngines);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel11.add(jScrollPane1, gridBagConstraints);

        btnAddInstallationEngines.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnAddInstallationEngines.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/agregar.png"))); // NOI18N
        btnAddInstallationEngines.setText("Agregar");
        btnAddInstallationEngines.setComponentPopupMenu(subMenuMotors);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel11.add(btnAddInstallationEngines, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelElevadores.add(jPanel11, gridBagConstraints);

        jTabbedPane2.addTab("Motores", jPanelElevadores);

        jPanelMainFeeder.setLayout(new java.awt.GridBagLayout());

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Alimentador Principal", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 1, 12))); // NOI18N
        jPanel12.setLayout(new java.awt.GridBagLayout());

        jPanel14.setLayout(new java.awt.GridBagLayout());

        jLabel13.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel13.setText("Buscar:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel14.add(jLabel13, gridBagConstraints);

        txtFindMainFeeder.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        txtFindMainFeeder.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtFindMainFeeder.setMinimumSize(new java.awt.Dimension(300, 22));
        txtFindMainFeeder.setPreferredSize(new java.awt.Dimension(300, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel14.add(txtFindMainFeeder, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel12.add(jPanel14, gridBagConstraints);

        tblInstallationMainFeeder.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        tblInstallationMainFeeder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Proyecto Codigo", "Proyecto Tipo", "Tipo Carga Codigo", "Carga", "Potencia", "Intensidad", "Cantidad", "Neutro"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblInstallationMainFeeder.setComponentPopupMenu(subMenuMainFeeder);
        tblInstallationMainFeeder.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblInstallationMainFeeder);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelMainFeeder.add(jPanel12, gridBagConstraints);

        jTabbedPane2.addTab("Alimentador Principal", jPanelMainFeeder);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jTabbedPane2, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        btnClose.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/cerrar.png"))); // NOI18N
        btnClose.setMnemonic('C');
        btnClose.setText("Cerrar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(btnClose, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jPanel4, gridBagConstraints);

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem btnAddCharge;
    private javax.swing.JMenuItem btnAddCharges;
    private javax.swing.JButton btnAddInstallationEngines;
    private javax.swing.JMenuItem btnCalculateMainFeeder;
    private javax.swing.JButton btnClose;
    private javax.swing.JMenuItem btnDelete;
    private javax.swing.JMenuItem btnDeleteChargesInAreas;
    private javax.swing.JMenuItem btnDeleteInstallationMotor;
    private javax.swing.JMenuItem btnLightingCircuit;
    private javax.swing.JMenuItem btnModify;
    private javax.swing.JButton btnNew;
    private javax.swing.JMenuItem btnSubAlimentador;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelConfigArea;
    private javax.swing.JPanel jPanelElevadores;
    private javax.swing.JPanel jPanelMainFeeder;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel lblAmperage;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPowerTotal;
    private javax.swing.JLabel lblType;
    private javax.swing.JPopupMenu subMenu;
    private javax.swing.JPopupMenu subMenuCharges;
    private javax.swing.JPopupMenu subMenuChargesInAreas;
    private javax.swing.JPopupMenu subMenuMainFeeder;
    private javax.swing.JPopupMenu subMenuMotors;
    private javax.swing.JPopupMenu subMenuMotosDelete;
    private javax.swing.JTable tblArea;
    private javax.swing.JTable tblAreasCharges;
    private javax.swing.JTable tblInstallationEngines;
    private javax.swing.JTable tblInstallationMainFeeder;
    private javax.swing.JTextField txtFindAreas;
    private javax.swing.JTextField txtFindAreasCharge;
    private javax.swing.JTextField txtFindInstallationEngines;
    private javax.swing.JTextField txtFindMainFeeder;
    // End of variables declaration//GEN-END:variables
}
