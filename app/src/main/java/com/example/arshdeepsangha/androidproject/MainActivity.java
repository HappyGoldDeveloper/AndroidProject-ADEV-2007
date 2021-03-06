package com.example.arshdeepsangha.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private TextView registerLink;

    private EditText etEmailLog;
    private EditText etPasswordLog;
    private String email;
    private String password;
    private Button btnLogin;

    private ProgressBar progressBar2;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        registerLink = findViewById(R.id.registerLink);
        etEmailLog = findViewById(R.id.etEmailLog);
        etPasswordLog = findViewById(R.id.etPasswordLog);
        btnLogin = findViewById(R.id.btnLogin);
        progressBar2 = findViewById(R.id.progressBar2);


        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
                login();
            }
        });
    }

    public void check() {
        email = etEmailLog.getText().toString();
        password = etPasswordLog.getText().toString();
    }

    public void login() {
        if (email.equals("") || password.equals("")) {
            Toast.makeText(MainActivity.this, "Please fill in your credentials", Toast.LENGTH_LONG).show();
        } else {
            progressBar2.setVisibility(View.VISIBLE);
            progressBar2.isShown();
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {

                        if(firebaseAuth.getCurrentUser().isEmailVerified()) {


                            Intent intent = new Intent(MainActivity.this, Home.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            progressBar2.setVisibility(View.INVISIBLE);
                            Toast.makeText(MainActivity.this, "Please verify your email!", Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        progressBar2.setVisibility(View.INVISIBLE);
                        Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
