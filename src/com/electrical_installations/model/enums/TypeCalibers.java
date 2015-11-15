/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.enums;

/**
 * Clase enum para enumerar los tipos de Calibres
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-23
 */
public enum TypeCalibers {
    
    PHASE("F"),NEUTRO("N"),EARTH("E");
    
    //Objetos, variables y constantes
    private final String type;
    
    /**
     * Constructor que recibe un type
     * @param type 
     */
    private TypeCalibers(String type){
        this.type = type;
    }//Fin del constructor
    
    /**
     * Método para obtener el type
     * @return Retorna un type
     */
    public String getType(){
        return type;
    }//Fin del método
    
}
