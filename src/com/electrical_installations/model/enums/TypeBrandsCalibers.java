/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.enums;

/**
 * Clase enum para enumerar las marcas de calibres
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-13
 */
public enum TypeBrandsCalibers {
    
    TW("+TW"),THW("+THW"),TTU("TTU"),NOT_FOUND("?");
    
    //Objetos, variables y constantes
    private final String brand;
    
    /**
     * Constructor que recibe una marca
     * @param brand 
     */
    private TypeBrandsCalibers(String brand){
        this.brand = brand;
    }//Fin del constructor
    
    /**
     * Método para obtener la marca del cable.
     * @return Retorna la marca del cable
     */
    public String getBrand(){
        return brand;
    }//Fin del método
    
}
