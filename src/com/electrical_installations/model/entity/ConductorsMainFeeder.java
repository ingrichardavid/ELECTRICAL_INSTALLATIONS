/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.entity;

/**
 * Clase Entidad Conductores para el alimentador pricipal.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-11-19
 */
public class ConductorsMainFeeder {

    //Objetos, variables y constantes
    private Project project;
    private String phase, neutral, hearth, pipelinePhase, pipelineNeutral;
    private int conductorPhase, conductorNeutral;

    /**
     * Constructor para registro, modificación y eliminación.
     * @param project
     * @param phase
     * @param neutral
     * @param hearth
     * @param pipelinePhase
     * @param pipelineNeutral
     * @param conductorPhase
     * @param conductorNeutral 
     */
    public ConductorsMainFeeder(Project project, String phase, String neutral, String hearth, String pipelinePhase, String pipelineNeutral, int conductorPhase, int conductorNeutral) {
        this.project = project;
        this.phase = phase;
        this.neutral = neutral;
        this.hearth = hearth;
        this.pipelinePhase = pipelinePhase;
        this.pipelineNeutral = pipelineNeutral;
        this.conductorPhase = conductorPhase;
        this.conductorNeutral = conductorNeutral;
    }//Fin del Constructor.
    
    //Getters y Setters
    
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getNeutral() {
        return neutral;
    }

    public void setNeutral(String neutral) {
        this.neutral = neutral;
    }

    public String getHearth() {
        return hearth;
    }

    public void setHearth(String hearth) {
        this.hearth = hearth;
    }

    public String getPipelinePhase() {
        return pipelinePhase;
    }

    public void setPipelinePhase(String pipelinePhase) {
        this.pipelinePhase = pipelinePhase;
    }

    public String getPipelineNeutral() {
        return pipelineNeutral;
    }

    public void setPipelineNeutral(String pipelineNeutral) {
        this.pipelineNeutral = pipelineNeutral;
    }

    public int getConductorPhase() {
        return conductorPhase;
    }

    public void setConductorPhase(int conductorPhase) {
        this.conductorPhase = conductorPhase;
    }

    public int getConductorNeutral() {
        return conductorNeutral;
    }

    public void setConductorNeutral(int conductorNeutral) {
        this.conductorNeutral = conductorNeutral;
    }       
        
}
