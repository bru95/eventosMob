package com.example.trabalhodesandroidupf;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import Model.Evento;

public class EventoDetalhesActivity extends AppCompatActivity {

    private Evento evento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_detalhes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            this.evento = (Evento) getIntent()
                    .getSerializableExtra("evento");
        }

        mostraInfoEvento();
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
        if(evento != null) {
            TextView nome = findViewById(R.id.tv_eventoDetalhes);
            nome.setText(evento.getNome());

            TextView desc = findViewById(R.id.tv_descricaoDetalhes);
            desc.setText(evento.getDescricao());

            TextView data = findViewById(R.id.tv_dataDetalhes);
            data.setText(evento.getData());

            TextView local = findViewById(R.id.tv_localDetalhes);
            local.setText(evento.getLocal());

            TextView valor = findViewById(R.id.tv_valorDetalhes);
            valor.setText("R$" + evento.getValor());

            TextView vagas = findViewById(R.id.tv_vagasDetalhes);
            vagas.setText(evento.getVagas() + " vagas restantes");
        }
    }

}
