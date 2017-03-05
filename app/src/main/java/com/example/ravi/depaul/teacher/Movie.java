package com.example.ravi.depaul.teacher;


public class Movie {
    private String name,photo,quali,exp;

    public Movie() {
    }

    public Movie(String name, String photo, String quali,String exp) {
        this.name=name;
        this.photo=photo;
        this.quali=quali;
        this.exp=exp;
    }

    public void setname(String name) {
        this.name = name;
    }


    public void setphoto(String photo) {
        this.photo = photo;
    }

    public void setquali(String quali) {
        this.quali = quali;
    }

    public void setexp(String exp) {
        this.exp = exp;
    }

    public String getname() {
        return name;
    }
    public String getphoto() {
        return photo;
    }
    public String getquali() {
        return quali;
    }
    public String getexp() {
        return exp;
    }

}