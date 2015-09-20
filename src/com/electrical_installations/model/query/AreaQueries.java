/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.query;

/**
 * Clase que contiene los Queries de Area en Objetos estáticos de tipo String
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-02
 */
public class AreaQueries {
    
    /**
     * Insertar Area.
     */
    public static final String INSERT = "INSERT INTO negocio.\"AREA\"(\n" +
                                        "            proyecto_codigo, proyecto_tipo_instalacion, nombre, potencia_total, \n" +
                                        "            neutro, cantidad)\n" +
                                        "    VALUES (?, ?, ?, ?, \n" +
                                        "            ?, ?);";
        
    /**
     * Insertar Iluminaria y Toma Corriente en Área.
     */
    public static final String INSERT_ILUMINARIA_POWER_POINT =  "INSERT INTO negocio.\"AREA_ILUMINARIA_TOMA_CORRIENTES\"(area_codigo, calibres_codigo, voltaje_codigo, fase_codigo, \n" +
                                                                "            tipo, area_cantidad, constante, factor_potencia, calibre_codigo, \n" +
                                                                "            interruptor_codigo,acometida,longitud,ducto_codigo,angulo,calibre_a_usar)\n" +
                                                                "    VALUES (?, ?, ?, ?, \n" +
                                                                "            ?, ?, ?, ?, ?, \n" +
                                                                "            ?,?,?,?,?,?);";
    
    /**
     * Modificar Área.
     */
    public static final String UPDATE = "UPDATE negocio.\"AREA\"\n" +
                                        "   SET nombre=?, potencia_total=?, neutro=?\n" +
                                        " WHERE codigo=?;";
    
    /**
     * Modificar Iluminaria y Toma Corriente en Área.
     */
    public static final String UPDATE_ILUMINARIA_POWER_POINT =  "UPDATE negocio.\"AREA_ILUMINARIA_TOMA_CORRIENTES\"\n" +
                                                                "   SET calibres_codigo=?, voltaje_codigo=?, \n" +
                                                                "       fase_codigo=?, tipo=?, area_cantidad=?, constante=?, factor_potencia=?, \n" +
                                                                "       calibre_codigo=?, interruptor_codigo=?, acometida=?, longitud=?, \n" +
                                                                "       ducto_codigo=?, angulo=?,calibre_a_usar = ?\n" +
                                                                " WHERE codigo=?;";
    
    /**
     * ELiminar Área.
     */
    public static final String DELETE = "DELETE FROM negocio.\"AREA\" WHERE codigo=?;";
    
    /**
     * Buscar Área.
     */    
    public static final String SELECT = "SELECT codigo, nombre, potencia_total, \n" +
                                        "       neutro\n" +
                                        "  FROM negocio.\"AREA\"\n" +
                                        "WHERE \n" +
                                        "	codigo=1;";
    
    /**
     * Mostrar todos las Áreas asignadas a un proyecto.
     */ 
    public static final String SELECT_ALL = "SELECT a.codigo,\n" +
                                            "	a.nombre,\n" +
                                            "	a.potencia_total,\n" +
                                            "	a.neutro,\n" +
                                            "	a.cantidad\n" +
                                            "FROM\n" +
                                            "	negocio.\"AREA\" AS a\n" +
                                            "WHERE\n" +
                                            "	a.proyecto_codigo = ? AND a.proyecto_tipo_instalacion = ?\n" +
                                            "ORDER BY\n" +
                                            "	a.nombre;";
    
    /**
     * Validar nombre de Área.
     */
    public static final String VALIDATE_NAME = "SELECT codigo FROM negocio.\"AREA\" WHERE TRIM(LOWER(nombre)) = TRIM(LOWER(?)) AND proyecto_codigo=? AND proyecto_tipo_instalacion=?;";
        
    /**
     * Validar la existencia de un Área.
     */
    public static final String VALIDATE_EXISTENCE = "SELECT COUNT(codigo) FROM negocio.\"AREA\" WHERE codigo=?;";
       
    /**
     * Mostrar todos las Áreas asignadas a un proyecto filtradas por nombre.
     */    
    public static final String FILTER_BY_NAME = "SELECT a.codigo,\n" +
                                                "	a.nombre,\n" +
                                                "	a.potencia_total,\n" +
                                                "	a.neutro,\n" +
                                                "	a.cantidad\n" +
                                                "FROM\n" +
                                                "	negocio.\"AREA\" AS a\n" +
                                                "WHERE\n" +
                                                "	TRIM(LOWER(a.nombre)) LIKE TRIM(LOWER(?)) AND a.proyecto_codigo = ? AND a.proyecto_tipo_instalacion = ?\n" +
                                                "GROUP BY\n" +
                                                "	a.codigo,a.nombre\n" +
                                                "ORDER BY\n" +
                                                "	a.nombre;";
    /**
     * Encontrar reactancia.
     */
    public static final String FIND_REACTANCE = "SELECT\n" +
                                                "	rr.codigo,v.codigo,v.valor\n" +
                                                "FROM\n" +
                                                "	maestros.\"RESISTENCIA_REACTANCIA\" AS rr\n" +
                                                "LEFT JOIN\n" +
                                                "	maestros.\"CALIBRE\" AS c\n" +
                                                "ON\n" +
                                                "	(rr.calibre_codigo = c.codigo)\n" +
                                                "LEFT JOIN\n" +
                                                "	maestros.\"DUCTO\" AS d\n" +
                                                "ON\n" +
                                                "	(rr.ducto_codigo = d.codigo)\n" +
                                                "LEFT JOIN\n" +
                                                "	maestros.\"VALOR\" AS v\n" +
                                                "ON\n" +
                                                "	(rr.valor_codigo = v.codigo)\n" +
                                                "WHERE\n" +
                                                "	rr.tipo = ?\n" +
                                                "AND\n" +
                                                "	c.codigo = ?\n" +
                                                "AND\n" +
                                                "	d.codigo = ?";
    /**
     * Encontrar resistencia.
     */
    public static final String FIND_RESISTANCE =    "SELECT\n" +
                                                    "	rr.codigo,v.codigo,v.valor\n" +
                                                    "FROM\n" +
                                                    "	maestros.\"RESISTENCIA_REACTANCIA\" AS rr\n" +
                                                    "LEFT JOIN\n" +
                                                    "	maestros.\"CALIBRE\" AS c\n" +
                                                    "ON\n" +
                                                    "	(rr.calibre_codigo = c.codigo)\n" +
                                                    "LEFT JOIN\n" +
                                                    "	maestros.\"MATERIAL\" AS m\n" +
                                                    "ON\n" +
                                                    "	(rr.material_codigo = m.codigo)\n" +
                                                    "LEFT JOIN\n" +
                                                    "	maestros.\"DUCTO\" AS d\n" +
                                                    "ON\n" +
                                                    "	(rr.ducto_codigo = d.codigo)\n" +
                                                    "LEFT JOIN\n" +
                                                    "	maestros.\"VALOR\" AS v\n" +
                                                    "ON\n" +
                                                    "	(rr.valor_codigo = v.codigo)\n" +
                                                    "WHERE\n" +
                                                    "	rr.tipo = ?\n" +
                                                    "AND\n" +
                                                    "	m.codigo = ?\n" +
                                                    "AND\n" +
                                                    "	c.codigo = ?\n" +
                                                    "AND\n" +
                                                    "	d.codigo = ?;";
    
    /**
     * Encontrar Iluminaria y Toma Corriente para un Área específica.
     */
    public static final String FIND_ILUMINARIA_POWERPOINT = "SELECT \n" +
                                                            "	ait.codigo,cs.codigo,m.codigo,m.nombre,t.codigo,t.cantidad,i.codigo,i.intensidad,c.codigo,c.nombre,\n" +
                                                            "	v.codigo,v.voltaje,f.codigo,f.nombre,ait.tipo,ait.area_cantidad,ait.factor_potencia,clb.codigo,clb.nombre,\n" +
                                                            "	it.codigo,it.capacidad,ait.acometida,ait.longitud,d.codigo,d.nombre,ait.angulo\n" +
                                                            "FROM\n" +
                                                            "	negocio.\"AREA_ILUMINARIA_TOMA_CORRIENTES\" AS ait\n" +
                                                            "JOIN\n" +
                                                            "	negocio.\"AREA\" AS a\n" +
                                                            "ON\n" +
                                                            "	(ait.area_codigo = a.codigo)\n" +
                                                            "JOIN\n" +
                                                            "	maestros.\"CALIBRES\" AS cs\n" +
                                                            "ON\n" +
                                                            "	(ait.calibres_codigo = cs.codigo)\n" +
                                                            "JOIN\n" +
                                                            "	maestros.\"MATERIAL\" AS m\n" +
                                                            "ON\n" +
                                                            "	(cs.material_codigo = m.codigo)\n" +
                                                            "JOIN\n" +
                                                            "	maestros.\"TEMPERATURA\" AS t\n" +
                                                            "ON\n" +
                                                            "	(cs.temperatura_codigo = t.codigo)\n" +
                                                            "JOIN\n" +
                                                            "	maestros.\"INTENSIDAD\" AS i\n" +
                                                            "ON\n" +
                                                            "	(cs.intensidad_codigo = i.codigo)\n" +
                                                            "JOIN	\n" +
                                                            "	maestros.\"CALIBRE\" AS c\n" +
                                                            "ON\n" +
                                                            "	(cs.calibre_codigo = c.codigo)\n" +
                                                            "JOIN\n" +
                                                            "	maestros.\"VOLTAJE\" AS v\n" +
                                                            "ON\n" +
                                                            "	(ait.voltaje_codigo = v.codigo)\n" +
                                                            "JOIN\n" +
                                                            "	maestros.\"FASE\" AS f\n" +
                                                            "ON\n" +
                                                            "	(ait.fase_codigo = f.codigo)\n" +
                                                            "JOIN\n" +
                                                            "	maestros.\"CALIBRE\" AS clb\n" +
                                                            "ON\n" +
                                                            "	(ait.calibre_codigo = clb.codigo)\n" +
                                                            "JOIN\n" +
                                                            "	maestros.\"INTERRUPTOR\" AS it\n" +
                                                            "ON\n" +
                                                            "	(ait.interruptor_codigo = it.codigo)\n" +
                                                            "JOIN\n" +
                                                            "	maestros.\"DUCTO\" AS d\n" +
                                                            "ON\n" +
                                                            "	(ait.ducto_codigo = d.codigo)\n" +
                                                            "WHERE\n" +
                                                            "	a.codigo = ?";
    
}
