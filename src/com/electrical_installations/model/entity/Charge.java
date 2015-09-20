/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.entity;

/**
 * Clase Entidad Carga.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-01
 */
public class Charge {

    //Objetos, variables y constantes
    private int code;
    private String name;
    private int potency;
    private Energy energy;
    
    /**
     * Constructor para la búsqueda de Cargas
     * @param code
     * @param name 
     * @param potency
     */
    public Charge(int code,String name,int potency){
        this.code = code;
        this.name = name;
        this.potency = potency;
    }//Fin del constructor
    
    /**
     * Constructor para la búsqueda de Cargas filtradas por nombre
     * @param name 
     */
    public Charge(String name){
        this.name = name;
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

    public int getPotency() {
        return potency;
    }

    public void setPotency(int potency) {
        this.potency = potency;
    }

    public Energy getEnergy() {
        return energy;
    }

    public void setEnergy(Energy energy) {
        this.energy = energy;
    }

}
