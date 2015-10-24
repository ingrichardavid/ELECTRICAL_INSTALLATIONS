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
                                            "	cp.nombre,cp.valor\n" +
                                            "FROM\n" +
                                            "	maestros.\"CABALLO_DE_POTENCIA\" AS cp\n" +
                                            "ORDER BY\n" +
                                            "	cp.codigo;";
    /**
     * Encontrar la intensidad por caballos de fuerza y voltaje.
     */
    public static final String SELECT_INTENSITY_HORSE_POWER =   "SELECT\n" +
                                                                "	cdp.codigo,i.codigo,i.intensidad\n" +
                                                                "FROM\n" +
                                                                "	maestros.\"CABALLOS_DE_POTENCIAS\" AS cdp\n" +
                                                                "JOIN\n" +
                                                                "	maestros.\"CABALLO_DE_POTENCIA\" AS cp\n" +
                                                                "ON\n" +
                                                                "	(cdp.caballo_de_potencia_codigo = cp.codigo)\n" +
                                                                "JOIN\n" +
                                                                "	maestros.\"VOLTAJE\" AS v\n" +
                                                                "ON\n" +
                                                                "	(cdp.voltaje_codigo = v.codigo)\n" +
                                                                "JOIN\n" +
                                                                "	maestros.\"INTENSIDAD\" AS i\n" +
                                                                "ON\n" +
                                                                "	(cdp.intensidad_codigo = i.codigo)\n" +
                                                                "WHERE\n" +
                                                                "	cp.nombre = ?\n" +
                                                                "AND\n" +
                                                                "	v.voltaje = ?;";    
    
}
