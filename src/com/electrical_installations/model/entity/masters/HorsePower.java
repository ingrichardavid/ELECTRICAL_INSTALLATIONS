/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.entity.masters;

import java.util.Objects;

/**
 * Clase para representar a la entidad Caballo de Potencia.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-20
 */
public class HorsePower {
    
    //Objetos, variables y constantes
    private int code;
    private String name;
    private Unit unit;
    private double value;

    /**
     * Constructor para encontrar valores de capacidades.
     * @param code
     * @param name
     * @param value
     */
    public HorsePower(int code, String name, double value) {
        this.code = code;
        this.name = name;
        this.value = value;
    }//Fin del constructor

    //Getters y Setters

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
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

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return name;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.code;
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.unit);
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
        final HorsePower other = (HorsePower) obj;
        if (this.code != other.code) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.unit, other.unit)) {
            return false;
        }
        return true;
    }
    
}
