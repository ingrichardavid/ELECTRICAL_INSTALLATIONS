/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.dao;

 
import com.electrical_installations.model.entity.masters.Caliber;
import java.util.List;

/**
 * Intefaz para el acoplamiento de la entidad Calibre
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-08
 */
public interface CaliberDAO {
    
    /** 
     * Listar todos los Calibres.
     * @return Retorna una lista de Calibres
     */
    public List<Caliber> find_caliber();
    
    /**
     * Método para encontrar el área de un calibre.
     * @param caliber
     * @return Retorna el área de un calibre.
     */
    public double find_area(Caliber caliber);

}
