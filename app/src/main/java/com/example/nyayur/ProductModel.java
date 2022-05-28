package com.example.nyayur;

public class ProductModel {
    String thumbnail, nama, pembelian, kondisi, deskripsi, stock;
    int harga;

    public String getThumbnail() {
        return thumbnail;
    }

    public String getNama() {
        return nama;
    }

    public String getPembelian() {
        return pembelian;
    }

    public String getKondisi() {
        return kondisi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public int getHarga() {
        return harga;
    }

    public String getStock() {
        return stock;
    }

    public ProductModel(String thumbnail, String nama, String pembelian, String kondisi, String deskripsi, int harga, String stock) {
        this.thumbnail = thumbnail;
        this.nama = nama;
        this.pembelian = pembelian;
        this.kondisi = kondisi;
        this.deskripsi = deskripsi;
        this.harga = harga;
        this.stock = stock;
    }
}
