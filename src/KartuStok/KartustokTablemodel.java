/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KartuStok;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Awenk
 */
public class KartustokTablemodel extends AbstractTableModel {

    private List<KartuStok> stock = new ArrayList<KartuStok>();

    public KartustokTablemodel(List<KartuStok> stok) {

        this.stock = stok;
    }

    @Override
    public int getRowCount() {
        return stock.size();
    }

    @Override
    public int getColumnCount() {
        return 7;//ingat untuk menampilkan jumlah kolom
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        KartuStok k = stock.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        switch (columnIndex) {
            case 0:
                return rowIndex+1;
            case 1:
                return sdf.format(k.getTanggal());
            case 2:
                return k.getNobukti();
            case 3:
                return k.getKodebarang().getNamabarang();
            case 4:
                return k.getMasuk();
            case 5:
                return k.getKeluar();
            case 6:
                return k.getSaldoStok();
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
                return "Tanggal";
            case 2:
                return "No.Bukti";
            case 3:
                return "Barang";
            case 4:
                return "Masuk";
            case 5:
                return "Keluar";
            case 6:
                return "Saldo.Stok";
            default:
                ;
                return "";
        }
    }
}
