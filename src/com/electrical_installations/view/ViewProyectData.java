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
 * Clave ViewProjectDatau.
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
        int[] anchos2 = {0, 0, 100, 30, 45, 0, 0, 0};
        for (int i = 0; i < tblAreasCharges.getColumnCount(); i++) {
            tblAreasCharges.getColumnModel().getColumn(i).setPreferredWidth(anchos2[i]);
        }

    
        
        //Tabla Elevadores
        tblElevators.getTableHeader().setFont(fuente);
        tblElevators.getColumnModel().getColumn(0).setMaxWidth(0);
        tblElevators.getColumnModel().getColumn(0).setMinWidth(0);
        tblElevators.getColumnModel().getColumn(0).setPreferredWidth(0);  
        tblElevators.getColumnModel().getColumn(1).setMaxWidth(0);
        tblElevators.getColumnModel().getColumn(1).setMinWidth(0);
        tblElevators.getColumnModel().getColumn(1).setPreferredWidth(0); 
        tblElevators.getColumnModel().getColumn(2).setMaxWidth(0);
        tblElevators.getColumnModel().getColumn(2).setMinWidth(0);
        tblElevators.getColumnModel().getColumn(2).setPreferredWidth(0);               
        int[] anchos4 = {0,0,0,40,40,40,40};
        for (int i = 0; i < tblElevators.getColumnCount(); i++) {
            tblElevators.getColumnModel().getColumn(i).setPreferredWidth(anchos4[i]);
        }

        //Tabla Tipo de Elevadores
        tblTypesOfElevators.getTableHeader().setFont(fuente);
        tblTypesOfElevators.getColumnModel().getColumn(0).setMaxWidth(0);
        tblTypesOfElevators.getColumnModel().getColumn(0).setMinWidth(0);
        tblTypesOfElevators.getColumnModel().getColumn(0).setPreferredWidth(0);                
        int[] anchos5 = {0,100,60,100};
        for (int i = 0; i < tblTypesOfElevators.getColumnCount(); i++) {
            tblTypesOfElevators.getColumnModel().getColumn(i).setPreferredWidth(anchos5[i]);
        }
        
        controllerProjectData = new ControllerProjectData(this);
        controllerProjectData.fill_table_type_elevators();
        this.btnNew.addActionListener(controllerProjectData);
        this.btnModify.addActionListener(controllerProjectData);
        this.btnDelete.addActionListener(controllerProjectData);
        this.btnClose.addActionListener(controllerProjectData);
        this.btnAddCharges.addActionListener(controllerProjectData);
        this.btnAddCharge.addActionListener(controllerProjectData);
        this.btnDeleteChargesInAreas.addActionListener(controllerProjectData);
        this.btnAddElevators.addActionListener(controllerProjectData);
        this.btnDeleteElevatorInInstallation.addActionListener(controllerProjectData);
        this.txtFindAreas.addKeyListener(controllerProjectData);        
        this.txtFindAreasCharge.addKeyListener(controllerProjectData);
        this.txtFindTypesOfElevators.addKeyListener(controllerProjectData);
        this.txtFindElevators.addKeyListener(controllerProjectData);        
        this.tblArea.addMouseListener(controllerProjectData);
        this.tblAreasCharges.addMouseListener(controllerProjectData);
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

    public JMenuItem getBtnAddCharge() {
        return btnAddCharge;
    }

    public void setBtnAddCharge(JMenuItem btnAddCharge) {
        this.btnAddCharge = btnAddCharge;
    }

    public JMenuItem getBtnDeleteElevatorInInstallation() {
        return btnDeleteElevatorInInstallation;
    }

    public void setBtnDeleteElevatorInInstallation(JMenuItem btnDeleteElevatorInInstallation) {
        this.btnDeleteElevatorInInstallation = btnDeleteElevatorInInstallation;
    }

    public JMenuItem getBtnAddElevators() {
        return btnAddElevators;
    }

    public void setBtnAddElevators(JMenuItem btnAddElevators) {
        this.btnAddElevators = btnAddElevators;
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
        return btnDeleteElevatorInInstallation;
    }

    public void setBtnRemove(JMenuItem btnRemove) {
        this.btnDeleteElevatorInInstallation = btnRemove;
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

    public JPopupMenu getSubMenuElevators() {
        return subMenuElevators;
    }

    public void setSubMenuElevators(JPopupMenu subMenuElevators) {
        this.subMenuElevators = subMenuElevators;
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
 
    public JTable getTblElevators() {
        return tblElevators;
    }

    public void setTblElevators(JTable tblElevators) {
        this.tblElevators = tblElevators;
    }

    public JTable getTblTypesOfElevators() {
        return tblTypesOfElevators;
    }

    public void setTblTypesOfElevators(JTable tblTypesOfElevators) {
        this.tblTypesOfElevators = tblTypesOfElevators;
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

    public JTextField getTxtFindElevators() {
        return txtFindElevators;
    }

    public void setTxtFindElevators(JTextField txtFindElevators) {
        this.txtFindElevators = txtFindElevators;
    }

    public JTextField getTxtFindTypesOfElevators() {
        return txtFindTypesOfElevators;
    }

    public void setTxtFindTypesOfElevators(JTextField txtFindTypesOfElevators) {
        this.txtFindTypesOfElevators = txtFindTypesOfElevators;
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

        subMenu = new javax.swing.JPopupMenu();
        btnAddCharge = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        btnModify = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        btnDelete = new javax.swing.JMenuItem();
        subMenuElevators = new javax.swing.JPopupMenu();
        btnDeleteElevatorInInstallation = new javax.swing.JMenuItem();
        subMenuCharges = new javax.swing.JPopupMenu();
        btnAddCharges = new javax.swing.JMenuItem();
        subMenuChargesInAreas = new javax.swing.JPopupMenu();
        btnDeleteChargesInAreas = new javax.swing.JMenuItem();
        subMenuTypesOfElevators = new javax.swing.JPopupMenu();
        btnAddElevators = new javax.swing.JMenuItem();
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
        txtFindElevators = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblElevators = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtFindTypesOfElevators = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTypesOfElevators = new javax.swing.JTable();
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

        btnDeleteElevatorInInstallation.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnDeleteElevatorInInstallation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/eliminar.png"))); // NOI18N
        btnDeleteElevatorInInstallation.setText("Eliminar");
        subMenuElevators.add(btnDeleteElevatorInInstallation);

        btnAddCharges.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnAddCharges.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/agregar.png"))); // NOI18N
        btnAddCharges.setText("Agregar");
        subMenuCharges.add(btnAddCharges);

        btnDeleteChargesInAreas.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnDeleteChargesInAreas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/eliminar.png"))); // NOI18N
        btnDeleteChargesInAreas.setText("Eliminar");
        subMenuChargesInAreas.add(btnDeleteChargesInAreas);

        btnAddElevators.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnAddElevators.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/agregar.png"))); // NOI18N
        btnAddElevators.setText("Agregar");
        subMenuTypesOfElevators.add(btnAddElevators);

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

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Elevadores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 1, 12))); // NOI18N
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

        txtFindElevators.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        txtFindElevators.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel13.add(txtFindElevators, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel11.add(jPanel13, gridBagConstraints);

        tblElevators.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        tblElevators.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código Proyecto", "Código Tipo de Instalación", "Código de Elevador", "Nº de Personas", "Cantidad", "Potencia-W", "Voltaje-V"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
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
        tblElevators.setComponentPopupMenu(subMenuElevators);
        tblElevators.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblElevators);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel11.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelElevadores.add(jPanel11, gridBagConstraints);

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tipos de Elevadores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 1, 12))); // NOI18N
        jPanel12.setLayout(new java.awt.GridBagLayout());

        java.awt.GridBagLayout jPanel14Layout = new java.awt.GridBagLayout();
        jPanel14Layout.columnWidths = new int[] {0, 5, 0};
        jPanel14Layout.rowHeights = new int[] {0};
        jPanel14.setLayout(jPanel14Layout);

        jLabel13.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel13.setText("Buscar:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel14.add(jLabel13, gridBagConstraints);

        txtFindTypesOfElevators.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        txtFindTypesOfElevators.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel14.add(txtFindTypesOfElevators, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel12.add(jPanel14, gridBagConstraints);

        tblTypesOfElevators.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        tblTypesOfElevators.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nº de Personas", "Velocidad m/s", "Potencia-W"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTypesOfElevators.setComponentPopupMenu(subMenuTypesOfElevators);
        tblTypesOfElevators.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblTypesOfElevators);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelElevadores.add(jPanel12, gridBagConstraints);

        jTabbedPane2.addTab("Elevadores", jPanelElevadores);

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
    private javax.swing.JMenuItem btnAddElevators;
    private javax.swing.JButton btnClose;
    private javax.swing.JMenuItem btnDelete;
    private javax.swing.JMenuItem btnDeleteChargesInAreas;
    private javax.swing.JMenuItem btnDeleteElevatorInInstallation;
    private javax.swing.JMenuItem btnModify;
    private javax.swing.JButton btnNew;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JPopupMenu.Separator jSeparator1;
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
    private javax.swing.JPopupMenu subMenuElevators;
    private javax.swing.JPopupMenu subMenuTypesOfElevators;
    private javax.swing.JTable tblArea;
    private javax.swing.JTable tblAreasCharges;
    private javax.swing.JTable tblElevators;
    private javax.swing.JTable tblTypesOfElevators;
    private javax.swing.JTextField txtFindAreas;
    private javax.swing.JTextField txtFindAreasCharge;
    private javax.swing.JTextField txtFindElevators;
    private javax.swing.JTextField txtFindTypesOfElevators;
    // End of variables declaration//GEN-END:variables
}
