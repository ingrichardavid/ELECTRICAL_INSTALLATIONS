/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.service;

import com.electrical_installations.configuration.Messages;
import com.electrical_installations.configuration.MessagesStructure;
import com.electrical_installations.global.method.Methods;
import com.electrical_installations.model.entity.Project;
import com.electrical_installations.model.entity.User;
import com.electrical_installations.model.implementation.ProjectImplDAO;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Clase que sirve la capa de servicios para Proyecto, los servicios se comunican con la capa Modelo para acceder a los datos.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-07-28
 */
public class ServiceProject {
    
    //Objetos, variables y constantes
    private static final ProjectImplDAO projectImplDAO = ProjectImplDAO.getInstance();  
    private static final Messages messages = Messages.getInstance();
    
    /**
     * Constructor de la clase
     */
    public ServiceProject(){
    }
    
    /**
     * Servicio para la creación de proyectos, recibe un objeto Project.
     * @param project 
     * @return Retorna true si el proceso de registro a finalizado con exito 
     */
    public static boolean create_project(Project project){
        if (MessagesStructure.ConfirmationMessage(MessagesStructure.format(200, messages.getProperty(Messages.CONFIRM), MessagesStructure.justify)) == 0){        
            if (projectImplDAO.validate_project_name_for_a_user(project) != null){
                MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.PROJECT_NAME_EXISTS), MessagesStructure.justify));
                return false;
            } else {
                if (projectImplDAO.insert(project)){
                    MessagesStructure.InformationMessage(MessagesStructure.format(200, messages.getProperty(Messages.PROJECT_REGISTERED), MessagesStructure.justify));
                    return true;
                } else {
                    MessagesStructure.ErrorMessage(MessagesStructure.format(200, messages.getProperty(Messages.OPERATION_ERROR), MessagesStructure.justify));
                    return false;
                }
            }
        }
        return false;
    }//Fin del servicio
    
    /**
     * Servicio para la modificación de proyectos, recibe un objeto Project.
     * @param project
     * @return Retorna true si el proceso de modificación a finalizado con exito
     */
    public static boolean update(Project project){
        if (projectImplDAO.update(project)){
            MessagesStructure.InformationMessage(MessagesStructure.format(200, messages.getProperty(Messages.PROJECT_MODIFIED), MessagesStructure.justify));
            return true;
        } else {
            MessagesStructure.ErrorMessage(MessagesStructure.format(200, messages.getProperty(Messages.OPERATION_ERROR), MessagesStructure.justify));
            return false;
        }
    }//Fin del servicio
        
    /**
     * Servicio para solicitar datos de todos los proyectos asociados a un Usuario.
     * @param user
     * @return Retorna una lista de Proyectos
     */
    public static List<Project> find_projects(User user){
        return projectImplDAO.find_projects(user);
    }//Fin del servicio//Fin del servicio
        
    /**
     * Servicio para solicitar datos de todos los proyectos asociados a un Usuario filtrados por nombre.
     * @param project
     * @return Retorna una lista de Proyectos
     */
    public static List<Project> filter_by_name(Project project){
        return projectImplDAO.filter_by_name(project);
    }//Fin del servicio
        
    /**
     * Servicio para solicitar datos de todos los proyectos asociados a un Usuario filtrados por tipo.
     * @param project
     * @return Retorna una lista de Proyectos
     */
    public static List<Project> filter_by_type(Project project){
        return projectImplDAO.filter_by_type(project);
    }//Fin del servicio
    
    /**
     * Servicio para solicitar datos de un proyecto, recibe un objeto Project
     * @param project
     * @return 
     */
    public static Project find_project(Project project){
        Project projectFound = projectImplDAO.find(project);
        if (projectFound == null){
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.PROJECT_NO_FOUND), MessagesStructure.justify));            
        } 
        return projectFound;
    }//Fin del servicio
    
    /**
     * Servicio para eliminar proyectos, recibe un objeto Project
     * @param project 
     * @return Retorna true en caso de que la operación sea exitosa 
     */   
    public static boolean delete_project(Project project){
        if (MessagesStructure.ConfirmationMessage(MessagesStructure.format(200, messages.getProperty(Messages.PROJECT_CONFIRM_DELETED), MessagesStructure.justify)) == 0){  
            if (projectImplDAO.delete(project)){
                MessagesStructure.InformationMessage(MessagesStructure.format(200, messages.getProperty(Messages.PROJECT_DELETED), MessagesStructure.justify));
                return true;
            } else {
                MessagesStructure.ErrorMessage(MessagesStructure.format(200, messages.getProperty(Messages.OPERATION_ERROR), MessagesStructure.justify));
                return false;
            }
        }
        return false;
    }//Fin del servicio
    
    /**
     * Servicio para validar nombre de un proyecto para un usuario.
     * @param project
     * @return Retorna un objecto Project
     */
    public static boolean validate_project_name_for_a_user(Project project){  
        Project projectFound = projectImplDAO.validate_project_name_for_a_user(project);
        if (projectFound != null && projectFound.getCode() != project.getCode()){
            MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.PROJECT_NAME_EXISTS), MessagesStructure.justify));
            return false;
        } 
        return true;
    }
    
    /**
     * Servicio para validar proyectos
     * @param project
     * @return Retorna un objeto Project.
     */
    public static Project validate_project(Project project){
        Project projectFound = projectImplDAO.validate_project(project);
        if (projectFound == null){
            return null;
        } 
        return projectFound;
    }//Fin del servicio
            
}
