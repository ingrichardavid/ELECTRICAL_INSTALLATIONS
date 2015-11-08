/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.entity;

import java.util.Objects;

/**
 * Clase Entidad Tipo de Cargas.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-10-21
 */
public class TypeCharges {
    
    //Objetos, variables y constantes.
    private int code;
    private String name;
    private String type;

    /**
     * Constructor para la busqueda de los tipos de Cargas
     * @param code
     * @param name
     * @param type 
     */
    public TypeCharges(int code, String name, String type) {
        this.code = code;
        this.name = name;
        this.type = type;
    }
        
    //getters y setters

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    //fin de los getters y setters

    
    //toString y hashCode
    @Override
    public String toString() {
        return type;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.type);
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
        final TypeCharges other = (TypeCharges) obj;
        if (this.code != other.code) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }
    
    //fin del toString y hashCode
    
}
