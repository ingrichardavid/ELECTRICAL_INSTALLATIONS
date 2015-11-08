/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.dao;

import com.electrical_installations.model.entity.masters.PercentageOfThreePhaseMotors;
import java.util.List;

/**
 * Intefaz para el acoplamiento de la entidad Porcentaje Trifásicos de Motores.
 * @author JRichard
 * @version 1
 * @since 2015-10-27  
 */
public interface PercentageOfThreePhaseMotorsDAO {
        
    /**
     * Lista de todos los tipos de porcentaje trifásico de motores 
     * @return Retorna una lista de Typo
     */
    public List<PercentageOfThreePhaseMotors> find_type();

    /**
     * Listar todos los Porcentajes de Motores Trifásicos.
     * @param percentageOfThreePhaseMotors
     * @return Retorna una lista de Porcentajes de Motores Trifásicos.
     */
    public List<PercentageOfThreePhaseMotors> find_percentage_motors_three_phase(PercentageOfThreePhaseMotors percentageOfThreePhaseMotors);
    
}
