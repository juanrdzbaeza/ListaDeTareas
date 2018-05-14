package com.juanrdzbaeza.listadetareas;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

/**
 * Created by juan on 10/4/18.
 *
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private int recyclerItemRes;
    private List<Tarea> data;
    private Context context;

    /**
     * Constructor de clase.
     * @param context contexto de la aplicación
     * @param recyclerItemRes elemento de la lista
     * @param data datos que rellenaran la lista
     */
    public RecyclerViewAdapter(Context context, int recyclerItemRes, List<Tarea> data) {
        this.recyclerItemRes = recyclerItemRes;
        this.data = data;
        this.context = context;
    }

    /**
     *
     * @param parent elemento padre
     * @param viewType vista
     * @return ViewHolder correspondiente
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(recyclerItemRes, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Se van extrayendo los datos contenidos en la lita "data" (contiene las tareas). Cada objeto
     * tarea extraido es consultado para obtener sus atributos descripcion y fecha, que son incrustados
     * en los correspondientes campos de texto. Primero definimos el color del campo de texto fecha
     * a gris oscuro, si se detecta que la fecha de la tarea es anterior a la fecha de ejecucion,
     * se pintara este campo de texto de color rojo.
     * @param holder ViewHolder correspondiente
     * @param position posicion del array de datos que se va a usar.
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.descripcion.setText(data.get(position).getDescripcion());
        holder.fecha.setTextColor(Color.DKGRAY);
        // TODO: 13/4/18 el string conca de momento esta a prueba, arreglar futuramente.

        Calendar now = Calendar.getInstance();
        /*
         * si o = 1 o > fecha
         * si o = 0 o = fecha
         * si o = -1 o < fecha
         */
        int o = now.compareTo(data.get(position).getFecha());
        if (o == 1){
            holder.fecha.setTextColor(Color.RED);
        }
        String conca = (data.get(position).toString());
        holder.fecha.setText(conca);
    }

    /**
     *
     * @return cuenta del array de datos
     */
    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    /**
     * La clase que representa su ViewHolder hereda de RecyclerView.ViewHolder tiene un atributo para cada elemento
     * gráfico y un constructor que inicializa estos elementos gráficos.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView descripcion;
        private TextView fecha;

        private ViewHolder(View itemView) {
            super(itemView);
            descripcion = itemView.findViewById(R.id.descripcion);
            fecha       = itemView.findViewById(R.id.fecha);
        }
    }
}
