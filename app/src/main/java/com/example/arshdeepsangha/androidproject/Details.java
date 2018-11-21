package com.example.arshdeepsangha.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    private TextView detailAddress,detailResidence,detailOcc,detailMax,detailRent,detailPhone,detailUser;
    private String address;
    private String residence;
    private int occupancy;
    private int maximumOccupany;
    private double rent2;
    private String phone;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        detailAddress = findViewById(R.id.detailAddress);
        detailResidence = findViewById(R.id.detailResidence);
        detailOcc = findViewById(R.id.detailOcc);
        detailMax = findViewById(R.id.detailMax);
        detailRent = findViewById(R.id.detailRent);
        detailPhone = findViewById(R.id.detailPhone);
        detailUser = findViewById(R.id.detailUser);

        Intent intent = getIntent();

        address = intent.getStringExtra("address").toString();
        residence = intent.getStringExtra("residence");
        maximumOccupany = intent.getIntExtra("max",0);
        occupancy = intent.getIntExtra("current",0);
        rent2 = intent.getDoubleExtra("rent",0);
        phone = intent.getStringExtra("phone");
        user = intent.getStringExtra("user");


        detailAddress.setText(address);
        detailResidence.setText(residence);
        detailOcc.setText(String.valueOf(maximumOccupany));
        detailMax.setText(String.valueOf(occupancy));
        detailRent.setText("$" + String.format("%,.2f", rent2));
        detailPhone.setText(phone.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1)-$2-$3"));
        detailUser.setText(user);

    }
}
