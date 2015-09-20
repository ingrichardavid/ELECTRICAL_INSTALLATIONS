/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.entity;

/**
 * Clase Entidad Area.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-01
 */
public class Area {

    //Objetos, variables y constantes
    private int code;
    private String name;
    private Project project;
    private double potency_total;   
    private double neutral;
    private int quantity;
    
    /**
     * Constructor vacío.
     */
    public Area(){}//Fin del constructor
    
    /**
     * Constructor para la eliminación de Áreas por código.
     * @param code 
     */
    public Area(int code){
        this.code = code;
    }//Fin del constructor 
    
    /**
     * Constructor para la creación y modificación de Areas.
     * @param code
     * @param name
     * @param project 
     * @param potency_total 
     * @param neutral 
     * @param quantity 
     */
        public Area(int code, String name, Project project, double potency_total, double neutral, int quantity){
            this.code = code;
            this.name = name;
            this.project = project;
            this.potency_total = potency_total;
            this.neutral = neutral;
            this.quantity = quantity;
        }//Fin del constructor
    
        /**
     * Constructor para la búsqueda de Áreas
     * @param code
     * @param name
     * @param potency_total
     * @param project
     */
    public Area(int code, String name, double potency_total, Project project) {
        this.code = code;
        this.name = name;
        this.potency_total = potency_total;
        this.project = project;
    } //Fin del constructor
        
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public double getPotency_total() {
        return potency_total;
    }

    public void setPotency_total(double potency_total) {
        this.potency_total = potency_total;
    }

    public double getNeutral() {
        return neutral;
    }

    public void setNeutral(double neutral) {
        this.neutral = neutral;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
