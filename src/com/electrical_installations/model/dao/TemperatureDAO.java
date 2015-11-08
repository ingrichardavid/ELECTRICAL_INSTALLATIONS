/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.dao;

import com.electrical_installations.model.entity.masters.Temperature;
import java.util.List;

/**
 * Intefaz para el acoplamiento de la entidad Temperatura
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-06
 */
public interface TemperatureDAO {

    /**
     * Listar todas las Temperaturas.
     * @return Retorna una lista de Areas
     */
    public List<Temperature> find_temperatures();
    
}
