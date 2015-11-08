/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.entity.masters;

import java.util.Objects;

/**
 * Clase para representar la entidad Calibre.
 *
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-08
 */
public class Caliber {

    //Objetos, variables y constantes
    private int code;
    private String name;

    /**
     * Constructor para crear, modificar, eliminar y buscar el Calibre.
     *
     * @param code
     * @param name
     */
    public Caliber(int code, String name) {
        this.code = code;
        this.name = name;
    }//Fin del contructor.

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

    //toString()
    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.code;
        hash = 17 * hash + Objects.hashCode(this.name);
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
        final Caliber other = (Caliber) obj;
        if (this.code != other.code) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

}
