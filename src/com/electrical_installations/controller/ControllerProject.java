/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.controller;

import com.electrical_installations.configuration.Control;
import com.electrical_installations.configuration.Messages;
import com.electrical_installations.configuration.MessagesStructure;
import com.electrical_installations.global.method.Methods;
import com.electrical_installations.model.entity.Project;
import com.electrical_installations.model.entity.TypeOfInstallation;
import com.electrical_installations.model.entity.User;
import com.electrical_installations.model.service.ServiceProject;
import com.electrical_installations.model.service.ServiceTypeOfInstallation;
import com.electrical_installations.view.Session;
import com.electrical_installations.view.ViewCalculateIntensityMotors;
import com.electrical_installations.view.ViewMainFeeder;
import com.electrical_installations.view.ViewProject;
import com.electrical_installations.view.ViewProyectData;
import com.electrical_installations.view.ViewUser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

/**
 * Controlador para la vista ViewUser
 * @author Ing. Richard David
 * @version 1
 * @since 2015-07-27
 */
public class ControllerProject implements ActionListener, KeyListener, ChangeListener, WindowListener, MouseListener, ItemListener{

    //Objectos, variables y constantes
    private final ViewProject viewProject; 
    private ViewUser viewUser;
    private ViewProyectData viewProjectData;
    private static final Messages messages = Messages.getInstance();
    private static final Session session = Session.getInstance();
    private List<TypeOfInstallation> typeOfInstallations;
    private char character;
    private Project project;
    private List<Project> projects;
    private TypeOfInstallation typeOfInstallation; 
    
    /**
     * Constructor de la clase, recibe un objeto ViewProject1
     * @param viewProject 
     */
    public ControllerProject(ViewProject viewProject){
        this.viewProject = viewProject;
    }//Fin del constructor
    
    /**
     * Método para validar que el campo de texto nombre sea completado.
     * @return Retorna true en caso de que el campo sea completado
     */
    private boolean validate_name(){
        if (viewProject.getTxtName().getText().trim().equals("")){
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.PROJECT_NAME_NO_FOUND), MessagesStructure.justify));
            viewProject.getTxtName().requestFocus();
            return false;
        }
        return true;
    }//Fin del método
    
    /**
     * Método para validar que los campos obligatorios sean completados.
     * @return Retorna true en caso de que los campos sean completados
     */
    private boolean validate_fields(){
        return validate_name();
    }//Fin del método
    
    /**
     * Método para llenar todos los campos de la vista ViewProject, recibe como parámetro un objeto Project.
     * @param project 
     */
    private void fill_fields(Project project){
        viewProject.enable_jtextFields(true,false);
        viewProject.enable_buttons(false, true, false, true, true);   
        viewProject.setCode(project.getCode());
        viewProject.getTxtName().setText(project.getName());
        viewProject.getCmbType().setSelectedItem(project.getTypeOfInstallation());
        viewProject.getLblCurrentDate().setText(project.getRegistration_date());
        viewProject.getLblPowerTotal().setText(String.valueOf(project.getPowerTotal()));
        viewProject.getTxtName().requestFocus();
    }//Fin del método
    
    /**
     * Método para buscar un proyecto.
     */    
    private void find_project(String name){
        if (validate_name()){
            project = ServiceProject.find_project(new Project(0,name));
            if (project != null){
                fill_fields(project);
            } else {
                viewProject.enable_jtextFields(false, false);
                viewProject.enable_buttons(false, true, true, false, true); 
                viewProject.getCmbType().requestFocus();
            }            
        }
    }//Fin del método
    
    /**
     * Método para llenar tabla con datos de projectos.
     */
    public void fill_table(){
        projects = ServiceProject.find_projects(new User(session.getNationality(), session.getDni()));
        if (projects != null){            
            Methods.removeRows(viewProject.getTblData());
            for (Project project : projects) {
                Object[] data = {project.getCode(),project.getName(),project.getTypeOfInstallation().getCode(),project.getTypeOfInstallation().getName(),project.getPowerTotal(),project.getRegistration_date()};
                ((DefaultTableModel) viewProject.getTblData().getModel()).addRow(data);
            }
        }
    }//Fin del método
        
    /**
     * Método para llenar tabla con datos de proyectos filtrados por nombres.
     * @param name 
     */
    private void fill_table_names(String name){        
        projects = ServiceProject.filter_by_name(new Project(new User(session.getNationality(), session.getDni()),
                null, name,0));
        if (projects != null){            
            Methods.removeRows(viewProject.getTblData());
          
            for (Project project_data : projects) {
                                 
                Object[] data = {project_data.getCode(),project_data.getName(),project_data.getTypeOfInstallation().getCode(),project_data.getTypeOfInstallation().getName(),project_data.getPowerTotal(),project_data.getRegistration_date()};
                               
                ((DefaultTableModel) viewProject.getTblData().getModel()).addRow(data);
            }
        }
    }//Fin del método
        
    /**
     * Método para llenar tabla con datos de proyectos filtrados por tipos.
     * @param type 
     */
    private void fill_table_types(String type){        
        projects = ServiceProject.filter_by_type(new Project(new User(session.getNationality(), session.getDni()),
                new TypeOfInstallation(0, type), null,0));
        if (projects != null){            
            Methods.removeRows(viewProject.getTblData());
            for (Project project_data : projects) {
                Object[] data = {project_data.getCode(),project_data.getName(),project_data.getTypeOfInstallation().getCode(),project_data.getTypeOfInstallation().getName(),project_data.getPowerTotal(),project_data.getRegistration_date()};
                ((DefaultTableModel) viewProject.getTblData().getModel()).addRow(data);
            }
        }
    }//Fin del método
    
    /**
     * Método para registrar un proyecto
     */
    private void project_registration(){
        if(validate_fields()){
            typeOfInstallation = (TypeOfInstallation)viewProject.getCmbType().getSelectedItem();
            if (ServiceProject.create_project(new Project(new User(session.getNationality(), session.getDni()),
                    new TypeOfInstallation(typeOfInstallation.getCode(),typeOfInstallation.getName()), 
                    viewProject.getTxtName().getText(),
                    Integer.valueOf(viewProject.getLblPowerTotal().getText())))){
                this.clean_all();
            }
        }
    }//Fin del método  
        
    /**
     * Método para modificar datos de un proyecto.
     */
    private void modify_project(){
        if (validate_fields()){
            typeOfInstallation = (TypeOfInstallation)viewProject.getCmbType().getSelectedItem();            
            if (MessagesStructure.ConfirmationMessage(MessagesStructure.format(200, messages.getProperty(Messages.CONFIRM), MessagesStructure.justify)) == 0){  
                if (ServiceProject.validate_project_name_for_a_user(new Project(viewProject.getCode(),new User(session.getNationality(), session.getDni()),
                        new TypeOfInstallation(typeOfInstallation.getCode(),typeOfInstallation.getName()), 
                        viewProject.getTxtName().getText(),
                        Integer.valueOf(viewProject.getLblPowerTotal().getText()),null))){
                    if (ServiceProject.update(new Project(viewProject.getCode(),new User(session.getNationality(), session.getDni()),
                            new TypeOfInstallation(typeOfInstallation.getCode(),typeOfInstallation.getName()), 
                            viewProject.getTxtName().getText(),
                            Integer.valueOf(viewProject.getLblPowerTotal().getText()),null))){
                        this.clean_all();
                    }
                } else {
                    viewProject.getTxtName().requestFocus();
                }
            }
        }
    }//Fin del método
    
    /**
     * Método para eliminar un proyecto
     */
    private void remove_user(){        
        try {
            int row = viewProject.getTblData().getSelectedRow();
            if (ServiceProject.delete_project(new Project(Integer.valueOf(viewProject.getTblData().getValueAt(row, 0).toString())))){
                Methods.removeRow(viewProject.getTblData());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.NOT_SELECT_ROW), MessagesStructure.justify));
            viewProject.getTblData().requestFocus();
        }
    }//Fin del método
    
    /**
     * Método para llenar combo Tipo de Instalación.
     */
    public void fill_types_of_installation(){
        typeOfInstallations = ServiceTypeOfInstallation.find_type_of_installations();
        if (typeOfInstallations != null){
            for (TypeOfInstallation typeOfInstallation : typeOfInstallations){
                viewProject.getCmbType().addItem(typeOfInstallation);
            }     
            viewProject.getCmbType().setSelectedIndex(0);
        } else {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.TYPE_INSTALLATIONS_NO_FOUND), MessagesStructure.justify));
            viewProject.dispose();
        }       
    }//Fin del método
    
    /**
     * Método para abrir la vista ViewProjectData.
     */
    private void open_project_data(){          
        try {
            int row = viewProject.getTblData().getSelectedRow();
            viewProjectData = new ViewProyectData(null, true);
            viewProjectData.fill_basic_data(new Project(Integer.valueOf(viewProject.getTblData().getValueAt(row, 0).toString()), 
                    null, 
                    new TypeOfInstallation(Integer.valueOf(viewProject.getTblData().getValueAt(row, 2).toString()), viewProject.getTblData().getValueAt(row, 3).toString()), 
                    viewProject.getTblData().getValueAt(row, 1).toString(), 
                    Integer.valueOf(viewProject.getTblData().getValueAt(row, 4).toString()), 
                    viewProject.getTblData().getValueAt(row, 5).toString()));
            viewProjectData.controllerProjectData.fill_areas();
            viewProjectData.controllerProjectData.fill_installation_motors();
            viewProjectData.setTitle("Proyecto: " + viewProjectData.getLblName().getText());
            viewProjectData.setVisible(true);
            this.clean_all();
        } catch (ArrayIndexOutOfBoundsException e) {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.NOT_SELECT_ROW), MessagesStructure.justify));
            viewProject.getTblData().requestFocus();
        }
    }//Fin del método
    
    /**
     * Método para restablecer formulario
     */
    private void clean_all(){
        viewProject.clean_all();
        fill_table();
    }//Fin del método
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(viewProject.getBtnUser())){   
            viewUser = new ViewUser(viewProject, true);
            viewUser.setVisible(true);
        } else if (e.getSource().equals(viewProject.getBtnExit())){
               if (MessagesStructure.ConfirmationMessage(MessagesStructure.format(100, messages.getProperty(Messages.LOG_OUT), MessagesStructure.justify)) == 0){        
                 new Control().closeApp();           
                System.exit(0);
            } else {
                     viewProject.setDefaultCloseOperation(0);
            }   
        } else if (e.getSource().equals(viewProject.getBtnRegister())){
            project_registration();
        } else if (e.getSource().equals(viewProject.getBtnModify())){
            modify_project();
        } else if (e.getSource().equals(viewProject.getBtnFind())){
            find_project(viewProject.getTxtName().getText());
        } else if (e.getSource().equals(viewProject.getBtnClean())){
            this.clean_all();
        } else if (e.getSource().equals(viewProject.getBtnOpen())){
            open_project_data();
        } else if (e.getSource().equals(viewProject.getBtnDelete())){
            remove_user();
        } 
    }

    @Override
    public void keyTyped(KeyEvent e) {        
        if (e.getSource().equals(viewProject.getTxtName())) {
            if (viewProject.getTxtName().getText().length() == 100) {
                viewProject.getToolkit().beep();
                e.consume();                
            } 
        } else if (e.getSource().equals(viewProject.getTxtFind())) {
            if (viewProject.getCmbFilter().getSelectedIndex() == 0){                
                if (viewProject.getTxtFind().getText().length() == 98) {
                    viewProject.getToolkit().beep();
                    e.consume();                
                }             
            } else if (viewProject.getCmbFilter().getSelectedIndex() == 1){                
                if (viewProject.getTxtFind().getText().length() == 48) {
                    viewProject.getToolkit().beep();
                    e.consume();                
                }             
            } 
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource().equals(viewProject.getTxtFind())){
            if (viewProject.getCmbFilter().getSelectedIndex() == 0){
                fill_table_names(viewProject.getTxtFind().getText());
            } else if (viewProject.getCmbFilter().getSelectedIndex() == 1){
                fill_table_types(viewProject.getTxtFind().getText());
            } 
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        if (MessagesStructure.ConfirmationMessage(MessagesStructure.format(100, messages.getProperty(Messages.LOG_OUT), MessagesStructure.justify)) == 0){        
            new Control().closeApp();
            System.exit(0);
        } else {
            viewProject.setDefaultCloseOperation(0);
        }   
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(viewProject.getTblData())){
            if (e.getClickCount() == 2){
                int row = viewProject.getTblData().getSelectedRow();
                viewProject.getTxtName().setText(viewProject.getTblData().getValueAt(row, 1).toString());
                find_project(viewProject.getTblData().getValueAt(row, 1).toString());                
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource().equals(viewProject.getCmbFilter())){
            fill_table();
            viewProject.getTxtFind().setText(null);
            viewProject.getTxtFind().requestFocus();
        }        
    }
    
}
