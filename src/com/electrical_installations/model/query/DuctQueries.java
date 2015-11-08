/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.query;

/**
 * Clase que contiene los Queries de la entidad Ducto en Objetos estáticos de tipo String
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-09
 */
public class DuctQueries {
 
    /**
     * Buscar Ductos.
     */    
    public static final String SELECT_ALL = "SELECT d.codigo, d.nombre FROM maestros.\"DUCTO\" AS d;";
         
}
