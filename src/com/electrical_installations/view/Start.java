/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.view;
import javax.swing.JFrame;
import org.jvnet.substance.SubstanceLookAndFeel;

/**
 * Clase que encapsula el inicio de la aplicación.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-20
 */
public class Start {    
    
    /**
     * Método main para iniciar la aplicación.
     * @param args 
     */
    public static void main(String args[]) { 
        
        /**
         * Configuración de la librería Substance para dar look a la aplicación. 
         */
        JFrame.setDefaultLookAndFeelDecorated(true);
        SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.ModerateSkin");
        SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.SubstanceColorBlindTheme");
        SubstanceLookAndFeel.setCurrentWatermark("org.jvnet.substance.watermark.SubstanceCopperplateEngravingWatermark");
        ViewLogin viewLogin  = new ViewLogin();
        viewLogin.setVisible(true);
        
    }
    
} 