/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.enums;

/**
 * Clase enum para enumerar los tipos de cargas.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-10-25
 */
public enum TypeTypeOfCharges {
    
    ILUMINARIA_POWER_POINT("Iluminaria y Toma Corriente");
    
    //Objetos, variables y constantes
    private final String typeCharge;
    
    /**
     * Constructor que recibe un typeCharge
     * @param typeCharge 
     */
    private TypeTypeOfCharges(String typeCharge){
        this.typeCharge = typeCharge;
    }//Fin del constructor
    
    /**
     * Método para obtener el tipo de carga
     * @return Retorna un tipo de carga
     */
    public String getTypeCharge(){
        return typeCharge;
    }//Fin del método
    
}
