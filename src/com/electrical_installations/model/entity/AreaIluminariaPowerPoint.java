/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.entity;

import com.electrical_installations.model.entity.masters.Breaker;
import com.electrical_installations.model.entity.masters.Caliber;
import com.electrical_installations.model.entity.masters.Calibers;
import com.electrical_installations.model.entity.masters.Duct;
import com.electrical_installations.model.entity.masters.Phase;
import com.electrical_installations.model.entity.masters.Voltage;
import com.electrical_installations.model.enums.TypeOfBranchCircuitInArea;
import com.electrical_installations.model.enums.TypeRush;

/**
 * Clase para representar a la entidad Área Iluminaria y Toma Corrientes.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-19
 */
public class AreaIluminariaPowerPoint {
    
    //Objetos, variables y constantes
    private int code;
    private Area area;
    private Calibers calibers;
    private Voltage voltage;
    private Phase phase;
    private TypeOfBranchCircuitInArea typeOfBranchCircuitInArea;
    private double areaOrQuantity;
    private int constant;
    private double powerFactor;
    private Caliber caliber;
    private Breaker breaker;
    private TypeRush typeRush;
    private double length;
    private Duct duct;
    private double angle;
    private String caliberUse;
    private int branchCircuit;
    private String pipeline;
    private String materialPipeline;

    /**
     * Constructor para insertar, modificar y encontrar iluminarias y toma corrientes.
     * @param code
     * @param area
     * @param calibers
     * @param voltage
     * @param phase
     * @param typeOfBranchCircuitInArea
     * @param areaOrQuantity
     * @param constant
     * @param powerFactor
     * @param caliber
     * @param breaker 
     * @param typeRush 
     * @param length 
     * @param duct 
     * @param angle 
     * @param caliberUse 
     * @param branchCircuit 
     * @param pipeline 
     * @param materialPipeline 
     */
    public AreaIluminariaPowerPoint(int code, Area area, Calibers calibers, Voltage voltage, Phase phase, TypeOfBranchCircuitInArea typeOfBranchCircuitInArea, double areaOrQuantity, int constant, double powerFactor, Caliber caliber, Breaker breaker, TypeRush typeRush, double length, Duct duct, double angle, String caliberUse, int branchCircuit, String pipeline, String materialPipeline) {
        this.code = code;
        this.area = area;
        this.calibers = calibers;
        this.voltage = voltage;
        this.phase = phase;
        this.typeOfBranchCircuitInArea = typeOfBranchCircuitInArea;
        this.areaOrQuantity = areaOrQuantity;
        this.constant = constant;
        this.powerFactor = powerFactor;
        this.caliber = caliber;
        this.breaker = breaker;
        this.typeRush = typeRush;
        this.length = length;
        this.duct = duct;
        this.angle = angle;
        this.caliberUse = caliberUse;
        this.branchCircuit = branchCircuit;
        this.pipeline = pipeline;
        this.materialPipeline = materialPipeline;
    }//Fin del constructor
    
    //Getters y Setters

    public String getMaterialPipeline() {
        return materialPipeline;
    }

    public void setMaterialPipeline(String materialPipeline) {
        this.materialPipeline = materialPipeline;
    }

    public String getPipeline() {
        return pipeline;
    }

    public void setPipeline(String pipeline) {
        this.pipeline = pipeline;
    }

    public int getBranchCircuit() {
        return branchCircuit;
    }

    public void setBranchCircuit(int branchCircuit) {
        this.branchCircuit = branchCircuit;
    }

    public String getCaliberUse() {
        return caliberUse;
    }

    public void setCaliberUse(String caliberUse) {
        this.caliberUse = caliberUse;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public Duct getDuct() {
        return duct;
    }

    public void setDuct(Duct duct) {
        this.duct = duct;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Calibers getCalibers() {
        return calibers;
    }

    public void setCalibers(Calibers calibers) {
        this.calibers = calibers;
    }

    public Voltage getVoltage() {
        return voltage;
    }

    public void setVoltage(Voltage voltage) {
        this.voltage = voltage;
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    public TypeOfBranchCircuitInArea getTypeOfBranchCircuitInArea() {
        return typeOfBranchCircuitInArea;
    }

    public void setTypeOfBranchCircuitInArea(TypeOfBranchCircuitInArea typeOfBranchCircuitInArea) {
        this.typeOfBranchCircuitInArea = typeOfBranchCircuitInArea;
    }

    public double getAreaOrQuantity() {
        return areaOrQuantity;
    }

    public void setAreaOrQuantity(double areaOrQuantity) {
        this.areaOrQuantity = areaOrQuantity;
    }

    public int getConstant() {
        return constant;
    }

    public void setConstant(int constant) {
        this.constant = constant;
    }

    public double getPowerFactor() {
        return powerFactor;
    }

    public void setPowerFactor(double powerFactor) {
        this.powerFactor = powerFactor;
    }

    public Caliber getCaliber() {
        return caliber;
    }

    public void setCaliber(Caliber caliber) {
        this.caliber = caliber;
    }

    public Breaker getBreaker() {
        return breaker;
    }

    public void setBreaker(Breaker breaker) {
        this.breaker = breaker;
    }

    public TypeRush getTypeRush() {
        return typeRush;
    }

    public void setTypeRush(TypeRush typeRush) {
        this.typeRush = typeRush;
    }
    
}
