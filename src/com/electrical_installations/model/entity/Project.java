/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.entity;

/**
 * Clase Entidad Proyecto
 * @author Ing. Richard David
 * @version 1
 * @since 2015-07-27
 */
public class Project {
    
    //Objetos, variables y constantes
    private int code;
    private User user;
    private TypeOfInstallation typeOfInstallation;
    private String name;
    private int powerTotal;
    private String registration_date;
    private double intensity_total;
    private String phase_motor;
    private String earth_motor;
    private String pipeline_motor;

    /**
     * Constructor para la búsqueda de proyectos
     * @param code
     * @param user
     * @param typeOfInstallation
     * @param name
     * @param powerTotal
     * @param registration_date 
     */    
    public Project(int code, User user, TypeOfInstallation typeOfInstallation, String name, int powerTotal, String registration_date) {
        this.code = code;
        this.user = user;
        this.typeOfInstallation = typeOfInstallation;
        this.name = name;
        this.powerTotal = powerTotal;
        this.registration_date = registration_date;
    }//Fin del constructor

    
    /**
     * Constructor para la inserción de cables del sub-alimentador de motores.
     * @param code
     * @param user
     * @param typeOfInstallation
     * @param name
     * @param powerTotal
     * @param registration_date 
     * @param pipeline_motor 
     */    
    public Project(int code, User user, TypeOfInstallation typeOfInstallation, String name, int powerTotal, String registration_date, String pipeline_motor) {
        this.code = code;
        this.user = user;
        this.typeOfInstallation = typeOfInstallation;
        this.name = name;
        this.powerTotal = powerTotal;
        this.registration_date = registration_date;
        this.pipeline_motor = pipeline_motor;
    }//Fin del constructor
    
    /**
     * Constructor para la creación y modificación de Proyectos
     * @param user
     * @param typeOfInstallation
     * @param name
     * @param powerTotal 
     */
    public Project(User user, TypeOfInstallation typeOfInstallation, String name, int powerTotal) {
        this.user = user;
        this.typeOfInstallation = typeOfInstallation;
        this.name = name;
        this.powerTotal = powerTotal;
    }//Fin del constructor

    /**
     * Constructor para eliminación de Proyectos
     * @param code 
     */
    public Project(int code) {
        this.code = code;
    }//Fin del constructor    
    
    /**
     * Constructor para consulta de Proyectos
     * @param code
     * @param name 
     */
    public Project(int code,String name) {
        this.code = code;
        this.name = name;
    }//Fin del constructor  

    /**
     * Constructor utilizado para modificar la intensidad total en motores de un proyecto.
     * @param code
     * @param typeOfInstallation
     * @param intensity_total 
     */
    public Project(int code, TypeOfInstallation typeOfInstallation, double intensity_total) {
        this.code = code;
        this.typeOfInstallation = typeOfInstallation;
        this.intensity_total = intensity_total;
    }//Fin del constructor.

    /**
     * Constructor para modificar en el proyecto los campos fase de motor y tierra de motor.
     * @param code
     * @param typeOfInstallation
     * @param phase_motor
     * @param earth_motor 
     * @param intensity_total 
     * @param pipeline_motor 
     */
    public Project(int code, TypeOfInstallation typeOfInstallation, String phase_motor, String earth_motor, double intensity_total, String pipeline_motor) {
        this.code = code;
        this.typeOfInstallation = typeOfInstallation;
        this.phase_motor = phase_motor;
        this.earth_motor = earth_motor;
        this.intensity_total = intensity_total;
        this.pipeline_motor = pipeline_motor;
    }//fin del constructor
    
    //Getters y Setters

    public String getPipeline_motor() {
        return pipeline_motor;
    }

    public void setPipeline_motor(String pipeline_motor) {
        this.pipeline_motor = pipeline_motor;
    }
    
    public String getPhase_motor() {    
        return phase_motor;
    }

    public void setPhase_motor(String phase_motor) {
        this.phase_motor = phase_motor;
    }

    public String getEarth_motor() {
        return earth_motor;
    }
 
    public void setEarth_motor(String earth_motor) {
        this.earth_motor = earth_motor;
    }

    public double getIntensity_total() {
        return intensity_total;
    }

    public void setIntensity_total(double intensity_total) {
        this.intensity_total = intensity_total;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TypeOfInstallation getTypeOfInstallation() {
        return typeOfInstallation;
    }

    public void setTypeOfInstallation(TypeOfInstallation typeOfInstallation) {
        this.typeOfInstallation = typeOfInstallation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPowerTotal() {
        return powerTotal;
    }

    public void setPowerTotal(int powerTotal) {
        this.powerTotal = powerTotal;
    }

    public String getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(String registration_date) {
        this.registration_date = registration_date;
    }
    
}
