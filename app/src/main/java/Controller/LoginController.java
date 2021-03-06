package Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.trabalhodesandroidupf.MainActivity;
import com.example.trabalhodesandroidupf.R;
import com.example.trabalhodesandroidupf.SplashActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import DAO.Auth;
import DAO.callbackLogin;
import Utils.sharedPreferencesController;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class LoginController {

    private Auth auth;
    private Activity act;

    public LoginController(Activity act) {
        this.act = act;
        auth = new Auth();
    }

    public void loginUsuario(String email, String senha, final callbackLogin callbackLogin) {
        Task<AuthResult> task = auth.login(email, senha);
        task.addOnCompleteListener(act, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Context ctx = act.getApplicationContext();
                sharedPreferencesController sharedPreference = new sharedPreferencesController(ctx);

                if (task.isSuccessful()) {
                    sharedPreference.setUsrLogado(true);
                    callbackLogin.onLogin(true);
                } else { //erro
                    sharedPreference.setUsrLogado(false);
                    callbackLogin.onLogin(false);
                }
            }
        });
    }
}
