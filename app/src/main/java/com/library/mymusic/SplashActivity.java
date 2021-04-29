package com.library.mymusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler h=new Handler();
        h.postDelayed(() -> {

            Intent Intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(Intent);
            finish();
        }, 4000);
    }
    }

