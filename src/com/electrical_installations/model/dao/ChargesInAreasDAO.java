/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.dao;

import com.electrical_installations.model.DataBaseConnection;
import com.electrical_installations.model.entity.ChargesInAreas;
import com.electrical_installations.model.entity.Area;
import com.electrical_installations.model.entity.masters.Voltage;
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
     * @param area
     * @param voltage
     * @return Retorna true en caso de que los datos se hayan insertado con éxito.
     */
    public boolean insert_charge_in_area(ChargesInAreas chargesInAreas, Area area, Voltage voltage);
  
    /**
     * Eliminar carga de un área.
     * @param chargesInAreas
     * @param area
     * @param voltage
     * @return Retorna true en caso de que los datos se hayan insertado con éxito.
     */
    public boolean delete_charge_in_area(ChargesInAreas chargesInAreas, Area area);
            
    /**
     * Eliminar un tipo de carga de la entidad alimentador principal
     * @param chargesInAreas
     * @param area
     * @param dataBaseConnection
     * @param voltage
     * @return 
     */
    public boolean delete_charge_main_feeder(ChargesInAreas chargesInAreas, Area area, DataBaseConnection dataBaseConnection, double voltage);
    
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
    
    /**
     * Validar que una carga ya no haya sido asignada a un área.
     * @param chargesInAreas
     * @return Retorna true en caso de que la carga ya haya sido asignada. Caso contrario retorna false.
     */
    public boolean validate_charge_in_area(ChargesInAreas chargesInAreas);
 
    /**
     * Método para contar el número de cargas asignadas a un área.
     * @param area
     * @return Retorna el número de áreas asignadas a un área.
     */
    public int count_charges_in_area(Area area);
    
}
