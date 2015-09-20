/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.dao;

import com.electrical_installations.model.entity.ElevatorInInstallation;
import java.util.List;
/**
 * Intefaz para el acoplamiento de la Entidad Elevadores en Instalación.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-26
 */
public interface ElevatorsInInstallationDAO {
    
    /**
     * Insertar elevador en instalación.
     * @param elevatorInInstallation
     * @return Retorna true en caso de que los datos se hayan insertado con éxito.
     */
    public boolean insert_elevator_in_installation(ElevatorInInstallation elevatorInInstallation);
    
    /**
     * Modificar cantidad de elevadores en una instalación.
     * @param elevatorInInstallation
     * @return Retorna true en caso de que los datos se hayan insertado con éxito.
     */
    public boolean modify_elevator_in_installation(ElevatorInInstallation elevatorInInstallation);
    
    /**
     * Eliminar elevador de una instalación.
     * @param elevatorInInstallation
     * @return Retorna true en caso de que los datos se hayan insertado con éxito.
     */
    public boolean delete_elevator_in_installation(ElevatorInInstallation elevatorInInstallation);
            
    /**
     * Consultar todas los elevadores asociados a una instalación.
     * @param elevatorInInstallation
     * @return Retorna una lista de elevadores
     */
    public List<ElevatorInInstallation> find_elevators_in_installation(ElevatorInInstallation elevatorInInstallation);
        
    /**
     * Listar todas los elevadores asignados a una instalación filtrados por nombre.
     * @param elevatorInInstallation 
     * @return Retorna una lista de Cargas
     */
    public List<ElevatorInInstallation> filter_by_name(ElevatorInInstallation elevatorInInstallation);
    
}
