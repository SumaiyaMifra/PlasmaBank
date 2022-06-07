package com.example.plasmabank;

public class ReceiverModel {
    String name,email,phoneno,negdate,lastdonate,bgroup,district,nearhos,endDonate, gender = "";

    ReceiverModel()
    {

    }


    public ReceiverModel(String name, String email, String phoneno, String negdate, String lastdonate, String bgroup, String district, String nearhos, String endDonate, String gender) {
        this.name = name;
        this.email = email;
        this.phoneno = phoneno;
        this.negdate = negdate;
        this.lastdonate = lastdonate;
        this.bgroup = bgroup;
        this.district = district;
        this.nearhos = nearhos;
        this.gender = gender;
        this.endDonate = endDonate;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public String getphoneno() {
        return phoneno;
    }

    public void setphoneno(String phoneno) {
        this.phoneno = phoneno;
    }



    public String getnegdate() {
        return negdate;
    }

    public void setnegdate(String negdate) {
        this.negdate = negdate;
    }

    public String getlastdonate() {
        return lastdonate;
    }

    public void setlastdonate(String lastdonate) {
        this.lastdonate = lastdonate;
    }

    public String getbgroup() {
        return bgroup;
    }

    public void setbgroup(String bgroup) {
        this.bgroup = bgroup;
    }

    public String getdistrict() {
        return district;
    }

    public void setdistrict(String district) {
        this.district = district;
    }

    public String getnearhos() {
        return nearhos;
    }

    public void setnearhos(String nearhos) {
        this.nearhos = nearhos;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEndDonate() {
        return endDonate;
    }

    public void setEndDonate(String endDonate) {
        this.endDonate = endDonate;
    }
}
