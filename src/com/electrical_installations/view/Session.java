
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.view;

import com.electrical_installations.model.entity.User;

/**
 * Clase que contiene la Sesión de Usuario
 * @author Ing. Richard David 
 * @version 1 
 * @since 2015-07-27
 */
public class Session {

    //Objetos, variables y constantes.
    public static Session instance;
    private String nationality;
    private int dni;
    private String name;
    private String lastName;
    private String address;
    private String phone;
    private String userName;
    
    /**
     * Constructor de la clase, es privado para cumplir con el patrón Singlenton.
     */
    private Session() {
    }//Fin del Constructor
    
    /**
     * Este método permite obtener la instancia de la clase.
     * @return Retorna el objeto instance que es del mismo tipo que la clase.
     */
    public synchronized static Session getInstance() {        
        if (instance == null) {
            instance = new Session();
        }
        return instance;        
    }//Fin del método getInstance

    public void session_data(User user){
        this.nationality = user.getNationality();
        this.dni = user.getDni();
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.address = user.getAddress();
        this.phone = user.getPhone();
        this.userName = user.getUserName();
    }
    
    /**
     * Este método elimina la referencia en memoria para el objeto instance.
     */
    public void delete_instance() {
        instance = null;
    }
    
    //Getters y Setters

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
}
