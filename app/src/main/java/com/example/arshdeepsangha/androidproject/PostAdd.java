package com.example.arshdeepsangha.androidproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class PostAdd extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText etAddress;
    private Spinner spinnerResidence;
    private EditText etOcc;
    private EditText etMaxOcc;
    private EditText etRent;
    private EditText etPhone;

    private String address;
    private String residence;
    private String occ;
    private String maxOcc;
    private String rent;
    private String phone;
    private String user;

    private Button btnPost;

    private FirebaseAuth firebaseAuth;

    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_add);

        etAddress = findViewById(R.id.etAddress);

        db = FirebaseFirestore.getInstance();

        firebaseAuth = FirebaseAuth.getInstance();

        user = firebaseAuth.getCurrentUser().getEmail();

        spinnerResidence = findViewById(R.id.spinnerResidence);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.residence, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerResidence.setAdapter(adapter);
        spinnerResidence.setOnItemSelectedListener(this);


        etOcc = findViewById(R.id.etOcc);
        etMaxOcc = findViewById(R.id.etMaxOcc);
        etRent = findViewById(R.id.etRent);
        etPhone = findViewById(R.id.etPhone);

        btnPost = findViewById(R.id.btnPost);

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getValues();

            }
        });


    }

    private void getValues()
    {
        address = etAddress.getText().toString();
        occ = etOcc.getText().toString();
        maxOcc = etMaxOcc.getText().toString();
        rent = etRent.getText().toString();
        phone= etPhone.getText().toString();

        if(address.equals("") || occ.equals("") || maxOcc.equals("") || rent.equals("") || phone.equals(""))
        {
            Toast.makeText(PostAdd.this,"Please fill in all fields to Post Ad",Toast.LENGTH_LONG).show();
        }

        if(phone.length() > 10)
        {
            Toast.makeText(PostAdd.this,"Please enter a Valid Phone Number",Toast.LENGTH_LONG).show();
        }
    }

    private void saveAdd()
    {
        CollectionReference dbAd = db.collection("ad");

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(parent.getId() == R.id.spinnerResidence)
        {
            residence = spinnerResidence.getSelectedItem().toString();
            Log.d("Arsh",residence);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
