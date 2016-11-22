package com.mizaelgalvez.market;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mizaelgalvez.market.Activity.LoginActivity;
import com.mizaelgalvez.market.Activity.SignupActivity;
import com.mizaelgalvez.market.R;


public class MainActivity extends AppCompatActivity {

    private Button btnChangePassword, changepassword, btnChangeEmail, changeemail, btnSendResetEmail, sendresetemail, btnRemoveruser, removeuser, sigOut;
    private EditText oldEmail, newEmail, password, newpassword;
    private ProgressBar progresbar;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        auth=FirebaseAuth.getInstance();
        final FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        authListener =new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if (user==null){

                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };

        btnChangeEmail = (Button) findViewById(R.id.change_email_button);
        btnChangePassword = (Button) findViewById(R.id.change_password_button);
        btnSendResetEmail = (Button)findViewById(R.id.sending_pass_reset_button);
        btnRemoveruser = (Button) findViewById(R.id.remove_user_button);
        changeemail = (Button) findViewById(R.id.changeEmail);
        changepassword = (Button) findViewById(R.id.changePass);
        sendresetemail = (Button)findViewById(R.id.send);
        removeuser = (Button) findViewById(R.id.remove);
        sigOut = (Button) findViewById(R.id.sign_out);

        oldEmail = (EditText) findViewById(R.id.old_email);
        newEmail = (EditText) findViewById(R.id.new_email);
        password = (EditText) findViewById(R.id.password);
        newpassword = (EditText) findViewById(R.id.newPassword);

        progresbar = (ProgressBar) findViewById(R.id.progressBar);

        oldEmail.setVisibility(View.GONE);
        newEmail.setVisibility(View.GONE);
        password.setVisibility(View.GONE);
        newpassword.setVisibility(View.GONE);
        changeemail.setVisibility(View.GONE);
        changepassword.setVisibility(View.GONE);
        sendresetemail.setVisibility(View.GONE);
        removeuser.setVisibility(View.GONE);

        if (progresbar!=null){

            progresbar.setVisibility(View.GONE);

        }

        sigOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Salir();
            }
        });
        btnRemoveruser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progresbar.setVisibility(View.VISIBLE);
                if (user!=null){
                    user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Perfil Eliminado", Toast.LENGTH_LONG);
                                startActivity(new Intent(MainActivity.this, SignupActivity.class));
                                finish();
                                progresbar.setVisibility(View.GONE);

                            }else{
                                Toast.makeText(getApplicationContext(),"Error al iliminar al usario", Toast.LENGTH_LONG);
                                progresbar.setVisibility(View.GONE);

                            }


                        }
                    });
                }


            }
        });













    }
    void Salir(){
        auth.signOut();
    }
}
