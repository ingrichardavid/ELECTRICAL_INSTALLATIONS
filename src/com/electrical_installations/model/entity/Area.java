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
    private double potency_iluminaria_powerPoint_old;
    private double potency_iluminaria_powerPoint_new;
    
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
     * Constructor para la creación de Áreas.
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
     * Constructor para la modificación de Áreas.
     * @param code
     * @param name
     * @param project 
     * @param potency_total 
     * @param neutral 
     * @param quantity 
     * @param potency_iluminaria_powerPoint_old 
     * @param potency_iluminaria_powerPoint_new 
     */
    public Area(int code, String name, Project project, double potency_total, double neutral, int quantity, double potency_iluminaria_powerPoint_old, double potency_iluminaria_powerPoint_new){
        this.code = code;
        this.name = name; 
        this.project = project;
        this.potency_total = potency_total;
        this.neutral = neutral;
        this.quantity = quantity;
        this.potency_iluminaria_powerPoint_old = potency_iluminaria_powerPoint_old;
        this.potency_iluminaria_powerPoint_new = potency_iluminaria_powerPoint_new;
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
           
    public double getPotency_iluminaria_powerPoint_old() {
        return potency_iluminaria_powerPoint_old;
    }

    public void setPotency_iluminaria_powerPoint_old(double potency_iluminaria_powerPoint_old) {
        this.potency_iluminaria_powerPoint_old = potency_iluminaria_powerPoint_old;
    }

    public double getPotency_iluminaria_powerPoint_new() {
        return potency_iluminaria_powerPoint_new;
    }

    public void setPotency_iluminaria_powerPoint_new(double potency_iluminaria_powerPoint_new) {
        this.potency_iluminaria_powerPoint_new = potency_iluminaria_powerPoint_new;
    }

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
