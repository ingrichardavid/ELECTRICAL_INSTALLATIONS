/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.entity;

/**
 * Clase Entidad Elevador
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-24
 */
public class Elevator {

    //Objetos, variables y constantes
    private int code;
    private int number_persons;
    private double speed;
    private int potency;
    private String elevator_name;
    
    /**
     * Constructor para la búsqueda de Elevadores
     * @param code
     * @param number_persons
     * @param speed
     * @param potency
     */
    public Elevator(int code, int number_persons, double speed, int potency) {
        this.code = code;
        this.number_persons = number_persons;
        this.speed = speed;
        this.potency = potency;
    }//Fin del constructor

    /**
     * Constructor para buscar los elevadores filtrador por todos los campos
     * @param elevator_name 
     */
    public Elevator(String elevator_name) {
        this.elevator_name = elevator_name;
    }//Fin del constructor
    
    //Getters y Setters

    public String getElevator_name() {
        return elevator_name;
    }

    public void setElevator_name(String elevator_name) {
        this.elevator_name = elevator_name;
    }
    
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getNumber_persons() {
        return number_persons;
    }

    public void setNumber_persons(int number_persons) {
        this.number_persons = number_persons;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getPotency() {
        return potency;
    }

    public void setPotency(int potency) {
        this.potency = potency;
    }

}
