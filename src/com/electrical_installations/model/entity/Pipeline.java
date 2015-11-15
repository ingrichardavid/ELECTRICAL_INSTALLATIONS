/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.entity;

/**
 * Clase Entidad Tubería.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-11-15
 */
public class Pipeline {

    //Objetos, variables y constantes
    private int code;
    private String size;
    private double two_drivers;
    private double three_or_more_drivers;

    /**
     * Constructor para obtener el nombre de la tubería.
     * @param size 
     */
    public Pipeline(String size) {
        this.size = size;
    }//Fin del Constructor.
    
    //Getters y Setters

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getTwo_drivers() {
        return two_drivers;
    }

    public void setTwo_drivers(double two_drivers) {
        this.two_drivers = two_drivers;
    }

    public double getThree_or_more_drivers() {
        return three_or_more_drivers;
    }

    public void setThree_or_more_drivers(double three_or_more_drivers) {
        this.three_or_more_drivers = three_or_more_drivers;
    }
    
}
