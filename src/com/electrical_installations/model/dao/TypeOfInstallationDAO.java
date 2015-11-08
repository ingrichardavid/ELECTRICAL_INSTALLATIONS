/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.dao;

import com.electrical_installations.model.entity.TypeOfInstallation;
import java.util.List;

/**
 * Intefaz para el acoplamiento del Tipo de Instalación
 * @author Ing. Richard David
 * @version 1
 * @since 2015-07-27
 */
public interface TypeOfInstallationDAO {
    
    /**
     * Listar todos los tipos de instalaciones
     * @return Retorna una lista de instalaciones
     */
    public List<TypeOfInstallation> find_installations();
    
}
