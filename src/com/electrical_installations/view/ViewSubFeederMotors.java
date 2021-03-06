/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.view;

import com.electrical_installations.controller.ControllerSubFeederMotors;
import com.electrical_installations.model.entity.Project;
import com.electrical_installations.model.entity.masters.Breaker;
import com.electrical_installations.model.entity.masters.Intensity;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

/**
 * Vista ViewSubFeederMotors.
 * 
 * @author JRichard
 * @version 1
 * @since 2015-11-05
 */
public class ViewSubFeederMotors extends javax.swing.JDialog {
    
    //Objetos, variables y constantes
    private ControllerSubFeederMotors controller;
    private Intensity intensity;
    private Breaker breaker;
    private Project project;
    
    /**
     * Constructor de ViewSubFeederMotors, por ser subclase de JDialog recibe como parámetro el padre y true en caso de que sea modal.
     * @param parent
     * @param modal 
     */
    public ViewSubFeederMotors(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
              
        controller = new ControllerSubFeederMotors(this);         
        controller.fill_combos_materials();
        controller.fill_combos_temperatures();    
        controller.fill_combos_phases(); 
        this.btnAdd.addActionListener(controller);
        this.btnClose.addActionListener(controller);
        this.cmbMaterial.addItemListener(controller);
        this.cmbPhases.addItemListener(controller);
        this.cmbTemperature.addItemListener(controller);
        this.rBtnAir.addActionListener(controller);
        this.rBtnGround.addActionListener(controller);
        this.rBtnAir.addChangeListener(controller);
        this.rBtnGround.addChangeListener(controller);
        this.rBtnGround.setSelected(true); 
         
        this.setLocationRelativeTo(null);        
    }  
   
    //Getters y setters

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public ControllerSubFeederMotors getController() {
        return controller;
    }

    public void setController(ControllerSubFeederMotors controller) {
        this.controller = controller;
    }
    
    public Breaker getBreaker() {
        return breaker;
    }

    public void setBreaker(Breaker breaker) {
        this.breaker = breaker;
    }
    
    public Intensity getIntensity() {    
        return intensity;
    }
      
    public void setIntensity(Intensity intensity) {
        this.intensity = intensity;
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

    public ButtonGroup getBtnGroupRush() {
        return btnGroupRush;
    }

    public void setBtnGroupRush(ButtonGroup btnGroupRush) {
        this.btnGroupRush = btnGroupRush;
    }

    public JComboBox getCmbMaterial() {
        return cmbMaterial;
    }

    public void setCmbMaterial(JComboBox cmbMaterial) {
        this.cmbMaterial = cmbMaterial;
    }

    public JComboBox getCmbTemperature() {
        return cmbTemperature;
    }

    public void setCmbTemperature(JComboBox cmbTemperature) {
        this.cmbTemperature = cmbTemperature;
    }

    public JLabel getLblBreakers() {
        return lblBreakers;
    }

    public void setLblBreakers(JLabel lblBreakers) {
        this.lblBreakers = lblBreakers;
    }

    public JLabel getLblCaliberEarth() {
        return lblCaliberEarth;
    }

    public void setLblCaliberEarth(JLabel lblCaliberEarth) {
        this.lblCaliberEarth = lblCaliberEarth;
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

    public JLabel getLblIntensity() {
        return lblIntensity;
    }

    public void setLblIntensity(JLabel lblIntensity) {
        this.lblIntensity = lblIntensity;
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

    public JComboBox getCmbPhases() {
        return cmbPhases;
    }

    public void setCmbPhases(JComboBox cmbPhases) {
        this.cmbPhases = cmbPhases;
    }//fin de los getters y setters

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

        btnGroupRush = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmbTemperature = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        cmbMaterial = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        pa = new javax.swing.JPanel();
        rBtnAir = new javax.swing.JRadioButton();
        rBtnGround = new javax.swing.JRadioButton();
        jLabel17 = new javax.swing.JLabel();
        lblCaliberPhase = new javax.swing.JLabel();
        lblHP = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblCaliberEarth = new javax.swing.JLabel();
        lblIntensity = new javax.swing.JLabel();
        lblBreakers = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cmbPhases = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Calcular Sub-Alimentador");
        setMinimumSize(new java.awt.Dimension(620, 197));
        setPreferredSize(new java.awt.Dimension(620, 197));
        setResizable(false);
        getContentPane().setLayout(new javax.swing.OverlayLayout(getContentPane()));

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

        java.awt.GridBagLayout jPanel4Layout = new java.awt.GridBagLayout();
        jPanel4Layout.columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0};
        jPanel4Layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0};
        jPanel4.setLayout(jPanel4Layout);

        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel3.setText("Breakers:");
        jLabel3.setMinimumSize(new java.awt.Dimension(98, 23));
        jLabel3.setPreferredSize(new java.awt.Dimension(98, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanel4.add(jLabel3, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel6.setText("Temperatura:");
        jLabel6.setMinimumSize(new java.awt.Dimension(98, 23));
        jLabel6.setPreferredSize(new java.awt.Dimension(98, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanel4.add(jLabel6, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel7.setText("Material:");
        jLabel7.setMaximumSize(new java.awt.Dimension(82, 23));
        jLabel7.setMinimumSize(new java.awt.Dimension(82, 23));
        jLabel7.setPreferredSize(new java.awt.Dimension(82, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel4.add(jLabel7, gridBagConstraints);

        cmbTemperature.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbTemperature.setMinimumSize(new java.awt.Dimension(148, 23));
        cmbTemperature.setPreferredSize(new java.awt.Dimension(148, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
        jPanel4.add(cmbTemperature, gridBagConstraints);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        cmbMaterial.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbMaterial.setMinimumSize(new java.awt.Dimension(148, 23));
        cmbMaterial.setPreferredSize(new java.awt.Dimension(148, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel6.add(cmbMaterial, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 0);
        jPanel4.add(jPanel6, gridBagConstraints);

        jLabel15.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel15.setText("Acometida:");
        jLabel15.setMaximumSize(new java.awt.Dimension(82, 23));
        jLabel15.setMinimumSize(new java.awt.Dimension(82, 23));
        jLabel15.setPreferredSize(new java.awt.Dimension(82, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
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
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(pa, gridBagConstraints);

        jLabel17.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel17.setText("Conductor:");
        jLabel17.setMaximumSize(new java.awt.Dimension(82, 23));
        jLabel17.setMinimumSize(new java.awt.Dimension(82, 23));
        jLabel17.setPreferredSize(new java.awt.Dimension(82, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel4.add(jLabel17, gridBagConstraints);

        lblCaliberPhase.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        lblCaliberPhase.setMaximumSize(new java.awt.Dimension(230, 23));
        lblCaliberPhase.setMinimumSize(new java.awt.Dimension(230, 23));
        lblCaliberPhase.setPreferredSize(new java.awt.Dimension(230, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel4.add(lblCaliberPhase, gridBagConstraints);

        lblHP.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        lblHP.setText("Intensidad:");
        lblHP.setMaximumSize(new java.awt.Dimension(82, 23));
        lblHP.setMinimumSize(new java.awt.Dimension(82, 23));
        lblHP.setPreferredSize(new java.awt.Dimension(82, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel4.add(lblHP, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel2.setText("C. Tierra:");
        jLabel2.setMinimumSize(new java.awt.Dimension(98, 23));
        jLabel2.setPreferredSize(new java.awt.Dimension(98, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanel4.add(jLabel2, gridBagConstraints);

        lblCaliberEarth.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        lblCaliberEarth.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCaliberEarth.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblCaliberEarth.setMaximumSize(new java.awt.Dimension(200, 23));
        lblCaliberEarth.setMinimumSize(new java.awt.Dimension(200, 23));
        lblCaliberEarth.setPreferredSize(new java.awt.Dimension(150, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel4.add(lblCaliberEarth, gridBagConstraints);

        lblIntensity.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        lblIntensity.setMinimumSize(new java.awt.Dimension(34, 23));
        lblIntensity.setPreferredSize(new java.awt.Dimension(34, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel4.add(lblIntensity, gridBagConstraints);

        lblBreakers.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        lblBreakers.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblBreakers.setText("0");
        lblBreakers.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblBreakers.setMinimumSize(new java.awt.Dimension(44, 23));
        lblBreakers.setPreferredSize(new java.awt.Dimension(44, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel4.add(lblBreakers, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel1.setText("Fases:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanel4.add(jLabel1, gridBagConstraints);

        cmbPhases.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cmbPhases.setMinimumSize(new java.awt.Dimension(148, 23));
        cmbPhases.setPreferredSize(new java.awt.Dimension(148, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel4.add(cmbPhases, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel1.add(jPanel4, gridBagConstraints);

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClose;
    private javax.swing.ButtonGroup btnGroupRush;
    private javax.swing.JComboBox cmbMaterial;
    private javax.swing.JComboBox cmbPhases;
    private javax.swing.JComboBox cmbTemperature;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lblBreakers;
    private javax.swing.JLabel lblCaliberEarth;
    private javax.swing.JLabel lblCaliberPhase;
    private javax.swing.JLabel lblHP;
    private javax.swing.JLabel lblIntensity;
    private javax.swing.JPanel pa;
    private javax.swing.JRadioButton rBtnAir;
    private javax.swing.JRadioButton rBtnGround;
    // End of variables declaration//GEN-END:variables
}
