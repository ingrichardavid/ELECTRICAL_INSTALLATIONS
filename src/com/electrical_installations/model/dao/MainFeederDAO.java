/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.dao;
 
import com.electrical_installations.model.entity.MainFeeder;
import java.util.List;

/**
 * Intefaz para el acoplamiento de la entidad Alimentador Principal
 * @author Ing. Richard David
 * @version 1
 * @since 2015-11-17
 */
public interface MainFeederDAO {
 
    
    /**
     * Listar todos los alimentadores principales que le pertenecen a un proyecto
     * @param mainFeeder 
     * @return Retorna una lista de Alimentadores Principales
     */
    public List<MainFeeder> find_main_feeder(MainFeeder mainFeeder);
    
    /**
     * Listar todos los alimentadores principales que le pertenecen a un proyecto filtrado por nombre
     * @param mainFeeder 
     * @return Retorna una lista de Alimentadores Principales filtrado por nombre
     */
    public List<MainFeeder> find_main_feeder_filter_by_name(MainFeeder mainFeeder);
    
    /**
     * Se obtiene la suma de la intensidad de la suma de la tabla circuito de iluminación más motores en instalación.
     * @param mainFeeder   
     * @return    
     */
    public double  find_Intensity(MainFeeder mainFeeder);
         
}

