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

import java.util.*;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private ArrayList<Tarea> tareas;
    private Tarea nuevaTarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "colora", Toast.LENGTH_SHORT).show();

        initList(); // TODO: 11/4/18 inicializacion de la lista cada vez que abre esta vista, desactivar llegado el momento.


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (null != toolbar) {
            setSupportActionBar(toolbar);
        }

        Intent intentNewTask = getIntent();
        if (null != getIntent()){
            if (intentNewTask.hasExtra("Tarea")) {
                nuevaTarea = (Tarea) getIntent().getExtras().getSerializable("Tarea");
                tareas.add(nuevaTarea);
            }
        }

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerViewAdapter(this, R.layout.layout_fila, tareas);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "de manteca", Toast.LENGTH_SHORT).show();
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
                startActivity(intent);
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
        /*
        Tarea cupcake          = new Tarea("Cupcake", new Date());
        Tarea donut            = new Tarea("Donut", new Date());
        Tarea eclair           = new Tarea("Eclair", new Date());
        Tarea froyo            = new Tarea("Froyo", new Date());
        Tarea gingerbread      = new Tarea("Gingerbread", new Date());
        Tarea honeycomb        = new Tarea("Honeycomb", new Date());
        Tarea iceCreamSandwich = new Tarea("Ice Cream Sandwich", new Date());
        Tarea jellyBean        = new Tarea("Jelly Bean", new Date());
        Tarea kitkat           = new Tarea("KitKat", new Date());
        Tarea lollipop         = new Tarea("Lollipop", new Date());
        Tarea marshmallow      = new Tarea("Marshmallow", new Date());
        Tarea nougat           = new Tarea("Nougat", new Date());
        Tarea oreo             = new Tarea("Oreo", new Date());
        Tarea p                = new Tarea("P",new Date());
        Collections.addAll(tareas,cupcake,donut,eclair,froyo,
                gingerbread,honeycomb,iceCreamSandwich,
                jellyBean,kitkat,lollipop,marshmallow,
                nougat,oreo,p);
        */
    }


}
