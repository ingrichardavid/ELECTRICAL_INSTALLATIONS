/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.entity.masters;

import java.util.Objects;

/**
 * Clase para representar a la entidad Porcentaje Motores Monofásicos.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-20
 */
public class PercentageSinglePhaseMotors {
    
    //Objetos, variables y constantes
    private int code;
    private String name;
    private int percentage;
    private Unit unit;

    /**
     * Constructor para encontrar valores de Porcentaje Motores Monofásicos.
     * @param code 
     * @param name 
     * @param percentage 
     */
    public PercentageSinglePhaseMotors(int code, String name, int percentage) {
        this.code = code;
        this.name = name;
        this.percentage = percentage;
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

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
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
        int hash = 7;
        hash = 89 * hash + this.code;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + this.percentage;
        hash = 89 * hash + Objects.hashCode(this.unit);
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
        final PercentageSinglePhaseMotors other = (PercentageSinglePhaseMotors) obj;
        if (this.code != other.code) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.percentage != other.percentage) {
            return false;
        }
        if (!Objects.equals(this.unit, other.unit)) {
            return false;
        }
        return true;
    }
    
}
