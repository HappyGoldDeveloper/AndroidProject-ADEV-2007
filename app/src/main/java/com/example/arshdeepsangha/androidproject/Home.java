package com.example.arshdeepsangha.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    private Button btnPostAd;
    private RecyclerView adView;
    private ArrayList<Ad> ads = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnPostAd = findViewById(R.id.btnPostAd);
        adView = findViewById(R.id.adView);


        btnPostAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,PostAdd.class);
                startActivity(intent);
            }
        });
    }
}
