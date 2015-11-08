/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.entity.masters;

/**
 * Clase para representar a la entidad Velocidad.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-06
 */
public class Speed {
    
    //Objetos, variables y constantes
    private int code;
    private double speed;

    /**
     * Constructor para crear, modificar, eliminar y buscar velocidades.
     * @param code 
     * @param speed 
     */
    public Speed(int code, double speed) {
        this.code = code;
        this.speed = speed;
    }//Fin del constructor
        
    //Getters y Setters

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }    
    
}
