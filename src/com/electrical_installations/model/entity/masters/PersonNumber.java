/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.entity.masters;

/**
 * Clase para representar a la entidad Número de personas.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-06
 */
public class PersonNumber {
    
    //Objetos, variables y constantes
    private int codigo;
    private int quantity;

    /**
     * Constructor para insertar, modificar, eliminar y buscar número de personas
     * @param codigo 
     */
    public PersonNumber(int codigo) {
        this.codigo = codigo;
    }//Fin del constructor
    
    //Getter y Setters

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
