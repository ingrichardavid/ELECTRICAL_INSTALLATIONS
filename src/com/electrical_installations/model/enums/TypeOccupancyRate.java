/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.enums;

/**
 * Clase enum para enumerar los porcentajes de ocupación
 * @author Ing. Richard David
 * @version 1
 * @since 2015-11-15
 */
public enum TypeOccupancyRate {
    
    THIRTY_ONE_PERCENT("0,31",0.31),FORTY("0,4",0.4);
    
    //Objetos, variables y constantes
    private final String name;
    private final double percentage;
    
    /**
     * Constructor que recibe el nombre y porcentaje de ocupación.
     * @param name 
     * @param percentage
     */
    private TypeOccupancyRate(String name, double percentage){
        this.name = name;
        this.percentage = percentage;
    }//Fin del constructor
    
    /**
     * Método para obtener el nombre
     * @return Retorna un nombre
     */
    public String getName(){
        return name;
    }//Fin del método
    
    /**
     * Método para obtener el porcentaje
     * @return Retorna un porcentaje
     */
    public double getPercentage(){
        return percentage;
    }//Fin del método
    
}
