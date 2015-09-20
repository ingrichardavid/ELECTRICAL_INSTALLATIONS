/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.enums;

/**
 * Clase enum para enumerar los tipos de calibres
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-13
 */
public enum TypeCalibers {
    
    CALIBER_1_0("1/0"),
    CALIBER_2_0("2/0"),
    CALIBER_3_0("3/0"),
    CALIBER_4_0("4/0"),
    CALIBER_2("2"),
    CALIBER_4("4"),
    CALIBER_6("6"),
    CALIBER_8("8"),
    CALIBER_9("9"),
    CALIBER_10("10"),
    CALIBER_12("12"),
    CALIBER_14("14"),
    CALIBER_250("250"),
    CALIBER_300("300"),
    CALIBER_350("350"),
    CALIBER_500("500");
    
    //Objetos, variables y constantes
    private final String caliber;
    
    /**
     * Constructor que recibe un calibre
     * @param caliber 
     */
    private TypeCalibers(String caliber){
        this.caliber = caliber;
    }//Fin del constructor
    
    /**
     * Método para obtener un calibre
     * @return Retorna la marca del cable
     */
    public String getCaliber(){
        return caliber;
    }//Fin del método
    
}
