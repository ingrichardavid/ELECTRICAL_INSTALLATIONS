/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.service;

import com.electrical_installations.configuration.Messages;
import com.electrical_installations.model.entity.Area;
import com.electrical_installations.model.entity.ChargesInAreas;
import com.electrical_installations.model.implementation.ChargesInAreasImplDAO;
import java.util.List;

/**
 * Clase que sirve la capa de servicios para Cargas en Áreas, los servicios se comunican con la capa Modelo para acceder a los datos.
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-02
 */
public class ServiceChargesInAreas {
    
    //Objetos, variables y constantes
    private static final ChargesInAreasImplDAO chargesInAreasImplDAO = ChargesInAreasImplDAO.getInstance();
    private static final Messages messages = Messages.getInstance();
    
    /**
     * Constructor de la clase
     */
    public ServiceChargesInAreas(){
    }//Fin del constructor
    
    /**
     * Servicio para la creación de cargas en áreas, recibe un objeto ChargesInAreas.
     * @param chargesInAreas 
     * @param area 
     * @return Retorna true si el proceso de registro a finalizado con exito 
     */
    public static boolean create_charge_in_area(ChargesInAreas chargesInAreas, Area area){
        return chargesInAreasImplDAO.insert_charge_in_area(chargesInAreas, area);  
    }//Fin del servicio
           
    /**
     * Servicio para consultar todas las cargas asociadas a un área.
     * @param chargesInAreas
     * @return Retorna una lista de cargas asociadas a un área
     */
    public static List<ChargesInAreas> find_charges_in_areas(ChargesInAreas chargesInAreas){
        return chargesInAreasImplDAO.find_charges_in_areas(chargesInAreas);
    }//Fin del Servicio
        
    /**
     * Servicio para consultar todas las cargas asociadas a un área filtradas por nombre.
     * @param chargesInAreas 
     * @return Retorna una lista de áreas filtrada por nombre
     */
    public static List<ChargesInAreas> filter_by_name(ChargesInAreas chargesInAreas){
        return chargesInAreasImplDAO.filter_by_name(chargesInAreas);
    }//Fin del servicio  
    
    /**
     * Servicio para la eliminar cargas de un área, recibe un objeto ChargesInAreas.
     * @param chargesInAreas 
     * @param area 
     * @return Retorna true si el proceso de registro a finalizado con exito 
     */
    public static boolean delete_charge_in_area(ChargesInAreas chargesInAreas, Area area){
        return chargesInAreasImplDAO.delete_charge_in_area(chargesInAreas,area);  
    }//Fin del servicio
        
    /**
     * Servicio para validar que una carga ya no haya sido asignada a un área.
     * @param chargesInAreas
     * @return Retorna true en caso de que la carga ya haya sido asignada. Caso contrario retorna false.
     */
    public static boolean validate_charge_in_area(ChargesInAreas chargesInAreas){
        return chargesInAreasImplDAO.validate_charge_in_area(chargesInAreas);
    }//FIn del Servicio
    
}
