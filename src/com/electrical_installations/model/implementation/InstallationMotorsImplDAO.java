/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.implementation;

import com.electrical_installations.model.DataBaseConnection;
import com.electrical_installations.model.dao.InstallationMotorsDAO;
import com.electrical_installations.model.entity.InstallationMotors;
import com.electrical_installations.model.enums.TypePhase;
import com.electrical_installations.model.query.InstallationMotorsQueries;
import com.electrical_installations.model.query.ProjectQueries;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que encapsula la capa Modelo para acceder a los datos de motores en la instalación,
 * implementa la interfaz InstallationMotorsDAO
 *
 * @author Ing. Richard David
 * @version 1
 * @since 2015-10-31
 */
public class InstallationMotorsImplDAO implements InstallationMotorsDAO {

    //Objetos, variables y constantes de la clase.
    private static final DataBaseConnection connection = DataBaseConnection.getInstance();
    private static InstallationMotorsImplDAO instance;
    private PreparedStatement preparedStatement;
    private ResultSet result; 
    private List<InstallationMotors> installationMotorsFound;
    private InstallationMotors installationMotorsDescriptionFounds;

    /**
     * Constructor de la clase, es privado para cumplir con el patrón
     * Singlenton.
     */
    private InstallationMotorsImplDAO() {
    }//Fin del Constructor

    /**
     * Este método permite obtener la instancia de la clase.
     *
     * @return Retorna el objeto instance que es del mismo tipo que la clase.
     */
    public static synchronized InstallationMotorsImplDAO getInstance() {
        if (instance == null) {
            instance = new InstallationMotorsImplDAO();
        }
        return instance;
    }//Fin del método getConexion

    /**
     * Este método cierra la conexión con la base de datos, para que esto sea
     * posible se elimina la referencia en memoria para el objeto instance.
     */
    public void closeConnection() {
        instance = null;
    }//Fin del método
    
    /**
     *  Metodo para insertar motores en la instalación.
     * @param installation_motors
     * @return Retorna un valor booleano.
     */ 
    @Override
    public boolean insert(InstallationMotors installation_motors) {                
        try {
            preparedStatement = connection.getConexion().prepareStatement(InstallationMotorsQueries.INSERT);
            preparedStatement.setInt(1, installation_motors.getProject().getCode());
            preparedStatement.setInt(2, installation_motors.getProject().getTypeOfInstallation().getCode());
            preparedStatement.setString(3, installation_motors.getDescription());
            preparedStatement.setDouble(4, installation_motors.getIntensity());
            preparedStatement.setInt(5, installation_motors.getQuantity());
            preparedStatement.setString(6, installation_motors.getCaliberPhase());
            preparedStatement.setString(7, installation_motors.getCaliberNeutral());
            preparedStatement.setString(8, installation_motors.getCaliberHearth());
            preparedStatement.setInt(9, installation_motors.getTypePhase().getCode());
            preparedStatement.setString(10, installation_motors.getHorse_power());
            preparedStatement.setDouble(11, installation_motors.getBreaker());
            preparedStatement.setString(12, installation_motors.getPipeline());
            preparedStatement.setString(13, installation_motors.getMaterialPipeline());
            if (preparedStatement.executeUpdate() > 0) {
                preparedStatement.close();
                preparedStatement = connection.getConexion().prepareStatement(ProjectQueries.UPDATE_INTENSITY_TOTAL);
                preparedStatement.setDouble(1, installation_motors.getIntensity() * installation_motors.getQuantity());
                preparedStatement.setInt(2, installation_motors.getProject().getCode());
                preparedStatement.setInt(3, installation_motors.getProject().getTypeOfInstallation().getCode());
                if (preparedStatement.executeUpdate() > 0 ) return true;
            }            
        } catch (SQLException ex) {
            Logger.getLogger(InstallationMotorsImplDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.closeConnection();
        }     
        return false;
    }//Fin del Método

    /**
     * Metodo para buscar todos los motores de un área filtrada por el proyecto y el tipo de proyecto.
     * @param installationMotors
     * @return Retorna una lista de motores en la instalación.
     */        
    @Override
    public List<InstallationMotors> find_instalation_motors(InstallationMotors installationMotors) {
        installationMotorsFound = new ArrayList<>();
        try {
            preparedStatement = connection.getConexion().prepareStatement(InstallationMotorsQueries.SELECT_ALL);
            preparedStatement.setInt(1, installationMotors.getProject().getCode());
            preparedStatement.setInt(2, installationMotors.getProject().getTypeOfInstallation().getCode()); 
            result = preparedStatement.executeQuery();
            while(result.next()){
                installationMotorsFound.add(new InstallationMotors(
                        result.getInt(1),
                        null,
                        result.getString(2),
                        result.getDouble(3),
                        result.getInt(4),
                        null,
                        null,
                        null,
                        result.getInt(5) == TypePhase.SINGLE_PHASE.getCode() ? TypePhase.SINGLE_PHASE : TypePhase.THREE_PHASE,    
                        result.getString(6),
                        result.getDouble(7),
                        null,
                        null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return installationMotorsFound;
    }//Fin del Método

    @Override
    public List<InstallationMotors> find_instalation_motors_filter_name(InstallationMotors installationMotors) {
   
         installationMotorsFound = new ArrayList<>();
        try {
            preparedStatement = connection.getConexion().prepareStatement(InstallationMotorsQueries.SELECT_ALL_FILTER_NAME);
            preparedStatement.setString(1, "%"+installationMotors.getDescription()+"%");
            preparedStatement.setInt(2, installationMotors.getProject().getCode());
            preparedStatement.setInt(3, installationMotors.getProject().getTypeOfInstallation().getCode()); 
            result = preparedStatement.executeQuery();
            while(result.next()){
                installationMotorsFound.add(new InstallationMotors(
                        result.getInt(1),
                        null,
                        result.getString(2),
                        result.getDouble(3),
                        result.getInt(4),
                        null,
                        null,
                        null,
                        result.getInt(5) == TypePhase.SINGLE_PHASE.getCode() ? TypePhase.SINGLE_PHASE : TypePhase.THREE_PHASE,
                        result.getString(6),
                        result.getDouble(7),
                        null,
                        null));    
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return installationMotorsFound;
        
    }//Fin del meetodo
    
    /**
     * Método para eliminar un motor de una instalación.
     * @param installationMotors
     * @return Return true si el motor es eliminado.
     */
    @Override
    public boolean delete(InstallationMotors installationMotors) {
        try {
            preparedStatement = connection.getConexion().prepareStatement(InstallationMotorsQueries.DELETE_INSTALLATION_MOTOR);
            preparedStatement.setInt(1, installationMotors.getCode());
            if (preparedStatement.executeUpdate() > 0) {
                preparedStatement.close();
                preparedStatement = connection.getConexion().prepareStatement(ProjectQueries.UPDATE_INTENSITY_TOTAL_DELETE);
                preparedStatement.setDouble(1, installationMotors.getIntensity() * installationMotors.getQuantity());
                preparedStatement.setInt(2, installationMotors.getProject().getCode());
                preparedStatement.setInt(3, installationMotors.getProject().getTypeOfInstallation().getCode());
                if (preparedStatement.executeUpdate() > 0 ) return true;
            }  
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return false;
    }//Fin del Método

    /**
     * Método para validar que no exista la descripción de los motores en la instalación.
     * @param installationMotors
     * @return Retorna un Objeto de Motores.
     */
    @Override
    public InstallationMotors validate_description(InstallationMotors installationMotors) {
         installationMotorsDescriptionFounds = null;
        try {
            preparedStatement = connection.getConexion().prepareStatement(InstallationMotorsQueries.VALIDATE_DESCRIPTION);
            preparedStatement.setString(1, installationMotors.getDescription()); 
            preparedStatement.setInt(2, installationMotors.getProject().getCode());
            preparedStatement.setInt(3, installationMotors.getProject().getTypeOfInstallation().getCode());
            result = preparedStatement.executeQuery();
            while(result.next()) return installationMotorsDescriptionFounds = new InstallationMotors(result.getInt(1), null, 0, 0);
         } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return installationMotorsDescriptionFounds;
     }//fin del constructor.
    
}