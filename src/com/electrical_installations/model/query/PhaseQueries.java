/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.query;

/**
 * Clase que contiene los Queries de la entidad fase en Objetos estáticos de tipo String
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-09
 */
public class PhaseQueries {
 
    /**
     * Buscar Fases.
     */    
    public static final String SELECT_ALL = "SELECT f.codigo, f.nombre FROM maestros.\"FASE\" AS f;";
         
}
