package com.example.trabalhodesandroidupf;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import Controller.LoginController;
import DAO.callbackLogin;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class LoginActivity extends AppCompatActivity {

    private EditText et_email;
    private EditText et_senha;
    private LoginController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        et_email = findViewById(R.id.et_email);
        et_senha = findViewById(R.id.et_senha);
        controller = new LoginController(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mainActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void mainActivity(){
        startMainActivity();
    }

    @Override
    public void onBackPressed() {
        startMainActivity();
    }

    public void getInfoLogin(final View view) {
        String email = et_email.getText().toString();
        String senha = et_senha.getText().toString();

        if(!email.isEmpty() && !senha.isEmpty()) {

            final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
            pDialog.getProgressHelper().setBarColor(R.color.colorPrimaryDark);
            pDialog.setTitleText("Carregando ...");
            pDialog.setCancelable(false);
            pDialog.show();

            controller.loginUsuario(email, senha, new callbackLogin() {
                @Override
                public void onLogin(Boolean result) {
                    pDialog.dismiss();
                    if (result) {
                        startSplashActivity();
                    } else {
                        Snackbar.make(view, getString(R.string.login_incorreto), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            });
        } else {
            Snackbar.make(view, getString(R.string.informe_email_senha), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    private void startSplashActivity(){
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);
        finish();
    }


    public void startMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
