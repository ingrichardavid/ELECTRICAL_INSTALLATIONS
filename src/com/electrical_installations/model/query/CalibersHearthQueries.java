/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.query;

/**
 * Clase que contiene los Queries de la entidad Conductores de Tierra
 * @author Ing. Richard David
 * @version 1
 * @since 2015-10-04
 */
public class CalibersHearthQueries {

    /**
     * Consulta para encontrar un calibre de tierra de acuerdo a una intensidad.
     */
    public static final String FIND_CALIBER_HEARTH =    "SELECT \n" +
                                                        "	ct.codigo,ct.intensidad_codigo,i.intensidad,c.codigo,CONCAT('#',c.nombre,' Cu')\n" +
                                                        "FROM\n" +
                                                        "	maestros.\"CONDUCTORES_DE_TIERRA\" AS ct\n" +
                                                        "JOIN\n" +
                                                        "	maestros.\"CALIBRE\" AS c	\n" +
                                                        "ON\n" +
                                                        "	(ct.calibre_codigo = c.codigo)\n" +
                                                        "JOIN\n" +
                                                        "	maestros.\"INTENSIDAD\" AS i\n" +
                                                        "ON\n" +
                                                        "	(ct.intensidad_codigo = i.codigo)\n" +
                                                        "WHERE\n" +
                                                        "       ? <= i.intensidad\n" +
                                                        "ORDER BY\n" +
                                                        "	i.intensidad\n" +
                                                        "ASC LIMIT 1;";
       
}
