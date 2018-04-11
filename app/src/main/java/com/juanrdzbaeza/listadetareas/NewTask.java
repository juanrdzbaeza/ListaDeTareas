package com.juanrdzbaeza.listadetareas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;


public class NewTask extends AppCompatActivity {

    // TODO: 10/4/18 implementar Vista y Presentador para NewTask 

    EditText taskDescription, taskDate;
    DatePicker datePicker;
    Button btnCalendar, btnOK;
    Integer d,m,y;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (null != toolbar) {
            setSupportActionBar(toolbar);
        }

        taskDescription = findViewById(R.id.task);
        taskDate        = findViewById(R.id.date);
        datePicker      = findViewById(R.id.dp);
        btnCalendar     = findViewById(R.id.btn_calendar);
        btnOK           = findViewById(R.id.btn_ok);
        
        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.setVisibility(View.VISIBLE);
                btnOK.setVisibility(View.VISIBLE);
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
                Toast.makeText(this, "ha seleccionado el boton para salvar la tarea", Toast.LENGTH_SHORT).show();
                // TODO: 11/4/18 si los campos de texto estan vacios la app deveria advertir de ello y no permitir el envio
                Intent newTask = new Intent(this, MainActivity.class);
                newTask.putExtra("Descripcion", taskDescription.getText().toString());
                newTask.putExtra("dia",d.toString());
                newTask.putExtra("mes",m.toString());
                newTask.putExtra("year",y.toString());
                startActivity(newTask);
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

        d = datePicker.getDayOfMonth();
        m = datePicker.getMonth(); // los meses empiezan en 0 (enero = 0, diciembre = 11)
        y = datePicker.getYear();


        taskDate.setText(d.toString()+"/"+(++m).toString()+"/"+y.toString());



        datePicker.setVisibility(View.INVISIBLE);
        btnOK.setVisibility(View.INVISIBLE);
    }

}
