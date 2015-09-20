/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.query;

/**
 * Clase que contiene los Queries de la entidad Carga en Objetos estáticos de tipo String
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-02
 */
public class ChargeQueries {
  
    /**
     * Mostrar todos todas las Cargas.
     */
    public static final String SELECT_ALL = "SELECT \n" +
                                            "	c.codigo,CONCAT(c.nombre,' ',e.cantidad,' ',u.nombre),p.cantidad\n" +
                                            "FROM\n" +
                                            "	maestros.\"CARGA\" AS c\n" +
                                            "LEFT JOIN\n" +
                                            "	maestros.\"ENERGIA\" AS e\n" +
                                            "ON\n" +
                                            "	(c.energia_codigo = e.codigo)\n" +
                                            "JOIN\n" +
                                            "	maestros.\"POTENCIA\" AS p\n" +
                                            "ON\n" +
                                            "	(c.potencia_codigo = p.codigo)\n" +
                                            "LEFT JOIN\n" +
                                            "	maestros.\"UNIDAD\" AS u\n" +
                                            "ON\n" +
                                            "	(e.unidad_codigo = u.codigo)\n" +
                                            "ORDER BY \n" +
                                            "	c.nombre\n" +
                                            "ASC;";
  
    /**
     * Mostrar todos todas las Cargas filtradas por nombre.
     */
    public static final String FILTER_BY_NAME = "SELECT \n" +
                                                "	c.codigo,CONCAT(c.nombre,' ',e.cantidad,' ',u.nombre),p.cantidad\n" +
                                                "FROM\n" +
                                                "	maestros.\"CARGA\" AS c\n" +
                                                "LEFT JOIN\n" +
                                                "	maestros.\"ENERGIA\" AS e\n" +
                                                "ON\n" +
                                                "	(c.energia_codigo = e.codigo)\n" +
                                                "JOIN\n" +
                                                "	maestros.\"POTENCIA\" AS p\n" +
                                                "ON\n" +
                                                "	(c.potencia_codigo = p.codigo)\n" +
                                                "LEFT JOIN\n" +
                                                "	maestros.\"UNIDAD\" AS u\n" +
                                                "ON\n" +
                                                "	(e.unidad_codigo = u.codigo)\n" +
                                                "WHERE \n" +
                                                "	LOWER(c.nombre) LIKE LOWER(?) \n" +
                                                "ORDER BY \n" +
                                                "	c.nombre\n" +
                                                "ASC;";
    
}