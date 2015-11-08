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
 * @since 2015-11-01
 */
public enum TypePhase {
    
    SINGLE_PHASE(1,"Monofásico"),THREE_PHASE(2,"Trifásico");
    
    //Objetos, variables y constantes
    private final int code;
    private final String phase;
    
    /**
     * Contructor privado, recibe una fase.
     * @param phase 
     */
    private TypePhase(int code,String phase){
        this.code = code;
        this.phase = phase;
    }//Fin del constructor
    
    /**
     * Método para retornar la fase.
     * @return Retorna el nombre de la fase seleccionada
     */
    public String getPhase(){
        return phase;
    }//Fin del método.
    
    /**
     * Método para obtener el código del tipo de Fase.
     * @return Retorn el código del tipo de fase.
     */
    public int getCode(){
        return code;
    }//Fin del Método
    
}
