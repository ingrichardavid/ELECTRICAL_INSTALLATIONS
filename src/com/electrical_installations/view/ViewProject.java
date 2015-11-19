/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.view;

import com.electrical_installations.controller.ControllerProject;
import com.electrical_installations.global.method.Methods;
import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField; 

/**
 *
 * @author JRichard
 */
public class ViewProject extends javax.swing.JFrame {

    //Objetos, variables y constantes
    private final ControllerProject controllerProject;
    private final Font fuente;
    private int code;

    /**
     * Creates new form ViewProject
     */
    public ViewProject() {
        initComponents();

        code = 0;

        fuente = new Font("DejaVu Sans", Font.BOLD, 11);
        tblData.getTableHeader().setFont(fuente);
        tblData.getColumnModel().getColumn(0).setMaxWidth(0);
        tblData.getColumnModel().getColumn(0).setMinWidth(0);
        tblData.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblData.getColumnModel().getColumn(2).setMaxWidth(0);
        tblData.getColumnModel().getColumn(2).setMinWidth(0);
        tblData.getColumnModel().getColumn(2).setPreferredWidth(0);
        tblData.getColumnModel().getColumn(5).setMaxWidth(0);
        tblData.getColumnModel().getColumn(5).setMinWidth(0);
        tblData.getColumnModel().getColumn(5).setPreferredWidth(0);

        int[] anchos = {0, 280, 0, 100, 50, 0};

        for (int i = 0; i < tblData.getColumnCount(); i++) {
            tblData.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }

        controllerProject = new ControllerProject(this);
        controllerProject.fill_types_of_installation();
        controllerProject.fill_table();
        
        btnFind.addActionListener(controllerProject);
        btnClean.addActionListener(controllerProject);
        btnRegister.addActionListener(controllerProject);
        btnModify.addActionListener(controllerProject);
        btnOpen.addActionListener(controllerProject);
        btnDelete.addActionListener(controllerProject);  
        btnUser.addActionListener(controllerProject);
        btnExit.addActionListener(controllerProject);
        txtName.addKeyListener(controllerProject);
        txtFind.addKeyListener(controllerProject);
        tblData.addMouseListener(controllerProject);
        cmbFilter.addItemListener(controllerProject);
        this.addWindowListener(controllerProject);

        this.txtName.requestFocus();
        this.lblCurrentDate.setText(Methods.get_date());
        this.enable_jtextFields(true, false);
        this.enable_buttons(true, true, false, false, true);
        this.setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);
    }

    /**
     * Método para restaurar todos los campos de ViewProject
     */
    public void clean_all() {
        this.code = 0;
        this.txtName.setText(null);
        this.txtName.requestFocus();
        this.lblPowerTotal.setText("0");
        this.txtFind.setText(null);
        this.lblCurrentDate.setText(Methods.get_date());
        this.cmbType.setSelectedIndex(0);
        this.cmbFilter.setSelectedIndex(0);
        this.tabbedPane.setSelectedIndex(0);
        Methods.removeRows(tblData);
        enable_jtextFields(true, false);
        enable_buttons(true, true, false, false, true);
    }

    /**
     * Método para habilitar o deshabilitar los campos en ViewProject
     *
     * @param name
     * @param type
     */
    public void enable_jtextFields(boolean name, boolean type) {
        this.txtName.setEditable(name);
        this.cmbType.setEnabled(type);
    }//Fin del Método

    /**
     * Método para habilitar y deshabilitar los botones
     *
     * @param find
     * @param clean
     * @param register
     * @param modify
     * @param close
     */
    public void enable_buttons(boolean find, boolean clean, boolean register, boolean modify, boolean close) {
        this.btnFind.setEnabled(find);
        this.btnClean.setEnabled(clean);
        this.btnRegister.setEnabled(register);
        this.btnModify.setEnabled(modify);
        this.btnClean.setEnabled(close);
    }//Fin del método

    //Getters y Setters
   
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public JMenuItem getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(JMenuItem btnDelete) {
        this.btnDelete = btnDelete;
    }

    public ButtonGroup getBtnGroupStatus() {
        return btnGroupStatus;
    }

    public void setBtnGroupStatus(ButtonGroup btnGroupStatus) {
        this.btnGroupStatus = btnGroupStatus;
    }

    public JMenuItem getBtnOpen() {
        return btnOpen;
    }

    public void setBtnOpen(JMenuItem btnOpen) {
        this.btnOpen = btnOpen;
    }

    public JPopupMenu getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(JPopupMenu subMenu) {
        this.subMenu = subMenu;
    }
    
    
    public JButton getBtnClean() {
        return btnClean;
    }

    public void setBtnClean(JButton btnClean) {
        this.btnClean = btnClean;
    }

    public JButton getBtnExit() {
        return btnExit;
    }

    public void setBtnExit(JButton btnExit) {
        this.btnExit = btnExit;
    }

    public JButton getBtnFind() {
        return btnFind;
    }

    public void setBtnFind(JButton btnFind) {
        this.btnFind = btnFind;
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

    public JButton getBtnUser() {
        return btnUser;
    }

    public void setBtnUser(JButton btnUser) {
        this.btnUser = btnUser;
    }

    public JComboBox getCmbFilter() {
        return cmbFilter;
    }

    public void setCmbFilter(JComboBox cmbFilter) {
        this.cmbFilter = cmbFilter;
    }

    public JComboBox getCmbType() {
        return cmbType;
    }

    public void setCmbType(JComboBox cmbType) {
        this.cmbType = cmbType;
    }

    public JLabel getLblCurrentDate() {
        return lblCurrentDate;
    }

    public void setLblCurrentDate(JLabel lblCurrentDate) {
        this.lblCurrentDate = lblCurrentDate;
    }

    public JLabel getLblPowerTotal() {
        return lblPowerTotal;
    }

    public void setLblPowerTotal(JLabel lblPowerTotal) {
        this.lblPowerTotal = lblPowerTotal;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public void setTabbedPane(JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
    }

    public JPanel getTb1() {
        return tb1;
    }

    public void setTb1(JPanel tb1) {
        this.tb1 = tb1;
    }

    public JTable getTblData() {
        return tblData;
    }

    public void setTblData(JTable tblData) {
        this.tblData = tblData;
    }

    public JTextField getTxtFind() {
        return txtFind;
    }

    public void setTxtFind(JTextField txtFind) {
        this.txtFind = txtFind;
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

        subMenu = new javax.swing.JPopupMenu();
        btnOpen = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        btnDelete = new javax.swing.JMenuItem();
        btnGroupStatus = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnExit = new javax.swing.JButton();
        btnUser = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        tabbedPane = new javax.swing.JTabbedPane();
        tb1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        cmbType = new javax.swing.JComboBox();
        lblPowerTotal = new javax.swing.JLabel();
        btnFind = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        lblCurrentDate = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtFind = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cmbFilter = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        btnClean = new javax.swing.JButton();
        btnModify = new javax.swing.JButton();
        btnRegister = new javax.swing.JButton();

        btnOpen.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/abrir.png"))); // NOI18N
        btnOpen.setText("Abrir");
        subMenu.add(btnOpen);
        subMenu.add(jSeparator1);

        btnDelete.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/eliminar.png"))); // NOI18N
        btnDelete.setText("Eliminar");
        btnDelete.setToolTipText("");
        subMenu.add(btnDelete);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 500));
        getContentPane().setLayout(new javax.swing.OverlayLayout(getContentPane()));

        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] {0};
        jPanel1Layout.rowHeights = new int[] {0, 0, 0};
        jPanel1.setLayout(jPanel1Layout);

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        java.awt.GridBagLayout jPanel2Layout = new java.awt.GridBagLayout();
        jPanel2Layout.columnWidths = new int[] {0, 5, 0};
        jPanel2Layout.rowHeights = new int[] {0};
        jPanel2.setLayout(jPanel2Layout);

        btnExit.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/salir32.png"))); // NOI18N
        btnExit.setText("Salir");
        btnExit.setAutoscrolls(true);
        btnExit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        btnExit.setBorderPainted(false);
        btnExit.setContentAreaFilled(false);
        btnExit.setFocusPainted(false);
        btnExit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExit.setIconTextGap(8);
        btnExit.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnExit.setMaximumSize(new java.awt.Dimension(100, 75));
        btnExit.setMinimumSize(new java.awt.Dimension(100, 75));
        btnExit.setPreferredSize(new java.awt.Dimension(100, 75));
        btnExit.setRequestFocusEnabled(false);
        btnExit.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/salir48.png"))); // NOI18N
        btnExit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(btnExit, gridBagConstraints);

        btnUser.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        btnUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/usuario32.png"))); // NOI18N
        btnUser.setText("Usuarios");
        btnUser.setAutoscrolls(true);
        btnUser.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        btnUser.setBorderPainted(false);
        btnUser.setContentAreaFilled(false);
        btnUser.setFocusPainted(false);
        btnUser.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnUser.setIconTextGap(8);
        btnUser.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnUser.setMaximumSize(new java.awt.Dimension(100, 75));
        btnUser.setMinimumSize(new java.awt.Dimension(100, 75));
        btnUser.setPreferredSize(new java.awt.Dimension(100, 75));
        btnUser.setRequestFocusEnabled(false);
        btnUser.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/usuario48.png"))); // NOI18N
        btnUser.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel2.add(btnUser, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel1.add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new javax.swing.OverlayLayout(jPanel3));

        java.awt.GridBagLayout jPanel4Layout = new java.awt.GridBagLayout();
        jPanel4Layout.columnWidths = new int[] {0, 5, 0, 5, 0};
        jPanel4Layout.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
        jPanel4.setLayout(jPanel4Layout);

        tabbedPane.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        tabbedPane.setMinimumSize(new java.awt.Dimension(573, 100));
        tabbedPane.setPreferredSize(new java.awt.Dimension(573, 100));

        java.awt.GridBagLayout tb1Layout = new java.awt.GridBagLayout();
        tb1Layout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0};
        tb1Layout.rowHeights = new int[] {0, 0, 0};
        tb1.setLayout(tb1Layout);

        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel3.setText("Nombre:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 2, 0);
        tb1.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel4.setText("Tipo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 6, 0);
        tb1.add(jLabel4, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel7.setText("Potencia Total:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 0);
        tb1.add(jLabel7, gridBagConstraints);

        txtName.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        txtName.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtName.setMinimumSize(new java.awt.Dimension(200, 23));
        txtName.setPreferredSize(new java.awt.Dimension(200, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        tb1.add(txtName, gridBagConstraints);

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 5));

        cmbType.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        cmbType.setEnabled(false);
        jPanel5.add(cmbType);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        tb1.add(jPanel5, gridBagConstraints);

        lblPowerTotal.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        lblPowerTotal.setForeground(new java.awt.Color(0, 0, 102));
        lblPowerTotal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPowerTotal.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 5);
        tb1.add(lblPowerTotal, gridBagConstraints);

        btnFind.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/buscar.png"))); // NOI18N
        btnFind.setText("Buscar");
        btnFind.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        tb1.add(btnFind, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel5.setText("F. de Registro:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        tb1.add(jLabel5, gridBagConstraints);

        lblCurrentDate.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        lblCurrentDate.setText("jLabel6");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 5);
        tb1.add(lblCurrentDate, gridBagConstraints);

        tabbedPane.addTab("Datos Básicos", tb1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 3, 5);
        jPanel4.add(tabbedPane, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel1.setText("Buscar:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 5, 0, 0);
        jPanel4.add(jLabel1, gridBagConstraints);

        txtFind.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        txtFind.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtFind.setMinimumSize(new java.awt.Dimension(200, 23));
        txtFind.setPreferredSize(new java.awt.Dimension(200, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 5, 5, 5);
        jPanel4.add(txtFind, gridBagConstraints);

        java.awt.GridBagLayout jPanel6Layout = new java.awt.GridBagLayout();
        jPanel6Layout.columnWidths = new int[] {0, 5, 0};
        jPanel6Layout.rowHeights = new int[] {0};
        jPanel6.setLayout(jPanel6Layout);

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel2.setText("Por:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel6.add(jLabel2, gridBagConstraints);

        cmbFilter.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        cmbFilter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nombre", "Tipo" }));
        cmbFilter.setMinimumSize(new java.awt.Dimension(100, 23));
        cmbFilter.setPreferredSize(new java.awt.Dimension(100, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel6.add(cmbFilter, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 5, 5);
        jPanel4.add(jPanel6, gridBagConstraints);

        jScrollPane1.setMinimumSize(new java.awt.Dimension(300, 150));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(300, 150));

        tblData.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        tblData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Proyecto", "Código Tipo", "Tipo de Proyecto", "Potencia Total", "Fecha de Registro"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblData.setComponentPopupMenu(subMenu);
        tblData.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblData.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblData);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel4.add(jScrollPane1, gridBagConstraints);

        java.awt.GridBagLayout jPanel7Layout = new java.awt.GridBagLayout();
        jPanel7Layout.columnWidths = new int[] {0, 5, 0, 5, 0};
        jPanel7Layout.rowHeights = new int[] {0};
        jPanel7.setLayout(jPanel7Layout);

        btnClean.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        btnClean.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/limpiar.png"))); // NOI18N
        btnClean.setMnemonic('L');
        btnClean.setText("Limpiar");
        btnClean.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        jPanel7.add(btnClean, gridBagConstraints);

        btnModify.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        btnModify.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/modificar.png"))); // NOI18N
        btnModify.setMnemonic('M');
        btnModify.setText("Modificar");
        btnModify.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel7.add(btnModify, gridBagConstraints);

        btnRegister.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        btnRegister.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/registrar.png"))); // NOI18N
        btnRegister.setMnemonic('R');
        btnRegister.setText("Registrar");
        btnRegister.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel7.add(btnRegister, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(jPanel7, gridBagConstraints);

        jPanel3.add(jPanel4);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel3, gridBagConstraints);

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClean;
    private javax.swing.JMenuItem btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnFind;
    private javax.swing.ButtonGroup btnGroupStatus;
    private javax.swing.JButton btnModify;
    private javax.swing.JMenuItem btnOpen;
    private javax.swing.JButton btnRegister;
    private javax.swing.JButton btnUser;
    private javax.swing.JComboBox cmbFilter;
    private javax.swing.JComboBox cmbType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel lblCurrentDate;
    private javax.swing.JLabel lblPowerTotal;
    private javax.swing.JPopupMenu subMenu;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JPanel tb1;
    private javax.swing.JTable tblData;
    private javax.swing.JTextField txtFind;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
