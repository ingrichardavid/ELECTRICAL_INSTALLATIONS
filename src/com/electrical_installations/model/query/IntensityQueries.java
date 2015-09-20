/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.query;

/**
 * Clase que contiene los Queries de la entidad Intensidad en Objetos estáticos
 * de tipo String
 *
 * @author Ing. Richard David
 * @version 1
 * @since 2015-09-08
 */
public class IntensityQueries {

    /**
     * Buscar Intensidades.
     */
    public static final String SELECT_ALL = "SELECT i.codigo,CONCAT(i.intensidad,' ',u.nombre) AS intensidad FROM maestros.\"INTENSIDAD\" AS i JOIN maestros.\"UNIDAD\" AS u ON (i.unidad_codigo=u.codigo);";

}
