package com.example.trabalhodesandroidupf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import Utils.sharedPreferencesController;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    private void startActivitiInicial() {
        sharedPreferencesController ctrl_sp = new sharedPreferencesController(this);
        if (ctrl_sp.usrLogado()) {
            Intent intent = new Intent(this, EventoAdminActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override public void run() {
                startActivitiInicial();
            }
        }, 2000);
    }
}
