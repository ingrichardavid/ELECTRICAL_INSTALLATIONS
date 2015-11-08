/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.entity.masters;

import java.util.Objects;

/**
 * Clase para representar la entidad Intensidad
 *
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-06
 */
public class Intensity {

    //Objetos, variables y constantes
    private int code;
    private Unit unit;
    private double intensity;

    /**
     * Contructor para encontrar y eliminar Intensidades.
     *
     * @param code
     */
    public Intensity(int code) {
        this.code = code;
    }//Fin del constructor

    /**
     * Constructor para insertar, modificar y encontrar Intensidades.
     *
     * @param code
     * @param unit
     * @param intensity
     */
    public Intensity(int code, Unit unit, double intensity) {
        this.code = code;
        this.unit = unit;
        this.intensity = intensity;
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

    public double getIntensity() {
        return intensity;
    }

    public void setIntensity(double intensity) {
        this.intensity = intensity;
    }
    //fin de los Getters y Setters

    //toString()
    @Override
    public String toString() {
        return unit.getName();
    }

    //equals y hashCode
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }//fin del toString

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Intensity other = (Intensity) obj;
        if (this.code != other.code) {
            return false;
        }
        if (!Objects.equals(this.unit, other.unit)) {
            return false;
        }
        if (this.intensity != other.intensity) {
            return false;
        }
        return true;
    }
    //fin equals y hashCode

}
