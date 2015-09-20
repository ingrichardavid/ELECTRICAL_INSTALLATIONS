/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.enums;

/**
 * Clase enumerada para almacenar los tipos de circuitos ramales en un Área, (Iluminaria y Toma Corriente).
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-13
 */
public enum TypeOfBranchCircuitInArea {
    
    ILUMINARIA("I"),POWER_POINT("T");
    
    //Objetos, variables y constantes
    private final String type;
    
    /**
     * Constructor que recibe un tipo
     * @param type 
     */
    private TypeOfBranchCircuitInArea(String type){
        this.type = type;
    }//Fin del constructor
    
    /**
     * Método para obtener el tipo
     * @return Retorna un tipo
     */
    public String getType(){
        return type;
    }//Fin del método
    
}
