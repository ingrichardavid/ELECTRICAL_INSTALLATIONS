/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.enums;

/**
 * Clase enum para enumerar la Reactancia y Resistencia
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-13
 */
public enum TypeResistancesAndReactances {
    
    REACTANCE("T"),RESISTENCE("R");
    
    //Objetos, variables y constantes
    private final String type;
    
    /**
     * Constructor que recibe un tipo
     * @param type 
     */
    private TypeResistancesAndReactances(String type){
        this.type = type;
    }//Fin del constructor
    
    /**
     * Método para obtener el tipo.
     * @return Retorna el tipo
     */
    public String getType(){
        return type;
    }//Fin del método
    
}
