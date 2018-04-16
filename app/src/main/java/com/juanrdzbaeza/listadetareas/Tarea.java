package com.juanrdzbaeza.listadetareas;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by juan on 10/4/18.
 *
 */

// TODO: 13/4/18 pensar si seria posible implementar fecha de inicio y fecha de fin.

public class Tarea implements Serializable {

    private String descripcion;
    private Date fecha;

    public Tarea(String descripcion, Date fecha) {
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    /* TODO 13-04-2018: ¿es necesario un constructor que tome solo la descripcion?
    public Tarea(String descripcion) {
        this.descripcion = descripcion;
    }
    */

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        // TODO: 13/4/18 implementar un toString que devuelva la fecha formateada.

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        String fecha = dateFormat.format(getFecha());



        return fecha;
    }
}
