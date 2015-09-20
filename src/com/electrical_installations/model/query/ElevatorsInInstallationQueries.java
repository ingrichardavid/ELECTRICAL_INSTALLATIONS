/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.query;

/**
 * Clase que contiene los Queries de la entidad Elevadores en instalación en Objetos estáticos de tipo String
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-26
 */
public class ElevatorsInInstallationQueries {
    
    /**
     * Insertar un Elevador en una instalación.
     */
    public static final String INSERT_ELEVATOR_IN_INSTALLATION = "INSERT INTO negocio.\"ELEVADORES_EN_LA_INSTALACION\"(\n" +
                                                                "            proyecto_codigo, proyecto_tipo_instalacion,elevador_codigo, cantidad, consumo_de_potencia, \n" +
                                                                "             temperatura, material, fase, voltaje)\n" +
                                                                "    VALUES (?, ?, ?, ?, \n" +
                                                                "            ?, ?, ?, ?, ?);"; 
    
    /**
     * Consultar todos los elevadores de una instalación.
     */
    public static final String SELECT_ALL_ELEVATORS_IN_INSTALLATION = "SELECT \n" +
                                                                    "	ei.proyecto_codigo, ei.proyecto_tipo_instalacion, ei.elevador_codigo, cantidad, ei.consumo_de_potencia, \n" +
                                                                    "	ei.temperatura, ei.material, ei.fase, ei.voltaje, e.numero_de_personas\n" +
                                                                    "FROM \n" +
                                                                    "	negocio.\"ELEVADORES_EN_LA_INSTALACION\" AS ei\n" +
                                                                    "LEFT JOIN\n" +
                                                                    "	negocio.\"ELEVADOR\" AS e\n" +
                                                                    "ON\n" +
                                                                    "	(ei.elevador_codigo = e.codigo)\n" +
                                                                    "WHERE \n" +
                                                                    "	ei.proyecto_codigo = ? AND ei.proyecto_tipo_instalacion = ?\n" +
                                                                    "ORDER BY\n" +
                                                                    "	e.numero_de_personas\n" +
                                                                    "ASC;";
    /**
     * Consultar todas las cargas asociadas a un área filtradas por nombre.
     */
    public static final String FILTER_BY_NAME = "SELECT \n" +
                                                "	ei.proyecto_codigo, ei.proyecto_tipo_instalacion, ei.elevador_codigo, ei.cantidad, ei.consumo_de_potencia, \n" +
                                                "	ei.temperatura, ei.material, ei.fase, ei.voltaje, e.numero_de_personas\n" +
                                                "FROM \n" +
                                                "	negocio.\"ELEVADORES_EN_LA_INSTALACION\" AS ei\n" +
                                                "LEFT JOIN\n" +
                                                "	negocio.\"ELEVADOR\" AS e\n" +
                                                "ON\n" +
                                                "	(ei.elevador_codigo = e.codigo)\n" +
                                                "WHERE \n" +
                                                "	TRIM(LOWER(CONCAT(e.numero_de_personas,' ',ei.cantidad,' ',ei.consumo_de_potencia,ei.voltaje))) LIKE TRIM(LOWER(?)) AND ei.proyecto_codigo = ? AND ei.proyecto_tipo_instalacion = ?\n" +
                                                "ORDER BY\n" +
                                                "	e.numero_de_personas\n" +
                                                "ASC;";
   
    /**
     * Modificar la cantidad de elevadores de una instalación.
     */
    public static final String UPDATE_ELEVATOR_IN_INSTALLATION = "UPDATE \n" +
                                                       "	negocio.\"ELEVADORES_EN_LA_INSTALACION\"\n" +
                                                       "SET \n" +
                                                       "	cantidad=cantidad+1\n" +
                                                       "WHERE \n" +
                                                       "	proyecto_codigo = ? AND proyecto_tipo_instalacion = ? AND elevador_codigo=?;";
    
    /**
     * Eliminar un elevador de una instalación.
     */
    public static final String DELETE_ELEVATOR_IN_INSTALLATION = "DELETE FROM\n" +
                                                       "	negocio.\"ELEVADORES_EN_LA_INSTALACION\"\n" +
                                                       "WHERE \n" +
                                                       "	proyecto_codigo = ? AND proyecto_tipo_instalacion = ? AND elevador_codigo=?;";
    
}
