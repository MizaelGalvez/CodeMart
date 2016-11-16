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
import com.google.firebase.auth.FirebaseAuth;
import com.mizaelgalvez.market.R;

/**
 * Created by Mizael on 13/11/2016.
 */

public class ResetPasswordActivity extends AppCompatActivity {
    EditText txtEmail;
    Button btnback, btnreset;
    ProgressBar progresbar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reset_password);
        btnback = (Button) findViewById(R.id.btn_back);
        btnreset = (Button) findViewById(R.id.btn_reset_password);
        txtEmail = (EditText) findViewById(R.id.email);
        progresbar = (ProgressBar) findViewById(R.id.progressBar);
        auth = FirebaseAuth.getInstance();

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nEmail = txtEmail.getText().toString();
                if (TextUtils.isEmpty(nEmail)) {
                    Toast.makeText(getApplicationContext(), getString(R.string.txt_falta_email),Toast.LENGTH_LONG).show();
                    return;
                }

                progresbar.setVisibility(View.VISIBLE);

                auth.sendPasswordResetEmail(nEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progresbar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            startActivity(new Intent(ResetPasswordActivity.this, LoginActivity.class));
                            finish();

                        }else {
                            Toast.makeText(getApplicationContext(), getString(R.string.txt_error_envioEmail),Toast.LENGTH_LONG).show();
                        }
                    }
                });


            }
        });



    }
}
