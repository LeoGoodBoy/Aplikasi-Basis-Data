/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Laporan;

import KartuStok.KartuStok;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author basisb09
 */
public class LaporanStokTabelModel extends AbstractTableModel{
    private List<KartuStok> stock = new ArrayList<KartuStok>();

    public LaporanStokTabelModel(List<KartuStok> stok) {

        this.stock = stok;
    }

    @Override
    public int getRowCount() {
        return stock.size();
    }

    @Override
    public int getColumnCount() {
        return 6;//ingat untuk menampilkan jumlah kolom
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        KartuStok k = stock.get(rowIndex);
        DecimalFormat m = new DecimalFormat("###,###,##");
        switch (columnIndex) {
            case 0:
                return rowIndex+1;
            case 1:
                return k.getKodebarang().getKodebarang();
            case 2:
                return k.getKodebarang().getNamabarang();
            case 3:
                return k.getSaldoStok();
            case 4:
                return m.format(k.getKodebarang().getBeliSesudah());
            case 5:
                return m.format(k.getNilaiPersedian());
            default:
                ;
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "No";
            case 1:
                return "Kode Barang";
            case 2:
                return "Nama barang";
            case 3:
                return "Saldo Stok";
            case 4:
                return "Harga Beli";
            case 5:
                return "Nilai Persediaan";
            default:
                ;
                return "";
        }
    }
}
