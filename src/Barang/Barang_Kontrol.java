/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Barang;

import Kategori.Kategori;
import Kategori.Kategori_kontrol;
import Koneksi.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author basisb24la
 */
public class Barang_Kontrol {
       private Connection conn;

    public Barang_Kontrol(Connection koneksi) {
        this.conn = koneksi;
    }

    public Barang_Kontrol() {
    }

    public static Barang_Kontrol getkoneksibarang() throws SQLException {//membuat koneksi dikontrol dengan memanggil kelas koneksi
        Koneksi k = new Koneksi();
        Barang_Kontrol kon = new Barang_Kontrol(k.getDBConnection());
        return kon;
    }
    
    public int kodeOtomatis(String kodekategori) throws SQLException{
        ResultSet rset= null;
        int kode=0;
        String sql="select Substr(max(kodebarang),4) from barang where kodebarang like "
                +"'"+kodekategori+"%' order by kodebarang";
        Statement stat= conn.createStatement();
        rset = stat.executeQuery(sql);
        while(rset.next()){
            kode=rset.getInt(1);
        }
        stat.close();
        conn.close();
        return kode;
    }
    
    
    
    public int kodeOtomatisCekBarang(String kodebarang) throws SQLException{
        ResultSet rset= null;
        String sql="select Substr(kodebarang,4) from barang where kodebarang like "
                +"'"+kodebarang+"%' order by kodebarang";
        Statement stat= conn.createStatement();
        rset = stat.executeQuery(sql);
        ResultSet rset1=null;
        String sql1="select Substr(max(kodebarang),4) from barang where kodebarang like "
                +"'"+kodebarang+"%' order by kodebarang";
        Statement stat1= conn.createStatement();
        rset1 = stat1.executeQuery(sql1);
        int kode=0;
        int bantu=1;
        boolean x=false;
        while(rset.next()){
            System.out.println(bantu+" = "+rset.getInt(1));
            if (bantu != rset.getInt(1)) {
                kode= bantu-1;
                x=true;
                break;
            }
            bantu++;
        }
        if (x==false) {
            kode=0;
            while(rset1.next()){
                kode=rset1.getInt(1);
            }
        }
        stat.close();
        conn.close();
        return kode;
    }
    
    public void tambahBarang(Barang bar) throws SQLException {      
            conn.setAutoCommit(false);
            String sql = "insert into barang values (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, bar.getKodebarang());
            statement.setString(2, bar.getNamabarang());
            statement.setString(3, bar.getKategori().getKode());
            statement.setString(4, bar.getSatuan());
            statement.setInt(5, bar.getQtymin());
            statement.setInt(6, bar.getQtymax());
            statement.setDouble(7, bar.getRitelSebelum());
            statement.setDouble(8, bar.getRitelSesudah());
            statement.setDouble(9, bar.getBeliSebelum());
            statement.setDouble(10, bar.getBeliSesudah());
            statement.setString(11, bar.getImage());
            statement.executeUpdate();
            statement.close();
            conn.commit();
            conn.close();       
    }
    public void HapusBarang(String kode) throws SQLException {
        conn.setAutoCommit(false);
        String sql = "delete from barang where kodebarang = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, kode);
        statement.executeUpdate();
        statement.close();
        conn.commit();
        conn.close();
    }
    
    public void updateBarang(Barang bar) throws SQLException {
        conn.setAutoCommit(false);
        String sql = "update barang set namabarang =?, kodekategori=?, satuan=?, qtymin=?,qtymax=?,"
                + "ritelsetelah=?, ritelsebelum=?,belisetelah=?,belisebelum=? where kodebarang=?";
        PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(10, bar.getKodebarang());
            statement.setString(1, bar.getNamabarang());
            statement.setString(2, bar.getKategori().getKode());
            statement.setString(3, bar.getSatuan());
            statement.setInt(4, bar.getQtymin());
            statement.setInt(5, bar.getQtymax());
            statement.setDouble(6, bar.getRitelSebelum());
            statement.setDouble(7, bar.getRitelSesudah());
            statement.setDouble(8, bar.getBeliSebelum());
            statement.setDouble(9, bar.getBeliSesudah());
        statement.executeUpdate();
        statement.close();
        conn.commit();
        conn.close();
    }
    
     public List<Barang> LihatSemuaBarang() throws SQLException {
        conn.setAutoCommit(false);
        String sql = "select * from barang order by kodebarang";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        //Simpan Seluruh isi kategori ke dalam sebuah LIST
        List<Barang> b = new ArrayList<Barang>();
        while (result.next()) {
            Barang br = new Barang();
            br.setKodebarang(result.getString(1));
            br.setNamabarang(result.getString(2));
            Kategori kat = new Kategori();
            kat.setKode(result.getString(3));
            br.setKategori(kat);
            br.setSatuan(result.getString(4));
            br.setQtymin(result.getInt(5));
            br.setQtymax(result.getInt(6));
            br.setRitelSesudah(result.getDouble(7));
            br.setRitelSebelum(result.getDouble(8));
            br.setBeliSesudah(result.getDouble(9));
            br.setBeliSebelum(result.getDouble(10));
            b.add(br);
        }
         
        statement.close();
        conn.commit();
        conn.close();
        return b;
    }
    
     public Barang LihatSatuBarang(String kode) throws SQLException {
        conn.setAutoCommit(false);
        String sql = "select * from barang where kodebarang=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, kode);
        ResultSet result = statement.executeQuery();
        //Simpan Seluruh isi kategori ke dalam sebuah LIST
        Barang br = new Barang();
        while (result.next()) {
            
            br.setKodebarang(result.getString(1));
            br.setNamabarang(result.getString(2));
            Kategori kat = new Kategori();
            kat.setKode(result.getString(3));
            br.setKategori(kat);
            br.setSatuan(result.getString(4));
            br.setQtymin(result.getInt(5));
            br.setQtymax(result.getInt(6));
            br.setRitelSesudah(result.getDouble(7));
            br.setRitelSebelum(result.getDouble(8));
            br.setBeliSesudah(result.getDouble(9));
            br.setBeliSebelum(result.getDouble(10));
        }
         
        statement.close();
        conn.commit();
        conn.close();
        return br;
    }
}
