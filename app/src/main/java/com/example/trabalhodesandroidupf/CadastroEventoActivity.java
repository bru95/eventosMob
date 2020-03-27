package com.example.trabalhodesandroidupf;

import android.app.DatePickerDialog;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import Controller.CadastroEventoController;
import Model.Evento;

public class CadastroEventoActivity extends AppCompatActivity {

    private EditText nomeEvento;
    private EditText descEvento;
    private EditText dataEvento;
    private EditText valorEvento;
    private EditText vagasEvento;
    private EditText localEvento;

    private Calendar myCalendar;

    private CadastroEventoController controller;

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

        myCalendar = Calendar.getInstance();

        dataEvento.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    openDataPicker();
                }
            }
        });

    }

    public void openDataPicker() {
        final DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateEditTextDta();
            }

        };
        new DatePickerDialog(this, R.style.DataPickerTheme, dateListener, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateEditTextDta() {

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt", "BR"));
        dataEvento.setText(sdf.format(myCalendar.getTime()));
        dataEvento.setSelection(dataEvento.getText().toString().length());
    }

    public void cadastrarEvento(View view) {
        String nome = nomeEvento.getText().toString();
        String descricao = descEvento.getText().toString();
        String data = dataEvento.getText().toString();
        String valor = valorEvento.getText().toString();
        String vagas = vagasEvento.getText().toString();
        String local = localEvento.getText().toString();

        if(!nome.isEmpty() && !descricao.isEmpty() && !data.isEmpty() && !valor.isEmpty() && !vagas.isEmpty() && !local.isEmpty()) {
            controller.salvarEvento(nome, descricao, data, Double.valueOf(valor), Integer.valueOf(vagas), local);
            finish();
        } else {
            Snackbar.make(view, getString(R.string.informe_todos_dados), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }

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
