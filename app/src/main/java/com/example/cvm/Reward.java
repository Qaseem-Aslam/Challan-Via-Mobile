package com.example.cvm;

public class Reward {
    public String Phone;
    public String Amount;
    public String Status;

    public Reward(String phone, String amount, String status) {
        Phone = phone;
        Amount = amount;
        Status = status;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
