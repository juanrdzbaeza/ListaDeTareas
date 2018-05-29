package com.juanrdzbaeza.listadetareas;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by juan on 10/4/18.
 * Objeto Tarea.
 */

// TODO: 13/4/18 pensar si sería posible implementar fecha de inicio y fecha de fin.

public class Tarea implements Serializable {

    private Integer primaryKey;
    private String descripcion;
    private Calendar fecha;

    public Tarea(String descripcion, Calendar fecha) {
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public Integer getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Integer primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    /**
     * Método toString() para la clase Tarea, devuelve un String formateado de la forma "E, dd/MM/yyyy a las HH:mm"
     * siendo E el nombre del dia en la semana, d el numero del dia en el mes, M el numero del mes en el año y el año
     * H la hora en formato 24 y m el numero de minuto en la hora.
     * @return String "E, dd/MM/yyyy a las HH:mm"
     */
    @Override
    public String toString() {
        SimpleDateFormat dateFormatFecha    = new SimpleDateFormat("E, dd/MM/yyyy", Locale.getDefault());
        SimpleDateFormat dateFormatHora     = new SimpleDateFormat("HH:mm", Locale.getDefault());
        Date d = getFecha().getTime();
        return dateFormatFecha.format(d)+" a las "+dateFormatHora.format(d);
    }
}
