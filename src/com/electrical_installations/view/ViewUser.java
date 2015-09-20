/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.view;

import com.electrical_installations.controller.ControllerUser;
import com.electrical_installations.global.method.Methods;
import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Clave ViewUser.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-07-21
 */

public final class ViewUser extends javax.swing.JDialog {

    //Objetos, variables y constantes
    private final ControllerUser controllerUser;
    private final Font fuente;

    /**
     * Constructor de ViewUser, por ser subclase de JDialog recibe como parámetro el padre y true en caso de que sea modal.
     * @param parent
     * @param modal 
     */    
    public ViewUser(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
               fuente = new Font("DejaVu Sans", Font.BOLD, 11);
        tblData.getTableHeader().setFont(fuente);  
//        tblData.getColumnModel().getColumn(0).setMaxWidth(0);
//        tblData.getColumnModel().getColumn(0).setMinWidth(0);
//        tblData.getColumnModel().getColumn(0).setPreferredWidth(0);
                
        int[] anchos = {20,100,100,50};
        
        for (int i = 0; i < tblData.getColumnCount(); i++){
        
            tblData.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        
        }
        
        controllerUser = new ControllerUser(this);
        btnFind.addActionListener(controllerUser);
        btnClean.addActionListener(controllerUser);
        btnRegister.addActionListener(controllerUser);
        btnModify.addActionListener(controllerUser);
        btnSaveChanges.addActionListener(controllerUser);
        btnDelete.addActionListener(controllerUser);
        btnClose.addActionListener(controllerUser);
        txtDni.addKeyListener(controllerUser);
        txtName.addKeyListener(controllerUser);
        txtLastName.addKeyListener(controllerUser);
        txtAddress.addKeyListener(controllerUser);
        txtPhone.addKeyListener(controllerUser);
        txtUserName.addKeyListener(controllerUser);
        txtPassword.addKeyListener(controllerUser);
        txtConfirmPassword.addKeyListener(controllerUser);
        txtFind.addKeyListener(controllerUser);
        tblData.addMouseListener(controllerUser);
        radioBtnActive.addChangeListener(controllerUser);
        radioBtnInactive.addChangeListener(controllerUser);
        cmbFilter.addItemListener(controllerUser);
        this.addWindowListener(controllerUser);
        
        this.lblPasswordName.setText("Clave:");
        this.lblCurrentDate.setText(Methods.get_date());
        this.txtPreviousPassword.setVisible(false);
        this.lblHidePassword.setVisible(false);
        this.btnSaveChanges.setVisible(false);
        this.cmbNationality.requestFocus();
        this.radioBtnActive.setSelected(true);
        this.enable_jtextFields(true, true, false, false, false, false, false, false, false,false,false);
        this.enable_buttons(true, true, false, false, true);
        this.setLocationRelativeTo(null);
    }
    
    /**
     * Método para restaurar todos los campos de ViewUser
     */
    public void clean_all(){
        this.cmbNationality.setSelectedIndex(0);
        this.txtDni.setText(null);
        this.txtName.setText(null);
        this.txtLastName.setText(null);
        this.txtAddress.setText(null);
        this.txtPassword.setText(null);
        this.txtUserName.setText(null);
        this.txtFind.setText(null);
        this.txtConfirmPassword.setText(null);
        this.txtPhone.setText(null);
        this.lblCurrentDate.setText(Methods.get_date());
        this.cmbFilter.setSelectedIndex(0);
        this.lblPasswordName.setText("Clave:");
        this.radioBtnActive.setSelected(true);
        this.txtPreviousPassword.setText(null);
        this.txtPreviousPassword.setVisible(false);
        this.lblHidePassword.setVisible(false);
        this.btnSaveChanges.setVisible(false);
        this.tabbedPane.setSelectedIndex(0);
        this.cmbNationality.requestFocus();
        Methods.removeRows(tblData);
        enable_jtextFields(true, true, false, false, false, false, false, false, false,false,false);
        enable_buttons(true, true, false, false, true);
    }
    
    /**
     * Método para habilitar o deshabilitar los campos en ViewUser
     * @param nationality
     * @param dni
     * @param name
     * @param lastName
     * @param address
     * @param phone
     * @param userName
     * @param password
     * @param confirmPassword 
     * @param radioBtnActive 
     * @param radioBtnInactive 
     */
    public void enable_jtextFields(boolean nationality, boolean dni, boolean name, boolean lastName, boolean address, boolean phone,
            boolean userName, boolean password, boolean confirmPassword,boolean radioBtnActive, boolean radioBtnInactive){
        this.txtDni.setEditable(dni);
        this.txtName.setEditable(name);
        this.txtLastName.setEditable(lastName);
        this.txtAddress.setEditable(address);
        this.txtPhone.setEditable(phone);
        this.txtUserName.setEditable(userName);
        this.txtPassword.setEditable(password);
        this.txtConfirmPassword.setEditable(confirmPassword);
        this.cmbNationality.setEnabled(nationality);
        this.radioBtnActive.setEnabled(radioBtnActive);
        this.radioBtnInactive.setEnabled(radioBtnInactive);
    }//Fin del Método
    
    /**
     * Método para habilitar y deshabilitar los botones
     * @param find
     * @param clean
     * @param register
     * @param modify
     * @param close 
     */
    public void enable_buttons(boolean find, boolean clean, boolean register, boolean modify, boolean close){
        this.btnFind.setEnabled(find);
        this.btnClean.setEnabled(clean);
        this.btnRegister.setEnabled(register);
        this.btnModify.setEnabled(modify);
        this.btnClose.setEnabled(close);
    }//Fin del método
    
    //Getters y Setters

    public JButton getBtnSaveChanges() {
        return btnSaveChanges;
    }

    public void setBtnSaveChanges(JButton saveChanges) {
        this.btnSaveChanges = saveChanges;
    }
    
    public ButtonGroup getBtnGroupStatus() {
        return btnGroupStatus;
    }

    public void setBtnGroupStatus(ButtonGroup btnGroupStatus) {
        this.btnGroupStatus = btnGroupStatus;
    }

    public JRadioButton getRadioBtnActive() {
        return radioBtnActive;
    }

    public void setRadioBtnActive(JRadioButton radioBtnActive) {
        this.radioBtnActive = radioBtnActive;
    }

    public JRadioButton getRadioBtnInactive() {
        return radioBtnInactive;
    }

    public void setRadioBtnInactive(JRadioButton radioBtnInactive) {
        this.radioBtnInactive = radioBtnInactive;
    }
        
    public JButton getBtnClean() {
        return btnClean;
    }

    public void setBtnClean(JButton btnClean) {
        this.btnClean = btnClean;
    }

    public JButton getBtnClose() {
        return btnClose;
    }

    public void setBtnClose(JButton btnClose) {
        this.btnClose = btnClose;
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

    public JComboBox getCmbFilter() {
        return cmbFilter;
    }

    public void setCmbFilter(JComboBox cmbFilter) {
        this.cmbFilter = cmbFilter;
    }

    public JComboBox getCmbNationality() {
        return cmbNationality;
    }

    public void setCmbNationality(JComboBox cmbNationality) {
        this.cmbNationality = cmbNationality;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public void setTabbedPane(JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
    }

    public JLabel getLblCurrentDate() {
        return lblCurrentDate;
    }

    public void setLblCurrentDate(JLabel lblCurrentDate) {
        this.lblCurrentDate = lblCurrentDate;
    }

    public JLabel getLblHidePassword() {
        return lblHidePassword;
    }

    public void setLblHidePassword(JLabel lblHidePassword) {
        this.lblHidePassword = lblHidePassword;
    }

    public JLabel getLblPasswordName() {
        return lblPasswordName;
    }

    public void setLblPasswordName(JLabel lblPasswordName) {
        this.lblPasswordName = lblPasswordName;
    }

    public JPanel getPanelDate() {
        return panelDate;
    }

    public void setPanelDate(JPanel panelDate) {
        this.panelDate = panelDate;
    }

    public JPanel getPanelDni() {
        return panelDni;
    }

    public void setPanelDni(JPanel panelDni) {
        this.panelDni = panelDni;
    }

    public JPanel getTb1() {
        return tb1;
    }

    public void setTb1(JPanel tb1) {
        this.tb1 = tb1;
    }

    public JPanel getTb2() {
        return tb2;
    }

    public void setTb2(JPanel tb2) {
        this.tb2 = tb2;
    }

    public JTable getTblData() {
        return tblData;
    }

    public void setTblData(JTable tblData) {
        this.tblData = tblData;
    }

    public JTextField getTxtAddress() {
        return txtAddress;
    }

    public void setTxtAddress(JTextField txtAddress) {
        this.txtAddress = txtAddress;
    }

    public JTextField getTxtConfirmPassword() {
        return txtConfirmPassword;
    }

    public void setTxtConfirmPassword(JTextField txtConfirmPassword) {
        this.txtConfirmPassword = (JPasswordField) txtConfirmPassword;
    }

    public JTextField getTxtDni() {
        return txtDni;
    }

    public void setTxtDni(JTextField txtDni) {
        this.txtDni = txtDni;
    }

    public JTextField getTxtFind() {
        return txtFind;
    }

    public void setTxtFind(JTextField txtFind) {
        this.txtFind = txtFind;
    }

    public JTextField getTxtLastName() {
        return txtLastName;
    }

    public void setTxtLastName(JTextField txtLastName) {
        this.txtLastName = txtLastName;
    }

    public JTextField getTxtName() {
        return txtName;
    }

    public void setTxtName(JTextField txtName) {
        this.txtName = txtName;
    }

    public JTextField getTxtPassword() {
        return txtPassword;
    }

    public void setTxtPassword(JTextField txtPassword) {
        this.txtPassword = (JPasswordField) txtPassword;
    }

    public JFormattedTextField getTxtPhone() {
        return txtPhone;
    }

    public void setTxtPhone(JFormattedTextField txtPhone) {
        this.txtPhone = txtPhone;
    }

    public JTextField getTxtPreviousPassword() {
        return txtPreviousPassword;
    }

    public void setTxtPreviousPassword(JTextField txtPreviousPassword) {
        this.txtPreviousPassword = (JPasswordField) txtPreviousPassword;
    }

    public JTextField getTxtUserName() {
        return txtUserName;
    }

    public void setTxtUserName(JTextField txtUserName) {
        this.txtUserName = txtUserName;
    }

    public JMenuItem getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(JMenuItem btnDelete) {
        this.btnDelete = btnDelete;
    }

    public JPopupMenu getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(JPopupMenu subMenu) {
        this.subMenu = subMenu;
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
        btnDelete = new javax.swing.JMenuItem();
        btnGroupStatus = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        tabbedPane = new javax.swing.JTabbedPane();
        tb1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        panelDni = new javax.swing.JPanel();
        cmbNationality = new javax.swing.JComboBox();
        txtDni = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        panelDate = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        lblCurrentDate = new javax.swing.JLabel();
        btnFind = new javax.swing.JButton();
        txtName = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        txtPhone = new javax.swing.JFormattedTextField();
        txtLastName = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        radioBtnActive = new javax.swing.JRadioButton();
        radioBtnInactive = new javax.swing.JRadioButton();
        tb2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        lblHidePassword = new javax.swing.JLabel();
        lblPasswordName = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        txtPreviousPassword = new javax.swing.JPasswordField();
        txtPassword = new javax.swing.JPasswordField();
        jLabel13 = new javax.swing.JLabel();
        txtConfirmPassword = new javax.swing.JPasswordField();
        btnSaveChanges = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtFind = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cmbFilter = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        btnClean = new javax.swing.JButton();
        btnModify = new javax.swing.JButton();
        btnRegister = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();

        btnDelete.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/eliminar.png"))); // NOI18N
        btnDelete.setText("Eliminar");
        subMenu.add(btnDelete);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Usuarios");
        setMinimumSize(new java.awt.Dimension(620, 400));
        setPreferredSize(new java.awt.Dimension(620, 400));
        getContentPane().setLayout(new javax.swing.OverlayLayout(getContentPane()));

        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0};
        jPanel1Layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0};
        jPanel1.setLayout(jPanel1Layout);

        tabbedPane.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        tabbedPane.setMinimumSize(new java.awt.Dimension(573, 150));
        tabbedPane.setPreferredSize(new java.awt.Dimension(573, 150));

        java.awt.GridBagLayout tb1Layout = new java.awt.GridBagLayout();
        tb1Layout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0};
        tb1Layout.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
        tb1.setLayout(tb1Layout);

        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel3.setText("Cédula:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 2, 0);
        tb1.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel4.setText("Nombres:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 6, 0);
        tb1.add(jLabel4, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel5.setText("Dirección:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 6, 0);
        tb1.add(jLabel5, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel6.setText("Teléfono:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        tb1.add(jLabel6, gridBagConstraints);

        java.awt.GridBagLayout panelDniLayout = new java.awt.GridBagLayout();
        panelDniLayout.columnWidths = new int[] {0, 5, 0};
        panelDniLayout.rowHeights = new int[] {0};
        panelDni.setLayout(panelDniLayout);

        cmbNationality.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbNationality.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "V", "E" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        panelDni.add(cmbNationality, gridBagConstraints);

        txtDni.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        txtDni.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelDni.add(txtDni, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 2, 0);
        tb1.add(panelDni, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel7.setText("Apellidos:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 0);
        tb1.add(jLabel7, gridBagConstraints);

        java.awt.GridBagLayout panelDateLayout = new java.awt.GridBagLayout();
        panelDateLayout.columnWidths = new int[] {0, 5, 0, 5, 0};
        panelDateLayout.rowHeights = new int[] {0};
        panelDate.setLayout(panelDateLayout);

        jLabel8.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel8.setText("F. De Registro:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 1.0;
        panelDate.add(jLabel8, gridBagConstraints);

        lblCurrentDate.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        lblCurrentDate.setText("jLabel10");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        panelDate.add(lblCurrentDate, gridBagConstraints);

        btnFind.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/buscar.png"))); // NOI18N
        btnFind.setMnemonic('B');
        btnFind.setText("Buscar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelDate.add(btnFind, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 2, 5);
        tb1.add(panelDate, gridBagConstraints);

        txtName.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        txtName.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtName.setMinimumSize(new java.awt.Dimension(200, 21));
        txtName.setPreferredSize(new java.awt.Dimension(200, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 0);
        tb1.add(txtName, gridBagConstraints);

        txtAddress.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        txtAddress.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 5);
        tb1.add(txtAddress, gridBagConstraints);

        try {
            txtPhone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(####)-(#######)")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtPhone.setText("");
        txtPhone.setToolTipText("Ej: 0251-2667656");
        txtPhone.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtPhone.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        txtPhone.setMinimumSize(new java.awt.Dimension(200, 21));
        txtPhone.setPreferredSize(new java.awt.Dimension(200, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        tb1.add(txtPhone, gridBagConstraints);

        txtLastName.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        txtLastName.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtLastName.setMinimumSize(new java.awt.Dimension(200, 21));
        txtLastName.setPreferredSize(new java.awt.Dimension(200, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 5);
        tb1.add(txtLastName, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel10.setText("Estatus:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 0);
        tb1.add(jLabel10, gridBagConstraints);

        java.awt.GridBagLayout jPanel3Layout = new java.awt.GridBagLayout();
        jPanel3Layout.columnWidths = new int[] {0, 5, 0};
        jPanel3Layout.rowHeights = new int[] {0};
        jPanel3.setLayout(jPanel3Layout);

        btnGroupStatus.add(radioBtnActive);
        radioBtnActive.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        radioBtnActive.setText("Activo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel3.add(radioBtnActive, gridBagConstraints);

        btnGroupStatus.add(radioBtnInactive);
        radioBtnInactive.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        radioBtnInactive.setText("Inactivo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel3.add(radioBtnInactive, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 0);
        tb1.add(jPanel3, gridBagConstraints);

        tabbedPane.addTab("Datos Básicos", tb1);

        java.awt.GridBagLayout tb2Layout = new java.awt.GridBagLayout();
        tb2Layout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0};
        tb2Layout.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
        tb2.setLayout(tb2Layout);

        jLabel9.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel9.setText("Usuario:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 6, 0);
        tb2.add(jLabel9, gridBagConstraints);

        lblHidePassword.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        lblHidePassword.setText("Clave:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 6, 0);
        tb2.add(lblHidePassword, gridBagConstraints);

        lblPasswordName.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        lblPasswordName.setText("Nueva Clave:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        tb2.add(lblPasswordName, gridBagConstraints);

        txtUserName.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        txtUserName.setToolTipText("Ej: javier01");
        txtUserName.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtUserName.setMinimumSize(new java.awt.Dimension(175, 21));
        txtUserName.setPreferredSize(new java.awt.Dimension(175, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 6, 0);
        tb2.add(txtUserName, gridBagConstraints);

        txtPreviousPassword.setMinimumSize(new java.awt.Dimension(175, 21));
        txtPreviousPassword.setPreferredSize(new java.awt.Dimension(175, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 0);
        tb2.add(txtPreviousPassword, gridBagConstraints);

        txtPassword.setMinimumSize(new java.awt.Dimension(175, 21));
        txtPassword.setPreferredSize(new java.awt.Dimension(175, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        tb2.add(txtPassword, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel13.setText("Confirmar Clave:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        tb2.add(jLabel13, gridBagConstraints);

        txtConfirmPassword.setMinimumSize(new java.awt.Dimension(175, 21));
        txtConfirmPassword.setPreferredSize(new java.awt.Dimension(175, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        tb2.add(txtConfirmPassword, gridBagConstraints);

        btnSaveChanges.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnSaveChanges.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/guardar.gif"))); // NOI18N
        btnSaveChanges.setText("Guardar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        tb2.add(btnSaveChanges, gridBagConstraints);

        tabbedPane.addTab("Cuenta de Usuario", tb2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel1.add(tabbedPane, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel1.setText("Buscar:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 5, 0, 0);
        jPanel1.add(jLabel1, gridBagConstraints);

        txtFind.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        txtFind.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtFind.setMinimumSize(new java.awt.Dimension(200, 21));
        txtFind.setPreferredSize(new java.awt.Dimension(200, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 5, 0, 5);
        jPanel1.add(txtFind, gridBagConstraints);

        java.awt.GridBagLayout jPanel2Layout = new java.awt.GridBagLayout();
        jPanel2Layout.columnWidths = new int[] {0, 5, 0};
        jPanel2Layout.rowHeights = new int[] {0};
        jPanel2.setLayout(jPanel2Layout);

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel2.setText("Por:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel2.add(jLabel2, gridBagConstraints);

        cmbFilter.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbFilter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nombre", "Apellido", "Usuario" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel2.add(cmbFilter, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 0, 5);
        jPanel1.add(jPanel2, gridBagConstraints);

        jScrollPane1.setMinimumSize(new java.awt.Dimension(300, 150));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(300, 150));

        tblData.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        tblData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cédula", "Nombres", "Apellidos", "Usuario"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        tblData.setComponentPopupMenu(subMenu);
        tblData.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblData.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblData);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        java.awt.GridBagLayout jPanel5Layout = new java.awt.GridBagLayout();
        jPanel5Layout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0};
        jPanel5Layout.rowHeights = new int[] {0};
        jPanel5.setLayout(jPanel5Layout);

        btnClean.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnClean.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/limpiar.png"))); // NOI18N
        btnClean.setMnemonic('L');
        btnClean.setText("Limpiar");
        btnClean.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        jPanel5.add(btnClean, gridBagConstraints);

        btnModify.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnModify.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/modificar.png"))); // NOI18N
        btnModify.setMnemonic('M');
        btnModify.setText("Modificar");
        btnModify.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel5.add(btnModify, gridBagConstraints);

        btnRegister.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnRegister.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/registrar.png"))); // NOI18N
        btnRegister.setMnemonic('R');
        btnRegister.setText("Registrar");
        btnRegister.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel5.add(btnRegister, gridBagConstraints);

        btnClose.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/electrical_installations/resource/image/cerrar.png"))); // NOI18N
        btnClose.setMnemonic('C');
        btnClose.setText("Cerrar");
        btnClose.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel5.add(btnClose, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jPanel5, gridBagConstraints);

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClean;
    private javax.swing.JButton btnClose;
    private javax.swing.JMenuItem btnDelete;
    private javax.swing.JButton btnFind;
    private javax.swing.ButtonGroup btnGroupStatus;
    private javax.swing.JButton btnModify;
    private javax.swing.JButton btnRegister;
    private javax.swing.JButton btnSaveChanges;
    private javax.swing.JComboBox cmbFilter;
    private javax.swing.JComboBox cmbNationality;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCurrentDate;
    private javax.swing.JLabel lblHidePassword;
    private javax.swing.JLabel lblPasswordName;
    private javax.swing.JPanel panelDate;
    private javax.swing.JPanel panelDni;
    private javax.swing.JRadioButton radioBtnActive;
    private javax.swing.JRadioButton radioBtnInactive;
    private javax.swing.JPopupMenu subMenu;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JPanel tb1;
    private javax.swing.JPanel tb2;
    private javax.swing.JTable tblData;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JPasswordField txtConfirmPassword;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtFind;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtName;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JFormattedTextField txtPhone;
    private javax.swing.JPasswordField txtPreviousPassword;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
