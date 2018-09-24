/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kategori;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author basisb06
 */
public class KategoriTabelModel extends AbstractTableModel {

    private List<Kategori> kategoribaru = new ArrayList<Kategori>();

    public KategoriTabelModel(List<Kategori> kat) {

        this.kategoribaru = kat;
    }

    @Override
    public int getRowCount() {
        return kategoribaru.size();
    }

    @Override
    public int getColumnCount() {
        return 3;//menampilkan jumlah kolom
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Kategori k = kategoribaru.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex+1;
            case 1:
                return k.getKode();
            case 2:
                return k.getNama();
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
                return "Kode Kategori";
            case 2:
                return "Nama Kategori";
            default:
                ;
                return "";
        }
    }
}
