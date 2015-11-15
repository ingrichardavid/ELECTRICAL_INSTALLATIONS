/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.query;

/**
 * Clase que contiene los Queries de la entidad Alimentardor Principal en Objetos estáticos de tipo String
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-02
 */
public class MainFeederQueries {
  
    /**
     * Insertar Alimentador Principal.
     */
    public static final String INSERT_MAIN_FEEDER_TYPE_CHARGE =  "INSERT INTO negocio.\"ALIMENTADOR_PRINCIPAL\"(\n" +
                                                                 "            proyecto_codigo, proyecto_tipo, tipo_carga_codigo, potencia, \n" +
                                                                 "            cantidad, intensidad)\n" +
                                                                 "    VALUES (?, ?, ?, ?, \n" +
                                                                 "            ?, ?);"; 
    
     /**
     * Modificar tipo de carga en la entidad alimentador principal.
     */
    public static final String UPDATE_MAIN_FEEDER_TYPE_CHARGE = "UPDATE negocio.\"ALIMENTADOR_PRINCIPAL\"\n" +
                                                                "SET potencia= potencia + ?, cantidad= cantidad + ?, intensidad= intensidad + ?\n" +
                                                                "WHERE proyecto_codigo=? AND proyecto_tipo=? AND tipo_carga_codigo=?;";
    
     /**
     * Consulta para modificar el tipo de carga iluminaria y toma corriente en la entidad alimentador principal.
     */
    public static final String UPDATE_MAIN_FEEDER_TYPE_CHARGE_ILUMIARIA_POWER_POINT = "UPDATE negocio.\"ALIMENTADOR_PRINCIPAL\"\n" +
                                                                "SET potencia = (potencia - ?) + ?, cantidad = cantidad + ?, intensidad = intensidad + ?\n" +
                                                                "WHERE proyecto_codigo = ? AND proyecto_tipo=? AND tipo_carga_codigo=?;";
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
                                                                "SET potencia= potencia - ?, cantidad= cantidad - ?, intensidad= intensidad - ?\n" +
                                                                "WHERE proyecto_codigo=? AND proyecto_tipo=? AND tipo_carga_codigo=?;";
    
    /**
     * Elimina tipo de carga de la entidad alimentador principal.
     */
    public static final String DELETE_CHARGE_MAIN_FEEDER = "DELETE FROM negocio.\"ALIMENTADOR_PRINCIPAL\"\n" +
                                                           " WHERE proyecto_codigo=? AND proyecto_tipo=? AND tipo_carga_codigo=?;";
    
    /**
     * Validate si Intensidad, Potencia o Cantidad es igual a 0.
     */
    public static final String VALIDATE_MAIN_FEEDER_I_P_C ="SELECT proyecto_codigo\n" +
                                                         "FROM negocio.\"ALIMENTADOR_PRINCIPAL\"\n" +
                                                         "WHERE proyecto_codigo=? AND proyecto_tipo=? AND tipo_carga_codigo=? \n" +
                                                         "AND cantidad = 0 AND intensidad = 0 AND potencia = 0;";
 
}
