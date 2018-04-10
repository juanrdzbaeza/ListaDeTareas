package com.juanrdzbaeza.listadetareas;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by juan on 10/4/18.
 *
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private int recyclerItemRes;
    private String[] data;
    private Context context;

    /**
     * Constructor de clase.
     * @param context contexto de la aplicación
     * @param recyclerItemRes elemento de la lista
     * @param data datos que rellenaran la lista
     */
    public RecyclerViewAdapter(Context context, int recyclerItemRes, String[] data) {
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
     *
     * @param holder ViewHolder correspondiente
     * @param position posicion del array de datos que se va a usar.
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.descripcion.setText(data[position]);
    }

    /**
     *
     * @return cuenta del array de datos
     */
    @Override
    public int getItemCount() {
        if (data != null) {
            return data.length;
        }
        return 0;
    }

    /**
     * La clase que representa su ViewHolder hereda de RecyclerView.ViewHolder tiene un atributo para cada elemento
     * gráfico y un constructor que inicializa estos elementos gráficos.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView descripcion;
        //private TextView fecha;

        private ViewHolder(View itemView) {
            super(itemView);
            descripcion = itemView.findViewById(R.id.descripcion);
            //fecha       = itemView.findViewById(R.id.fecha);
        }
    }
}
