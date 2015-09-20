/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.query;

/**
 * Clase que contiene los Queries de la entidad Voltaje en Objetos estáticos de tipo String
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-10
 */
public class VoltageQueries {
 
    /**
     * Buscar Voltages.
     */    
    public static final String SELECT_ALL = "SELECT v.codigo,v.voltaje,CONCAT(v.voltaje,' ',u.nombre) AS voltage FROM maestros.\"VOLTAJE\" AS v JOIN maestros.\"UNIDAD\" AS u ON (v.unidad_codigo=u.codigo);";
         
}
