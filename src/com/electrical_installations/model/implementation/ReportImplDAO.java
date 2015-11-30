/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.implementation;

import com.electrical_installations.model.DataBaseConnection;
import com.electrical_installations.model.dao.ReportDAO;
import com.electrical_installations.model.entity.Project;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport; 
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Administrador
 */
public class ReportImplDAO implements ReportDAO {

    private static final DataBaseConnection connection = DataBaseConnection.getInstance();
    private static ReportImplDAO instance;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private String ruta;
    private Map<String, Object> parametros;
    private JasperReport jasperReport;
    private JasperPrint jasperPrint;
    private JasperViewer jasperViewer;
    private File file;
    private Object ChartFactory;

    private ReportImplDAO() {
    }

    public static synchronized ReportImplDAO GetInstance() {
        if (instance == null) {
            instance = new ReportImplDAO();
        }
        return instance;
    }

    public void cancelInstance() {
        instance = null;
    }

    
   
    
    @Override
    public void report(Project project) {    
        try {
            file = new File("C:\\Program Files\\ELECTRICAL_INSTALLATIONS\\report\\reporte_inicial.jasper");
            parametros = new HashMap();
            parametros.put("project_code", project.getCode());
            parametros.put("project_code_type", project.getTypeOfInstallation().getCode());
            jasperPrint = JasperFillManager.fillReport(file.getAbsolutePath(), parametros,
                    connection.getConexion());
            jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setTitle("Hoja de Cálculo");
            jasperViewer.setZoomRatio((float) 0.75);
            jasperViewer.setLocationRelativeTo(null);
            jasperViewer.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
    }
    
}
