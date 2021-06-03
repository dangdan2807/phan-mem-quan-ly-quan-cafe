package UI.PanelCustom;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.border.TitledBorder;

public class pnProduct extends JPanel implements interBtn, ActionListener {
    private JTable table;
    private DefaultTableModel modelTable;
    private JTextField txtProductID, txtProductName, txtPrice;
    private JComboBox<String> cboCategory, cboSearchCategory;
    private JButton btnAdd, btnDelete, btnUpdate, btnRefresh, btnLogOut, btnExit;
    private JTextField txtKeyWord;

    public pnProduct() {
        setLayout(null);
        setSize(1270, 630);
        setLayout(new BorderLayout(0, 0));

        JPanel pnTop = new JPanel();
        pnTop.setBackground(Color.WHITE);
        pnTop.setPreferredSize(new Dimension(10, 200));
        pnTop.setLayout(null);
        this.add(pnTop, BorderLayout.NORTH);

        JPanel pnTitle = new JPanel();
        pnTitle.setBackground(Color.WHITE);
        pnTitle.setBounds(0, 0, 1270, 40);
        pnTitle.setBackground(Color.decode("#d0e1fd"));
        pnTop.add(pnTitle);

        JLabel lbTitle = new JLabel("Quản Lý Sản Phẩm");
        lbTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
        lbTitle.setForeground(Color.decode("#1a66e3"));
        pnTitle.add(lbTitle);

        JPanel pnInfo = new JPanel();
        pnInfo.setBorder(
                new TitledBorder(null, "Thông tin sản phẩm ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnInfo.setBackground(Color.WHITE);
        pnInfo.setLayout(null);
        pnInfo.setBounds(0, 41, 1270, 120);
        pnTop.add(pnInfo);

        JLabel lbProductID = new JLabel("Mã sản phẩm: ");
        lbProductID.setBounds(10, 22, 90, 14);
        lbProductID.setBackground(Color.decode("#f9f9f9"));
        pnInfo.add(lbProductID);

        txtProductID = new JTextField();
        txtProductID.setEditable(false);
        txtProductID.setBounds(101, 19, 150, 20);
        pnInfo.add(txtProductID);
        txtProductID.setColumns(10);

        JLabel lbProductName = new JLabel("Tên sản phẩm: ");
        lbProductName.setBounds(10, 48, 90, 16);
        pnInfo.add(lbProductName);

        txtProductName = new JTextField();
        txtProductName.setBounds(101, 46, 150, 20);
        pnInfo.add(txtProductName);
        txtProductName.setColumns(10);

        JLabel lbPrice = new JLabel("Đơn giá: ");
        lbPrice.setBounds(300, 46, 90, 16);
        pnInfo.add(lbPrice);

        txtPrice = new JTextField();
        txtPrice.setBounds(400, 46, 150, 20);
        pnInfo.add(txtPrice);
        txtPrice.setColumns(10);

        JLabel lbCategory = new JLabel("Loại sản phẩm: ");
        lbCategory.setBounds(300, 21, 100, 16);
        pnInfo.add(lbCategory);

        cboCategory = new JComboBox<String>();
        cboCategory.setBounds(400, 19, 150, 20);
        pnInfo.add(cboCategory);

        btnAdd = new JButton("Thêm");
        btnAdd.setBounds(10, 77, 101, 26);
        btnAdd.setBackground(Color.decode("#d0e1fd"));
        btnAdd.setForeground(Color.decode("#1a66e3"));
        pnInfo.add(btnAdd);

        btnDelete = new JButton("Xóa");
        btnDelete.setBounds(123, 77, 101, 26);
        btnDelete.setBackground(Color.decode("#d0e1fd"));
        btnDelete.setForeground(Color.decode("#1a66e3"));
        pnInfo.add(btnDelete);

        btnUpdate = new JButton("Sửa");
        btnUpdate.setBounds(236, 77, 101, 26);
        btnUpdate.setBackground(Color.decode("#d0e1fd"));
        btnUpdate.setForeground(Color.decode("#1a66e3"));
        pnInfo.add(btnUpdate);

        btnRefresh = new JButton("Làm mới");
        btnRefresh.setBounds(349, 77, 101, 26);
        btnRefresh.setBackground(Color.decode("#d0e1fd"));
        btnRefresh.setForeground(Color.decode("#1a66e3"));
        pnInfo.add(btnRefresh);

        btnLogOut = new JButton("Đăng xuất");
        btnLogOut.setBounds(575, 77, 101, 26);
        btnLogOut.setBackground(Color.decode("#d0e1fd"));
        btnLogOut.setForeground(Color.decode("#1a66e3"));
        pnInfo.add(btnLogOut);

        btnExit = new JButton("Thoát");
        btnExit.setBounds(462, 77, 101, 26);
        btnExit.setBackground(Color.decode("#d0e1fd"));
        btnExit.setForeground(Color.decode("#1a66e3"));
        pnInfo.add(btnExit);

        JPanel pnSearch = new JPanel();
        pnSearch.setBackground(Color.WHITE);
        pnSearch.setBounds(0, 161, 1270, 39);
        pnSearch.setLayout(null);
        pnTop.add(pnSearch);

        JLabel lbSearchCategory = new JLabel("Loại sản phẩm: ");
        lbSearchCategory.setBounds(12, 12, 100, 16);
        pnSearch.add(lbSearchCategory);

        cboSearchCategory = new JComboBox<String>();
        cboSearchCategory.setBounds(111, 10, 150, 20);
        pnSearch.add(cboSearchCategory);

        JLabel lbKeyWord = new JLabel("Từ khóa: ");
        lbKeyWord.setBounds(279, 12, 70, 16);
        pnSearch.add(lbKeyWord);

        txtKeyWord = new JTextField();
        txtKeyWord.setBounds(345, 10, 150, 20);
        pnSearch.add(txtKeyWord);
        txtKeyWord.setColumns(10);

        JButton btnSearch = new JButton("Tìm kiếm");
        btnSearch.setForeground(new Color(26, 102, 227));
        btnSearch.setBackground(new Color(208, 225, 253));
        btnSearch.setBounds(507, 7, 101, 26);
        pnSearch.add(btnSearch);

        String[] cols = { "STT", "Mã sản phẩm", "Tên sản phẩm ", "Loại sản phẩm", "Giá" };
        modelTable = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };
        table = new JTable(modelTable);

        JScrollPane scpTable = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scpTable.getViewport().setBackground(Color.WHITE);

        JPanel pnTable = new JPanel();
        pnTable.setBackground(Color.WHITE);
        pnTable.setLayout(new BorderLayout(0, 0));
        pnTable.add(scpTable, BorderLayout.CENTER);
        pnTable.setBounds(10, 25, 1250, 600);

        this.add(pnTable, BorderLayout.CENTER);
        // reSizeColumnTable();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public JButton getBtnLogOut() {
        return btnLogOut;
    }

    @Override
    public JButton getBtnExit() {
        return btnExit;
    }
}
