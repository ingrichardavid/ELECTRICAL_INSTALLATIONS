/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.query;

/**
 * Clase que contiene los Queries de Tipo de Instalación en Objetos estáticos de tipo String
 * @author Ing. Richard David
 * @version 1
 * @since 2015-07-27
 */
public class TypeOfInstallationQueries {
  
    /**
     * Mostrar todos todos los tipos de instalación.
     */
    public static final String SELECT_ALL = "SELECT codigo,nombre FROM maestros.\"TIPO_DE_INSTALACION\" ORDER BY codigo ASC;";
    
}
