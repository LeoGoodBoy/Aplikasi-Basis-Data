/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kategori;
import Koneksi.Koneksi;
import Koneksi.Koneksi;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author basisb06
 */
public class Kategori_kontrol {
    private Connection conn;

    public Kategori_kontrol(Connection koneksi) {
        this.conn = koneksi;
    }

    public Kategori_kontrol() {
    }

    public static Kategori_kontrol getkoneksikategori() throws SQLException {//membuat koneksi dikontrol dengan memanggil kelas koneksi
        Koneksi k = new Koneksi();
        Kategori_kontrol kon = new Kategori_kontrol(k.getDBConnection());
        return kon;
    }
    
    //Method Tambah Kategori Prepared
    public void tambahKategori(Kategori kat) throws SQLException {      
            conn.setAutoCommit(false);
            String sql = "insert into kategori values (?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, kat.getKode());
            statement.setString(2, kat.getNama());
            statement.executeUpdate();
            statement.close();
            conn.commit();
            conn.close();       
    }
    //method untuk meng-update nama kategori Prepared
    public void updateKategori(Kategori kat) throws SQLException {
        conn.setAutoCommit(false);
        String sql = "update kategori set namakategori =? where kodekategori =?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, kat.getNama());
        statement.setString(2, kat.getKode());
        statement.executeUpdate();
        statement.close();
        conn.commit();
        conn.close();
    }
  //method untuk menghapus kategori berdasarkan Kode Kategori
    public void HapusKategori(String kode) throws SQLException {
        conn.setAutoCommit(false);
        String sql = "delete from kategori where kodekategori = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, kode);
        statement.executeUpdate();
        statement.close();
        conn.commit();
        conn.close();
    }
    //Method untuk melihat seluruh Kategori
    public List<Kategori> LihatSemuaKategori() throws SQLException {
        conn.setAutoCommit(false);
        String sql = "select * from kategori order by kodekategori";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        //Simpan Seluruh isi kategori ke dalam sebuah LIST
        List<Kategori> kat = new ArrayList<Kategori>();
        while (result.next()) {
            Kategori kategoribaru = new Kategori();
            kategoribaru.setKode(result.getString(1));
            kategoribaru.setNama(result.getString(2));
            kat.add(kategoribaru);
        }
        statement.close();
        conn.commit();
        conn.close();
        return kat;
    }
    //Method untuk melihat satu Kategori
    public Kategori CariKategori(String kode) throws SQLException {
        conn.setAutoCommit(false);
        String sql = "select * from kategori where kodekategori=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, kode);
        ResultSet result = statement.executeQuery();
        Kategori kat = new Kategori();
        while (result.next()) {
            kat.setKode(result.getString(1));
            kat.setNama(result.getString(2));
        }
        statement.close();
        conn.commit();
        conn.close();
        return kat;
    }
    
    public boolean CariKode(String kode) throws SQLException {
        conn.setAutoCommit(false);
        String sql = "select * from kategori where kodekategori=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, kode);
        ResultSet result = statement.executeQuery();
        boolean bantu=false;
        while (result.next()) {
            if (kode.equals(result.getString(1))) {
                bantu=true;
            }
        }
        statement.close();
        conn.commit();
        conn.close();
        return bantu;
    }
    
    
}
