package com.example.trabalhodesandroidupf;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Controller.CadastroEventoController;
import Model.Evento;

public class CadastroEventoActivity extends AppCompatActivity {

    private EditText nomeEvento;
    private EditText descEvento;
    private EditText dataEvento;
    private EditText valorEvento;
    private EditText vagasEvento;
    private EditText localEvento;

    private CadastroEventoController controller;

    private Evento eventoEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Evento evento = null;
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            evento = (Evento) getIntent()
                    .getSerializableExtra("evento");
        }

        controller = new CadastroEventoController(evento);

        nomeEvento = findViewById(R.id.et_nomeNovoEvento);
        descEvento = findViewById(R.id.et_descricaoNovoEvento);
        dataEvento = findViewById(R.id.et_dataNovoEvento);
        valorEvento = findViewById(R.id.et_valorNovoEvento);
        vagasEvento = findViewById(R.id.et_vagasNovoEvento);
        localEvento = findViewById(R.id.et_localNovoEvento);

    }

    public void cadastrarEvento(View view) {
        String nome = nomeEvento.getText().toString();
        String descricao = descEvento.getText().toString();
        String data = dataEvento.getText().toString();
        String valor = valorEvento.getText().toString();
        String vagas = vagasEvento.getText().toString();
        String local = localEvento.getText().toString();

        controller.salvarEvento(nome, descricao, data, Double.valueOf(valor), Integer.valueOf(vagas), local);
        finish();
    }

    private void preencheForm(Evento evento) {
        nomeEvento.setText(evento.getNome());
        descEvento.setText(evento.getDescricao());
        dataEvento.setText(evento.getData());
        valorEvento.setText(String.valueOf(evento.getValor()));
        vagasEvento.setText(String.valueOf(evento.getVagas()));
        localEvento.setText(evento.getLocal());

        Button bt_edit = findViewById(R.id.bt_salvarEvento);
        bt_edit.setText(R.string.salvar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (controller.getEventoEdit() != null) {
            preencheForm(controller.getEventoEdit());
        }
    }
}
