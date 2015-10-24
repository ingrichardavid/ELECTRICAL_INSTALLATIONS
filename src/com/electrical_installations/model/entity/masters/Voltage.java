/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.entity.masters;

import java.util.Objects;

/**
 * Clase para representar la entidad Voltaje.
 *
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-10
 */
public class Voltage {

    //Objetos, variables y constantes
    private int code;
    private int voltage;
    private Unit unit;

    /**
     * Constructor para encontrar y eliminar Voltaje.
     *
     * @param code
     */
    public Voltage(int code) {
        this.code = code;
    }//Fin del constructor

    /**
     * Constructor para crear, modificar, eliminar y encontrar Volataje.
     *
     * @param code
     * @param voltage
     * @param unit
     */
    public Voltage(int code, int voltage, Unit unit) {
        this.code = code;
        this.voltage = voltage;
        this.unit = unit;
    }//Fin del constructor

    //Getters y Setters
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getVoltage() {
        return voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }//fin getters y setters

    //toString
    @Override
    public String toString() {
        return unit.getName();
    }

    //hashCode y Equals

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.code;
        hash = 17 * hash + this.voltage;
        hash = 17 * hash + Objects.hashCode(this.unit);
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
        final Voltage other = (Voltage) obj;
        if (this.code != other.code) {
            return false;
        }
        if (this.voltage != other.voltage) {
            return false;
        }
        if (!Objects.equals(this.unit, other.unit)) {
            return false;
        }
        return true;
    }
   
    //Fin hashCode y Equals

}
