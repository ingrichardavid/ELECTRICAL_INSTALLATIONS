/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.query;

/**
 * Clase que contiene los Queries de la entidad Elevador en Objetos estáticos de tipo String
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-24
 */
public class ElevatorQueries {
  
    /**
     * Mostrar todos todos los Elevadores
     */
    public static final String SELECT_ALL = "SELECT \n" +
                                            "	e.codigo,np.cantidad AS cant_persona,v.velocidad AS velocidad,(p.cantidad / 1000) AS cant_potencia \n" +
                                            "FROM \n" +
                                            "	maestros.\"ELEVADOR\" AS e\n" +
                                            "JOIN \n" +
                                            "	maestros.\"NUMERO_DE_PERSONAS\" AS np\n" +
                                            "ON \n" +
                                            "	e.numero_de_personas_codigo = np.codigo\n" +
                                            "JOIN \n" +
                                            "	maestros.\"VELOCIDAD\" AS v\n" +
                                            "ON  \n" +
                                            "	e.velocidad_codigo = v.codigo\n" +
                                            "JOIN \n" +
                                            "	maestros.\"POTENCIA\" AS p\n" +
                                            "ON \n" +
                                            "	e.potencia_codigo = p.codigo \n" +
                                            "ORDER BY \n" +
                                            "	e.numero_de_personas_codigo,e.velocidad_codigo ASC;";
      
    /**
     * Mostrar todos todas los Elevadores filtrados por nombre.
     */
    public static final String FILTER_BY_NAME = "SELECT \n" +
                                                "	e.codigo,np.cantidad AS cant_persona,v.velocidad AS velocidad,(p.cantidad / 1000) AS cant_potencia \n" +
                                                "FROM \n" +
                                                "	maestros.\"ELEVADOR\" AS e\n" +
                                                "JOIN \n" +
                                                "	maestros.\"NUMERO_DE_PERSONAS\" AS np\n" +
                                                "ON \n" +
                                                "	e.numero_de_personas_codigo = np.codigo\n" +
                                                "JOIN \n" +
                                                "	maestros.\"VELOCIDAD\" AS v\n" +
                                                "ON  \n" +
                                                "	e.velocidad_codigo = v.codigo\n" +
                                                "JOIN \n" +
                                                "	maestros.\"POTENCIA\" AS p\n" +
                                                "ON \n" +
                                                "	e.potencia_codigo = p.codigo\n" +
                                                "WHERE \n" +
                                                "	TRIM(LOWER(CONCAT(np.cantidad,' ',v.velocidad ,' ',p.cantidad))) LIKE TRIM(LOWER(?))\n" +
                                                "ORDER BY \n" +
                                                "	e.numero_de_personas_codigo,e.velocidad_codigo ASC;";
      
    
}
