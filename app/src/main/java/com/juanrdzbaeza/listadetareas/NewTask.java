package com.juanrdzbaeza.listadetareas;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;


public class NewTask extends AppCompatActivity {

    // TODO: 10/4/18 implementar Vista y Presentador para NewTask 

    EditText taskDescription, taskDate, taskClock;
    DatePicker datePicker;
    TimePicker timePicker;
    Button btnCalendar, btnClock, btnOkDate, btnOkClock;
    Integer d,m,y,hor,min;
    Calendar calendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        Intent a = getIntent();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_save:
                //Toast.makeText(this, "ha seleccionado el boton para salvar la tarea", Toast.LENGTH_SHORT).show();
                // TODO: 11/4/18 si los campos de texto estan vacios la app deveria advertir de ello y no permitir el envio
                Intent intentNewTask = new Intent();
                Tarea nuevaTarea = new Tarea(taskDescription.getText().toString(), calendar);
                intentNewTask.putExtra("Tarea", nuevaTarea);

                setResult(1,intentNewTask);
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

        calendar.set(y,m,d,hor,min);

        taskClock.setText(hor.toString()+":"+min.toString());
        timePicker.setVisibility(View.INVISIBLE);
        btnOkClock.setVisibility(View.INVISIBLE);
    }

}
