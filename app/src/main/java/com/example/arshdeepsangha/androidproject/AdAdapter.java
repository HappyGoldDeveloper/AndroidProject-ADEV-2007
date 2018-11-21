package com.example.arshdeepsangha.androidproject;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdAdapter extends RecyclerView.Adapter<AdAdapter.AdViewHolder> {


    private Context mCtx;
    private List<Ad> adList;

    private String address;
    private String residence;
    private int occupancy;
    private int maximumOccupany;
    private double rent2;
    private String phone;
    private String user;

    public AdAdapter(Context mCtx , List<Ad> adList)
    {
        this.mCtx = mCtx;
        this.adList = adList;
    }

    @NonNull
    @Override
    public AdViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new AdViewHolder(
                LayoutInflater.from(mCtx).inflate(R.layout.layout_add,viewGroup,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull AdViewHolder adViewHolder, int i) {
        Ad ad = adList.get(i);


        adViewHolder.addressText.setText("Address : " + ad.getAddress());
        address = ad.getAddress().toString();
        int max = ad.getMaximumOccupany();
        maximumOccupany = ad.getMaximumOccupany();
        int current = ad.getOccupancy();
        occupancy = ad.getOccupancy();
        adViewHolder.maxText.setText("Max Occupants : " + String.valueOf(max));

        adViewHolder.occText.setText("Current Occupants : " + String.valueOf(current));
        double rent = ad.getRent();
        rent2 = ad.getRent();
        adViewHolder.rentText.setText("Rent : $" + String.format("%,.2f", rent));
        adViewHolder.houseTypeText.setText("Residence Type : " + ad.getResidence());
        residence = ad.getResidence();

        phone = ad.getPhone();
        user = ad.getUser();
        adViewHolder.phoneText.setText("Phone : " + ad.getPhone().replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1)-$2-$3"));



        adViewHolder.viewAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx,Details.class);
                intent.putExtra("address",address);
                intent.putExtra("max",maximumOccupany);
                intent.putExtra("current",occupancy);
                intent.putExtra("rent",rent2);
                intent.putExtra("residence",residence);
                intent.putExtra("phone",phone);
                intent.putExtra("user",user);
                mCtx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return adList.size();
    }

    class AdViewHolder extends RecyclerView.ViewHolder {

        TextView addressText ,maxText , occText , phoneText , rentText ,houseTypeText , viewAd;

        public AdViewHolder(View itemView)
        {
            super(itemView);

            addressText = itemView.findViewById(R.id.addressText);
            maxText = itemView.findViewById(R.id.maxText);
            occText = itemView.findViewById(R.id.occText);
            phoneText = itemView.findViewById(R.id.phoneText);
            rentText = itemView.findViewById(R.id.rentText);
            houseTypeText = itemView.findViewById(R.id.houseTypeText);
            viewAd = itemView.findViewById(R.id.viewAd);
        }


    }
}
