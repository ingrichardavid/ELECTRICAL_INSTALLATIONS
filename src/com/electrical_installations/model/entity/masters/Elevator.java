/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.entity.masters;

/**
 * Clase que representa la entidad Elevador.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-06
 */
public class Elevator {
    
    //Objetos, variables y constantes
    private int code;
    private PersonNumber personNumber;
    private Speed speed;
    private Potency potency;

    /**
     * Constructor para crear, modificar, eliminar y encontrar elevadores.
     * @param code
     * @param personNumber
     * @param speed
     * @param potency 
     */
    public Elevator(int code,PersonNumber personNumber, Speed speed, Potency potency) {
        this.code = code;
        this.personNumber = personNumber;
        this.speed = speed;
        this.potency = potency;
    }//Fin del constructor

    //Getters y Setters

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    
    public PersonNumber getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(PersonNumber personNumber) {
        this.personNumber = personNumber;
    }

    public Speed getSpeed() {
        return speed;
    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    public Potency getPotency() {
        return potency;
    }

    public void setPotency(Potency potency) {
        this.potency = potency;
    }
    
}
