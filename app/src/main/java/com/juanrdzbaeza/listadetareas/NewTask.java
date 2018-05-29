package com.juanrdzbaeza.listadetareas;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.util.Calendar;


public class NewTask extends AppCompatActivity {

    EditText taskDescription, taskDate, taskClock;
    DatePicker datePicker;
    TimePicker timePicker;
    Button btnCalendar, btnOkDate, btnOkClock;
    Integer d,m,y,hor,min;
    Calendar calendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        Toolbar toolbar = findViewById(R.id.toolbar);
        if (null != toolbar) {
            setSupportActionBar(toolbar);
        }
        taskDescription = findViewById(R.id.task);
        taskDate        = findViewById(R.id.date);
        taskClock       = findViewById(R.id.clock);
        datePicker      = findViewById(R.id.dp);
        timePicker      = findViewById(R.id.tp);
        btnCalendar     = findViewById(R.id.btn_calendar);
        btnOkDate       = findViewById(R.id.btn_ok_date);
        btnOkClock      = findViewById(R.id.btn_ok_clock);

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.setVisibility(View.VISIBLE);
                btnOkDate.setVisibility(View.VISIBLE);

                timePicker.setVisibility(View.INVISIBLE);
                btnOkClock.setVisibility(View.INVISIBLE);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent(); // TODO: preparar la carga de la tarea para llenar la vista y hacer la cama al update
        if (intent.hasExtra("SelectedTask")) {
            Tarea t = (Tarea) intent.getSerializableExtra("SelectedTask");
            taskDescription.setText(t.getDescripcion());
        } else {
            Toast.makeText(this, "arzobispo", Toast.LENGTH_LONG).show();
        }
        

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_save:
                // TODO: 11/4/18 si los campos de texto estan vacios la app deveria advertir de ello y no permitir el envio

                Tarea nuevaTarea = new Tarea(taskDescription.getText().toString(), calendar);
                storeTask(nuevaTarea);
                closeContextMenu();
                finish();
                return true;
            case R.id.action_cancel:
                Toast.makeText(this, "ha seleccionado el boton para cancelar la tarea", Toast.LENGTH_SHORT).show();
                closeContextMenu();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * storeTask recive la nueva tarea recien registrada por el usuario, extrae sus atributos,
     * descripcion como String, y fecha lo pasa a long, despues se conecta a la base de datos
     * gestionTareas, y escribe alli la informacion en los campos correspondientes.
     * @param nuevaTarea la nueva tarea para ser almacenada
     */
    private void storeTask(Tarea nuevaTarea) {
        String descripcion = nuevaTarea.getDescripcion();
        Calendar fecha = nuevaTarea.getFecha();
        long timeInMillis = fecha.getTimeInMillis();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(
                this,"gestionTareas", null, 1
        );
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("descripcion",descripcion);
        values.put("fecha",timeInMillis);
        db.insert("tareas",null, values);
    }

    public void getDate(View v) {
        datePicker.setVisibility(View.INVISIBLE);
        btnOkDate.setVisibility(View.INVISIBLE);
        d = datePicker.getDayOfMonth();
        m = datePicker.getMonth(); // los meses empiezan en 0 (enero = 0, diciembre = 11)
        y = datePicker.getYear();



        taskDate.setText(d.toString()+"/"+(++m).toString()+"/"+y.toString());
        timePicker.setVisibility(View.VISIBLE);
        btnOkClock.setVisibility(View.VISIBLE);
        taskClock.setVisibility(View.VISIBLE);

    }

    public void getClock(View v) {
        hor = timePicker.getCurrentHour();
        min = timePicker.getCurrentMinute();

        /*
         * se incorpora un parche al mes restandole una unidad para hacer corresponder la fecha
         * introducida con la mostrada y con la almacenada, que será esta ultima la que se recoja en
         * la actividad principal para listar las tareas.
         * no he encontrado porque devuelve un mes mas del que se introduce, supongo que la clase
         * Calendar resuelve el hecho de que los meses empiezen en 0 para enero.
         */

        calendar.set(y,--m,d,hor,min);

        taskClock.setText(hor.toString()+":"+min.toString());

        taskClock.setText(hor.toString()+":"+min.toString());
        timePicker.setVisibility(View.INVISIBLE);
        btnOkClock.setVisibility(View.INVISIBLE);
    }

}
