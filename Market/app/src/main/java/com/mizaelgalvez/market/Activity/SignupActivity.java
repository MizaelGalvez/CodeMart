package com.mizaelgalvez.market.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
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
import com.mizaelgalvez.market.R;

/**
 * Created by Mizael on 08/11/2016.
 */

public class SignupActivity extends AppCompatActivity {

    private EditText txtEmail, txtPassword;
    private Button btnSignup, btnSignin, btnResetpass;
    private ProgressBar progressBar;
    private FirebaseAuth auth;


    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        txtEmail=(EditText) findViewById(R.id.txtEmail);
        txtPassword=(EditText) findViewById(R.id.txtPassword);
        btnSignin=(Button) findViewById(R.id.btnIngresar);
        btnSignup=(Button) findViewById(R.id.btnSingn);
        btnResetpass=(Button) findViewById(R.id.btnResetpass);
        auth=FirebaseAuth.getInstance();

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Programar el evento de ingreso

                if (TextUtils.isEmpty(txtEmail.getText())) {
                    Toast.makeText(getBaseContext(), "el email es obligatorio", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(txtPassword.getText())) {
                    Toast.makeText(getBaseContext(), "el password es muy obligatorio", Toast.LENGTH_LONG).show();
                    return;
                }
                if (txtPassword.length()<6) {
                    Toast.makeText(getBaseContext(), "el la contraseña debe ser mayor a 6 digitos", Toast.LENGTH_LONG).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE); //mostramos en control de ProgressBAR.
                auth.createUserWithEmailAndPassword(txtEmail.getText().toString(), txtPassword.getText().toString()).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(SignupActivity.this,"Creaando la Cuenta", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                        if (!task.isSuccessful()) {
                            Toast.makeText(SignupActivity.this,"Error al crear la Cuenta", Toast.LENGTH_LONG).show();
                        }else{
                            //TODO: terminar el codigo para enviar a pantalla de inicio
                            //startActivity(new Intent(this, finish();));
                        }
                    }
                });
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Programar el evento de registro
            }
        });

        btnResetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:nprogramar el evento de resetear contraseña
            }
        });
    }
}
