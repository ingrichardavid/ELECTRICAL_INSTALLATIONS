/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.query;

/**
 * Clase que contiene los Queries de Porcentajes Motores Monofásicos en Objetos estáticos de tipo String
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-20
 */
public class PercentageSinglePhaseMotorsQueries {    
     
    /**
     * Mostrar todos los Porcentajes de Motores Monofásicos.
     */ 
    public static final String SELECT_ALL = "SELECT pm.codigo,\n" +
                                            "	pm.nombre,\n" +
                                            "	pm.porcentaje\n" +
                                            "FROM\n" +
                                            "	maestros.\"PORCENTAJE_MOTORES_MONOFASICOS\" AS pm\n" +
                                            "ORDER BY\n" +
                                            "	pm.codigo\n"+
                                            "ASC;";
        
}
