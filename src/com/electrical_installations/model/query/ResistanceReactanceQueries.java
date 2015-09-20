/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.query;

/**
 * Clase que contiene los Queries de Resistencia y Reactancia en Objetos estáticos de tipo String
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-13
 */
public class ResistanceReactanceQueries {
        
    /**
     * Encontrar reactancia.
     */
    public static final String FIND_REACTANCE = "SELECT\n" +
                                                "	rr.codigo,v.codigo,v.valor\n" +
                                                "FROM\n" +
                                                "	maestros.\"RESISTENCIA_REACTANCIA\" AS rr\n" +
                                                "LEFT JOIN\n" +
                                                "	maestros.\"CALIBRE\" AS c\n" +
                                                "ON\n" +
                                                "	(rr.calibre_codigo = c.codigo)\n" +
                                                "LEFT JOIN\n" +
                                                "	maestros.\"DUCTO\" AS d\n" +
                                                "ON\n" +
                                                "	(rr.ducto_codigo = d.codigo)\n" +
                                                "LEFT JOIN\n" +
                                                "	maestros.\"VALOR\" AS v\n" +
                                                "ON\n" +
                                                "	(rr.valor_codigo = v.codigo)\n" +
                                                "WHERE\n" +
                                                "	rr.tipo = ?\n" +
                                                "AND\n" +
                                                "	c.codigo = ?\n" +
                                                "AND\n" +
                                                "	d.codigo = ?";
    /**
     * Encontrar resistencia.
     */
    public static final String FIND_RESISTANCE =    "SELECT\n" +
                                                    "	rr.codigo,v.codigo,v.valor\n" +
                                                    "FROM\n" +
                                                    "	maestros.\"RESISTENCIA_REACTANCIA\" AS rr\n" +
                                                    "LEFT JOIN\n" +
                                                    "	maestros.\"CALIBRE\" AS c\n" +
                                                    "ON\n" +
                                                    "	(rr.calibre_codigo = c.codigo)\n" +
                                                    "LEFT JOIN\n" +
                                                    "	maestros.\"MATERIAL\" AS m\n" +
                                                    "ON\n" +
                                                    "	(rr.material_codigo = m.codigo)\n" +
                                                    "LEFT JOIN\n" +
                                                    "	maestros.\"DUCTO\" AS d\n" +
                                                    "ON\n" +
                                                    "	(rr.ducto_codigo = d.codigo)\n" +
                                                    "LEFT JOIN\n" +
                                                    "	maestros.\"VALOR\" AS v\n" +
                                                    "ON\n" +
                                                    "	(rr.valor_codigo = v.codigo)\n" +
                                                    "WHERE\n" +
                                                    "	rr.tipo = ?\n" +
                                                    "AND\n" +
                                                    "	m.codigo = ?\n" +
                                                    "AND\n" +
                                                    "	c.codigo = ?\n" +
                                                    "AND\n" +
                                                    "	d.codigo = ?;";
    
}
