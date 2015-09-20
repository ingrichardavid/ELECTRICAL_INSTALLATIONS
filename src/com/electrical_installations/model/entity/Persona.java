/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electrical_installations.model.entity;

/**
 *
 * @author JRichard
 */
public class Persona {
    
    private int codigo;
    private String nombre;
    private String apellido;
    private Area area;

    public Persona() {
    }
    
    public Persona(int codigo) {
        this.codigo = codigo;
    }

    public Persona(int codigo, String nombre, String apellido, Area area) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.area = area;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Persona{" + "codigo=" + codigo + ", nombre=" + nombre + ", apellido=" + apellido + ", area=" + area + '}';
    }
    
}
