package com.library.mymusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.library.mymusic.UI.LOGIN.LoginActivity;
import com.library.mymusic.UI.REGISTER.RegisterActivity;

public class MainActivity extends AppCompatActivity {

    Button login;
    Button sign;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login=(Button)findViewById(R.id.B1);
        sign=(Button)findViewById(R.id.B2);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}