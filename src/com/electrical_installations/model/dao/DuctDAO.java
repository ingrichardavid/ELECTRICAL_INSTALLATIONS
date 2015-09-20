/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.dao;
 
import com.electrical_installations.model.entity.masters.Duct;
import java.util.List;

/**
 * Intefaz para el acoplamiento de la entidad Ducto.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-09
 */
public interface DuctDAO {
 
    
    /** 
     * Listar todos los Ductos.
     * @return Retorna una lista de Ductos.
     */
    public List<Duct> find_ducts();
}
