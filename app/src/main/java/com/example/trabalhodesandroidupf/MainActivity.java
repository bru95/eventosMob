package com.example.trabalhodesandroidupf;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Adapter.adapterListaEventos;
import Controller.MainController;
import Model.Evento;

public class MainActivity extends AppCompatActivity {

    private ListView lista ;
    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lista = findViewById(R.id.lv_eventos);
        controller = new MainController(this);

        mostraEventos();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_login) {
            controller.loginActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void mostraEventos() {
        ArrayList<Evento> eventos = controller.getEventos();

        adapterListaEventos adapter = new adapterListaEventos(this, eventos);
        this.lista.setAdapter(adapter);
    }

    public void abreDetalhesEvento(Evento evento) {
        controller.maisDetalhesEvento(evento);
    }
}
