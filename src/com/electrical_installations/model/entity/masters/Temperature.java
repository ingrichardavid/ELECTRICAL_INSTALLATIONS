/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.entity.masters;

import java.util.Objects;

/**
 * Clase para representar la entidad Temperatura.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-06
 */
public class Temperature {
    
    //Objetos, variables y constantes
    private int code;
    private Unit unit;
    private int quantity;

    /**
     * Constructor para encontrar y eliminar Temperaturas.
     * @param code 
     */
    public Temperature(int code) {
        this.code = code;
    }//Fin del constructor

    /**
     * Constructor para crear, modificar, eliminar y encontrar Temperaturas.
     * @param code
     * @param unit
     * @param quantity 
     */
    public Temperature(int code, Unit unit, int quantity) {
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

    @Override
    public String toString() {
        return unit.getName();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Temperature other = (Temperature) obj;
        if (this.code != other.code) {
            return false;
        }
        if (!Objects.equals(this.unit, other.unit)) {
            return false;
        }
        if (this.quantity != other.quantity) {
            return false;
        }
        return true;
    }
    
}
