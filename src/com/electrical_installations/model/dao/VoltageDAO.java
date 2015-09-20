/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.dao;

import com.electrical_installations.model.entity.masters.Voltage;
import java.util.List;

/**
 * Intefaz para el acoplamiento de la entidad Voltaje
 *
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-10
 */
public interface VoltageDAO {

    /**
     * Listar todos los Volatejes.
     *
     * @return Retorna una lista de Voltajes
     */
    public List<Voltage> find_voltages();
}
