/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.entity;

import com.electrical_installations.model.entity.masters.Phase;

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
    private double potency;
    private int quantity;
    private String caliberPhase;
    private String caliberNeutral;
    private String caliberHearth;
    private Phase phase;
    private String pipeline;
    private String materialPipeline;

    /**
     * Constructor para almacenar, insertar, modificar y eliminar cargas en un área.
     * @param charge
     * @param area
     * @param potency
     * @param quantity
     * @param caliberPhase
     * @param caliberNeutral
     * @param caliberHearth 
     * @param phase 
     * @param pipeline 
     * @param materialPipeline 
     */
    public ChargesInAreas(Charge charge, Area area, double potency, int quantity, String caliberPhase, String caliberNeutral, String caliberHearth, Phase phase, String pipeline, String materialPipeline) {
        this.charge = charge;
        this.area = area;
        this.potency = potency;
        this.quantity = quantity;
        this.caliberPhase = caliberPhase;
        this.caliberNeutral = caliberNeutral;
        this.caliberHearth = caliberHearth;
        this.phase = phase;
        this.pipeline = pipeline;
        this.materialPipeline = materialPipeline;
    }//Fin del constructor 
    
    //Getters y Setters

    public String getPipeline() {
        return pipeline;
    }

    public void setPipeline(String pipeline) {
        this.pipeline = pipeline;
    }

    public String getMaterialPipeline() {
        return materialPipeline;
    }

    public void setMaterialPipeline(String materialPipeline) {
        this.materialPipeline = materialPipeline;
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
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

    public double getPotency() {
        return potency;
    }

    public void setPotency(double potency) {
        this.potency = potency;
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
    
}
