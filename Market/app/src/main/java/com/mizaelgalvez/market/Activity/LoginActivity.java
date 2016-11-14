package com.mizaelgalvez.market.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.mizaelgalvez.market.MainActivity;
import com.mizaelgalvez.market.R;

/**
 * Created by Mizael on 11/11/2016.
 */

public class LoginActivity extends AppCompatActivity {

    EditText txtEmail, txtPassword;
    Button btnlogin, btnReset, btnSingup;
    ProgressBar progresbar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);//cargar el layout al final para optimizar el rendimiento

        txtEmail = (EditText) findViewById(R.id.email);
        txtPassword = (EditText) findViewById(R.id.password);

        btnlogin =  (Button)findViewById(R.id.btn_login);
        btnReset =  (Button)findViewById(R.id.btn_reset_password);
        btnSingup =  (Button)findViewById(R.id.btn_signup);

        progresbar = (ProgressBar) findViewById(R.id.progressBar);

        auth=FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null){

            startActivity(new Intent(LoginActivity.this, MainActivity.class));

        }



    }
}
