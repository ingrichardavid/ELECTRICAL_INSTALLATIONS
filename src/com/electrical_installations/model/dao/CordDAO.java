/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.dao;

import com.electrical_installations.model.entity.masters.Cord; 
import java.util.List;

/**
 * Intefaz para el acoplamiento de la entidad Cable
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-08
 */
public interface CordDAO {
 
    
    /** 
     * Listar todos los Cables.
     * @return Retorna una lista de Cables
     */
    public List<Cord> find_cables();
}
