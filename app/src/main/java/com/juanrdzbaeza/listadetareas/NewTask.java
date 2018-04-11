package com.juanrdzbaeza.listadetareas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class NewTask extends AppCompatActivity {

    // TODO: 10/4/18 implementar Vista y Presentador para NewTask 
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (null != toolbar) {
            setSupportActionBar(toolbar);
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
        switch (id){ // TODO: 10/4/18 controlar las acciones de los distintos botones del menu.
            case R.id.action_save:
                Toast.makeText(this, "ha seleccionado el boton para salvar la tarea", Toast.LENGTH_SHORT).show();

                return true;
            case R.id.action_cancel:
                Toast.makeText(this, "ha seleccionado el boton para cancelar la tarea", Toast.LENGTH_SHORT).show();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
