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
    public static final String INSERT_CHARGE_IN_AREA =  "INSERT INTO negocio.\"CARGAS_EN_AREAS\"(\n" +
                                                        "            codigo_carga, codigo_area, potencia, cantidad, calibre_fase, \n" +
                                                        "            calibre_neutro, calibre_tierra, fase_codigo, tuberia, tuberia_material, cantidad_en_voltaje)\n" +
                                                        "    VALUES (?, ?, ?, ?, ?, \n" +
                                                        "            ?, ?, ?, ?, ?, ?);"; 
    /**
     * Modificar un área luego de haber insertado una carga.
     */
    public static final String UPDATE_AREA_AFTER_INSERT_CHARGE = "UPDATE negocio.\"AREA\" SET potencia_total= potencia_total + ?, neutro = neutro + ? WHERE codigo = ?;";
    
    /**
     * Modificar un área luego de haber eliminado una carga.
     */
    public static final String UPDATE_AREA_AFTER_DELETE_CHARGE = "UPDATE negocio.\"AREA\" SET potencia_total= ?,neutro = ? WHERE codigo = ?;";
    
    
    /**
     * Consultar todas las cargas asignadas a un área.
     */
    public static final String SELECT_ALL_CHARGES_IN_AREA = "SELECT\n" +
                                                            "	cea.codigo_area,cea.codigo_carga,CONCAT(c.nombre,' ',e.cantidad,' ',u.nombre),cea.cantidad,cea.potencia,cea.fase_codigo,tp.codigo,tp.tipo\n" +
                                                            "FROM\n" +
                                                            "	negocio.\"CARGAS_EN_AREAS\" AS cea\n" +
                                                            "JOIN\n" +
                                                            "	maestros.\"CARGA\" AS c\n" +
                                                            "ON \n" +
                                                            "	(cea.codigo_carga = c.codigo)\n" + 
                                                            "JOIN\n" +
                                                            "	maestros.\"TIPO_CARGA\" AS tp\n" +
                                                            "ON \n" +
                                                            "	(c.tipo_carga_codigo = tp.codigo)\n" +
                                                            "LEFT JOIN\n" +
                                                            "	maestros.\"ENERGIA\" AS e\n" +
                                                            "ON\n" +
                                                            "	(c.energia_codigo = e.codigo)\n" +
                                                            "LEFT JOIN\n" +
                                                            "	maestros.\"UNIDAD\" AS u\n" +
                                                            "ON\n" +
                                                            "	(e.unidad_codigo = u.codigo)\n" +
                                                            "WHERE\n" +
                                                            "	cea.codigo_area = ?\n" +
                                                            "ORDER BY\n" +
                                                            "	c.nombre\n" +
                                                            "ASC;";
         
    /**
     * Consultar todas las cargas asignadas a un área filtradas por nombre.
     */
    public static final String FILTER_BY_NAME = "SELECT\n" +
                                                "	cea.codigo_area,cea.codigo_carga,CONCAT(c.nombre,' ',e.cantidad,' ',u.nombre),cea.cantidad,cea.potencia,cea.fase_codigo,tp.codigo,tp.tipo\n" +
                                                "FROM\n" +
                                                "	negocio.\"CARGAS_EN_AREAS\" AS cea\n" +
                                                "JOIN\n" +
                                                "	maestros.\"CARGA\" AS c\n" +
                                                "ON \n" +
                                                "       (cea.codigo_carga = c.codigo)\n" + 
                                                "JOIN\n" +
                                                "	maestros.\"TIPO_CARGA\" AS tp\n" +
                                                "ON \n" +
                                                "	(c.tipo_carga_codigo = tp.codigo)\n" +
                                                "LEFT JOIN\n" +
                                                "	maestros.\"ENERGIA\" AS e\n" +
                                                "ON\n" +
                                                "	(c.energia_codigo = e.codigo)\n" +
                                                "LEFT JOIN\n" +
                                                "	maestros.\"UNIDAD\" AS u\n" +
                                                "ON\n" +
                                                "	(e.unidad_codigo = u.codigo)\n" +
                                                "WHERE\n" +
                                                "	TRIM(LOWER(CONCAT(c.nombre,' ',e.cantidad,' ',u.nombre))) LIKE TRIM(LOWER(?)) AND cea.codigo_area = ?\n" +
                                                "ORDER BY\n" +
                                                "	c.nombre\n" +
                                                "ASC;";
        
    /**
     * Eliminar carga en un área.
     */
    public static final String DELETE_CHARGE_IN_AREA = "DELETE FROM\n" +
                                                       "	negocio.\"CARGAS_EN_AREAS\"\n" +
                                                       "WHERE \n" +
                                                       "	codigo_carga=? AND codigo_area=?;";
    
    /**
     * Validar que una carga ya haya sido asignada a un área.
     */
    public static final String VALIDATE_CHARGE_IN_AREA = "SELECT codigo_carga FROM\n" +
                                                         "  negocio.\"CARGAS_EN_AREAS\"\n" +
                                                         "WHERE \n" +
                                                         "  codigo_carga=? AND codigo_area=?;";
    
    /**
     * Insertar Alimentador Principal.
     */
    public static final String INSERT_MAIN_FEEDER_TYPE_CHARGE =  "INSERT INTO negocio.\"ALIMENTADOR_PRINCIPAL\"(\n" +
                                                                 "            proyecto_codigo, proyecto_tipo, tipo_carga_codigo, potencia, \n" +
                                                                 "            cantidad, intensidad, neutro)\n" +
                                                                 "    VALUES (?, ?, ?, ?, \n" +
                                                                 "            ?, ?, ?);"; 
    
     /**
     * Modificar tipo de carga en la entidad alimentador principal.
     */
    public static final String UPDATE_MAIN_FEEDER_TYPE_CHARGE = "UPDATE negocio.\"ALIMENTADOR_PRINCIPAL\"\n" +
                                                                "SET potencia= potencia + ?, cantidad= cantidad + ?, intensidad= intensidad + ?, neutro = neutro + ?\n" +
                                                                "WHERE proyecto_codigo=? AND proyecto_tipo=? AND tipo_carga_codigo=?;";
    /**
     * Validar existencia de tipo de carga en la entidad alimentador principal.
     */
    public static final String VALIDATE_MAIN_FEEDER_EXIST = "SELECT \n" +
                                                            "	proyecto_codigo\n" +
                                                            "FROM\n" +
                                                            "	negocio.\"ALIMENTADOR_PRINCIPAL\"\n" +
                                                            "WHERE \n" +
                                                            "	proyecto_codigo=? AND proyecto_tipo=? AND tipo_carga_codigo=?;";
    /**
     * Modificar tipo de carga en entidad alimentador principal una vez eliminada una carga de un área.
     */
    public static final String DELETE_CHARGE_UPDATE_MAIN_FEEDER_TYPE_CHARGE = "UPDATE negocio.\"ALIMENTADOR_PRINCIPAL\"\n" +
                                                                "SET potencia= potencia - ?, cantidad = cantidad - ?, intensidad= intensidad - ?, neutro = neutro - ?\n" +
                                                                "WHERE proyecto_codigo=? AND proyecto_tipo=? AND tipo_carga_codigo=?;";
    
    /**
     * Elimina tipo de carga de la entidad alimentador principal.
     */
    public static final String DELETE_CHARGE_MAIN_FEEDER = "DELETE FROM negocio.\"ALIMENTADOR_PRINCIPAL\"\n" +
                                                           " WHERE proyecto_codigo=? AND proyecto_tipo=? AND tipo_carga_codigo=?;";
    
    /**
     * Validate si Intensidad, Potencia o Cantidad es igual a 0.
     */
    public static final String VALIDATE_MAIN_FEEDER_I_P_C ="SELECT proyecto_codigo,cantidad,intensidad,potencia\n" +
                                                         "FROM negocio.\"ALIMENTADOR_PRINCIPAL\"\n" +
                                                         "WHERE proyecto_codigo=? AND proyecto_tipo=? AND tipo_carga_codigo=? \n" +
                                                         "AND cantidad = 0 AND intensidad = 0 AND potencia = 0;";
 
    /**
     * Calcular la cantidad de cargas que se encuentran asignadas a un Área.
     */
    public static final String COUNT_CHARGES_IN_AREA = "SELECT\n" +
                                                    "	COUNT(ca.codigo_area) AS cantidad\n" +
                                                    "FROM\n" +
                                                    "	negocio.\"CARGAS_EN_AREAS\" AS ca\n" +
                                                    "WHERE\n" +
                                                    "	ca.codigo_area = ?;";
 
    /**
     * Consultar el voltaje de una carga en un área determinadad.
     */
    public static final String CONSULT_VOLTAGE = "SELECT cantidad_en_voltaje\n" +
                                                "  FROM negocio.\"CARGAS_EN_AREAS\"\n" +
                                                "WHERE\n" +
                                                "	codigo_carga = ?\n" +
                                                "AND \n" +
                                                "	codigo_area = ?;";
    
}
