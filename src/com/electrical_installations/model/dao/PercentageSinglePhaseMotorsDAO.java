/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.dao;

import com.electrical_installations.model.entity.masters.PercentageSinglePhaseMotors;
import java.util.List;

/**
 * Intefaz para el acoplamiento de la Porcentaje de Motores Monofásicos.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-20
 */
public interface PercentageSinglePhaseMotorsDAO {

    /**
     * Listar todos los Porcentajes de Motores Monofásicos.
     * @return Retorna una lista de Porcentajes de Motores Monofásicos
     */
    public List<PercentageSinglePhaseMotors> find_percentage_motors_single_phase();
        
}
