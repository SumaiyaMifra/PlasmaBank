package com.example.plasmabank;

public class ProfileReceiver {

    String name,email,phoneno,gender,bgroup,district,nearhos;

    public ProfileReceiver(){}

    public ProfileReceiver(String name, String email, String phoneno, String gender, String bgroup, String district, String nearhos) {
        this.name = name;
        this.email = email;
        this.phoneno = phoneno;
        this.gender = gender;
        this.bgroup = bgroup;
        this.district = district;
        this.nearhos = nearhos;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public String getGender() {
        return gender;
    }

    public String getBgroup() {
        return bgroup;
    }

    public String getDistrict() {
        return district;
    }

    public String getNearhos() {
        return nearhos;
    }
}

