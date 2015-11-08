/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.query;

/**
 * Clase que contiene los Queries de Interruptores en objetos estáticos de tipo String
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-13
 */
public class BreakerQueries {
 
    /**
     * Encontrar interruptor por capacidad.
     */
    public static final String FIND_BREAKER_BY_CAPACITY =   "SELECT\n" +
                                                            "	i.codigo,i.capacidad\n" +
                                                            "FROM\n" +
                                                            "	maestros.\"INTERRUPTOR\" AS i\n" +
                                                            "WHERE\n" +
                                                            "	i.capacidad >= ?\n" +
                                                            "LIMIT 1;";
    
}
