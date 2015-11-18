/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.entity;
 

/**
 * Clase Entidad Alimentador Principal.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-11-17
 */
public class MainFeeder {

    //Objetos, variables y constantes 
    private Project project;
    private Charge charge;
    private double potency;   
    private double intensity;
    private int quantity; 

    
    /**
     * Constructor para insertar, modificar, eliminar y selecionar Alimentadores Principales.
     * @param project
     * @param charge
     * @param potency
     * @param intensity
     * @param quantity 
     */
    public MainFeeder(Project project, Charge charge, double potency, double intensity, int quantity) {
        this.project = project;
        this.charge = charge;
        this.potency = potency;
        this.intensity = intensity;
        this.quantity = quantity;
    }//Fin del Constructor
      

    
    //Getters y Setters
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Charge getCharge() {
        return charge;
    }

    public void setCharge(Charge charge) {
        this.charge = charge;
    }

    public double getPotency() {
        return potency;
    }

    public void setPotency(double potency) {
        this.potency = potency;
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
    }//Fin getters y setters
    
     
}
