/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.dao;

import com.electrical_installations.model.entity.LightingCircuit;

import java.util.List;

/**
 * Intefaz para el acoplamiento de la entidad Circuito de iluminación
 * @author Ing. Richard David
 * @version 1
 * @since 2015-11-16
 */
public interface lightingCircuitDAO {
     
    /**
     * Crear un circuito en iluminación.
     * @param lightingCircuit
     * @return Retorna un valor booleano.
     */ 
    public boolean insert(LightingCircuit lightingCircuit);
         
    /**
     * Encontrar los circuitos de iluminación de un proyecto dado.
     * @param lightingCircuit
     * @return Retorna los circuitos de iluminación.
     */
    public List<LightingCircuit> find_lightingCircuit(LightingCircuit lightingCircuit);
    
    /**
     * Eliminar un circuito de iluminación.
     * @param lightingCircuit
     * @return Retorna true si el circuito se eliminó.
     */
    public boolean delete(LightingCircuit lightingCircuit);
    
}

