package com.library.mymusic.UI.RESET;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.library.mymusic.R;
import com.library.mymusic.UI.LOGIN.LoginActivity;
import com.library.mymusic.UI.PLAYLIST.PlayActivity;
import com.library.mymusic.UTILITY.ConnectionManager;
import com.library.mymusic.UTILITY.TransparentProgressDialog;

public class ResetActivity extends AppCompatActivity {

    Button send;
    TextInputEditText cell;
    private ConnectionManager connectionStatus;
    private TransparentProgressDialog pd;
    private Handler h;
    private Runnable r;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        connectionStatus =new ConnectionManager(getApplicationContext());
        Boolean isInternetPresent = connectionStatus.isConnectingToInternet();
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

        cell=(TextInputEditText)findViewById(R.id.cellphone);
        send=(Button)findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (validation(cell.getText().toString()))
                    {
                        if (isInternetPresent) {
                            pd.show();
                            Intent intent = new Intent(ResetActivity.this, OTPActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    }
                }
        });
    }
    private boolean validation(String el) {
        if(el.isEmpty()){
            cell.setError("Enter phone number");
            cell.requestFocus();
            return false;
        }
        else {
            return true;
        }
    }

}