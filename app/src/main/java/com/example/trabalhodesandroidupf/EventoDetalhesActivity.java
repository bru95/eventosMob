package com.example.trabalhodesandroidupf;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import Controller.EventoDetalhesController;
import Model.Evento;
import Model.Participante;
import Utils.sharedPreferencesController;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class EventoDetalhesActivity extends AppCompatActivity {

    private EventoDetalhesController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_detalhes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle extra = getIntent().getExtras();
        Evento evento = null;
        if (extra != null) {
            evento = (Evento) getIntent()
                    .getSerializableExtra("evento");
        }


        controller = new EventoDetalhesController(this, evento);

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
        Evento evento = controller.getEvento();
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
            vagas.setText((evento.getVagas() - evento.getParticipantes().size()) + " vagas restantes");

            if(evento.getVagas() - evento.getParticipantes().size() == 0) {
                Button bt_participar = findViewById(R.id.bt_participar);
                bt_participar.setEnabled(false);
            }

            sharedPreferencesController shared = new sharedPreferencesController(this);
            Participante previous = shared.getInfoParticipante();
            TextView inscrito = findViewById(R.id.tv_inscrito);
            if(evento.procuraParticipante(previous)) {
                inscrito.setVisibility(View.VISIBLE);
            } else {
                inscrito.setVisibility(View.GONE);
            }
        }
    }

    public void openDialogInscreva_se(final View view) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_inscricao);
        dialog.setTitle("Preencha o formulário");

        final EditText et_nome = dialog.findViewById(R.id.et_nomeParticipante);
        final EditText et_email = dialog.findViewById(R.id.et_emailParticipante);

        sharedPreferencesController shared = new sharedPreferencesController(this);
        Participante previous = shared.getInfoParticipante();
        et_nome.setText(previous.getNome());
        et_email.setText(previous.getEmail());

        Button dialogButton = (Button) dialog.findViewById(R.id.bt_inscrever);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = et_nome.getText().toString();
                String email = et_email.getText().toString();

                if(!nome.isEmpty() && !email.isEmpty()) {
                    dialog.dismiss();
                    novoParticipante(nome, email);
                }
            }
        });

        dialog.show();
    }

    private void novoParticipante(String nome, String email) {
        SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(R.color.colorPrimaryDark);
        pDialog.setTitleText("Carregando ...");
        pDialog.setCancelable(false);
        pDialog.show();

        controller.insereParticipante(controller.getEvento(), nome, email);

        pDialog.dismiss();

        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Parabéns!")
                .setContentText("Você está inscrito neste evento.")
                .show();

        mostraInfoEvento();

    }

}
