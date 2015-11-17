/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.entity;

/**
 * Clase entidad de circuito de iluminación.
 * @author JRichard
 * @version 1
 * @since 2015-11-16
 */
public class LightingCircuit {
    
    //Objetos, variables y constantes
    private int code; 
    private Project project;
    private String caliberPhaseNeutral;
    private String pipeline;
    private String description;
    private double intensity_total; 
  
    
    /**
     * Constructor para buscar, insertar, modificar y eliminar Circuito de iluminación.
     * @param code
     * @param project
     * @param caliberPhaseNeutral
     * @param pipeline
     * @param description
     * @param intensity_total 
     */
    public LightingCircuit(int code, Project project, String caliberPhaseNeutral, String pipeline, String description, double intensity_total) {
        this.code = code;
        this.project = project;
        this.caliberPhaseNeutral = caliberPhaseNeutral;
        this.pipeline = pipeline;
        this.description = description;
        this.intensity_total = intensity_total; 
    }
 
    
    //Getters y Setters
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

    public String getCaliberPhaseNeutral() {
        return caliberPhaseNeutral;
    }

    public void setCaliberPhaseNeutral(String caliberPhaseNeutral) {
        this.caliberPhaseNeutral = caliberPhaseNeutral;
    }

    public String getPipeline() {
        return pipeline;
    }

    public void setPipeline(String pipeline) {
        this.pipeline = pipeline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getIntensity_total() {
        return intensity_total;
    }

    public void setIntensity_total(double intensity_total) {
        this.intensity_total = intensity_total;
    }//fin Getters y Setters
 
    
    
}
