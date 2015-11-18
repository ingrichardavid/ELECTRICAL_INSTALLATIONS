/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.query;

/**
 * Clase que contiene los Queries de la entidad Circuito de Iluminación en Objetos estáticos de tipo String
 * @author JRichard
 * @version 1
 * @since 2015-11-16
 */
public class LightingCircuitQueries {
    
    /**
     * Insertar circuito de iluminación.
     */
    public static final String INSERT = "INSERT INTO \n" +
                                        "negocio.\"CIRCUITO_DE_ILUMINACION\"(proyecto_codigo, \n" +
                                        "proyecto_tipo_instalacion, calibre_fase_neutro, \n" +
                                        "tuberia, descripcion, intensidad_total)\n" +
                                        "VALUES (?, ?, ?, ?, ?, ?);";
    
    /**
     * Consulta para obtener todos los circuitos de iluminarias asociados a un proyecto.
     */
    public static final String SELECT = "SELECT codigo, calibre_fase_neutro, \n" +
                                        "       tuberia, descripcion, intensidad_total\n" +
                                        "  FROM negocio.\"CIRCUITO_DE_ILUMINACION\"\n" +
                                        "WHERE\n" +
                                        "	proyecto_codigo = ? AND proyecto_tipo_instalacion = ?;";
    
     /**
     * Consulta para filtrar todos los circuitos de iluminarias asociados a un proyecto.
     */
    public static final String SELECT_FOR_NAME = "SELECT \n" +
                                                    "codigo, calibre_fase_neutro,tuberia, descripcion, intensidad_total\n" +
                                                    "FROM \n" +
                                                    "negocio.\"CIRCUITO_DE_ILUMINACION\"\n" +
                                                    "WHERE\n" +
                                                    "TRIM\n" +
                                                    "(LOWER(CONCAT(descripcion,' ',intensidad_total))) \n" +
                                                    "LIKE TRIM(LOWER(?)) \n" +
                                                    "AND \n" +
                                                    "proyecto_codigo = ? \n" +
                                                    "AND \n" +
                                                    "proyecto_tipo_instalacion = ?;";
    
    /**
     * Consulta para eliminar un circuito de iluminación.
     */
    public static final String DELETE = "DELETE FROM negocio.\"CIRCUITO_DE_ILUMINACION\"\n" +
                                        " WHERE codigo=?;";
    
}
