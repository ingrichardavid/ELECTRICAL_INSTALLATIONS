/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.implementation;

import com.electrical_installations.model.DataBaseConnection;
import com.electrical_installations.model.dao.ProjectDAO;
import com.electrical_installations.model.entity.Project;
import com.electrical_installations.model.entity.TypeOfInstallation;
import com.electrical_installations.model.entity.User;
import com.electrical_installations.model.query.ProjectQueries;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que encapsula la capa Modelo para acceder a los datos del proyecto, implementa la interfaz ProjectDAO
 * @author Ing. Richard David
 * @version 1
 * @since 2015-07-28
 */
public class ProjectImplDAO implements ProjectDAO{
    
    //Objetos, variables y constantes de la clase.
    private static final DataBaseConnection connection = DataBaseConnection.getInstance();    
    private static ProjectImplDAO instance;
    private PreparedStatement preparedStatement;
    private ResultSet result;
    private Project projectFound;
    private List<Project> projectsFound;

    /**
     * Constructor de la clase, es privado para cumplir con el patrón Singlenton.
     */
    private ProjectImplDAO() {
    }//Fin del Constructor
    
    /**
     * Este método permite obtener la instancia de la clase.
     * @return Retorna el objeto instance que es del mismo tipo que la clase.
     */
    public static synchronized ProjectImplDAO getInstance() {
        if (instance == null) {
            instance = new ProjectImplDAO();
        }
        return instance;
    }//Fin del método getConexion

    /**
     * Este método cierra la conexión con la base de datos, para que esto sea posible
     * se elimina la referencia en memoria para el objeto instance.
     */
    public void closeConnection() {
        instance = null;
    }//Fin del método

    /**
     * Método para insertar un Proyecto
     * @param project
     * @return Retorna true en caso de que el procedimiento sea exitoso 
     */
    @Override
    public boolean insert(Project project) {
        try {
            preparedStatement = connection.getConexion().prepareStatement(ProjectQueries.INSERT);
            preparedStatement.setString(1, project.getUser().getNationality());
            preparedStatement.setInt(2, project.getUser().getDni());
            preparedStatement.setInt(3, project.getTypeOfInstallation().getCode());
            preparedStatement.setString(4, project.getName());
            preparedStatement.setInt(5, project.getPowerTotal()); 
            if (preparedStatement.executeUpdate() > 0) return true;   
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return false;
    }//Fin del método insert

    /**
     * Método para modificar un Proyecto
     * @param project
     * @return Retorna true en caso de que el procedimiento sea exitoso
     */
    @Override
    public boolean update(Project project) {
        try {
            preparedStatement = connection.getConexion().prepareStatement(ProjectQueries.UPDATE);
            preparedStatement.setInt(1, project.getTypeOfInstallation().getCode());
            preparedStatement.setString(2, project.getName());
            preparedStatement.setInt(3, project.getPowerTotal());           
            preparedStatement.setInt(4, project.getCode());
            if (preparedStatement.executeUpdate() > 0) return true;   
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return false;
    }//Fin del método

    /**
     * Método para eliminar un Proyecto
     * @param project
     * @return Retorna true en caso de que el procedimiento sea exitoso
     */
    @Override
    public boolean delete(Project project) {
        try {
            preparedStatement = connection.getConexion().prepareStatement(ProjectQueries.DELETE);
            preparedStatement.setInt(1, project.getCode());
            if (preparedStatement.executeUpdate() > 0) return true;   
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return false;
    }//Fin del Método

    /**
     * Método para encontrar un Proyecto
     * @param project
     * @return Retorna un objeto Project
     */
    @Override
    public Project find(Project project) { 
        projectFound = null;
        try {
            preparedStatement = connection.getConexion().prepareStatement(ProjectQueries.SELECT);
            preparedStatement.setString(1, project.getName());
            result = preparedStatement.executeQuery();
            while(result.next()){
                projectFound = new Project(result.getInt(1), null, new TypeOfInstallation(result.getInt(2), result.getString(3)), result.getString(4), result.getInt(5), result.getString(6),null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return projectFound;
    }//Fin del Método  
        
    /**
     * Método para encontrar todos los Proyectos asociados a un Usuario
     * @return Retorna una lista de proyectos
     */
    @Override
    public List<Project> find_projects(User user) {
        projectsFound = new ArrayList<>();
        try {
            preparedStatement = connection.getConexion().prepareStatement(ProjectQueries.SELECT_ALL);
            preparedStatement.setString(1, user.getNationality());
            preparedStatement.setInt(2, user.getDni());
            result = preparedStatement.executeQuery();
            while(result.next()){
                projectsFound.add(new Project(result.getInt(1), null, new TypeOfInstallation(result.getInt(2), result.getString(3)), result.getString(4), result.getInt(5), result.getString(6),null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return projectsFound;
    }//Fin del método  
        
    /**
     * Método para encontrar todos los Proyectos filtrados por nombre asociados a un Usuario
     * @return Retorna una lista de proyectos
     */
    @Override
    public List<Project> filter_by_name(Project project) {
        projectsFound = new ArrayList<>();
        try {
            preparedStatement = connection.getConexion().prepareStatement(ProjectQueries.FILTER_BY_NAME);
            preparedStatement.setString(1, project.getUser().getNationality());
            preparedStatement.setInt(2, project.getUser().getDni());
            preparedStatement.setString(3, "%"+ project.getName() +"%");
            result = preparedStatement.executeQuery();
            while(result.next()){
                projectsFound.add(new Project(result.getInt(1), null, new TypeOfInstallation(result.getInt(2), result.getString(3)), result.getString(4), result.getInt(5), result.getString(6),null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return projectsFound;
    }//Fin del método
        
    /**
     * Método para encontrar todos los Proyectos filtrados por tipo asociados a un Usuario
     * @return Retorna una lista de proyectos
     */
    @Override
    public List<Project> filter_by_type(Project project) {
        projectsFound = new ArrayList<>();
        try {
            preparedStatement = connection.getConexion().prepareStatement(ProjectQueries.FILTER_BY_TYPE);
            preparedStatement.setString(1, project.getUser().getNationality());
            preparedStatement.setInt(2, project.getUser().getDni());
            preparedStatement.setString(3, "%"+ project.getTypeOfInstallation().getName() +"%");
            result = preparedStatement.executeQuery();
            while(result.next()){
                projectsFound.add(new Project(result.getInt(1), null, new TypeOfInstallation(result.getInt(2), result.getString(3)), result.getString(4), result.getInt(5), result.getString(6),null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return projectsFound;
    }//Fin del método
    
    /**
     * Método para validar nombre de un proyecto para un usuario.
     * @param project
     * @return 1 en caso de que el procedimiento sea exitoso, caso contrario retorna 0
     */
    @Override
    public Project validate_project_name_for_a_user(Project project) {
        projectFound = null;
        try {
            preparedStatement = connection.getConexion().prepareStatement(ProjectQueries.VALIDATE_NAME_PROJECT);
            preparedStatement.setString(1, project.getName());
            preparedStatement.setString(2, project.getUser().getNationality());
            preparedStatement.setInt(3, project.getUser().getDni());
            result = preparedStatement.executeQuery();
            while(result.next()) projectFound = new Project(result.getInt(1),result.getString(2));            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return projectFound;
    }//Fin del Método  
        
    /**
     * Método para validar existencia de un proyecto.
     * @param project
     * @return Retorna un objecto Project
     */
    @Override
    public Project validate_project(Project project) {
        projectFound = null;
        try {
            preparedStatement = connection.getConexion().prepareStatement(ProjectQueries.VALIDATE_PROJECT);
            preparedStatement.setInt(1, project.getCode());
            result = preparedStatement.executeQuery();
            while(result.next()){
                projectFound = new Project(result.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return projectFound;
    }//Fin del Método  

    @Override
    public boolean update_project_phase_earth_motor(Project project) {
        try {
            connection.getConexion().setAutoCommit(false);
            preparedStatement = connection.getConexion().prepareStatement(ProjectQueries.UPDATE_PROJECT_PHASE_EARTH_MOTOR);
            System.out.println(project.getPhase_motor());
            System.out.println(project.getEarth_motor());
            System.out.println(project.getCode());
            System.out.println(project.getTypeOfInstallation().getCode());
            System.out.println(project.getPipeline_motor());
            preparedStatement.setString(1, project.getPhase_motor());
            preparedStatement.setString(2, project.getEarth_motor());  
            preparedStatement.setString(3, project.getPipeline_motor());           
            preparedStatement.setInt(4, project.getCode());
            preparedStatement.setInt(5, project.getTypeOfInstallation().getCode()); 
//            if (preparedStatement.executeUpdate() > 0){ 
//                preparedStatement.close();
//                preparedStatement = connection.getConexion().prepareStatement(ChargesInAreasQueries.UPDATE_MAIN_FEEDER_TYPE_CHARGE);
//                preparedStatement.setDouble(1, 0);
//                preparedStatement.setInt(2, 0); 
//                preparedStatement.setDouble(3, project.getIntensity_total());
//                preparedStatement.setInt(4, project.getCode());
//                preparedStatement.setInt(5, project.getTypeOfInstallation().getCode());
//                preparedStatement.setInt(6, 7);
                if (preparedStatement.executeUpdate() > 0){ 
                    connection.getConexion().commit();
                    return true;
                }else { 
                    connection.getConexion().rollback();
                    return false;
                }
            //}
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.getConexion().setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(ProjectImplDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connection.closeConnection();
        }
        return false;
    }//fin del metodo
    
}
