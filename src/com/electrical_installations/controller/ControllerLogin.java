/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.controller;

import com.electrical_installations.configuration.Messages;
import com.electrical_installations.configuration.MessagesStructure;
import com.electrical_installations.model.entity.User;
import com.electrical_installations.model.service.ServiceUser;
import com.electrical_installations.view.Session;
import com.electrical_installations.view.ViewArea;
import com.electrical_installations.view.ViewCharge;
import com.electrical_installations.view.ViewLogin;
import com.electrical_installations.view.ViewProject;
import com.electrical_installations.view.ViewProyectData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Controlador para la vista ViewLogin
 * @author Ing. Richard David
 * @version 1
 * @since 2015-07-20
 */
public class ControllerLogin implements ActionListener, WindowListener, KeyListener{

    //Campos de la clase
    private final ViewLogin viewLogin;
    private ViewProject viewProject; 
    private ViewProyectData viewProyectData;
    private ViewCharge viewCharge;
    private ViewArea viewArea;
    private static final Messages messages = Messages.getInstance();
    private static final Session session = Session.getInstance();
    private char character;
    private User user;

    /**
     * Constructor de la clase, recibe como parámetro la clave ViewLogin
     * @param viewLogin 
     */
    public ControllerLogin(ViewLogin viewLogin){
        this.character = 0;
        this.viewLogin = viewLogin;
    }//Fin del constructor
        
    /**
     * Método para validar que todos los campos obligatorios sean completados.
     */
    private boolean validate_fields(){
        if (viewLogin.getTxtUserName().getText().equals("")){
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.EMPTY_USERNAME), MessagesStructure.justify));
            viewLogin.getTxtUserName().requestFocus();
            return false;
        } else if (viewLogin.getTxtPassword().getText().equals("")){
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.EMPTY_PASSWORD), MessagesStructure.justify));
            viewLogin.getTxtPassword().requestFocus();
            return false;
        } 
        return true;
    }
    
    /**
     * Método login: Este método válida que todos los campos obligatorios sean completados
     * y llama al servicio login, al cual se le pasa como parámetro el usuario que esta intentado acceder 
     * al sistema, en caso de que todo se ejecute correctamente se destruye ViewLogin y se da acceso a ViewMenu.
     */
    public void login(){
        if (validate_fields()){
            user = ServiceUser.login(new User(viewLogin.getTxtUserName().getText(),viewLogin.getTxtPassword().getText()));
            if (user != null){ 
                session.session_data(user);
                this.viewLogin.setVisible(false);                     
                this.viewProject = new ViewProject();
                this.viewProject.dispose();
                this.viewProyectData = new ViewProyectData(null, true);
                this.viewProyectData.dispose();
                this.viewCharge = new ViewCharge(null, true);
                this.viewCharge.dispose();
                this.viewArea = new ViewArea(null, true,"");
                this.viewArea.dispose(); 
                this.viewLogin.dispose();
                this.viewProject = new ViewProject();
                this.viewProject.setVisible(true);
            }
        }
    }//Fin del método
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(viewLogin.getBtnLogin())){
            login();
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {        
        if (MessagesStructure.ConfirmationMessage(MessagesStructure.format(100, messages.getProperty(Messages.LOG_OUT), MessagesStructure.justify)) == 0){        
            System.exit(0);
        } else {
            viewLogin.setDefaultCloseOperation(0);
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
    public void keyTyped(KeyEvent e) {
        character = e.getKeyChar();
        if (e.getSource().equals(viewLogin.getTxtUserName())) {
            if (viewLogin.getTxtUserName().getText().length() == 20) {
                viewLogin.getToolkit().beep();
                e.consume();                
            } else if (character == (char) KeyEvent.VK_ENTER) {
                login();
            } 
        } else if (e.getSource().equals(viewLogin.getTxtPassword())) {
            if (viewLogin.getTxtPassword().getText().length() == 20) {
                viewLogin.getToolkit().beep();
                e.consume();                
            } else if (character == (char) KeyEvent.VK_ENTER) {
                login();
            } 
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
