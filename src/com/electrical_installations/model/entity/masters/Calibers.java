/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.entity.masters;

import java.util.Objects;

/**
 * Clase para representar a la entidad Calibres.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-13
 */
public class Calibers {
    
    //Objetos, variables y contantes
    private int code;
    private Material material;
    private Temperature temperature;
    private Intensity intensity;
    private Caliber caliber;
    private int branchCircuit;

    /**
     * Constructor para almacenar los datos del calibre encontrado.
     * @param code
     * @param caliber 
     */
    public Calibers(int code, Caliber caliber) {
        this.code = code;
        this.caliber = caliber;
    }//Fin del constructor
    
    /**
     * Constructor para encontrar el calibre deseado.
     * @param code
     * @param material
     * @param temperature
     * @param intensity 
     * @param caliber 
     */
    public Calibers(int code, Material material, Temperature temperature, Intensity intensity, Caliber caliber) {
        this.code = code;
        this.material = material;
        this.temperature = temperature;
        this.intensity = intensity;
        this.caliber = caliber;
    }//Fin del constructor
    //Getters y Setters
    
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public Intensity getIntensity() {
        return intensity;
    }

    public void setIntensity(Intensity intensity) {
        this.intensity = intensity;
    }

    public Caliber getCaliber() {
        return caliber;
    }

    public void setCaliber(Caliber caliber) {
        this.caliber = caliber;
    }

    public int getBranchCircuit() {
        return branchCircuit;
    }

    public void setBranchCircuit(int branchCircuit) {
        this.branchCircuit = branchCircuit;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.code;
        hash = 43 * hash + Objects.hashCode(this.material);
        hash = 43 * hash + Objects.hashCode(this.temperature);
        hash = 43 * hash + Objects.hashCode(this.intensity);
        hash = 43 * hash + Objects.hashCode(this.caliber);
        hash = 43 * hash + this.branchCircuit;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Calibers other = (Calibers) obj;
        if (this.code != other.code) {
            return false;
        }
        if (!Objects.equals(this.material, other.material)) {
            return false;
        }
        if (!Objects.equals(this.temperature, other.temperature)) {
            return false;
        }
        if (!Objects.equals(this.intensity, other.intensity)) {
            return false;
        }
        if (!Objects.equals(this.caliber, other.caliber)) {
            return false;
        }
        if (this.branchCircuit != other.branchCircuit) {
            return false;
        }
        return true;
    }
        
}
