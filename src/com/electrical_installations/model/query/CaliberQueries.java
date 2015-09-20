/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.query;

/**
 * Clase que contiene los Queries de la entidad Calibre en Objetos estáticos de tipo String
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-08
 */
public class CaliberQueries {
 
    /**
     * Buscar Calibre.
     */    
    public static final String SELECT_ALL = "SELECT c.codigo, c.nombre FROM maestros.\"CALIBRE\" AS c;";
    
    /**
     * Encontrar calibre para iluminaria o toma corrientes.
     */
    public static final String FIND_CALIBER =   "SELECT\n" +
                                                "	c.codigo,l.codigo,l.nombre\n" +
                                                "FROM \n" +
                                                "	maestros.\"CALIBRES\" AS c\n" +
                                                "LEFT JOIN\n" +
                                                "	maestros.\"MATERIAL\" AS m\n" +
                                                "ON\n" +
                                                "	(c.material_codigo = m.codigo)\n" +
                                                "LEFT JOIN\n" +
                                                "	maestros.\"TEMPERATURA\" AS t\n" +
                                                "ON\n" +
                                                "	(c.temperatura_codigo = t.codigo)\n" +
                                                "LEFT JOIN\n" +
                                                "	maestros.\"INTENSIDAD\" AS i\n" +
                                                "ON\n" +
                                                "	(c.intensidad_codigo = i.codigo)\n" +
                                                "LEFT JOIN\n" +
                                                "	maestros.\"CALIBRE\" AS l\n" +
                                                "ON\n" +
                                                "	(c.calibre_codigo = l.codigo)\n" +
                                                "WHERE\n" +
                                                "	m.codigo = ? \n" +
                                                "AND \n" +
                                                "	t.codigo = ? \n" +
                                                "AND\n" +
                                                "	? <= (i.intensidad * ?) \n" +
                                                "LIMIT 1;";
       
}
