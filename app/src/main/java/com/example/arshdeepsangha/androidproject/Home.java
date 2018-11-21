package com.example.arshdeepsangha.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    private Button btnPostAd;
    private RecyclerView adView;
    private AdAdapter adAdapter;
    private ArrayList<Ad> ads;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnPostAd = findViewById(R.id.btnPostAd);
        adView = findViewById(R.id.adView);
        adView.setHasFixedSize(true);
        adView.hasFixedSize();
        adView.setLayoutManager(new LinearLayoutManager(this));

        ads = new ArrayList<>();
        adAdapter = new AdAdapter(this,ads);

        adView.setAdapter(adAdapter);

        db = FirebaseFirestore.getInstance();

        btnPostAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,PostAdd.class);
                startActivity(intent);
            }
        });
    }

    private void loadData()
    {
        db.collection("ads").orderBy("id", Query.Direction.DESCENDING).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                if(!queryDocumentSnapshots.isEmpty())
                {
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                    for (DocumentSnapshot d : list){

                        Ad adObject = d.toObject(Ad.class);
                        ads.add(adObject);
                    }

                    adAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadData();

    }
}
