package com.library.mymusic.UI.RESET;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.textfield.TextInputEditText;
import com.library.mymusic.R;
import com.library.mymusic.UI.LOGIN.LoginActivity;
import com.library.mymusic.UI.PLAYLIST.PlayActivity;
import com.library.mymusic.UTILITY.ConnectionManager;
import com.library.mymusic.UTILITY.TransparentProgressDialog;

public class PasswordActivity extends AppCompatActivity {

    TextInputEditText conf,newpass;
    LinearLayout enter;
    Intent intent;
    private ConnectionManager connectionStatus;
    private Boolean isInternetPresent;
    private TransparentProgressDialog pd;
    private Handler h;
    private Runnable r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

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

        enter.setOnClickListener(v -> {
        if (validation(newpass.getText().toString(), conf.getText().toString())) {
            if (isInternetPresent) {
                pd.show();
                intent = new Intent(PasswordActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }

        });

    }

    private boolean validation(String confpass, String pass) {
         if(pass.isEmpty()){
            newpass.setError("Enter Password");
            newpass.requestFocus();
            return false;
        }
        else if(confpass.isEmpty()){
            conf.setError("Enter Confirm Password");
            conf.requestFocus();
            return false;
        }
        else if(!pass.equals(confpass)){
            conf.setError("Password and Confirm Password are not same");
            conf.requestFocus();
            return false;
        }
        else {
            return true;
        }

    }
}