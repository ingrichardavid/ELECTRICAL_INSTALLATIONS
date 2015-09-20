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
    public static final String SELECT_ALL = "SELECT codigo,numero_de_personas_codigo,velocidad_codigo,potencia_codigo FROM maestros.\"ELEVADOR\" ORDER BY numero_de_personas_codigo,velocidad_codigo ASC;";
      
    /**
     * Mostrar todos todas los Elevadores filtrados por nombre.
     */
    public static final String FILTER_BY_NAME = "SELECT e.codigo,e.numero_de_personas,e.velocidad,e.potencia FROM negocio.\"ELEVADOR\" AS e WHERE TRIM(LOWER(CONCAT(e.numero_de_personas,' ',e.velocidad,' ',e.potencia))) LIKE TRIM(LOWER(?)) ORDER BY e.numero_de_personas,e.velocidad ASC;";
      
    
}
