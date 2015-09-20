/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.query;

/**
 * Clase que contiene los Queries de la entidad Temperatura en Objetos estáticos de tipo String
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-06
 */
public class TemperatureQueries {
 
    /**
     * Buscar Temperaturas.
     */    
    public static final String SELECT_ALL = "SELECT t.codigo,t.cantidad,CONCAT(t.cantidad,' ',u.nombre) AS temperatura FROM maestros.\"TEMPERATURA\" AS t JOIN maestros.\"UNIDAD\" AS u ON (t.unidad_codigo=u.codigo);";
         
}
