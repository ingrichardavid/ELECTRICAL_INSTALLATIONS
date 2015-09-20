/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.entity;

/**
 * Clase Entidad Energía.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-01
 */
public class Energy {

    //Objetos, variables y constantes
    private int code;
    private String name;
    private EnergyUnit energyUnit;
    
    /**
     * Constructor para la búsqueda de Energías
     * @param code
     * @param name 
     * @param energyUnit 
     */
    public Energy(int code,String name,EnergyUnit energyUnit){
        this.code = code;
        this.name = name;
        this.energyUnit = energyUnit;
    }//Fin del constructor
    
    //Getters y Setters

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EnergyUnit getEnergyUnit() {
        return energyUnit;
    }

    public void setEnergyUnit(EnergyUnit energyUnit) {
        this.energyUnit = energyUnit;
    }
    
}
