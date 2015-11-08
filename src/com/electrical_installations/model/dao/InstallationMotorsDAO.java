/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.dao;
  
import com.electrical_installations.model.entity.InstallationMotors;
import java.util.List;



/**
 * Intefaz para el acoplamiento de la entidad motores en la instalación.
 * @author JRichard
 * @version 1 
 * @since 2015-10-31
 */

public interface InstallationMotorsDAO {

    /**
     * Insertar motores en la instalación.
     * @param installation_Motors
     * @return Retorna un valor booleano.
    */
    public boolean insert(InstallationMotors installation_Motors);
   
    
    /**
     * Listar todos los motores de una instalación que le pertenecen a un proyecto.
     * @param installationMotors
     * @return Una lista de motores de una instalación.
     */
    public List<InstallationMotors> find_instalation_motors(InstallationMotors installationMotors);
    
     /**
     * Listar todos los motores de una instalación que le pertenecen a un proyecto filtrado por nombre.
     * @param installationMotors
     * @return Una lista de motores de la instalación filtrado por nombre.
     */
    public List<InstallationMotors> find_instalation_motors_filter_name(InstallationMotors installationMotors);

    /**
     * Método para eliminar un motor de una instalación.
     * @param installationMotors
     * @return Return true si el motor es eliminado.
     */
    public boolean delete(InstallationMotors installationMotors);
    
    /**
     * Validar la si existe la descripción de los motores dentro de la instalación. 
     * @param installationMotors 
     * @return Retorna una objeto de motores de la instalacón.
     */
    public InstallationMotors validate_description(InstallationMotors installationMotors);
    
}
