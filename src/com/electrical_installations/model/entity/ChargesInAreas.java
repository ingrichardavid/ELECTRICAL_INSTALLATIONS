/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.entity;

/**
 * Clase Entidad Cargas en Áreas.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-02
 */
public class ChargesInAreas {

    //Objetos, variables y constantes
    private Charge charge;
    private Area area;
    private int quantity;
    private int voltage;
    private String temperature;
    private String material;
    private String phase;
    
    /**
     * Constructor para la inserción, modificación, eliminación y búsqueda de Cargas en Áreas
     * @param charge
     * @param area
     * @param quantity
     * @param voltage
     */
    public ChargesInAreas(Charge charge, Area area, int quantity, int voltage, String temperature, String material, String phase){
        this.charge = charge;
        this.area = area;
        this.quantity = quantity;
        this.voltage = voltage;
        this.temperature = temperature;
        this.material = material;
        this.phase = phase;
    }//Fin del constructor
            
    //Getters y Setters

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

    public int getVoltage(){
        return voltage;
    }
    
    public void setVoltage(int voltage){
        this.voltage = voltage;
    }
    
    public Charge getCharge() {
        return charge;
    }

    public void setCharge(Charge charge) {
        this.charge = charge;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }    

}
