package Utils;

import android.content.Context;
import android.content.SharedPreferences;

import Model.Participante;

public class sharedPreferencesController {

    private static final String NOME_SHAREDPREFERENCES = "shared_eventosMob";
    private Context ctx;

    public sharedPreferencesController (Context ctx) {
        this.ctx = ctx;
     }

    public boolean usrLogado() {
        SharedPreferences sharedPref = ctx.getSharedPreferences(NOME_SHAREDPREFERENCES, ctx.MODE_PRIVATE);
        return sharedPref.getBoolean("usr_logado", false);
    }

    public void setUsrLogado(boolean login) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(NOME_SHAREDPREFERENCES, ctx.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("usr_logado", login);
        editor.commit();
    }

     public void setInfoParticipante(String nome, String email) {
         SharedPreferences sharedPref = ctx.getSharedPreferences(NOME_SHAREDPREFERENCES, ctx.MODE_PRIVATE);
         SharedPreferences.Editor editor = sharedPref.edit();
         editor.putString("nome_participante", nome);
         editor.putString("email_participante", email);
         editor.commit();
     }


     public Participante getInfoParticipante() {
         SharedPreferences sharedPref = ctx.getSharedPreferences(NOME_SHAREDPREFERENCES, ctx.MODE_PRIVATE);
         String nome = sharedPref.getString("nome_participante", "");
         String email = sharedPref.getString("email_participante", "");

         return new Participante(nome, email);
     }
}
