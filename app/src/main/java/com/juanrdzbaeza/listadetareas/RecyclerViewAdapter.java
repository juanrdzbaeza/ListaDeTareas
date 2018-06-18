package com.juanrdzbaeza.listadetareas;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
    private boolean etiquetaTiempo;

    /**
     * Constructor de clase.
     * @param context contexto de la aplicación
     * @param recyclerItemRes elemento de la lista
     * @param data datos que rellenaran la lista
     */
    public RecyclerViewAdapter(Context context, int recyclerItemRes, List<Tarea> data) {
        this.recyclerItemRes    = recyclerItemRes;
        this.data               = data;
        this.context            = context;
        this.etiquetaTiempo     = false;
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
        holder.pk.setText(String.valueOf(data.get(position).getPrimaryKey()));
        holder.descripcion.setText(data.get(position).getDescripcion());
        holder.fecha.setTextColor(Color.DKGRAY);
        Calendar now = Calendar.getInstance();
        holder.cuando.setTextColor(Color.BLUE);
        // TODO: 18/6/18 logica para las etiquetas de tiempo
        /*
         * si o = 1 o > fecha
         * si o = 0 o = fecha
         * si o = -1 o < fecha
         */
        int o = now.compareTo(data.get(position).getFecha());
        if (o > 0) {
            if(!etiquetaTiempo && (o > 0)) {
                holder.cuando.setVisibility(View.VISIBLE);
                holder.cuando.setText("atrasado");
                etiquetaTiempo = true;
            }
            holder.fecha.setTextColor(Color.RED);
        }else if(etiquetaTiempo && (o < 0)){
            holder.cuando.setVisibility(View.VISIBLE);
            holder.cuando.setText("no atrasado aun");
            etiquetaTiempo = false;
        }

        String fechaString = (data.get(position).toString());
        holder.fecha.setText(fechaString);
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
        private SparseBooleanArray selectedItems = new SparseBooleanArray();

        private TextView cuando;
        private TextView pk;
        private TextView descripcion;
        private TextView fecha;

        private ViewHolder(View itemView) {
            super(itemView);

            cuando      = itemView.findViewById(R.id.cuando);
            pk          = itemView.findViewById(R.id.primarykey);
            descripcion = itemView.findViewById(R.id.descripcion);
            fecha       = itemView.findViewById(R.id.fecha);
            itemView.setOnClickListener(new View.OnClickListener() {
                /**
                 * Called when a view has been clicked.
                 *
                 * @param v The view that was clicked.
                 */
                @Override
                public void onClick(View v) {
                    if (selectedItems.get(getAdapterPosition(),false)) {
                        selectedItems.delete(getAdapterPosition());
                        v.setSelected(false);
                        Toast.makeText(context,"sada", Toast.LENGTH_LONG).show();
                    } else {
                        selectedItems.put(getAdapterPosition(),true);
                        v.setSelected(true);
                        Tarea selectedTask = data.get(getAdapterPosition());
                        Intent intent = new Intent(context, NewTask.class);
                        intent.putExtra("SelectedTask", selectedTask);
                        context.startActivity(intent);
                    }
                }
            });

        }
    }
}
