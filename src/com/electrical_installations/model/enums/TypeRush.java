/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.enums;

/**
 * Clase enumerada para almacenar los tipos de acometida.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-13
 */
public enum TypeRush {
    
    UNDERGROUND("S"),AIR("A");
    
    //Objetos, variables y constantes
    private final String rush;
    
    /**
     * Constructor privado, recibe el tipo de acometida.
     * @param rush 
     */
    private TypeRush(String rush){
        this.rush = rush;
    }//FIn del constructor
    
    /**
     * Método para obtener el tipo de acometida.
     * @return Retorna el tipo de acometida
     */
    public String getRush(){
        return rush;
    }//Fin del método.
    
}
