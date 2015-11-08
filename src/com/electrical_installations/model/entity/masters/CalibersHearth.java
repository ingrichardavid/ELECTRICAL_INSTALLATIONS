/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.entity.masters;

/**
 * Clase para representar a la entidad conductores de Tierra.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-10-04
 */
public class CalibersHearth {
    
    //Objetos, variables y contantes
    private int code;
    private Intensity intensity;
    private Caliber caliber;
    private Material material;

    /**
     * Constructor para almacenar datos de los conductores de tierra.
     * @param code
     * @param intensity
     * @param caliber
     * @param material 
     */
    public CalibersHearth(int code, Intensity intensity, Caliber caliber, Material material) {
        this.code = code;
        this.intensity = intensity;
        this.caliber = caliber;
        this.material = material;
    }

    //Getters y Setters
    
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
        
}
