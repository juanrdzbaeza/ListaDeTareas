package com.juanrdzbaeza.listadetareas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.sql.Time;
import java.util.*;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private ArrayList<Tarea> tareas;
    private Tarea nuevaTarea;
    private int REQUEST_TASK = 1, TAREA_OK = 1, TAREA_KO = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initList(); // TODO: 11/4/18 inicializacion de la lista cada vez que abre esta vista, desactivar llegado el momento.
        
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (null != toolbar) {
            setSupportActionBar(toolbar);
        }
        
        
        llenarVista();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TASK){
            if (resultCode == TAREA_OK) {
                data.hasExtra("Tarea");
                if (null != getIntent()){
                    if (data.hasExtra("Tarea")) {

                        Toast.makeText(this, "ciqueracici", Toast.LENGTH_LONG).show();

                        nuevaTarea = (Tarea) data.getExtras().getSerializable("Tarea");
                        tareas.add(nuevaTarea);

                    }
                }
                llenarVista();
            } else {
                Toast.makeText(this, "adonde esta er jachi", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void llenarVista() {

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerViewAdapter(this, R.layout.layout_fila, tareas);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){ // TODO: 10/4/18 controlar las acciones de los distintos botones del menu. 
            case R.id.action_settings:
                Toast.makeText(this, "ha seleccionado el boton para agregar una tarea", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, NewTask.class);
                startActivityForResult(intent, REQUEST_TASK);
                return true;
            case R.id.action_prueba:

                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_delete:
                Toast.makeText(this, "ha seleccionado el boton para eliminar una o varias tareas", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_search:
                Toast.makeText(this, "ha seleccionado el boton para buscar", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initList() {
        /*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        String fecha = dateFormat.format(date);
        */
        tareas = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        c.set(2008,8, 23, 12,0);
        Tarea applePie          = new Tarea("Apple Pie", c);

        c = Calendar.getInstance();
        c.set(2009,1, 9, 12,0);
        Tarea bananaBread       = new Tarea("Banana Bread", c);

        c = Calendar.getInstance();
        c.set(2009,3, 25, 12,0);
        Tarea cupcake           = new Tarea("Cupcake", c);

        c = Calendar.getInstance();
        c.set(2009,8, 15, 12,0);
        Tarea donut             = new Tarea("Donut", c);

        c = Calendar.getInstance();
        c.set(2009,9, 26, 12,0);
        Tarea eclair            = new Tarea("Eclair", c);

        c = Calendar.getInstance();
        c.set(2010,4, 20, 12,0);
        Tarea froyo             = new Tarea("Froyo", c);

        c = Calendar.getInstance();
        c.set(2010,11, 6, 12,0);
        Tarea gingerbread       = new Tarea("Gingerbread", c);

        c = Calendar.getInstance();
        c.set(2011,1, 22, 12,0);
        Tarea honeycomb         = new Tarea("Honeycomb", c);

        c = Calendar.getInstance();
        c.set(2011,9, 18, 12,0);
        Tarea iceCreamSandwich  = new Tarea("Ice Cream Sandwich", c);

        c = Calendar.getInstance();
        c.set(2012,6, 9, 12,0);
        Tarea jellyBean         = new Tarea("Jelly Bean", c);

        c = Calendar.getInstance();
        c.set(2013,9, 31, 12,0);
        Tarea kitKat            = new Tarea("KitKat", c);

        c = Calendar.getInstance();
        c.set(2014,10, 12, 12,0);
        Tarea lollipop          = new Tarea("Lollipop", c);

        c = Calendar.getInstance();
        c.set(2015,9, 5, 12,0);
        Tarea marshmallow       = new Tarea("Marshmallow", c);

        c = Calendar.getInstance();
        c.set(2016,5, 15, 12,0);
        Tarea nougat            = new Tarea("Nougat", c);

        c = Calendar.getInstance();
        c.set(2017,7, 21, 12,0);
        Tarea oreo              = new Tarea("Oreo", c);

        c = Calendar.getInstance();
        c.set(2018,0, 27, 12,0);
        Tarea p                 = new Tarea("P", c);

        Collections.addAll(tareas,applePie,bananaBread,cupcake,donut,eclair,froyo,
                gingerbread,honeycomb,iceCreamSandwich,
                jellyBean,kitKat,lollipop,marshmallow,
                nougat,oreo,p);
    }


}
