/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.dao;

import com.electrical_installations.model.DataBaseConnection;
import com.electrical_installations.model.entity.Area;
import com.electrical_installations.model.entity.AreaIluminariaPowerPoint;
import com.electrical_installations.model.entity.Project;
import com.electrical_installations.model.entity.masters.Calibers;
import com.electrical_installations.model.enums.TypeTypeOfCharges;
import java.util.List;

/**
 * Intefaz para el acoplamiento de la entidad Area
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-02
 */
public interface ReportDAO {

   public void report(Project project);
    
}

