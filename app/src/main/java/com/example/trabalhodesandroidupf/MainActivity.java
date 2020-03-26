package com.example.trabalhodesandroidupf;

import android.os.Bundle;


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
import DAO.callbackEvento;
import Model.Evento;
import cn.pedant.SweetAlert.SweetAlertDialog;

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_login) {
            controller.loginActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void mostraEventos() {
        final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(R.color.colorPrimaryDark);
        pDialog.setTitleText("Carregando ...");
        pDialog.setCancelable(false);
        pDialog.show();

        controller.getEventos(new callbackEvento() {
            @Override
            public void onAllEventscallback(ArrayList<Evento> evs) {
                adapterListaEventos adapter = new adapterListaEventos(MainActivity.this, evs);
                lista.setAdapter(adapter);
                pDialog.dismiss();
            }
        });
    }

    public void abreDetalhesEvento(Evento evento) {
        controller.maisDetalhesEvento(evento);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mostraEventos();
    }
}
