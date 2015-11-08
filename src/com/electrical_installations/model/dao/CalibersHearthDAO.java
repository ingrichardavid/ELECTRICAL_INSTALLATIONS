/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.dao;

 
import com.electrical_installations.model.entity.masters.CalibersHearth;

/**
 * Intefaz para el acoplamiento de la entidad Calibre de Tierra
 * @author Ing. Richard David
 * @version 1
 * @since 2015-10-04
 */
public interface CalibersHearthDAO {
    
    /** 
     * Encontrar un conductor de tierra.
     * @param calibersHearth
     * @return Retorna un conductor de tierra
     */
    public CalibersHearth find_caliber_hearth(CalibersHearth calibersHearth);
    
}
