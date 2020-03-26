package com.example.trabalhodesandroidupf;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Adapter.adapterListaParticipantes;
import Controller.EventoDetalhesAdminController;
import Model.Evento;
import Model.Participante;

public class EventoDetalhesAdminActivity extends AppCompatActivity {

    private ExpandableListView list_participantes;
    private TextView tv_nome;
    private TextView tv_descricao;
    private TextView tv_data;
    private TextView tv_local;
    private TextView tv_valor;
    private TextView tv_vagas;
    private Evento evento;
    private EventoDetalhesAdminController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_detalhes_admin);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        list_participantes = findViewById(R.id.elv_participantes);
        tv_nome = findViewById(R.id.tv_eventoDetalhesAdmin);
        tv_descricao = findViewById(R.id.tv_descricaoDetalhesAdmin);
        tv_data = findViewById(R.id.tv_dataDetalhesAdmin);
        tv_local = findViewById(R.id.tv_localDetalhesAdmin);
        tv_valor = findViewById(R.id.tv_valorDetalhesAdmin);
        tv_vagas = findViewById(R.id.tv_vagasDetalhesAdmin);

        controller = new EventoDetalhesAdminController(this);

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            this.evento = (Evento) getIntent()
                    .getSerializableExtra("evento");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void mostraInfoEvento() {

        tv_nome.setText(evento.getNome());
        tv_descricao.setText(evento.getDescricao());
        tv_data.setText(evento.getData());
        tv_local.setText(evento.getLocal());
        tv_valor.setText("R$" + evento.getValor());
        tv_vagas.setText((evento.getVagas() - evento.getParticipantes().size()) + " vagas restantes");

        List<String> lstGrupos = new ArrayList<>();
        lstGrupos.add("Participantes");

        HashMap<String, List<Participante>> lstParticipantes = new HashMap<>();
        lstParticipantes.put(lstGrupos.get(0), evento.getParticipantes());
        adapterListaParticipantes adaptador = new adapterListaParticipantes(this, lstGrupos, lstParticipantes);
        // define o apadtador do ExpandableListView
        list_participantes.setAdapter(adaptador);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mostraInfoEvento();
    }

    public void excluirEvento(View view) {
        controller.removerEvento(evento);
        finish();
    }

    public void editarEvento(View view) {
        Intent intent = new Intent(this, CadastroEventoActivity.class);
        intent.putExtra("evento", evento);
        startActivity(intent);
        finish();
    }
}
