/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.enums;

/**
 * Clase enumerada para identificar los tipos de fases.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-16
 */
public enum TypePhases {
    
    SINGLE_PHASE_TWO_THREAD("Monofásico 2 Hilos"), SINGLE_PHASE_THREE_THREAD("Monofásico 3 Hilos"), PASHE_FOUR_THREAD("Trifásico 4 Hilos");
    
    //Objetos, variables y constantes
    private final String phase;
    
    /**
     * Contructor privado, recibe una fase.
     * @param phase 
     */
    private TypePhases(String phase){
        this.phase = phase;
    }//Fin del constructor
    
    /**
     * Método para retornar la fase.
     * @return Retorna el nombre de la fase seleccionada
     */
    public String getPhase(){
        return phase;
    }//Fin del método..
    
}
