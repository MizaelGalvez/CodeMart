package com.mizaelgalvez.market.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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

        setContentView(R.layout.activity_login);//cargar el layout al final para optimizar el rendimiento

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(txtEmail.getText())){
                    Toast.makeText(getApplicationContext(),getString(R.string.txt_falta_email),Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(txtPassword.getText())){
                    Toast.makeText(getApplicationContext(),getString(R.string.txt_falta_contraseña),Toast.LENGTH_LONG).show();
                    return;
                }else {
                    if (txtEmail.getText().length()<6){
                        Toast.makeText(getApplicationContext(),getString(R.string.txt_contraseña_muycorta),Toast.LENGTH_LONG).show();
                        return;
                    }
                }

                progresbar.setVisibility(View.VISIBLE);

                auth.signInWithEmailAndPassword(txtEmail.getText().toString(),txtPassword.getText().toString()).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progresbar.setVisibility(View.GONE);
                        if (!task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),getString(R.string.auth_failed),Toast.LENGTH_LONG).show();
                        }else {
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        }
                    }
                });

            }
        });

        btnSingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
            }
        });




    }
}
