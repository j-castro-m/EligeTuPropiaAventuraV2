package com.joelcastro.eligetupropiaaventura.models;

import java.io.Serializable;

/**
 * Created by joel on 12/10/14.
 */
public class AdventureNode implements Serializable{
    private int id;
    private String texto;
    private String GPS;
    private String titulo;
    private int siguienteNodoId1;
    private int siguienteNodoId2;

    public AdventureNode(){};
    public AdventureNode(int id, String texto, String GPS, String titulo, int siguienteNodoId1, int siguienteNodoId2) {
        this.id = id;
        this.texto = texto;
        this.GPS = GPS;
        this.titulo = titulo;
        this.siguienteNodoId1 = siguienteNodoId1;
        this.siguienteNodoId2 = siguienteNodoId2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getGPS() {
        return GPS;
    }

    public void setGPS(String GPS) {
        this.GPS = GPS;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getSiguienteNodoId1() {

        return siguienteNodoId1;
    }

    public void setSiguienteNodoId1(int siguienteNodoId1) {
        this.siguienteNodoId1 = siguienteNodoId1;
    }

    public int getSiguienteNodoId2() {
        return siguienteNodoId2;
    }

    public void setSiguienteNodoId2(int siguienteNodoId2) {
        this.siguienteNodoId2 = siguienteNodoId2;
    }
}
