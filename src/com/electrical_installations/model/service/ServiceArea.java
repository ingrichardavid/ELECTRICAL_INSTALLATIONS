/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.service;

import com.electrical_installations.configuration.Messages;
import com.electrical_installations.configuration.MessagesStructure;
import com.electrical_installations.model.entity.Area;
import com.electrical_installations.model.entity.AreaIluminariaPowerPoint;
import com.electrical_installations.model.entity.masters.Calibers;
import com.electrical_installations.model.implementation.AreaImplDAO;
import java.util.List;

/**
 * Clase que sirve la capa de servicios para Áreas, los servicios se comunican con la capa Modelo para acceder a los datos.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-02
 */
public class ServiceArea {
    
    //Objetos, variables y constantes
    private static final AreaImplDAO areaImplDAO = AreaImplDAO.getInstance();
    private static final Messages messages = Messages.getInstance();
    
    /**
     * Constructor de la clase
     */
    public ServiceArea(){
    }//Fin del constructor
    
    /**
     * Servicio para la creación de áreas, recibe un objeto Area.
     * @param area 
     * @param areaIluminariaPowerPoints 
     * @return Retorna true si el proceso de registro a finalizado con exito 
     */
    public static boolean create_area(Area area, List<AreaIluminariaPowerPoint> areaIluminariaPowerPoints){
        if (MessagesStructure.ConfirmationMessage(MessagesStructure.format(200, messages.getProperty(Messages.CONFIRM), MessagesStructure.justify)) == 0){        
            if (areaImplDAO.validate_name(area) != null){
                MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.AREA_FOUND), MessagesStructure.justify));
                return false;
            } else {
                if (areaImplDAO.insert(area,areaIluminariaPowerPoints)){
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
     * Servio para la modificación de áreas, recibe un objeto Area.
     * @param area
     * @param areaIluminariaPowerPoints
     * @return Retorna true si el proceso de modificación a finalizado con exito
     */
    public static boolean update(Area area, List<AreaIluminariaPowerPoint> areaIluminariaPowerPoints){
        Area areaFound = areaImplDAO.validate_name(area);
        if (MessagesStructure.ConfirmationMessage(MessagesStructure.format(200, messages.getProperty(Messages.CONFIRM), MessagesStructure.justify)) == 0){  
            if (areaImplDAO.validate_existence(area) == 0){
                MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.AREA_NO_FOUND), MessagesStructure.justify));
                return false;            
            } else {
                if (areaImplDAO.validate_name(area) != null && areaFound.getCode() != area.getCode()){
                    MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.AREA_NAME_EXISTS), MessagesStructure.justify));
                    return false;
                } else {
                    if (areaImplDAO.update(area, areaIluminariaPowerPoints)){
                        return true;
                    } else {
                        MessagesStructure.ErrorMessage(MessagesStructure.format(200, messages.getProperty(Messages.OPERATION_ERROR), MessagesStructure.justify));
                        return false;
                    }
                }
            }            
        }
        return false;
    }//Fin del servicio
        
    /**
     * Método para buscar datos de un àrea específica.
     * @param area
     * @return Retorna la àrea encontrada en caso contrario retorna null.
     */
    public static Area find_area(Area area){
        return areaImplDAO.find(area);
    }//Fin del Mètodo
    
    /**
     * Servicio para solicitar datos de todas las areas.
     * @param area
     * @return Retorna una lista de Áreas
     */
    public static List<Area> find_areas(Area area){
        return areaImplDAO.find_areas(area);
    }//Fin del servicio
        
    /**
     * Servicio para consultar datos de áreas filtrados por nombre, recibe un objeto Area
     * @param area 
     * @return Retorna una lista de áreas filtrada por nombre
     */
    public static List<Area> filter_by_name(Area area){
        return areaImplDAO.filter_by_name(area);
    }//Fin del servicio   
        
    /**
     * Servicio para eliminar áreas, recibe un objeto Area
     * @param area 
     * @return Retorna true en caso de que la operación sea exitosa 
     */   
    public static boolean delete_area(Area area){
        if (MessagesStructure.ConfirmationMessage(MessagesStructure.format(200, messages.getProperty(Messages.AREA_CONFIRM_DELETED), MessagesStructure.justify)) == 0){  
            if (areaImplDAO.validate_existence(area) == 0){
                MessagesStructure.Warning(MessagesStructure.format(200, messages.getProperty(Messages.AREA_NO_FOUND), MessagesStructure.justify));
                return false;
            } else {
                if (areaImplDAO.delete(area)){
                    return true;
                } 
            }
        }
        return false;
    }//Fin del servicio
            
    /**
     * Servicio para encontrar calibres.
     * @param calibers
     * @param roominess
     * @return Retorna un calibre
     */
    public static Calibers find_caliber_iluminaria_power_point(Calibers calibers,double roominess){
        return areaImplDAO.find_caliber_iluminaria_power_point(calibers, roominess);
    }//Fin del servicio
    
    /**
     * Servicio para encontrar la Iluminaria y Toma corriente de un Área.
     * @param area
     * @return Retorna un arreglo con la Iluminaria y Toma Corriente.
     */
    public static List<AreaIluminariaPowerPoint> find_iluminaria_powerPoint(Area area){     
        return areaImplDAO.find_iluminaria_powerPoint(area);
    }//Fin del serivicio.
    
}
