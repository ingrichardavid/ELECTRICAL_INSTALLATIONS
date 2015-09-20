/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.controller;

import com.electrical_installations.configuration.Messages;
import com.electrical_installations.configuration.MessagesStructure;
import com.electrical_installations.global.method.Methods;
import com.electrical_installations.model.entity.User;
import com.electrical_installations.model.service.ServiceUser;
import com.electrical_installations.view.Session;
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
 * @since 21/07/2015
 */
public class ControllerUser implements ActionListener, KeyListener, ChangeListener, WindowListener, MouseListener, ItemListener{

    //Objetos, variables y constantes
    private final ViewUser viewUser;
    private static final Messages messages = Messages.getInstance();
    private static final Session session = Session.getInstance();
    private char character;
    private User user;
    private List<User> users;
    private boolean status;
    
    /**
     * Contructor de la clase, recibe un objeto ViewUser
     * @param viewUser 
     */
    public ControllerUser(ViewUser viewUser){
        this.viewUser = viewUser;        
        this.character = 0;
        this.user = null;
        this.users = null;
    }//Fin del constructor
        
    /**
     * Método para validar que el campo cédula sea completado.
     * @return Retorna true si el campo cédula ha sido completado.
     */    
    private boolean validate_dni(){
        if (viewUser.getTxtDni().getText().trim().equalsIgnoreCase("")){
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.USER_DNI_NO_FOUND), MessagesStructure.justify));
            viewUser.getTabbedPane().setSelectedIndex(0);
            viewUser.getTxtDni().requestFocus();
            return false;
        }
        return true;
    }//Fin del método
    
    /**
     * Método para validar datos de seguridad de usuario.
     * @return Retorna true si los campos obligatorios han sido completados
     */
    private boolean validate_user_data_security(){
        if (viewUser.getTxtUserName().getText().equals("")){
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.USER_USERNAME_NO_FOUND), MessagesStructure.justify));
            viewUser.getTabbedPane().setSelectedIndex(1);
            viewUser.getTxtUserName().requestFocus();
            return false;
        } else if (viewUser.getTxtPassword().getText().equals("")){
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.USER_PASSWORD_NO_FOUND), MessagesStructure.justify));
            viewUser.getTabbedPane().setSelectedIndex(1);
            viewUser.getTxtPassword().requestFocus();
            return false;
        } else if (viewUser.getTxtConfirmPassword().getText().equals("")){
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.USER_CONFIRMPASSWORD_NO_FOUND), MessagesStructure.justify));
            viewUser.getTabbedPane().setSelectedIndex(1);
            viewUser.getTxtConfirmPassword().requestFocus();
            return false;
        } else if (!Methods.compare(viewUser.getTxtPassword().getText(), viewUser.getTxtConfirmPassword().getText())){
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.USER_KEY_MISMATCHED), MessagesStructure.justify));
            viewUser.getTabbedPane().setSelectedIndex(1);
            viewUser.getTxtPassword().requestFocus();
            return false;
        }
        return true;
    }//Fin del método
    
    /**
     * Método para validar campos obligatorios de seguridad de usuario al modificar
     * @return Retorna true si los campos obligatorios han sido completados
     */
    private boolean validate_user(){
        if (validate_dni()){
            if (viewUser.getTxtPreviousPassword().getText().equals("")){
                MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.USER_PASSWORD_PREVIOUS_NO_FOUND), MessagesStructure.justify));
                viewUser.getTabbedPane().setSelectedIndex(1);
                viewUser.getTxtPreviousPassword().requestFocus();
                return false;                
            } else if (!validate_user_data_security()){
                return false;
            }
        }
        return true;
    }//Fin del método
    
    /**
     * Método para validar que todos los campos obligatorios al intentar registrar sean completados.
     * @return Retorna true si los campos obligatorios han sido completados
     */
    private boolean validate_fields_to_register(){
        if (!validate_dni()){
            return false;
        } else if (viewUser.getTxtName().getText().equals("")){
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.USER_NAME_NO_FOUND), MessagesStructure.justify));
            viewUser.getTabbedPane().setSelectedIndex(0);
            viewUser.getTxtName().requestFocus();
            return false;
        } else if (viewUser.getTxtLastName().getText().equals("")){
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.USER_LASTNAME_NO_FOUND), MessagesStructure.justify));
            viewUser.getTabbedPane().setSelectedIndex(0);
            viewUser.getTxtLastName().requestFocus();
            return false;
        } else if (!validate_user_data_security()){
            return false;
        }
        return true;
    }//Fin del método   
    
    /**
     * Método para validar que todos los campos obligatorios al intentar modificar sean completados.
     * @return Retorna true si los campos obligatorios son completados.
     */
    private boolean validate_fields_to_modify(){
        if (!validate_dni()){
            return false;
        } else if (viewUser.getTxtName().getText().equals("")){
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.USER_NAME_NO_FOUND), MessagesStructure.justify));
            viewUser.getTabbedPane().setSelectedIndex(0);
            viewUser.getTxtName().requestFocus();
            return false;
        } else if (viewUser.getTxtLastName().getText().equals("")){
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.USER_LASTNAME_NO_FOUND), MessagesStructure.justify));
            viewUser.getTabbedPane().setSelectedIndex(0);
            viewUser.getTxtLastName().requestFocus();
            return false;
        } 
        return true;
    }//Fin del método
    
    /**
     * Método para llenar todos los campos de la vista ViewUser, recibe como parámetro un objeto User.
     * @param user 
     */
    private void fill_fields(User user){
        viewUser.enable_jtextFields(false, false, true, true, true, true, true, true, true,true,true);
        viewUser.enable_buttons(false, true, false, true, true);
        viewUser.getCmbNationality().setSelectedItem(user.getNationality());
        viewUser.getTxtDni().setText(String.valueOf(user.getDni()));
        viewUser.getTxtName().setText(user.getName());
        viewUser.getTxtLastName().setText(user.getLastName());
        viewUser.getTxtAddress().setText(user.getAddress());
        viewUser.getTxtPhone().setText(user.getPhone());
        viewUser.getLblCurrentDate().setText(user.getDate());
        viewUser.getTxtUserName().setText(user.getUserName());
        viewUser.getLblHidePassword().setVisible(true);
        viewUser.getTxtPreviousPassword().setVisible(true);
        viewUser.getLblPasswordName().setText("Nueva Clave:");
        viewUser.getBtnSaveChanges().setVisible(true);
        if (user.isStatus()){
            viewUser.getRadioBtnActive().setSelected(true);
        } else {
            viewUser.getRadioBtnInactive().setSelected(true);
        }
        viewUser.getTxtName().requestFocus();
    }//Fin del método
    
    /**
     * Método para buscar un usuario.
     */
    private void find_user(String nationality,int dni){
        user = ServiceUser.find_user(new User(nationality,dni));
        if (user != null){
            fill_fields(user);
        } else {
            viewUser.enable_jtextFields(false, false, true, true, true, true, true, true, true,true,true);
            viewUser.enable_buttons(false, true, true, false, true); 
            viewUser.getTxtName().requestFocus();
        }
    }//Fin del método
    
    /**
     * Método para registrar un usuario.
     */
    private void user_registration(){
        if(validate_fields_to_register()){
            if (ServiceUser.create_user(new User(viewUser.getCmbNationality().getSelectedItem().toString(), 
                    Integer.valueOf(viewUser.getTxtDni().getText()), 
                    viewUser.getTxtName().getText(),
                    viewUser.getTxtLastName().getText(),
                    viewUser.getTxtAddress().getText(),
                    viewUser.getTxtUserName().getText(),
                    viewUser.getTxtPassword().getText(),
                    viewUser.getTxtPhone().getText(),
                    status))){
                clean_all();
            }
        }
    }//Fin del método    
    
    /**
     * Método para modificar datos de un usuario.
     */
    private void modify_user(){
        if (validate_fields_to_modify()){
            if (Integer.valueOf(viewUser.getTxtDni().getText()) == 0){
                MessagesStructure.ErrorMessage(MessagesStructure.format(200, messages.getProperty(Messages.USER_NOT_BE_MODIFIED), MessagesStructure.justify));
                viewUser.getTxtName().requestFocus();              
            } else {
                if (ServiceUser.update(new User(viewUser.getCmbNationality().getSelectedItem().toString(), 
                        Integer.valueOf(viewUser.getTxtDni().getText()), 
                        viewUser.getTxtName().getText(),
                        viewUser.getTxtLastName().getText(),
                        viewUser.getTxtAddress().getText(),
                        viewUser.getTxtPhone().getText()))){
                    clean_all();
                }
            }
        }
    }
    
    /**
     * Método para modificar datos de seguridad de un usuario.
     */
    private void modify_user_security(){
        if(validate_user()){  
            if (ServiceUser.verify_key(new User(viewUser.getCmbNationality().getSelectedItem().toString(), 
                        Integer.valueOf(viewUser.getTxtDni().getText()), 
                        viewUser.getTxtUserName().getText(),
                        viewUser.getTxtPreviousPassword().getText(),
                        status))){            
                if (ServiceUser.update_user(new User(viewUser.getCmbNationality().getSelectedItem().toString(), 
                        Integer.valueOf(viewUser.getTxtDni().getText()), 
                        viewUser.getTxtUserName().getText(),
                        viewUser.getTxtPassword().getText(),
                        status))){
                    clean_all();
                }
            } else {
                viewUser.getTxtPreviousPassword().requestFocus();
            }
        }
    }//Fin del método
    
    /**
     * Método para eliminar un usuario.
     */
    private void remove_user(){        
        try {
            int row = viewUser.getTblData().getSelectedRow();
            if (Integer.valueOf(viewUser.getTblData().getValueAt(row, 0).toString().substring(2)) == 0){
                MessagesStructure.ErrorMessage(MessagesStructure.format(200, messages.getProperty(Messages.USER_NOT_BE_REMOVED), MessagesStructure.justify));
                viewUser.getTblData().requestFocus();                
            } else {
                if (ServiceUser.delete_user(new User(viewUser.getTblData().getValueAt(row, 0).toString().substring(0,1),
                                    Integer.valueOf(viewUser.getTblData().getValueAt(row, 0).toString().substring(2))))){
                    if (session.getNationality().equals(viewUser.getTblData().getValueAt(row, 0).toString().substring(0,1)) &&
                            session.getDni() == Integer.valueOf(viewUser.getTblData().getValueAt(row, 0).toString().substring(2))){
                        System.exit(0);
                    }
                    Methods.removeRow(viewUser.getTblData());
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.NOT_SELECT_ROW), MessagesStructure.justify));
            viewUser.getTblData().requestFocus();
        }
    }//Fin del método
    
    /**
     * Método para llenar tabla con datos de usuarios.
     */
    private void fill_table(){
        users = ServiceUser.find_users();
        if (users != null){            
            Methods.removeRows(viewUser.getTblData());
            for (User user_data : users) {
                Object[] data = {user_data.getNationality(),user_data.getName(),user_data.getLastName(),user_data.getUserName()};
                ((DefaultTableModel) viewUser.getTblData().getModel()).addRow(data);
            }
        }
    }//Fin del método
    
    /**
     * Método para llenar tabla con datos de usuarios filtrados por nombres.
     * @param name 
     */
    private void fill_table_names(String name){        
        users = ServiceUser.filter_by_name(new User(null, name, null, null));
        if (users != null){            
            Methods.removeRows(viewUser.getTblData());
            for (User user_data : users) {
                Object[] data = {user_data.getNationality(),user_data.getName(),user_data.getLastName(),user_data.getUserName()};
                ((DefaultTableModel) viewUser.getTblData().getModel()).addRow(data);
            }
        }
    }//Fin del método
    
    /**
     * Método para llenar tabla con datos de usuarios filtrados por apellidos.
     * @param lastName  
     */
    private void fill_table_lastNames(String lastName){        
        users = ServiceUser.filter_by_lastName(new User(null, null, lastName, null));
        if (users != null){            
            Methods.removeRows(viewUser.getTblData());
            for (User user_data : users) {
                Object[] data = {user_data.getNationality(),user_data.getName(),user_data.getLastName(),user_data.getUserName()};
                ((DefaultTableModel) viewUser.getTblData().getModel()).addRow(data);
            }
        }
    }//Fin del método
    
    /**
     * Método para llenar tabla con datos de usuarios filtrados por nombres de usuarios.
     * @param userName  
     */
    private void fill_table_userName(String userName){        
        users = ServiceUser.filter_by_userName(new User(null, null, null, userName));
        if (users != null){            
            Methods.removeRows(viewUser.getTblData());
            for (User user_data : users) {
                Object[] data = {user_data.getNationality(),user_data.getName(),user_data.getLastName(),user_data.getUserName()};
                ((DefaultTableModel) viewUser.getTblData().getModel()).addRow(data);
            }
        }
    }//Fin del método
    
    /**
     * Método para restablecer formulario.
     */
    private void clean_all(){
        viewUser.clean_all();
        fill_table();
    }//Fin del método
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(viewUser.getBtnFind())){            
            if (validate_dni()){    
                find_user(viewUser.getCmbNationality().getSelectedItem().toString(), Integer.valueOf(viewUser.getTxtDni().getText()));
            }
        } else if (e.getSource().equals(viewUser.getBtnClean())){
            clean_all();
        } else if (e.getSource().equals(viewUser.getBtnRegister())){
            user_registration();
        } else if (e.getSource().equals(viewUser.getBtnModify())){
            modify_user();
        } else if (e.getSource().equals(viewUser.getBtnSaveChanges())){
            modify_user_security();
        } else if (e.getSource().equals(viewUser.getBtnDelete())){
            remove_user();
        } else if (e.getSource().equals(viewUser.getBtnClose())){
            viewUser.dispose();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {    
        character = e.getKeyChar();
        if (e.getSource().equals(viewUser.getTxtDni())) {
            if (Methods.validate_number_only(character)){
                e.consume();
            } else if (viewUser.getTxtDni().getText().length() == 9) {
                viewUser.getToolkit().beep();
                e.consume();                
            } else if (character == (char) KeyEvent.VK_ENTER) {
                find_user(viewUser.getCmbNationality().getSelectedItem().toString(), Integer.valueOf(viewUser.getTxtDni().getText()));
            } 
        } else if (e.getSource().equals(viewUser.getTxtName())) {
            if (Methods.validate_text_only(character)){
                e.consume();
            } else if (viewUser.getTxtName().getText().length() == 50) {
                viewUser.getToolkit().beep();
                e.consume();                
            } 
        } else if (e.getSource().equals(viewUser.getTxtLastName())) {
            if (Methods.validate_text_only(character)){
                e.consume();
            } else if (viewUser.getTxtLastName().getText().length() == 50) {
                viewUser.getToolkit().beep();
                e.consume();                
            } 
        } else if (e.getSource().equals(viewUser.getTxtAddress())) {
            if (viewUser.getTxtAddress().getText().length() == 200) {
                viewUser.getToolkit().beep();
                e.consume();                
            } 
        } else if (e.getSource().equals(viewUser.getTxtUserName())) {
            if (viewUser.getTxtUserName().getText().length() == 20) {
                viewUser.getToolkit().beep();
                e.consume();                
            } 
        } else if (e.getSource().equals(viewUser.getTxtPassword())) {
            if (viewUser.getTxtPassword().getText().length() == 20) {
                viewUser.getToolkit().beep();
                e.consume();                
            } 
        } else if (e.getSource().equals(viewUser.getTxtPreviousPassword())) {
            if (viewUser.getTxtPreviousPassword().getText().length() == 20) {
                viewUser.getToolkit().beep();
                e.consume();                
            } 
        } else if (e.getSource().equals(viewUser.getTxtConfirmPassword())) {
            if (viewUser.getTxtConfirmPassword().getText().length() == 20) {
                viewUser.getToolkit().beep();
                e.consume();                
            } 
        } else if (e.getSource().equals(viewUser.getTxtFind())) {
            if (viewUser.getCmbFilter().getSelectedIndex() == 0){                
                if (Methods.validate_text_only(character)){
                    e.consume();
                } else if (viewUser.getTxtFind().getText().length() == 48) {
                    viewUser.getToolkit().beep();
                    e.consume();                
                }             
            } else if (viewUser.getCmbFilter().getSelectedIndex() == 1){                
                if (Methods.validate_text_only(character)){
                    e.consume();
                } else if (viewUser.getTxtFind().getText().length() == 48) {
                    viewUser.getToolkit().beep();
                    e.consume();                
                }             
            } else {
                if (viewUser.getTxtFind().getText().length() == 48) {
                    viewUser.getToolkit().beep();
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
        if (e.getSource().equals(viewUser.getTxtFind())){
            if (viewUser.getCmbFilter().getSelectedIndex() == 0){
                fill_table_names(viewUser.getTxtFind().getText());
            } else if (viewUser.getCmbFilter().getSelectedIndex() == 1){
                fill_table_lastNames(viewUser.getTxtFind().getText());
            } else if (viewUser.getCmbFilter().getSelectedIndex() == 2){
                fill_table_userName(viewUser.getTxtFind().getText());
            }
        }
    }    

    @Override
    public void stateChanged(ChangeEvent e) {
        if (viewUser.getRadioBtnActive().isSelected()){
            status = true;
        } else if (viewUser.getRadioBtnInactive().isSelected()){
            status = false;
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        fill_table();
    }

    @Override
    public void windowClosing(WindowEvent e) {
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
        if (e.getSource().equals(viewUser.getTblData())){
            if (e.getClickCount() == 2){
                int row = viewUser.getTblData().getSelectedRow();
                viewUser.getCmbNationality().setSelectedItem(viewUser.getTblData().getValueAt(row, 0).toString().substring(0,1));
                viewUser.getTxtDni().setText(viewUser.getTblData().getValueAt(row, 0).toString().substring(2));
                find_user(viewUser.getTblData().getValueAt(row, 0).toString().substring(0,1),
                            Integer.valueOf(viewUser.getTblData().getValueAt(row, 0).toString().substring(2)));
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
        if (e.getSource().equals(viewUser.getCmbFilter())){
            fill_table();
            viewUser.getTxtFind().setText(null);
            viewUser.getTxtFind().requestFocus();
        }        
    }
    
}
