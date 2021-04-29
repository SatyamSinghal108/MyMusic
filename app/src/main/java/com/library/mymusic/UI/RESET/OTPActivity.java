package com.library.mymusic.UI.RESET;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.library.mymusic.R;
import com.library.mymusic.UI.LOGIN.LoginActivity;
import com.library.mymusic.UTILITY.ConnectionManager;
import com.library.mymusic.UTILITY.TransparentProgressDialog;

import java.net.PasswordAuthentication;

public class OTPActivity extends AppCompatActivity {
    TextView resend;
    EditText n1,n2,n3,n4,n5,n6;
    Button verify;
    private ConnectionManager connectionStatus;
    private Boolean isInternetPresent;
    private TransparentProgressDialog pd;
    private Handler h;
    private Runnable r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p);

        n1 = (EditText)findViewById(R.id.et1);
        n2 = (EditText)findViewById(R.id.et2);
        n3 = (EditText)findViewById(R.id.et3);
        n4 = (EditText)findViewById(R.id.et4);
        n5 = (EditText)findViewById(R.id.et5);
        n6 = (EditText)findViewById(R.id.et6);
        verify = (Button) findViewById(R.id.verify);
        resend = (TextView) findViewById(R.id.send);

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

        //resend.setOnClickListener(v -> {
          //  Toast t=Toast.makeText(OTPActivity.this,"Code Sent",Toast.LENGTH_SHORT);
            //t.show();
        //});

        verify.setOnClickListener(v -> {
            if (isInternetPresent) {
                pd.show();
                Intent intent = new Intent(OTPActivity.this, PasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}