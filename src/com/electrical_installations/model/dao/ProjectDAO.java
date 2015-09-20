/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.dao;

import com.electrical_installations.model.entity.Project;
import com.electrical_installations.model.entity.User;
import java.util.List;

/**
 * Intefaz para el acoplamiento de Proyecto
 * @author Ing. Richard David
 * @version 1
 * @since 2015-07-28
 */
public interface ProjectDAO {

    /**
     * Crear Proyecto
     * @param project
     * @return Retorna un valor booleano 
     */
    public boolean insert(Project project);

    /**
     * Modificar datos básicos de un Proyecto
     * @param project
     * @return Retorna un valor booleano
     */
    public boolean update(Project project);
    
    /**
     * Eliminar un Proyecto
     * @param project
     * @return Retorna un booleano
     */
    public boolean delete(Project project);

    /**
     * Encontrar un Proyecto
     * @param project
     * @return Retorna un objeto Project
     */
    public Project find(Project project);
    
    /**
     * Listar todos los Proyectos asociados a un usuario
     * @param user
     * @return Retorna una lista de proyectos
     */
    public List<Project> find_projects(User user);
    
    /**
     * Encontrar proyectos filtrados por nombre
     * @param project
     * @return Retorna una lista de proyectos filtradas por nombre
     */
    public List<Project> filter_by_name(Project project);

    /**
     * Encontrar proyectos filtrados por tipo
     * @param project
     * @return Retorna una lista de proyectos filtrados por tipo
     */
    public List<Project> filter_by_type(Project project);
        
    /**
     * Validar nombre de un proyecto para un usuario.
     * @param project
     * @return Retorna un objecto Project
     */
    public Project validate_project_name_for_a_user(Project project);

    /**
     * Validar existencia de un proyecto
     * @param project
     * @return Retorna un objeto Project
     */
    public Project validate_project(Project project);
    
}
