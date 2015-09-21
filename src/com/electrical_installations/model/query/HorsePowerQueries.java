/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.query;

/**
 * Clase que contiene los Queries de Caballo de Potencia en Objetos estáticos de tipo String
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-20
 */
public class HorsePowerQueries {    
     
    /**
     * Mostrar todos los Caballo de Potencia.
     */ 
    public static final String SELECT_ALL = "SELECT cp.codigo,\n" +
                                            "	cp.nombre\n" +
                                            "FROM\n" +
                                            "	maestros.\"CABALLO_DE_POTENCIA\" AS cp\n" +
                                            "ORDER BY\n" +
                                            "	cp.codigo;";
        
}
