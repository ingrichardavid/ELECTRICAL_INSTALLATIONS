/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.enums;

/**
 * Clase enum para enumerar los caballos de fuerza monofásicos.
 * @author JRichard
 * @version 1
 * @since 2015-11-01
 */
public enum TypeHorsePowerSinglePhases {
    
    a_sixth("1/6",1), fourth("1/4",2),one_third("1/3",3),a_half("1/2",4),three_quarts("3/4",5),one("1",6),
    one_and_a_half("1 1/2",7),two("2",8),three("3",9),five("5",10),seven_and_a_half("7 1/2",11),ten("10",12);

    //Objetos, variables y constantes
    private final String name;
    private final Integer priority;    
     
    /**
     * Constructor que recibe un nombre y una prioridad.
     * @param name
     * @param priority 
     */
    private TypeHorsePowerSinglePhases(String name,int priority){ 
      this.name = name;
      this.priority = priority;
    } 
    
    /**
     * Método para obtener los caballos de potencia monofásica.
     * @return Retorna la marca del cable
     */
    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }
     
    
}
