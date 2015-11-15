/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.entity.masters;

import com.electrical_installations.model.enums.TypePhase;

/**
 * Clase para representar a la entidad Caballos de Fuerzas.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-10-04
 */
public class HorsesPowers {
    
    //Objetos, variables y constantes
    private int code;
    private HorsePower horsePower;
    private Voltage voltage;
    private Intensity intensity;
    private TypePhase typePhase;

    /**
     * Constructor para almacenar datos
     * @param code
     * @param horsePower
     * @param voltage
     * @param intensity 
     */
    public HorsesPowers(int code, HorsePower horsePower, Voltage voltage, Intensity intensity, TypePhase typePhase) {
        this.code = code;
        this.horsePower = horsePower;
        this.voltage = voltage;
        this.intensity = intensity;
        this.typePhase = typePhase;
    }//Fin del Constructor

    //Getters y Setters

    public TypePhase getTypePhase() {
        return typePhase;
    }

    public void setTypePhase(TypePhase typePhase) {
        this.typePhase = typePhase;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public HorsePower getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(HorsePower horsePower) {
        this.horsePower = horsePower;
    }

    public Voltage getVoltage() {
        return voltage;
    }

    public void setVoltage(Voltage voltage) {
        this.voltage = voltage;
    }

    public Intensity getIntensity() {
        return intensity;
    }

    public void setIntensity(Intensity intensity) {
        this.intensity = intensity;
    }
    
}
