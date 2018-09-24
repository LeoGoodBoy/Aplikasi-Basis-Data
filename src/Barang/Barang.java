/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Barang;

import Kategori.Kategori;

/**
 *
 * @author basisb24la
 */
public class Barang {
    private String kodebarang;
    private String namabarang;
    private Kategori kategori;
    private String satuan;
    private int qtymin;
    private int qtymax;
    private double ritelSebelum;
    private double ritelSesudah;
    private double beliSebelum;
    private double beliSesudah;
    private String image;

    public Barang() {
    }

    public Barang(String kodebarang, String namabarang, Kategori kategori, String satuan, int qtymin, int qtymax, double ritelSebelum, double ritelSesudah, double beliSebelum, double beliSesudah, String image) {
        this.kodebarang = kodebarang;
        this.namabarang = namabarang;
        this.kategori = kategori;
        this.satuan = satuan;
        this.qtymin = qtymin;
        this.qtymax = qtymax;
        this.ritelSebelum = ritelSebelum;
        this.ritelSesudah = ritelSesudah;
        this.beliSebelum = beliSebelum;
        this.beliSesudah = beliSesudah;
        this.image = image;
    }

//    
//    public Barang(String kodebarang, String namabarang, Kategori kategori) {
//        this.kodebarang = kodebarang;
//        this.namabarang = namabarang;
//        this.kategori = kategori;
//        
//    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getBeliSebelum() {
        return beliSebelum;
    }

    public void setBeliSebelum(double beliSebelum) {
        this.beliSebelum = beliSebelum;
    }

    public double getBeliSesudah() {
        return beliSesudah;
    }

    public void setBeliSesudah(double beliSesudah) {
        this.beliSesudah = beliSesudah;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public String getKodebarang() {
        return kodebarang;
    }

    public void setKodebarang(String kodebarang) {
        this.kodebarang = kodebarang;
    }

    public String getNamabarang() {
        return namabarang;
    }

    public void setNamabarang(String namabarang) {
        this.namabarang = namabarang;
    }

    public int getQtymax() {
        return qtymax;
    }

    public void setQtymax(int qtymax) {
        this.qtymax = qtymax;
    }

    public int getQtymin() {
        return qtymin;
    }

    public void setQtymin(int qtymin) {
        this.qtymin = qtymin;
    }

    public double getRitelSebelum() {
        return ritelSebelum;
    }

    public void setRitelSebelum(double ritelSebelum) {
        this.ritelSebelum = ritelSebelum;
    }

    public double getRitelSesudah() {
        return ritelSesudah;
    }

    public void setRitelSesudah(double ritelSesudah) {
        this.ritelSesudah = ritelSesudah;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }
    
    
}
