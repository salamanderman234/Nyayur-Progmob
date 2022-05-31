package com.example.nyayur;

public class TransactionModel {
    String tanggal, status;
    int total;

    public String getTanggal() {
        return tanggal;
    }

    public String getStatus() {
        return status;
    }

    public int getTotal() {
        return total;
    }

    public TransactionModel(String tanggal, String status, int total){
        this.tanggal = tanggal;
        this.status = status;
        this.total = total;
    }
}
