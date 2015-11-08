/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.entity.masters;

/**
 * Clase para representar la entidad Potencia
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-06
 */
public class Potency {
    
    //Objetos, variables y constantes
    private int code;
    private Unit unit;
    private int quantity;

    /**
     * Contructor para encontrar y eliminar potencias.
     * @param code 
     */
    public Potency(int code) {
        this.code = code;
    }//Fin del constructor

    /**
     * Constructor para insertar, modificar y encontrar potencias.
     * @param code
     * @param unit
     * @param quantity 
     */
    public Potency(int code, Unit unit, int quantity) {
        this.code = code;
        this.unit = unit;
        this.quantity = quantity;
    }//Fin del constructor
    
    //Getters y Setters

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
