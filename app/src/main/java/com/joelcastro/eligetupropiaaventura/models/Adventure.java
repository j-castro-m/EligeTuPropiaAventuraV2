package com.joelcastro.eligetupropiaaventura.models;

/**
 * Created by joel on 12/10/14.
 */
public class Adventure {

    private String nombre;
    private String descripcion;
    private int idNodoInicial;
    private int idNodoActual;

    public Adventure(){};


    public Adventure(String nombre, String descripcion, int idNodoInicial, int idNodoActual) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idNodoInicial = idNodoInicial;
        this.idNodoActual = idNodoActual;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdNodoInicial() {
        return idNodoInicial;
    }

    public void setIdNodoInicial(int idNodoInicial) {
        this.idNodoInicial = idNodoInicial;
    }

    public int getIdNodoActual() {
        return idNodoActual;
    }

    public void setIdNodoActual(int idNodoActual) {
        this.idNodoActual = idNodoActual;
    }
}
