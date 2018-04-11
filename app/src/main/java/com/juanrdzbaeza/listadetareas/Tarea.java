package com.juanrdzbaeza.listadetareas;

import java.util.Date;

/**
 * Created by juan on 10/4/18.
 *
 */

/*
 * TODO: 11/4/18 hay que razonar la gestion de los datos, una tarea tiene una descripcion y una fecha que estara
 * compuesta por dia/mes/año y hora:minutos, por lo que habra que pensar como recoger los datos para almacenarlos.
 */
public class Tarea {

    private String descripcion;
    private String fecha;   /* TODO: 10/4/18 el atributo fecha del objeto tarea debera ser tipo date,
                             * se hará necesario tratar los datos en algun momento para mostrarlo como un String
                             */
    private String hora;

    public Tarea(String descripcion, String fecha, String hora) {
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.hora = hora;
    }

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

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "descripcion='" + descripcion + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                '}';
    }
}
