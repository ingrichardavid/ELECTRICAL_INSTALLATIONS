/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.query;

/**
 * Clase que contiene los Queries de la entidad Valour en Objetos estáticos de tipo String
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-08
 */
public class ValourQueries {
 
    /**
     * Buscar Valores.
     */    
    public static final String SELECT_ALL = "SELECT v.codigo, v.valor FROM maestros.\"VALOR\" AS v;";
         
}
