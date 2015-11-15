/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.implementation;

import com.electrical_installations.controller.MethodsForCalculationsIluminariaPowerPoint;
import com.electrical_installations.model.DataBaseConnection;
import com.electrical_installations.model.dao.AreaDAO;
import com.electrical_installations.model.entity.Area;
import com.electrical_installations.model.entity.AreaIluminariaPowerPoint;
import com.electrical_installations.model.entity.masters.Breaker;
import com.electrical_installations.model.entity.masters.Caliber;
import com.electrical_installations.model.entity.masters.Calibers;
import com.electrical_installations.model.entity.masters.Duct;
import com.electrical_installations.model.entity.masters.Intensity;
import com.electrical_installations.model.entity.masters.Material;
import com.electrical_installations.model.entity.masters.Phase;
import com.electrical_installations.model.entity.masters.Temperature;
import com.electrical_installations.model.entity.masters.Unit;
import com.electrical_installations.model.entity.masters.Voltage;
import com.electrical_installations.model.enums.TypeOfBranchCircuitInArea;
import com.electrical_installations.model.enums.TypeRush;
import com.electrical_installations.model.enums.TypeTypeOfCharges;
import com.electrical_installations.model.query.AreaQueries;
import com.electrical_installations.model.query.CaliberQueries;
import com.electrical_installations.model.query.MainFeederQueries;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que encapsula la capa Modelo para acceder a los datos de Áreas, implementa la interfaz AreaDAO
 * @author Ing. Richard David
 * @version 1
 * @since 2015-08-02
 */
public class AreaImplDAO implements AreaDAO{
    
    //Objetos, variables y constantes de la clase.
    private static final DataBaseConnection connection = DataBaseConnection.getInstance();  
    private static AreaImplDAO instance;
    private PreparedStatement preparedStatement;
    private ResultSet result;
    private Area areaFound;
    private Calibers calibersFound;
    private List<Area> areasFound;
    private List<AreaIluminariaPowerPoint> areaIluminariaPowerPoints;

    /**
     * Constructor de la clase, es privado para cumplir con el patrón Singlenton.
     */
    private AreaImplDAO() {
    }//Fin del Constructor
    
    /**
     * Este método permite obtener la instancia de la clase.
     * @return Retorna el objeto instance que es del mismo tipo que la clase.
     */
    public static synchronized AreaImplDAO getInstance() {
        if (instance == null) {
            instance = new AreaImplDAO();
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
     * Método para insertar un Área
     * @param area
     * @param areaIluminariaPowerPoints
     * @return Retorna true en caso de que el procedimiento sea exitoso 
     */
    @Override
    public boolean insert(Area area, List<AreaIluminariaPowerPoint> areaIluminariaPowerPoints) {
        boolean status = false;
        try {
            connection.getConexion().setAutoCommit(false);            
            preparedStatement = connection.getConexion().prepareStatement(AreaQueries.INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, area.getProject().getCode());
            preparedStatement.setInt(2, area.getProject().getTypeOfInstallation().getCode());
            preparedStatement.setString(3, area.getName());
            preparedStatement.setDouble(4, area.getPotency_total());
            preparedStatement.setDouble(5, area.getNeutral());
            preparedStatement.setInt(6, area.getQuantity());
            if (preparedStatement.executeUpdate() > 0){                
                result = preparedStatement.getGeneratedKeys();
                while (result.next()) {
                    area.setCode(result.getInt(1));
                }
                preparedStatement.close();
                preparedStatement = connection.getConexion().prepareStatement(AreaQueries.INSERT_ILUMINARIA_POWER_POINT);
                for(AreaIluminariaPowerPoint areaIluminariaPowerPoint : areaIluminariaPowerPoints){
                    preparedStatement.setInt(1, area.getCode());
                    preparedStatement.setInt(2, areaIluminariaPowerPoint.getCalibers().getCode());
                    preparedStatement.setInt(3, areaIluminariaPowerPoint.getVoltage().getCode());
                    preparedStatement.setInt(4, areaIluminariaPowerPoint.getPhase().getCode());
                    preparedStatement.setString(5, areaIluminariaPowerPoint.getTypeOfBranchCircuitInArea().getType());
                    preparedStatement.setDouble(6, areaIluminariaPowerPoint.getAreaOrQuantity());
                    preparedStatement.setInt(7, areaIluminariaPowerPoint.getConstant());
                    preparedStatement.setDouble(8, areaIluminariaPowerPoint.getPowerFactor());
                    preparedStatement.setInt(9, areaIluminariaPowerPoint.getCaliber().getCode());
                    preparedStatement.setInt(10, areaIluminariaPowerPoint.getBreaker().getCode());
                    preparedStatement.setString(11, areaIluminariaPowerPoint.getTypeRush().getRush());
                    preparedStatement.setDouble(12, areaIluminariaPowerPoint.getLength());
                    preparedStatement.setInt(13, areaIluminariaPowerPoint.getDuct().getCode());
                    preparedStatement.setDouble(14, areaIluminariaPowerPoint.getAngle());
                    preparedStatement.setString(15, areaIluminariaPowerPoint.getCaliberUse());
                    preparedStatement.setInt(16, areaIluminariaPowerPoint.getBranchCircuit());
                    preparedStatement.setString(17, areaIluminariaPowerPoint.getPipeline());
                    preparedStatement.setString(18, areaIluminariaPowerPoint.getMaterialPipeline());
                    if (preparedStatement.executeUpdate() > 0){ 
                        status = true;
                    } else {
                        status = false;
                        break;
                    }
                }
                if (status){                    
                    preparedStatement.close();
                    preparedStatement = connection.getConexion().prepareStatement(MainFeederQueries.VALIDATE_MAIN_FEEDER_EXIST);
                    preparedStatement.setInt(1, area.getProject().getCode());
                    preparedStatement.setInt(2, area.getProject().getTypeOfInstallation().getCode());
                    preparedStatement.setInt(3, 12);
                    result = preparedStatement.executeQuery();
                    int code_project = 0;
                    while(result.next()) {
                        code_project = result.getInt(1);                   
                    }
                    if (code_project > 0){ 
                        preparedStatement = connection.getConexion().prepareStatement(MainFeederQueries.UPDATE_MAIN_FEEDER_TYPE_CHARGE);
                        preparedStatement.setDouble(1, area.getPotency_total() * area.getQuantity());
                        preparedStatement.setInt(2, 0);
                        preparedStatement.setDouble(3, 0); 
                        preparedStatement.setInt(4, area.getProject().getCode());
                        preparedStatement.setInt(5, area.getProject().getTypeOfInstallation().getCode());
                        preparedStatement.setInt(6, 12);
                        if (preparedStatement.executeUpdate() > 0){
                            connection.getConexion().commit();
                            return true;                     
                        } else {  
                            connection.getConexion().rollback();
                            return false;       
                        }                        
                    } else {                    
                        preparedStatement = connection.getConexion().prepareStatement(MainFeederQueries.INSERT_MAIN_FEEDER_TYPE_CHARGE);
                        preparedStatement.setInt(1, area.getProject().getCode());
                        preparedStatement.setInt(2, area.getProject().getTypeOfInstallation().getCode());
                        preparedStatement.setInt(3, 12);
                        preparedStatement.setDouble(4, area.getPotency_total() * area.getQuantity());
                        preparedStatement.setInt(5, 0);
                        preparedStatement.setDouble(6, 0); 
                        if (preparedStatement.executeUpdate() > 0){
                            connection.getConexion().commit();
                            return true;                     
                        } else {  
                            connection.getConexion().rollback();
                            return false;       
                        }                         
                    }                  
                } else {
                    connection.getConexion().rollback();
                    return false;                       
                }
            } else {
                connection.getConexion().rollback();
                return false;
            }            
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.getConexion().rollback();
            } catch (SQLException ex) {
                Logger.getLogger(AreaImplDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        } finally {
            try {
                connection.getConexion().setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AreaImplDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connection.closeConnection();
        }
    }//Fin del método insert

    /**
     * Método para modificar un Área
     * @param area
     * @return Retorna true en caso de que el procedimiento sea exitoso
     */
    @Override
    public boolean update(Area area, List<AreaIluminariaPowerPoint> areaIluminariaPowerPoints) {
        boolean status = false;
        try {
            connection.getConexion().setAutoCommit(false);            
            preparedStatement = connection.getConexion().prepareStatement(AreaQueries.UPDATE);
            preparedStatement.setString(1, area.getName());
            preparedStatement.setDouble(2, area.getPotency_total());
            preparedStatement.setDouble(3, area.getNeutral());
            preparedStatement.setInt(4, area.getCode());
            if (preparedStatement.executeUpdate() > 0){     
                preparedStatement.close();
                preparedStatement = connection.getConexion().prepareStatement(AreaQueries.UPDATE_ILUMINARIA_POWER_POINT);
                for(AreaIluminariaPowerPoint areaIluminariaPowerPoint : areaIluminariaPowerPoints){
                    preparedStatement.setInt(1, areaIluminariaPowerPoint.getCalibers().getCode());
                    preparedStatement.setInt(2, areaIluminariaPowerPoint.getVoltage().getCode());
                    preparedStatement.setInt(3, areaIluminariaPowerPoint.getPhase().getCode());
                    preparedStatement.setString(4, areaIluminariaPowerPoint.getTypeOfBranchCircuitInArea().getType());
                    preparedStatement.setDouble(5, areaIluminariaPowerPoint.getAreaOrQuantity());
                    preparedStatement.setInt(6, areaIluminariaPowerPoint.getConstant());
                    preparedStatement.setDouble(7, areaIluminariaPowerPoint.getPowerFactor());
                    preparedStatement.setInt(8, areaIluminariaPowerPoint.getCaliber().getCode());
                    preparedStatement.setInt(9, areaIluminariaPowerPoint.getBreaker().getCode());
                    preparedStatement.setString(10, areaIluminariaPowerPoint.getTypeRush().getRush());
                    preparedStatement.setDouble(11, areaIluminariaPowerPoint.getLength());
                    preparedStatement.setInt(12, areaIluminariaPowerPoint.getDuct().getCode());
                    preparedStatement.setDouble(13, areaIluminariaPowerPoint.getAngle());
                    preparedStatement.setString(14, areaIluminariaPowerPoint.getCaliberUse());
                    preparedStatement.setInt(15, areaIluminariaPowerPoint.getBranchCircuit());
                    preparedStatement.setString(16, areaIluminariaPowerPoint.getPipeline());
                    preparedStatement.setString(17, areaIluminariaPowerPoint.getMaterialPipeline());
                    preparedStatement.setInt(18, areaIluminariaPowerPoint.getCode());
                    if (preparedStatement.executeUpdate() > 0){ 
                        status = true;
                    } else {
                        status = false;
                        break;
                    }
                }
                if (status){
                    System.out.println(area.getPotency_iluminaria_powerPoint_old());
                    System.out.println(area.getPotency_iluminaria_powerPoint_new());
                    preparedStatement.close();
                    preparedStatement = connection.getConexion().prepareStatement(MainFeederQueries.UPDATE_MAIN_FEEDER_TYPE_CHARGE_ILUMIARIA_POWER_POINT);
                    preparedStatement.setDouble(1, area.getPotency_iluminaria_powerPoint_old());
                    preparedStatement.setDouble(2, area.getPotency_iluminaria_powerPoint_new());
                    preparedStatement.setInt(3, 0);
                    preparedStatement.setDouble(4, 0); 
                    preparedStatement.setInt(5, area.getProject().getCode());
                    preparedStatement.setInt(6, area.getProject().getTypeOfInstallation().getCode());
                    preparedStatement.setInt(7, 12);
                    if (preparedStatement.executeUpdate() > 0){
                        connection.getConexion().commit();
                        return true;                     
                    } else {  
                        connection.getConexion().rollback();
                        return false;       
                    }      
                } else {
                    connection.getConexion().rollback();
                    return false;                       
                }
            } else {
                connection.getConexion().rollback();
                return false;
            }            
        } catch (SQLException e) {
            try {
                connection.getConexion().rollback();
            } catch (SQLException ex) {
                Logger.getLogger(AreaImplDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        } finally {
            try {
                connection.getConexion().setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AreaImplDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connection.closeConnection();
        }
    }//Fin del método
    
    /**
     * Método para eliminar un Área
     * @param area
     * @param dataBaseConnection
     * @return Retorna true en caso de que el procedimiento sea exitoso.
     */
    @Override
    public boolean delete(Area area, DataBaseConnection dataBaseConnection) {
        try {
            connection.getConexion().setAutoCommit(false);
            preparedStatement = dataBaseConnection.getConexion().prepareStatement(AreaQueries.DELETE);
            preparedStatement.setInt(1, area.getCode());
            return preparedStatement.executeUpdate() > 0;   
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return false;
    }//Fin del Método

    /**
     * Método para modificar o eliminar la potencia en iluminaria y toma corriente del sub-alimentador principal.
     * @param area
     * @return Retorna true si el proceso de modificación o eliminación se llevó a cabo.
     */
    @Override
    public boolean delete_main_feeder(Area area) {    
        double potency_iluminaria_power_point = this.consult_total_iluminaria_power_point(area);
        boolean status = false;
        try {
            connection.getConexion().setAutoCommit(false);
            preparedStatement = connection.getConexion().prepareStatement(MainFeederQueries.DELETE_CHARGE_UPDATE_MAIN_FEEDER_TYPE_CHARGE);
            preparedStatement.setDouble(1, potency_iluminaria_power_point);
            preparedStatement.setInt(2, 0);
            preparedStatement.setDouble(3, 0);             
            preparedStatement.setInt(4, area.getProject().getCode());
            preparedStatement.setInt(5, area.getProject().getTypeOfInstallation().getCode());
            preparedStatement.setInt(6, 12);  
            if (preparedStatement.executeUpdate() > 0){
                preparedStatement.close();
                preparedStatement = connection.getConexion().prepareStatement(MainFeederQueries.VALIDATE_MAIN_FEEDER_I_P_C);
                preparedStatement.setInt(1, area.getProject().getCode());
                preparedStatement.setInt(2, area.getProject().getTypeOfInstallation().getCode());
                preparedStatement.setInt(3, 12); 
                result = preparedStatement.executeQuery();
                int code_project = 0;
                while (result.next()){
                    code_project = result.getInt(1);
                }
                if (code_project > 0){
                    preparedStatement.close();  
                    preparedStatement = connection.getConexion().prepareStatement(MainFeederQueries.DELETE_CHARGE_MAIN_FEEDER);
                    preparedStatement.setInt(1, area.getProject().getCode());
                    preparedStatement.setInt(2, area.getProject().getTypeOfInstallation().getCode());
                    preparedStatement.setInt(3, 12);
                    status = preparedStatement.executeUpdate() > 0;
                } else {
                    status = true;
                }
                if (status){
                    if (delete(area, connection)) {
                        connection.getConexion().commit();
                        return true;
                    } else {
                        connection.getConexion().rollback();
                        return false;
                    }
                } else {
                    connection.getConexion().rollback();
                    return false;
                }
            } else {
                connection.getConexion().rollback();
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.getConexion().setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AreaImplDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connection.closeConnection();
        }
        return status;
    }
    
    /**
     * Método para encontrar un Área
     * @param area
     * @return Retorna un objeto Area
     */
    @Override
    public Area find(Area area) { 
        areaFound = null;
        try {
            preparedStatement = connection.getConexion().prepareStatement(AreaQueries.SELECT);
            preparedStatement.setInt(1, area.getCode());
            result = preparedStatement.executeQuery();
            while(result.next()){
//                areaFound = new Area(
//                        result.getInt(1), 
//                        result.getString(2),
//                        result.getDouble(3),
//                        result.getInt(4),
//                        result.getInt(5),
//                        result.getInt(6),
//                        result.getInt(7),
//                        result.getInt(8),
//                        result.getString(9),
//                        result.getString(10),
//                        result.getString(11),
//                        result.getString(12),
//                        result.getString(13),
//                        result.getString(14),
//                        null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return areaFound;
    }//Fin del Método

    /**
     * Método para filtrar áreas por nombres
     * @param area
     * @return Retorna un arreglo de areas
     */
    @Override
    public List<Area> filter_by_name(Area area) { 
        areasFound = new ArrayList<>();
        try {
            preparedStatement = connection.getConexion().prepareStatement(AreaQueries.FILTER_BY_NAME);
            preparedStatement.setString(1, "%"+area.getName()+"%");
            preparedStatement.setInt(2, area.getProject().getCode());
            preparedStatement.setInt(3, area.getProject().getTypeOfInstallation().getCode());
            result = preparedStatement.executeQuery();
            while(result.next()){
                areasFound.add(new Area(
                        result.getInt(1),
                        result.getString(2),
                        null,
                        result.getDouble(3), 
                        result.getDouble(4), 
                        result.getInt(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return areasFound;
    }//Fin del Método

    /**
     * Método para validar nombre de usuario
     * @param area
     * @return Retorna una objeto Area
     */
    @Override
    public Area validate_name(Area area) {
        areaFound = null;
        try {
            preparedStatement = connection.getConexion().prepareStatement(AreaQueries.VALIDATE_NAME);
            preparedStatement.setString(1, area.getName());
            preparedStatement.setInt(2, area.getProject().getCode());
            preparedStatement.setInt(3, area.getProject().getTypeOfInstallation().getCode());
            result = preparedStatement.executeQuery();
            while(result.next()) return areaFound = new Area(result.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return areaFound;
    }//Fin del Método   
    
    /**
     * Método para encontrar todos las Áreas
     * @return Retorna una lista de áreas
     */
    @Override
    public List<Area> find_areas(Area area) {
        areasFound = new ArrayList<>();
        try {
            preparedStatement = connection.getConexion().prepareStatement(AreaQueries.SELECT_ALL);
            preparedStatement.setInt(1, area.getProject().getCode());
            preparedStatement.setInt(2, area.getProject().getTypeOfInstallation().getCode());
            result = preparedStatement.executeQuery();
            while(result.next()){
                areasFound.add(new Area(
                        result.getInt(1),
                        result.getString(2),
                        null,
                        result.getDouble(3), 
                        result.getDouble(4), 
                        result.getInt(5)));
                    
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return areasFound;
    }//Fin del método 
    
    /**
     * Método para validar la existencia de un área
     * @param area
     * @return 1 en caso de que el procedimiento sea exitoso, caso contrario retorna 0
     */
    @Override
    public int validate_existence(Area area) {
        try {
            preparedStatement = connection.getConexion().prepareStatement(AreaQueries.VALIDATE_EXISTENCE);
            preparedStatement.setInt(1, area.getCode());
            result = preparedStatement.executeQuery();
            while(result.next()) return result.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return 0;
    }//Fin del Método   
    
    /**
     * Método para encontrar un calibre para iluminaria o toma corrientes.
     * @param calibers
     * @param roominess
     * @return Retorna el calibre deseado.
     */
    @Override
    public Calibers find_caliber_iluminaria_power_point(Calibers calibers,double roominess){
        calibersFound = null;
        try {
            preparedStatement = connection.getConexion().prepareStatement(CaliberQueries.FIND_CALIBER);
            preparedStatement.setInt(1, calibers.getMaterial().getCode());
            preparedStatement.setInt(2, calibers.getTemperature().getCode());
            preparedStatement.setDouble(3, calibers.getIntensity().getIntensity());
            preparedStatement.setDouble(4, roominess);
            result = preparedStatement.executeQuery();
            while(result.next()) calibersFound = new Calibers(result.getInt(1),new Caliber(result.getInt(2), result.getString(3)));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return calibersFound;        
    }//Fin del método
    
    /**
     * Método para encontrar la Iluminaria y Toma corriente de un Área.
     * @param area
     * @return Retorna un arreglo con la Iluminaria y Toma Corriente.
     */
    @Override
    public List<AreaIluminariaPowerPoint> find_iluminaria_powerPoint(Area area){        
        areaIluminariaPowerPoints = new ArrayList<>();
        try {
            preparedStatement = connection.getConexion().prepareStatement(AreaQueries.FIND_ILUMINARIA_POWERPOINT);
            preparedStatement.setInt(1, area.getCode());
            result = preparedStatement.executeQuery();
            while(result.next()){
                areaIluminariaPowerPoints.add(new AreaIluminariaPowerPoint(
                        result.getInt(1), 
                        null, 
                        new Calibers(result.getInt(2), 
                                new Material(result.getInt(3), result.getString(4)), 
                                new Temperature(result.getInt(5), 
                                        new Unit(0, String.valueOf(result.getInt(6) + " °C")), 
                                        result.getInt(6)), 
                                new Intensity(result.getInt(7), null, result.getInt(8)),
                                new Caliber(result.getInt(9), result.getString(10))), 
                        new Voltage(result.getInt(11), result.getInt(12), new Unit(0, String.valueOf(result.getInt(12) + " V"))), 
                        new Phase(result.getInt(13), result.getString(14)), 
                        result.getString(15).equalsIgnoreCase(TypeOfBranchCircuitInArea.ILUMINARIA.getType()) ? TypeOfBranchCircuitInArea.ILUMINARIA : 
                                result.getString(15).equalsIgnoreCase(TypeOfBranchCircuitInArea.POWER_POINT.getType()) ? TypeOfBranchCircuitInArea.POWER_POINT :
                                        result.getString(15).equalsIgnoreCase(TypeOfBranchCircuitInArea.SUB_FEEDER.getType()) ? TypeOfBranchCircuitInArea.SUB_FEEDER :
                                                result.getString(15).equalsIgnoreCase(TypeOfBranchCircuitInArea.NEUTRAL.getType()) ? TypeOfBranchCircuitInArea.NEUTRAL : null, 
                        result.getDouble(16), 
                        0, 
                        result.getDouble(17), 
                        new Caliber(result.getInt(18), result.getString(19)), 
                        new Breaker(result.getInt(20), result.getDouble(21)),
                        result.getString(22).equalsIgnoreCase(TypeRush.UNDERGROUND.getRush()) ? TypeRush.UNDERGROUND : TypeRush.AIR,
                        result.getDouble(23),
                        new Duct(result.getInt(24), result.getString(25)),
                        result.getDouble(26),
                        null,
                        0,
                        null,
                        result.getString(27)));
            }   
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return areaIluminariaPowerPoints; 
    }//Fin del método
    
    /**
     * Método para encontrar el total en iluminaria y toma corriente de un área determinada.
     * @param area
     * @return Retorna el total en iluminaria y toma corriente.
     */
    @Override
    public double consult_total_iluminaria_power_point(Area area){
        double potency_iluminaria = 0;
        double potency_power_point = 0;
        int quantity = 0;
        System.out.println("ENTRO");
        try {
            preparedStatement = connection.getConexion().prepareStatement(AreaQueries.CONSULT_TOTAL_ILUMINARIA);
            preparedStatement.setInt(1, area.getCode());
            result = preparedStatement.executeQuery();
            while (result.next()){
                potency_iluminaria = result.getDouble(1);
            }
            preparedStatement.close();
            preparedStatement = connection.getConexion().prepareStatement(AreaQueries.CONSULT_TOTAL_POWER_POINT);
            preparedStatement.setInt(1, area.getCode());
            result = preparedStatement.executeQuery();
            while (result.next()){
                potency_power_point = result.getDouble(1);
            }            
            preparedStatement.close();
            preparedStatement = connection.getConexion().prepareStatement(AreaQueries.CONSULT_QUANTITY_OF_A_AREA);
            preparedStatement.setInt(1, area.getCode());
            result = preparedStatement.executeQuery();
            while (result.next()) {
                quantity = result.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        System.out.println(potency_iluminaria);
        System.out.println(potency_power_point);
        System.out.println(quantity);
        return MethodsForCalculationsIluminariaPowerPoint.potencyInIluminariaAndPowerPoint(potency_iluminaria,potency_power_point) * quantity;
    }//Fin del método.
    
    /**
     * Método para consultar el código de un tipo de cagar.
     * @param typeTypeOfCharges
     * @return Retorna un código.
     */
    @Override
    public int consult_code_type_charge(TypeTypeOfCharges typeTypeOfCharges){
        int code_type_charge = 0;
        try {
            preparedStatement = connection.getConexion().prepareStatement(AreaQueries.CONSULT_CODE_TYPE_CHARGE);
            preparedStatement.setString(1, typeTypeOfCharges.getTypeCharge());
            result = preparedStatement.executeQuery();
            while(result.next()){
                code_type_charge = result.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return code_type_charge;
    }//Fin del método.
    
}
