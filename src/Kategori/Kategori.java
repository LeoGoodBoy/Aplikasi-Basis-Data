/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kategori;

/**
 *
 * @author basisb06
 */
public class Kategori {
    private String kode;
    private String nama;

    public Kategori() {
    }

    public Kategori(String kode, String nama) {
        this.kode = kode;
        this.nama = nama;
    }
    
    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
    
    
}
