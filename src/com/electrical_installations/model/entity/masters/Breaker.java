/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.entity.masters;

/**
 * Clase para representar a la entidad Interruptor.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-17
 */
public class Breaker {
    
    //Objetos, variables y constantes
    private int code;
    private double capacity;
    private Unit unit;

    /**
     * Constructor para encontrar valores de capacidades.
     * @param code
     * @param capacity 
     */
    public Breaker(int code, double capacity) {
        this.code = code;
        this.capacity = capacity;
    }//Fin del constructor

    //Getters y Setters
    
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
    
}
