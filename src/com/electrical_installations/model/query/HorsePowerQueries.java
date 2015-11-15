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
     * Mostrar todos los Caballo de Fuerza Monofásico.
     */ 
    public static final String SELECT_ALL = "SELECT cp.codigo,\n" +
                                            "	cp.nombre,cp.valor\n" +
                                            "FROM\n" +
                                            "	maestros.\"CABALLO_DE_POTENCIA\" AS cp\n" +
                                            "ORDER BY\n" +
                                            "	cp.codigo;";
    
    /**
     * Mostrar todos los Caballos de Potencia Trifásicos.
     */
    public static final String  SELECT_ALL_THREEPHASES =  "SELECT codigo, nombre, valor\n" +
                                                            "FROM maestros.\"CABALLO_DE_FUERZA_TRIFASICO\"\n" +
                                                            "ORDER BY codigo;";
    /**
     * Encontrar la intensidad por caballos de fuerza y voltaje (Motores Monofásicos).
     */
    public static final String SELECT_INTENSITY_HORSE_POWER_SINGLE_PHASE =   "SELECT\n" +
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
    
    /**
     * Encontrar la intensidad por caballo de fuerza y voltaje (Motores Trifásicos).
     * 
     */
    public static final String SELECT_INTENSITY_HORSE_POWER_THREE_PHASE = "SELECT\n" +
                                                                "	intensidad\n" +
                                                                "FROM\n" +
                                                                "	maestros.\"MOTORES_TRIFASICOS_DE_INDUCCION\"\n" +
                                                                "WHERE\n" +
                                                                "	caballo_de_fuerza_trifasico_codigo = ?\n" +
                                                                "AND\n" +
                                                                "	voltaje_motores_trifasicos_codigo = ?;";
    
}
