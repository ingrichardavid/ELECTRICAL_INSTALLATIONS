/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.enums;

/**
 * Clase enum para enumerar los Sub-tipos de cargas
 * @author Ing. Richard David
 * @version 1
 * @since 2015-10-22
 */
public enum TypeSubTypeCharge {
    
    INTESITY("I"),POTENCY("P"),QUANTITY("C");
    
    //Objetos, variables y constantes
    private final String subTypeCharge;
    
    /**
     * Constructor que recibe un sub-tipo de carga
     * @param subTypeCharge 
     */
    private TypeSubTypeCharge(String subTypeCharge){
        this.subTypeCharge = subTypeCharge;
    }//Fin del constructor
    
    /**
     * Método para obtener el sub-tipo de carga
     * @return Retorna un sub-tipo de carga
     */
    public String getSubTypeCharge(){
        return subTypeCharge;
    }//Fin del método
    
}
