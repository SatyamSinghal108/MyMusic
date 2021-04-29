package com.library.mymusic.UI.REGISTER;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.library.mymusic.R;
import com.library.mymusic.UI.LOGIN.LoginActivity;
import com.library.mymusic.UTILITY.ConnectionManager;
import com.library.mymusic.UTILITY.TransparentProgressDialog;

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText fname,mname,lname,email,phone,passwrd,confpass;
    TextView login;
    LinearLayout sign;
    private ConnectionManager connectionStatus;
    private Boolean isInternetPresent;
    private TransparentProgressDialog pd;
    private Handler h;
    private Runnable r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



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

        fname=findViewById(R.id.First_Name);
        mname=findViewById(R.id.MiddleName);
        lname=findViewById(R.id.lastname);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.cell);
        passwrd=findViewById(R.id.Pass);
        confpass=findViewById(R.id.cnfpass);
        //AppCompatCheckBox terms=findViewById(R.id.signup_tc);
        //AppCompatCheckBox newsletter=findViewById(R.id.signup_newsletter);
        sign=findViewById(R.id.signin_submit);
        login=findViewById(R.id.login_page);
        //google=findViewById(R.id.signup_google);
        //facebook=findViewById(R.id.signup_fb);
        //twitter=findViewById(R.id.signup_twitter);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isInternetPresent) {
                    pd.show();
                Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();}
            }
        });



        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation(fname.getText().toString(),lname.getText().toString(),email.getText().toString(),
                        phone.getText().toString(),passwrd.getText().toString(),confpass.getText().toString())) {

                    if (isInternetPresent) {
                        pd.show();
                        Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(i);
                        finish();

                    }
                }
            }
        });
    }

    private boolean validation(String fn,String ln,String el,String number,String pass,String conf) {

        if(fn.isEmpty()){
            fname.setError("Enter Firstname");
            fname.requestFocus();
            return false;
        }
        else if(ln.isEmpty()){
            lname.setError("Enter Lastname");
            lname.requestFocus();
            return false;
        }
        else if(el.isEmpty()){
            email.setError("Enter Email");
            email.requestFocus();
            return false;
        }
        else if(!el.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")){
            email.setError("Enter Valid Email");
            email.requestFocus();
            return false;
        }
        else if(number.isEmpty()){
            phone.setError("Enter Phone");
            phone.requestFocus();
            return false;
        }
        else if(pass.isEmpty()){
            passwrd.setError("Enter Password");
            passwrd.requestFocus();
            return false;
        }
        else if(conf.isEmpty()){
            confpass.setError("Enter Confirm Password");
            confpass.requestFocus();
            return false;
        }
        else if(!pass.equals(conf)){
            confpass.setError("Password and Confirm Password are not same");
            confpass.requestFocus();
            return false;
        }
        else {
            return true;
        }
    }
}