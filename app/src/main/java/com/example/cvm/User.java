package com.example.cvm;

public class User {
private String Name;
private String phone;
private String Password;
private String Email;
    public User(String name, String phone, String password,String email) {
        Name = name;
        this.phone = phone;
        Password = password;
        Email=email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail(){return Email;}

    public void setEmail(String email){this.Email=email;}
}
