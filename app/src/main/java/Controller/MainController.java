package Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.trabalhodesandroidupf.EventoDetalhesActivity;
import com.example.trabalhodesandroidupf.LoginActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import DAO.EventoRepository;
import DAO.callbackEvento;
import Model.Evento;

public class MainController {

    private Activity act;

    public MainController(Activity act) {
        this.act = act;
    }

    public void getEventos(callbackEvento callbackEvento) {
        EventoRepository db = new EventoRepository();
        db.getTodosEventos(callbackEvento);
    }

    public void maisDetalhesEvento(Evento evento) {
        Context ctx = act.getApplicationContext();
        Intent intent = new Intent(ctx, EventoDetalhesActivity.class);
        intent.putExtra("evento", evento);
        ctx.startActivity(intent);
    }

    public void loginActivity() {
        Context ctx = act.getApplicationContext();
        Intent intent = new Intent(ctx, LoginActivity.class);
        ctx.startActivity(intent);
        this.act.finish();
    }
}
