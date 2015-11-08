/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.dao;

import com.electrical_installations.model.entity.masters.Breaker;

/**
 * Intefaz para el acoplamiento de la entidad Interruptor
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-17
 */
public interface BreakerDAO {

    /**
     * Encontrar Interruptor por capacidad de corriente.
     * @param breaker
     * @return Retorna un Interruptor
     */
    public Breaker find_breaker_by_capacity(Breaker breaker);

}
