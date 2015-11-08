/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.entity.masters;

/**
 * Clase para representar la entidad Valor.
 *
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-08
 */
public class Value {

    //Objetos, variables y constantes
    private int code;
    private double value;

    /**
     * Constructor para crear, modificar, eliminar y buscar el Valor.
     *
     * @param code
     * @param valour
     */
    public Value(int code, double valour) {
        this.code = code;
        this.value = valour;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public double getValour() {
        return value;
    }

    //Getters y Setters
    public void setValour(double valour) {
        this.value = valour;
    }

    //toString()
    @Override
    public String toString() {
        return String.valueOf(value);
    }

    //equals and hashCode
    @Override
    public int hashCode() {
        int hash = 7;
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
        final Value other = (Value) obj;
        if (this.code != other.code) {
            return false;
        }
        if (Double.doubleToLongBits(this.value) != Double.doubleToLongBits(other.value)) {
            return false;
        }
        return true;
    }//Fin del equals and hashCode
    
}
