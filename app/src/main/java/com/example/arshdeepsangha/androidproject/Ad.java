package com.example.arshdeepsangha.androidproject;

public class Ad {

    private int id;
    private String address;
    private String residence;
    private int occupancy;
    private int maximumOccupany;
    private double rent;
    private String phone;
    private String user;

    public Ad()
    {}

    public Ad(int id , String address, String residence, int occupancy, int maximumOccupany, double rent, String phone,String user) {
        this.id = id;
        this.address = address;
        this.residence = residence;
        this.occupancy = occupancy;
        this.maximumOccupany = maximumOccupany;
        this.rent = rent;
        this.phone = phone;
        this.user = user;
    }

    public int getId() {return id;}

    public String getAddress() {
        return address;
    }

    public String getResidence() {
        return residence;
    }

    public int getOccupancy() {
        return occupancy;
    }

    public int getMaximumOccupany() { return maximumOccupany; }

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
