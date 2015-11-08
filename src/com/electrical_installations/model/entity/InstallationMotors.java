/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.entity;

import com.electrical_installations.model.enums.TypePhase;

/**
 * Clase entidad Motores en la Instalación.
 * @author JRichard
 * @version 1
 * @since 2015-10-31
 */
public class InstallationMotors {
    
    //Objetos, variables y constantes
    private int code; 
    private Project project; 
    private String description;
    private double intensity;
    private int quantity;    
    private String caliberPhase;
    private String caliberNeutral;
    private String caliberHearth; 
    private TypePhase typePhase;
    private String horse_power;
    private double breaker;
    
    /**
     * Constructor para buscar, insertar, modificar y eliminar Motores en la Instalación
     * @param code
     * @param project
     * @param description
     * @param intensity
     * @param quantity
     * @param caliberPhase
     * @param caliberNeutral
     * @param caliberHearth 
     * @param typePhase 
     * @param horse_power 
     * @param breaker 
     */
    public InstallationMotors(int code, Project project, String description, double intensity, int quantity, String caliberPhase, String caliberNeutral, String caliberHearth, TypePhase typePhase, String horse_power, double breaker) {
        this.code = code;
        this.project = project;
        this.description = description;
        this.intensity = intensity;
        this.quantity = quantity;
        this.caliberPhase = caliberPhase;
        this.caliberNeutral = caliberNeutral;
        this.caliberHearth = caliberHearth;
        this.typePhase = typePhase;
        this.horse_power = horse_power;
        this.breaker = breaker;
    }//Fin del constructor

    /**
     * Constructor para eliminar un motor de una instalación.
     * @param code
     * @param project 
     * @param intensity
     * @param quantity 
     */
    public InstallationMotors(int code, Project project, double intensity, int quantity) {
        this.code = code;
        this.project = project;
        this.intensity = intensity;
        this.quantity = quantity;
    }//Fin del constructor
    
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
 
    public double getIntensity() {
        return intensity;
    }

    public void setIntensity(double intensity) {
        this.intensity = intensity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCaliberPhase() {
        return caliberPhase;
    }

    public void setCaliberPhase(String caliberPhase) {
        this.caliberPhase = caliberPhase;
    }

    public String getCaliberNeutral() {
        return caliberNeutral;
    }

    public void setCaliberNeutral(String caliberNeutral) {
        this.caliberNeutral = caliberNeutral;
    }

    public String getCaliberHearth() {
        return caliberHearth;
    }

    public void setCaliberHearth(String caliberHearth) {
        this.caliberHearth = caliberHearth; 
        
    } 

    public TypePhase getTypePhase() {
        return typePhase;
    }

    public void setTypePhase(TypePhase typePhase) {
        this.typePhase = typePhase; 
    } 

    public String getHorse_power() {
        return horse_power;
    }

    public void setHorse_power(String horse_power) {
        this.horse_power = horse_power;
    } 
    
    public double getBreaker() {
        return breaker;
    }

    public void setBreaker(double breaker) {
        this.breaker = breaker;
    }//fin getters y setters
    
    
}
