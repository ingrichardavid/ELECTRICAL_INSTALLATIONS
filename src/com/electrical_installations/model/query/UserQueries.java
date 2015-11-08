/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.query;

/**
 * Clase que contiene los Queries de Usuario en Objetos estáticos de tipo String
 * @author Ing. Richard David
 * @version 1
 * @since 2015-07-25
 */
public class UserQueries {
    
    /**
     * Insertar Usuario.
     */
    public static final String INSERT = "INSERT INTO negocio.\"USUARIO\"(\n" +
                                        "            nacionalidad, cedula, nombre, apellido, direccion, nombre_usuario, \n" +
                                        "            clave_usuario, telefono,estatus)\n" +
                                        "    VALUES (?, ?, ?, ?, ?, LOWER(?), \n" +
                                        "            negocio.digest(?,'sha1'),?,?);";
    
    /**
     * Modificar Usuario.
     */
    public static final String UPDATE = "UPDATE negocio.\"USUARIO\"\n" +
                                        "   SET nombre=?, apellido=?, direccion=?,\n" +
                                        "   telefono=?,estatus=?\n" +
                                        " WHERE nacionalidad =? AND cedula=? AND cedula != 0;";
    
    /**
     * Modificar datos de seguridad de Usuario.
     */
    public static final String UPDATE_USER = "UPDATE negocio.\"USUARIO\"\n" +
                                        "   SET nombre_usuario=LOWER(?),clave_usuario=negocio.digest(?,'sha1')\n" +
                                        " WHERE nacionalidad =? AND cedula=?;";
    
    /**
     * ELiminar Usuario.
     */
    public static final String DELETE = "DELETE FROM negocio.\"USUARIO\" WHERE nacionalidad=? AND cedula=? AND cedula != 0;";
    
    /**
     * Buscar Usuario.
     */    
    public static final String SELECT = "SELECT nacionalidad, cedula, nombre, apellido, direccion, nombre_usuario, \n" +
                                        "       telefono,estatus,TO_CHAR(fecha_registro,'DD/MM/YYYY') as fecha_registro\n" +
                                        "  FROM negocio.\"USUARIO\" WHERE nacionalidad=? AND cedula=?;";
    
    /**
     * Mostrar todos los usuarios.
     */
    public static final String SELECT_ALL = "SELECT CONCAT(nacionalidad,'-',cedula) AS cedula,nombre, apellido,nombre_usuario FROM negocio.\"USUARIO\" ORDER BY cedula ASC;";
    
    /**
     * Buscar usuarios filtrados por nombre.
     */    
    public static final String FILTER_BY_NAME = "SELECT CONCAT(nacionalidad,'-',cedula) AS cedula,nombre, apellido,nombre_usuario FROM negocio.\"USUARIO\" WHERE LOWER(nombre) LIKE LOWER(?) ORDER BY nombre ASC;";
    
    /**
     * Buscar usuarios filtrados por apellido. 
     */   
    public static final String FILTER_BY_LASTNAME = "SELECT CONCAT(nacionalidad,'-',cedula) AS cedula,nombre, apellido,nombre_usuario FROM negocio.\"USUARIO\" WHERE LOWER(apellido) LIKE LOWER(?) ORDER BY apellido ASC;";
    
    /**
     * Buscar usuarios filtrados por nombre de usuario. 
     */   
    public static final String FILTER_BY_USERNAME = "SELECT CONCAT(nacionalidad,'-',cedula) AS cedula,nombre, apellido,nombre_usuario FROM negocio.\"USUARIO\" WHERE LOWER(nombre_usuario) LIKE LOWER(?) ORDER BY nombre_usuario ASC;";
        
    /**
     * Inicio de sesión.
     */
    public static final String LOGIN = "SELECT nacionalidad,cedula,nombre,apellido,direccion,telefono,nombre_usuario FROM negocio.\"USUARIO\" WHERE nombre_usuario=LOWER(?) AND clave_usuario=negocio.digest(?,'sha1') AND estatus=true;";
    
    /**
     * Validar existencia de Usuario.
     */
    public static final String VALIDATE_EXISTENCE = "SELECT COUNT(cedula) FROM negocio.\"USUARIO\" WHERE nacionalidad=? AND cedula=?;";
    
    /**
     * Validar nombre de Usuario.
     */
    public static final String VALIDATE_USERNAME = "SELECT nacionalidad,cedula FROM negocio.\"USUARIO\" WHERE LOWER(nombre_usuario) = LOWER(?);";
    
    /**
     * Validar Usuario.
     */
    public static final String VALIDATE_USER = "SELECT CONCAT(nacionalidad,'-',cedula) AS cedula FROM negocio.\"USUARIO\" WHERE nacionalidad=? AND cedula=?;";
    
    /**
     * Validar clave de usuario.
     */
    public static final String VALIDATE_KEY = "SELECT COUNT(cedula) FROM negocio.\"USUARIO\" WHERE nacionalidad=? AND cedula=? AND clave_usuario=negocio.digest(?,'sha1');";
    
}
