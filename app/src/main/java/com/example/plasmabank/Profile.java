package com.example.plasmabank;

public class Profile {
    String name,email,phoneno,gender,negdate,lastdonate,bgroup,district,nearhos,endDonate;

    //public Profile(String namestr1, String namestr, String emailstr, String phonenostr, String bgroupstr, String districtstr, String nearhosstr, String s){}  //rterieve korar jonno pore

    public Profile(){};

    public Profile(String name, String email, String phoneno, String gender, String negdate, String lastdonate, String bgroup, String district, String nearhos, String endDonate) {
        this.name = name;
        this.email = email;
        this.phoneno = phoneno;
        this.gender = gender;
        this.negdate = negdate;
        this.lastdonate = lastdonate;
        this.bgroup = bgroup;
        this.district = district;
        this.nearhos = nearhos;
        this.endDonate=endDonate;
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

    public String getNegdate() {
        return negdate;
    }

    public String getLastdonate() {
        return lastdonate;
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

    public String getEndDonate() {return endDonate;}


}
