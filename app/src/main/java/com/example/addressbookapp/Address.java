package com.example.addressbookapp;

public class Address {
    // Instant Variables
    String name;
    String phone;
    String email;

    // Constructor Declaration of Class
    public Address(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    // Methods
    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public String getEmail(){
        return email;
    }
}
