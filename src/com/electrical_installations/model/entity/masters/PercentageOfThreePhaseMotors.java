/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.entity.masters;

/**
 * Clase para representar la entidad Porcentaje trifásicos de motores.
 * @author JRichard
 * @version 1
 * @since 2015-10-27
 */
public class PercentageOfThreePhaseMotors {
    
    //Objetos, variables y constantes
    private int code;
    private String typeOfMotor;
    private String name;
    private int percentage;
    private int unitCode;
    
    /**
     * Constructor para hacer búsquedas, insertar, modificar, eliminar.
     * @param code
     * @param typeOfMotor
     * @param name
     * @param percentage
     * @param unitCode 
     */
    public PercentageOfThreePhaseMotors(int code, String typeOfMotor, String name, int percentage, int unitCode) {
        this.code = code;
        this.typeOfMotor = typeOfMotor;
        this.name = name;
        this.percentage = percentage;
        this.unitCode = unitCode;
    }
     
    //getters y setters

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTypeOfMotor() {
        return typeOfMotor;
    }

    public void setTypeOfMotor(String typeOfMotor) {
        this.typeOfMotor = typeOfMotor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public int getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(int unitCode) {
        this.unitCode = unitCode;
    }
    //fin getters y setters

    @Override
    public String toString() {
        return typeOfMotor == null ? name : typeOfMotor;
    }
        
}
