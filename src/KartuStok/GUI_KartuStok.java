/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KartuStok;

import Barang.Barang;
import Barang.Barang_Kontrol;
import Barang.BarangTabelModel;
import Kategori.Kategori;
import Kategori.Kategori_kontrol;
import Koneksi.Koneksi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author core
 */
public class GUI_KartuStok extends javax.swing.JDialog {

    /**
     * Creates new form GUI_KartuStok
     */
    public GUI_KartuStok(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        Dialog_Barang.setVisible(false);
        Dialog_Barang.setLocationRelativeTo(null);
        Dialog_Barang.setSize(700, 400);
        Dialog_Barang.setTitle("DAFTAR SELURUH BARANG");
        textfield_nobukti.setEditable(false);

        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yy");
        String kodemasuk = "M-" + sf.format(gc.getTime()) + "-";
        String kodekeluar = "K-" + sf.format(gc.getTime()) + "-";
        jDateChooser1.setDate(gc.getTime());
        tampilStok("%");
//        kode();
//        hitungTotalStokDanPersediaan();
    }

    public void KodeOtomatisNoBuktiMasuk() {
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yy");
        String kodemasuk = "M-" + sf.format(gc.getTime()) + "-";
        String bantu;
        int kode;
        try {
            kode = KartuStokKontrol.getkoneksikartustok().KodeOtomatisMasuk() + 1;
            if (kode == 1) {
                bantu = "001";
                textfield_nobukti.setText(kodemasuk + bantu);
            } else if (kode < 10) {
                bantu = "00";
                textfield_nobukti.setText(kodemasuk + bantu + Integer.toString(kode));
            } else if (10 <= kode && kode < 100) {
                bantu = "0";
                textfield_nobukti.setText(kodemasuk + bantu + Integer.toString(kode));
            } else {
                textfield_nobukti.setText(kodemasuk + Integer.toString(kode));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUI_KartuStok.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void KodeOtomatisNoBuktiKeluar() {
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yy");
        String kodekeluar = "K-" + sf.format(gc.getTime()) + "-";
        String bantu;
        int kode;
        try {
            kode = KartuStokKontrol.getkoneksikartustok().KodeOtomatisKeluar() + 1;
            if (kode == 1) {
                bantu = "001";
                textfield_nobukti.setText(kodekeluar + bantu);
            } else if (kode < 10) {
                bantu = "00";
                textfield_nobukti.setText(kodekeluar + bantu + Integer.toString(kode));
            } else if (10 <= kode && kode < 100) {
                bantu = "0";
                textfield_nobukti.setText(kodekeluar + bantu + Integer.toString(kode));
            } else {
                textfield_nobukti.setText(kodekeluar + Integer.toString(kode));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUI_KartuStok.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void bersih() {
        textfield_kategori.setText("");
        textfield_keluar.setText("");
        textfield_keterangan.setText("");
        textfield_kodebarang.setText("");
        textfield_masuk.setText("");
        textfield_namabarang.setText("");
        textfield_nobukti.setText("");
        textfield_satuan.setText("");
    }

//    public void hitungTotalStokDanPersediaan() {
//        int row = tabelstok.getRowCount();
//        int stok1 = 0;
//        int stok2 = 0;
//        int stok3 = 0;
//        long persediaan = 0;
//        for (int i = 0; i < row; i++) {
//            String st1 = tabelstok.getValueAt(i, 5).toString();
//            String st2 = tabelstok.getValueAt(i, 6).toString();
//            stok1 += Integer.parseInt(st1);
//            stok2 += Integer.parseInt(st2);
//
////            String np = tabelLaporan.getValueAt(i, 5).toString();
////            String GbgRibuan[]= np.split("\\,");
////            String nilaiP="";
////            for (int j = 0; j < GbgRibuan.length; j++) {
////                nilaiP = nilaiP + GbgRibuan[j];
////            }
////            persediaan += Long.parseLong(nilaiP);
//        }
////        DecimalFormat m = new DecimalFormat("###,###,##");
//        stok3 = stok1 + stok2;
//        KartuStok k = new KartuStok();
//        k.setSaldoStok(stok3);
//        aa.setText(String.valueOf(stok3));
////        teks_stok.setText(String.valueOf(stok));
////        teks_persediaan.setText(String.valueOf(m.format(persediaan)));
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Dialog_Barang = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_barang = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        labeltransaksikartustovk = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        label_kodebarang = new javax.swing.JLabel();
        textfield_kodebarang = new javax.swing.JTextField();
        label_namabarang = new javax.swing.JLabel();
        textfield_namabarang = new javax.swing.JTextField();
        label_kategori = new javax.swing.JLabel();
        textfield_kategori = new javax.swing.JTextField();
        label_satuan = new javax.swing.JLabel();
        textfield_satuan = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textfield_nobukti = new javax.swing.JTextField();
        label_keterangan = new javax.swing.JLabel();
        textfield_keterangan = new javax.swing.JTextField();
        label_masuk = new javax.swing.JLabel();
        textfield_masuk = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        textfield_keluar = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelstok = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        tombol_keluar = new javax.swing.JButton();
        tombol_simpan = new javax.swing.JButton();
        tombol_batal = new javax.swing.JButton();
        tombol_cetak = new javax.swing.JButton();

        Dialog_Barang.setModal(true);
        Dialog_Barang.setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("DAFTAR SELURUH BARANG"));

        tabel_barang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel_barang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_barangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabel_barang);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout Dialog_BarangLayout = new javax.swing.GroupLayout(Dialog_Barang.getContentPane());
        Dialog_Barang.getContentPane().setLayout(Dialog_BarangLayout);
        Dialog_BarangLayout.setHorizontalGroup(
            Dialog_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(Dialog_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Dialog_BarangLayout.setVerticalGroup(
            Dialog_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 283, Short.MAX_VALUE)
            .addGroup(Dialog_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Dialog_BarangLayout.createSequentialGroup()
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Kartu Stok");
        setAutoRequestFocus(false);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setModalExclusionType(null);
        setModalityType(java.awt.Dialog.ModalityType.DOCUMENT_MODAL);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel2.setBackground(new java.awt.Color(102, 102, 255));

        labeltransaksikartustovk.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        labeltransaksikartustovk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labeltransaksikartustovk.setText("Kartu Stok");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/17.png"))); // NOI18N
        jLabel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Digunakan untuk mencatat transaksi keluar masuknya barang");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labeltransaksikartustovk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(labeltransaksikartustovk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        label_kodebarang.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        label_kodebarang.setText("Kode Barang");

        textfield_kodebarang.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textfield_kodebarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textfield_kodebarangKeyReleased(evt);
            }
        });

        label_namabarang.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        label_namabarang.setText("Nama Barang");

        textfield_namabarang.setEditable(false);
        textfield_namabarang.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        label_kategori.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        label_kategori.setText("Kategori");

        textfield_kategori.setEditable(false);
        textfield_kategori.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        label_satuan.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        label_satuan.setText("Satuan");

        textfield_satuan.setEditable(false);
        textfield_satuan.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jButton1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/1442483749_Search.png"))); // NOI18N
        jButton1.setText("CARI");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label_namabarang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                    .addComponent(label_kodebarang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(textfield_kodebarang, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(textfield_namabarang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(label_kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textfield_kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(label_satuan, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textfield_satuan, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_kodebarang)
                    .addComponent(textfield_kodebarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_namabarang)
                    .addComponent(textfield_namabarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_kategori)
                    .addComponent(textfield_kategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_satuan)
                    .addComponent(textfield_satuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jLabel1.setText("Tanggal");

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jLabel2.setText("No Bukti");

        textfield_nobukti.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        label_keterangan.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        label_keterangan.setText("Keterangan");

        textfield_keterangan.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        label_masuk.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        label_masuk.setText("Masuk");

        textfield_masuk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textfield_masuk.setText("0");
        textfield_masuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfield_masukActionPerformed(evt);
            }
        });
        textfield_masuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textfield_masukKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jLabel3.setText("Keluar");

        textfield_keluar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textfield_keluar.setText("0");
        textfield_keluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textfield_keluarKeyReleased(evt);
            }
        });

        jDateChooser1.setDateFormatString("d-MMM-yyyy");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textfield_nobukti, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textfield_keterangan, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_keterangan, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label_masuk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textfield_masuk, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textfield_keluar, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {textfield_keluar, textfield_masuk});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(label_keterangan)
                    .addComponent(label_masuk)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textfield_nobukti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textfield_keterangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textfield_masuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textfield_keluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Daftar Stock", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 0, 11))); // NOI18N

        tabelstok.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        tabelstok.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "Tanggal", "No Bukti", "Barang", "Masuk", "Keluar", "Saldo.Stok"
            }
        ));
        tabelstok.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabelstok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelstokMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabelstokMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabelstok);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(102, 102, 255));
        jPanel6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        tombol_keluar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tombol_keluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/1442483766_Delete.png"))); // NOI18N
        tombol_keluar.setText("Keluar");
        tombol_keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombol_keluarActionPerformed(evt);
            }
        });

        tombol_simpan.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tombol_simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/1442483718_Save.png"))); // NOI18N
        tombol_simpan.setText("Simpan");
        tombol_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombol_simpanActionPerformed(evt);
            }
        });

        tombol_batal.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tombol_batal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/1442483809_Undo.png"))); // NOI18N
        tombol_batal.setText("Batal");
        tombol_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombol_batalActionPerformed(evt);
            }
        });

        tombol_cetak.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tombol_cetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/1442483829_Print.png"))); // NOI18N
        tombol_cetak.setText("Cetak");
        tombol_cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombol_cetakActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tombol_cetak)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tombol_batal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tombol_simpan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tombol_keluar)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tombol_keluar)
                    .addComponent(tombol_simpan)
                    .addComponent(tombol_batal)
                    .addComponent(tombol_cetak))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {tombol_batal, tombol_cetak, tombol_keluar, tombol_simpan});

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 767, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 532, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabelstokMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelstokMouseClicked
        // TODO add your handling code here:
        int row = tabelstok.getSelectedRow();
        String nobukti = tabelstok.getValueAt(row, 2).toString();
        try {
            KartuStok stok = KartuStokKontrol.getkoneksikartustok().SatuStok(nobukti);
            String no = String.valueOf(stok.getNobukti());
            String masuk = String.valueOf(stok.getMasuk());
            String keluar = String.valueOf(stok.getKeluar());
            Date tgl = stok.getTanggal();
            jDateChooser1.setDate(tgl);
            textfield_nobukti.setText(no);
            textfield_keterangan.setText(stok.getKeterangan());
            textfield_masuk.setText(masuk);
            textfield_keluar.setText(keluar);

            Barang caribarang = Barang_Kontrol.getkoneksibarang().LihatSatuBarang(stok.getKodebarang().getKodebarang());
            Kategori nama = Kategori_kontrol.getkoneksikategori().CariKategori(caribarang.getKategori().getKode());
            textfield_namabarang.setText(caribarang.getNamabarang());
            textfield_kategori.setText(nama.getNama());
            textfield_satuan.setText(caribarang.getSatuan());
            textfield_kodebarang.setText(caribarang.getKodebarang());

            tombol_batal.setText("Hapus");
            textfield_nobukti.setEnabled(false);
            textfield_keluar.setEnabled(true);
            tombol_simpan.setEnabled(true);
            tombol_simpan.setText("Update");
            tombol_keluar.setText("Batal");
        } catch (SQLException ex) {
            Logger.getLogger(GUI_KartuStok.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tabelstokMouseClicked

    private void tabelstokMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelstokMouseReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_tabelstokMouseReleased

    private void tombol_keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombol_keluarActionPerformed
        // TODO add your handling code here:
        if (tombol_keluar.getText().equals("Batal")) {
            tombol_batal.setText("Batal");
            textfield_nobukti.setEnabled(false);
            textfield_keluar.setEnabled(true);
            tombol_simpan.setEnabled(true);
            tombol_simpan.setText("Simpan");
            tombol_keluar.setText("Keluar");
            bersih();
        } else {
            this.setVisible(false);
        }
    }//GEN-LAST:event_tombol_keluarActionPerformed

    private void tombol_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombol_simpanActionPerformed
        // TODO add your handling code here:
        String namatombol = tombol_simpan.getText();
        Date tgl = jDateChooser1.getDate();
        String no = textfield_nobukti.getText();
        String ket = textfield_keterangan.getText();
        int masuk = Integer.parseInt(textfield_masuk.getText());
        int keluar = Integer.parseInt(textfield_keluar.getText());
        String kode = textfield_kodebarang.getText();
        KartuStok stok = new KartuStok();

        Barang barang = new Barang();
        barang.setKodebarang(kode);
        stok.setTanggal(tgl);
        stok.setNobukti(no);
        stok.setKeterangan(ket);
        stok.setMasuk(masuk);
        stok.setKeluar(keluar);
        stok.setKodebarang(barang);

        KartuStokKontrol ktok;

        try {
            if (tombol_simpan.getText().equals("Update")) {
                ktok = KartuStokKontrol.getkoneksikartustok();
                ktok.updateKartustok(stok);
                JOptionPane.showMessageDialog(null, "Data Berhasil DiUpdate");
                tampilStok("%");

                textfield_nobukti.setText("");
                textfield_keterangan.setText("");
                textfield_masuk.setText("");
                textfield_keluar.setText("");
                tombol_simpan.setText("Simpan");
                tombol_batal.setText("Batal");
                tombol_keluar.setText("Keluar");
                textfield_nobukti.setEnabled(false);
            } else {
                ktok = KartuStokKontrol.getkoneksikartustok();
                ktok.tambahkartustok(stok);
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
                tampilStok("%");

                textfield_nobukti.setText("");
                textfield_keterangan.setText("");
                textfield_masuk.setText("0");
                textfield_keluar.setText("0");
                textfield_masuk.setEnabled(true);
                textfield_keluar.setEnabled(true);
                textfield_kodebarang.setText("");
                textfield_namabarang.setText("");
                textfield_satuan.setText("");
                textfield_kategori.setText("");

            }

        } catch (SQLException ex) {
            Logger.getLogger(GUI_KartuStok.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tombol_simpanActionPerformed
//
    private void tombol_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombol_batalActionPerformed
        // TODO add your handling code here:
        KartuStokKontrol ktok;
        if (tombol_batal.getText().equals("Hapus")) {
            try {
                ktok = KartuStokKontrol.getkoneksikartustok();
                ktok.DeleteStok(textfield_nobukti.getText());
                tampilStok("%");
            } catch (SQLException ex) {
                Logger.getLogger(GUI_KartuStok.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            bersih();
        }
    }//GEN-LAST:event_tombol_batalActionPerformed

    private void tombol_cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombol_cetakActionPerformed
        try {
            // TODO add your handling code here:
            String namafile = "./Laporan/CetakStok.jasper";
            Map<String, Object> params = new HashMap<String, Object>();
//            params.put("tgl1", tgl1.getDate());
//            params.put("tgl2", tgl2.getDate());
            JasperPrint jasperPrint = JasperFillManager.fillReport(namafile, params, new Koneksi().getDBConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            Logger.getLogger(GUI_KartuStok.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_tombol_cetakActionPerformed

    private void tabel_barangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_barangMouseClicked
        try {
            int row = tabel_barang.getSelectedRow();
            String kode = tabel_barang.getValueAt(row, 1).toString();
            String namaBarang = tabel_barang.getValueAt(row, 2).toString();
            String kodeKategori = tabel_barang.getValueAt(row, 3).toString();
            String Satuan = tabel_barang.getValueAt(row, 4).toString();
            textfield_kodebarang.setText(kode);
            textfield_namabarang.setText(namaBarang);
            Kategori k = Kategori_kontrol.getkoneksikategori().CariKategori(kodeKategori);
            textfield_kategori.setText(k.getNama());
            textfield_satuan.setText(Satuan);
            Dialog_Barang.setVisible(false);
        } catch (SQLException ex) {
            Logger.getLogger(GUI_KartuStok.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tabel_barangMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        tampilBarang();
        Dialog_Barang.setLocationRelativeTo(null);
        Dialog_Barang.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void textfield_masukKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfield_masukKeyReleased
        // TODO add your handling code here:
        if (tombol_simpan.getText().equals("Simpan")) {
            if (!textfield_masuk.getText().equalsIgnoreCase("")) {
            if (textfield_masuk.getText().equalsIgnoreCase("0")) {
                textfield_keluar.setEditable(true);
                textfield_keluar.requestFocus();
            } else {
                textfield_keluar.setText("0");
                textfield_keluar.setEnabled(false);
                tombol_simpan.setEnabled(true);
            }
        } else {
            textfield_keluar.setEnabled(true);
            textfield_keluar.setText("0");
        }

        KodeOtomatisNoBuktiMasuk();

        }
        

    }//GEN-LAST:event_textfield_masukKeyReleased

    private void textfield_keluarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfield_keluarKeyReleased
        // TODO add your handling code here:
         if (tombol_simpan.getText().equals("Simpan")) {
        if (!textfield_keluar.getText().equalsIgnoreCase("")) {
            if (textfield_keluar.getText().equalsIgnoreCase("0")) {
                textfield_masuk.setEditable(true);
                textfield_masuk.requestFocus();
            } else {
                textfield_masuk.setText("0");
                textfield_masuk.setEnabled(false);
                tombol_simpan.setEnabled(true);
            }
        } else {
            textfield_masuk.setEnabled(true);
            textfield_masuk.setText("0");
        }
        KodeOtomatisNoBuktiKeluar();
         }
    }//GEN-LAST:event_textfield_keluarKeyReleased

    private void textfield_kodebarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfield_kodebarangKeyReleased
        try {
            // TODO add your handling code here:
            Barang b = Barang_Kontrol.getkoneksibarang().LihatSatuBarang(textfield_kodebarang.getText());
            if (b.getKodebarang() != null) {
                textfield_namabarang.setText(b.getNamabarang());
                textfield_kategori.setText(b.getKategori().getKode());
                textfield_satuan.setText(b.getSatuan());
            } else {
                textfield_namabarang.setText("");
                textfield_kategori.setText("");
                textfield_satuan.setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUI_KartuStok.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_textfield_kodebarangKeyReleased

    private void textfield_masukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfield_masukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfield_masukActionPerformed
    public void tampilBarang() {
        try {
            List<Barang> brg = Barang_Kontrol.getkoneksibarang().LihatSemuaBarang();
            BarangTabelModel model = new BarangTabelModel(brg);
            tabel_barang.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(GUI_KartuStok.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void tampilStok(String kode) {
        try {
            List<KartuStok> stok = KartuStokKontrol.getkoneksikartustok().getAllStok(kode);
            KartustokTablemodel model = new KartustokTablemodel(stok);
            tabelstok.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(GUI_KartuStok.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI_KartuStok.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_KartuStok.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_KartuStok.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_KartuStok.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GUI_KartuStok dialog = new GUI_KartuStok(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Dialog_Barang;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel label_kategori;
    private javax.swing.JLabel label_keterangan;
    private javax.swing.JLabel label_kodebarang;
    private javax.swing.JLabel label_masuk;
    private javax.swing.JLabel label_namabarang;
    private javax.swing.JLabel label_satuan;
    private javax.swing.JLabel labeltransaksikartustovk;
    private javax.swing.JTable tabel_barang;
    private javax.swing.JTable tabelstok;
    private javax.swing.JTextField textfield_kategori;
    private javax.swing.JTextField textfield_keluar;
    private javax.swing.JTextField textfield_keterangan;
    private javax.swing.JTextField textfield_kodebarang;
    private javax.swing.JTextField textfield_masuk;
    private javax.swing.JTextField textfield_namabarang;
    private javax.swing.JTextField textfield_nobukti;
    private javax.swing.JTextField textfield_satuan;
    private javax.swing.JButton tombol_batal;
    private javax.swing.JButton tombol_cetak;
    private javax.swing.JButton tombol_keluar;
    private javax.swing.JButton tombol_simpan;
    // End of variables declaration//GEN-END:variables
}
