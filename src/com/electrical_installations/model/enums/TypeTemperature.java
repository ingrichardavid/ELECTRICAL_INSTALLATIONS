/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.enums;

/**
 * Clase para enumerar los tipos de temperaturas
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-13
 */
public enum TypeTemperature {
    
    TEMPERATURE_60(60),TEMPERATURE_75(75);
    
    //Objetos, variables y constantes
    private final int temperature;
    
    /**
     * Constructor privado que recibe una temperatura
     * @param temperature 
     */
    private TypeTemperature(int temperature){
        this.temperature = temperature;
    }//Fin del constructor
    
    /**
     * Método para obtener la temperatura
     * @return Retorna la temperatura
     */
    public int getTemperature(){
        return temperature;
    }//Fin del método
    
}
