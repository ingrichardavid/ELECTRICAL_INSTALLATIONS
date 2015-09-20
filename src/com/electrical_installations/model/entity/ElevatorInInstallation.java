/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.entity;

/**
 * Clase Entidad Elevador en la instalación
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-26
 */
public class ElevatorInInstallation {

    //Objetos, variables y constantes
    private Project project;
    private Elevator elevator;
    private int quantity;
    private int potency;
    private String temperature;
    private String material;
    private String phase;
    private int voltage;
    
    /**
     * Constructor para la Inserción, modificación, eliminación y consulta de Elevadores dentro de una instalación
     * @param project     
     * @param elevator     
     * @param quantity     
     * @param potency     
     * @param temperature     
     * @param material     
     * @param phase     
     * @param voltage     
     */
    public ElevatorInInstallation(Project project, Elevator elevator, int quantity, int potency, String temperature, String material, String phase, int voltage) {
        this.project = project;
        this.elevator = elevator;
        this.quantity = quantity;
        this.potency = potency;
        this.temperature = temperature;
        this.material = material;
        this.phase = phase;
        this.voltage = voltage;
    }//Fin del constructor

    //Getters y Setters

    public int getVoltage() {
        return voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }
    
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Elevator getElevator() {
        return elevator;
    }

    public void setElevator(Elevator elevator) {
        this.elevator = elevator;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPotency() {
        return potency;
    }

    public void setPotency(int potency) {
        this.potency = potency;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

}
