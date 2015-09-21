/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.dao;

import com.electrical_installations.model.entity.masters.HorsePower;
import java.util.List;

/**
 * Intefaz para el acoplamiento de la entidad HorsePower
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-20
 */
public interface HorsePowerDAO {

    /**
     * Listar todos los Caballos de Potencia.
     * @return Retorna una lista de Caballos de Potencia
     */
    public List<HorsePower> find_horses_power();
    
    
}
