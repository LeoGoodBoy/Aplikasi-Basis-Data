/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Laporan;

import Barang.Barang;
import KartuStok.KartuStok;
import Koneksi.Koneksi;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author basisb09
 */
public class LaporanStokKontrol {
    private Connection conn;
    
    public LaporanStokKontrol(Connection koneksi) {
        this.conn = koneksi;
    }
    
    public LaporanStokKontrol() {
    }
    
    public static LaporanStokKontrol getkoneksilaporanstok() throws SQLException {
        Koneksi k = new Koneksi();
        LaporanStokKontrol kon = new LaporanStokKontrol(k.getDBConnection());
        return kon;
    }
    
//    public List<KartuStok> getAllLaporanStok(Date tgl1,Date tgl2) throws SQLException {
//        PreparedStatement psmt = null;
//        ResultSet rset = null;
//        conn.setAutoCommit(false);
//        String sql = "select k.kodebarang, b.namabarang, SUM(k.masuk-k.keluar), b.belisetelah"
//                + "from kartustok k LEFT JOIN barang ON k.kodebarang=b.kodebarang"
//                + "where b.kodebarang IN( select kodebarang from kartustok where tanggal BETWEEN ? AND ? )"
//                + "group by k.kodebarang, b.namabarang,b.belisetelah";
//        psmt = conn.prepareStatement(sql);
//        psmt.setDate(1, new java.sql.Date(tgl1.getTime()));
//        psmt.setDate(2, new java.sql.Date(tgl2.getTime()));
//        rset = psmt.executeQuery();
//        List<KartuStok> st = new ArrayList<KartuStok>();
//        int saldostok = 0;
//        while (rset.next()) {
//            KartuStok stok = new KartuStok();
//            Barang brg = new Barang();
//            brg.setKodebarang(rset.getString(1));
//            brg.setNamabarang(rset.getString(2));
//            brg.setBeliSesudah(rset.getDouble(4));
//            stok.setKodebarang(brg);
//            stok.setSaldoStok(rset.getInt(3));
//            stok.setNilaiPersedian(rset.getInt(3)*rset.getInt(4));
//            st.add(stok);            
//        }
//        conn.commit();
//        conn.close();
//        return st;
//    }

    public List<KartuStok> getAllLaporanStok(Date tgl1,Date tgl2) throws SQLException {
        PreparedStatement psmt = null;
        ResultSet rset = null;
        conn.setAutoCommit(false);
        String sql = "select k.kodebarang, b.namabarang, SUM(k.masuk-k.keluar), b.belisetelah from kartustok k LEFT JOIN barang b ON k.kodebarang=b.kodebarang where b.kodebarang IN( select kodebarang from kartustok where tanggal BETWEEN ? AND ?) group by k.kodebarang, b.namabarang,b.belisetelah";
        psmt = conn.prepareStatement(sql);
        psmt.setDate(1, new java.sql.Date(tgl1.getTime()));
        psmt.setDate(2, new java.sql.Date(tgl2.getTime()));
        rset = psmt.executeQuery();
        List<KartuStok> st = new ArrayList<KartuStok>();
        int saldostok = 0;
        while (rset.next()) {
            KartuStok stok = new KartuStok();
            Barang brg = new Barang();
            brg.setKodebarang(rset.getString(1));
            brg.setNamabarang(rset.getString(2));
            brg.setBeliSesudah(rset.getDouble(4));
            stok.setKodebarang(brg);
            stok.setSaldoStok(rset.getInt(3));
            stok.setNilaiPersedian(rset.getInt(3)*rset.getInt(4));
            st.add(stok);            
        }
        conn.commit();
        conn.close();
        return st;
    }

    
}
