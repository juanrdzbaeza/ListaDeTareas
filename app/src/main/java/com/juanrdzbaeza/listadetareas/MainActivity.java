package com.juanrdzbaeza.listadetareas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.*;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private String[] myDataset = {
                "Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread", "Honeycomb", "Ice Cream Sandwich",
                "Jelly Bean", "KitKat", "Lolipop", "Marshmallow", "Nougat", "Oreo", "P"
        };

    private ArrayList<Tarea> tareas;

    /*
map.put(4, "Donut");
map.put(5, "Eclair");		map.put(8, "Froyo");
map.put(9, "Gingerbread");	map.put(11, "Honeycomb");
map.put(14, "Ice Cream Sandwich");	map.put(16, "Jelly Bean");
map.put(19, "KitKat");		map.put(21, "Lolipop");
map.put(23, "Marshmallow");
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initList();

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);



                                                                            // myDataSet seran los datos recuperados
        adapter = new RecyclerViewAdapter(this, R.layout.layout_fila, tareas);
        recyclerView.setAdapter(adapter);

    }

    private void initList() {
        /*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        String fecha = dateFormat.format(date);
        */
        tareas = new ArrayList<>();
        Tarea cupcake          = new Tarea("Cupcake", Integer.valueOf(3).toString());
        Tarea donut            = new Tarea("Donut", Integer.valueOf(4).toString());
        Tarea eclair           = new Tarea("Eclair", Integer.valueOf(5).toString());
        Tarea froyo            = new Tarea("Froyo", Integer.valueOf(8).toString());
        Tarea gingerbread      = new Tarea("Gingerbread", Integer.valueOf(9).toString());
        Tarea honeycomb        = new Tarea("Honeycomb", Integer.valueOf(11).toString());
        Tarea iceCreamSandwich = new Tarea("Ice Cream Sandwich", Integer.valueOf(14).toString());
        Tarea jellyBean        = new Tarea("Jelly Bean", Integer.valueOf(16).toString());
        Tarea kitkat           = new Tarea("KitKat",Integer.valueOf(19).toString());
        Tarea lollipop         = new Tarea("Lollipop", Integer.valueOf(21).toString());
        Tarea marshmallow      = new Tarea("Marshmallow", Integer.valueOf(23).toString());
        Tarea nougat           = new Tarea("Nougat", Integer.valueOf(24).toString());
        Tarea oreo             = new Tarea("Oreo",Integer.valueOf(26).toString());
        Tarea p                = new Tarea("P",Integer.valueOf(28).toString());
        Collections.addAll(tareas,cupcake,donut,eclair,froyo,
                gingerbread,honeycomb,iceCreamSandwich,
                jellyBean,kitkat,lollipop,marshmallow,
                nougat,oreo,p);
    }


}
