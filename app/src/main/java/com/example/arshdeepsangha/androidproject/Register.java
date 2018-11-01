package com.example.arshdeepsangha.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private TextView loginLink;
    private Button btnRegister;
    private EditText etEmailReg;
    private EditText etPasswordReg;
    private EditText etRePasswordReg;

    private String email;
    private String password;
    private String rePassword;

    private ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        progressBar = findViewById(R.id.progressBar);
        loginLink = findViewById(R.id.loginLink);
        etEmailReg = findViewById(R.id.etEmailReg);
        etPasswordReg = findViewById(R.id.etPasswordReg);
        etRePasswordReg = findViewById(R.id.etRePasswordReg);
        btnRegister = findViewById(R.id.btnRegister);
        firebaseAuth = FirebaseAuth.getInstance();

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this,MainActivity.class);
                startActivity(intent);
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assign();
                register();
            }
        });
    }

    private void assign()
    {
        email = etEmailReg.getText().toString();
        password = etPasswordReg.getText().toString();
        rePassword = etRePasswordReg.getText().toString();
    }

    private void register()
    {
        if(email.equals("") || password.equals("") || rePassword.equals(""))
        {
            Toast.makeText(Register.this,"Please enter the required fields",Toast.LENGTH_LONG).show();
        }
        else if(password.length() < 6 || rePassword.length() < 6)
        {
            Toast.makeText(Register.this,"Password must be more than 6 characters",Toast.LENGTH_LONG).show();
        }
        else if(!password.equals(rePassword))
        {
            Toast.makeText(Register.this,"Passwords do not match",Toast.LENGTH_LONG).show();
        }
        else
        {
            progressBar.setVisibility(View.VISIBLE);
            progressBar.isShown();
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful())
                    {
                        firebaseAuth.getCurrentUser().sendEmailVerification()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if(task.isSuccessful()) {
                                            progressBar.setVisibility(View.INVISIBLE);
                                            Toast.makeText(Register.this, "Registration Successful! , We have sent a verfication email. Please verify.", Toast.LENGTH_LONG).show();
                                        }
                                        else
                                        {
                                            progressBar.setVisibility(View.INVISIBLE);
                                            Toast.makeText(Register.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                            etEmailReg.setText("");
                                            etPasswordReg.setText("");
                                            etRePasswordReg.setText("");
                                        }
                                    }
                                });
                    }
                    else
                    {
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(Register.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        etEmailReg.setText("");
                        etPasswordReg.setText("");
                        etRePasswordReg.setText("");
                    }
                }
            });
        }
    }
}
