package com.example.cvm;

public class History {
    private String Phone;
    private String Amount;
    private String Date;
    private String CarNumber;
    public History(String phone, String amount, String date,String carNumber) {
        Phone = phone;
        Amount = amount;
        Date = date;
        CarNumber=carNumber;
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

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
    public String getCarNumber() {
        return CarNumber;
    }
    public void setCarNumber(String carNumber){
        CarNumber=carNumber;
    }
}
