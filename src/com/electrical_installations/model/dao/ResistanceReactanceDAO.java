/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.dao;

import com.electrical_installations.model.entity.masters.ResistanceReactance;
import com.electrical_installations.model.enums.TypeResistancesAndReactances;

/**
 * Intefaz para el acoplamiento de la entidad Resistencia y Reactancia
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-13
 */
public interface ResistanceReactanceDAO {

    /**
     * Encontrar Resistencia o Reactancia.
     * @param resistanceReactance
     * @param resistancesAndReactances
     * @return Retorna la resistencia o reactancia encontrada.
     */
    public ResistanceReactance find_resistanceReactance(ResistanceReactance resistanceReactance, TypeResistancesAndReactances resistancesAndReactances);
    
}
