/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.dao;

import com.electrical_installations.model.entity.Elevator;
import java.util.List;

/**
 * Intefaz para el acoplamiento de la entidad Elevador
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-24
 */
public interface ElevatorDAO {
    
    /**
     * Listar todos los Elevadores
     * @return Retorna una lista Elevadores
     */
    public List<Elevator> find_elevators();
    
    /**
     * Listar todos los Elevadores filtrados por nombre
     * @param elevator 
     * @return Retorna una lista de Elevadores
     */
    public List<Elevator> filter_by_name(Elevator elevator);
    
}
