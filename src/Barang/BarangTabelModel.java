/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Barang;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author basisb12
 */
public class BarangTabelModel extends AbstractTableModel{
     private List<Barang> barangbaru = new ArrayList<Barang>();

    public BarangTabelModel(List<Barang> br) {

        this.barangbaru = br;
    }

    @Override
    public int getRowCount() {
        return barangbaru.size();
    }

    @Override
    public int getColumnCount() {
        return 11;//menampilkan jumlah kolom
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Barang b = barangbaru.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex+1;
            case 1:
                return b.getKodebarang();
            case 2:
                return b.getNamabarang();
            case 3:
                return b.getKategori().getKode();
            case 4:
                return b.getSatuan();
            case 5:
                return b.getQtymin();
            case 6:
                return b.getQtymax();
            case 7:
                return b.getRitelSesudah();
            case 8:
                return b.getRitelSebelum();
            case 9:
                return b.getBeliSesudah();
            case 10:
                return b.getBeliSebelum();
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
                return "Nama Barang";
            case 3:
                return "Kode Kategori";
            case 4:
                return "Satuan";
            case 5:
                return "QtyMin";
            case 6:
                return "QtyMax";
            case 7:
                return "Ritel Setelah Penjualan";
            case 8:
                return "Ritel Sebelum Penjualan";
            case 9:
                return "Ritel Setelah Pembelian";
            case 10:
                return "Ritel Sebelum Pembelian";
            default:
                ;
                return "";
        }
    }
}
