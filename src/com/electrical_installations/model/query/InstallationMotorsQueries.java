/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.query;

/**
 * Clase que contiene los Queries de Motores en la Instalación en Objetos estáticos de tipo String
 * @author JRichard
 * @version 1 
 * @since 2015-10-31
 */
public class InstallationMotorsQueries {
    
    
    /**
    * Insertar motores en la instalacion.
    */
    public static final String INSERT = "INSERT INTO \n" +
                                        "negocio.\"MOTORES_EN_INSTALACION\"(\n" +
                                        "proyecto_codigo, proyecto_tipo_instalacion, descripcion, \n" +
                                        "intensidad, cantidad, calibre_fase, calibre_neutro, calibre_tierra,tipo_fase_codigo,caballo_de_fuerza,breaker, tuberia, tuberia_material)\n" +
                                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    
    /**
     * Consultar motores en una instalación.
     */    
    public static final String SELECT_ALL = "SELECT\n" +
                                            "	mei.codigo, mei.descripcion, mei.intensidad, mei.cantidad, tf.codigo, mei.caballo_de_fuerza,mei.breaker\n" +
                                            "FROM \n" +
                                            "	negocio.\"MOTORES_EN_INSTALACION\" AS mei\n" +
                                            "JOIN\n" +
                                            "	maestros.\"TIPO_FASE\" AS tf\n" +
                                            "ON\n" +
                                            "	(mei.tipo_fase_codigo = tf.codigo)\n" +
                                            "WHERE\n" +
                                            "	mei.proyecto_codigo = ? AND mei.proyecto_tipo_instalacion = ?";
    
    /**
     * Consultar motores en una instalación filtrados por nombre.
     */
    public static final String SELECT_ALL_FILTER_NAME = "SELECT\n" +
                                                        "	mei.codigo, mei.descripcion, mei.intensidad, mei.cantidad, tf.codigo, mei.caballo_de_fuerza,mei.breaker\n" +
                                                        "FROM \n" +
                                                        "	negocio.\"MOTORES_EN_INSTALACION\" AS mei\n" +
                                                        "JOIN\n" +
                                                        "	maestros.\"TIPO_FASE\" AS tf\n" +
                                                        "ON\n" +
                                                        "	(mei.tipo_fase_codigo = tf.codigo)\n" +
                                                        "WHERE\n" +
                                                        "TRIM\n" +
                                                        "(LOWER(CONCAT(mei.codigo,' ',mei.descripcion,' ',tf.nombre,' ',mei.caballo_de_fuerza,' ',mei.intensidad,' ',mei.cantidad))) LIKE TRIM(LOWER(?)) \n" +
                                                        "AND\n" +
                                                        "mei.proyecto_codigo = ? \n" +
                                                        "AND mei.proyecto_tipo_instalacion = ?;";
 
    /**
     * Eliminar un motor de una instalación.
     */
    public static final String DELETE_INSTALLATION_MOTOR = "DELETE FROM negocio.\"MOTORES_EN_INSTALACION\"\n" +
                                                        " WHERE codigo = ?;";
    
    /**
     * Validar si existe la descripción 
     */
    public static final String VALIDATE_DESCRIPTION =  "SELECT \n" +
                                                        "codigo\n" +
                                                        "FROM \n" +
                                                        "negocio.\"MOTORES_EN_INSTALACION\"\n" +
                                                        "WHERE TRIM \n" +
                                                        "(LOWER(descripcion)) = \n" +
                                                        "TRIM\n" +
                                                        "(LOWER(?)) AND proyecto_codigo=? AND proyecto_tipo_instalacion=?;";
    
}
