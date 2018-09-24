/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Koneksi;


import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.pool.OracleDataSource;

/**
 *
 * @author basisc20
 */
public class Koneksi {
//    ini kalau offline
    String jdbcUrl="jdbc:oracle:thin:@localhost:1521:xe";
    String userid = "hr";
    String password = "admin";
    
//    ini kalau online
//    String jdbcUrl="jdbc:oracle:thin:@172.23.9.185:1521:ORCL";
//    String userid = "mhs135314040";
//    String password = "mhs135314040";
//    String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
//    String userid = "hr";
//    String password = "097LEO";
    Connection conn;
    
    public static void main(String[] args) {
        try {
            Koneksi k=new Koneksi();
            k.getDBConnection();
            
            //menggunakan statement dan ribet kalau perlu banyak parameter
//            k.simpanKategoriStatement();
//            k.updateKategoriStatement();
//            k.hapusKategoriStatement();
            
            //menggunakan prepared statemnt
//            k.simpanKategoriPreparedStatement();
//            k.updateKategoriPreparedStatement();
//            k.hapusKategoriPreparedStatement();
            
            //cari
            System.out.println("Nama kategori : "+k.cariKategori("002"));
            
        } catch (SQLException ex) {
            Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Koneksi() {
    }
    
    public Connection getDBConnection(){
        try {
            OracleDataSource ds=new OracleDataSource();
            ds.setURL(jdbcUrl);
            conn = ds.getConnection(userid,password);
            System.out.println("Koneksi berhasil");
        } catch (Exception e) {
            System.out.println("Gagal koneksi");
        }
        return conn;
    }
    
    public void TampilEmployees(String eid){
        try {
            String sql = "select * from employees where employee_id="+eid;
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            
            while(rs.next()){
                System.out.println("ID : "+rs.getString(1)+
                        ", First Name : " +rs.getString(2)+
                        ", Salary : "+rs.getString(8) );
            }
            stat.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void simpanKategoriStatement() throws SQLException{
        String kodekategori = "001";
        String namakategori ="Makanan";
        
        conn.setAutoCommit(false);
        String sql="insert into kategori (kodekategori,namakategori) values('"+kodekategori+"','"+namakategori+"')";
        Statement stat= conn.createStatement();
        stat.executeUpdate(sql);
        stat.close();
        conn.commit();
        conn.close();
        System.out.println("Data berhasil ditambahkan");
    }
    
    public void updateKategoriStatement()throws SQLException{
        String kodekategori = "001";
        String namakategori ="Peralatan Dapur";
        
        conn.setAutoCommit(false);
        String sql="update kategori set namakategori='"+namakategori+"' where kodekategori='"+kodekategori+"'";
        Statement stat= conn.createStatement();
        stat.executeUpdate(sql);
        stat.close();
        conn.commit();
        conn.close();
        System.out.println("Data berhasil diubah");
    }
    
    public void hapusKategoriStatement()throws SQLException{
        String kodekategori = "001";
        String namakategori ="Minuman";
        
        conn.setAutoCommit(false);
        String sql="delete from kategori where kodekategori='"+kodekategori+"'";
        Statement stat= conn.createStatement();
        stat.executeUpdate(sql);
        stat.close();
        conn.commit();
        conn.close();
        System.out.println("Data berhasil dihapus");
    }
    
    public void simpanKategoriPreparedStatement() throws SQLException{
        String kodekategori = "002";
        String namakategori ="Detergen";
        
        conn.setAutoCommit(false);
        String sql="insert into kategori (kodekategori,namakategori) values(?,?)";
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setString(1, kodekategori);
        stat.setString(2, namakategori);
        stat.executeUpdate();
        stat.close();
        conn.commit();
        conn.close();
        System.out.println("Data berhasil ditambahkan");
    }
    public void updateKategoriPreparedStatement()throws SQLException{
        String kodekategori = "002";
        String namakategori ="Pakaian";
        
        conn.setAutoCommit(false);
        String sql="update kategori set namakategori=? where kodekategori=?";
//        Statement stat= conn.createStatement();
        PreparedStatement stat=conn.prepareStatement(sql);
        stat.setString(1, namakategori);
        stat.setString(2, kodekategori);
        stat.executeUpdate();
        stat.close();
        conn.commit();
        conn.close();
        System.out.println("Data berhasil diubah");
    }
    
    public void hapusKategoriPreparedStatement()throws SQLException{
        String kodekategori = "002";
        
        conn.setAutoCommit(false);
        String sql="delete from kategori where kodekategori=?";
//        Statement stat= conn.createStatement();
        PreparedStatement stat=conn.prepareStatement(sql);
        stat.setString(1, kodekategori);
        stat.executeUpdate();
        stat.close();
        conn.commit();
        conn.close();
        System.out.println("Data berhasil dihapus");
    }
    
    public String cariKategori(String kodekategori)throws SQLException{
        String namakategori = "";
        
        String sql="{call cari (?,?)}";
        CallableStatement cstat = conn.prepareCall(sql);
        cstat.setString(1, kodekategori);
        cstat.registerOutParameter(2, java.sql.Types.VARCHAR);
        cstat.execute();
        namakategori=cstat.getString(2);
        cstat.close();
        conn.commit();
        conn.close();
        return namakategori;
    }
    
}
