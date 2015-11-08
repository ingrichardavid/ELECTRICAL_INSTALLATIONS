/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.entity;

/**
 * Clase Entidad Unidad de Energía
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-01
 */
public class EnergyUnit {

    //Objetos, variables y constantes
    private int code;
    private String name;
        
    /**
     * Constructor para la búsqueda de unidades de energía
     * @param code
     * @param name 
     */
    public EnergyUnit(int code,String name){
        this.code = code;
        this.name = name;
    }
    
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
    
}
