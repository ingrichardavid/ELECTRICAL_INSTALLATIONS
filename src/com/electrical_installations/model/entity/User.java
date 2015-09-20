/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.entity;

/**
 * Clase Entidad Usuario
 * @author Ing. Richard David
 * @version 1
 * @since 2015-07-27
 */
public class User {

    //Objetos, variables y constantes
    private String nationality;
    private int dni;
    private String name;
    private String lastName;
    private String address;
    private String phone;
    private String userName;
    private String password;
    private boolean status;
    private String date;
    
    /**
     * Constructor para la creación de Usuarios
     * @param nationality
     * @param dni
     * @param name
     * @param lastName
     * @param address
     * @param userName
     * @param password
     * @param phone 
     * @param status 
     */
    public User(String nationality, int dni, String name, String lastName, String address, String userName, String password,String phone,boolean status) {
        this.nationality = nationality;
        this.dni = dni;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.userName = userName;
        this.password = password;
        this.phone = phone;
        this.status = status;
    }//Fin del constructor
    
    /**
     * Constructor para listar usuarios y buscarlos por nombres, apellidos y nombre de usuarios
     * @param cedula
     * @param name
     * @param lastName
     * @param userName
     */
    public User(String cedula,String name, String lastName,String userName) {
        this.nationality = cedula;
        this.name = name;
        this.lastName = lastName;
        this.userName = userName;
    }//Fin del constructor
    
    /**
     * Constructor para la búsqueda de Usuarios filtrados por campos
     * @param nationality
     * @param dni
     * @param name
     * @param lastName
     * @param address
     * @param userName
     * @param phone 
     * @param status
     * @param date
     */
    public User(String nationality, int dni, String name, String lastName, String address, String userName, String phone,boolean status, String date) {
        this.nationality = nationality;
        this.dni = dni;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.userName = userName;
        this.phone = phone;
        this.status = status;
        this.date = date;
    }//Fin del constructor
    
    /**
     * Constructor para modificar datos básicos del usuario
     * @param nationality
     * @param dni
     * @param name
     * @param lastName
     * @param address
     * @param phone 
     */
    public User(String nationality, int dni, String name, String lastName, String address,String phone){
        this.nationality = nationality;
        this.dni = dni;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;        
    }//Fin del constructor
        
    /**
     * Constructor validar usuarios
     * @param nationality
     * @param dni 
     */
    public User(String nationality, int dni) {
        this.nationality = nationality;
        this.dni = dni;
    }//Fin del constructor
    
    /**
     * Constructor para validar nombre de usuario
     * @param userName 
     */
    public User(String userName){
        this.userName = userName;
    }//Fin del constructor
                
    /**
     * Constructor iniciar sesión
     * @param userName
     * @param password 
     */
    public User(String userName,String password){
        this.userName = userName;
        this.password = password;
    }//Fin del constructor
    
    /**
     * Contructor para modificar datos de seguridad de Usuarios
     * @param nationality
     * @param dni
     * @param userName
     * @param password
     * @param status 
     */
    public User(String nationality, int dni,String userName, String password,boolean status){
        this.nationality = nationality;
        this.dni = dni;
        this.userName = userName;
        this.password = password;
        this.status = status;
    }//Fin del constructor
    
    /**
     * Contructor para almacenar datos de sesión de usuario
     * @param nationality
     * @param dni
     * @param name
     * @param lastName
     * @param phone
     * @param address
     * @param userName
     */
    public User(String nationality, int dni,String name, String lastName,String address,String phone,String userName){
        this.nationality = nationality;
        this.dni = dni;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.userName = userName;
    }//Fin del constructor
    
    //Getters y Setters   

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }    

    @Override
    public String toString() {
        return "User{" + "nationality=" + nationality + ", dni=" + dni + ", name=" + name + ", lastName=" + lastName + ", address=" + address + ", phone=" + phone + ", userName=" + userName + ", password=" + password + ", status=" + status + ", date=" + date + '}';
    }
    
    
    
}
