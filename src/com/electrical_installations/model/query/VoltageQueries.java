/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.query;

/**
 * Clase que contiene los Queries de la entidad Voltaje en Objetos estáticos de tipo String
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-10
 */
public class VoltageQueries {
 
    /**
     * Buscar Voltages.
     */    
    public static final String SELECT_ALL = "SELECT \n" +
                                            "v.codigo,v.voltaje,CONCAT(v.voltaje,' ',u.nombre) AS voltage \n" +
                                            "FROM \n" +
                                            "maestros.\"VOLTAJE\" AS v \n" +
                                            "JOIN \n" +
                                            "maestros.\"UNIDAD\" AS u \n" +
                                            "ON \n" +
                                            "(v.unidad_codigo=u.codigo)\n" +
                                            "ORDER BY \n" +
                                            "v.voltaje;";
       
    public static final String SELECT_ALL_MOTORS_TRHEE_PHASES = "SELECT \n" +
                                                                "v.codigo,v.voltaje,CONCAT(v.voltaje,' ',u.nombre) AS voltage \n" +
                                                                "FROM \n" +
                                                                "maestros.\"VOLTAJE_MOTORES_TRIFASICOS\" AS v \n" +
                                                                "JOIN \n" +
                                                                "maestros.\"UNIDAD\" AS u \n" +
                                                                "ON \n" +
                                                                "(v.unidad_codigo=u.codigo) \n" +
                                                                "ORDER BY \n" +
                                                                "v.voltaje;";
}
