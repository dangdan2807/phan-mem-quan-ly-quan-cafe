// Author : Pham Dang Dan
// Date   : April 23, 2021

package UI.PanelCustom;

import javax.swing.*;
// import javax.swing.event.*;
import javax.swing.table.*;
// import java.awt.*;
import java.awt.event.*;

public class kPnDataListView extends JPanel implements ActionListener, MouseListener {

    private JPanel pnMain;
    private JButton btnThem, btnXoa, btnXoaTrang, btnSua, btnXem;
    private int widthPn = 467, heightPn = 500;
    private JTable table;
    private DefaultTableModel modelTable;
    private String[] cols = {};
    private String[] selectedRowData;

    public kPnDataListView(String[] column) {
        setLayout(null);
        setSize(widthPn, heightPn);
        pnMain = new JPanel();
        pnMain.setBounds(0, 0, widthPn, heightPn);
        pnMain.setLayout(null);

        JPanel pnBtnPhong = new JPanel();
        pnBtnPhong.setLayout(null);
        pnBtnPhong.setBounds(0, 0, 467, 36);
        pnMain.add(pnBtnPhong);

        btnThem = new JButton("Thêm");
        btnThem.setBounds(0, 0, 75, 36);
        pnBtnPhong.add(btnThem);

        btnXoa = new JButton("Xóa");
        btnXoa.setBounds(87, 0, 75, 36);
        pnBtnPhong.add(btnXoa);

        btnXoaTrang = new JButton("Xóa Trắng");
        btnXoaTrang.setBounds(174, 0, 117, 36);
        pnBtnPhong.add(btnXoaTrang);

        btnSua = new JButton("Sửa");
        btnSua.setBounds(303, 0, 75, 36);
        pnBtnPhong.add(btnSua);

        btnXem = new JButton("Xem");
        btnXem.setBounds(390, 0, 75, 36);
        pnBtnPhong.add(btnXem);

        JPanel pnListViewPhong = new JPanel();
        pnListViewPhong.setLayout(null);
        pnListViewPhong.setBounds(0, 37, 467, 407);
        pnMain.add(pnListViewPhong);

        JScrollPane scpPhong = new JScrollPane();
        scpPhong.setBounds(0, 0, 467, 393);
        pnListViewPhong.add(scpPhong);

        cols = column;
        modelTable = new DefaultTableModel(cols, 0) {
            // khóa không cho người dùng nhập trên table
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };
        table = new JTable(modelTable);
        scpPhong.setViewportView(table);

        this.add(pnMain);

        btnThem.addActionListener(this);
        btnXoa.addActionListener(this);
        btnXoaTrang.addActionListener(this);
        btnSua.addActionListener(this);
        btnXem.addActionListener(this);

        table.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = table.getSelectedRow();
        for (int i = 0; i < cols.length; i++) {
            selectedRowData[i] = modelTable.getValueAt(row, i).toString();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void setWidth(int width) {
        widthPn = width;
    }

    public int getWidth() {
        return widthPn;
    }

    public void setHeight(int height) {
        heightPn = height;
    }

    public int getHeight() {
        return heightPn;
    }

    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getDefaultTableModel() {
        return modelTable;
    }

    public JButton getBtnThem() {
        return btnThem;
    }

    public JButton getBtnSua() {
        return btnSua;
    }

    public JButton getBtnXoa() {
        return btnXoa;
    }

    public JButton getBtnXem() {
        return btnXem;
    }

    public JButton getBtnXoaTrang() {
        return btnXoaTrang;
    }

    public String[] getSelectedRowData() {
        return selectedRowData;
    }
}
