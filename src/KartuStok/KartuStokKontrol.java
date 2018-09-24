/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KartuStok;

import Barang.Barang;
import Barang.Barang_Kontrol;
import Kategori.Kategori;
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
 * @author Awenk
 */
public class KartuStokKontrol {
    
    private Connection conn;
    
    public KartuStokKontrol(Connection koneksi) {
        this.conn = koneksi;
    }
    
    public KartuStokKontrol() {
    }
    
    public static KartuStokKontrol getkoneksikartustok() throws SQLException {
        Koneksi k = new Koneksi();
        KartuStokKontrol kon = new KartuStokKontrol(k.getDBConnection());
        return kon;
    }
    
    public int kodeOtomatisCekStok(String nobukti) throws SQLException{
        ResultSet rset= null;
        String sql="select Substr(nobukti,12) from kartustok where nobukti like "
                +"'"+nobukti+"%' order by nobukti";
        Statement stat= conn.createStatement();
        rset = stat.executeQuery(sql);
        ResultSet rset1=null;
        String sql1="select Substr(max(nobukti),12) from kartustok where nobukti like "
                +"'"+nobukti+"%' order by nobukti";
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
    
    public void tambahkartustok(KartuStok stock) throws SQLException {
        PreparedStatement psmt = null;
        conn.setAutoCommit(false);
        java.util.Date tanggal = stock.getTanggal();
        String sql = "insert into kartustok values(?,?,?,?,?,?)";
        psmt = conn.prepareStatement(sql);
        psmt.setDate(1, new java.sql.Date(tanggal.getTime()));
        psmt.setString(2, stock.getNobukti());
        psmt.setString(3, stock.getKodebarang().getKodebarang());
        psmt.setString(4, stock.getKeterangan());
        psmt.setInt(5, stock.getMasuk());
        psmt.setInt(6, stock.getKeluar());
        psmt.executeUpdate();
        psmt.close();
        conn.commit();
        conn.close();
    }
    
    public void updateKartustok(KartuStok stock) throws SQLException {
        PreparedStatement psmt = null;
        conn.setAutoCommit(false);
        Barang kodebarang = stock.getKodebarang();
        String kode = kodebarang.getKodebarang();
        java.util.Date tanggal = stock.getTanggal();
        String sql = "update kartustok set tanggal =?,kodebarang=?, keterangan=?,masuk=?,keluar=? where nobukti=?";
        psmt = conn.prepareStatement(sql);
        psmt.setDate(1, new java.sql.Date(tanggal.getTime()));
        psmt.setString(2, stock.getKodebarang().getKodebarang());
        psmt.setString(3, stock.getKeterangan());
        psmt.setInt(4, stock.getMasuk());
        psmt.setInt(5, stock.getKeluar());
        psmt.setString(6, stock.getNobukti());
        psmt.executeUpdate();
        psmt.close();
        conn.commit();
        conn.close();
        
    }
    
//    public List<KartuStok> getAllStok(String kode) throws SQLException {
//        PreparedStatement psmt = null;
//        ResultSet rset = null;
//        conn.setAutoCommit(false);
//        String sql = "select * from kartustok where kodebarang like ? order by tanggal, nobukti";
//        psmt = conn.prepareStatement(sql);
//        psmt.setString(1, kode);
//        rset = psmt.executeQuery();
//        List<KartuStok> st = new ArrayList<KartuStok>();
//        int saldostok = 0;
//        while (rset.next()) {
//            KartuStok stok = new KartuStok();
//            stok.setTanggal(rset.getDate(1));
//            stok.setNobukti(rset.getString(2));
//            Barang brg = new Barang();
//            brg.setKodebarang(rset.getString(3));
//            Barang b = Barang_Kontrol.getkoneksibarang().LihatSatuBarang(rset.getString(3));
//            brg.setNamabarang(b.getNamabarang());
//            stok.setKodebarang(brg);
//            stok.setKeterangan(rset.getString(4));
//            stok.setMasuk(rset.getInt(5));
//            stok.setKeluar(rset.getInt(6));
////            stok.setSaldoStok(rset.getInt(5) - rset.getInt(6));
//            saldostok = saldostok+rset.getInt(5);
//            stok.setSaldoStok(saldostok - rset.getInt(6));
//            System.out.println("Saldo Masuk"+rset.getInt(5)+"Saldo Keluar"+rset.getInt(6));
//            st.add(stok);            
//        }
//        psmt.close();
//        conn.commit();
//        conn.close();
//        return st;
//    }   
    
    public List<KartuStok> getAllStok(String kode) throws SQLException {
        PreparedStatement psmt = null;
        ResultSet rset = null;
        conn.setAutoCommit(false);
        String sql = "select * from kartustok where kodebarang like ? order by tanggal, nobukti desc";
        psmt = conn.prepareStatement(sql);
        psmt.setString(1, kode);
        
        rset = psmt.executeQuery();
        List<KartuStok> st = new ArrayList<KartuStok>();
        int saldostok = 0;
        while (rset.next()) {
            KartuStok stok = new KartuStok();
            stok.setTanggal(rset.getDate(1));
            stok.setNobukti(rset.getString(2));
            Barang brg = new Barang();
            brg.setKodebarang(rset.getString(3));
            Barang b = Barang_Kontrol.getkoneksibarang().LihatSatuBarang(rset.getString(3));
            brg.setNamabarang(b.getNamabarang());
            stok.setKodebarang(brg);
            stok.setKeterangan(rset.getString(4));
            stok.setMasuk(rset.getInt(5));
            stok.setKeluar(rset.getInt(6));
            saldostok = saldostok+rset.getInt(5);
            stok.setSaldoStok(saldostok - rset.getInt(6));
            System.out.println("Saldo Masuk"+rset.getInt(5)+"Saldo Keluar"+rset.getInt(6));
            st.add(stok);            
        }
        psmt.close();
        conn.commit();
        conn.close();
        return st;
    }   
    public KartuStok SatuStok(String kode) throws SQLException {
        PreparedStatement psmt = null;
        ResultSet rset = null;
        conn.setAutoCommit(false);
        String sql = "select * from kartustok where nobukti like ?";
        psmt = conn.prepareStatement(sql);
        psmt.setString(1, kode);
        rset = psmt.executeQuery();
        KartuStok stok = new KartuStok();
        int saldostok = 0;
        while (rset.next()) {
            stok.setTanggal(rset.getDate(1));
            stok.setNobukti(rset.getString(2));
            Barang brg = new Barang();
            brg.setKodebarang(rset.getString(3));
            Barang b = Barang_Kontrol.getkoneksibarang().LihatSatuBarang(rset.getString(3));
            brg.setNamabarang(b.getNamabarang());
            stok.setKodebarang(brg);
            stok.setKeterangan(rset.getString(4));
            stok.setMasuk(rset.getInt(5));
            stok.setKeluar(rset.getInt(6));
            stok.setSaldoStok(rset.getInt(5) - rset.getInt(6));
                
        }
        psmt.close();
        conn.commit();
        conn.close();
        return stok;
    }  
   
   
    public void DeleteStok(String nobukti) throws SQLException {//menghapus data kategori
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = "delete from kartustok where nobukti=?";
        Statement stat = conn.prepareStatement(sql);
        psmt = conn.prepareStatement(sql);
        psmt.setString(1, nobukti);
        rset = psmt.executeQuery();
        psmt.close();
        conn.close();
    }
    
    public int KodeOtomatisMasuk() throws SQLException {
        ResultSet rset=null;
        int kode = 0;
        String sql="select substr(max(nobukti),12) from kartustok where nobukti like 'M%'";
        Statement st = conn.createStatement();
        rset = st.executeQuery(sql);
        while(rset.next()){
            kode=rset.getInt(1);
        }
        conn.close();
        return kode;
    }
    
    public int KodeOtomatisKeluar() throws SQLException {
        ResultSet rset = null;
        int kode =0;
        String sql="select substr(max(nobukti),12) from kartustok where nobukti like 'K%'";
        Statement st = conn.createStatement();
        rset = st.executeQuery(sql);
        while(rset.next()){
            kode = rset.getInt(1);
        }
        conn.close();
        return kode;
    }
}
