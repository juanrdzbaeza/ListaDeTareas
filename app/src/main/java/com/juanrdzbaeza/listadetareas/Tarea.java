package com.juanrdzbaeza.listadetareas;

import java.util.Date;

/**
 * Created by juan on 10/4/18.
 *
 */

public class Tarea {

    private String descripcion;
    private String fecha;   /* TODO: 10/4/18 el atributo fecha del objeto tarea debera ser tipo date,
                             * se hará necesario tratar los datos en algun momento para mostrarlo como un String
                             */


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
