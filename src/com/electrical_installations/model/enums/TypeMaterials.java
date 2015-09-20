/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.enums;

/**
 * Clase enum para enumerar los tipos de materiales
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-13
 */
public enum TypeMaterials {
    
    COOPER("Cobre (Cu)"),ALUMINIUM("Aluminio (Al)");
    
    //Objetos, variables y constantes
    private final String material;
    
    /**
     * Constructor que recibe un material
     * @param material 
     */
    private TypeMaterials(String material){
        this.material = material;
    }//Fin del constructor
    
    /**
     * Método para obtener el material
     * @return Retorna un material
     */
    public String getMaterial(){
        return material;
    }//Fin del método
    
}
