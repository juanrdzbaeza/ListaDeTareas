package com.juanrdzbaeza.listadetareas;

import java.util.Date;

/**
 * Created by juan on 10/4/18.
 *
 */

public class Tarea {

    private String descripcion;
    private String fecha;

    public Tarea(String descripcion, String fecha) {
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public Tarea(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
