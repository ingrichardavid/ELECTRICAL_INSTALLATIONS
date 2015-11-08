/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.dao;

import com.electrical_installations.model.entity.Charge;
import java.util.List;

/**
 * Intefaz para el acoplamiento de la Entidad Carga
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-02
 */
public interface ChargeDAO {
    
    /**
     * Listar todos las cargas
     * @return Retorna una lista de cargas
     */
    public List<Charge> find_charges();
    
    /**
     * Listar todos las cargas filtradas por nombre
     * @param charge
     * @return Retorna una lista de cargas
     */
    public List<Charge> filter_by_name(Charge charge);

}
