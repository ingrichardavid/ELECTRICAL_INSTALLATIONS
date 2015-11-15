/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.query;

/**
 * Clase que contiene los Queries de Proyecto en Objetos estáticos de tipo String
 * @author Ing. Richard David
 * @version 1
 * @since 2015-07-27
 */
public class ProjectQueries {
    
    /**
     * Insertar Proyecto.
     */
    public static final String INSERT = "INSERT INTO negocio.\"PROYECTO\"(\n" +
                                        "            usuario_nacionalidad, usuario_cedula, tipo_de_instalacion_codigo, nombre, \n" +
                                        "            potencia_total)\n" +
                                        "    VALUES (?, ?, ?, ?, ?);";
    
    /**
     * Modificar Proyecto.
     */
    public static final String UPDATE = "UPDATE negocio.\"PROYECTO\"\n" +
                                        "   SET tipo_de_instalacion_codigo=?,nombre=?, potencia_total=?\n" +
                                        " WHERE codigo=?;";
    /**
     * Eliminar Proyecto.
     */
    public static final String DELETE = "DELETE FROM negocio.\"PROYECTO\" WHERE codigo=?;";
    
    /**
     * Buscar Proyecto.
     */    
    public static final String SELECT = "SELECT \n" +
                                        "	p.codigo,t.codigo,t.nombre,p.nombre,p.potencia_total,TO_CHAR(p.fecha_registro,'DD/MM/YYYY') AS fecha_registro\n" +
                                        "FROM \n" +
                                        "	negocio.\"PROYECTO\" AS p \n" +
                                        "JOIN \n" +
                                        "	maestros.\"TIPO_DE_INSTALACION\" AS t\n" +
                                        "ON\n" +
                                        "	(p.tipo_de_instalacion_codigo = t.codigo)\n" +
                                        "WHERE\n" +
                                        "	TRIM(LOWER(p.nombre)) = TRIM(LOWER(?));";
   
    /**
     * Buscar todos los proyectos asociados a un usuario.
     */
    public static final String SELECT_ALL = "SELECT \n" +
                                        "	p.codigo,t.codigo,t.nombre,p.nombre,p.potencia_total,TO_CHAR(p.fecha_registro,'DD/MM/YYYY') AS fecha_registro\n" +
                                        "FROM \n" +
                                        "	negocio.\"PROYECTO\" AS p \n" +
                                        "JOIN \n" +
                                        "	maestros.\"TIPO_DE_INSTALACION\" AS t\n" +
                                        "ON\n" +
                                        "	(p.tipo_de_instalacion_codigo = t.codigo)\n" +
                                        "JOIN\n" +
                                        "	negocio.\"USUARIO\" AS u\n" +
                                        "ON\n" +
                                        "	(p.usuario_nacionalidad = u.nacionalidad AND p.usuario_cedula=u.cedula)\n" +
                                        "WHERE\n" +
                                        "	u.nacionalidad = ? AND u.cedula=? ORDER BY p.nombre ASC;";
   
    /**
     * Buscar todos los proyectos asociados a un usuario filtrados por nombre.
     */
    public static final String FILTER_BY_NAME = "SELECT \n" +
                                        "	p.codigo,t.codigo,t.nombre,p.nombre,p.potencia_total,TO_CHAR(p.fecha_registro,'DD/MM/YYYY') AS fecha_registro\n" +
                                        "FROM \n" +
                                        "	negocio.\"PROYECTO\" AS p \n" +
                                        "JOIN \n" +
                                        "	maestros.\"TIPO_DE_INSTALACION\" AS t\n" +
                                        "ON\n" +
                                        "	(p.tipo_de_instalacion_codigo = t.codigo)\n" +
                                        "JOIN\n" +
                                        "	negocio.\"USUARIO\" AS u\n" +
                                        "ON\n" +
                                        "	(p.usuario_nacionalidad = u.nacionalidad AND p.usuario_cedula=u.cedula)\n" +
                                        "WHERE\n" +
                                        "	u.nacionalidad = ? AND u.cedula=? AND LOWER(p.nombre) LIKE LOWER(?) ORDER BY p.nombre ASC;";
   
    /**
     * Buscar todos los proyectos asociados a un usuario filtrados por tipo.
     */
    public static final String FILTER_BY_TYPE = "SELECT \n" +
                                        "	p.codigo,t.codigo,t.nombre,p.nombre,p.potencia_total,TO_CHAR(p.fecha_registro,'DD/MM/YYYY') AS fecha_registro\n" +
                                        "FROM \n" +
                                        "	negocio.\"PROYECTO\" AS p \n" +
                                        "JOIN \n" +
                                        "	maestros.\"TIPO_DE_INSTALACION\" AS t\n" +
                                        "ON\n" +
                                        "	(p.tipo_de_instalacion_codigo = t.codigo)\n" +
                                        "JOIN\n" +
                                        "	negocio.\"USUARIO\" AS u\n" +
                                        "ON\n" +
                                        "	(p.usuario_nacionalidad = u.nacionalidad AND p.usuario_cedula=u.cedula)\n" +
                                        "WHERE\n" +
                                        "	u.nacionalidad = ? AND u.cedula=? AND LOWER(t.nombre) LIKE LOWER(?) ORDER BY t.nombre ASC;";
    
    /**
     * Validar nombre de un proyecto para un usuario.
     */
    public static final String VALIDATE_NAME_PROJECT = "SELECT \n" +
                                                        "	codigo,nombre\n" +
                                                        "FROM\n" +
                                                        "	negocio.\"PROYECTO\"\n" +
                                                        "WHERE\n" +
                                                        "	TRIM(LOWER(nombre))=TRIM(LOWER(?)) AND usuario_nacionalidad=? AND usuario_cedula=?;";
    /**
     * Consulta para validar existencia de un proyecto.
     */
    public static final String VALIDATE_PROJECT = "SELECT \n" +
                                                    "	codigo\n" +
                                                    "FROM\n" +
                                                    "	negocio.\"PROYECTO\"\n" +
                                                    "WHERE\n" +
                                                    "	codigo=?;";
    
    /**
     * Consulta para modificar la intensidad acumulada en motores en un proyecto.
     */
    public static final String UPDATE_INTENSITY_TOTAL = "UPDATE\n" +
                                                    "	negocio.\"PROYECTO\"\n" +
                                                    "SET\n" +
                                                    "	intensidad_motores = intensidad_motores + ?\n" +
                                                    "WHERE\n" +
                                                    "	codigo = ? AND tipo_de_instalacion_codigo = ?";
    
    /**
     * Consulta para modificar la intensidad acumulada en motores en un proyecto cuando un motor es eliminado.
     */
    public static final String UPDATE_INTENSITY_TOTAL_DELETE = "UPDATE\n" +
                                                    "	negocio.\"PROYECTO\"\n" +
                                                    "SET\n" +
                                                    "	intensidad_motores = intensidad_motores - ?\n" +
                                                    "WHERE\n" +
                                                    "	codigo = ? AND tipo_de_instalacion_codigo = ?";
    
    /**
     * Consulta para modificar en el proyecto los campo fase y tierra de motor.
     */
    public static final String UPDATE_PROJECT_PHASE_EARTH_MOTOR = "UPDATE\n" +
                                                                    "	negocio.\"PROYECTO\"\n" +
                                                                    "SET \n" +
                                                                    "	fase_motor=?, tierra_motor=?\n" +
                                                                    "WHERE \n" +
                                                                    "	codigo = ? \n" +
                                                                    "AND \n" +
                                                                    "	tipo_de_instalacion_codigo = ?;";
    
}
