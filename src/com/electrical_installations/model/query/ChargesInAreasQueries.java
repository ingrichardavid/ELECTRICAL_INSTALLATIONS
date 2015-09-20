/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.query;

/**
 * Clase que contiene los Queries de la entidad Cargas en Áreas en Objetos estáticos de tipo String
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-02
 */
public class ChargesInAreasQueries {
    
    /**
     * Insertar Carga en Área.
     */
    public static final String INSERT_CHARGE_IN_AREA = "INSERT INTO negocio.\"CARGAS_EN_AREAS\"(\n" +
                                                       "    carga_codigo, area_codigo, cantidad, consumo_potencia, voltaje, temperatura, material, fase)\n" +
                                                       "    VALUES (?, ?, ?, ?, ?, ?, ? ,?);"; 
    
    /**
     * Consultar todas las cargas asignadas a un área.
     */
    public static final String SELECT_ALL_CHARGES_IN_AREA = "SELECT\n" +
                                                            "	ca.area_codigo,ca.carga_codigo,CONCAT(c.nombre,' ',e.cantidad,' ',u.nombre),ca.cantidad,ca.consumo_potencia,ca.voltaje\n" +
                                                            "FROM\n" +
                                                            "	negocio.\"CARGAS_EN_AREAS\" AS ca\n" +
                                                            "JOIN\n" +
                                                            "	negocio.\"CARGA\" AS c\n" +
                                                            "ON \n" +
                                                            "	(ca.carga_codigo = c.codigo)\n" +
                                                            "LEFT JOIN\n" +
                                                            "	negocio.\"ENERGIA\" as e\n" +
                                                            "ON\n" +
                                                            "	(c.energia_codigo = e.codigo)\n" +
                                                            "LEFT JOIN\n" +
                                                            "	negocio.\"UNIDAD_DE_ENERGIA\" AS u\n" +
                                                            "ON\n" +
                                                            "	(e.unidad_energia_codigo = u.codigo)\n" +
                                                            "WHERE\n" +
                                                            "	ca.area_codigo = ?\n" +
                                                            "ORDER BY\n" +
                                                            "	c.nombre\n" +
                                                            "ASC;";
         
    /**
     * Consultar todas las cargas asignadas a un área filtradas por nombre.
     */
    public static final String FILTER_BY_NAME = "SELECT\n" +
                                                            "	ca.area_codigo,ca.carga_codigo,CONCAT(c.nombre,' ',e.cantidad,' ',u.nombre),ca.cantidad,ca.consumo_potencia,ca.voltaje\n" +
                                                            "FROM\n" +
                                                            "	negocio.\"CARGAS_EN_AREAS\" AS ca\n" +
                                                            "JOIN\n" +
                                                            "	negocio.\"CARGA\" AS c\n" +
                                                            "ON \n" +
                                                            "	(ca.carga_codigo = c.codigo)\n" +
                                                            "LEFT JOIN\n" +
                                                            "	negocio.\"ENERGIA\" as e\n" +
                                                            "ON\n" +
                                                            "	(c.energia_codigo = e.codigo)\n" +
                                                            "LEFT JOIN\n" +
                                                            "	negocio.\"UNIDAD_DE_ENERGIA\" AS u\n" +
                                                            "ON\n" +
                                                            "	(e.unidad_energia_codigo = u.codigo)\n" +
                                                            "WHERE\n" +
                                                            "	TRIM(LOWER(CONCAT(c.nombre,' ',e.cantidad,' ',u.nombre))) LIKE TRIM(LOWER(?)) AND ca.area_codigo = ?\n" +
                                                            "ORDER BY\n" +
                                                            "	c.nombre\n" +
                                                            "ASC;";
    
    /**
     * Modificar la cantidad de una carga asignada a una área.
     */
    public static final String UPDATE_CHARGE_IN_AREA = "UPDATE \n" +
                                                       "	negocio.\"CARGAS_EN_AREAS\"\n" +
                                                       "SET \n" +
                                                       "	cantidad=cantidad+1\n" +
                                                       "WHERE \n" +
                                                       "	carga_codigo=? AND area_codigo=?;";
    
    /**
     * Eliminar carga en un área.
     */
    public static final String DELETE_CHARGE_IN_AREA = "DELETE FROM\n" +
                                                       "	negocio.\"CARGAS_EN_AREAS\"\n" +
                                                       "WHERE \n" +
                                                       "	carga_codigo=? AND area_codigo=?;";
    
}
