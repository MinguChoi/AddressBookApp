package com.example.addressbookapp;

import android.provider.Telephony;

public class Address {
    // Instant Variables
    private String name;
    private String phone;
    private String email;
    private int imageResId;

    // Constructor Declaration of Class
    public Address(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public Address(String name, String phone, String email,int imageResId) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.imageResId = imageResId;
    }

    // Methods
    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public String getEmail(){ return email; }
    public int getIamgeResId() {return imageResId;}

}
