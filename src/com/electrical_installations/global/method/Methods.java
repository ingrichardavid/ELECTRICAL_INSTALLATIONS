/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.global.method;

import com.electrical_installations.configuration.Messages;
import com.electrical_installations.configuration.MessagesStructure;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Clase que contiene métodos estáticos para ser utilizados en cualquier parte de la aplicación
 * @author Ing. Richard David
 * @version 1
 * @since 2015-07-20
 */
public class Methods {
    
    //Objetos, variables y constantes
    private static final Messages messages = Messages.getInstance();
    
    /**
     * Método para dar formato a textos, cantidades numéricas, etc.
     * @param pattern
     * @param value
     * @return Retorna el valor formateado.
     */
    public static final String customFormat(String pattern, double value){
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        String outPut = myFormatter.format(value);
        return outPut;
    }//Fin del Método
    
    /**
     * Método para remover una fila de una tabla
     * @param table 
     */
    public static final void removeRow(JTable table) {
        try {
            ((DefaultTableModel) table.getModel()).removeRow(table.getSelectedRow());
        } catch (ArrayIndexOutOfBoundsException e) {
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.NOT_SELECT_ROW), MessagesStructure.justify));
            table.requestFocus();
        }
    }//Fin del método

    /**
     * Método para remover todas las filas de una tabla
     * @param table 
     */
    public static final void removeRows(JTable table) {
        while (((DefaultTableModel) table.getModel()).getRowCount() > 0) {
            ((DefaultTableModel) table.getModel()).removeRow(0);
        }
    }//Fin del método
    
    /**
     * Método que permite obtener la fecha actual del sistem en formato dd/MM/yyyy
     * @return Retorna un String con la fecha formateada.
     */
    public static String get_date(){
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }//Fin del método
    
    /**
     * Método para quitar espacios en blanco de un texto, recice un texto y retorna el texto sin espacios
     * @param text 
     * @return Retorna el texto sin espacios 
     */
    public static String replace_space(String text){
        return text.replace(" ", "");

    }//Fin del método
    
    /**
     * Método para comparar si dos objetos tipo String son iguales.
     * @param string1
     * @param string2
     * @return Retorna true en caso de que los objetos sean iguales, false en caso contrario
     */    
    public static boolean compare(String string1,String string2){
        return string1.trim().toLowerCase().equals(string2.trim().toLowerCase());
    }//Fin del método
    
    /**
     * Método que redondea valores flotantes, recibe una cantidad y el número de decimales a los que se desea redondear.
     * @param quantity
     * @param decimal
     * @return Retorna la cantidad redondeada a los decimales especificados
     */
    public static double round(double quantity, int decimal) {
        int digit = (int) Math.pow(10, decimal);
        return Math.rint(quantity * digit) / digit;
    }//Fin del método
    
    /**
     * Método para validar que un caracter tipeado sea solo texto o espacio
     * @param character
     * @return Retorna true si el caracter no es texto.
     */
    public static boolean validate_text_only(char character){
        return (character < 'a' || character > 'z') && (character < 'A' || character > 'Z') && (character != '`') && (character != (char) KeyEvent.VK_SPACE) && (character != (char) KeyEvent.VK_ENTER)
            && (character != 'á') && (character != 'é') && (character != 'í') && (character != 'ó') && (character != 'ú')
            && (character != 'Á') && (character != 'É') && (character != 'Í') && (character != 'Ó') && (character != 'Ú');
    }//Fin del método
    
    /**
     * Método para validar que un caracter ingresado sea numérico
     * @param character
     * @return Retorna true en caso de que el caracter no sea numérico.
     */
    public static boolean validate_number_only(char character){        
        return (character < '0' || character > '9') && (character != (char) KeyEvent.VK_BACK_SPACE) && (character != (char) KeyEvent.VK_ENTER);                
    }//Fin del método
        
    /**
     * Método para validar que un caracter ingresado sea numérico o espacio.
     * @param character
     * @return Retorna true en caso de que el caracter no sea numérico o espacio.
     */
    public static boolean validate_only_number_or_space(char character){        
        return (character < '0' || character > '9') && (character != (char) KeyEvent.VK_BACK_SPACE) && (character != (char) KeyEvent.VK_ENTER) && (character != (char) KeyEvent.VK_SPACE);                
    }//Fin del método
    
    /**
     * Método para validar que un caracter ingresado sea numérico decimal
     * @param character
     * @return Retorna true en caso de que el caracter no sea numérico decimal.
     */
    public static boolean validate_number_decimal(char character){        
        return (character < '0' || character > '9') && (character != '.')  && (character != (char) KeyEvent.VK_BACK_SPACE) && (character != (char) KeyEvent.VK_ENTER);                
    }//Fin del método
    
    /**
     * Método para validar que un caracter sea alfanumérico y no un caractér extraño Ej: ?!,'.
     * @param character
     * @return Retorna true en caso de que el caracter no sea alfanumérico
     */
    public static boolean validate_alphanumeric(char character) {
        return (character < 'a' || character > 'z') && (character < 'A' || character > 'Z') && (character < '0' || character > '9') && (character != (char) KeyEvent.VK_SPACE) && (character != (char) KeyEvent.VK_ENTER)
                && (character != 'á') && (character != 'é') && (character != 'í') && (character != 'ó') && (character != 'ú')
                && (character != 'Á') && (character != 'É') && (character != 'Í') && (character != 'Ó') && (character != 'Ú');

    }//Fin del método
    
}
