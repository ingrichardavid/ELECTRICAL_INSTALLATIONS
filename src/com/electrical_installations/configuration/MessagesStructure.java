/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.configuration;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Clase que contiene la configuración de los mensajes emitidos, ya sean mensajes de información, advertencia, error, etc.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-01
 */
public class MessagesStructure {
    
    //Objectos, variables y constantes
    private static int response = 0;
    public static final String left = "left";    
    public static final String right = "right";
    public static final String center = "center";
    public static final String justify = "justify";
    
    /**
     * Método para dar formato el texto mostrado en la ventana de mensaje.
     * @param width
     * @param message
     * @param alignment
     * @return 
     */
    public static String format(int width, String message, String alignment) {
        return "<html><body><div width='" + width + "px' align='" + alignment + "'>" + message + "</div></body></html>";
    }//Fin del método

    /**
     * Método que muestra un mensaje información.
     * @param message 
     */
    public static void InformationMessage(String message) {
        JOptionPane.showMessageDialog(new JDialog(), message, "Información", JOptionPane.INFORMATION_MESSAGE);
    }//Fin del método

    /**
     * Método que muestra un mensaje de advertencia.
     * @param message 
     */
    public static void Warning(String message) {
        JOptionPane.showMessageDialog(new JDialog(), message, "Advertencia", JOptionPane.WARNING_MESSAGE);
    }//Fin del método

    /**
     * Método que muestra un mensaje de confirmación.
     * @param message
     * @return Retorna 0 en caso de que la respuesta sea Sí y 1 si es No.
     */
    public static int ConfirmationMessage(String message) {
        return response = JOptionPane.showConfirmDialog(new JDialog(), message, "Confirmación", JOptionPane.YES_NO_OPTION);
    }//Fin del método

    /**
     * Método que muestra un mensaje de error.
     * @param message 
     */
    public static void ErrorMessage(String message) {
        JOptionPane.showMessageDialog(new JDialog(), message, "Error", JOptionPane.ERROR_MESSAGE);
    }//Fin del método    
}
