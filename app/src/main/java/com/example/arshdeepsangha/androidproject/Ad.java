package com.example.arshdeepsangha.androidproject;

public class Ad {

    private String address;
    private String residence;
    private int Occupancy;
    private int MaximumOccupany;
    private double rent;
    private String phone;
    private String user;

    public Ad()
    {}

    public Ad(String address, String residence, int occupancy, int maximumOccupany, double rent, String phone,String user) {
        this.address = address;
        this.residence = residence;
        Occupancy = occupancy;
        MaximumOccupany = maximumOccupany;
        this.rent = rent;
        this.phone = phone;
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public String getResidence() {
        return residence;
    }

    public int getOccupancy() {
        return Occupancy;
    }

    public int getMaximumOccupany() {
        return MaximumOccupany;
    }

    public double getRent() {
        return rent;
    }

    public String getPhone() {
        return phone;
    }

    public String getUser() {
        return user;
    }
}
