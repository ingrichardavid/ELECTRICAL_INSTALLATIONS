/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.dao;

import com.electrical_installations.model.entity.ChargesInAreas;
import java.util.List;
/**
 * Intefaz para el acoplamiento de la Entidad Cargas en Áreas
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-02
 */
public interface ChargesInAreasDAO {
    
    /**
     * Insertar carga en área.
     * @param chargesInAreas
     * @return Retorna true en caso de que los datos se hayan insertado con éxito.
     */
    public boolean insert_charge_in_area(ChargesInAreas chargesInAreas);
    
    /**
     * Modificar cantidad de una carga en área.
     * @param chargesInAreas
     * @return Retorna true en caso de que los datos se hayan insertado con éxito.
     */
    public boolean modify_charge_in_area(ChargesInAreas chargesInAreas);
    
    /**
     * Eliminar carga de un área.
     * @param chargesInAreas
     * @return Retorna true en caso de que los datos se hayan insertado con éxito.
     */
    public boolean delete_charge_in_area(ChargesInAreas chargesInAreas);
            
    /**
     * Consultar todas las cargas asociadas a un área.
     * @param chargesInAreas
     * @return Retorna una lista de cargas asociadas a un área
     */
    public List<ChargesInAreas> find_charges_in_areas(ChargesInAreas chargesInAreas);
    
    /**
     * Listar todas las Cargas asignadas a un área filtradas por nombre
     * @param chargesInAreas 
     * @return Retorna una lista de Cargas
     */
    public List<ChargesInAreas> filter_by_name(ChargesInAreas chargesInAreas);
    
}
