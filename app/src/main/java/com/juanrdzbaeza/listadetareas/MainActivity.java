package com.juanrdzbaeza.listadetareas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

    private ArrayList<Tarea> tareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (null != toolbar) {
            setSupportActionBar(toolbar);
        }

        //loadTask();
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean r = loadTask();
        if (r){
            Toast.makeText(this, "Cargando tareas por hacer", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "No se encontraron tareas por hacer", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * TODO: javadoc metodo cargarTareas()
     * El método loadTask() es llamado desde el estado de onResume de la vista para realizar la
     * conexion a la base de datos "gestionTareas", recoger toda la tabla "tareas" y ordenarla
     * por "fecha". se crea una tarea auxiliar donde se iran asignando en un bucle while los atributos
     * encontrados en cada una de las filas que contenga la tabla, para ir llamando al metodo initList
     * que agrega la tarea a la estructura de datos del tipo ArrayList, que en tijecución almacena
     * el estado de la base de datos, esta consulta se realiza cada vez que la aplicacion vuelve de
     * una pausa.
     * @return valor booleano correcto o no.
     */
    @SuppressLint("Recycle")
    public boolean loadTask() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(
                this,"gestionTareas", null, 1
        );
        //realizo la consulta
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor fila = null;
        try {
            fila = db.query(
                    "tareas",
                    null,
                    null,
                    null,
                    null,
                    null,
                    "fecha"
            );
        }catch (Exception e){
            e.printStackTrace();
        }

        //declaro lo necesario
        tareas = new ArrayList<>();
        Tarea aux;
        Calendar c;
        boolean r = false;

        //implemento lógica para almacenar datos en tiempo de ejecución
        if ((fila != null) && (fila.moveToFirst())) {
            do {
                aux = new Tarea(null,null);
                c   = Calendar.getInstance();
                aux.setDescripcion(fila.getString(1));
                c.setTimeInMillis(fila.getLong(2));
                aux.setFecha(c);
                r = initList(aux);
            }while (fila.moveToNext());
        } else {
            return false;
        }
        if (r) fillView();
        return true;
    }

    /**
     * El método fillView() [llenar vista] realiza el inflado de la vista principal a partir de la
     * recicler view, para hacerlo recoge los datos almacenados ya en memoria principal dentro de la
     * estructura de datos tareas, se instancia un RecyclerViewAdapter a partir del contexto de la
     * vista, el "layout_fila", y la lista de tareas y se llama al metodo setAdapter para que realice
     * el llenado de la vista con los datos.
     */
    public void fillView() {

        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, R.layout.layout_fila, tareas);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Metodo sobrescrito de la clase AppCompatActivity extendida
     * @param menu menu de la app para inflarlo
     * @return valor booleano correcto o no
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Metodo sobrescrito de la clase AppCompatActivity extendida. se trata de la logica de
     * actuacion de los botones del menu de la app. recibe como parametro el elemento seleccionado
     * desde la interfaz de la aplicacion. dependiendo del id del elemento se seleccionara una u
     * otra accion.
     * @param item elemento seleccionado
     * @return valor booleano correcto o no
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){ // TODO: 10/4/18 controlar las acciones de los distintos botones del menu. 
            case R.id.action_settings:
                //Toast.makeText(this, "ha seleccionado el boton para agregar una tarea", Toast.LENGTH_SHORT).show();
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

    /**
     * El método initList() se encarga de recibir una tarea cada vez que es llamado, y la agrega a
     * la estructura de datos que maneja la informacion en tiempo de ejecucion.
     * en tiempo de ejecucion y de rellenarla con
     * @param t se recibe la tarea que se desea agregar a la estructura de datos
     * @return valor booleano correcto o no
     */
    private boolean initList(Tarea t) {
        return tareas.add(t);
    }

}

    /*
    papelera:
     */

    /*
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
                fillView();
            } else {
                Toast.makeText(this, "el perro de sanroque no tiene rabo", Toast.LENGTH_LONG).show();
            }
        }
    }
    */

    /*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        String fecha = dateFormat.format(date);

        tareas = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        c.set(2008,8, 23, 12,00);
        Tarea applePie          = new Tarea("Apple Pie", c);

        c = Calendar.getInstance();
        c.set(2009,1, 9, 12,00);
        Tarea bananaBread       = new Tarea("Banana Bread", c);

        c = Calendar.getInstance();
        c.set(2009,3, 25, 12,00);
        Tarea cupcake           = new Tarea("Cupcake", c);

        c = Calendar.getInstance();
        c.set(2009,8, 15, 12,00);
        Tarea donut             = new Tarea("Donut", c);

        c = Calendar.getInstance();
        c.set(2009,9, 26, 12,00);
        Tarea eclair            = new Tarea("Eclair", c);

        c = Calendar.getInstance();
        c.set(2010,4, 20, 12,00);
        Tarea froyo             = new Tarea("Froyo", c);

        c = Calendar.getInstance();
        c.set(2010,11, 6, 12,00);
        Tarea gingerbread       = new Tarea("Gingerbread", c);

        c = Calendar.getInstance();
        c.set(2011,1, 22, 12,00);
        Tarea honeycomb         = new Tarea("Honeycomb", c);

        c = Calendar.getInstance();
        c.set(2011,9, 18, 12,00);
        Tarea iceCreamSandwich  = new Tarea("Ice Cream Sandwich", c);

        c = Calendar.getInstance();
        c.set(2012,6, 9, 12,00);
        Tarea jellyBean         = new Tarea("Jelly Bean", c);

        c = Calendar.getInstance();
        c.set(2013,9, 31, 12,00);
        Tarea kitKat            = new Tarea("KitKat", c);

        c = Calendar.getInstance();
        c.set(2014,10, 12, 12,00);
        Tarea lollipop          = new Tarea("Lollipop", c);

        c = Calendar.getInstance();
        c.set(2015,9, 5, 12,00);
        Tarea marshmallow       = new Tarea("Marshmallow", c);

        c = Calendar.getInstance();
        c.set(2016,5, 15, 12,00);
        Tarea nougat            = new Tarea("Nougat", c);

        c = Calendar.getInstance();
        c.set(2017,7, 21, 12,00);
        Tarea oreo              = new Tarea("Oreo", c);

        c = Calendar.getInstance();
        c.set(2018,0, 27, 12,00);
        Tarea p                 = new Tarea("P", c);

        Collections.addAll(tareas,applePie,bananaBread,cupcake,donut,eclair,froyo,
                gingerbread,honeycomb,iceCreamSandwich,
                jellyBean,kitKat,lollipop,marshmallow,
                nougat,oreo,p);
        */
