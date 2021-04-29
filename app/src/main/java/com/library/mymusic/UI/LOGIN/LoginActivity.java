package com.library.mymusic.UI.LOGIN;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.library.mymusic.R;
import com.library.mymusic.UI.PLAYLIST.PlayActivity;
import com.library.mymusic.UI.REGISTER.RegisterActivity;
import com.library.mymusic.UI.RESET.ResetActivity;
import com.library.mymusic.UTILITY.ConnectionManager;
import com.library.mymusic.UTILITY.TransparentProgressDialog;


public class LoginActivity extends AppCompatActivity {

    TextView signup,forgotpass;
    EditText email,passwrd;
    LinearLayout login;
    Intent intent;
    private ConnectionManager connectionStatus;
    private Boolean isInternetPresent;
    private TransparentProgressDialog pd;
    private Handler h;
    private Runnable r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        connectionStatus =new ConnectionManager(getApplicationContext());
        isInternetPresent =connectionStatus.isConnectingToInternet();
        h = new Handler();
        pd = new TransparentProgressDialog(this, R.drawable.loader);
        r =new Runnable() {
            @Override
            public void run() {
                if (pd.isShowing()) {
                    pd.dismiss();
                }
            }
        };
        signup=(TextView)findViewById(R.id.signup_page);
        forgotpass=(TextView)findViewById(R.id.forgot_pass);
        email=(EditText) findViewById(R.id.userid);
       passwrd=(EditText)findViewById(R.id.Pass);
        login=(LinearLayout) findViewById(R.id.login_submit);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation(email.getText().toString(), passwrd.getText().toString())) {
                    if (isInternetPresent) {
                        pd.show();
                        intent = new Intent(LoginActivity.this, PlayActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isInternetPresent) {
                    pd.show();
                    intent = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        forgotpass.setOnClickListener(new View.OnClickListener() {
          @Override
            public void onClick(View v) {
              //if (isInternetPresent) {
               //   pd.show();
                  intent = new Intent(LoginActivity.this, ResetActivity.class);
                  startActivity(intent);
                  finish();
              }
          //}
        });
    }
    private boolean validation(String el,String pass) {



         if(el.isEmpty()){
            email.setError("Enter Email");
            email.requestFocus();
            return false;
        }
        else if(!el.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")){
            email.setError("Enter Valid Email");
            email.requestFocus();
            return false;
        }

        else if(pass.isEmpty()){
            passwrd.setError("Enter Password");
            passwrd.requestFocus();
            return false;
        }
        else {
            return true;
        }
    }
}