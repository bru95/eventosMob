package com.example.trabalhodesandroidupf;

import android.content.Intent;
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
import Adapter.adapterListaEventos;
import Controller.EventoAdminController;
import DAO.callbackEvento;
import Model.Evento;
import cn.pedant.SweetAlert.SweetAlertDialog;

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
        final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(R.color.colorPrimaryDark);
        pDialog.setTitleText("Carregando ...");
        pDialog.setCancelable(false);
        pDialog.show();

        controller.getTodosEventosAdmin(new callbackEvento() {
            @Override
            public void onAllEventscallback(ArrayList<Evento> eventos) {
                adapterGridEventos adapter = new adapterGridEventos(EventoAdminActivity.this, R.layout.item_evento_admin, eventos);
                gv_eventos.setAdapter(adapter);
                pDialog.dismiss();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mostraEventosAdmin();
    }

    public void startDetalhesAdminActivity(Evento evento) {
        Intent intent = new Intent(this, EventoDetalhesAdminActivity.class);
        intent.putExtra("evento", evento);
        startActivity(intent);
    }
}
