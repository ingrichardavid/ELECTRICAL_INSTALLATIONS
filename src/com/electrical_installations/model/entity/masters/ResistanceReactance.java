/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.entity.masters;

import com.electrical_installations.model.enums.TypeResistancesAndReactances;

/**
 * Clase para representar a la entidad Resistencia y Reactancia
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-13
 */
public class ResistanceReactance {
    
    //Objetos, variables y constantes
    private int code;
    private Caliber caliber;
    private Duct duct;
    private Value value;
    private Material material;
    private TypeResistancesAndReactances reactancesAndResistances;
    
    /**
     * Constructor para encontrar Resistencias o Reactancias.
     * @param caliber
     * @param duct
     * @param material
     * @param reactancesAndResistances 
     */
    public ResistanceReactance(Caliber caliber, Duct duct, Material material, TypeResistancesAndReactances reactancesAndResistances) {
        this.caliber = caliber;
        this.duct = duct;
        this.material = material;
        this.reactancesAndResistances = reactancesAndResistances;
    }//Fin del constructor
    
    /**
     * Constructor para almacenar la Resistencia o Reactancia encontrada.
     * @param code
     * @param value 
     */
    public ResistanceReactance(int code, Value value) {
        this.code = code;
        this.value = value;
    }//Fin del constructor
    
    //Getters y Setters

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Caliber getCaliber() {
        return caliber;
    }

    public void setCaliber(Caliber caliber) {
        this.caliber = caliber;
    }

    public Duct getDuct() {
        return duct;
    }

    public void setDuct(Duct duct) {
        this.duct = duct;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public TypeResistancesAndReactances getReactancesAndResistances() {
        return reactancesAndResistances;
    }

    public void setReactancesAndResistances(TypeResistancesAndReactances reactancesAndResistances) {
        this.reactancesAndResistances = reactancesAndResistances;
    }
        
}
