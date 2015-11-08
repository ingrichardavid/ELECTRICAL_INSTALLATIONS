/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.query;

/**
 * Clase que contiene los Queries de los porcentajes trifásicos de motor en Objetos estáticos de tipo String
 * @author JRichard
 * @version 1
 * @since 2015-10-27
 */
public class PercentageOfThreePhaseMotorsQueries {
    
    /**
     * Consulta para obtener los tipos de motores trifásicos.
     */
    public static final String SELECT_TYPE_MOTOR = "SELECT DISTINCT(tipo_de_motor) \n" +
                                                    "FROM maestros.\"PORCENTAJE_MOTORES_TRIFASICOS\"\n" +
                                                    "ORDER BY tipo_de_motor;";  
     
    /**
     * Mostrar todos los Porcentajes de Motores Trifásicos.
     */ 
    public static final String SELECT_ALL = "SELECT\n" +
                                            "	codigo,nombre,porcentaje\n" +
                                            "FROM\n" +
                                            "	maestros.\"PORCENTAJE_MOTORES_TRIFASICOS\"\n" +
                                            "WHERE\n" +
                                            "	tipo_de_motor = ?;";
    
}
