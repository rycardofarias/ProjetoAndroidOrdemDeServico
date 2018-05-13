package com.projetoengenharia.projetoengenharia.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.projetoengenharia.projetoengenharia.R;

public class TelaSplash extends AppCompatActivity {

    private final static int TIME_SPLASH = 3500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(TelaSplash.this, MainActivity.class));
                finish();
            }
        }, TIME_SPLASH);
    }
}
