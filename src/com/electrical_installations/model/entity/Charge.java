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

    //Objetos, variables y constantes.
    private int code;
    private String name;
    private int potency;
    private Energy energy;
    private boolean horsePower;
    private boolean dryer;
    private boolean electricKitchen;
    private TypeCharges typeCharges;
    
    /**
     * Constructor para la búsqueda de Cargas.
     * @param code
     * @param name
     * @param potency
     * @param horsePower
     * @param dryer
     * @param electricKitchen
     * @param typeCharges 
     */
    public Charge(int code,String name,int potency, boolean horsePower, boolean dryer, boolean electricKitchen, TypeCharges typeCharges){
        this.code = code;
        this.name = name;
        this.potency = potency;
        this.horsePower = horsePower;
        this.dryer = dryer;
        this.electricKitchen = electricKitchen;
        this.typeCharges = typeCharges;
    }//Fin del constructor
    
    /**
     * Constructor para la búsqueda de Cargas filtradas por nombre.
     * @param name 
     */
    public Charge(String name){
        this.name = name;
    }//Fin del constructor
        
    //Getters y Setters

    public boolean isElectricKitchen() {
        return electricKitchen;
    }

    public void setElectricKitchen(boolean electricKitchen) {
        this.electricKitchen = electricKitchen;
    }

    public boolean isDryer() {
        return dryer;
    }

    public void setDryer(boolean dryer) {
        this.dryer = dryer;
    }
    
    public boolean isHorsePower() {
        return horsePower;
    }

    public void setHorsePower(boolean horsePower) {
        this.horsePower = horsePower;
    }

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

    public TypeCharges getTypeCharges() {
        return typeCharges;
    }

    public void setTypeCharges(TypeCharges typeCharges) {
        this.typeCharges = typeCharges;
    }
    
    

}
