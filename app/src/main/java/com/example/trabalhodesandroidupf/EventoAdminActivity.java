package com.example.trabalhodesandroidupf;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

import Adapter.adapterGridEventos;
import Controller.EventoAdminController;
import Model.Evento;

public class EventoAdminActivity extends AppCompatActivity {

    private EventoAdminController controller;
    private GridView gv_eventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_admin);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        controller = new EventoAdminController(this);
        gv_eventos = findViewById(R.id.gv_eventos);

        FloatingActionButton fab = findViewById(R.id.novo_evento);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.startCadastroEventoActivity();
            }
        });

        mostraEventosAdmin();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            controller.logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void mostraEventosAdmin() {
        ArrayList<Evento> eventos = controller.getTodosEventosAdmin();

        adapterGridEventos adapter = new adapterGridEventos(this, R.layout.item_evento_admin, eventos);
        gv_eventos.setAdapter(adapter);
    }

}
