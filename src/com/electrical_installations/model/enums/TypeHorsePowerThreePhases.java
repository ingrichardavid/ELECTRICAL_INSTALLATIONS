/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.enums;

/**
 * Clase enum para enumerar los caballos de fuerza trifásicos.
 * @author JRichard
 * @version 1
 * @since 2015-11-01
 */
public enum TypeHorsePowerThreePhases {

     a_half("1/2",1),three_quarts("3/4",2),one("1",3),one_and_a_half("1 1/2",4),two("2",5),three("3",6),five("5",7)
    ,seven_and_a_half("7 1/2",8),ten("10",9),fifteen("15",10),twenty("20",11),Twentyfive("25",12),thirty("30",13)
    ,forty("40",14),fifty("50",15),sixty("60",16),seventy_five("75",17),hundred("100",18)
    ,one_hundred_twenty_five("125",19),hundred_fifty("150",20),two_Hundred("200",21),two_hundred_and_fifty("250",22)
    ,three_hundred("300",23),three_hundred_and_fifty("350",24),four_hundred("400",25),four_hundred_and_fifty("450",26)
    ,five_hundred("500",27);
 
    //Objetos, variables y constantes
    private final String name;
    private final Integer priority;    
     
    /**
     * Constructor que recibe un nombre y una prioridad.
     * @param name
     * @param priority 
     */
    private TypeHorsePowerThreePhases(String name,int priority){ 
      this.name = name;
      this.priority = priority;
    } 
    
    /**
     * Método para obtener los caballos de potencia trifásicos.
     * @return Retorna la marca del cable
     */
    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }
     
    
}
