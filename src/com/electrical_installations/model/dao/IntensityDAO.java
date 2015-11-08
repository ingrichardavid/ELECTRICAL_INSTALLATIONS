/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.dao;

import com.electrical_installations.model.entity.masters.Calibers;
import com.electrical_installations.model.entity.masters.Intensity;
import java.util.List;

/**
 * Intefaz para el acoplamiento de la entidad Intensidades
 *
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-08
 */
public interface IntensityDAO {

    /**
     * Listar todas las Intensidades.
     *
     * @return Retorna una lista de Intensidades
     */
    public List<Intensity> find_Intensitys();
    
    /**
     * Método para calcular la intensidad de diseño a partir de un calibre dado.
     * @param calibers
     * @return 
     */
    public Intensity calculate_intensity_design(Calibers calibers);
    
}
