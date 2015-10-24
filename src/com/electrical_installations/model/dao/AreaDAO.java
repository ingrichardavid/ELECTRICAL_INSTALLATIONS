/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.dao;

import com.electrical_installations.model.entity.Area;
import com.electrical_installations.model.entity.AreaIluminariaPowerPoint;
import com.electrical_installations.model.entity.masters.Calibers;
import java.util.List;

/**
 * Intefaz para el acoplamiento de la entidad Area
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-02
 */
public interface AreaDAO {

    /**
     * Crear Area.
     * @param area
     * @param areaIluminariaPowerPoints
     * @return Retorna un valor booleano 
     */
    public boolean insert(Area area, List<AreaIluminariaPowerPoint> areaIluminariaPowerPoints);

    /**
     * Modificar datos básicos de una Área
     * @param area
     * @param areaIluminariaPowerPoints
     * @return Retorna un valor booleano
     */
    public boolean update(Area area, List<AreaIluminariaPowerPoint> areaIluminariaPowerPoints);

    /**
     * Validar nombre de usuario
     * @param area
     * @return Retorna una objeto Area
     */
    public Area validate_name(Area area);
    
    /**
     * Validar existencia de un área
     * @param area
     * @return 1 en caso de que la área exista 0 caso contrario
     */
    public int validate_existence(Area area);

    /**
     * Encontrar un Area
     * @param area
     * @return Retorna un objeto Area
     */
    public Area find(Area area);
    
    /**
     * Listar todas las Areas que le pertenecen a un proyecto
     * @param area
     * @return Retorna una lista de Areas
     */
    public List<Area> find_areas(Area area);
    
    /**
     * Listar todas las Areas que le pertenecen a un proyecto filtradas por nombre
     * @param area
     * @return Retorna una lista de Areas
     */
    public List<Area> filter_by_name(Area area);
        
    /**
     * Eliminar un Área
     * @param area
     * @return Retorna un booleano
     */
    public boolean delete(Area area);
    
    /**
     * Método para encontrar el calibre deseado para iluminaria o toma corrientes.
     * @param calibers
     * @param roominess
     * @return Retorna el código del registro, el código y nombre del calibre.
     */
    public Calibers find_caliber_iluminaria_power_point(Calibers calibers,double roominess);
    
    /**
     * Método para encontrar la Iluminaria y Toma corriente de un Área.
     * @param area
     * @return Retorna un arreglo con la Iluminaria y Toma Corriente.
     */
    public List<AreaIluminariaPowerPoint> find_iluminaria_powerPoint(Area area);
    
}
