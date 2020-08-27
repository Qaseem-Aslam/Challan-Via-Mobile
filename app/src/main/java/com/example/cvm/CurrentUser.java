package com.example.cvm;

public class CurrentUser {
    String Phone;
    String Password;

    public CurrentUser(String phone, String password) {
        Phone = phone;
        Password = password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
