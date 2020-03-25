package Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.trabalhodesandroidupf.CadastroEventoActivity;
import com.example.trabalhodesandroidupf.SplashActivity;

import java.util.ArrayList;

import DAO.EventoRepository;
import Model.Evento;
import Utils.sharedPreferencesController;

public class EventoAdminController {

    private Activity act;

    public EventoAdminController(Activity act) {
        this.act = act;
    }

    public void logout() {
        Context ctx = act.getApplicationContext();
        sharedPreferencesController sharedPreference = new sharedPreferencesController(ctx);
        sharedPreference.setUsrLogado(false);
        startSplashActivity();
    }

    public void startSplashActivity(){
        Context ctx = act.getApplicationContext();
        Intent intent = new Intent(ctx, SplashActivity.class);
        ctx.startActivity(intent);
        this.act.finish();
    }

    public ArrayList<Evento> getTodosEventosAdmin() {
        EventoRepository db = new EventoRepository();
        return db.getTodosEventos();
    }

    public void startCadastroEventoActivity(){
        Context ctx = act.getApplicationContext();
        Intent intent = new Intent(ctx, CadastroEventoActivity.class);
        ctx.startActivity(intent);
    }
}
